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
public interface OrgDao {
    
    Org getOrgById(int id);
    List<Org> getAllOrgs();
    Org addOrg(Org org);
    void updateOrg(Org org);
    void deleteOrg(int id);
    
    List<Org> getOrgsForSuper(Superhuman superhuman);
}
