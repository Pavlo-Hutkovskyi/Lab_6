package com.javafx.lab_6.data;

import java.lang.reflect.InvocationTargetException;
import java.sql.*;

public class DataBaseConnector {
    private String dataBaseUrl;
    private String dataBaseUser;
    private String dataBasePassword;
    private String driverClass;

    public DataBaseConnector(String dataBaseName) {
        this.dataBaseUrl = "jdbc:h2:file:./" + dataBaseName;
        this.dataBaseUser = "admin";
        this.dataBasePassword = "";
        this.driverClass = "org.h2.Driver";
    }
    public boolean testDriver(){
        try{
            Class.forName(driverClass)
                    .getDeclaredConstructor().newInstance();
            return true;
        }catch (ClassNotFoundException | IllegalAccessException | InstantiationException | NoSuchMethodException |
                InvocationTargetException e) {
            e.printStackTrace();
            return false;
        }
    }
    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(dataBaseUrl,
                dataBaseUser,dataBasePassword);
    }
}
