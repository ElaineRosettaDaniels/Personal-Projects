/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package floormaster.dto;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 *
 * @author Asus1
 */
public class Tax {
    private String stateAbb;
    private String stateName;
    private BigDecimal taxRate;
    public Tax() {
    }
    public Tax(String stateAbb, String stateName, BigDecimal taxRate) {
        this.stateAbb = stateAbb;
        this.stateName = stateName;
        this.taxRate = taxRate.setScale(2, RoundingMode.HALF_UP);
    }
    public String getStateAbb() {
        return stateAbb;
    }
    public void setStateAbb(String stateAbb) {
        this.stateAbb = stateAbb;
    }
    public String getStateName() {
        return stateName;
    }
    public void setStateName(String stateName) {
        this.stateName = stateName;
    }
    public BigDecimal getTaxRate() {
        return taxRate;
    }
    public void setTaxRate(BigDecimal taxRate) {
        this.taxRate = taxRate;
    }  
}
