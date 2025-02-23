module com.tapngo.tapngo {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires java.desktop;


    opens com.tapngo to javafx.fxml;
    exports com.tapngo;
    exports com.tapngo.controller;
    opens com.tapngo.controller to javafx.fxml;
    opens com.tapngo.view to javafx.fxml;
    exports com.tapngo.view;
    opens com.tapngo.model.utility to javafx.fxml;
    exports com.tapngo.model.utility;
    opens com.tapngo.model.bean to javafx.fxml;
    exports com.tapngo.model.bean;
    opens com.tapngo.exception to javafx.fxml;
    exports com.tapngo.exception;



}