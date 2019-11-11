/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.elainerosetta.tavernsimulator.controller;

import com.elainerosetta.tavernsimulator.DAO.TavernDao;
import com.elainerosetta.tavernsimulator.DAO.TavernReportDao;
import com.elainerosetta.tavernsimulator.DTO.Tavern;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

/**
 *
 * @author Asus1
 */
@Controller
public class MainController {
    
    @Autowired
    TavernDao tavDao;
    
    @Autowired
    TavernReportDao tavRepDao;
    
    @GetMapping("index")
    public String displayTavSimHome(Model model) {
        List<Tavern> taverns = tavDao.getAllTaverns();
        model.addAttribute("taverns", taverns);
        return "index";
    }
    
    @PostMapping("createTavern")
    public String addTavern(HttpServletRequest req) {
        String tavernName = req.getParameter("tavernName");
        String tavernOwner = req.getParameter("tavernOwner");
        String tavernAbout = req.getParameter("tavernAbout");
        
        Tavern tav = new Tavern();
        tav.setTavernName(tavernName);
        tav.setTavernOwner(tavernOwner);
        tav.setTavernAbout(tavernAbout);
        tav.setTavernFunds(1000);
        tav.setTavernDrinks(100);
        tav.setTavernFood(100);
        tav.setTavernRepairs(100);
        tavDao.addTavern(tav);
        
        return "redirect:/index";
    }
    
}
