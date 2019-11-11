/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.elainerosetta.tavernsimulator.DAO;

import com.elainerosetta.tavernsimulator.DTO.Tavern;
import java.util.List;

/**
 *
 * @author Asus1
 */
public interface TavernDao {
    
    Tavern getTavernById(int tavernId);
    List<Tavern> getAllTaverns();
    Tavern addTavern(Tavern tav);
    void updateTavernBio(Tavern tav);
    void updateTavernStats(Tavern tav);
    void deleteTavern(int tavernId);
    
}
