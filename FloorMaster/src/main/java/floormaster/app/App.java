/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package floormaster.app;

import floormaster.control.Control;
import floormaster.dao.FilePersistenceException;
import floormaster.dao.OrderDaoException;
import floormaster.dao.ProductDaoException;
import floormaster.dao.TaxDaoException;
import floormaster.exceptions.InvalidInputException;
import java.io.FileNotFoundException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author Asus1
 */
public class App {
    public static void main(String[] args) throws FileNotFoundException, ProductDaoException, TaxDaoException, OrderDaoException, FilePersistenceException, InvalidInputException {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("FloorApplicationContext.xml");
        Control control = ctx.getBean("controller", Control.class);
        control.run();
    } 
}
