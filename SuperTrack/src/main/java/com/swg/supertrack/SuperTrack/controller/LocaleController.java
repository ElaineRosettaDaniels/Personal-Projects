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
import com.swg.supertrack.SuperTrack.dto.Superhuman;
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
public class LocaleController {
    
    @Autowired
    SuperhumanDao shDao;
    
    @Autowired
    OrgDao orgDao;
    
    @Autowired
    LocaleDao locDao;
    
    @Autowired
    SightingDao siDao;
    
    Set<ConstraintViolation<Locale>> violations = new HashSet<>();
    
    @GetMapping("locales")
    public String displayLocales(Model model) {
        List<Locale> locales = locDao.getAllLocales();
        model.addAttribute("locales", locales);
        model.addAttribute("errors", violations);
        return "locales";
    }
    
    @PostMapping("addLocale")
    public String addLocale(HttpServletRequest req) {
        String localeName = req.getParameter("localeName");
        String localeDossier = req.getParameter("localeDossier");
        String address = req.getParameter("address");
        String district = req.getParameter("district");
        String city = req.getParameter("city");
        String country = req.getParameter("country");
        String longLat = req.getParameter("longLat");
        
        Locale loc = new Locale();
        loc.setLocaleName(localeName);
        loc.setLocaleDossier(localeDossier);
        loc.setAddress(address);
        loc.setDistrict(district);
        loc.setCity(city);
        loc.setCountry(country);
        loc.setLongLat(longLat);
        
        Validator validate = Validation.buildDefaultValidatorFactory().getValidator();
        violations = validate.validate(loc);
        
        if(violations.isEmpty()) {
            locDao.addLocale(loc);
        }
        
        return "redirect:/locales";
    }
    
    @GetMapping("editLocale")
    public String editLocale(HttpServletRequest req, Model model) {
        int localeId = Integer.parseInt(req.getParameter("localeId"));
        Locale locale = locDao.getLocaleById(localeId);
        
        model.addAttribute("locale", locale);
        return "editLocale";
    }
    
    @PostMapping("editLocale")
    public String performEditLocale(@Valid Locale locale, BindingResult result) {
        if(result.hasErrors()) {
            return "editLocale";
        }
        locDao.updateLocale(locale);
        return "redirect:/locales";
    }
}
