package com.javafx.lab_6.data;

import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DataBaseConnectorTest {

    @Test
    void addCar() {
        DataBaseRepository repository = new DataBaseRepository(
                new DataBaseConnector("InternetShopDB"));
        repository.addProduct(new Product("NameProduct1", "Category1", "Description1",
                12.67, true, 12, LocalDate.now()));
        repository.addProduct(new Product("NameProduct2", "Category2", "Description2",
                32.00, false, 65, LocalDate.now()));
        repository.addProduct(new Product("NameProduct3", "Category3", "Description3",
                89.01, false, 2, LocalDate.now()));
        assertNotNull(repository.getById(1));
        java.util.List<Product> products = repository.getAll();
        System.out.println(products);
        assertTrue(products.size() > 0);
    }

    @Test
    void getAllByCategory() {
        DataBaseRepository repository = new DataBaseRepository(
                new DataBaseConnector("InternetShopDB"));
        repository.addProduct(new Product("NameProduct4", "Category2", "Description4",
                32.00, false, 65, LocalDate.now()));
        repository.addProduct(new Product("NameProduct5", "Category2", "Description5",
                89.01, true, 2, LocalDate.now()));
        java.util.List<Product> products =
                repository.getAllByCategory("Category2");
        assertNotNull(repository.getById(1));
        assertTrue(products.size() > 1);
    }

    @Test
    void getProductsIsNotTheStorage() {
        DataBaseRepository repository = new DataBaseRepository(
                new DataBaseConnector("test3"));
        java.util.List<Product> products =
                repository.getProductsIsNotTheStorage();
        System.out.println(products);
        assertTrue(products.size() > 0);
    }

    @Test
    void updateProduct() {
        DataBaseRepository repository = new DataBaseRepository(
                new DataBaseConnector("test3"));
        var newProduct = repository.updateProduct(2, new Product("NameProduct5", "Category2", "Description4",
                45.00, false, 65, LocalDate.now()));
        System.out.println(repository.getAll());
        assertTrue(newProduct);
    }

    @Test
    void deleteProduct() {
        DataBaseRepository repository = new DataBaseRepository(
                new DataBaseConnector("test3"));
        assertTrue(repository.deleteProduct(2));
    }
}