package com.example.logicieldereservationdesejours;

import com.example.logicieldereservationdesejours.Main;
import com.example.logicieldereservationdesejours.models.Voyage;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

public class SearchController {

    @FXML
    private Button bouton_accueil;

    @FXML
    private Pane contact;

    @FXML
    private DatePicker debut_sejour2;

    @FXML
    private DatePicker fin_sejour2;

    @FXML
    private Button hote;

    @FXML
    private ImageView imgMap;

    @FXML
    private ScrollPane paneResults;

    @FXML
    private Button rechercher;

    @FXML
    private VBox tripListView;

    @FXML
    private TextField txtRecherche2;

    @FXML
    private Text txtResults;

    @FXML
    private Text txtWarning2;

    @FXML
    void goToAccueil(ActionEvent event) {

    }


    private ArrayList<Voyage> listAllVoyages;
    private ArrayList<Voyage> listVoyagesResults2;

    public void init() {
        setMap();
        handleResultsQty();
        initialiseResultsPane();
        txtRecherche2.textProperty().addListener((observable, oldValue, newValue) -> {
            searchVoyages();
        });

    }

    public void updateUI(){
        handleResultsQty();
        initialiseResultsPane();
    }
    public void setMap(){
        File file = new File("com/example/logicieldereservationdesejours/images/map.png");
        Image image = new Image(file.toURI().toString());
        imgMap.setImage(image);
    }

    public void handleResultsQty(){
        // gère le nombre de résultats
        if (listVoyagesResults2.size() == 0) txtResults.setText("Désolé, aucun résultat n'a été trouvé...");
        else txtResults.setText(listVoyagesResults2.size() + " séjours peuvent vous satisfaire");
        imgMap.setVisible(listVoyagesResults2.size() != 0);
        paneResults.setVisible(listVoyagesResults2.size() != 0);
    }

    public void initialiseResultsPane(){
        tripListView.getChildren().clear();
        if (listVoyagesResults2.size() > 0){
            Node[] nodes = new Node[5];

            // initialize graphics
            for (int i=0; i<nodes.length && i < listVoyagesResults2.size();i++){
                try{
                    final int index=i;
                    nodes[i] = FXMLLoader.load(Main.class.getResource("trip-card-view.fxml"));
                    nodes[i].setStyle("-fx-border-color: lightgrey");

                    // bind data
                    ImageView iv = (ImageView) nodes[i].lookup("#image");
                    Label titre = (Label) nodes[i].lookup("#titre");
                    Label contrepartie = (Label) nodes[i].lookup("#contrepartie");
                    Label logement = (Label) nodes[i].lookup("#logement");
                    Label heures = (Label) nodes[i].lookup("#heures");

                    File file = new File("com/example/logicieldereservationdesejours/images/villes/"+ listVoyagesResults2.get(i).getVille() +".png");
                    Image image = new Image(file.toURI().toString());
                    iv.setImage(image);
                    titre.setText(listVoyagesResults2.get(i).getContreparties() + " à " + listVoyagesResults2.get(i).getVille());
                    contrepartie.setText(listVoyagesResults2.get(i).getContreparties());
                    logement.setText(listVoyagesResults2.get(i).getType());
                    heures.setText(listVoyagesResults2.get(i).getHeure() + " h/j");

                    // add some effect
                    nodes[i].setOnMouseEntered(event -> {
                        nodes[index].setStyle("-fx-border-color: grey");
                    });
                    nodes[i].setOnMouseExited(event -> {
                        nodes[index].setStyle("-fx-border-color: lightgrey");
                    });

                    // add onClickListener
                    int voyageIndex =  i;
                    nodes[i].setOnMouseClicked((new EventHandler<MouseEvent>() {
                        public void handle(MouseEvent event) {
                            System.out.println("VoyageIndex: " + voyageIndex);
                            //navigateToDetailVoyageView(listVoyagesResults2.get(voyageIndex), event);
                        }
                    }));

                    // add items
                    tripListView.getChildren().add(nodes[i]);
                } catch (IOException e){
                    e.printStackTrace();
                }
            }
        }

    }
    public void setListVoyagesResults(ArrayList<Voyage> listVoyagesResults2) {
        this.listVoyagesResults2 = listVoyagesResults2;
    }

    public void setListAllVoyages(ArrayList<Voyage> listAllVoyages) {
        this.listAllVoyages = listAllVoyages;
    }

    public void settxtRecherche2(TextField txtDestination) {
        this.txtRecherche2.setText(txtDestination.getText());
    }

    public void setDebut_sejour2(DatePicker debut_sejour2) {
        this.debut_sejour2.setValue(debut_sejour2.getValue());
    }

    public void setFin_sejour2(DatePicker fin_sejour2) {
        this.fin_sejour2.setValue(fin_sejour2.getValue());
    }

    @FXML
    public void onSearchButtonClick2(ActionEvent event) throws IOException {
        searchVoyages();
    }

    public void searchVoyages(){
        if (txtRecherche2.getText().length() > 1) {

            listVoyagesResults2 = searchVoyagesResults2();
            setListVoyagesResults(listVoyagesResults2);
            updateUI();

        } else {
            displayWarning();
        }

    }
    public ArrayList<Voyage> searchVoyagesResults2() {
        System.out.println(this.txtRecherche2.getText());
        if (debut_sejour2.getValue() != null) System.out.println(this.debut_sejour2.getValue());
        if (fin_sejour2.getValue() != null) System.out.println(this.fin_sejour2.getValue());

        // FOR DEMO: if input= "all", results if all 10000 voyages
        if (txtRecherche2.getText().equals("all")) {
            return this.listAllVoyages;
        }

        ArrayList<Voyage> results = new ArrayList<Voyage>();
        DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

        for (Voyage v : listAllVoyages) {
            try {
                LocalDate dateArrivee = LocalDate.parse(v.getDateArrivee(), inputFormatter);
                LocalDate dateDepart = LocalDate.parse(v.getDateDepart(), inputFormatter);

                if ((v.getVille().contains(txtRecherche2.getText()) || v.getContreparties().contains(txtRecherche2.getText()) || v.getType().contains(txtRecherche2.getText()))
                        && (debut_sejour2.getValue() == null || !debut_sejour2.getValue().isBefore(dateArrivee))
                        && (fin_sejour2.getValue() == null || !fin_sejour2.getValue().isAfter(dateDepart))) {
                    results.add(v);
                }
            } catch (DateTimeParseException e) {
                System.err.println("Erreur de parsing de la date pour le voyage : " + v);
            }
        }

        System.out.println(results.size());
        return results;
    }

    public void displayWarning(){
        txtWarning2.setVisible(true);
        new Thread( new Runnable() {
            public void run()  {
                try  { Thread.sleep( 3000 ); }
                catch (InterruptedException ie)  {}
                txtWarning2.setVisible(false);
            }
        } ).start();
    }
   /* public void goToAccueil(ActionEvent event) throws IOException {
        try {
            System.out.println("Vous êtes sur la page d'accueil");
            Parent Accueil = FXMLLoader.load(getClass().getResource("src/main/resources/com/example/application/Accueil.fxml"));
            Scene AccueilScene = new Scene(Accueil);

            Stage settStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            settStage.setScene(AccueilScene);
            settStage.show();
        }
        catch (Exception e) {
            System.err.println(e.getLocalizedMessage());
        }
    }*/



}
