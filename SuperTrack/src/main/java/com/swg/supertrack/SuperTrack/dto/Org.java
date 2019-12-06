/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swg.supertrack.SuperTrack.dto;

import java.util.List;
import java.util.Objects;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

/**
 *
 * @author Asus1
 */
public class Org {
    
    private int orgId;
    
    @NotBlank(message = "Please enter a name.")
    @Size(max = 100, message = "'Name' must not exceed 100 characters.")
    private String orgName;
    
    @Size(max = 255, message = "'Description' must not exceed 255 characters.")
    private String orgDossier;
    
    private Locale locale;
    
    @NotEmpty(message = "Please select at least one superhuman.")
    @Size(min = 1, message = "Please select at least one superhuman.")
    private List<Superhuman> orgMembers;

    public int getOrgId() {
        return orgId;
    }

    public void setOrgId(int orgId) {
        this.orgId = orgId;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public String getOrgDossier() {
        return orgDossier;
    }

    public void setOrgDossier(String orgDossier) {
        this.orgDossier = orgDossier;
    }

    public Locale getLocale() {
        return locale;
    }

    public void setLocale(Locale locale) {
        this.locale = locale;
    }

    public List<Superhuman> getOrgMembers() {
        return orgMembers;
    }

    public void setOrgMembers(List<Superhuman> orgMembers) {
        this.orgMembers = orgMembers;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + this.orgId;
        hash = 59 * hash + Objects.hashCode(this.orgName);
        hash = 59 * hash + Objects.hashCode(this.orgDossier);
        hash = 59 * hash + Objects.hashCode(this.locale);
        hash = 59 * hash + Objects.hashCode(this.orgMembers);
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
        final Org other = (Org) obj;
        if (this.orgId != other.orgId) {
            return false;
        }
        if (!Objects.equals(this.orgName, other.orgName)) {
            return false;
        }
        if (!Objects.equals(this.orgDossier, other.orgDossier)) {
            return false;
        }
        if (!Objects.equals(this.locale, other.locale)) {
            return false;
        }
        if (!Objects.equals(this.orgMembers, other.orgMembers)) {
            return false;
        }
        return true;
    }



    

    
    
}
