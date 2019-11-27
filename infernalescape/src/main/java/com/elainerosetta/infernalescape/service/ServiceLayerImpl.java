/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.elainerosetta.infernalescape.service;

import com.elainerosetta.infernalescape.dao.RiderDao;
import com.elainerosetta.infernalescape.dao.StationDao;
import com.elainerosetta.infernalescape.dao.VehicleDao;
import com.elainerosetta.infernalescape.dto.Rider;
import com.elainerosetta.infernalescape.dto.Station;
import com.elainerosetta.infernalescape.dto.Vehicle;
import static java.lang.Math.random;
import java.util.List;
import java.util.Random;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Asus1
 */
@Repository
public class ServiceLayerImpl implements ServiceLayer {

    @Autowired
    VehicleDao veDao;
    
    @Autowired
    StationDao stDao;
    
    @Autowired
    RiderDao riDao;
    
    

    public ServiceLayerImpl() {}
    
    public Random random = new Random();
    
    @Override
    public int rollDie(int numRolls, int die) {
        int total = 0;
        for (int i = 0; i < numRolls; i++) {
            total += random.nextInt(die) + 1;
        }
        return total;
    }
    
    // Harpoon Flinger attack method
    @Override
    public int atkHarpoon(Vehicle atk, Vehicle tar) {
        // Distance between atkV and tarV:
        int distBtwn = Math.abs(tar.getTotalDist() - atk.getTotalDist());
        int hitRoll = 0;
        int damage = 0;
        // if atkV is in range of tarV, roll to hit:
        if (distBtwn <= 120) {
            hitRoll = rollDie(1, 20) + 5 + atk.getDexBonus(); // 5 is harpoon's base hit bonus
        }
        // if hitRoll can hit target's AC, roll damage (2d8 + dex):
        if (hitRoll >= tar.getArmor()) {
            damage = rollDie(2, 8) + atk.getDexBonus();
        }
        return damage;
    }
    
    @Override
    public void checkDamVsThres(int dam, Vehicle tar) {
        if (dam >= tar.getDamThres()) {
            tar.setHitPoints(tar.getHitPoints() - dam);
        }
        veDao.updateVehicle(tar);
    }
    
    // VehicleDao passthru
    
    @Override
    public Vehicle getVehicleById(int vehicleId) {
        return veDao.getVehicleById(vehicleId);
    }

    @Override
    public List<Vehicle> getAllVehicles() {
        return veDao.getAllVehicles();
    }

    @Override
    public Vehicle addVehicle(Vehicle v) {
        return veDao.addVehicle(v);
    }

    @Override
    public void updateVehicle(Vehicle v) {
        veDao.updateVehicle(v);
    }

    @Override
    public void deleteVehicle(int vehicleId) {
        veDao.deleteVehicle(vehicleId);
    }
    
    // StationDao passthru

    @Override
    public Station getStationById(int stationId) {
        return stDao.getStationById(stationId);
    }

    @Override
    public List<Station> getAllStations() {
        return stDao.getAllStations();
    }

    @Override
    public List<Station> getStationsForVehicle(int vehicleId) {
        return stDao.getStationsForVehicle(vehicleId);
    }

    @Override
    public Station addStation(Station s) {
        return stDao.addStation(s);
    }

    @Override
    public void addStationToVehicle(Station s, Vehicle v) {
        stDao.addStationToVehicle(s, v);
    }

    @Override
    public void updateStation(Station s) {
        stDao.updateStation(s);
    }

    @Override
    public void deleteStation(Station s) {
        stDao.deleteStation(s);
    }
    
    // RiderDao passthru

    @Override
    public Rider getRiderById(int riderId) {
        return riDao.getRiderById(riderId);
    }

    @Override
    public List<Rider> getAllRiders() {
        return riDao.getAllRiders();
    }

    @Override
    public List<Rider> getRidersForVehicle(int vehicleId) {
        return riDao.getRidersForVehicle(vehicleId);
    }

    @Override
    public Rider addRider(Rider r) {
        return riDao.addRider(r);
    }

    @Override
    public void addRiderToVehicle(Rider r, Vehicle v) {
        riDao.addRiderToVehicle(r, v);
    }

    @Override
    public void addRiderToStation(Rider r, Station s) {
        riDao.addRiderToStation(r, s);
    }

    @Override
    public void updateRider(Rider r) {
        riDao.updateRider(r);
    }

    @Override
    public void deleteRider(Rider r) {
        riDao.deleteRider(r);
    }

    

    

    
    
}
