/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swg.supertrack.SuperTrack.dao;

import com.swg.supertrack.SuperTrack.dto.Locale;
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
public class LocaleDaoImpl implements LocaleDao {
    
    @Autowired
    JdbcTemplate jdbc;

    @Override
    public Locale getLocaleById(int id) {
        try {
            final String SELECT_LOCALE_BY_ID = "SELECT * FROM locale WHERE localeId = ?";
            return jdbc.queryForObject(SELECT_LOCALE_BY_ID, new LocaleMapper(), id);
        } catch (DataAccessException ex) {
            return null;
        }
    }

    @Override
    public List<Locale> getAllLocales() {
        final String SELECT_ALL_LOCALES = "SELECT * FROM locale";
        return jdbc.query(SELECT_ALL_LOCALES, new LocaleMapper());
    }

    @Override
    @Transactional
    public Locale addLocale(Locale locale) {
        final String INSERT_LOCALE = "INSERT INTO locale(localeName, localeDossier, "
                + "address, district, city, country, longLat) VALUES(?,?,?,?,?,?,?)";
        jdbc.update(INSERT_LOCALE, 
                locale.getLocaleName(), 
                locale.getLocaleDossier(), 
                locale.getAddress(), 
                locale.getDistrict(), 
                locale.getCity(), 
                locale.getCountry(), 
                locale.getLongLat());
        
        int newId = jdbc.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
        locale.setLocaleId(newId);
        return locale;
    }

    @Override
    public void updateLocale(Locale locale) {
        final String UPDATE_LOCALE = "UPDATE locale SET "
                + "localeName = ?, "
                + "localeDossier = ?, "
                + "address = ?, "
                + "district = ?, "
                + "city = ?, "
                + "country = ?, "
                + "longLat = ? "
                + "WHERE LocaleId = ?";
        jdbc.update(UPDATE_LOCALE, 
                locale.getLocaleName(), 
                locale.getLocaleDossier(), 
                locale.getAddress(), 
                locale.getDistrict(),
                locale.getCity(), 
                locale.getCountry(), 
                locale.getLongLat(), 
                locale.getLocaleId());
    }

    @Override
    @Transactional
    public void deleteLocale(int id) {
        final String DELETE_LOCALE_SIGHT = "DELETE FROM sighting WHERE localeId = ?";
        jdbc.update(DELETE_LOCALE_SIGHT, id);
        
        final String DELETE_LOCALE = "DELETE FROM locale WHERE localeId = ?";
        jdbc.update(DELETE_LOCALE, id);
    }
    
    public static final class LocaleMapper implements RowMapper<Locale> {

        @Override
        public Locale mapRow(ResultSet rs, int index) throws SQLException {
            Locale locale = new Locale();
            locale.setLocaleId(rs.getInt("localeId"));
            locale.setLocaleName(rs.getString("localeName"));
            locale.setLocaleDossier(rs.getString("localeDossier"));
            locale.setAddress(rs.getString("address"));
            locale.setDistrict(rs.getString("district"));
            locale.setCity(rs.getString("city"));
            locale.setCountry(rs.getString("country"));
            locale.setLongLat(rs.getString("longLat"));
            return locale;
        }
        
    }
}
