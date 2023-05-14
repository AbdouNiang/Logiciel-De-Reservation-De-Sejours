package com.example.logicieldereservationdesejours;

import com.example.logicieldereservationdesejours.models.Voyage;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.ResourceBundle;


public class AccueilController implements Initializable {

    @FXML
    private MenuItem connex;
    @FXML
    private Pane contact;
    @FXML private TextField txtf_rechercher_voyage;
    @FXML private TextField txtDestination;
    @FXML private TextField txtRecherche;
    @FXML private DatePicker debut_sejour;
    @FXML private DatePicker fin_sejour;
    @FXML private Text txtWarning;

    @FXML private Button connexion, profil;

    @FXML private Button proposerVoyage;

    private ArrayList<Voyage> listAllVoyages;
    private ArrayList<Voyage> listVoyagesResults;


    public void initialize(URL arg0, ResourceBundle arg1) {
        contact.setBorder(new Border(new BorderStroke(Color.BLACK,BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));


        // get all voyages
        listAllVoyages = this.getAllVoyages();
        // init datepickers
        LocalDate today = LocalDate.now();
        updateDatePicker(debut_sejour, today);
        updateDatePicker(fin_sejour, today);

        debut_sejour.valueProperty().addListener((ov, oldValue, newValue) -> {
            if (newValue != null) {
                updateDatePicker(fin_sejour, newValue);
                LocalDate finSejourValue = fin_sejour.getValue();
                if (finSejourValue != null && finSejourValue.isBefore(newValue)) {
                    fin_sejour.setValue(newValue);
                }
            }
        });

    }
    @FXML
    protected void onSearchButtonClick(ActionEvent event) throws IOException {
        if (txtRecherche.getText().length() > 1) {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("search-view.fxml"));
            Parent root = (Parent) fxmlLoader.load();

            SearchController searchController = (SearchController)fxmlLoader.getController();
            listVoyagesResults = searchVoyagesResults();
            searchController.setListAllVoyages(listAllVoyages);
            searchController.setListVoyagesResults(listVoyagesResults);
            searchController.settxtRecherche2(txtRecherche);
            searchController.setDebut_sejour2(debut_sejour);
            searchController.setFin_sejour2(fin_sejour);
            searchController.init();

            Scene scene = new Scene(root);
            Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();

            stage.setScene(scene);
            stage.centerOnScreen();
            stage.show();
        } else {
            displayWarning();
        }

    }

    public ArrayList<Voyage> getAllVoyages() {
        ArrayList<Voyage> voyages = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader("src/main/java/com/example/logicieldereservationdesejours/assets/images/voyages.csv"))) {
            String line;
            while ((line = br.readLine()) != null) {
                //System.out.println(line);
                String[] values = line.split(";");
                Voyage voyage = new Voyage(
                        values[0],
                        values[1],
                        values[2],
                        values[3],
                        values[4],
                        values[5],
                        values[6],
                        values[7],
                        values[8],
                        values[9]
                );
              //  System.out.println(voyage);
                voyages.add(voyage);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


        //System.out.println(voyages);
        return voyages;

    }

    public ArrayList<Voyage> searchVoyagesResults() {
        String searchText = this.txtRecherche.getText();
        LocalDate debutSejourValue = this.debut_sejour.getValue();
        LocalDate finSejourValue = this.fin_sejour.getValue();

        // FOR DEMO: if no input, results if all 10000 voyages
        if ("all".equals(searchText)) {
            return this.listAllVoyages;
        }

        ArrayList<Voyage> results = new ArrayList<>();

        for (Voyage v : listAllVoyages) {
            if ((v.getVille().contains(searchText) || v.getContreparties().contains(searchText))
                    && (debutSejourValue == null || (v.getDateArrivee() != null && isDateValid(v.getDateArrivee()) && !debutSejourValue.isBefore(LocalDate.parse(v.getDateArrivee()))))
                    && (finSejourValue == null || (v.getDateDepart() != null && isDateValid(v.getDateDepart()) && !finSejourValue.isAfter(LocalDate.parse(v.getDateDepart()))))) {
                results.add(v);
            }
        }

        return results;
    }

    // Méthode pour vérifier si la date est valide
    private boolean isDateValid(String dateString) {
        try {
            LocalDate.parse(dateString);
            return true;
        } catch (DateTimeParseException | NullPointerException e) {
            return false;
        }
    }


    public void displayWarning(){
        txtWarning.setVisible(true);
        new Thread( new Runnable() {
            public void run()  {
                try  { Thread.sleep( 3000 ); }
                catch (InterruptedException ie)  {}
                txtWarning.setVisible(false);
            }
        } ).start();
    }

    public void updateDatePicker(DatePicker dp, LocalDate startLocalDate){
        // restriction sur date picker
        dp.setDayCellFactory(picker -> new DateCell() {
            public void updateItem(LocalDate date, boolean empty) {
                super.updateItem(date, empty);
                setDisable(empty || date.compareTo(startLocalDate) < 0 );
            }
        });
    }

    public void goToAccueil(ActionEvent event) throws IOException {
        try {
            System.out.println("Vous êtes sur la page d'accueil");
            Parent Accueil = FXMLLoader.load(getClass().getResource("Accueil.fxml"));
            Scene AccueilScene = new Scene(Accueil);

            Stage settStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            settStage.setScene(AccueilScene);
            settStage.show();
        }
        catch (Exception e) {
            System.err.println(e.getLocalizedMessage());
        }
    }
    public void connex(){
        Scene scene = connex.getParentPopup().getOwnerWindow().getScene();
        try {
            AnchorPane root = (AnchorPane)FXMLLoader.load(getClass().getResource("Connexion.fxml"));
            scene.setRoot(root);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void connexion() {
        Scene scene = connexion.getScene();
        try {
            AnchorPane root = (AnchorPane)FXMLLoader.load(getClass().getResource("Connexion.fxml"));
            scene.setRoot(root);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void profilVoyageur() {
        Scene scene = profil.getScene();
        try {
            AnchorPane root = (AnchorPane)FXMLLoader.load(getClass().getResource("ProfilVoyageur.fxml"));
            scene.setRoot(root);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void proposerVoyage(ActionEvent actionEvent) {
    }
}
