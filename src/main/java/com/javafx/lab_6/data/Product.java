package com.javafx.lab_6.data;

import java.io.Serializable;
import java.time.LocalDate;

public class Product implements Serializable {

    private int id;
    private String nameProduct;
    private String category;
    private String description;
    private double price;
    private boolean isOnStorage;
    private int amount;
    private LocalDate deliveryDate;

    public Product() {
    }

    public Product(String nameProduct, String category, String description, double price, boolean isOnStorage, int amount) {
        this(nameProduct, category, description);
        this.price = price;
        this.isOnStorage = isOnStorage;
        this.amount = amount;
        this.deliveryDate = LocalDate.now();
    }

    public Product(int id, String nameProduct, String category, String description, double price, boolean isOnStorage, int amount, LocalDate deliveryDate) {
        this.id = id;
        this.nameProduct = nameProduct;
        this.category = category;
        this.description = description;
        this.price = price;
        this.isOnStorage = isOnStorage;
        this.amount = amount;
        this.deliveryDate = deliveryDate;
    }

    public Product(String nameProduct, String category, String description, double price, boolean isOnStorage, int amount, LocalDate date) {
        this(nameProduct, category, description);
        this.price = price;
        this.isOnStorage = isOnStorage;
        this.amount = amount;
        this.deliveryDate = date;
    }

    public Product(String nameProduct, String category, String description) {
        this.nameProduct = nameProduct;
        this.category = category;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNameProduct() {
        return nameProduct;
    }

    public void setNameProduct(String nameProduct) {
        this.nameProduct = nameProduct;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public boolean isOnStorage() {
        return isOnStorage;
    }

    public void setOnStorage(boolean onStorage) {
        isOnStorage = onStorage;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public LocalDate getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(LocalDate deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    @Override
    public String toString() {
        return String.format("\nId: %d\nName: %s\nCategory: %s\nDescription: %s\nPrice: %.2f\nIs on storage: %b\nAmount: %d\nDelivery date: %s\n",
                id, nameProduct, category, description, price, isOnStorage, amount, deliveryDate);
    }
}
