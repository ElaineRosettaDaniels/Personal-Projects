/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swg.supertrack.SuperTrack.dao;

import com.swg.supertrack.SuperTrack.dao.LocaleDaoImpl.LocaleMapper;
import com.swg.supertrack.SuperTrack.dao.SuperhumanDaoImpl.SuperhumanMapper;
import com.swg.supertrack.SuperTrack.dto.Locale;
import com.swg.supertrack.SuperTrack.dto.Sighting;
import com.swg.supertrack.SuperTrack.dto.Superhuman;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
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
public class SightingDaoImpl implements SightingDao {
    
    @Autowired
    JdbcTemplate jdbc;

    @Override
    public Sighting getSightById(int id) {
        try {
            final String SELECT_SIGHT_BY_ID = "SELECT * FROM sighting WHERE sightId = ?";
            Sighting sight = jdbc.queryForObject(SELECT_SIGHT_BY_ID, new SightingMapper(), id);
            sight.setLocale(getLocaleForSight(id));
            sight.setSuperhuman(getSuperForSight(id));
            return sight;
        } catch(DataAccessException ex) {
            return null;
        }
    }
    
    private Superhuman getSuperForSight(int id) {
        final String SELECT_SUPER_FOR_SIGHT = "SELECT sh.* FROM superhuman sh "
                + "JOIN sighting si ON si.superId = sh.superId WHERE si.sightId = ?";
        return jdbc.queryForObject(SELECT_SUPER_FOR_SIGHT, new SuperhumanMapper(), id);
    }
    
    private Locale getLocaleForSight(int id) {
        final String SELECT_LOCALE_FOR_SIGHT = "SELECT l.* FROM locale l "
                + "JOIN sighting si ON si.localeId = l.localeId WHERE si.sightId = ?";
        return jdbc.queryForObject(SELECT_LOCALE_FOR_SIGHT, new LocaleMapper(), id);
    }

    @Override
    public List<Sighting> getAllSightings() {
        final String SELECT_ALL_SIGHTS = "SELECT * FROM sighting";
        List<Sighting> sights = jdbc.query(SELECT_ALL_SIGHTS, new SightingMapper());
        associateSuperAndLocale(sights);
        return sights;
    }
    
    private void associateSuperAndLocale(List<Sighting> sights) {
        for (Sighting sight : sights) {
            sight.setLocale(getLocaleForSight(sight.getSightId()));
            sight.setSuperhuman(getSuperForSight(sight.getSightId()));
        }
    }

    @Override
    @Transactional
    public Sighting addSighting(Sighting sight) {
        final String INSERT_SIGHT = "INSERT INTO sighting(sightDate, superId, localeId) "
                + "VALUES(?,?,?)";
        jdbc.update(INSERT_SIGHT, 
                sight.getSightDate(), 
                sight.getSuperhuman().getSuperId(), 
                sight.getLocale().getLocaleId());
        
        int newId = jdbc.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
        sight.setSightId(newId);
        return sight;
    }

    @Override
    @Transactional
    public void updateSighting(Sighting sight) {
        final String UPDATE_SIGHT = "UPDATE sighting SET sightDate = ?, "
                + "superId = ?, localeId = ? WHERE sightId = ?";
        jdbc.update(UPDATE_SIGHT,
                sight.getSightDate(), 
                sight.getSuperhuman().getSuperId(), 
                sight.getLocale().getLocaleId(), 
                sight.getSightId());
    }

    @Override
    @Transactional
    public void deleteSighting(int id) {
        final String DELETE_SIGHT = "DELETE FROM sighting WHERE sightId = ?";
        jdbc.update(DELETE_SIGHT, id);
    }

    @Override
    public List<Sighting> getSightsBySuper(Superhuman superhuman) {
        final String SELECT_SIGHTS_FOR_SUPER = "SELECT * FROM sighting WHERE superId = ?";
        List<Sighting> sights = jdbc.query(SELECT_SIGHTS_FOR_SUPER, 
                new SightingMapper(), superhuman.getSuperId());
        associateSuperAndLocale(sights);
        return sights;
    }

    @Override
    public List<Sighting> getSightsByLocale(Locale locale) {
        final String SELECT_SIGHTS_FOR_LOCALE = "SELECT * FROM sighting WHERE localeId = ?";
        List<Sighting> sights = jdbc.query(SELECT_SIGHTS_FOR_LOCALE, 
                new SightingMapper(), locale.getLocaleId());
        associateSuperAndLocale(sights);
        return sights;
    }

    @Override
    public List<Sighting> getSightsByDate(LocalDate date) {
        final String SELECT_SIGHTS_FOR_DATE = "SELECT * FROM sighting WHERE sightDate = ?";
        List<Sighting> sights = jdbc.query(SELECT_SIGHTS_FOR_DATE, 
                new SightingMapper(), date);
        associateSuperAndLocale(sights);
        return sights;
    }
    
    public static final class SightingMapper implements RowMapper<Sighting> {

        @Override
        public Sighting mapRow(ResultSet rs, int index) throws SQLException {
            Sighting sight = new Sighting();
            sight.setSightId(rs.getInt("sightId"));
            sight.setSightDate(rs.getObject("sightDate", LocalDate.class));
            return sight;
        }
        
    }
}
