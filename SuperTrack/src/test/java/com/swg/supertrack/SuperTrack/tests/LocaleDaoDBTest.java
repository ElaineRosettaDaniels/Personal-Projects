/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swg.supertrack.SuperTrack.tests;

import com.swg.supertrack.SuperTrack.dao.*;
import com.swg.supertrack.SuperTrack.dto.*;
import java.util.Date;
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
public class LocaleDaoDBTest {
    
    @Autowired
    SuperhumanDao shDao;
    
    @Autowired
    OrgDao orgDao;
    
    @Autowired
    LocaleDao locDao;
    
    @Autowired
    SightingDao siDao;
    
    public LocaleDaoDBTest() {}
    
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
    public void testAddAndGetLocale() {
        Locale l = new Locale();
        l.setLocaleName("Test Locale");
        l.setLocaleDossier("Test Dossier");
        l.setAddress("Test Address");
        l.setDistrict("Test District");
        l.setCity("Test City");
        l.setCountry("Test Country");
        l.setLongLat("Test LongLat");
        l = locDao.addLocale(l);
        
        Locale fromDao = locDao.getLocaleById(l.getLocaleId());
        
        assertEquals(l, fromDao);
    }
    
    @Test
    public void testGetAllLocales() {
        Locale l1 = new Locale();
        l1.setLocaleName("Test Locale 1");
        l1.setLocaleDossier("Test Dossier 1");
        l1.setAddress("Test Address 1");
        l1.setDistrict("Test District 1");
        l1.setCity("Test City 1");
        l1.setCountry("Test Country 1");
        l1.setLongLat("Test LongLat 1");
        l1 = locDao.addLocale(l1);
        
        Locale l2 = new Locale();
        l2.setLocaleName("Test Locale 2");
        l2.setLocaleDossier("Test Dossier 2");
        l2.setAddress("Test Address 2");
        l2.setDistrict("Test District 2");
        l2.setCity("Test City 2");
        l2.setCountry("Test Country 2");
        l2.setLongLat("Test LongLat 2");
        l2 = locDao.addLocale(l2);
        
        List<Locale> allLocs = locDao.getAllLocales();
        
        assertEquals(2, allLocs.size());
        assertTrue(allLocs.contains(l1));
        assertTrue(allLocs.contains(l2));
    }
    
    @Test
    public void testUpdateLocale() {
        Locale l = new Locale();
        l.setLocaleName("Test Locale");
        l.setLocaleDossier("Test Dossier");
        l.setAddress("Test Address");
        l.setDistrict("Test District");
        l.setCity("Test City");
        l.setCountry("Test Country");
        l.setLongLat("Test LongLat");
        l = locDao.addLocale(l);
        
        Locale fromDao = locDao.getLocaleById(l.getLocaleId());
        assertEquals(l, fromDao);
        
        l.setLocaleName("New Name");
        locDao.updateLocale(l);
        
        assertNotEquals(l, fromDao);
        
        fromDao = locDao.getLocaleById(l.getLocaleId());
        assertEquals(l, fromDao);
    }
    
    @Test
    public void testDeleteLocale() {
        Locale l = new Locale();
        l.setLocaleName("Test Locale");
        l.setLocaleDossier("Test Dossier");
        l.setAddress("Test Address");
        l.setDistrict("Test District");
        l.setCity("Test City");
        l.setCountry("Test Country");
        l.setLongLat("Test LongLat");
        l = locDao.addLocale(l);
        
        Locale fromDao = locDao.getLocaleById(l.getLocaleId());
        assertEquals(l, fromDao);
        
        locDao.deleteLocale(l.getLocaleId());
        
        fromDao = locDao.getLocaleById(l.getLocaleId());
        assertNull(fromDao);
    }
    
}
