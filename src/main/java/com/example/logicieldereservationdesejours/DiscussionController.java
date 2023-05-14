package com.example.logicieldereservationdesejours;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import java.io.FileWriter;
import java.io.IOException;

public class DiscussionController {
    @FXML
    private TextField commetaire;

    @FXML
    private Button send;
    @FXML
    private Button accueil;


    @FXML
    private Label successLabel;

    @FXML
    void send(ActionEvent event) {
        String csvFile = "src/main/java/com/example/logicieldereservationdesejours/commentaire.csv";
        String commentaire = commetaire.getText();

        try (FileWriter writer = new FileWriter(csvFile, true)) {
            // Ajouter le commentaire au fichier CSV
            writer.append(commentaire);
            writer.append("\n"); // Nouvelle ligne

            successLabel.setText("Commentaire enregistré avec succès.");
            System.out.println("Commentaire enregistré avec succès dans le fichier CSV.");
        } catch (IOException e) {
            System.out.println("Erreur lors de l'enregistrement du commentaire dans le fichier CSV: " + e.getMessage());
        }
    }

    @FXML
    void accueil(ActionEvent event) {
        Scene scene = accueil.getScene();
        try {
            AnchorPane root = (AnchorPane) FXMLLoader.load(getClass().getResource("Accueil.fxml"));
            scene.setRoot(root);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }








}
