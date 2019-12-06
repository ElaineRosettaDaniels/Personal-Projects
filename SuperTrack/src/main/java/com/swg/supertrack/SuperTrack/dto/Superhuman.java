/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swg.supertrack.SuperTrack.dto;

import java.util.Objects;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 *
 * @author Asus1
 */
public class Superhuman {
    
    private int superId;
    
    @NotBlank(message = "Please enter a name.")
    @Size(max = 100, message = "'Name' must not exceed 100 characters.")
    private String superName;
    
    @Size(max = 255, message = "'Description' must not exceed 255 characters.")
    private String superDossier;
    
    @NotBlank(message = "Please select a power class.")
    private String powerClass;
    
    @NotBlank(message = "Please select an alignment.")
    private String alignment;

    public int getSuperId() {
        return superId;
    }

    public void setSuperId(int superId) {
        this.superId = superId;
    }

    public String getSuperName() {
        return superName;
    }

    public void setSuperName(String superName) {
        this.superName = superName;
    }

    public String getSuperDossier() {
        return superDossier;
    }

    public void setSuperDossier(String superDossier) {
        this.superDossier = superDossier;
    }

    public String getPowerClass() {
        return powerClass;
    }

    public void setPowerClass(String powerClass) {
        this.powerClass = powerClass;
    }

    public String getAlignment() {
        return alignment;
    }

    public void setAlignment(String alignment) {
        this.alignment = alignment;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + this.superId;
        hash = 59 * hash + Objects.hashCode(this.superName);
        hash = 59 * hash + Objects.hashCode(this.superDossier);
        hash = 59 * hash + Objects.hashCode(this.powerClass);
        hash = 59 * hash + Objects.hashCode(this.alignment);
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
        final Superhuman other = (Superhuman) obj;
        if (this.superId != other.superId) {
            return false;
        }
        if (!Objects.equals(this.superName, other.superName)) {
            return false;
        }
        if (!Objects.equals(this.superDossier, other.superDossier)) {
            return false;
        }
        if (!Objects.equals(this.powerClass, other.powerClass)) {
            return false;
        }
        if (!Objects.equals(this.alignment, other.alignment)) {
            return false;
        }
        return true;
    }

    

    
}
