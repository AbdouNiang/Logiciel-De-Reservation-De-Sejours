package com.example.logicieldereservationdesejours;

import com.example.logicieldereservationdesejours.models.Voyage;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

import java.io.File;
import java.io.IOException;

public class DetailVoyageController {
    private Voyage voyage;
    @FXML
    ImageView imgVoyage;
    @FXML
    private Button commentaire;
    @FXML
    private Button retour;
    @FXML
    Text txtTitle;
    @FXML Text txtService;
    @FXML Text txtHeures;
    @FXML Text txtNomPrenomHote;
    @FXML Text txtDescriptifHote;
    @FXML ImageView imgHote;
    @FXML
    Button btnContacter;
    @FXML Text txtLogement;
    @FXML ImageView imgLogement;
    @FXML ImageView imgContreparties;


    public void init() {
        File file = new File("src/main/java/com/example/logicieldereservationdesejours/assets/images/ville/" + voyage.getVille() + ".png");
        Image image = new Image(file.toURI().toString());
        imgVoyage.setImage(image);
        txtTitle.setText(voyage.getContreparties() + " à " + voyage.getVille());
        txtService.setText(voyage.getContreparties());
        txtHeures.setText(voyage.getHeure() + "h/j");

        txtLogement.setText(voyage.getType());
        File file2 = new File("src/main/java/com/example/logicieldereservationdesejours/assets/images/logement/" + voyage.getType() + ".png");
        Image image2 = new Image(file2.toURI().toString());
        imgLogement.setImage(image2);

        File file3 = new File("src/main/java/com/example/logicieldereservationdesejours/assets/images/contreparties.png");
        Image image3 = new Image(file3.toURI().toString());
        imgContreparties.setImage(image3);
    }

    public void setVoyage(Voyage voyage){
        this.voyage = voyage;
    }
    @FXML
    void retour (ActionEvent event){
        Scene scene = retour.getScene();
        try {
            AnchorPane root = (AnchorPane) FXMLLoader.load(getClass().getResource("Accueil.fxml"));
            scene.setRoot(root);
        }
        catch (IOException e) {
            e.printStackTrace();
        }

    }

    @FXML
    void commentaire(ActionEvent event){
        Scene scene = commentaire.getScene();
        try {
            AnchorPane root = (AnchorPane) FXMLLoader.load(getClass().getResource("Discussion.fxml"));
            scene.setRoot(root);
        }
        catch (IOException e) {
            e.printStackTrace();
        }

    }
}
