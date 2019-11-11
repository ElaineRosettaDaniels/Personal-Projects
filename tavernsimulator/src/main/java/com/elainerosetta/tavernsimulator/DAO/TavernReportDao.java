/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.elainerosetta.tavernsimulator.DAO;

import com.elainerosetta.tavernsimulator.DTO.Tavern;
import com.elainerosetta.tavernsimulator.DTO.TavernReport;
import java.util.List;

/**
 *
 * @author Asus1
 */
public interface TavernReportDao {
    
    TavernReport getReportById(int tavernReportId);
    List<TavernReport> getReportsForTavern(Tavern tavern);
    TavernReport addTavernReport(TavernReport report);
    void deleteTavernReport(int tavernReportId);
    
}
