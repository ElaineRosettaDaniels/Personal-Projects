/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package floormaster.dao;

import floormaster.dto.Tax;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Asus1
 */
public class TaxDaoFileImpl implements TaxDao {

    private static final String TAX_FILE = "Taxes.txt";
    private static final String DELIMITER = ",";
    private Map<String, Tax> taxes = new TreeMap<>();
    
    @Override
    public Tax addTax(String stateAbb, Tax tax) {
        Tax addedTax = taxes.put(stateAbb, tax);
        return addedTax;
    }
    
    private Tax unmarshallTax(String taxAsText) {
        String[] taxTokens = taxAsText.split(DELIMITER);
        String stateAbb = taxTokens[0];
        Tax taxFromFile = new Tax();
        taxFromFile.setStateAbb(stateAbb);
        taxFromFile.setStateName(taxTokens[1]);
        taxFromFile.setTaxRate(new BigDecimal(taxTokens[2]));
        return taxFromFile;
    }
    
    @Override
    public void loadTaxes() throws TaxDaoException {
        Scanner scanner;
        
        try{
            scanner = new Scanner(new BufferedReader(new FileReader(TAX_FILE)));
        } catch (FileNotFoundException e) {
            throw new TaxDaoException("Could not load the tax file.", e);
        }
        String currentLine;
        Tax currentTax;
        String headerLine = scanner.nextLine(); // DO THIS TO SKIP HEADER LINE AND PREVENT CRASH
        while(scanner.hasNextLine()) {
            currentLine = scanner.nextLine();
            currentTax = unmarshallTax(currentLine);
            taxes.put(currentTax.getStateAbb(), currentTax);
        }
        scanner.close();
    }
    
    @Override
    public Tax getOneTax(String stateAbb) {
        return taxes.get(stateAbb);
    }

    @Override
    public List<Tax> getAllTaxes() {
        return new ArrayList<Tax>(taxes.values());
    }

    

}
