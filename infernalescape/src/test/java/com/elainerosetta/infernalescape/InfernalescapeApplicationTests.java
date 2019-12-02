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
    void contextLoads() {}

    @Autowired
    VehicleDao veDao;
    
    @Autowired
    StationDao stDao;
    
    @Autowired
    RiderDao riDao;
    
    @BeforeClass
    public static void setUpClass() {}
    
    @AfterClass
    public static void tearDownClass() {}
    
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
    public void tearDown() {}
    
    @Test
    public void addAndGetVehicleTest() {
        Vehicle v = new Vehicle();
        v.setVeName("Player");
        v.setVeType("Tormentor");
        v.setArmor(21);
        v.setSpeed(100);
        v.setDexBonus(2);
        v.setStrBonus(3);
        v.setHitPoints(60);
        v.setDamThres(10);
        v.setMisThres(20);
        v.setTotalDist(0);
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
        v1.setDexBonus(2);
        v1.setStrBonus(3);
        v1.setHitPoints(60);
        v1.setDamThres(10);
        v1.setMisThres(20);
        v1.setTotalDist(0);
        v1.setIchorBoosted(false);
        v1.setIchorUses(3);
        v1.setMaxRiders(4);
        v1 = veDao.addVehicle(v1);
        
        Vehicle v2 = new Vehicle();
        v2.setVeName("Player2");
        v2.setVeType("Tormentor");
        v2.setArmor(21);
        v2.setSpeed(100);
        v2.setDexBonus(2);
        v2.setStrBonus(3);
        v2.setHitPoints(60);
        v2.setDamThres(10);
        v2.setMisThres(20);
        v2.setTotalDist(0);
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
    public void getAllStationsTest() {
        Station s1 = new Station();
        s1.setName("Harpoon Flinger");
        s1.setArmorBonus(3);
        s1.setStAction("Harpoon");
        s1.setCrewed(false);
        s1 = stDao.addStation(s1);
        
        Station s2 = new Station();
        s2.setName("Harpoon Flinger");
        s2.setArmorBonus(3);
        s2.setStAction("Harpoon");
        s2.setCrewed(false);
        s2 = stDao.addStation(s2);
        
        List<Station> allS = stDao.getAllStations();
        
        assertEquals(2, allS.size());
        assertTrue(allS.contains(s1));
        assertTrue(allS.contains(s2));
    }
    
    @Test
    public void getAllRidersTest() {
        Rider r1 = new Rider();
        r1.setName("Rider");
        r1.setArmor(15);
        r1.setHitPoints(30);
        r1 = riDao.addRider(r1);
        
        Rider r2 = new Rider();
        r2.setName("Rider");
        r2.setArmor(15);
        r2.setHitPoints(30);
        r2 = riDao.addRider(r2);
        
        List<Rider> allR = riDao.getAllRiders();
        
        assertEquals(2, allR.size());
        assertTrue(allR.contains(r1));
        assertTrue(allR.contains(r2));
    }
    
    @Test
    public void updateVehicleTest() {
        Vehicle v = new Vehicle();
        v.setVeName("Player");
        v.setVeType("Tormentor");
        v.setArmor(21);
        v.setSpeed(100);
        v.setDexBonus(2);
        v.setStrBonus(3);
        v.setHitPoints(60);
        v.setDamThres(10);
        v.setMisThres(20);
        v.setTotalDist(0);
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
    public void updateStationTest() {
        Station s = new Station();
        s.setName("Harpoon Flinger");
        s.setArmorBonus(3);
        s.setStAction("Harpoon");
        s.setCrewed(false);
        s = stDao.addStation(s);
        
        Station fromDao = stDao.getStationById(s.getStationId());
        assertEquals(s, fromDao);
        
        s.setCrewed(true);
        stDao.updateStation(s);
        
        assertNotEquals(s, fromDao);
        
        fromDao = stDao.getStationById(s.getStationId());
        
        assertEquals(s, fromDao);
        
    }
    
    @Test
    public void updateRiderTest() {
        Rider r = new Rider();
        r.setName("Rider");
        r.setArmor(15);
        r.setHitPoints(30);
        r = riDao.addRider(r);
        
        Rider fromDao = riDao.getRiderById(r.getRiderId());
        assertEquals(fromDao, r);
        
        r.setHitPoints(20);
        riDao.updateRider(r);
        
        assertNotEquals(r, fromDao);
        
        fromDao = riDao.getRiderById(r.getRiderId());
        assertEquals(fromDao, r);
    }
    
    @Test
    public void deleteVehicleTest() {
        Vehicle v = new Vehicle();
        v.setVeName("Player");
        v.setVeType("Tormentor");
        v.setArmor(21);
        v.setSpeed(100);
        v.setDexBonus(2);
        v.setStrBonus(3);
        v.setHitPoints(60);
        v.setDamThres(10);
        v.setMisThres(20);
        v.setTotalDist(0);
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
        r = riDao.addRider(r);
        
        riDao.addRiderToVehicle(r, v);
        stDao.addStationToVehicle(s, v);
        
        Vehicle fromDao = veDao.getVehicleById(v.getVehicleId());
        assertEquals(v, fromDao);
        
        veDao.deleteVehicle(v.getVehicleId());
        
        fromDao = veDao.getVehicleById(v.getVehicleId());
        assertNull(fromDao);
    }
    
    @Test
    public void deleteStationTest() {
        Vehicle v = new Vehicle();
        v.setVeName("Player");
        v.setVeType("Tormentor");
        v.setArmor(21);
        v.setSpeed(100);
        v.setDexBonus(2);
        v.setStrBonus(3);
        v.setHitPoints(60);
        v.setDamThres(10);
        v.setMisThres(20);
        v.setTotalDist(0);
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
        r = riDao.addRider(r);
        
        stDao.addStationToVehicle(s, v);
        riDao.addRiderToStation(r, s);
        
        Station fromDao = stDao.getStationById(s.getStationId());
        assertEquals(s, fromDao);
        
        stDao.deleteStation(s);
        
        fromDao = stDao.getStationById(s.getStationId());
        assertNull(fromDao);
    }
    
    @Test
    public void deleteRiderTest() {
        Vehicle v = new Vehicle();
        v.setVeName("Player");
        v.setVeType("Tormentor");
        v.setArmor(21);
        v.setSpeed(100);
        v.setDexBonus(2);
        v.setStrBonus(3);
        v.setHitPoints(60);
        v.setDamThres(10);
        v.setMisThres(20);
        v.setTotalDist(0);
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
        r = riDao.addRider(r);
        
        riDao.addRiderToStation(r, s);
        riDao.addRiderToVehicle(r, v);
        
        Rider fromDao = riDao.getRiderById(r.getRiderId());
        assertEquals(r, fromDao);
        
        riDao.deleteRider(r);
        
        fromDao = riDao.getRiderById(r.getRiderId());
        assertNull(fromDao);
    }
}
