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
    
    /*
        Hey Ellie, we're gonna have to do something about these. It's kinda sloppy right now,
    but I think better that we have them so we can actually make some progress on the game. 
    Gotta stay in the groove. Anyway, we need to figure out a way to differentiate between
    actions that require an attack roll vs. actions that need a saving throw. If you can't build it, 
    find someone online who already has. But for now, let's make this game with just these
    handful of stations so we can have something working. 
        Keep at it, love you.
        -Elaine
    */
    
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
    public int atkScythes(Vehicle atk, Vehicle tar) {
        int distBtwn = Math.abs(tar.getTotalDist() - atk.getTotalDist());
        int saveRoll = 0;
        int damage = 0;
        int dC = 13;
        if (distBtwn <= 5) {
            saveRoll = rollDie(1, 20) + tar.getDexBonus();
        }
        if (saveRoll < dC) {
            damage = rollDie(2, 10) + atk.getDexBonus();
        }
        return damage;
    }

    @Override
    public int atkWreckBall(Vehicle atk, Vehicle tar) {
        int distBtwn = Math.abs(tar.getTotalDist() - atk.getTotalDist());
        int hitRoll = 0;
        int damage = 0;
        if (distBtwn <= 15) {
            hitRoll = rollDie(1, 20) + 5 + atk.getStrBonus();
        }
        if (hitRoll >= tar.getArmor()) {
            damage = rollDie(8, 8) + atk.getStrBonus();
        }
        return damage;
    }

    @Override
    public int atkChomper(Vehicle atk, Vehicle tar) {
        int distBtwn = Math.abs(tar.getTotalDist() - atk.getTotalDist());
        int hitRoll = 0;
        int damage = 0;
        if (distBtwn <= 5) {
            hitRoll = rollDie(1, 20) + 5 + atk.getStrBonus();
        }
        if (hitRoll >= tar.getArmor()) {
            damage = rollDie(6, 6) + atk.getStrBonus();
        }
        return damage;
    }
    
    @Override
    public int atkAcid(Vehicle atk, Vehicle tar) {
        int distBtwn = Math.abs(tar.getTotalDist() - atk.getTotalDist());
        int saveRoll = 0;
        int damage = 0;
        int dC = 12;
        if (distBtwn <= 30) {
            saveRoll = rollDie(1, 20) + tar.getDexBonus();
        }
        // full dmg on fail
        if (saveRoll < dC) {
            damage = rollDie(9, 8);
        }
        // half dmg on success
        if (saveRoll >= dC) {
            damage = rollDie(9, 8) / 2;
        }
        return damage;
    }
    
    @Override
    public int atkFlame(Vehicle atk, Vehicle tar) {
        int distBtwn = Math.abs(tar.getTotalDist() - atk.getTotalDist());
        int saveRoll = 0;
        int damage = 0;
        int dC = 15;
        if (distBtwn <= 60) {
            saveRoll = rollDie(1, 20) + tar.getDexBonus();
        }
        // full dmg on fail
        if (saveRoll < dC) {
            damage = rollDie(4, 8);
        }
        // half dmg on success
        if (saveRoll >= dC) {
            damage = rollDie(4, 8) / 2;
        }
        return damage;
    }
    
    @Override
    public void checkDamVsThresAndDeal(int dam, Vehicle tar) {
        if (dam >= tar.getDamThres()) {
            tar.setHitPoints(tar.getHitPoints() - dam);
        }
        veDao.updateVehicle(tar);
    }
    
    // Method to choose which station attack is being used:
    
    @Override
    public int getActionAndMakeAttack(String stAction, Vehicle atk, Vehicle tar) {
        int dmg = 0;
        switch (stAction) {
            case "Harpoon":
               dmg = atkHarpoon(atk, tar);
               break;
            case "Scythes":
                dmg = atkScythes(atk, tar);
                break;
            case "WreckBall":
                dmg = atkWreckBall(atk, tar);
                break;
            case "Acid":
                dmg = atkAcid(atk, tar);
                break;
            case "Flame":
                dmg = atkFlame(atk, tar);
                break;
        }
        return dmg;
    }
    
    // Don't forget to update in controller method after doing this:
    
    @Override
    public Vehicle moveVehicleUpToSpeed(Vehicle v) {
        int vPosition = v.getTotalDist();
        int vSpd = v.getSpeed();
        vPosition += vSpd;
        // Ichor boost adds an extra 30 ft whenever it moves
        if (v.isIchorBoosted()) {
            vPosition += 30;
        }
        v.setTotalDist(vPosition);
        return v;
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
