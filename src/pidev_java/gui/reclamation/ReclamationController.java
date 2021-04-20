/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev_java.gui.reclamation;

import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import pidev_java.entities.Reclamation_1;
import pidev_java.services.ReclamationService;
import pidev_java.services.ReclamationService_1;

/**
 * FXML Controller class
 *
 * @author Ayari Ghaith
 */
public class ReclamationController implements Initializable {

    @FXML
    private TextField texttype;
    @FXML
    private TextField texttext;
    @FXML
    private Button btnmodifer;
    @FXML
    private Button btnsupprimer;
    @FXML
    private Button btnajouter;
    @FXML
    private Label textid;
     @FXML
    private TableView<Reclamation_1> tablerec;
    @FXML
    private TableColumn<Reclamation_1, String> type;
    @FXML
    private TableColumn<Reclamation_1, String> text;
    @FXML
    private TableColumn<Reclamation_1, String> date;
    @FXML
    private TableColumn<Reclamation_1, String> email;
    @FXML
    private TableColumn<Reclamation_1, String> nom;
   

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         ReclamationService_1 rs = new ReclamationService_1();

         List<Reclamation_1> lr = rs.Lister();
        
          ObservableList<Reclamation_1> data =
                 FXCollections.observableArrayList(lr); 
        
        type.setCellValueFactory(
                new PropertyValueFactory<>("type"));
          
        text.setCellValueFactory(
                new PropertyValueFactory<>("TextReclamation"));
 
       
        date.setCellValueFactory(
                new PropertyValueFactory<>("DateReclamation"));
 
        
        email.setCellValueFactory(
                new PropertyValueFactory<>("EmailUser"));
        
        
        
        nom.setCellValueFactory(
                new PropertyValueFactory<>("nomUser"));
         
 
        tablerec.setItems(data);
    }    

    @FXML
    private void modifierec(MouseEvent event) {
         ReclamationService_1 rs = new ReclamationService_1();
        Reclamation_1 Rc=new Reclamation_1(Integer.parseInt(textid.getText()),texttype.getText(),texttext.getText(),"12/12/2005","aa@aa","ghaith",true);
        rs.Modifier(Rc);
         List<Reclamation_1> lr = rs.Lister();
        
          ObservableList<Reclamation_1> data =
                 FXCollections.observableArrayList(lr); 
          tablerec.setItems(data);
          initForm();
    }

    @FXML
    private void supprimerrec(MouseEvent event) {
        ReclamationService_1 rs = new ReclamationService_1();
        Reclamation_1 r = tablerec.getSelectionModel().getSelectedItem();
        rs.Supprimer(r);
          List<Reclamation_1> lr = rs.Lister();
        
          ObservableList<Reclamation_1> data =
                 FXCollections.observableArrayList(lr); 
          tablerec.setItems(data);
          initForm();
    }

    @FXML
    private void ajouterrec(MouseEvent event) {
        
           DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm");  
           LocalDateTime now = LocalDateTime.now();  
           String date=String.valueOf(now).replace("T", " ");
        
        
        ReclamationService_1 rs = new ReclamationService_1();
        Reclamation_1 Rc=new Reclamation_1(texttype.getText(),texttext.getText(),date,"aa@aa","ghaith",true);
        rs.Ajouter(Rc);
         List<Reclamation_1> lr = rs.Lister();
        
          ObservableList<Reclamation_1> data =
                 FXCollections.observableArrayList(lr); 
          tablerec.setItems(data);
          initForm();
    }

    @FXML
    private void afficherrec(MouseEvent event) {
         Reclamation_1 r = tablerec.getSelectionModel().getSelectedItem();
         textid.setText(String.valueOf(r.getId()));
         texttype.setText(r.getType());
         texttext.setText(r.getTextReclamation());
    }
    
    public void initForm(){
        texttype.setText("");
        texttext.setText("");
    }
    
}
