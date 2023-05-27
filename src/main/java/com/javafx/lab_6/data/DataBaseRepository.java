package com.javafx.lab_6.data;

import java.sql.*;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

public class DataBaseRepository implements Repository {

    private final DataBaseConnector dataBaseConnector;

    public DataBaseRepository(DataBaseConnector dataBaseConnector) {
        this.dataBaseConnector = dataBaseConnector;
        try (Connection conn = dataBaseConnector.getConnection()) {
            String tableCreateStr =
                    "CREATE TABLE IF NOT EXISTS Products\n" +
                            "(id INT NOT NULL AUTO_INCREMENT, " +
                            "NameProduct VARCHAR(50), Category VARCHAR(50)," +
                            "Description VARCHAR(50), Price Double," +
                            "IsOnStorage SMALLINT, Amount INT, DeliveryDate Date, " +
                            "PRIMARY KEY (id));";
            Statement createTable = conn.createStatement();
            createTable.execute(tableCreateStr);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Product> getAll() {
        List<Product> products = new ArrayList<>();
        try (Connection connection = dataBaseConnector.getConnection()) {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("select * from Products");
            while (rs.next()) {
                products.add(new Product(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getDouble(5),
                        rs.getBoolean(6),
                        rs.getInt(7),
                        rs.getDate(8).toLocalDate()));
            }
            rs.close();
        } catch (SQLException exception) {
            System.out.println("Не відбулося підключення до БД");
            exception.printStackTrace();
        }
        return products;
    }

    @Override
    public Product getById(int id) {
        Product product = null;
        try (Connection connection = dataBaseConnector.getConnection()) {
            PreparedStatement statement =
                    connection.prepareStatement("select * from Products where id = ?");
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                product = new Product(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getDouble(5),
                        rs.getBoolean(6),
                        rs.getInt(7),
                        rs.getDate(8).toLocalDate());
            }
            rs.close();
        } catch (SQLException exception) {
            exception.printStackTrace();
        } finally {
            return product;
        }

    }

    @Override
    public List<Product> getAllByCategory(String category) {
        List<Product> products = new ArrayList<>();
        try (Connection connection = dataBaseConnector.getConnection()) {
            PreparedStatement statement =
                    connection.prepareStatement(
                            "select * from Products where Category Like(?)"
                    );
            statement.setString(1, "%" + category + "%");
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                products.add(new Product(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getDouble(5),
                        rs.getBoolean(6),
                        rs.getInt(7),
                        rs.getDate(8).toLocalDate()));
            }
            rs.close();
        } catch (SQLException exception) {
            System.out.println("Не відбулося підключення до БД");
            exception.printStackTrace();
        }
        return products;
    }

    @Override
    public List<Product> getProductsIsNotTheStorage() {
        List<Product> products = new ArrayList<>();
        try (Connection connection = dataBaseConnector.getConnection()) {
            PreparedStatement statement =
                    connection.prepareStatement("select * from Products where IsOnStorage = ?");
            statement.setBoolean(1, false);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                products.add(new Product(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getDouble(5),
                        rs.getBoolean(6),
                        rs.getInt(7),
                        rs.getDate(8).toLocalDate()));
            }
            rs.close();
        } catch (SQLException exception) {
            System.out.println("Не відбулося підключення до БД");
            exception.printStackTrace();
        }
        return products;
    }

    @Override
    public boolean addProduct(Product product) {
        int updCount = 0;
        try (Connection conn = dataBaseConnector.getConnection()) {
            PreparedStatement preparedStatement =
                    conn.prepareStatement("INSERT INTO Products (NameProduct, Category," +
                            "Description, Price, IsOnStorage, Amount, DeliveryDate) VALUES (?,?,?,?,?,?,?)");
            preparedStatement.setString(1, product.getNameProduct());
            preparedStatement.setString(2, product.getCategory());
            preparedStatement.setString(3, product.getDescription());
            preparedStatement.setDouble(4, product.getPrice());
            preparedStatement.setBoolean(5, product.isOnStorage());
            preparedStatement.setInt(6, product.getAmount());
            preparedStatement.setDate(7, new java.sql.Date(Date.from(product.getDeliveryDate().atStartOfDay(ZoneId.systemDefault()).toInstant()).getTime()));
            updCount = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return updCount > 0;
    }

    @Override
    public boolean updateProduct(int id, Product product) {
        int updCount = 0;
        try (Connection conn = dataBaseConnector.getConnection()) {
            PreparedStatement preparedStatement =
                    conn.prepareStatement("UPDATE Products " +
                            "SET NameProduct = ?, Category = ?," +
                            "Description = ?, Price = ?, IsOnStorage = ?, Amount = ?, DeliveryDate = ?" +
                            "WHERE id = ?");
            preparedStatement.setString(1, product.getNameProduct());
            preparedStatement.setString(2, product.getCategory());
            preparedStatement.setString(3, product.getDescription());
            preparedStatement.setDouble(4, product.getPrice());
            preparedStatement.setBoolean(5, product.isOnStorage());
            preparedStatement.setInt(6, product.getAmount());
            preparedStatement.setDate(7, new java.sql.Date(Date.from(product.getDeliveryDate().atStartOfDay(ZoneId.systemDefault()).toInstant()).getTime()));
            preparedStatement.setInt(8, id);
            updCount = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return updCount > 0;
    }

    @Override
    public boolean deleteProduct(int id) {
        int updCount = 0;
        try (Connection conn = dataBaseConnector.getConnection()) {
            PreparedStatement preparedStatement =
                    conn.prepareStatement("DELETE FROM Products WHERE id = ?");
            preparedStatement.setInt(1, id);
            updCount = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return updCount > 0;
    }
}