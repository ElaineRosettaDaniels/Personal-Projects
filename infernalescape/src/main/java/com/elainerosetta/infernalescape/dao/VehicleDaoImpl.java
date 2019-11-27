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
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Asus1
 */
@Repository
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
    public List<Vehicle> getAllVehicles() {
        final String REQ = "SELECT * FROM vehicle";
        return jdbc.query(REQ, new VehicleMapper());
    }

    @Override
    @Transactional
    public Vehicle addVehicle(Vehicle v) {
        final String REQ = "INSERT INTO vehicle(veName, veType, armor, "
                + "speed, dexBonus, hitPoints, damThres, misThres, totalDist, ichorBoosted,"
                + "ichorUses, maxRiders)"
                + "VALUES(?,?,?,?,?,?,?,?,?,?,?,?)";
        jdbc.update(REQ, 
                v.getVeName(),
                v.getVeType(),
                v.getArmor(),
                v.getSpeed(),
                v.getDexBonus(),
                v.getHitPoints(),
                v.getDamThres(),
                v.getMisThres(),
                v.getTotalDist(),
                v.isIchorBoosted(),
                v.getIchorUses(),
                v.getMaxRiders());
        
        int newId = jdbc.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
        v.setVehicleId(newId);
        return v;
    }

    @Override
    @Transactional
    public void updateVehicle(Vehicle v) {
        final String REQ = "UPDATE vehicle SET veName = ?, veType = ?, armor = ?,"
                + " speed = ?, dexBonus = ?, hitPoints = ?, damThres = ?, misThres = ?, "
                + "totalDist = ?, ichorBoosted = ?, ichorUses = ?, maxRiders = ? "
                + "WHERE vehicleId = ?";
        jdbc.update(REQ,
                v.getVeName(), 
                v.getVeType(), 
                v.getArmor(), 
                v.getSpeed(),
                v.getDexBonus(),
                v.getHitPoints(), 
                v.getDamThres(), 
                v.getMisThres(), 
                v.getTotalDist(), 
                v.isIchorBoosted(), 
                v.getIchorUses(), 
                v.getMaxRiders(), 
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
            v.setDexBonus(rs.getInt("dexBonus"));
            v.setHitPoints(rs.getInt("hitPoints"));
            v.setDamThres(rs.getInt("damThres"));
            v.setMisThres(rs.getInt("misThres"));
            v.setTotalDist(rs.getInt("totalDist"));
            v.setIchorBoosted(rs.getBoolean("ichorBoosted"));
            v.setIchorUses(rs.getInt("ichorUses"));
            v.setMaxRiders(rs.getInt("maxRiders"));
            
            return v;
        }
    }
    
}
