module com.example.gameextention {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.gameextention to javafx.fxml;
    exports com.example.gameextention;
}