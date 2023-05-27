package com.javafx.lab_6;

import com.javafx.lab_6.data.Product;
import com.javafx.lab_6.data.Repository;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.time.LocalDate;

public class AddProductController {
    private Repository _repository;
    private MainController _mainController;

    public void set_mainController(MainController
                                           _mainController) {
        this._mainController = _mainController;
    }

    public void set_repository(Repository _repository) {
        this._repository = _repository;
    }

    @FXML
    TextField nameProduct;
    @FXML
    TextField category;
    @FXML
    TextField description;
    @FXML
    TextField price;
    @FXML
    RadioButton isOnStorage;
    @FXML
    TextField amount;
    @FXML
    DatePicker deliveryDate;

    public void addProductToFile(ActionEvent actionEvent) {
        String nameProduct_ = nameProduct.getText();
        String category_ = category.getText();
        String description_ = description.getText();
        String price_ = price.getText();
        String isOnStorage_ = isOnStorage.getText();
        String amount_ = amount.getText();
        String deliveryDate_ = String.valueOf(deliveryDate.getValue());

        Product newProduct = new Product(nameProduct_,category_,description_,
                Double.parseDouble(price_), Boolean.parseBoolean(isOnStorage_),
                Integer.parseInt(amount_), LocalDate.parse(deliveryDate_));
        _repository.addProduct(newProduct);
        final Node source = (Node) actionEvent.getSource();
        final Stage stage =
                (Stage) source.getScene().getWindow();
        _mainController.updateListsView();
        stage.close();
    }
}
