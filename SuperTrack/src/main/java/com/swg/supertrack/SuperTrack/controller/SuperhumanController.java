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
public class SuperhumanController {
    
    @Autowired
    SuperhumanDao shDao;
    
    @Autowired
    OrgDao orgDao;
    
    @Autowired
    LocaleDao locDao;
    
    @Autowired
    SightingDao siDao;
    
    Set<ConstraintViolation<Superhuman>> violations = new HashSet<>();
    
    @GetMapping("superhumans")
    public String displaySuperhumans(Model model) {
        List<Superhuman> allSupers = shDao.getAllSupers();
        model.addAttribute("superhumans", allSupers);
        model.addAttribute("errors", violations);
        return "superhumans";
    }
    
    @PostMapping("addSuperhuman")
    public String addSuperhuman(HttpServletRequest req) {
        String superName = req.getParameter("superName");
        String superDossier = req.getParameter("superDossier");
        String powerClass = req.getParameter("powerClass");
        String alignment = req.getParameter("alignment");
        
        Superhuman sH = new Superhuman();
        sH.setSuperName(superName);
        sH.setSuperDossier(superDossier);
        sH.setPowerClass(powerClass);
        sH.setAlignment(alignment);
    
        Validator validate = Validation.buildDefaultValidatorFactory().getValidator();
        violations = validate.validate(sH);
        
        if(violations.isEmpty()) {
            shDao.addSuperhuman(sH);
        }
        
        return "redirect:/superhumans";
    }
    
    @GetMapping("deleteSuperhuman")
    public String deleteSuperhuman(HttpServletRequest req) {
        int superId = Integer.parseInt(req.getParameter("superId"));
        shDao.deleteSuperhuman(superId);
        
        return "redirect:/superhumans";
    }
    
    @GetMapping("editSuperhuman")
    public String editSuperhuman(HttpServletRequest req, Model model) {
        int superId = Integer.parseInt(req.getParameter("superId"));
        Superhuman superhuman = shDao.getSuperById(superId);
        
        model.addAttribute("superhuman", superhuman);
        return "editSuperhuman";
    }
    
    @PostMapping("editSuperhuman")
    public String performEditSuperhuman(@Valid Superhuman superhuman, BindingResult result) {
        if(result.hasErrors()) {
            return "editSuperhuman";
        }
        
        shDao.updateSuperhuman(superhuman);
        return "redirect:/superhumans";
    }
}
