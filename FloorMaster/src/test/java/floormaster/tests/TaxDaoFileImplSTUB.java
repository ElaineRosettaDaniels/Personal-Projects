/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package floormaster.tests;

import floormaster.dao.TaxDao;
import floormaster.dao.TaxDaoException;
import floormaster.dto.Tax;
import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Assertions;

/**
 *
 * @author Asus1
 */
public class TaxDaoFileImplSTUB implements TaxDao {
    
    private Tax onlyTax;
    private List<Tax> taxList = new ArrayList<>();
    
    public TaxDaoFileImplSTUB() {
        onlyTax = new Tax();
        onlyTax.setStateAbb("KY");
        onlyTax.setStateName("Kentucky");
        onlyTax.setTaxRate(new BigDecimal("6.00"));
        taxList.add(onlyTax);
    }

    @Override
    public Tax getOneTax(String stateAbb) {
        if(stateAbb.equals(onlyTax.getStateAbb())) return onlyTax;
        else return null;
    }

    @Override
    public List<Tax> getAllTaxes() {
        return taxList;
    }

    @Override
    public void loadTaxes() throws FileNotFoundException, TaxDaoException {
        // NOT NEEDED FOR TESTS
    }

    @Override
    public Tax addTax(String stateAbb, Tax tax) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    

}
