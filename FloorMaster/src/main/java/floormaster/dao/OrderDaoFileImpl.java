/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package floormaster.dao;

import floormaster.dto.Order;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeMap;
import java.util.regex.Pattern;


/**
 *
 * @author Asus1
 */
public class OrderDaoFileImpl implements OrderDao {

    private static final String DELIMITER = ",";
    private String ORDER_FILE;
    private static final String MODE_FILE = "Mode.txt";
    private static final String MODE_DELIMITER = "::";
    private List<Order> allOrders = new ArrayList<>();
    private Set<String> allDates = new HashSet<>();
    // hashmap of string to int?
    
    

    private String unmarshallMode(String modeText){
        String[] modeTokens = modeText.split(MODE_DELIMITER);
        String activeMode = modeTokens[1];
        return activeMode;
    }
    
    @Override
    public boolean checkTrainingMode() throws OrderDaoException {
        Scanner scanner;
        boolean inTrainingMode = false;
        try {
                scanner = new Scanner(new BufferedReader(new FileReader(MODE_FILE)));
            } catch (FileNotFoundException e) {
                throw new OrderDaoException("Could not find order file.", e);
            }
        String currentLine;
        String currentMode = null;
        while(scanner.hasNextLine()) {
            currentLine = scanner.nextLine();
            currentMode = unmarshallMode(currentLine);
        }
        if(currentMode.equals("Training")){
            inTrainingMode = true;
        }
        return inTrainingMode;
    }
    
    private Order unmarshallOrder(String orderAsText) {
        String[] orderTokens = orderAsText.split(DELIMITER);
        Integer orderNum = Integer.parseInt(orderTokens[0]);
        Order orderFromFile = new Order();
        orderFromFile.setOrderNum(orderNum);
        orderFromFile.setClientName(orderTokens[1]);
        orderFromFile.setState(orderTokens[2]);
        orderFromFile.setTaxRate(new BigDecimal(orderTokens[3]));
        orderFromFile.setProductType(orderTokens[4]);
        orderFromFile.setArea(new BigDecimal(orderTokens[5]));
        orderFromFile.setCostPerSqFt(new BigDecimal(orderTokens[6]));
        orderFromFile.setLaborCostPerSqFt(new BigDecimal(orderTokens[7]));
        orderFromFile.setMaterialCost(new BigDecimal(orderTokens[8]));
        orderFromFile.setLaborCost(new BigDecimal(orderTokens[9]));
        orderFromFile.setTax(new BigDecimal(orderTokens[10]));
        orderFromFile.setTotal(new BigDecimal(orderTokens[11]));
        return orderFromFile;
    }
    
    @Override
    public void loadOrders() throws OrderDaoException {
        Scanner scanner;

        File folder = new File("Orders");
        File[] listOfFiles = folder.listFiles();
        for (File f : listOfFiles) {
            ORDER_FILE = f.getName();
            String fileDate = ORDER_FILE.substring(7, 15);
            allDates.add(fileDate); // ADD FILE DATE TO ALLDATES
            try {
                scanner = new Scanner(new BufferedReader(new FileReader(f)));
            } catch (FileNotFoundException e) {
                throw new OrderDaoException("Could not find order file.", e);
            }
            String currentLine;
            Order currentOrder;
            String headerLine = scanner.nextLine(); // DO THIS TO SKIP HEADER LINE AND PREVENT CRASH
            while(scanner.hasNextLine()) {
                currentLine = scanner.nextLine();
                currentOrder = unmarshallOrder(currentLine);
                currentOrder.setDelivDate(fileDate);
                allOrders.add(currentOrder); // ADD ORDER TO LIST OF ALL ORDERS
            }
            scanner.close();
        }
        
    }
    
    private String marshallOrder(Order order) {
        String orderText = ""; // DO THIS TO PREVENT null FROM APPEARING AT THE FRONT OF EVERY LINE
        orderText += order.getOrderNum() + DELIMITER;
        orderText += order.getClientName() + DELIMITER;
        orderText += order.getState() + DELIMITER;
        orderText += order.getTaxRate() + DELIMITER;
        orderText += order.getProductType() + DELIMITER;
        orderText += order.getArea() + DELIMITER;
        orderText += order.getCostPerSqFt() + DELIMITER;
        orderText += order.getLaborCostPerSqFt() + DELIMITER;
        orderText += order.getMaterialCost() + DELIMITER;
        orderText += order.getLaborCost() + DELIMITER;
        orderText += order.getTax() + DELIMITER;
        orderText += order.getTotal();
        return orderText;
    }
    
    @Override
    public void saveOrders() throws OrderDaoException {
        PrintWriter out;
        // ANY ORDERS W/O A DATE IN allDates WILL ADD THEIR DATE INTO allDates
        for (Order order : allOrders){
            if(!allDates.contains(order.getDelivDate())){
                allDates.add(order.getDelivDate());
            }
        }
        
        for (String date : allDates){
            try {
                out = new PrintWriter(new FileWriter("Orders/Orders_" + date + ".txt"));
            } catch (IOException e) {
                throw new OrderDaoException("Could not save order data to file.", e);
            }
            out.println("OrderNumber,CustomerName,State,TaxRate,ProductType,Area,CostPerSquareFoot,LaborCostPerSquareFoot,MaterialCost,LaborCost,Tax,Total");
            for (Order order : allOrders){
                if (order.getDelivDate().equals(date)) {
                String orderAsText = marshallOrder(order);
                out.println(orderAsText);
                out.flush();
                }
            }
            out.close();
        }
    }
    
    @Override
    public Order addOrder(Order order) {
        allOrders.add(order);
        return order;
    }
    
    @Override
    public List<Order> getAllOrders() {
        return allOrders;
    }
    
    @Override
    public Set<String> getAllDates() {
        return allDates;
    }
    
    @Override
    public Order getOneOrder(String date, Integer orderNum){
        Order wantedOrder = null;
        List<Order> ordersInDate = new ArrayList<>();
        for (Order each : allOrders){
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
    public Order getOrder(Integer orderNum) {
        Order gottenOrder = null;
        for (Order each : allOrders){
            if (each.getOrderNum().equals(orderNum)){
                gottenOrder = each;
            }
        }
        return gottenOrder;
    }

    @Override
    public void removeOrder(Order order) {
        allOrders.remove(order);
    }

    

}
