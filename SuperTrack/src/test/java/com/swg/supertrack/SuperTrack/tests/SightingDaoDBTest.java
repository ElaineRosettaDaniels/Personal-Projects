/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swg.supertrack.SuperTrack.tests;

import com.swg.supertrack.SuperTrack.dao.*;
import com.swg.supertrack.SuperTrack.dto.*;
import java.time.LocalDate;
import java.sql.Date;
import java.util.List;
import org.junit.*;
import static org.junit.Assert.*;
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
public class SightingDaoDBTest {
    
    @Autowired
    SuperhumanDao shDao;
    
    @Autowired
    OrgDao orgDao;
    
    @Autowired
    LocaleDao locDao;
    
    @Autowired
    SightingDao siDao;
    
    public SightingDaoDBTest() {}
    
    @BeforeClass
    public static void setUpClass() {}
    
    @AfterClass
    public static void tearDownClass() {}
    
    @Before
    public void setUp() {
        List<Superhuman> supers = shDao.getAllSupers();
        for(Superhuman sh : supers) {
            shDao.deleteSuperhuman(sh.getSuperId());
        }
        
        List<Org> orgs = orgDao.getAllOrgs();
        for(Org org : orgs) {
            orgDao.deleteOrg(org.getOrgId());
        }
        
        List<Locale> locs = locDao.getAllLocales();
        for(Locale loc : locs) {
            locDao.deleteLocale(loc.getLocaleId());
        }
        
        List<Sighting> sights = siDao.getAllSightings();
        for(Sighting sight : sights) {
            siDao.deleteSighting(sight.getSightId());
        }
    }
    
    @After
    public void tearDown() {}
    
    @Test
    public void testAddAndGetSighting() {
        Superhuman sH = new Superhuman();
        sH.setSuperName("Test Name");
        sH.setSuperDossier("Test Dossier");
        sH.setPowerClass("Test Power");
        sH.setAlignment("H");
        sH = shDao.addSuperhuman(sH);
        
        Locale l = new Locale();
        l.setLocaleName("Test Locale");
        l.setLocaleDossier("Test Dossier");
        l.setAddress("Test Address");
        l.setDistrict("Test District");
        l.setCity("Test City");
        l.setCountry("Test Country");
        l.setLongLat("Test LongLat");
        l = locDao.addLocale(l);
        
        Sighting sight = new Sighting();
        sight.setSightDate(LocalDate.now());
        sight.setSuperhuman(sH);
        sight.setLocale(l);
        sight = siDao.addSighting(sight);
        
        Sighting fromDao = siDao.getSightById(sight.getSightId());
        assertEquals(sight, fromDao);
    }
    
    @Test
    public void testGetAllSights() {
        Superhuman sH = new Superhuman();
        sH.setSuperName("Test Name");
        sH.setSuperDossier("Test Dossier");
        sH.setPowerClass("Test Power");
        sH.setAlignment("H");
        sH = shDao.addSuperhuman(sH);
        
        Locale l = new Locale();
        l.setLocaleName("Test Locale");
        l.setLocaleDossier("Test Dossier");
        l.setAddress("Test Address");
        l.setDistrict("Test District");
        l.setCity("Test City");
        l.setCountry("Test Country");
        l.setLongLat("Test LongLat");
        l = locDao.addLocale(l);
        
        Sighting sight1 = new Sighting();
        sight1.setSightDate(LocalDate.now());
        sight1.setSuperhuman(sH);
        sight1.setLocale(l);
        sight1 = siDao.addSighting(sight1);
        
        Sighting sight2 = new Sighting();
        sight2.setSightDate(LocalDate.now());
        sight2.setSuperhuman(sH);
        sight2.setLocale(l);
        sight2 = siDao.addSighting(sight2);
        
        List<Sighting> allSights = siDao.getAllSightings();
        assertEquals(2, allSights.size());
        assertTrue(allSights.contains(sight1));
        assertTrue(allSights.contains(sight2));
    }
    
    @Test
    public void updateSight() {
        Superhuman sH = new Superhuman();
        sH.setSuperName("Test Name");
        sH.setSuperDossier("Test Dossier");
        sH.setPowerClass("Test Power");
        sH.setAlignment("H");
        sH = shDao.addSuperhuman(sH);
        
        Locale l = new Locale();
        l.setLocaleName("Test Locale");
        l.setLocaleDossier("Test Dossier");
        l.setAddress("Test Address");
        l.setDistrict("Test District");
        l.setCity("Test City");
        l.setCountry("Test Country");
        l.setLongLat("Test LongLat");
        l = locDao.addLocale(l);
        
        Locale l2 = new Locale();
        l2.setLocaleName("Test Locale 2");
        l2.setLocaleDossier("Test Dossier 2");
        l2.setAddress("Test Address 2");
        l2.setDistrict("Test District 2");
        l2.setCity("Test City 2");
        l2.setCountry("Test Country 2");
        l2.setLongLat("Test LongLat 2");
        l2 = locDao.addLocale(l2);
        
        Sighting sight = new Sighting();
        sight.setSightDate(LocalDate.now());
        sight.setSuperhuman(sH);
        sight.setLocale(l);
        sight = siDao.addSighting(sight);
        
        Sighting fromDao = siDao.getSightById(sight.getSightId());
        assertEquals(sight, fromDao);
        
        sight.setLocale(l2);
        siDao.updateSighting(sight);
        
        assertNotEquals(sight, fromDao);
        
        fromDao = siDao.getSightById(sight.getSightId());
        assertEquals(sight, fromDao);
    }
    
    @Test
    public void testDeleteSight() {
        Superhuman sH = new Superhuman();
        sH.setSuperName("Test Name");
        sH.setSuperDossier("Test Dossier");
        sH.setPowerClass("Test Power");
        sH.setAlignment("H");
        sH = shDao.addSuperhuman(sH);
        
        Locale l = new Locale();
        l.setLocaleName("Test Locale");
        l.setLocaleDossier("Test Dossier");
        l.setAddress("Test Address");
        l.setDistrict("Test District");
        l.setCity("Test City");
        l.setCountry("Test Country");
        l.setLongLat("Test LongLat");
        l = locDao.addLocale(l);
        
        Sighting sight = new Sighting();
        sight.setSightDate(LocalDate.now());
        sight.setSuperhuman(sH);
        sight.setLocale(l);
        sight = siDao.addSighting(sight);
        
        Sighting fromDao = siDao.getSightById(sight.getSightId());
        assertEquals(sight, fromDao);
        
        siDao.deleteSighting(sight.getSightId());
        
        fromDao = siDao.getSightById(sight.getSightId());
        assertNull(fromDao);
    }
    
}
