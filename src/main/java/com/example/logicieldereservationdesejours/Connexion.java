package com.example.logicieldereservationdesejours;

import com.example.logicieldereservationdesejours.models.ConnexionParam;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class Connexion {
    @FXML
    private Button accueil, valider;
    @FXML
    private RadioButton hote, voyageur;
    @FXML
    private TextField id;

    private String typeConnexion;

    public void accueil() {
        Scene scene = accueil.getScene();
        try {
            AnchorPane root = (AnchorPane) FXMLLoader.load(getClass().getResource("Accueil.fxml"));
            scene.setRoot(root);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void valider() {
        ConnexionParam param = ConnexionParam.getInstance();
        param.setConnexion(id.getText(),typeConnexion);

        AnchorPane root = null;
        try {
            root = (AnchorPane) FXMLLoader.load(getClass().getResource("AccueilRecherche.fxml"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Scene scene = valider.getScene();
        scene.setRoot(root);

    }

}
