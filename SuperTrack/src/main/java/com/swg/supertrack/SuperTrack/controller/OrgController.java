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
import java.util.ArrayList;
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
public class OrgController {
    
    @Autowired
    SuperhumanDao shDao;
    
    @Autowired
    OrgDao orgDao;
    
    @Autowired
    LocaleDao locDao;
    
    @Autowired
    SightingDao siDao;
    
    Set<ConstraintViolation<Org>> violations = new HashSet<>();
    
    @GetMapping("organizations")
    public String displayOrgs(Model model) {
        List<Superhuman> superhumans = shDao.getAllSupers();
        List<Locale> locales = locDao.getAllLocales();
        List<Org> orgs = orgDao.getAllOrgs();
        model.addAttribute("superhumans", superhumans);
        model.addAttribute("locales", locales);
        model.addAttribute("orgs", orgs);
        model.addAttribute("errors", violations);
        return "organizations";
    }
    
    @PostMapping("addOrg")
    public String addOrg(Org org, HttpServletRequest req) {
        
        
        String localeId = req.getParameter("localeId");
        String[] superIds = req.getParameterValues("superId");
        
        org.setLocale(locDao.getLocaleById(Integer.parseInt(localeId)));
        
        List<Superhuman> orgMembers = new ArrayList<>();
        for(String superId : superIds) {
            orgMembers.add(shDao.getSuperById(Integer.parseInt(superId)));
        }

        org.setOrgMembers(orgMembers);

        orgDao.addOrg(org); 

        return "redirect:/organizations";
    }
    
    @GetMapping("orgDetails")
    public String orgDetails(Integer orgId, Model model) {
        Org org = orgDao.getOrgById(orgId);
        model.addAttribute("org", org);
        return "orgDetails";
    }
    
    @GetMapping("deleteOrg")
    public String deleteOrg(Integer orgId) {
        orgDao.deleteOrg(orgId);
        return "redirect:/organizations";
    }
    
    @GetMapping("editOrg")
    public String editOrg(Integer orgId, Model model) {
        Org org = orgDao.getOrgById(orgId);
        List<Superhuman> superhumans = shDao.getAllSupers();
        List<Locale> locales = locDao.getAllLocales();
        model.addAttribute("org", org);
        model.addAttribute("superhumans", superhumans);
        model.addAttribute("locales", locales);
        return "editOrg";
    }
    
    @PostMapping("editOrg")
    public String performEditOrg(@Valid Org org, BindingResult result, HttpServletRequest req, Model model) {
        String localeId = req.getParameter("localeId");
        String[] superIds = req.getParameterValues("superId");
        
        org.setLocale(locDao.getLocaleById(Integer.parseInt(localeId)));
        
        List<Superhuman> orgMembers = new ArrayList<>();
        if(superIds != null) {
            for(String superId : superIds) {
                orgMembers.add(shDao.getSuperById(Integer.parseInt(superId)));
            }
        }
        
        org.setOrgMembers(orgMembers);
        orgDao.updateOrg(org);
        
        return "redirect:/organizations";
    }
}
