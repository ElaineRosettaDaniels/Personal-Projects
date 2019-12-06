/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package floormaster.ui;

import floormaster.dto.Order;
import floormaster.exceptions.InvalidInputException;
import java.math.BigDecimal;
import java.util.List;

/**
 *
 * @author Asus1
 */
public class FloorMasterView {
    
    
    private UserIO io = new UserIOConsoleImpl();
    
    public FloorMasterView(){
        
    }
    
    public FloorMasterView(UserIO io){
        this.io = io;
    }
    
    public int printMenuAndGetSelection(){
        io.print("=== Main Menu ===");
        io.print("1. Display Orders");
        io.print("2. Find One Order");
        io.print("3. Add an Order");
        io.print("4. Edit an Order");
        io.print("5. Remove an Order");
        io.print("6. Save Current Work");
        io.print("7. Quit");
        return io.readInt("Please select an option.", 1, 7);
    }
    
    public Integer getOrderNumChoice() {
        Integer i = io.readInt("Please enter your desired order number.");
        return i;
    }
    
    public String getOrderDateChoice() {
        String s = io.readString("Please enter the date of the order you wish to view. [00000000]");
        return s;
    }
    
    public int editMenuGetChoice() throws InvalidInputException {
            io.print("=== Edit Order ===");
            io.print("1. Client Name");
            io.print("2. State");
            io.print("3. Product Type");
            io.print("4. Area");

            return io.readInt("What would you like to edit?", 1, 4);
    }
    
    public void editOrderClientName(Order order) {
        order.setClientName(io.readString("Please enter the new individual client name or company name."));
    }
    
    public void editOrderState(Order order) {
        order.setState(io.readString("Please enter the new two-letter abbreviation for the state where the worksite will be."));
    }
    
    public void editOrderProductType(Order order) {
        order.setProductType(io.readString("Please enter the new type of flooring you require. (Carpet, Laminate, Tile, Wood)"));
    }
    
    public void editOrderArea(Order order) {
        String areaAsText = io.readString("Please enter the area of the floor required to the nearest hundredth of an inch (00.00)");
        BigDecimal area = new BigDecimal(areaAsText);
        order.setArea(area);
    }
    
    public void displayEditSuccess() {
        io.readString("Order successfully edited. Please hit enter to continue.");
    }
    
    public Order getOrderNeeds() throws InvalidInputException {
        String delivDate = io.readString("Please enter your needed delivery date in format (MMDDYYYY)");
        String clientName = io.readString("Please enter the individual client name or company name.");
        String stateAbb = io.readString("Please enter the two-letter abbreviation for the state where the worksite will be.");
        String productType = io.readString("Please enter the type of flooring you require. (Carpet, Laminate, Tile, Wood)");
        String areaAsText = io.readString("Please enter the area of the floor required to the nearest hundredth of an inch (00.00)");
        BigDecimal area = new BigDecimal(areaAsText);
        Order newOrder = new Order();
        newOrder.setDelivDate(delivDate);
        newOrder.setClientName(clientName);
        newOrder.setState(stateAbb);
        newOrder.setProductType(productType);
        newOrder.setArea(area);
        return newOrder;
    }
    
    public void displayAllOrders(List<Order> orderList){
        for(Order currentOrder : orderList){
            io.print("Delivery Date: " + currentOrder.getDelivDate());
            io.print("Order #" + currentOrder.getOrderNum());
            io.print("Client name: " + currentOrder.getClientName());
            io.print("State : " + currentOrder.getState());
            io.print("Product Type: " + currentOrder.getProductType());
            io.print("Area (SqFt): " + currentOrder.getArea());
            io.print("Material Cost/(SqFt): $" + currentOrder.getCostPerSqFt());
            io.print("Labor Cost/(SqFt): $" + currentOrder.getLaborCostPerSqFt());
            io.print("State Tax Rate: " + currentOrder.getTaxRate() + "%");
            io.print("Total Material Cost: $" + currentOrder.getMaterialCost());
            io.print("Total Labor Cost: $" + currentOrder.getLaborCost());
            io.print("Total Tax Cost: $" + currentOrder.getTax());
            io.print("FINAL TOTAL: $" + currentOrder.getTotal());
            io.print("------------------------");
        }
    }
    
    public void displayOneOrder(Order order) {
        io.print("Delivery Date: " + order.getDelivDate());
        io.print("Order #" + order.getOrderNum());
        io.print("Client name: " + order.getClientName());
        io.print("State : " + order.getState());
        io.print("Product Type: " + order.getProductType());
        io.print("Area (SqFt): " + order.getArea());
        io.print("Material Cost/(SqFt): $" + order.getCostPerSqFt());
        io.print("Labor Cost/(SqFt): $" + order.getLaborCostPerSqFt());
        io.print("State Tax Rate: " + order.getTaxRate() + "%");
        io.print("Total Material Cost: $" + order.getMaterialCost());
        io.print("Total Labor Cost: $" + order.getLaborCost());
        io.print("Total Tax Cost: $" + order.getTax());
        io.print("FINAL TOTAL: $" + order.getTotal());
        io.print("------------------------");
    }
    
    public boolean commitOrderChanges(){
        boolean commit = false;
        String yesNo = io.readString("Do you wish to save this order? (y/n)");
        if(yesNo.equalsIgnoreCase("y")){
            commit = true;
        }
        return commit;
    }
    
    public void displayAddOrderBanner() {
        io.print("=== Add Order ===");
    }
    
    public void displayAddOrderSuccess() {
        io.readString("Order successfully created. Please hit enter to continue.");
    }
    
    public String getOrderChoice() {
        return io.readString("Please enter an order number.");
    }
    
    public void displayRemoveOrderBanner() {
        io.print("=== Remove Order ===");
    }
    
    public void displayRemoveOrderSuccess() {
        io.readString("Order successfully removed. Please hit enter to continue.");
    }
    
    public void displayShowAllBanner() {
        io.print("=== All Orders ===");
    }
    
    public void displaySaveSuccess() {
        io.readString("Data successfully saved. Please hit enter to continue.");
    }
    
    public boolean displayChooseSave() throws InvalidInputException{
        Boolean validChoice = false;
        Boolean saveChoice = null;
        while(!validChoice){
            String yesNo = io.readString("Would you like to save changes? (y/n)");
            if (yesNo.equalsIgnoreCase("y")){
                saveChoice = true;
                validChoice = true;
            } else if (yesNo.equalsIgnoreCase("n")){
                saveChoice = false;
                validChoice = true;
            } else {
                io.print("Please enter a valid choice.");
            }
        }
        return saveChoice;
    }
    
    public void displayExitMessage() {
        io.print("=== GOOD BYE ===");
    }
    
    public void displayCannotSaveInTraining(){
        io.readString("Could not save data file while program is in training mode. Please hit enter to continue.");
    }
    
    public void displayGenericContinue(){
        io.readString("Please hit enter to continue.");
    }
    
    public void displayErrorMessage(String msg) {
        io.print("!! OH NO !!");
        io.print(msg);
    }
}
