/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package floormaster.tests;

import floormaster.dao.OrderDao;
import floormaster.dao.OrderDaoFileImpl;
import floormaster.dto.Order;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 *
 * @author Asus1
 */
public class OrderDaoFileImplTest {
    
    OrderDao testDao;
    public OrderDaoFileImplTest() {
        testDao = new OrderDaoFileImpl();
    }
    
    @Test
    public void addOrderTest() {
        // ARRANGE
        Order testOrder = new Order(1);
        testOrder.setDelivDate("06032019");
        testOrder.setClientName("Bill D'yorhouse");
        testOrder.setState("KY");
        testOrder.setProductType("Wood");
        testOrder.setArea(new BigDecimal("100.00"));
        // ACT
        testDao.addOrder(testOrder);
        Order addedOrder = testDao.getOrder(1);
        // ASSERT
        Assertions.assertEquals(testOrder.getOrderNum(), addedOrder.getOrderNum(), "orderNum should match for both.");
        Assertions.assertEquals(testOrder.getClientName(), addedOrder.getClientName(), "clientName should match for both.");
        Assertions.assertEquals(testOrder.getState(), addedOrder.getState(), "state should match for both.");
        Assertions.assertEquals(testOrder.getProductType(), addedOrder.getProductType(), "productType should match for both.");
        Assertions.assertEquals(testOrder.getDelivDate(), addedOrder.getDelivDate(), "delivDate should match for both.");
    }
    
    @Test
    public void getAllOrdersTest() {
        // ARRANGE
        Order testOrder1 = new Order(1);
        testOrder1.setDelivDate("06032019");
        testOrder1.setClientName("Bill D'yorhouse");
        testOrder1.setState("KY");
        testOrder1.setProductType("Wood");
        testOrder1.setArea(new BigDecimal("100.00"));
        Order testOrder2 = new Order(2);
        testOrder2.setDelivDate("06032019");
        testOrder2.setClientName("Phil M'Kraken");
        testOrder2.setState("CA");
        testOrder2.setProductType("Tile");
        testOrder2.setArea(new BigDecimal("100.00"));
        // ACT
        testDao.addOrder(testOrder1);
        testDao.addOrder(testOrder2);
        
        List<Order> allOrders = testDao.getAllOrders();
        // ASSERT
        Assertions.assertNotNull(allOrders, "our order list should not be null");
        Assertions.assertEquals(2, allOrders.size(), "there should be 2 items in the list");
        Assertions.assertTrue(allOrders.contains(testOrder1),
                "Order list should have testOrder1.");
        Assertions.assertTrue(allOrders.contains(testOrder2),
                "Order list should have testOrder2.");
        
    }

    @Test
    public void getOneOrderTest() {
        // ARRANGE
        Order testOrder = new Order(1);
        testOrder.setDelivDate("06032019");
        testOrder.setClientName("Bill D'yorhouse");
        testOrder.setState("KY");
        testOrder.setProductType("Wood");
        testOrder.setArea(new BigDecimal("100.00"));
        // ACT
        testDao.addOrder(testOrder);
        Order gottenOrder = testDao.getOrder(1);
        // ASSERT
        Assertions.assertEquals(testOrder.getOrderNum(), gottenOrder.getOrderNum(), "orderNum should match for both.");
        Assertions.assertEquals(testOrder.getClientName(), gottenOrder.getClientName(), "clientName should match for both.");
        Assertions.assertEquals(testOrder.getState(), gottenOrder.getState(), "state should match for both.");
        Assertions.assertEquals(testOrder.getProductType(), gottenOrder.getProductType(), "productType should match for both.");
        Assertions.assertEquals(testOrder.getDelivDate(), gottenOrder.getDelivDate(), "delivDate should match for both.");
    }

    @Test
    public void editOrderTest() {
        
    }

    @Test
    public void removeOrderTest() {
        // ARRANGE
        
        // ACT
        
        // ASSERT
        
       
    }
    
}
