/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swg.supertrack.SuperTrack.dao;

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
public class SuperhumanDaoImpl implements SuperhumanDao {
    
    @Autowired
    JdbcTemplate jdbc;

    @Override
    public Superhuman getSuperById(int id) {
        try {
            final String GET_SH_BY_ID = "SELECT * FROM superhuman WHERE superId = ?";
            return jdbc.queryForObject(GET_SH_BY_ID, new SuperhumanMapper(), id);
        } catch(DataAccessException ex) {
            return null;
        }
    }

    @Override
    public List<Superhuman> getAllSupers() {
        final String GET_ALL_SHS = "SELECT * FROM superhuman";
        return jdbc.query(GET_ALL_SHS, new SuperhumanMapper());
    }

    @Override
    @Transactional
    public Superhuman addSuperhuman(Superhuman sH) {
        final String INSERT_SH = "INSERT INTO superhuman(superName, superDossier, alignment, powerClass) VALUES(?,?,?,?)";
        jdbc.update(INSERT_SH,
                sH.getSuperName(),
                sH.getSuperDossier(),
                sH.getAlignment(),
                sH.getPowerClass());
        
        int newId = jdbc.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
        sH.setSuperId(newId);
        return sH;
    }

    @Override
    public void updateSuperhuman(Superhuman sH) {
        final String UPDATE_SH = "UPDATE superhuman SET superName = ?, "
                + "superDossier = ?, alignment = ?, "
                + "powerClass = ? WHERE superId = ?";
        jdbc.update(UPDATE_SH, sH.getSuperName(),
                sH.getSuperDossier(), sH.getAlignment(), 
                sH.getPowerClass(), sH.getSuperId());
    }

    @Override
    @Transactional
    public void deleteSuperhuman(int id) {
        final String DELETE_SIGHT = "DELETE FROM sighting WHERE superId = ?";
        jdbc.update(DELETE_SIGHT, id);
        
        final String DELETE_SUPER_ORG = "DELETE FROM superorg WHERE superId = ?";
        jdbc.update(DELETE_SUPER_ORG, id);
        
        final String DELETE_SH = "DELETE FROM superhuman WHERE superId = ?";
        jdbc.update(DELETE_SH, id);
                
    }

    @Override
    public List<Superhuman> getSupersForOrg(Org org) {
        final String SELECT_SH_FOR_ORG = "SELECT sh.* FROM superhuman sh "
                + "JOIN superorg so ON so.superId = sh.superId WHERE so.orgId = ?";
        return jdbc.query(SELECT_SH_FOR_ORG, new SuperhumanMapper(), org.getOrgId());
    }

    @Override
    public List<Superhuman> getAllSuperVillains() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Superhuman> getSupersByPower(String powerClass) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public static final class SuperhumanMapper implements RowMapper<Superhuman> {

        @Override
        public Superhuman mapRow(ResultSet rs, int index) throws SQLException {
            Superhuman sH = new Superhuman();
            sH.setSuperId(rs.getInt("superId"));
            sH.setSuperName(rs.getString("superName"));
            sH.setSuperDossier(rs.getString("superDossier"));
            sH.setPowerClass(rs.getString("powerClass"));
            sH.setAlignment(rs.getString("alignment"));
            
            return sH;
        }
        
    }
}
