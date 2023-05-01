
package com.example.logicieldereservationdesejours.controllers;

import com.example.logicieldereservationdesejours.Main;
import com.example.logicieldereservationdesejours.controllers.SearchController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class AccueilController {

    @FXML
    private AnchorPane buttonMenu;

    @FXML
    private Button connexion;

    @FXML
    private DatePicker debut_sejour;

    @FXML
    private DatePicker fin_sejour;

    @FXML
    private ImageView im1;

    @FXML
    private ImageView im2;

    @FXML
    private ImageView im4;

    @FXML
    private ImageView imgLogo;

    @FXML
    private Button rechercher;

    @FXML
    private TextField txtRecherche;

    @FXML
    void connexion(ActionEvent event) {

    }

    @FXML
    void onSearchButtonClick(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("search-view.fxml"));
        Parent root = (Parent) fxmlLoader.load();
        SearchController controller = (SearchController) fxmlLoader.getController();
        // to init the controller
//		controller.init();

        Scene scene = new Scene(root);
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();

        stage.setScene(scene);
        stage.centerOnScreen();
        stage.show();

    }

}
