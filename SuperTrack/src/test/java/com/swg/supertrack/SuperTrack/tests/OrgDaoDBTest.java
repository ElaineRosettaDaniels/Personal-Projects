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
public class OrgDaoDBTest {
    
    @Autowired
    SuperhumanDao shDao;
    
    @Autowired
    OrgDao orgDao;
    
    @Autowired
    LocaleDao locDao;
    
    @Autowired
    SightingDao siDao;
    
    public OrgDaoDBTest(){}
    
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
    public void testAddAndGetOrg() {
        Superhuman sH = new Superhuman();
        sH.setSuperName("Test Name");
        sH.setSuperDossier("Test Dossier");
        sH.setPowerClass("Test Power");
        sH.setAlignment("H");
        sH = shDao.addSuperhuman(sH);
        
        List<Superhuman> allSups = shDao.getAllSupers();
        
        Locale l = new Locale();
        l.setLocaleName("Test Locale");
        l.setLocaleDossier("Test Dossier");
        l.setAddress("Test Address");
        l.setDistrict("Test District");
        l.setCity("Test City");
        l.setCountry("Test Country");
        l.setLongLat("Test LongLat");
        l = locDao.addLocale(l);
        
        Org o = new Org();
        o.setOrgName("Test Name");
        o.setOrgDossier("Test Dossier");
        o.setLocale(l);
        o.setOrgMembers(allSups);
        o = orgDao.addOrg(o);
        
        Org fromDao = orgDao.getOrgById(o.getOrgId());
        assertTrue(o.equals(fromDao));
        //assertEquals(o, fromDao);
    }
    
    @Test
    public void testGetAllOrgs() {
        Superhuman sH = new Superhuman();
        sH.setSuperName("Test Name");
        sH.setSuperDossier("Test Dossier");
        sH.setPowerClass("Test Power");
        sH.setAlignment("H");
        sH = shDao.addSuperhuman(sH);
        
        List<Superhuman> allSups = shDao.getAllSupers();
        
        Locale l = new Locale();
        l.setLocaleName("Test Locale");
        l.setLocaleDossier("Test Dossier");
        l.setAddress("Test Address");
        l.setDistrict("Test District");
        l.setCity("Test City");
        l.setCountry("Test Country");
        l.setLongLat("Test LongLat");
        l = locDao.addLocale(l);
        
        Org o1 = new Org();
        o1.setOrgName("Test Name 1");
        o1.setOrgDossier("Test Dossier 1");
        o1.setLocale(l);
        o1.setOrgMembers(allSups);
        o1 = orgDao.addOrg(o1);
        
        Org o2 = new Org();
        o2.setOrgName("Test Name 2");
        o2.setOrgDossier("Test Dossier 2");
        o2.setLocale(l);
        o2.setOrgMembers(allSups);
        o2 = orgDao.addOrg(o2);
        
        List<Org> allOrgs = orgDao.getAllOrgs();
        
        assertEquals(2, allOrgs.size());
        assertTrue(allOrgs.contains(o1));
        assertTrue(allOrgs.contains(o2));
    }
    
    @Test
    public void testUpdateOrg() {
        Superhuman sH = new Superhuman();
        sH.setSuperName("Test Name");
        sH.setSuperDossier("Test Dossier");
        sH.setPowerClass("Test Power");
        sH.setAlignment("H");
        sH = shDao.addSuperhuman(sH);
        
        List<Superhuman> allSups = shDao.getAllSupers();
        
        Locale l = new Locale();
        l.setLocaleName("Test Locale");
        l.setLocaleDossier("Test Dossier");
        l.setAddress("Test Address");
        l.setDistrict("Test District");
        l.setCity("Test City");
        l.setCountry("Test Country");
        l.setLongLat("Test LongLat");
        l = locDao.addLocale(l);
        
        Org o = new Org();
        o.setOrgName("Test Name");
        o.setOrgDossier("Test Dossier");
        o.setLocale(l);
        o.setOrgMembers(allSups);
        o = orgDao.addOrg(o);
        
        Org fromDao = orgDao.getOrgById(o.getOrgId());
        assertEquals(o, fromDao);
        
        o.setOrgName("New Name");
        orgDao.updateOrg(o);
        
        assertNotEquals(o, fromDao);
        
        fromDao = orgDao.getOrgById(o.getOrgId());
        
        assertEquals(o, fromDao);
    }
    
    @Test
    public void testDeleteOrg() {
        Superhuman sH = new Superhuman();
        sH.setSuperName("Test Name");
        sH.setSuperDossier("Test Dossier");
        sH.setPowerClass("Test Power");
        sH.setAlignment("H");
        sH = shDao.addSuperhuman(sH);
        
        List<Superhuman> allSups = shDao.getAllSupers();
        
        Locale l = new Locale();
        l.setLocaleName("Test Locale");
        l.setLocaleDossier("Test Dossier");
        l.setAddress("Test Address");
        l.setDistrict("Test District");
        l.setCity("Test City");
        l.setCountry("Test Country");
        l.setLongLat("Test LongLat");
        l = locDao.addLocale(l);
        
        Org o = new Org();
        o.setOrgName("Test Name");
        o.setOrgDossier("Test Dossier");
        o.setLocale(l);
        o.setOrgMembers(allSups);
        o = orgDao.addOrg(o);
        
        Org fromDao = orgDao.getOrgById(o.getOrgId());
        assertEquals(o, fromDao);
        
        orgDao.deleteOrg(o.getOrgId());
        
        fromDao = orgDao.getOrgById(o.getOrgId());
        assertNull(fromDao);
    }
}
