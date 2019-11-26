/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.elainerosetta.infernalescape.dao;

import com.elainerosetta.infernalescape.dto.Rider;
import com.elainerosetta.infernalescape.dto.Station;
import com.elainerosetta.infernalescape.dto.Vehicle;
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
public class RiderDaoImpl implements RiderDao {

    
    @Autowired
    JdbcTemplate jdbc;
    
    @Override
    public Rider getRiderById(int riderId) {
        try {
            final String REQ = "SELECT * FROM rider WHERE riderId = ?";
            return jdbc.queryForObject(REQ, new RiderMapper(), riderId);
        } catch(DataAccessException ex) {
            return null;
        }
    }

    @Override
    public List<Rider> getAllRiders() {
        final String REQ = "SELECT * FROM rider";
        return jdbc.query(REQ, new RiderMapper());
    }

    @Override
    public List<Rider> getRidersForVehicle(int vehicleId) {
        final String REQ = "SELECT r.* FROM rider r JOIN vehicleRiders vr ON "
                + "vr.riderId = r.riderId WHERE vr.vehicleId = ?";
        return jdbc.query(REQ, new RiderMapper(), vehicleId);
    }

    @Override
    @Transactional
    public Rider addRider(Rider r) {
        final String REQ = "INSERT INTO rider(riName, armor, hitPoints) "
                + "VALUES(?,?,?)";
        jdbc.update(REQ, 
                r.getName(), 
                r.getArmor(), 
                r.getHitPoints());
        
        int newId = jdbc.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
        r.setRiderId(newId);
        return r;
    }
    
    @Override
    @Transactional
    public void addRiderToVehicle(Rider r, Vehicle v) {
        final String REQ = "INSERT INTO vehicleRiders(vehicleId, riderId) "
                + "VALUES (?,?)";
        jdbc.update(REQ, 
                v.getVehicleId(), 
                r.getRiderId());
    }
    
    @Override
    @Transactional
    public void addRiderToStation(Rider r, Station s) {
        final String REQ = "INSERT INTO stationRider(stationId, riderId) "
                + "VALUES(?,?)";
        jdbc.update(REQ, 
                s.getStationId(), 
                r.getRiderId());
    }

    @Override
    @Transactional
    public void updateRider(Rider r) {
        final String REQ = "UPDATE rider SET riName = ?, armor = ?, hitPoints = ? "
                + "WHERE riderId = ?";
        jdbc.update(REQ, 
                r.getName(), 
                r.getArmor(), 
                r.getHitPoints(), 
                r.getRiderId());
    }

    @Override
    @Transactional
    public void deleteRider(Rider r) {
        final String DELETE_VR = "DELETE vr.* FROM vehicleRiders vr "
                + "WHERE riderId = ?";
        jdbc.update(DELETE_VR, r.getRiderId());
        
        final String DELETE_SR = "DELETE sr.* FROM stationRider sr "
                + "WHERE riderId = ?";
        jdbc.update(DELETE_SR, r.getRiderId());
        
        final String DELETE_R = "DELETE FROM rider WHERE riderId = ?";
        jdbc.update(DELETE_R, r.getRiderId());
    }

    

    public static final class RiderMapper implements RowMapper<Rider> {
        
        @Override
        public Rider mapRow(ResultSet rs, int index) throws SQLException {
            Rider r = new Rider();
            r.setRiderId(rs.getInt("riderId"));
            r.setName(rs.getString("riName"));
            r.setArmor(rs.getInt("armor"));
            r.setHitPoints(rs.getInt("hitPoints"));
            
            return r;
        }
    }
    
}
