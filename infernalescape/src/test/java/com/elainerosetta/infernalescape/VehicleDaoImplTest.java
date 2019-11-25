/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.elainerosetta.infernalescape;

import com.elainerosetta.infernalescape.dao.RiderDao;
import com.elainerosetta.infernalescape.dao.StationDao;
import com.elainerosetta.infernalescape.dao.VehicleDao;
import com.elainerosetta.infernalescape.dto.Rider;
import com.elainerosetta.infernalescape.dto.Station;
import com.elainerosetta.infernalescape.dto.Vehicle;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.jupiter.api.Test;
import org.junit.runner.*;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.beans.factory.annotation.Autowired;
/**
 *
 * @author Asus1
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class VehicleDaoImplTest {
    
    @Autowired
    VehicleDao veDao;
    
    @Autowired
    StationDao stDao;
    
    @Autowired
    RiderDao riDao;
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
        
    }
    
    @Before
    public void setup() {
        List<Vehicle> allV = veDao.getAllVehicle();
        for (Vehicle v : allV) {
            veDao.deleteVehicle(v.getVehicleId());
        }
        
        List<Station> allS = stDao.getAllStations();
        for (Station s : allS) {
            stDao.deleteStation(s);
        }
        
        List<Rider> allR = riDao.getAllRiders();
        for (Rider r : allR) {
            riDao.deleteRider(r);
        }
    }
    
    @After
    public void tearDown() {
    }
    
    @Test
    public void addAndGetVehicleTest
}
