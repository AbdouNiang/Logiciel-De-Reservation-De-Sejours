module com.example.logicieldereservationdesejours {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.opencsv;


    opens com.example.logicieldereservationdesejours to javafx.fxml;
    exports com.example.logicieldereservationdesejours;
    exports com.example.logicieldereservationdesejours.controllers;
    opens com.example.logicieldereservationdesejours.controllers to javafx.fxml;
}