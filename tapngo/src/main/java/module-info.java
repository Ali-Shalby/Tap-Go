module com.tapngo.tapngo {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.tapngo.tapngo to javafx.fxml;
    exports com.tapngo.tapngo;
}