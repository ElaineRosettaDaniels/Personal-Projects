/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.elainerosetta.infernalescape.dao;

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
public class VehicleDaoImpl implements VehicleDao {
    
    @Autowired
    JdbcTemplate jdbc;
    
    @Override
    public Vehicle getVehicleById(int vehicleId) {
        try {
            final String REQ = "SELECT * FROM vehicle WHERE vehicleId = ?";
            return jdbc.queryForObject(REQ, new VehicleMapper(), vehicleId);
        } catch(DataAccessException ex) {
            return null;
        }
    }

    @Override
    public List<Vehicle> getAllVehicle() {
        final String REQ = "SELECT * FROM vehicle";
        return jdbc.query(REQ, new VehicleMapper());
    }

    @Override
    @Transactional
    public Vehicle addVehicle(Vehicle v) {
        final String REQ = "INSERT INTO vehicle(veName, veType, armor, "
                + "speed, hitPoints, damThres, misThres, position, ichorBoosted,"
                + "ichorUses, maxCrew)"
                + "VALUES(?,?,?,?,?,?,?,?,?,?,?)";
        jdbc.update(REQ, 
                v.getVeName(),
                v.getVeType(),
                v.getArmor(),
                v.getSpeed(),
                v.getHitPoints(),
                v.getDamThres(),
                v.getMisThres(),
                v.getPosition(),
                v.isIchorBoosted(),
                v.getIchorUses(),
                v.getMaxCrew());
        
        int newId = jdbc.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
        v.setVehicleId(newId);
        return v;
    }

    @Override
    @Transactional
    public void updateVehicle(Vehicle v) {
        final String REQ = "UPDATE tavern SET veName = ?, veType = ?, armor = ?,"
                + " speed = ?, hitPoints = ?, damThres = ?, misThres = ?, "
                + "position = ?, ichorBoosted = ?, ichorUses = ?, maxCrew = ? "
                + "WHERE vehicleId = ?";
        jdbc.update(REQ,
                v.getVeName(), 
                v.getVeType(), 
                v.getArmor(), 
                v.getSpeed(), 
                v.getHitPoints(), 
                v.getDamThres(), 
                v.getMisThres(), 
                v.getPosition(), 
                v.isIchorBoosted(), 
                v.getIchorUses(), 
                v.getMaxCrew(), 
                v.getVehicleId());
    }

    @Override
    @Transactional
    public void deleteVehicle(int vehicleId) {
        final String DELETE_VEHICLE_RIDERS = "DELETE FROM vehicleRiders WHERE vehicleId = ?";
        jdbc.update(DELETE_VEHICLE_RIDERS, vehicleId);
        
        final String DELETE_VEHICLE_STATIONS = "DELETE FROM vehicleStations WHERE vehicleId = ?";
        jdbc.update(DELETE_VEHICLE_STATIONS, vehicleId);
        
        final String DELETE_VEHICLE = "DELETE FROM vehicle WHERE vehicleId = ?";
        jdbc.update(DELETE_VEHICLE, vehicleId);
    }
    
    public static final class VehicleMapper implements RowMapper<Vehicle> {
        
        @Override
        public Vehicle mapRow(ResultSet rs, int index) throws SQLException {
            Vehicle v = new Vehicle();
            v.setVehicleId(rs.getInt("vehicleId"));
            v.setVeName(rs.getString("veName"));
            v.setVeType(rs.getString("veType"));
            v.setArmor(rs.getInt("armor"));
            v.setSpeed(rs.getInt("speed"));
            v.setHitPoints(rs.getInt("hitPoints"));
            v.setDamThres(rs.getInt("damThres"));
            v.setMisThres(rs.getInt("misThres"));
            v.setPosition(rs.getInt("position"));
            v.setIchorBoosted(rs.getBoolean("ichorBoosted"));
            v.setIchorUses(rs.getInt("ichorUses"));
            v.setMaxCrew(rs.getInt("maxCrew"));
            
            return v;
        }
    }
    
}
