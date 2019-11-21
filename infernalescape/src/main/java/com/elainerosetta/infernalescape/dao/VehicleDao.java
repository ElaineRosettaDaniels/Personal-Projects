/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.elainerosetta.infernalescape.dao;

import com.elainerosetta.infernalescape.dto.Vehicle;
import java.util.List;

/**
 *
 * @author Asus1
 */
public interface VehicleDao {
    
    Vehicle getVehicleById(int vehicleId);
    List<Vehicle> getAllVehicle();
    Vehicle addVehicle(Vehicle v);
    void updateVehicle(Vehicle v);
    void deleteVehicle(int vehicleId);
}
