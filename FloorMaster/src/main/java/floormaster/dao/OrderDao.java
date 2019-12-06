/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package floormaster.dao;

import floormaster.dto.Order;
import java.util.List;
import java.util.Set;

/**
 *
 * @author Asus1
 */
public interface OrderDao {
    // C
    public Order addOrder(Order order);
    // R
    public List<Order> getAllOrders();
    public Set<String> getAllDates();
    public Order getOrder(Integer orderNum);
    public Order getOneOrder(String date, Integer orderNum);
    public void loadOrders() throws OrderDaoException;
    public boolean checkTrainingMode() throws OrderDaoException;
    // U
    public void saveOrders() throws OrderDaoException;
    // D
    public void removeOrder(Order order);
    
}
