/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swg.supertrack.SuperTrack.dao;

import com.swg.supertrack.SuperTrack.dto.Org;
import com.swg.supertrack.SuperTrack.dto.Superhuman;
import java.util.List;

/**
 *
 * @author Asus1
 */
public interface SuperhumanDao {
    
    Superhuman getSuperById(int id);
    List<Superhuman> getAllSupers();
    Superhuman addSuperhuman(Superhuman sH);
    void updateSuperhuman(Superhuman sH);
    void deleteSuperhuman(int id);
    
    List<Superhuman> getSupersForOrg(Org org);
    List<Superhuman> getAllSuperVillains();
    List<Superhuman> getSupersByPower(String powerClass);
}
