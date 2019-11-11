/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.elainerosetta.tavernsimulator.DTO;

import java.util.Objects;

/**
 *
 * @author Asus1
 */
public class Tavern {
    
    private int tavernId;
    private String tavernName;
    private String tavernOwner;
    private String tavernAbout;
    private int tavernFunds;
    private int tavernFood;
    private int tavernDrinks;
    private int tavernRepairs;

    public int getTavernId() {
        return tavernId;
    }

    public void setTavernId(int tavernId) {
        this.tavernId = tavernId;
    }

    public String getTavernName() {
        return tavernName;
    }

    public void setTavernName(String tavernName) {
        this.tavernName = tavernName;
    }

    public String getTavernOwner() {
        return tavernOwner;
    }

    public void setTavernOwner(String tavernOwner) {
        this.tavernOwner = tavernOwner;
    }

    public String getTavernAbout() {
        return tavernAbout;
    }

    public void setTavernAbout(String tavernAbout) {
        this.tavernAbout = tavernAbout;
    }

    public int getTavernFunds() {
        return tavernFunds;
    }

    public void setTavernFunds(int tavernFunds) {
        this.tavernFunds = tavernFunds;
    }

    public int getTavernFood() {
        return tavernFood;
    }

    public void setTavernFood(int tavernFood) {
        this.tavernFood = tavernFood;
    }

    public int getTavernDrinks() {
        return tavernDrinks;
    }

    public void setTavernDrinks(int tavernDrinks) {
        this.tavernDrinks = tavernDrinks;
    }

    public int getTavernRepairs() {
        return tavernRepairs;
    }

    public void setTavernRepairs(int tavernRepairs) {
        this.tavernRepairs = tavernRepairs;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 53 * hash + this.tavernId;
        hash = 53 * hash + Objects.hashCode(this.tavernName);
        hash = 53 * hash + Objects.hashCode(this.tavernOwner);
        hash = 53 * hash + Objects.hashCode(this.tavernAbout);
        hash = 53 * hash + this.tavernFunds;
        hash = 53 * hash + this.tavernFood;
        hash = 53 * hash + this.tavernDrinks;
        hash = 53 * hash + this.tavernRepairs;
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
        final Tavern other = (Tavern) obj;
        if (this.tavernId != other.tavernId) {
            return false;
        }
        if (this.tavernFunds != other.tavernFunds) {
            return false;
        }
        if (this.tavernFood != other.tavernFood) {
            return false;
        }
        if (this.tavernDrinks != other.tavernDrinks) {
            return false;
        }
        if (this.tavernRepairs != other.tavernRepairs) {
            return false;
        }
        if (!Objects.equals(this.tavernName, other.tavernName)) {
            return false;
        }
        if (!Objects.equals(this.tavernOwner, other.tavernOwner)) {
            return false;
        }
        if (!Objects.equals(this.tavernAbout, other.tavernAbout)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Tavern{" 
                + "tavernId=" + tavernId 
                + ", tavernName=" + tavernName 
                + ", tavernOwner=" + tavernOwner 
                + ", tavernAbout=" + tavernAbout 
                + ", tavernFunds=" + tavernFunds 
                + ", tavernFood=" + tavernFood 
                + ", tavernDrinks=" + tavernDrinks 
                + ", tavernRepairs=" + tavernRepairs 
                + '}';
    }
    
}
