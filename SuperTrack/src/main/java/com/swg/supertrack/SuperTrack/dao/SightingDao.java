/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swg.supertrack.SuperTrack.dao;

import com.swg.supertrack.SuperTrack.dto.Locale;
import com.swg.supertrack.SuperTrack.dto.Sighting;
import com.swg.supertrack.SuperTrack.dto.Superhuman;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author Asus1
 */
public interface SightingDao {
    
    Sighting getSightById(int id);
    List<Sighting> getAllSightings();
    Sighting addSighting(Sighting sight);
    void updateSighting(Sighting sight);
    void deleteSighting(int id);
    
    List<Sighting> getSightsBySuper(Superhuman superhuman);
    List<Sighting> getSightsByLocale(Locale locale);
    List<Sighting> getSightsByDate(LocalDate date);
}
