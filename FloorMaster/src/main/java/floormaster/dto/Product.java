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
public class Product {
    
    private String productType;
    private BigDecimal costPerSqFt;
    private BigDecimal laborCostPerSqFt;
    
    public Product() {}
    public Product(String productType, BigDecimal costPerSqFt, BigDecimal laborCostPerSqFt) {
        this.productType = productType;
        this.costPerSqFt = costPerSqFt.setScale(2, RoundingMode.HALF_UP);
        this.laborCostPerSqFt = laborCostPerSqFt.setScale(2, RoundingMode.HALF_UP);
    }

    
    public String getProductType() {
        return productType;
    }
    public void setProductType(String productType) {
        this.productType = productType;
    }
    public BigDecimal getCostPerSqFt() {
        return costPerSqFt;
    }
    public void setCostPerSqFt(BigDecimal costPerSqFt) {
        this.costPerSqFt = costPerSqFt;
    }
    public BigDecimal getLaborCostPerSqFt() {
        return laborCostPerSqFt;
    }
    public void setLaborCostPerSqFt(BigDecimal laborCostPerSqFt) {
        this.laborCostPerSqFt = laborCostPerSqFt;
    }
}