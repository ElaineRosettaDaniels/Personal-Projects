/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.elainerosetta.infernalescape.dao;

import com.elainerosetta.infernalescape.dto.Rider;
import java.util.List;

/**
 *
 * @author Asus1
 */
public interface RiderDao {
    
    Rider getRiderById(int riderId);
    List<Rider> getAllRiders();
    List<Rider> getRidersForVehicle(int vehicleId);
    Rider addRider(Rider r);
    void updateRider(Rider r);
    void deleteRider(Rider r);
}
