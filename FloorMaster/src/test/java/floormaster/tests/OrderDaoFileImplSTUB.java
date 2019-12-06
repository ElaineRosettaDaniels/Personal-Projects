/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package floormaster.tests;

import floormaster.dao.OrderDao;
import floormaster.dao.OrderDaoException;
import floormaster.dto.Order;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 *
 * @author Asus1
 */
public class OrderDaoFileImplSTUB implements OrderDao {
    
    private Order onlyOrder;
    private List<Order> orderList = new ArrayList<>();
    
    public OrderDaoFileImplSTUB() {
        onlyOrder = new Order(1);
        onlyOrder.setDelivDate("06032019");
        onlyOrder.setClientName("Dunk Nightly");
        onlyOrder.setState("KY");
        onlyOrder.setProductType("Wood");
        onlyOrder.setArea(new BigDecimal("4700.00"));
        orderList.add(onlyOrder);
    }

    @Override
    public Order addOrder(Order order) {
        if(order.getOrderNum().equals(onlyOrder.getOrderNum())) return onlyOrder;
        else return null;
    }

    @Override
    public List<Order> getAllOrders() {
        return orderList;
    }

    @Override
    public Order getOrder(Integer orderNum) {
        if(orderNum.equals(onlyOrder.getOrderNum())) return onlyOrder;
        else return null;
    }
    
    @Override
    public Order getOneOrder(String date, Integer orderNum){
        Order wantedOrder = null;
        List<Order> ordersInDate = new ArrayList<>();
        for (Order each : orderList){
            if(each.getDelivDate().equals(date)) {
                ordersInDate.add(each);
            }
        }
        for (Order each : ordersInDate) {
            if(each.getOrderNum().equals(orderNum)){
                wantedOrder = each;
            }
        }
        return wantedOrder;
    }

    @Override
    public void removeOrder(Order order) {
        orderList.remove(order);
    }
    
    @Override
    public void loadOrders() throws OrderDaoException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void saveOrders() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean checkTrainingMode() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Set<String> getAllDates() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
