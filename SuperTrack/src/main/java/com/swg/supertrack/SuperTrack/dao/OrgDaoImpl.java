/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swg.supertrack.SuperTrack.dao;

import com.swg.supertrack.SuperTrack.dao.LocaleDaoImpl.LocaleMapper;
import com.swg.supertrack.SuperTrack.dao.SuperhumanDaoImpl.SuperhumanMapper;
import com.swg.supertrack.SuperTrack.dto.Locale;
import com.swg.supertrack.SuperTrack.dto.Org;
import com.swg.supertrack.SuperTrack.dto.Superhuman;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Asus1
 */
@Repository
public class OrgDaoImpl implements OrgDao {
    
    @Autowired
    JdbcTemplate jdbc;

    @Override
    public Org getOrgById(int id) {
        try {
            final String SELECT_ORG_BY_ID = "SELECT * FROM org WHERE orgId = ?";
            Org org = jdbc.queryForObject(SELECT_ORG_BY_ID, new OrgMapper(), id);
            org.setLocale(getLocaleForOrg(id));
            org.setOrgMembers(getSupersForOrg(id));
            return org;
        } catch(DataAccessException ex) {
            return null;
        }
    }
    
    private Locale getLocaleForOrg(int id) {
        final String SELECT_LOCALE_FOR_ORG = "SELECT l.* FROM locale l "
                + "JOIN org o ON o.localeId = l.localeId WHERE o.orgId = ?";
        return jdbc.queryForObject(SELECT_LOCALE_FOR_ORG, new LocaleMapper(), id);
    }
    
    private List<Superhuman> getSupersForOrg(int id) {
        final String SELECT_SH_FOR_ORG = "SELECT sh.* FROM superhuman sh "
                + "JOIN superorg so ON so.superId = sh.superId WHERE so.orgId = ?";
        return jdbc.query(SELECT_SH_FOR_ORG, new SuperhumanMapper(), id);
    }

    @Override
    public List<Org> getAllOrgs() {
        final String SELECT_ALL_ORGS = "SELECT * FROM org";
        List<Org> orgs = jdbc.query(SELECT_ALL_ORGS, new OrgMapper());
        associateLocaleAndMembers(orgs);
        return orgs;
    }
    
    private void associateLocaleAndMembers(List<Org> orgs) {
        for (Org org : orgs) {
            org.setLocale(getLocaleForOrg(org.getOrgId()));
            org.setOrgMembers(getSupersForOrg(org.getOrgId()));
        }
    }

    @Override
    @Transactional
    public Org addOrg(Org org) {
        final String INSERT_ORG = "INSERT INTO org(orgName, orgDossier, localeId) "
                + "VALUES(?,?,?)";
        jdbc.update(INSERT_ORG,
                org.getOrgName(),
                org.getOrgDossier(),
                org.getLocale().getLocaleId());
        
        int newId = jdbc.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
        org.setOrgId(newId);
        insertOrgMember(org);
        return org;
    }
    
    private void insertOrgMember(Org org) {
        final String INSERT_ORG_SUPER = "INSERT INTO "
                + "superorg(superId, orgId) VALUES(?,?)";
        for (Superhuman sH : org.getOrgMembers()) {
            jdbc.update(INSERT_ORG_SUPER,
                    sH.getSuperId(), 
                    org.getOrgId());
        }
    }

    @Override
    @Transactional
    public void updateOrg(Org org) {
        final String UPDATE_ORG = "UPDATE org SET orgName = ?, orgDossier = ?,"
                + "localeId = ? WHERE orgId = ?";
        jdbc.update(UPDATE_ORG,
                org.getOrgName(),
                org.getOrgDossier(),
                org.getLocale().getLocaleId(),
                org.getOrgId());
        
        final String DELETE_SUPER_ORG = "DELETE FROM superorg WHERE orgId = ?";
        jdbc.update(DELETE_SUPER_ORG, org.getOrgId());
        insertOrgMember(org);
    }

    @Override
    @Transactional
    public void deleteOrg(int id) {
        final String DELETE_SUPER_ORG = "DELETE FROM superorg WHERE orgId = ?";
        jdbc.update(DELETE_SUPER_ORG, id);
        
        final String DELETE_ORG = "DELETE FROM org WHERE orgId = ?";
        jdbc.update(DELETE_ORG, id);
    }

    @Override
    public List<Org> getOrgsForSuper(Superhuman superhuman) {
        final String SELECT_ORGS_FOR_SUPER = "SELECT o.* FROM org o JOIN "
                + "superorg so ON so.orgId = o.orgId WHERE so.superId = ?";
        List<Org> orgs = jdbc.query(SELECT_ORGS_FOR_SUPER, new OrgMapper(), superhuman.getSuperId());
        associateLocaleAndMembers(orgs);
        return orgs;
    }
    
    public static final class OrgMapper implements RowMapper<Org> {
        
        @Override
        public Org mapRow(ResultSet rs, int index) throws SQLException {
            Org org = new Org();
            org.setOrgId(rs.getInt("orgId"));
            org.setOrgName(rs.getString("orgName"));
            org.setOrgDossier(rs.getString("orgDossier"));
            return org;
        }
    }
    
}
