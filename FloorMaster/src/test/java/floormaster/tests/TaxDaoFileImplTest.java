/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package floormaster.tests;

import floormaster.dao.TaxDao;
import floormaster.dao.TaxDaoFileImpl;
import floormaster.dto.Tax;
import java.math.BigDecimal;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 *
 * @author Asus1
 */
public class TaxDaoFileImplTest {
    
    TaxDao testDao;
    public TaxDaoFileImplTest() {
        testDao = new TaxDaoFileImpl();
    }
    
    @Test
    public void getTaxTest() {
        // ARRANGE
        Tax testTax = new Tax();
        testTax.setStateAbb("MA");
        testTax.setStateName("Massacheche");
        testTax.setTaxRate(new BigDecimal("5.00"));
        // ACT
        testDao.addTax(testTax.getStateAbb(), testTax);
        Tax addedTax = testDao.getOneTax(testTax.getStateAbb());
        // ASSERT
        Assertions.assertEquals(testTax.getStateAbb(), addedTax.getStateAbb(), "stateAbb should match for both.");
        Assertions.assertEquals(testTax.getStateName(), addedTax.getStateName(), "stateName should match for both.");
        Assertions.assertEquals(testTax.getTaxRate(), addedTax.getTaxRate(), "taxRate should match for both.");
    }
    
    @Test
    public void getAllTaxesTest() {
        Tax testTax1 = new Tax();
        testTax1.setStateAbb("MA");
        testTax1.setStateName("Massacheche");
        testTax1.setTaxRate(new BigDecimal("5.00"));
        Tax testTax2 = new Tax();
        testTax2.setStateAbb("FO");
        testTax2.setStateName("Flooon");
        testTax2.setTaxRate(new BigDecimal("50.00"));
        
        testDao.addTax(testTax1.getStateAbb(), testTax1);
        testDao.addTax(testTax2.getStateAbb(), testTax2);
        
        List<Tax> allTaxes = testDao.getAllTaxes();
        
        Assertions.assertNotNull(allTaxes, "our tax list should not be null");
        Assertions.assertEquals(2, allTaxes.size(), "there should be 2 items in the list");
        Assertions.assertTrue(allTaxes.contains(testTax1),
                "Order list should have testTax1.");
        Assertions.assertTrue(allTaxes.contains(testTax2),
                "Order list should have testTax2.");
    }
    
}
