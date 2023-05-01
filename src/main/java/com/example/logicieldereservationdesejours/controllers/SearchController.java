package com.example.logicieldereservationdesejours.controllers;

import com.example.logicieldereservationdesejours.Main;
import com.example.logicieldereservationdesejours.models.Voyage;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class SearchController {

    @FXML
    private Pane contact;

    @FXML
    private DatePicker debut_sejour;

    @FXML
    private DatePicker fin_séjour;

    @FXML
    private Button hote;

    @FXML
    private ImageView map;

    @FXML
    private Button rechercher;

    @FXML
    private VBox tripListView;

    @FXML
    private TextField txtf_rechercher_voyage;
    private List<Voyage> listVoyages;


    public void initialize(URL url, ResourceBundle resourceBundle) {
        listVoyages = getAllVoyages();

        // Initialize trip card views
        for (int i = 0; i < 4; i++) {
            try {
                Node tripCardView = FXMLLoader.load(Main.class.getResource("voyage.fxml"));
                tripCardView.setStyle("-fx-border-color: lightgrey");

                final int j = i;
                tripCardView.setOnMouseEntered(event -> {
                    tripCardView.setStyle("-fx-border-color: grey");
                });
                tripCardView.setOnMouseExited(event -> {
                    tripCardView.setStyle("-fx-border-color: lightgrey");
                });

                tripListView.getChildren().add(tripCardView);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        File file = new File("src/main/java/com/example/logicieldereservationdesejours/images/map.png");
        Image image = new Image(file.toURI().toString());
        map.setImage(image);
    }

    private List<Voyage> getAllVoyages(){
        List<Voyage> voyages = new ArrayList<>();
        String file = "com/example/logicieldereservationdesejours/images/voyages.csv";
        try (BufferedReader br = new BufferedReader(new FileReader(file))){
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(";");
                Voyage voyage = new Voyage();
                voyage.setVille(values[0]);
                voyage.setHeure(values[1]);
                voyage.setType(values[2]);
                voyage.setContreparties(values[3]);
                voyage.setContraintes(values[4]);
                voyage.setServices(values[5]);
                voyage.setTransfert(values[6]);
                voyage.setRestauration(values[7]);
                voyage.setDateArrivee(values[8]);
                voyage.setDateDepart(values[9]);
                voyages.add(voyage);
            }

        }catch (IOException e) {
            e.printStackTrace();
        }


        System.out.println("voyage");
        return voyages;

    }


}
