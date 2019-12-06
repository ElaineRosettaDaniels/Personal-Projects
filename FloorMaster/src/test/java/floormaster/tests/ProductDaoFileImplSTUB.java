/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package floormaster.tests;

import floormaster.dao.ProductDao;
import floormaster.dao.ProductDaoException;
import floormaster.dto.Product;
import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Asus1
 */
public class ProductDaoFileImplSTUB implements ProductDao {
    
    private Product onlyProduct;
    private List<Product> productList = new ArrayList<>();
    
    public ProductDaoFileImplSTUB() {
        onlyProduct = new Product();
        onlyProduct.setProductType("Wood");
        onlyProduct.setCostPerSqFt(new BigDecimal("5.15").setScale(2, RoundingMode.HALF_UP));
        onlyProduct.setLaborCostPerSqFt(new BigDecimal("4.75").setScale(2, RoundingMode.HALF_UP));
        productList.add(onlyProduct);
    }

    @Override
    public Product getOneProduct(String pType) {
        if(pType.equals(onlyProduct.getProductType())) return onlyProduct;
        else return null;
    }

    @Override
    public List<Product> getAllProducts() {
        return productList;
    }

    @Override
    public Product addProduct(String type, Product p) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public void loadProducts() throws FileNotFoundException, ProductDaoException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    

    
    
}
