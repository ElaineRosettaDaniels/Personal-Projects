/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package floormaster.dao;

import floormaster.dto.Product;
import java.io.FileNotFoundException;
import java.util.List;

/**
 *
 * @author Asus1
 */
public interface ProductDao {
    public Product addProduct(String type, Product p);
    public void loadProducts() throws FileNotFoundException, ProductDaoException;
    public Product getOneProduct(String pType);
    public List<Product> getAllProducts();

}
