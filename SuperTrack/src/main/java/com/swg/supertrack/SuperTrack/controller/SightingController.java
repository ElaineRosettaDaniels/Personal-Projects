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
import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;
import javax.validation.Valid;
import javax.validation.Validation;
import javax.validation.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

/**
 *
 * @author Asus1
 */
@Controller
public class SightingController {
    
    @Autowired
    SuperhumanDao shDao;
    
    @Autowired
    OrgDao orgDao;
    
    @Autowired
    LocaleDao locDao;
    
    @Autowired
    SightingDao siDao;
    
    Set<ConstraintViolation<Sighting>> violations = new HashSet<>();
    
    @GetMapping("sightings")
    public String displaySightings(Model model) {
        List<Superhuman> allSupers = shDao.getAllSupers();
        List<Locale> allLocales = locDao.getAllLocales();
        List<Sighting> allSights = siDao.getAllSightings();
        model.addAttribute("sightings", allSights);
        model.addAttribute("superhumans", allSupers);
        model.addAttribute("locales", allLocales);
        model.addAttribute("errors", violations);
        return "sightings";
    }
    
    @PostMapping("addSighting")
    public String addSight(HttpServletRequest req) {
        Sighting sight = new Sighting();
        
        String localeId = req.getParameter("localeId");
        String superId = req.getParameter("superId");
        LocalDate sightDate = LocalDate.parse(req.getParameter("sightDate"));
        
        sight.setSightDate(sightDate);
        sight.setLocale(locDao.getLocaleById(Integer.parseInt(localeId)));
        sight.setSuperhuman(shDao.getSuperById(Integer.parseInt(superId)));
        
        
        Validator validate = Validation.buildDefaultValidatorFactory().getValidator();
        violations = validate.validate(sight);
        
        if(violations.isEmpty()) {
            siDao.addSighting(sight);
        }
        
        return "redirect:/sightings";
    }
    
    @GetMapping("deleteSighting")
    public String deleteSight(Integer sightId) {
        siDao.deleteSighting(sightId);
        return "redirect:/sightings";
    }
    
    @GetMapping("editSighting")
    public String editSight(Integer sightId, Model model) {
        Sighting sighting = siDao.getSightById(sightId);
        List<Locale> allLocales = locDao.getAllLocales();
        List<Superhuman> allSupers = shDao.getAllSupers();
        model.addAttribute("sighting", sighting);
        model.addAttribute("superhumans", allSupers);
        model.addAttribute("locales", allLocales);
        return "editSighting";
    }
    
    @PostMapping("editSighting")
    public String performEditSight(@Valid Sighting sighting, BindingResult result, HttpServletRequest req, Model model) {
        LocalDate sightDate = LocalDate.parse(req.getParameter("sightDate"));
        String superId = req.getParameter("superId");
        String localeId = req.getParameter("localeId");
        
        sighting.setSightDate(sightDate);
        sighting.setLocale(locDao.getLocaleById(Integer.parseInt(localeId)));
        sighting.setSuperhuman(shDao.getSuperById(Integer.parseInt(superId)));
        
        siDao.updateSighting(sighting);
        
        return "redirect:/sightings";
    }
}
