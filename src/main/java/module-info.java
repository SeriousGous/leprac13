module com.example.leprac13 {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.example.leprac13 to javafx.fxml;
    exports com.example.leprac13;
}