/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package floormaster.service;

import floormaster.dao.FilePersistenceException;
import floormaster.dao.OrderDaoException;
import floormaster.dao.ProductDaoException;
import floormaster.dao.TaxDaoException;
import floormaster.dto.Order;
import floormaster.dto.Product;
import floormaster.dto.Tax;
import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.util.List;

/**
 *
 * @author Asus1
 */
public interface ServiceLayer {
    
    // OrderDao stuff
    public void addOrder(Order order);
    public void loadOrders() throws FileNotFoundException, OrderDaoException;
    public List<Order> getAllOrders();
    public Order getOrder(Integer orderNum);
    public Order getOneOrder(String date, Integer orderNum);
    public boolean checkTrainingMode() throws OrderDaoException;
    public void removeOrder(Order order);
    public void saveOrders() throws OrderDaoException;
    
    // TaxDao stuff
    public Tax getOneTax(String stateAbb);
    public List<Tax> getAllTaxes();
    public void loadTaxes() throws FileNotFoundException, TaxDaoException;

    
    // ProductDao stuff
    public void loadProducts() throws FileNotFoundException, ProductDaoException;
    public Product getOneProduct(String pType);
    public List<Product> getAllProducts();
    
    // Original methods
    public Integer newOrderNumber() throws FilePersistenceException;
    public void giveOrderDetails(Order order) throws FilePersistenceException, OrderDaoException;
    public BigDecimal calcMatCost(BigDecimal area, BigDecimal costPerSqFt);
    public BigDecimal calcLaborCost(BigDecimal area, BigDecimal laborCostPerSqFt);
    public BigDecimal calcTax(BigDecimal matCost, BigDecimal laborCost, BigDecimal taxRate);
    public BigDecimal calcTotal(BigDecimal matCost, BigDecimal laborCost, BigDecimal tax);
    public void deleteEmptyFiles();
}
