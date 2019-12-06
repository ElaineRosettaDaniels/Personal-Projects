/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package floormaster.dto;

import java.math.BigDecimal;

/**
 *
 * @author Asus1
 */
public class Order {
    
    private Integer orderNum;
    private String clientName;
    private String state;
    private BigDecimal taxRate;
    private String productType;
    private BigDecimal area;
    private BigDecimal costPerSqFt;
    private BigDecimal laborCostPerSqFt;
    private BigDecimal materialCost;
    private BigDecimal laborCost;
    private BigDecimal tax;
    private BigDecimal total;
    private String delivDate;

    
    // no args constructor
    public Order() {
    }
    
    public Order(Integer orderNum){
        this.orderNum = orderNum;
    }
    
    // Order info given to us by client
    public Order(String delivDate, String clientName, String state,
           String productType, BigDecimal area) {
        
    }
    // All ARGS constructor
    public Order(Integer orderNum, String clientName, String state, 
            BigDecimal taxRate, String productType, BigDecimal area, 
            BigDecimal costPerSqFt, BigDecimal laborCostPerSqFt, BigDecimal materialCost, 
            BigDecimal laborCost, BigDecimal tax, BigDecimal total,
            String delivDate) {
        this.orderNum = orderNum;
        this.clientName = clientName;
        this.state = state;
        this.taxRate = taxRate;
        this.productType = productType;
        this.area = area;
        this.costPerSqFt = costPerSqFt;
        this.laborCostPerSqFt = laborCostPerSqFt;
        this.materialCost = materialCost;
        this.laborCost = laborCost;
        this.tax = tax;
        this.total = total;
        this.delivDate = delivDate;
    }
    
    public Integer getOrderNum() {
        return orderNum;
    }
    public void setOrderNum(Integer orderNum) {
        this.orderNum = orderNum;
    }
    public String getClientName() {
        return clientName;
    }
    public void setClientName(String clientName) {
        this.clientName = clientName;
    }
    public String getState() {
        return state;
    }
    public void setState(String state) {
        this.state = state;
    }
    public BigDecimal getTaxRate() {
        return taxRate;
    }
    public void setTaxRate(BigDecimal taxRate) {
        this.taxRate = taxRate;
    }
    public String getProductType() {
        return productType;
    }
    public void setProductType(String productType) {
        this.productType = productType;
    }
    public BigDecimal getArea() {
        return area;
    }
    public void setArea(BigDecimal area) {
        this.area = area;
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
    public BigDecimal getMaterialCost() {
        return materialCost;
    }
    public void setMaterialCost(BigDecimal materialCost) {
        this.materialCost = materialCost;
    }
    public BigDecimal getLaborCost() {
        return laborCost;
    }
    public void setLaborCost(BigDecimal laborCost) {
        this.laborCost = laborCost;
    }
    public BigDecimal getTax() {
        return tax;
    }
    public void setTax(BigDecimal tax) {
        this.tax = tax;
    }
    public BigDecimal getTotal() {
        return total;
    }
    public void setTotal(BigDecimal total) {
        this.total = total;
    } 
    public String getDelivDate() {
        return delivDate;
    }
    public void setDelivDate(String delivDate) {
        this.delivDate = delivDate;
    }  
}
