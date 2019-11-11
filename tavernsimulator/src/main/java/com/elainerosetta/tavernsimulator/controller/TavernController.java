/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.elainerosetta.tavernsimulator.controller;

import com.elainerosetta.tavernsimulator.DAO.TavernDao;
import com.elainerosetta.tavernsimulator.DAO.TavernReportDao;
import com.elainerosetta.tavernsimulator.DTO.Tavern;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 *
 * @author Asus1
 */
@Controller
public class TavernController {
    
    @Autowired
    TavernDao tavDao;
    
    @Autowired
    TavernReportDao tavRepDao;
    
    @GetMapping("tavernGame")
    public String playGame(Integer tavernId, Model model) {
        Tavern tav = tavDao.getTavernById(tavernId);
        model.addAttribute("tavern", tav);
        return "tavernGame";
    }
 
    
    
}
