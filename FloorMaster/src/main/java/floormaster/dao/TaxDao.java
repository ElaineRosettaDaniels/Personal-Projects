/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package floormaster.dao;

import floormaster.dto.Tax;
import java.io.FileNotFoundException;
import java.util.List;

/**
 *
 * @author Asus1
 */
public interface TaxDao {
    public Tax addTax(String stateAbb, Tax tax);
    public Tax getOneTax(String stateAbb);
    public List<Tax> getAllTaxes();
    public void loadTaxes() throws FileNotFoundException, TaxDaoException;
    
}
