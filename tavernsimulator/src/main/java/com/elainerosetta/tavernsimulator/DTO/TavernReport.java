/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.elainerosetta.tavernsimulator.DTO;

import java.time.LocalDate;
import java.util.Objects;

/**
 *
 * @author Asus1
 */
public class TavernReport {
    
    private int tavernReportId;
    private LocalDate tavernReportDay;
    private int tavernReportRoll;
    private int tavernReportEarnings;
    private int tavernReportFunds;
    private int tavernId;

    public int getTavernReportId() {
        return tavernReportId;
    }

    public void setTavernReportId(int tavernReportId) {
        this.tavernReportId = tavernReportId;
    }
    
    public LocalDate getTavernReportDay() {
        return tavernReportDay;
    }

    public void setTavernReportDay(LocalDate tavernReportDay) {
        this.tavernReportDay = tavernReportDay;
    }

    public int getTavernReportRoll() {
        return tavernReportRoll;
    }

    public void setTavernReportRoll(int tavernReportRoll) {
        this.tavernReportRoll = tavernReportRoll;
    }

    public int getTavernReportEarnings() {
        return tavernReportEarnings;
    }

    public void setTavernReportEarnings(int tavernReportEarnings) {
        this.tavernReportEarnings = tavernReportEarnings;
    }

    public int getTavernReportFunds() {
        return tavernReportFunds;
    }

    public void setTavernReportFunds(int tavernReportFunds) {
        this.tavernReportFunds = tavernReportFunds;
    }

    public int getTavernId() {
        return tavernId;
    }

    public void setTavernId(int tavernId) {
        this.tavernId = tavernId;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + this.tavernReportId;
        hash = 79 * hash + Objects.hashCode(this.tavernReportDay);
        hash = 79 * hash + this.tavernReportRoll;
        hash = 79 * hash + this.tavernReportEarnings;
        hash = 79 * hash + this.tavernReportFunds;
        hash = 79 * hash + this.tavernId;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final TavernReport other = (TavernReport) obj;
        if (this.tavernReportId != other.tavernReportId) {
            return false;
        }
        if (this.tavernReportRoll != other.tavernReportRoll) {
            return false;
        }
        if (this.tavernReportEarnings != other.tavernReportEarnings) {
            return false;
        }
        if (this.tavernReportFunds != other.tavernReportFunds) {
            return false;
        }
        if (this.tavernId != other.tavernId) {
            return false;
        }
        if (!Objects.equals(this.tavernReportDay, other.tavernReportDay)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "TavernReport{" 
                + "tavernReportId=" + tavernReportId 
                + ", tavernReportDay=" + tavernReportDay 
                + ", tavernReportRoll=" + tavernReportRoll 
                + ", tavernReportEarnings=" + tavernReportEarnings 
                + ", tavernReportFunds=" + tavernReportFunds 
                + ", tavernId=" + tavernId 
                + '}';
    }

    
    
}
