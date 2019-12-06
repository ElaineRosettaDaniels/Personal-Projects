/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package floormaster.control;

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
import floormaster.exceptions.InvalidInputException;
import floormaster.service.ServiceLayerImpl;
import floormaster.ui.FloorMasterView;
import floormaster.ui.UserIO;
import floormaster.ui.UserIOConsoleImpl;
import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.util.List;
import floormaster.service.ServiceLayer;

/**
 *
 * @author Asus1
 */
public class Control {
    
    private UserIO io = new UserIOConsoleImpl();
    FloorMasterView view = new FloorMasterView();
    OrderDao daoO = new OrderDaoFileImpl();
    TaxDao daoT = new TaxDaoFileImpl();
    ProductDao daoP = new ProductDaoFileImpl();
    ServiceLayer serv = new ServiceLayerImpl(daoO, daoT, daoP); 
    
    public Control(){}
    
    public Control(FloorMasterView view, ServiceLayer serv){
        this.view = view;
        this.serv = serv;
    }
    
    private void loadEverything() throws FileNotFoundException, ProductDaoException, TaxDaoException, OrderDaoException {
        serv.loadOrders();
        serv.loadTaxes();
        serv.loadProducts();
    }
    
    private void addNewOrder() throws FilePersistenceException, InvalidInputException, OrderDaoException {
        try {
            view.displayAddOrderBanner();
            Order newOrder = view.getOrderNeeds();
            serv.giveOrderDetails(newOrder);
            view.displayOneOrder(newOrder);
            Boolean saveChoice = view.displayChooseSave();
            if (saveChoice) {
                newOrder.setOrderNum(serv.newOrderNumber());
                serv.addOrder(newOrder);
                view.displayAddOrderSuccess();
            }
        } catch (OrderDaoException e) {
            throw new OrderDaoException("Could not add new order.", e);
        }
    }
    
    private void showAllOrders() {
        view.displayShowAllBanner();
        List<Order> orderList = serv.getAllOrders();
        view.displayAllOrders(orderList);
    }
    
    private void showOneOrder(){
        view.displayOneOrder(
            serv.getOneOrder(
                view.getOrderDateChoice(), 
                view.getOrderNumChoice()));
        view.displayGenericContinue();
    }
    
    private Order editOrder(Integer orderNum) throws FilePersistenceException, InvalidInputException, OrderDaoException {
        try {
            Order returnedOrder;
            Order orderBeforeEdit = serv.getOrder(orderNum);
            Order editOrder = serv.getOrder(orderNum);
            int editChoice = view.editMenuGetChoice();

            switch(editChoice) {
                case 1:
                    view.editOrderClientName(editOrder);
                    break;
                case 2:
                    view.editOrderState(editOrder);
                    break;
                case 3: 
                    view.editOrderProductType(editOrder);
                    break;
                case 4:
                    view.editOrderArea(editOrder);
                    break;
            }
            serv.giveOrderDetails(editOrder);
            view.displayOneOrder(editOrder);
            boolean commit = view.commitOrderChanges();
            if(commit == true){
                returnedOrder = editOrder;
                view.displayEditSuccess();
            } else {
                returnedOrder = orderBeforeEdit;
            }
            return returnedOrder;
        } catch (InvalidInputException e) {
            throw new InvalidInputException("Invalid input. Please try again.", e);
        } catch (OrderDaoException o) {
            throw new OrderDaoException("Could not edit order. Please try again.", o);
        }
    }
    
    private void deleteOrder() {
        view.displayRemoveOrderBanner();
        Order order =
        serv.getOneOrder(
                view.getOrderDateChoice(), 
                view.getOrderNumChoice());
        serv.removeOrder(order);
        view.displayRemoveOrderSuccess();
    }
    
    private void finalSave() throws OrderDaoException, InvalidInputException{
        boolean inTraining = serv.checkTrainingMode();
        boolean saveChoice = view.displayChooseSave();
        
        if (saveChoice) {
            if(!inTraining){
                serv.saveOrders();
                view.displaySaveSuccess();
            } else {
                view.displayCannotSaveInTraining();
            }
        }
    }
    
    public void run() throws FileNotFoundException, ProductDaoException, TaxDaoException, OrderDaoException, FilePersistenceException, InvalidInputException {
        try {
            boolean keepGoing = true;
            int menuSelection = 0;
            loadEverything();
            while (keepGoing) {

                menuSelection = getMenuSelection(); 

                switch (menuSelection) {
                    case 1:
                       showAllOrders();
                       view.displayGenericContinue();
                       break;
                    case 2:
                        showOneOrder();
                        break;
                    case 3:
                        addNewOrder();
                        break;
                    case 4:
                        editOrder(view.getOrderNumChoice());
                        break;
                    case 5:
                        deleteOrder();
                        break;
                    case 6:
                        finalSave();
                        break;
                    case 7:
                        keepGoing = false;
                        break;
                    default:
                        io.print("UNKNOWN COMMAND");
                        break;
                }
            }
            finalSave();
            serv.deleteEmptyFiles();
            view.displayExitMessage();
        } catch (InvalidInputException e) {
            throw new InvalidInputException("Invalid input. Please try again.", e);
        } catch (FileNotFoundException n) {
            throw new FileNotFoundException("File could not be found. Please try again.");
        }
    }
    
    private int getMenuSelection() {
        return view.printMenuAndGetSelection();
    }
    
}
