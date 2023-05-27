module com.javafx.lab_ {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.javafx.lab_6 to javafx.fxml;
    exports com.javafx.lab_6;
    exports com.javafx.lab_6.data;
}