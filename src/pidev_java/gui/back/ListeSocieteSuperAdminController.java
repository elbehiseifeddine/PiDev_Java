/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev_java.gui.back;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.util.Callback;
import pidev_java.entities.Freelancer;
import pidev_java.entities.Societe;
import pidev_java.services.FreelancerService;
import pidev_java.services.SocieteService;
import pidev_java.utils.PdfGeneration;

/**
 * FXML Controller class
 *
 * @author ahmed
 */
public class ListeSocieteSuperAdminController implements Initializable {

        ObservableList<Societe> ListSociete = FXCollections.observableArrayList();

    private TableView<Societe> TableSociete;
    private TableColumn<Societe, Integer> id_societe;
    private TableColumn<Societe, String> nom;
    private TableColumn<Societe, String> email;
    private TableColumn<Societe, String> adresse;
    private TableColumn<Societe, String> statue_juridique;
    private TableColumn<Societe, String> date_creation;
    private TableColumn<Societe, Integer> etat;
    private TableColumn<Societe, String> actions;
    @FXML
    private AnchorPane NavBar;
    @FXML
    private Label pdfLab;
    @FXML
    private AnchorPane Admins;
    @FXML
    private HBox AdminRecHbox;
    @FXML
    private Label LabelReclamationVide;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        loadDataSociete();
    }    
    private void refrechSociete() {
        ListSociete.clear();
        SocieteService service = new SocieteService();
        ListSociete.addAll(service.getAll());
        
    }
    
     private void loadDataSociete() {
        refrechSociete();
        SocieteService societeService = new SocieteService();
        ArrayList<Societe> liste = societeService.getAll();
        if (liste.isEmpty()) {
            AdminRecHbox.setVisible(false);
            LabelReclamationVide.setText("Aucun admin disponible, pensez à lui créer ! ");

        } else {
            for (Societe societe : liste) {
                ImageView image = new ImageView("./pidev_java/assets/img-1.jpg");
                Label name = new Label(societe.getNom());
                name.setStyle("-fx-font-weight: bold;");
                Label LabelEmail = new Label(societe.getEmail());
                Label lock = new Label();
                Button activatebtn = new Button();

                if(societe.getEtat()==0){
                    activatebtn.setStyle(
                        " -fx-cursor: hand ;"
                        + "-glyph-size:28px;"
                        + "-fx-fill:#00E676;"
                        + "-fx-background-radius: 5px;"
                        + "-fx-background-color: green;"
                        + "-fx-text-fill: whitesmoke;" 
                        + "-fx-font-size: 19;" 
                        + "-fx-font-weight: 600;"
                    );
                    activatebtn.setText("Activated");                    
                }else{
                    activatebtn.setStyle(
                        " -fx-cursor: hand ;"
                        + "-glyph-size:28px;"
                        + "-fx-fill:#00E676;"
                        + "-fx-background-radius: 5px;"
                        + "-fx-background-color: red;"
                        + "-fx-text-fill: whitesmoke;" 
                        + "-fx-font-size: 19;" 
                        + "-fx-font-weight: 600;"
                    );
                    activatebtn.setText("Deactivated");  
                }
                
                activatebtn.setOnMouseClicked((event) -> {
                    if(societe.getEtat()==1){
                        societeService.ActivateSociete(societe.getId());
                        activatebtn.setStyle(
                            " -fx-cursor: hand ;"
                            + "-glyph-size:28px;"
                            + "-fx-fill:#00E676;"
                            + "-fx-background-radius: 5px;"
                            + "-fx-background-color: green;"
                            + "-fx-text-fill: whitesmoke;" 
                            + "-fx-font-size: 19;" 
                            + "-fx-font-weight: 600;"
                        );
                        activatebtn.setText("Activated");   
                    }else{
                        societeService.DeactivateSociete(societe.getId());
                        activatebtn.setStyle(
                            " -fx-cursor: hand ;"
                            + "-glyph-size:28px;"
                            + "-fx-fill:#00E676;"
                            + "-fx-background-radius: 5px;"
                            + "-fx-background-color: red;"
                            + "-fx-text-fill: whitesmoke;" 
                            + "-fx-font-size: 19;" 
                            + "-fx-font-weight: 600;"
                        );
                        activatebtn.setText("Deactivated");
                    }
                        
                });
                HBox managebtn = new HBox(activatebtn);
                managebtn.setStyle("-fx-alignment:center");
                HBox.setMargin(activatebtn, new Insets(2, 3, 0, 2));
                VBox vbox = new VBox(image, name, LabelEmail, lock, managebtn);
                vbox.setMaxWidth(1501 / liste.size());
                vbox.setPrefWidth(250);
                vbox.setSpacing(8);
                vbox.setPadding(new Insets(0, 20, 0, 20));
                vbox.setAlignment(Pos.CENTER);
                vbox.setStyle("-fx-background-color: white;"+"-fx-background-radius: 20px;");
                AdminRecHbox.getChildren().add(vbox);
            }
        }
        AdminRecHbox.setSpacing(20);
        AdminRecHbox.prefWidth(1585);

    }

    @FXML
    private void PDFGenerator(ActionEvent event) {
        SocieteService societeService = new SocieteService();
        ArrayList<Societe> List = societeService.getAll();
        PdfGeneration.SocieteListPdf(List);
        pdfLab.setVisible(true);
    }
    
}
