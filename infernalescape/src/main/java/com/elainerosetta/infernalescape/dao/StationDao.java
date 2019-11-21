/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.elainerosetta.infernalescape.dao;

import com.elainerosetta.infernalescape.dto.Station;
import com.elainerosetta.infernalescape.dto.Vehicle;
import java.util.List;

/**
 *
 * @author Asus1
 */
public interface StationDao {
    
    Station getStationById(int stationId);
    List<Station> getAllStations();
    List<Station> getStationsForVehicle(int vehicleId);
    Station addStation(Station s);
    void addStationToVehicle(Station s, Vehicle v);
    void updateStation(Station s);
    void deleteStation(Station s);
}
