package com.javafx.lab_6.data;

import java.util.List;

public interface Repository {
    List<Product> getAll();
    Product getById(int id);
    List<Product> getAllByCategory(String category);
    List<Product> getProductsIsNotTheStorage();
    boolean addProduct(Product product);
    boolean updateProduct(int id, Product product);
    boolean deleteProduct(int id);
}
