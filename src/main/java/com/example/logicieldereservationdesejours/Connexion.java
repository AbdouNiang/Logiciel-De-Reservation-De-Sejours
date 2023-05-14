package com.example.logicieldereservationdesejours;

import com.example.logicieldereservationdesejours.models.ConnexionParam;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;

import java.io.*;
import java.util.List;
import java.util.Optional;

public class Connexion {
    @FXML
    private Button accueil, valider;
    @FXML
    private RadioButton hote, voyageur;
    @FXML
    private TextField id;

    @FXML
    private Button inscrire;
    @FXML
    private TextField passwordField;

    @FXML
    private Button inscription;

    @FXML
    private TextField emailFieldinscri;

    @FXML
    private RadioButton hoteinscri;

    @FXML
    private TextField idinscri;

    @FXML
    private TextField nomFieldinscri;

    @FXML
    private TextField passwordFieldinscri;

    @FXML
    private TextField prenomFieldinscri;

    @FXML
    private ToggleGroup typeConnection;

    @FXML
    private HBox typeConnexion;

    @FXML
    private RadioButton voyageurinscri;

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



    //private String typeConnexion;

    //private static final String file = "src/main/java/com/example/logicieldereservationdesejours/assets/images/ville/login.csv";

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
    public void inscrire(){
        Scene scene = inscrire.getScene();
        try {
            AnchorPane root = (AnchorPane) FXMLLoader.load(getClass().getResource("formulaire.fxml"));
            scene.setRoot(root);
        }
        catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void valider() {
        String csvFile = "src/main/java/com/example/logicieldereservationdesejours/login.csv";
        String username = id.getText();
        String password = passwordField.getText(); // Remplacez "passwordField" par le nom de votre champ de saisie de mot de passe

        if (!password.isEmpty() && verifyCredentials(username, password)) {
            // Le mot de passe n'est pas vide et les informations d'identification sont valides
            ConnexionParam param = ConnexionParam.getInstance();
            param.setConnexion(username, password);

            AnchorPane root = null;
            try {
                root = (AnchorPane) FXMLLoader.load(getClass().getResource("AccueilRecherche.fxml"));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            Scene scene = valider.getScene();
            scene.setRoot(root);
        } else {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Inscription");
            alert.setHeaderText("Aucun compte trouvé");
            alert.setContentText("Voulez-vous vous inscrire ?");

            ButtonType buttonTypeOui = new ButtonType("Oui");
            ButtonType buttonTypeNon = new ButtonType("Non");

            alert.getButtonTypes().setAll(buttonTypeOui, buttonTypeNon);

            Optional<ButtonType> result = alert.showAndWait();
            if (result.isPresent() && result.get() == buttonTypeOui) {
                AnchorPane root = null;
                try {
                    root = (AnchorPane) FXMLLoader.load(getClass().getResource("formulaire.fxml"));
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                Scene scene = valider.getScene();
                scene.setRoot(root);
            } else {
                System.out.println("error");
            }


        }

    }
    private boolean verifyCredentials(String username, String password) {
        String csvFile = "src/main/java/com/example/logicieldereservationdesejours/login.csv";
        String line;
        String cvsSplitBy = ",";
        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            // Lire chaque ligne du fichier CSV
            while ((line = br.readLine()) != null) {
                // Diviser la ligne en colonnes
                String[] userData = line.split(cvsSplitBy);

                // Récupérer les informations d'identification de l'utilisateur
                String userIdentifier = userData[0];
                String userPassword = userData[1];

                // Vérifier si les informations d'identification correspondent
                if (userIdentifier.equals(username) && userPassword.equals(password)) {
                    // Les informations d'identification sont valides
                    return true;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Les informations d'identification ne correspondent à aucun utilisateur dans le fichier CSV
        return false;
    }

   /* public void inscription() {
        System.out.println("inscription");
        String csvFile = "src/main/java/com/example/logicieldereservationdesejours/login.csv";
        String username = idinscri.getText();
        String password = passwordFieldinscri.getText();
        String role = (hoteinscri.isSelected()) ? "hote" : "voyageur";
        String nom = nomFieldinscri.getText();
        String prenom = prenomFieldinscri.getText();
        String email = emailFieldinscri.getText();

        if (enregistrerUtilisateur(csvFile, username, password, role, nom, prenom, email)) {
            System.out.println("Inscription réussie");
        } else {
            System.out.println("Erreur lors de l'inscription");
        }
    }*/

    @FXML
    void inscription(ActionEvent event) {
        System.out.println("inscription");
        String csvFile = "src/main/java/com/example/logicieldereservationdesejours/login.csv";
        String username = idinscri.getText();
        String password = passwordFieldinscri.getText();
        String role = (hoteinscri.isSelected()) ? "hote" : "voyageur";
        String nom = nomFieldinscri.getText();
        String prenom = prenomFieldinscri.getText();
        String email = emailFieldinscri.getText();

        if (enregistrerUtilisateur(csvFile, username, password, role, nom, prenom, email)) {
            System.out.println("Inscription réussie");
            Scene scene = inscription.getScene();
            try {
                AnchorPane root = (AnchorPane) FXMLLoader.load(getClass().getResource("InscriptionReussi.fxml"));
                scene.setRoot(root);
            } catch (IOException e) {
                e.printStackTrace();
            }

        } else {
            System.out.println("Erreur lors de l'inscription");
        }

    }


    private boolean enregistrerUtilisateur(String csvFile, String username, String password, String role, String nom, String prenom, String email) {
        try (PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter(csvFile, true)))) {
            writer.println(username + "," + password + "," + role + "," + nom + "," + prenom + "," + email);
            writer.flush();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }
    @FXML
    private Button connectback;

    @FXML
    void connectback(ActionEvent event) {
        Scene scene = connectback.getScene();
        try {
            AnchorPane root = (AnchorPane) FXMLLoader.load(getClass().getResource("Connexion.fxml"));
            scene.setRoot(root);
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

}
