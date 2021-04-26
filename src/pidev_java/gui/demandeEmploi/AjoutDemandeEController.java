/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev_java.gui.demandeEmploi;

import java.io.IOException;
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
import pidev_java.entities.DemandeEmploi;
import pidev_java.entities.Freelancer;
import pidev_java.entities.offreEmploi;
import pidev_java.services.DemandeEmploiService;

/**
 * FXML Controller class
 *
 * @author ely
 */
public class AjoutDemandeEController implements Initializable {

    @FXML
    private TextField tfDescription;
    @FXML
    private TextField tfDomaine;
    @FXML
    private TextField tfDiplome;
     @FXML
    private TextField tfid_o;
    @FXML
    private TextArea tflettre;
    @FXML
    private TextField tfSalaire;
    @FXML
    private Button btnEnvoyerDemande;
    @FXML
    private Button btnFile;
     @FXML
    private VBox Vboxadd ;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
      
    }    

    
    @FXML
    private void AjoutDemandeE(ActionEvent event)throws IOException  {
          Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setHeaderText("Form non valide !");
            alert.setContentText("verifie les champs !");
        Label labelresponse= new Label();
        Freelancer f = Freelancer.getInstance();
        DemandeEmploiService sd = new DemandeEmploiService();
      
        
        DemandeEmploi d = new DemandeEmploi();
        d.setFreelancer_id(f.getId());
        d.setDate_creation(Date.valueOf(LocalDate.MAX));
        Boolean valid =true;
        if(!(tfDescription.getText().isEmpty()) ||!(tfDescription.getText().length()>=30)){
            d.setDescription(tfDescription.getText());
        }else{
            valid=false;
            labelresponse.setText("la description entrée est non valide");
            Vboxadd.getChildren().add(labelresponse);
        }
        if(!(tfDiplome.getText().isEmpty())){
           d.setDiplome(tfDiplome.getText()); 
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
            
       if(!tfSalaire.getText().isEmpty()){
            String s = tfSalaire.getText();
            try {
              
                d.setSalaire(Float.parseFloat(s));
           } catch (NumberFormatException  e) {
               valid=false;
           }
       
       }else
       {
           valid=false;
       }
       
       if(valid==true){
           
           d.setOffre_emploi_id(Integer.valueOf(tfid_o.getText()));
          sd.ajouter(d); 
           tfDescription.setText("");
        tfDiplome.setText("");
        tfDomaine.setText("");
        tflettre.setText("");
        tfSalaire.setText("");
        JOptionPane.showMessageDialog(null, "Demande Envoyer !");
       }else
       {
           alert.showAndWait();
       }
        
       
        
    }
    
    public void setOffreE(offreEmploi e) throws NullPointerException{
        
        this.tfid_o.setText(Integer.toString(e.getId()));
    }
}
