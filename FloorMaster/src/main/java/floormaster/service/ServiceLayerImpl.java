/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package floormaster.service;

import floormaster.dao.FilePersistenceException;
import floormaster.dao.OrderDao;
import floormaster.dao.OrderDaoException;
import floormaster.dao.OrderDaoFileImpl;
import floormaster.dao.ProductDao;
import floormaster.dao.ProductDaoException;
import floormaster.dao.ProductDaoFileImpl;
import floormaster.dao.TaxDao;
import floormaster.dao.TaxDaoException;
import floormaster.dao.TaxDaoFileImpl;
import floormaster.dto.Order;
import floormaster.dto.Product;
import floormaster.dto.Tax;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Asus1
 */
public class ServiceLayerImpl implements ServiceLayer {

    private final OrderDao daoO;
    private final TaxDao daoT;
    private final ProductDao daoP;
    
    public ServiceLayerImpl(OrderDao daoO, TaxDao daoT, ProductDao daoP){
        this.daoO = daoO;
        this.daoT = daoT;
        this.daoP = daoP;
    }
    
    
    
    // Hey Ellie, here's something Karl showed you how to do:
    @Override
    public Integer newOrderNumber() throws FilePersistenceException { // Method to give each new order an ascending orderNum
        List<Order> allOrders = daoO.getAllOrders(); // Make a list of Orders from our daoO
        List<Integer> orderNumbers = new ArrayList<>(); // Make a list of Integers which will hold the orderNums
        orderNumbers.add(0); // Add 0 in there so we skip it
        for (Order each : allOrders) { // For each Order in our list allOrders,
            orderNumbers.add(each.getOrderNum()); // Add each orderNum from allOrders into our list OrderNumbers
        }
        Integer newOrderNumber = Collections.max(orderNumbers) + 1; // Scan for highest orderNum in the collection, plus by 1
        return newOrderNumber; // Now you have an orderNum that is the next one up
    }
    
    
    // Ellie, I'm proud of you for getting this one. I love you so much.
    // -Elaine
    @Override
    public void giveOrderDetails(Order order) throws FilePersistenceException, OrderDaoException {
        /*
        Notes about this method:
        the orderNum must be set to 0 immediately,
        so that when the order is being officially designated its orderNum,
        it will not appear as null in the List of order numbers.
        If it is not given a number while the designation is happening,
        the method will read the null inside the List of numbers
        and freak out.
        EDIT: However, if it already has a number, skip that step.
        */
        if (order.getOrderNum() == null){
            order.setOrderNum(0);
        }
        String orderProdType = order.getProductType();
        Product type = daoP.getOneProduct(orderProdType);
        order.setCostPerSqFt(type.getCostPerSqFt());
        order.setLaborCostPerSqFt(type.getLaborCostPerSqFt());
        String orderState = order.getState();
        Tax stateTaxRate = daoT.getOneTax(orderState);
        order.setTaxRate(stateTaxRate.getTaxRate());
        order.setMaterialCost(calcMatCost(order.getArea(), order.getCostPerSqFt()).setScale(2, RoundingMode.HALF_UP));
        order.setLaborCost(calcLaborCost(order.getArea(), order.getLaborCostPerSqFt()).setScale(2, RoundingMode.HALF_UP));
        order.setTax(calcTax(order.getMaterialCost(), order.getLaborCost(), order.getTaxRate()).setScale(2, RoundingMode.HALF_UP));
        order.setTotal(order.getMaterialCost().add(order.getLaborCost().add(order.getTax())).setScale(2, RoundingMode.HALF_UP));
    }
    
    @Override
    public BigDecimal calcMatCost(BigDecimal area, BigDecimal costPerSqFt) {
        BigDecimal matCost = area.multiply(costPerSqFt);
        matCost.setScale(2, RoundingMode.HALF_UP);
        return matCost;
    }
    
    @Override
    public BigDecimal calcLaborCost(BigDecimal area, BigDecimal laborCostPerSqFt) {
        BigDecimal laborCost = area.multiply(laborCostPerSqFt);
        laborCost.setScale(2, RoundingMode.HALF_UP);
        return laborCost;
    }
    
    @Override
    public BigDecimal calcTax(BigDecimal matCost, BigDecimal laborCost, BigDecimal taxRate) {
        BigDecimal hundo = new BigDecimal("100");
        BigDecimal taxPercent = taxRate.divide(hundo);
        BigDecimal costB4Tax = matCost.add(laborCost);
        BigDecimal tax = costB4Tax.multiply(taxPercent).setScale(2, RoundingMode.HALF_UP);
        return tax;
    }
    
    @Override
    public BigDecimal calcTotal(BigDecimal matCost, BigDecimal laborCost, BigDecimal tax) {
        BigDecimal total = matCost.add(laborCost.add(tax)).setScale(2, RoundingMode.HALF_UP);
        return total;
    }
    
    @Override
    public void deleteEmptyFiles()  {
        List<Order> allOrders = daoO.getAllOrders();
        Set<String> allDates = daoO.getAllDates();
        List<String> emptyFiles = new ArrayList<>();
        for(String date : allDates) {
            List<Order> ordersInDate = new ArrayList<>();
            for(Order o : allOrders) {
                if(o.getDelivDate().equals(date)){
                    ordersInDate.add(o);
                }
            }
            if(ordersInDate.isEmpty()){
                emptyFiles.add(date);
            }
        }
        for (String date : emptyFiles) {
            File file = new File("Orders/Orders_" + date + ".txt");
            boolean exists = file.exists();
            try {
                Files.delete(file.toPath());
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }   
    
    /**
     * ORDERDAO
     * ORDERDAO
     * ORDERDAO
     */
    
    @Override
    public boolean checkTrainingMode() throws OrderDaoException {
        boolean inTraining = daoO.checkTrainingMode();
        return inTraining;
    }
    @Override
    public void loadOrders() throws FileNotFoundException, OrderDaoException {
        daoO.loadOrders();
    }
    @Override
    public void addOrder(Order order) {
        daoO.addOrder(order);
    }
    @Override
    public List<Order> getAllOrders() {
        return daoO.getAllOrders();
    }
    @Override
    public Order getOrder(Integer orderNum) {
        return daoO.getOrder(orderNum);
    }
    @Override
    public Order getOneOrder(String date, Integer orderNum){
        return daoO.getOneOrder(date, orderNum);
    }
    @Override
    public void removeOrder(Order order) {
        daoO.removeOrder(order);
    }
    @Override
    public void saveOrders() throws OrderDaoException {
        daoO.saveOrders();
    }
    /**
     * TAXDAO
     * TAXDAO
     * TAXDAO
     */
    @Override
    public void loadTaxes() throws FileNotFoundException, TaxDaoException {
        daoT.loadTaxes();
    }
    @Override
    public Tax getOneTax(String stateAbb) {
        return daoT.getOneTax(stateAbb);
    }
    @Override
    public List<Tax> getAllTaxes() {
        return daoT.getAllTaxes();
    }
    /**
     * PRODUCTDAO
     * PRODUCTDAO
     * PRODUCTDAO
     */
    @Override
    public void loadProducts() throws FileNotFoundException, ProductDaoException {
        daoP.loadProducts();
    }
    @Override
    public Product getOneProduct(String pType) {
        return daoP.getOneProduct(pType);
    }
    @Override
    public List<Product> getAllProducts() {
        return daoP.getAllProducts();
    }

    
}
