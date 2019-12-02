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
public class Vehicle {
    
    private int vehicleId;
    private String veName;
    private String veType;
    private int armor;
    private int speed;
    private int dexBonus;
    private int strBonus;
    private int hitPoints;
    private int damThres;
    private int misThres;
    private int totalDist;
    private boolean ichorBoosted;
    private int ichorUses;
    private int maxRiders;

    public Vehicle() {
    }

    public int getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(int vehicleId) {
        this.vehicleId = vehicleId;
    }

    public String getVeName() {
        return veName;
    }

    public void setVeName(String veName) {
        this.veName = veName;
    }

    public String getVeType() {
        return veType;
    }

    public void setVeType(String veType) {
        this.veType = veType;
    }

    public int getArmor() {
        return armor;
    }

    public void setArmor(int armor) {
        this.armor = armor;
    }

    public int getSpeed() {
        return speed;
    }

    public int getDexBonus() {
        return dexBonus;
    }

    public void setDexBonus(int dexBonus) {
        this.dexBonus = dexBonus;
    }

    public int getStrBonus() {
        return strBonus;
    }

    public void setStrBonus(int strBonus) {
        this.strBonus = strBonus;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getHitPoints() {
        return hitPoints;
    }

    public void setHitPoints(int hitPoints) {
        this.hitPoints = hitPoints;
    }

    public int getDamThres() {
        return damThres;
    }

    public void setDamThres(int damThres) {
        this.damThres = damThres;
    }

    public int getMisThres() {
        return misThres;
    }

    public void setMisThres(int misThres) {
        this.misThres = misThres;
    }

    public int getTotalDist() {
        return totalDist;
    }

    public void setTotalDist(int totalDist) {
        this.totalDist = totalDist;
    }

    public boolean isIchorBoosted() {
        return ichorBoosted;
    }

    public void setIchorBoosted(boolean ichorBoosted) {
        this.ichorBoosted = ichorBoosted;
    }

    public int getIchorUses() {
        return ichorUses;
    }

    public void setIchorUses(int ichorUses) {
        this.ichorUses = ichorUses;
    }

    public int getMaxRiders() {
        return maxRiders;
    }

    public void setMaxRiders(int maxRiders) {
        this.maxRiders = maxRiders;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 41 * hash + this.vehicleId;
        hash = 41 * hash + Objects.hashCode(this.veName);
        hash = 41 * hash + Objects.hashCode(this.veType);
        hash = 41 * hash + this.armor;
        hash = 41 * hash + this.speed;
        hash = 41 * hash + this.dexBonus;
        hash = 41 * hash + this.strBonus;
        hash = 41 * hash + this.hitPoints;
        hash = 41 * hash + this.damThres;
        hash = 41 * hash + this.misThres;
        hash = 41 * hash + this.totalDist;
        hash = 41 * hash + (this.ichorBoosted ? 1 : 0);
        hash = 41 * hash + this.ichorUses;
        hash = 41 * hash + this.maxRiders;
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
        final Vehicle other = (Vehicle) obj;
        if (this.vehicleId != other.vehicleId) {
            return false;
        }
        if (this.armor != other.armor) {
            return false;
        }
        if (this.speed != other.speed) {
            return false;
        }
        if (this.dexBonus != other.dexBonus) {
            return false;
        }
        if (this.strBonus != other.strBonus) {
            return false;
        }
        if (this.hitPoints != other.hitPoints) {
            return false;
        }
        if (this.damThres != other.damThres) {
            return false;
        }
        if (this.misThres != other.misThres) {
            return false;
        }
        if (this.totalDist != other.totalDist) {
            return false;
        }
        if (this.ichorBoosted != other.ichorBoosted) {
            return false;
        }
        if (this.ichorUses != other.ichorUses) {
            return false;
        }
        if (this.maxRiders != other.maxRiders) {
            return false;
        }
        if (!Objects.equals(this.veName, other.veName)) {
            return false;
        }
        if (!Objects.equals(this.veType, other.veType)) {
            return false;
        }
        return true;
    }

    

    @Override
    public String toString() {
        return "vehicle{" + 
                "vehicleId=" + vehicleId +
                "veName=" + veName + 
                ", veType=" + veType + 
                ", armor=" + armor + 
                ", speed=" + speed + 
                ", dexBonus=" + dexBonus +
                ", strBonus=" + strBonus +
                ", hitPoints=" + hitPoints + 
                ", damThres=" + damThres + 
                ", misThres=" + misThres + 
                ", totalDist=" + totalDist + 
                ", ichorBoosted=" + ichorBoosted + 
                ", ichorUses=" + ichorUses + 
                ", maxRiders=" + maxRiders + 
                '}';
    }
    
    
    
}
