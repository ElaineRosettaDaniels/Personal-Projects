/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swg.supertrack.SuperTrack.tests;

import com.swg.supertrack.SuperTrack.dao.*;
import com.swg.supertrack.SuperTrack.dto.*;
import java.time.LocalDate;
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
public class SuperhumanDaoDBTest {
    
    @Autowired
    SuperhumanDao shDao;
    
    @Autowired
    OrgDao orgDao;
    
    @Autowired
    LocaleDao locDao;
    
    @Autowired
    SightingDao siDao;
    
    public SuperhumanDaoDBTest() {}
    
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
    public void testAddAndGetSuper() {
        Superhuman sH = new Superhuman();
        sH.setSuperName("Test Name");
        sH.setSuperDossier("Test Dossier");
        sH.setPowerClass("Test Power");
        sH.setAlignment("H");
        sH = shDao.addSuperhuman(sH);
        
        Superhuman fromDao = shDao.getSuperById(sH.getSuperId());
        
        assertEquals(sH, fromDao);
    }
    
    @Test
    public void testGetAllSupers() {
        Superhuman sH1 = new Superhuman();
        sH1.setSuperName("Test Name");
        sH1.setSuperDossier("Test Dossier");
        sH1.setPowerClass("Test Power");
        sH1.setAlignment("H");
        sH1 = shDao.addSuperhuman(sH1);
        
        Superhuman sH2 = new Superhuman();
        sH2.setSuperName("Test Name");
        sH2.setSuperDossier("Test Dossier");
        sH2.setPowerClass("Test Power");
        sH2.setAlignment("V");
        sH2 = shDao.addSuperhuman(sH2);
        
        List<Superhuman> supers = shDao.getAllSupers();
        
        assertEquals(2, supers.size());
        assertTrue(supers.contains(sH1));
        assertTrue(supers.contains(sH2));
    }
    
    @Test
    public void updateSuper() {
        Superhuman sH = new Superhuman();
        sH.setSuperName("Test Name");
        sH.setSuperDossier("Test Dossier");
        sH.setPowerClass("Test Power");
        sH.setAlignment("H");
        sH = shDao.addSuperhuman(sH);
        
        Superhuman fromDao = shDao.getSuperById(sH.getSuperId());
        assertEquals(sH, fromDao);
        
        sH.setSuperName("Other Name");
        shDao.updateSuperhuman(sH);
        
        assertNotEquals(sH, fromDao);
        
        fromDao = shDao.getSuperById(sH.getSuperId());
        
        assertEquals(sH, fromDao);
    }
    
    @Test
    public void testDeleteSuperById() {
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
        o.setOrgName("Test Org");
        o.setOrgDossier("Test Dossier");
        o.setLocale(l);
        o.setOrgMembers(allSups);
        o = orgDao.addOrg(o);
        
        Sighting sight = new Sighting();
        sight.setSightDate(LocalDate.now());
        sight.setSuperhuman(sH);
        sight.setLocale(l);
        sight = siDao.addSighting(sight);
        
        Superhuman fromDao = shDao.getSuperById(sH.getSuperId());
        assertEquals(sH, fromDao);
        
        shDao.deleteSuperhuman(sH.getSuperId());
        
        fromDao = shDao.getSuperById(sH.getSuperId());
        assertNull(fromDao);
    }
}
