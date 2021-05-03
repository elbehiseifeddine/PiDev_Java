/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev_java.gui.demandeStage;

import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javax.swing.JOptionPane;

import pidev_java.entities.DemandeStage;
import pidev_java.entities.Freelancer;
import pidev_java.entities.offreEmploi;
import pidev_java.entities.offreStage;
import pidev_java.services.DemandeEmploiService;
import pidev_java.services.DemandeStageService;

/**
 * FXML Controller class
 *
 * @author ely
 */
public class AjoutDemandeSController implements Initializable {

   
    @FXML
    private TextField tfDescription;
    @FXML
    private TextField tfDomaine;
    @FXML
    private TextField tfEtude;
    @FXML
    private TextArea tflettre;
    @FXML
    private TextField tfDuree;
    @FXML
    private Button btnEnvoyerDemande;
    @FXML
    private TextField tfid;
    @FXML
    private TextField tfType;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void AjoutDemandeS(ActionEvent event) {
        
           Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setHeaderText("Form non valide !");
            alert.setContentText("verifie les champs !");
        Label labelresponse= new Label();
        Freelancer f = Freelancer.getInstance();
        DemandeStageService sd = new DemandeStageService();
      
        
        DemandeStage d = new DemandeStage();
        d.setFreelancer_id(f.getId());
        d.setDate_creation(Date.valueOf(LocalDate.MAX));
        Boolean valid =true;
        if(!(tfDescription.getText().isEmpty()) ||!(tfDescription.getText().length()>=30)){
            d.setDescription(tfDescription.getText());
        }else{
            valid=false;
           
        }
        if(!(tfType.getText().isEmpty())){
           d.setType(tfType.getText()); 
        }else
        {
            valid=false;
        }
        if(!tfDomaine.getText().isEmpty()){
            d.setDomaine(tfDomaine.getText());
        }else
        {
            valid=false;
        }
        if(!tflettre.getText().isEmpty()){
             d.setLettre(tflettre.getText());
        }else{
            valid=false;
        }
            
       if(!tfDuree.getText().isEmpty()){
            String s = tfDuree.getText();
            try {
              
                d.setDuree(Integer.parseInt(s));
           } catch (NumberFormatException  e) {
               valid=false;
           }
       }else
       {
           valid=false;
       }
            if(!tfEtude.getText().isEmpty()){
                
                d.setEtude(tfEtude.getText());
            }
       
       else
       {
           valid=false;
       }
       
       if(valid==true){
           
           d.setOffre_stage_id(Integer.valueOf(tfid.getText()));
          sd.ajouter(d); 
           tfDescription.setText("");
        tfEtude.setText("");
        tfDomaine.setText("");
        tflettre.setText("");
        tfDuree.setText("");
        JOptionPane.showMessageDialog(null, "Demande Envoyer !");
       }else
       {
           alert.showAndWait();
       }
    }
    
     public void setOffreE(offreStage e) {
        
        this.tfid.setText(Integer.toString(e.getId()));
    }
    
}