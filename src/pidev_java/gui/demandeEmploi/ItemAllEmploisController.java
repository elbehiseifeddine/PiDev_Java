/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev_java.gui.demandeEmploi;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javax.swing.JOptionPane;
import pidev_java.entities.offreEmploi;
import pidev_java.gui.offreEmploi.ConsulterOffreEmploiController;
import pidev_java.gui.offreEmploi.OffreEmploiController;
import pidev_java.services.emploiService;

/**
 * FXML Controller class
 *
 * @author Ghassen Riahi
 */
public class ItemAllEmploisController implements Initializable {

    @FXML
    private Label idNom;
    @FXML
    private Label idComp;
    @FXML
    private Label idDesc;
    @FXML
    private Label idSalaire;
    @FXML
    private Label idDTexpr;
    @FXML
    private Label idDomaine;
 @FXML
    private Button btnenvoyer;

    /**
     * Initializes the controller class.
     * 
     * 
     *
     */
    emploiService ss=new emploiService();
    ConsulterEmploiFreelancerController co;
    private offreEmploi offre;
    @FXML
    private Label idDevise;
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }   
    
    public void setData(offreEmploi os,ConsulterEmploiFreelancerController fc) {
         this.co=fc;
        this.offre = os;
        
        this.idNom.setText(os.getNomProjet());
        this.idComp.setText(os.getCompetence());
        this.idDesc.setText(os.getDescription());
        this.idDomaine.setText(String.valueOf(os.getDomaine()));
        this.idSalaire.setText(String.valueOf(os.getSalaire()));
       this.idDevise.setText(String.valueOf(os.getDevise()));
        this.idDTexpr.setText(String.valueOf(os.getDateExpiration()));
         
        
     }
    
    
    @FXML
    private void addD(ActionEvent event) {
        
        
          
            try {
            FXMLLoader fxmlLoader = new FXMLLoader();
             
          
               fxmlLoader.setLocation(getClass().getResource("/pidev_java/gui/demandeEmploi/AjoutDemandeE.fxml"));
               Parent parent = fxmlLoader.load();
        Scene Scene = new Scene(parent);
         AjoutDemandeEController ajout = fxmlLoader.getController();
            offreEmploi o = new offreEmploi();
            o.setId(77);
        ajout.setOffreE(o);
            Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        
        window.setScene(Scene);
        window.show();
            
        } catch (IOException ex) {
            Logger.getLogger(DemandeEmploiController.class.getName()).log(Level.SEVERE, null, ex);
        }  
           
           
        
        
        
    } 
    
   
  
    
}