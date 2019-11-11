/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.elainerosetta.tavernsimulator.tests;



import com.elainerosetta.tavernsimulator.DAO.TavernDao;
import com.elainerosetta.tavernsimulator.DAO.TavernReportDao;
import com.elainerosetta.tavernsimulator.DTO.Tavern;
import com.elainerosetta.tavernsimulator.DTO.TavernReport;
import java.time.LocalDate;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.jupiter.api.Test;
import org.junit.runner.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 *
 * @author Asus1
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class TavernDaoDBTest {
    
    @Autowired
    TavernDao tavDao;
    
    @Autowired
    TavernReportDao tavRepDao;
    
    public TavernDaoDBTest() {}
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
        
    }
    
    @Before
    public void setUp() {
        List<Tavern> taverns = tavDao.getAllTaverns();
        for (Tavern tav : taverns) {
            tavDao.deleteTavern(tav.getTavernId());
        }
    }
    
    @After
    public void tearDown() {
    }
    
    @Test
    public void addAndGetTavernTest(){
        Tavern tav = new Tavern();
        tav.setTavernName("Trollskull");
        tav.setTavernOwner("Floon");
        tav.setTavernAbout("Owned by adventurers");
        tav.setTavernFunds(1200);
        tav.setTavernDrinks(200);
        tav.setTavernFood(200);
        tav.setTavernRepairs(100);
        tav = tavDao.addTavern(tav);
        
        Tavern fromDao = tavDao.getTavernById(tav.getTavernId());
        
        assertEquals(tav, fromDao);
        
    }
    
    @Test
    public void getAllTavernsTest() {
        Tavern tav1 = new Tavern();
        tav1.setTavernName("Trollskull");
        tav1.setTavernOwner("Floon");
        tav1.setTavernAbout("Owned by adventurers");
        tav1.setTavernFunds(1200);
        tav1.setTavernDrinks(200);
        tav1.setTavernFood(200);
        tav1.setTavernRepairs(100);
        tav1 = tavDao.addTavern(tav1);
        
        Tavern tav2 = new Tavern();
        tav2.setTavernName("Tavern");
        tav2.setTavernOwner("Blagmaar");
        tav2.setTavernAbout("Owned by adventurers");
        tav2.setTavernFunds(1200);
        tav2.setTavernDrinks(200);
        tav2.setTavernFood(200);
        tav2.setTavernRepairs(100);
        tav2 = tavDao.addTavern(tav2);
        
        List<Tavern> allTavs = tavDao.getAllTaverns();
        assertEquals(2, allTavs.size());
        assertTrue(allTavs.contains(tav1));
        assertTrue(allTavs.contains(tav2));
    }
    
    @Test
    public void deleteTavernTest() {
        Tavern tav = new Tavern();
        tav.setTavernName("Trollskull");
        tav.setTavernOwner("Floon");
        tav.setTavernAbout("Owned by adventurers");
        tav.setTavernFunds(1200);
        tav.setTavernDrinks(200);
        tav.setTavernFood(200);
        tav.setTavernRepairs(100);
        tav = tavDao.addTavern(tav);
        
        TavernReport rep = new TavernReport();
        rep.setTavernReportWeek(1);
        rep.setTavernReportRoll(50);
        rep.setTavernReportEarnings(100);
        rep.setTavernReportFunds(200);
        rep.setTavernId(tav.getTavernId());
        rep = tavRepDao.addTavernReport(rep);
        
        Tavern tavFromDao = tavDao.getTavernById(tav.getTavernId());
        TavernReport repFromDao = tavRepDao.getReportById(rep.getTavernReportId());
        
        assertEquals(tav, tavFromDao);
        assertEquals(rep, repFromDao);
        
        tavDao.deleteTavern(tav.getTavernId());
        tavFromDao = tavDao.getTavernById(tav.getTavernId());
        assertNull(tavFromDao);
    }
}
