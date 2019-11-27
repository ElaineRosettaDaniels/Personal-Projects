/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.elainerosetta.infernalescape.service;

import com.elainerosetta.infernalescape.dto.Rider;
import com.elainerosetta.infernalescape.dto.Station;
import com.elainerosetta.infernalescape.dto.Vehicle;
import java.util.List;

/**
 *
 * @author Asus1
 */
public interface ServiceLayer {

    int rollDie(int numRolls, int die);
    int atkHarpoon(Vehicle atk, Vehicle tar);
    void checkDamVsThres(int dam, Vehicle tar);
    
    // VehicleDao passthru
    Vehicle getVehicleById(int vehicleId);
    List<Vehicle> getAllVehicles();
    Vehicle addVehicle(Vehicle v);
    void updateVehicle(Vehicle v);
    void deleteVehicle(int vehicleId);
    
    // StationDao passthru
    Station getStationById(int stationId);
    List<Station> getAllStations();
    List<Station> getStationsForVehicle(int vehicleId);
    Station addStation(Station s);
    void addStationToVehicle(Station s, Vehicle v);
    void updateStation(Station s);
    void deleteStation(Station s);
    
    // RiderDao passthru
    Rider getRiderById(int riderId);
    List<Rider> getAllRiders();
    List<Rider> getRidersForVehicle(int vehicleId);
    Rider addRider(Rider r);
    void addRiderToVehicle(Rider r, Vehicle v);
    void addRiderToStation(Rider r, Station s);
    void updateRider(Rider r);
    void deleteRider(Rider r);
}
