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
public class Locale {
    
    private int localeId;
    
    @NotBlank(message = "Please enter a name.")
    @Size(max = 100, message = "'Name' must not exceed 100 characters.")
    private String localeName;
    
    @Size(max = 255, message = "'Description' must not exceed 255 characters.")
    private String localeDossier;
    
    @Size(max = 50, message = "'Address' must not exceed 50 characters.")
    private String address;
    
    @Size(max = 50, message = "'District' must not exceed 50 characters.")
    private String district;
    
    @NotBlank(message = "Please enter a city.")
    @Size(max = 50, message = "'City' must not exceed 50 characters.")
    private String city;
    
    @NotBlank(message = "Please enter a country.")
    @Size(max = 50, message = "'Country' must not exceed 50 characters.")
    private String country;
    
    @Size(max = 50, message = "'Coordinates' must not exceed 50 characters.")
    private String longLat;

    public int getLocaleId() {
        return localeId;
    }

    public void setLocaleId(int localeId) {
        this.localeId = localeId;
    }

    public String getLocaleName() {
        return localeName;
    }

    public void setLocaleName(String localeName) {
        this.localeName = localeName;
    }

    public String getLocaleDossier() {
        return localeDossier;
    }

    public void setLocaleDossier(String localeDossier) {
        this.localeDossier = localeDossier;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getLongLat() {
        return longLat;
    }

    public void setLongLat(String longLat) {
        this.longLat = longLat;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 17 * hash + this.localeId;
        hash = 17 * hash + Objects.hashCode(this.localeName);
        hash = 17 * hash + Objects.hashCode(this.localeDossier);
        hash = 17 * hash + Objects.hashCode(this.address);
        hash = 17 * hash + Objects.hashCode(this.district);
        hash = 17 * hash + Objects.hashCode(this.city);
        hash = 17 * hash + Objects.hashCode(this.country);
        hash = 17 * hash + Objects.hashCode(this.longLat);
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
        final Locale other = (Locale) obj;
        if (this.localeId != other.localeId) {
            return false;
        }
        if (!Objects.equals(this.localeName, other.localeName)) {
            return false;
        }
        if (!Objects.equals(this.localeDossier, other.localeDossier)) {
            return false;
        }
        if (!Objects.equals(this.address, other.address)) {
            return false;
        }
        if (!Objects.equals(this.district, other.district)) {
            return false;
        }
        if (!Objects.equals(this.city, other.city)) {
            return false;
        }
        if (!Objects.equals(this.country, other.country)) {
            return false;
        }
        if (!Objects.equals(this.longLat, other.longLat)) {
            return false;
        }
        return true;
    }
    
    
}
