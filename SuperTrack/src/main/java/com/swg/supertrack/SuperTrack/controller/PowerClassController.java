/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swg.supertrack.SuperTrack.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 *
 * @author Asus1
 */
@Controller
public class PowerClassController {
    
    @GetMapping("powerclass")
    public String powerClass(Model model) {
        return "powerclass";
    }
    
    
}
