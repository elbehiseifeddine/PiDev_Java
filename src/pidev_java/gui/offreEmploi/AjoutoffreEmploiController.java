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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.util.Callback;
import pidev_java.entities.offreEmploi;
import pidev_java.services.emploiService;

/**
 * FXML Controller class
 *
 * @author Ghassen Riahi
 */
public class AjoutoffreEmploiController implements Initializable {
              ObservableList<String> List = FXCollections.observableArrayList("Informatique","Design","jeux vidéo","Artisanat");
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

     public void init(ConsulterOffreEmploiController cc){
        this.cs=cc;
       
    }
    
    @FXML
    private void ajouterEmploi(MouseEvent event) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");  
          LocalDateTime now = LocalDateTime.now();
          System.out.println(now.toLocalDate());
         Date dateC=Date.valueOf(now.toLocalDate());
         Date dateE = java.sql.Date.valueOf(dtExpiration.getValue());
        
       
        offreEmploi e = new offreEmploi(tfNom.getText(),tfCompetences.getText(),tfDescription.getText(),cmbDomaine.getSelectionModel().getSelectedItem(),tfsalaire.getText(),dateC,dateE);
      
        new emploiService().add(e);
        showAlertWithHeaderText();
        reset();
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
    
    this.dtExpiration.setValue(null);
    
    }
    
}
