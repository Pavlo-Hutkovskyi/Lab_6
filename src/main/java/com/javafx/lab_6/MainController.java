package com.javafx.lab_6;

import com.javafx.lab_6.data.Product;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import com.javafx.lab_6.data.DataBaseConnector;
import com.javafx.lab_6.data.DataBaseRepository;
import com.javafx.lab_6.data.Repository;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;


public class MainController implements Initializable {
    @FXML
    ListView listProducts;
    @FXML
    ComboBox categoryCombo;
    private Repository repository;

    @Override
    public void initialize(URL url,
                           ResourceBundle resourceBundle) {
        repository = new DataBaseRepository(
                new DataBaseConnector("InternetShopDB"));
        updateListsView();
    }

    public void updateListsView() {
        List<Product> products = repository.getAll();
        ObservableList<Product> productsList =
                FXCollections.observableList(products);
        listProducts.setItems(productsList);
        List<String> categories = new ArrayList<>(products
                .stream()
                .map(Product::getCategory)
                .distinct()
                .toList());
        categories.add("All");
        ObservableList<String> categoryList =
                FXCollections.observableList(categories);
        categoryCombo.setItems(categoryList);
        categoryCombo.getSelectionModel().select(categories.size() - 1);
    }

    @FXML
    public void addNewProduct(ActionEvent actionEvent) {
        Stage newWindow = new Stage();
        FXMLLoader loader =
                new FXMLLoader(InternetShop.class.getResource(
                        "add-product-form.fxml"
                ));
        Parent root = null;
        try {
            root = loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        newWindow.setTitle("Зареєструвати продукт");
        newWindow.setScene(new Scene(root, 550, 420));
        AddProductController secondController =
                loader.getController();
        secondController.set_repository(repository);
        secondController.set_mainController(this);
        newWindow.show();
    }

    public void filterByCategory(ActionEvent actionEvent) {
        String category =
                (String) categoryCombo.getSelectionModel().getSelectedItem();
        List<Product> products = null;
        if ("All".equals(category)) {
            products = repository.getAll();
        } else {
            products = repository.getAllByCategory(category);
        }
        ObservableList<Product> productsList =
                FXCollections.observableList(products);
        listProducts.setItems(productsList);
    }

    @FXML
    public void deleteProduct(ActionEvent actionEvent) {
        Product toDelete =
                (Product) listProducts.getSelectionModel().getSelectedItem();
        System.out.println(toDelete);
        repository.deleteProduct(toDelete.getId());
        updateListsView();
    }

    @FXML
    public void editProduct(ActionEvent actionEvent) {
        Product selectedProduct =
                (Product) listProducts.getSelectionModel().getSelectedItem();
        Stage newWindow = new Stage();
        FXMLLoader loader =
                new FXMLLoader(InternetShop.class.getResource(
                        "edit-product-form.fxml"
                ));
        Parent root = null;
        try {
            root = loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        newWindow.setTitle("Редагувати продукт");
        newWindow.setScene(new Scene(root, 550, 420));
        EditProductController editController =
                loader.getController();
        editController.setRepository(repository);
        editController.setMainController(this);
        editController.setProduct(selectedProduct); // Set the selected product for editing
        newWindow.show();
    }

    public void getProductsIsNotTheStorage() {
        List<Product> products = repository.getProductsIsNotTheStorage();
        ObservableList<Product> productsList =
                FXCollections.observableList(products);
        listProducts.setItems(productsList);
    }
}