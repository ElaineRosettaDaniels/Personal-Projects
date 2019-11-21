/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.elainerosetta.infernalescape.dto;

import java.util.Objects;

/**
 *
 * @author Asus1
 */
public class Station {
    
    private int stationId;
    private String name;
    private int armorBonus;
    private String stAction;
    private boolean crewed;

    public int getStationId() {
        return stationId;
    }

    public void setStationId(int stationId) {
        this.stationId = stationId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getArmorBonus() {
        return armorBonus;
    }

    public void setArmorBonus(int armorBonus) {
        this.armorBonus = armorBonus;
    }

    public String getStAction() {
        return stAction;
    }

    public void setStAction(String stAction) {
        this.stAction = stAction;
    }

    public boolean isCrewed() {
        return crewed;
    }

    public void setCrewed(boolean crewed) {
        this.crewed = crewed;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 67 * hash + this.stationId;
        hash = 67 * hash + Objects.hashCode(this.name);
        hash = 67 * hash + this.armorBonus;
        hash = 67 * hash + Objects.hashCode(this.stAction);
        hash = 67 * hash + (this.crewed ? 1 : 0);
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
        final Station other = (Station) obj;
        if (this.stationId != other.stationId) {
            return false;
        }
        if (this.armorBonus != other.armorBonus) {
            return false;
        }
        if (this.crewed != other.crewed) {
            return false;
        }
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.stAction, other.stAction)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Station{" 
                + "stationId=" + stationId 
                + ", name=" + name 
                + ", armorBonus=" + armorBonus 
                + ", stAction=" + stAction 
                + ", crewed=" + crewed 
                + '}';
    }
    
    
    
}
