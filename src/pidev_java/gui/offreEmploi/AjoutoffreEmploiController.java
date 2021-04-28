/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev_java.gui.offreEmploi;

import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.geometry.Side;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.util.Callback;
import javafx.util.Duration;
import org.controlsfx.control.CheckComboBox;
import org.controlsfx.control.Notifications;
import pidev_java.entities.Societe;
import pidev_java.entities.offreEmploi;
import pidev_java.services.Create_QR;
import pidev_java.services.emploiService;

/**
 * FXML Controller class
 *
 * @author Ghassen Riahi
 */
public class AjoutoffreEmploiController implements Initializable {
              ObservableList<String> List = FXCollections.observableArrayList("Informatique","Design","jeux vidéo","Artisanat");
               ObservableList<String> ListDevise = FXCollections.observableArrayList("Euros","Dollars","Dinars");
               private ConsulterOffreEmploiController cs;

    @FXML
    private TextField tfNom;
    @FXML
    private TextField tfCompetences;
    @FXML
    private TextArea tfDescription;
    @FXML
    private ComboBox<String> cmbDomaine;
    @FXML
    private DatePicker dtExpiration;
    @FXML
    private TextField tfsalaire;
    @FXML
    private Button btnAjout;
   // private CheckComboBox<String> cmbDevise;
    @FXML
    private ComboBox<String> cmbDevises;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.cmbDomaine.setItems(List);
        this.cmbDevises.setItems(this.ListDevise);
       // this.cmbDevises.setIems(this.ListDevise);
         DatePicker minDate = new DatePicker(); // DatePicker, used to define max date available, you can also create another for minimum date
minDate.setValue(LocalDate.now().plusDays(5)); // Max date available will be 2015-01-01
final Callback<DatePicker, DateCell> dayCellFactory;

dayCellFactory = (final DatePicker datePicker) -> new DateCell() {
    @Override
    public void updateItem(LocalDate item, boolean empty) {
        super.updateItem(item, empty);
        if (item.isBefore(minDate.getValue())) { //Disable all dates after required date
            setDisable(true);
            setStyle("-fx-background-color: #ffc0cb;"); //To set background on different color
        }
    }
}; 
dtExpiration.setDayCellFactory(dayCellFactory);
        // TODO
    }    

     public void init(ConsulterOffreEmploiController cc){
        this.cs=cc;
       
    }
    
    @FXML
    private void ajouterEmploi(MouseEvent event) {
        
        Societe s = Societe.getInstance();
        Create_QR q = new Create_QR();
        q.gene(s.getId(),s.getEmail());
        ContextMenu usernameValidator = new ContextMenu();
        usernameValidator.setAutoHide(false);
        final ContextMenu passValidator = new ContextMenu();
        passValidator.setAutoHide(false);
        
        
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");  
          LocalDateTime now = LocalDateTime.now();
          System.out.println(now.toLocalDate());
         Date dateC=Date.valueOf(now.toLocalDate());
         
         if (this.tfNom.getText().equals("")) {
                    usernameValidator.getItems().clear();
                    usernameValidator.getItems().add(
                            new MenuItem("entrer un nom de projet"));
                    usernameValidator.show(tfNom, Side.RIGHT, 10, 0);
                }
        else if (this.tfCompetences.getText().equals("")) {
                    usernameValidator.getItems().clear();
                    usernameValidator.getItems().add(
                            new MenuItem("entrer des compétences"));
                    usernameValidator.show(tfCompetences, Side.RIGHT, 10, 0);
                }
        else if (this.tfDescription.getText().equals("")) {
                    usernameValidator.getItems().clear();
                    usernameValidator.getItems().add(
                            new MenuItem("entrer une description"));
                    usernameValidator.show(tfDescription, Side.RIGHT, 10, 0);
                }
        else if (this.cmbDomaine.getValue().equals("")) {
                    usernameValidator.getItems().clear();
                    usernameValidator.getItems().add(
                            new MenuItem("choisir un domaine"));
                    usernameValidator.show(cmbDomaine, Side.RIGHT, 10, 0);
                }
         else if (this.tfsalaire.equals("")) {
                    usernameValidator.getItems().clear();
                    usernameValidator.getItems().add(
                            new MenuItem("saisir un salaire"));
                    usernameValidator.show(tfsalaire, Side.RIGHT, 10, 0);
         }
          else if (this.dtExpiration.getValue()==null) {
                    usernameValidator.getItems().clear();
                    usernameValidator.getItems().add(
                            new MenuItem("saisir une date d'expiration"));
                    usernameValidator.show(dtExpiration, Side.RIGHT, 10, 0);
         }
           else if (this.cmbDevises.getValue().equals("")) {
                    usernameValidator.getItems().clear();
                    usernameValidator.getItems().add(
                            new MenuItem("choisir votre devise"));
                    usernameValidator.show(cmbDomaine, Side.RIGHT, 10, 0);
                }
          else{
         Date dateE = java.sql.Date.valueOf(dtExpiration.getValue());
        
       emploiService service = new emploiService();
       int idOffreEmploi = service.maxId()+1;
        offreEmploi e = new offreEmploi(tfNom.getText(),tfCompetences.getText(),tfDescription.getText(),cmbDomaine.getSelectionModel().getSelectedItem(),Float.parseFloat(tfsalaire.getText()),dateC,dateE,cmbDevises.getSelectionModel().getSelectedItem());
      e.setIdSociete(s.getId());
        new emploiService().add(e);
         notification();
        new AdminEmploiService().SendOffreEmploiToAdminEmploi(e.getId());
       
        reset();
    }
    }

    @FXML
    private void AnnulerEmploi(ActionEvent event) {
        reset();
    }
    
     private void showAlertWithHeaderText() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Confirmation");
       alert.setHeaderText(null);
        alert.setContentText("votre offre est ajouté avec succés!");
 
        alert.showAndWait();
    }
     
     public void reset(){
    this.tfNom.setText("");
    this.tfCompetences.setText("");
    this.tfDescription.setText("");
    this.tfsalaire.setText("");
    this.cmbDomaine.setValue("-- choisir un domaine --");
    this.cmbDevises.setValue("--Devise--");
    this.dtExpiration.setValue(null);
    
    }
     public void notification(){
      // Image img = new Image("tt.png");
        Notifications notificationBuilder = Notifications.create()
                .title("Succés d'ajout")
                .text("votre offre a été ajouter avec succés")
              //  .graphic(new ImageView(img))
                .hideAfter(Duration.seconds(5))
                .position(Pos.TOP_RIGHT)
                .onAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                 System.out.println("clicked on notification");
            }
        });
        notificationBuilder.darkStyle();
        notificationBuilder.showInformation();
       
    
    }
    @FXML
    private void cmbDevise(MouseEvent event) {
    }
    
    

