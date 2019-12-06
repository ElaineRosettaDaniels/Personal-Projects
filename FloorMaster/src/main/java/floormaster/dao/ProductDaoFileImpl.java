/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package floormaster.dao;

import floormaster.dto.Product;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;


/**
 *
 * @author Asus1
 */
public class ProductDaoFileImpl implements ProductDao {

    public static final String PRODUCT_FILE = "Products.txt";
    public static final String DELIMITER = ",";
    private Map<String, Product> products = new TreeMap<>();
    
    public ProductDaoFileImpl() {}
    
    @Override
    public Product addProduct(String type, Product p) {
        Product addedProduct = products.put(type, p);
        return addedProduct;
    }
    
    private Product unmarshallProduct(String productAsText) {
        String[] productTokens = productAsText.split(DELIMITER);
        String productType = productTokens[0];
        Product productFromFile = new Product();
        productFromFile.setProductType(productType);
        productFromFile.setCostPerSqFt(new BigDecimal(productTokens[1]));
        productFromFile.setLaborCostPerSqFt(new BigDecimal(productTokens[2]));
        return productFromFile;
    }
    
    @Override
    public void loadProducts() throws ProductDaoException {
        Scanner scanner;
        
        try{
            scanner = new Scanner(new BufferedReader(new FileReader(PRODUCT_FILE)));
        } catch (FileNotFoundException e) {
            throw new ProductDaoException("Could not load the product file.", e);
        }
        String currentLine;
        Product currentProduct;
        String headerLine = scanner.nextLine(); // DO THIS TO SKIP HEADER LINE AND PREVENT CRASH
        while(scanner.hasNextLine()) {
            currentLine = scanner.nextLine();
            currentProduct = unmarshallProduct(currentLine);
            products.put(currentProduct.getProductType(), currentProduct);
        }
        scanner.close();
    }

    @Override
    public Product getOneProduct(String pType) {
        return products.get(pType);
    }

    @Override
    public List<Product> getAllProducts() {
        return new ArrayList<Product>(products.values());
    }

    
  
}
