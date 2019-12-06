/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swg.supertrack.SuperTrack.controller;

import com.swg.supertrack.SuperTrack.dao.LocaleDao;
import com.swg.supertrack.SuperTrack.dao.OrgDao;
import com.swg.supertrack.SuperTrack.dao.SightingDao;
import com.swg.supertrack.SuperTrack.dao.SuperhumanDao;
import com.swg.supertrack.SuperTrack.dto.Locale;
import com.swg.supertrack.SuperTrack.dto.Org;
import com.swg.supertrack.SuperTrack.dto.Sighting;
import com.swg.supertrack.SuperTrack.dto.Superhuman;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 *
 * @author Asus1
 */
@Controller
public class MainController {
    
    @Autowired
    SuperhumanDao shDao;
    
    @Autowired
    OrgDao orgDao;
    
    @Autowired
    LocaleDao locDao;
    
    @Autowired
    SightingDao siDao;
    
    @GetMapping("/")
    public String index(Model model) {
        List<Superhuman> allSupers = shDao.getAllSupers();
        List<Locale> allLocales = locDao.getAllLocales();
        List<Sighting> allSights = siDao.getAllSightings();
        List<Sighting> mostRecent = allSights.subList(Math.max(allSights.size() - 10, 0), allSights.size());
        model.addAttribute("sightings", mostRecent);
        model.addAttribute("superhumans", allSupers);
        model.addAttribute("locales", allLocales);
        return "index";
    }
}
