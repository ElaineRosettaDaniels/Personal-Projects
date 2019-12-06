/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package floormaster.tests;

import floormaster.dao.ProductDao;
import floormaster.dao.ProductDaoFileImpl;
import floormaster.dto.Product;
import java.math.BigDecimal;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 *
 * @author Asus1
 */
public class ProductDaoFileImplTest {
    
    ProductDao testDao;
    public ProductDaoFileImplTest() {
        testDao = new ProductDaoFileImpl();
    }
    
    @Test
    public void getOneProductTest() {
        // ARRANGE
        Product testProduct = new Product();
        testProduct.setProductType("Cactus");
        testProduct.setCostPerSqFt(new BigDecimal("1.50"));
        testProduct.setLaborCostPerSqFt(new BigDecimal("2.00"));
        // ACT
        testDao.addProduct(testProduct.getProductType(), testProduct);
        Product gottenProduct = testDao.getOneProduct(testProduct.getProductType());
        // ASSERT
        Assertions.assertEquals(testProduct.getProductType(), gottenProduct.getProductType(), "productType should match for both.");
        Assertions.assertEquals(testProduct.getCostPerSqFt(), gottenProduct.getCostPerSqFt(), "costPerSqFt should match for both.");
        Assertions.assertEquals(testProduct.getLaborCostPerSqFt(), gottenProduct.getLaborCostPerSqFt(), "laborCostPerSqFt should match for both.");
    }
    
    @Test
    public void getAllProductsTest() {
        // ARRANGE
        Product testProduct1 = new Product();
        testProduct1.setProductType("Cactus");
        testProduct1.setCostPerSqFt(new BigDecimal("1.50"));
        testProduct1.setLaborCostPerSqFt(new BigDecimal("2.00"));
        Product testProduct2 = new Product();
        testProduct2.setProductType("Water");
        testProduct2.setCostPerSqFt(new BigDecimal("0.10"));
        testProduct2.setLaborCostPerSqFt(new BigDecimal("0.01"));
        // ACT
        testDao.addProduct(testProduct1.getProductType(), testProduct1);
        testDao.addProduct(testProduct2.getProductType(), testProduct2);
        
        List<Product> allProducts = testDao.getAllProducts();
        // ASSERT
        Assertions.assertNotNull(allProducts, "our order list should not be null");
        Assertions.assertEquals(2, allProducts.size(), "there should be 2 items in the list");
        Assertions.assertTrue(allProducts.contains(testProduct1),
                "Product list should have testProduct1.");
        Assertions.assertTrue(allProducts.contains(testProduct2),
                "Product list should have testProduct2.");
    }
}
