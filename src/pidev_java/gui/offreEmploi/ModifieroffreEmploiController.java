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
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Side;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.Window;
import javafx.util.Callback;
import pidev_java.entities.offreEmploi;
import pidev_java.services.emploiService;

/**
 * FXML Controller class
 *
 * @author Ghassen Riahi
 */
public class ModifieroffreEmploiController implements Initializable {
    
      ObservableList<String> List = FXCollections.observableArrayList("Informatique","Design","jeux vidéo","Artisanat");
private offreEmploi os;
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
    private Button btnmodifier;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
 
        this.cmbDomaine.setItems(List);
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
    
     public void initUpdate(ConsulterOffreEmploiController fc,offreEmploi F){
         this.os=F;
        this.cs=fc;
        
        this.tfNom.setText(F.getNomProjet());
        this.tfCompetences.setText(F.getCompetence());
        this.tfDescription.setText(F.getDescription());
        this.cmbDomaine.setValue(String.valueOf(F.getDomaine()));
        this.tfsalaire.setText(F.getSalaire());
        this.dtExpiration.setValue(LocalDate.parse(F.getDateExpiration().toString()));
     //   BtnAjoutF.setText("Update");
      //  this.btnmodifer.setText("Modifier");

     }
    @FXML
    private void AnnulerEmploi(ActionEvent event) {
        reset();
    }
    
     public void reset(){
    this.tfNom.setText("");
    this.tfCompetences.setText("");
    this.tfDescription.setText("");
    this.tfsalaire.setText("");
    this.cmbDomaine.setValue("-- choisir un domaine --");
    
    this.dtExpiration.setValue(null);
     }
    @FXML
    private void modifierEmploi(MouseEvent event) {
        
         ContextMenu usernameValidator = new ContextMenu();
        usernameValidator.setAutoHide(false);
        final ContextMenu passValidator = new ContextMenu();
        passValidator.setAutoHide(false);
        
        
        
        emploiService ss=new emploiService();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");  
          LocalDateTime now = LocalDateTime.now();
          System.out.println(now.toLocalDate());
         Date dateC=Date.valueOf(now.toLocalDate());
         
         
          if (this.tfNom.getText().equals("")) {
                    usernameValidator.getItems().clear();
                    usernameValidator.getItems().add(
                            new MenuItem("entrer un nom de projet"));
                    usernameValidator.setStyle("-fx-background-color:wheat;");
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
        else if (this.tfsalaire.getText().equals("")) {
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
        else{
           
        Date dateE = java.sql.Date.valueOf(dtExpiration.getValue());
        offreEmploi e = new offreEmploi(tfNom.getText(),tfCompetences.getText(),tfDescription.getText(),cmbDomaine.getSelectionModel().getSelectedItem(),tfsalaire.getText(),dateC,dateE);
      
        new emploiService().update(e);
        this.cs.updateList();
        Window window =   ((Node)(event.getSource())).getScene().getWindow(); 
            if (window instanceof Stage){
                ((Stage) window).close();
            }
        }
    }

    @FXML
    private void AnnulerEmploi(MouseEvent event) {
        reset();
        
    }
     
    
}
