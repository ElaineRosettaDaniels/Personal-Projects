/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package floormaster.tests;

import floormaster.dao.OrderDao;
import floormaster.dao.ProductDao;
import floormaster.dao.TaxDao;
import floormaster.dto.Order;
import floormaster.dto.Product;
import floormaster.dto.Tax;
import floormaster.service.ServiceLayer;
import floormaster.service.ServiceLayerImpl;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 *
 * @author Asus1
 */
public class ServiceLayerImplTest {
    
    private ServiceLayer serv;
    public ServiceLayerImplTest(){
        OrderDao daoOS = new OrderDaoFileImplSTUB();
        TaxDao daoTS = new TaxDaoFileImplSTUB();
        ProductDao daoPS = new ProductDaoFileImplSTUB();
        serv = new ServiceLayerImpl(daoOS, daoTS, daoPS);
    }
    
    @Test
    public void newOrderNumberTest() {
        // ARRANGE
        List<Order> allOrders = serv.getAllOrders();
        List<Integer> orderNumbers = new ArrayList<>();
        orderNumbers.add(0);
        Integer orNum = 2;
        // ACT
        for(Order each : allOrders) {
            orderNumbers.add(each.getOrderNum());
        }
        Integer newOrderNumber = Collections.max(orderNumbers) + 1;
        // ASSERT
        Assertions.assertEquals(orNum, newOrderNumber, "newOrderNumber should be 2.");
        Assertions.assertNotNull(allOrders, "allOrders should not be null.");
    }
    
    @Test
    public void giveOrderDetailsTest() {
        // ARRANGE
        Order onlyOrder = serv.getOrder(1);
        Product type = serv.getOneProduct(onlyOrder.getProductType());
        String orderState = onlyOrder.getState();
        Tax stateTaxRate = serv.getOneTax(orderState);
        // ACT
        onlyOrder.setTaxRate(stateTaxRate.getTaxRate());
        onlyOrder.setCostPerSqFt(type.getCostPerSqFt());
        onlyOrder.setLaborCostPerSqFt(type.getLaborCostPerSqFt());
        onlyOrder.setMaterialCost(serv.calcMatCost(onlyOrder.getArea(), onlyOrder.getCostPerSqFt()).setScale(2, RoundingMode.HALF_UP));
        onlyOrder.setLaborCost(serv.calcLaborCost(onlyOrder.getArea(), onlyOrder.getLaborCostPerSqFt()).setScale(2, RoundingMode.HALF_UP));
        onlyOrder.setTax(serv.calcTax(onlyOrder.getMaterialCost(), onlyOrder.getLaborCost(), onlyOrder.getTaxRate()).setScale(2, RoundingMode.HALF_UP));
        onlyOrder.setTotal(serv.calcTotal(onlyOrder.getMaterialCost(), onlyOrder.getLaborCost(), onlyOrder.getTax()).setScale(2, RoundingMode.HALF_UP));
        // ASSERT
        Assertions.assertEquals(stateTaxRate.getTaxRate(), onlyOrder.getTaxRate(), "onlyOrder TaxRate does not match input.");
        Assertions.assertEquals(type.getCostPerSqFt(), onlyOrder.getCostPerSqFt(), "onlyOrder CostPerSqFt does not match input.");
        Assertions.assertEquals(type.getLaborCostPerSqFt(), onlyOrder.getLaborCostPerSqFt(), "onlyOrder LaborCostPerSqFt does not match input.");
        Assertions.assertEquals(new BigDecimal("24205.00"), onlyOrder.getMaterialCost(), "onlyOrder matCost should be 24205.00");
        Assertions.assertEquals(new BigDecimal("22325.00"), onlyOrder.getLaborCost(), "onlyOrder laborCost should be 22325.00");
        Assertions.assertEquals(new BigDecimal("2791.80"), onlyOrder.getTax(), "onlyOrder tax should be 2791.80");
        Assertions.assertEquals(new BigDecimal("49321.80"), onlyOrder.getTotal(), "onlyOrder total should be 49321.80");
        
    }
    
    
    // Other native calculations tests covered by above test
}
