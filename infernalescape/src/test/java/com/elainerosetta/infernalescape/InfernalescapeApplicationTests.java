package com.elainerosetta.infernalescape;

import com.elainerosetta.infernalescape.dao.RiderDao;
import com.elainerosetta.infernalescape.dao.StationDao;
import com.elainerosetta.infernalescape.dao.VehicleDao;
import com.elainerosetta.infernalescape.dto.Rider;
import com.elainerosetta.infernalescape.dto.Station;
import com.elainerosetta.infernalescape.dto.Vehicle;
import java.util.List;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
class InfernalescapeApplicationTests {

    @Test
    void contextLoads() {
    }

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
    
    @BeforeEach
    public void setup() {
        List<Vehicle> allV = veDao.getAllVehicles();
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
    
    @AfterEach
    public void tearDown() {
    }
    
    @Test
    public void addAndGetVehicleTest() {
        Vehicle v = new Vehicle();
        v.setVeName("Player");
        v.setVeType("Tormentor");
        v.setArmor(21);
        v.setSpeed(100);
        v.setHitPoints(60);
        v.setDamThres(10);
        v.setMisThres(20);
        v.setPosition(0);
        v.setIchorBoosted(false);
        v.setIchorUses(3);
        v.setMaxRiders(4);
        
        v = veDao.addVehicle(v);
        Vehicle fromDao = veDao.getVehicleById(v.getVehicleId());
        
        assertEquals(fromDao, v);
        
    }
    
    @Test
    public void addAndGetStationTest() {
        Station s = new Station();
        s.setName("Harpoon Flinger");
        s.setArmorBonus(3);
        s.setStAction("Harpoon");
        s.setCrewed(false);
        s = stDao.addStation(s);
        
        Station fromDao = stDao.getStationById(s.getStationId());
        
        assertEquals(s, fromDao);
    }
    
    @Test
    public void addAndGetRiderTest() {
        
        Rider r = new Rider();
        r.setName("Rider");
        r.setArmor(15);
        r.setHitPoints(30);
        r = riDao.addRider(r);
        
        Rider fromDao = riDao.getRiderById(r.getRiderId());
        
        assertEquals(r, fromDao);
    }
    
    @Test
    public void getAllVehiclesTest() {
        Vehicle v1 = new Vehicle();
        v1.setVeName("Player1");
        v1.setVeType("Tormentor");
        v1.setArmor(21);
        v1.setSpeed(100);
        v1.setHitPoints(60);
        v1.setDamThres(10);
        v1.setMisThres(20);
        v1.setPosition(0);
        v1.setIchorBoosted(false);
        v1.setIchorUses(3);
        v1.setMaxRiders(4);
        v1 = veDao.addVehicle(v1);
        
        Vehicle v2 = new Vehicle();
        v2.setVeName("Player2");
        v2.setVeType("Tormentor");
        v2.setArmor(21);
        v2.setSpeed(100);
        v2.setHitPoints(60);
        v2.setDamThres(10);
        v2.setMisThres(20);
        v2.setPosition(0);
        v2.setIchorBoosted(false);
        v2.setIchorUses(3);
        v2.setMaxRiders(4);
        v2 = veDao.addVehicle(v2);
        
        List<Vehicle> allV = veDao.getAllVehicles();
        
        assertEquals(2, allV.size());
        assertTrue(allV.contains(v1));
        assertTrue(allV.contains(v2));
        
    }
    
    @Test
    public void updateVehicleTest() {
        Vehicle v = new Vehicle();
        v.setVeName("Player");
        v.setVeType("Tormentor");
        v.setArmor(21);
        v.setSpeed(100);
        v.setHitPoints(60);
        v.setDamThres(10);
        v.setMisThres(20);
        v.setPosition(0);
        v.setIchorBoosted(false);
        v.setIchorUses(3);
        v.setMaxRiders(4);
        v = veDao.addVehicle(v);
        
        Vehicle fromDao = veDao.getVehicleById(v.getVehicleId());
        assertEquals(fromDao, v);
        
        v.setHitPoints(50);
        veDao.updateVehicle(v);
        
        assertNotEquals(v, fromDao);
        
        fromDao = veDao.getVehicleById(v.getVehicleId());
        assertEquals(fromDao, v);
        
    }
    
    @Test
    public void deleteVehicleTest() {
        Vehicle v = new Vehicle();
        v.setVeName("Player");
        v.setVeType("Tormentor");
        v.setArmor(21);
        v.setSpeed(100);
        v.setHitPoints(60);
        v.setDamThres(10);
        v.setMisThres(20);
        v.setPosition(0);
        v.setIchorBoosted(false);
        v.setIchorUses(3);
        v.setMaxRiders(4);
        v = veDao.addVehicle(v);
        
        Station s = new Station();
        s.setName("Harpoon Flinger");
        s.setArmorBonus(3);
        s.setStAction("Harpoon");
        s.setCrewed(false);
        s = stDao.addStation(s);
        
        Rider r = new Rider();
        r.setName("Rider");
        r.setArmor(15);
        r.setHitPoints(30);
        
        
    }
}
