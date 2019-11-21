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
public class Rider {
    
    private int riderId;
    private String name;
    private int armor;
    private int hitPoints;
    private int stationId;

    public int getRiderId() {
        return riderId;
    }

    public void setRiderId(int riderId) {
        this.riderId = riderId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getArmor() {
        return armor;
    }

    public void setArmor(int armor) {
        this.armor = armor;
    }

    public int getHitPoints() {
        return hitPoints;
    }

    public void setHitPoints(int hitPoints) {
        this.hitPoints = hitPoints;
    }

    public int getStationId() {
        return stationId;
    }

    public void setStationId(int stationId) {
        this.stationId = stationId;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + this.riderId;
        hash = 97 * hash + Objects.hashCode(this.name);
        hash = 97 * hash + this.armor;
        hash = 97 * hash + this.hitPoints;
        hash = 97 * hash + this.stationId;
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
        final Rider other = (Rider) obj;
        if (this.riderId != other.riderId) {
            return false;
        }
        if (this.armor != other.armor) {
            return false;
        }
        if (this.hitPoints != other.hitPoints) {
            return false;
        }
        if (this.stationId != other.stationId) {
            return false;
        }
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Rider{" + 
                "riderId=" + riderId 
                + ", name=" + name 
                + ", armor=" + armor 
                + ", hitPoints=" + hitPoints 
                + ", stationId=" + stationId 
                + '}';
    }
    
    
    
}
