/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev_java.gui.offreStage;

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
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.geometry.Side;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.Window;
import javafx.util.Callback;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;
import pidev_java.entities.offreStage;
import pidev_java.services.stageService;

/**
 * FXML Controller class
 *
 * @author Ghassen Riahi
 */
public class ModifieroffreStageController implements Initializable {
     ObservableList<String> ListDomaine = FXCollections.observableArrayList("Informatique","Design","jeux vidéo","Artisanat");
    ObservableList<String> ListType = FXCollections.observableArrayList("stage d'été","stage d'initiation","stage PFE","stage de perfectionnement");
    ObservableList<String> ListDuree = FXCollections.observableArrayList("2 semaines","1 mois","2 mois","3 mois","6 mois");
    private offreStage os;

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
    private Button btnmodifer;
    @FXML
    private ComboBox<String> cmbType;
    @FXML
    private Label textid;
    @FXML
    private ComboBox<String> cmbDuree;
    private ConsulterOffreStageController cs;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         this.cmbDomaine.setItems(this.ListDomaine);
       this.cmbType.setItems(ListType);
       this.cmbDuree.setItems(this.ListDuree);
       
       
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
    
    
    
     public void initUpdate(ConsulterOffreStageController fc,offreStage F){
         this.os=F;
        this.cs=fc;
        
        this.tfNom.setText(F.getNomProjet());
        this.tfCompetences.setText(F.getCompetence());
        this.tfDescription.setText(F.getDescription());
        this.cmbDomaine.setValue(String.valueOf(F.getDomaine()));
        this.cmbDuree.setValue(String.valueOf(F.getDuree()));
        this.cmbType.setValue(String.valueOf(F.getTypeStage()));
        this.dtExpiration.setValue(LocalDate.parse(F.getDateExpiration().toString()));
     //   BtnAjoutF.setText("Update");
      //  this.btnmodifer.setText("Modifier");
      //          
       
    }

    @FXML
    private void modifierStage(MouseEvent event) {
        
         ContextMenu usernameValidator = new ContextMenu();
        usernameValidator.setAutoHide(false);
        final ContextMenu passValidator = new ContextMenu();
        passValidator.setAutoHide(false);
        
        
        
        stageService ss=new stageService();
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
        else if (this.cmbType.getValue().equals("")) {
                    usernameValidator.getItems().clear();
                    usernameValidator.getItems().add(
                            new MenuItem("choisir un type de stage"));
                    usernameValidator.show(cmbType, Side.RIGHT, 10, 0);
                }
        else if (this.cmbDuree.getValue().equals("")) {
                    usernameValidator.getItems().clear();
                    usernameValidator.getItems().add(
                            new MenuItem("choisir une durée"));
                    usernameValidator.show(cmbDuree, Side.RIGHT, 10, 0);
                }
        else if (this.dtExpiration.getValue()==null) {
            
                    usernameValidator.getItems().clear();
                    usernameValidator.getItems().add(
                            new MenuItem("saisir une date d'expiration"));
                    usernameValidator.show(dtExpiration, Side.RIGHT, 10, 0);
         }
        else{
           
        Date dateE = java.sql.Date.valueOf(dtExpiration.getValue());
        
        offreStage e = new offreStage(this.os.getId(),tfNom.getText(),tfCompetences.getText(),tfDescription.getText(),this.cmbDomaine.getSelectionModel().getSelectedItem(),this.cmbDuree.getSelectionModel().getSelectedItem(),this.cmbType.getSelectionModel().getSelectedItem(),dateC,dateE);
       
        new stageService().update(e);
        this.cs.updateList();
       
        Window window =   ((Node)(event.getSource())).getScene().getWindow(); 
            if (window instanceof Stage){
                ((Stage) window).close();
            }
             notificationEdit();
        }
           
    }

    @FXML
    private void viderStage(MouseEvent event) {
        resetStage();
    }
    
     private void showAlertWithHeaderText() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Confirmation");
       alert.setHeaderText(null);
        alert.setContentText("votre offre est ajouté avec succés!");
 
        alert.showAndWait();
    }
     
      public void resetStage(){
    this.tfNom.setText("");
    this.tfCompetences.setText("");
    this.tfDescription.setText("");
  //  this.tfSalaire.setText("");
    this.cmbDuree.setValue(null);
    
    this.cmbDomaine.setValue("-- choisir un domaine --");
    this.cmbType.setValue("-- choisir un type --");
   // this.dtCreation.setValue(null);
    this.dtExpiration.setValue(null);
    
    }
      public void notificationEdit(){
      // Image img = new Image("tt.png");
        Notifications notificationBuilder = Notifications.create()
                .title("Succés de modification")
                .text("votre offre a été modifier avec succés")
               .graphic(null)
                .hideAfter(Duration.seconds(5))
                .position(Pos.TOP_RIGHT)
                .onAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                 System.out.println("clicked on notification");
            }
        });
        notificationBuilder.darkStyle();
        notificationBuilder.showInformation();
       
    
    }
    
}
