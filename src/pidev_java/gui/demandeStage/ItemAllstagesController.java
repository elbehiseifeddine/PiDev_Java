/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev_java.gui.demandeStage;

import java.io.IOException;
import pidev_java.gui.offreStage.*;
import java.net.URL;
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
import javafx.scene.control.Label;
import javafx.stage.Stage;
import pidev_java.entities.offreEmploi;
import pidev_java.entities.offreStage;
import pidev_java.gui.demandeEmploi.AjoutDemandeEController;
import pidev_java.gui.demandeEmploi.DemandeEmploiController;
import pidev_java.services.stageService;

/**
 * FXML Controller class
 *
 * @author Ghassen Riahi
 */
public class ItemAllstagesController implements Initializable {
    stageService ss=new stageService();
    ConsulterOffreFreelancerController co;
    private offreStage offre;

    @FXML
    private Label idNom;
    @FXML
    private Label idComp;
    @FXML
    private Label idDesc;
    @FXML
    private Label idDuree;
    @FXML
    private Label idType;
    @FXML
    private Label idDTexpr;
    @FXML
    private Label idDomaine;
@FXML
    private Button btnenvoyer;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        // TODO
    }    
    
    public void setData(offreStage os,ConsulterOffreFreelancerController fc) {
         this.co=fc;
        this.offre = os;
        
        this.idNom.setText(os.getNomProjet());
        this.idComp.setText(os.getCompetence());
        this.idDesc.setText(os.getDescription());
        this.idDomaine.setText(String.valueOf(os.getDomaine()));
        this.idDuree.setText(String.valueOf(os.getDuree()));
        this.idType.setText(String.valueOf(os.getTypeStage()));
        this.idDTexpr.setText(String.valueOf(os.getDateExpiration()));
    }
    
     @FXML
    private void addD(ActionEvent event) {
        
        
          
            try {
            FXMLLoader fxmlLoader = new FXMLLoader();
             
          
               fxmlLoader.setLocation(getClass().getResource("/pidev_java/gui/demandeStage/AjoutDemandeS.fxml"));
               Parent parent = fxmlLoader.load();
        Scene Scene = new Scene(parent);
         AjoutDemandeSController ajout = fxmlLoader.getController();
                System.out.println("aaaaaaaa");
            
        ajout.setOffreE(offre);
            Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        
        window.setScene(Scene);
        window.show();
            
        } catch (IOException ex) {
            Logger.getLogger(DemandeStageController.class.getName()).log(Level.SEVERE, null, ex);
        }  
           
           
        
        
        
    } 
    
}