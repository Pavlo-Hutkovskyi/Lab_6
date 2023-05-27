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

public class EditProductController {
    private Repository _repository;
    private MainController _mainController;
    private Product _product;

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

    public void setMainController(MainController mainController) {
        this._mainController = mainController;
    }

    public void setRepository(Repository repository) {
        this._repository = repository;
    }

    public void setProduct(Product product) {
        this._product = product;
        fillFieldsWithData();
    }

    public void updateProduct(ActionEvent actionEvent) {
        String nameProduct_ = nameProduct.getText();
        String category_ = category.getText();
        String description_ = description.getText();
        String price_ = price.getText();
        String isOnStorage_ = String.valueOf(isOnStorage.isSelected());
        String amount_ = amount.getText();
        String deliveryDate_ = String.valueOf(deliveryDate.getValue());

        _product.setNameProduct(nameProduct_);
        _product.setCategory(category_);
        _product.setDescription(description_);
        _product.setPrice(Double.parseDouble(price_));
        _product.setOnStorage(Boolean.parseBoolean(isOnStorage_));
        _product.setAmount(Integer.parseInt(amount_));
        _product.setDeliveryDate(LocalDate.parse(deliveryDate_));

        _repository.updateProduct(_product.getId(), _product);
        final Node source = (Node) actionEvent.getSource();
        final Stage stage = (Stage) source.getScene().getWindow();
        _mainController.updateListsView();
        stage.close();
    }

    private void fillFieldsWithData() {
        nameProduct.setText(_product.getNameProduct());
        category.setText(_product.getCategory());
        description.setText(_product.getDescription());
        price.setText(String.valueOf(_product.getPrice()));
        isOnStorage.setSelected(_product.isOnStorage());
        amount.setText(String.valueOf(_product.getAmount()));
        deliveryDate.setValue(_product.getDeliveryDate());
    }
}

