module com.example.logicieldereservationdesejours {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.logicieldereservationdesejours to javafx.fxml;
    exports com.example.logicieldereservationdesejours;
}