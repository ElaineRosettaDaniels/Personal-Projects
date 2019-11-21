/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.elainerosetta.infernalescape.dao;

import com.elainerosetta.infernalescape.dto.Station;
import com.elainerosetta.infernalescape.dto.Vehicle;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Asus1
 */
public class StationDaoImpl implements StationDao {

    @Autowired
    JdbcTemplate jdbc;
    
    @Override
    public Station getStationById(int stationId) {
        try {
            final String REQ = "SELECT * FROM station WHERE stationId = ?";
            return jdbc.queryForObject(REQ, new StationMapper(), stationId);
        } catch(DataAccessException ex) {
            return null;
        }
    }

    @Override
    public List<Station> getAllStations() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Station> getStationsForVehicle(int vehicleId) {
        final String REQ = "SELECT s.* FROM station s JOIN vehicleStation vs ON "
                + "vs.stationId = s.stationId WHERE vs.vehicleId = ?";
        return jdbc.query(REQ, new StationMapper(), vehicleId);
    }

    @Override
    @Transactional
    public Station addStation(Station s) {
        final String REQ = "INSERT INTO station(stName, armorBonus, stAction, crewed) "
                + "VALUES(?,?,?,?)";
        jdbc.update(REQ, 
                s.getName(), 
                s.getArmorBonus(), 
                s.getStAction(), 
                s.isCrewed());
        
        int newId = jdbc.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
        s.setStationId(newId);
        return s;
    }
    
    @Override
    @Transactional
    public void addStationToVehicle(Station s, Vehicle v) {
        final String REQ = "INSERT INTO vehicleStations(stationId, vehicleId) "
                + "VALUES (?,?)";
        jdbc.update(REQ, 
                s.getStationId(), 
                v.getVehicleId());
    }

    @Override
    @Transactional
    public void updateStation(Station station) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void deleteStation(Station station) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public static final class StationMapper implements RowMapper<Station> {
        
        @Override
        public Station mapRow(ResultSet rs, int index) throws SQLException {
            Station s = new Station();
            s.setStationId(rs.getInt("stationId"));
            s.setName(rs.getString("stName"));
            s.setArmorBonus(rs.getInt("armorBonus"));
            s.setStAction(rs.getString("stAction"));
            s.setCrewed(rs.getBoolean("crewed"));
            
            return s;
        }
    }
    
}
