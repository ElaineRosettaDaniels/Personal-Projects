/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swg.supertrack.SuperTrack.dao;

import com.swg.supertrack.SuperTrack.dto.Locale;
import com.swg.supertrack.SuperTrack.dto.Superhuman;
import java.util.List;

/**
 *
 * @author Asus1
 */
public interface LocaleDao {
    
    Locale getLocaleById(int id);
    List<Locale> getAllLocales();
    Locale addLocale(Locale locale);
    void updateLocale(Locale locale);
    void deleteLocale(int id);
    
}
