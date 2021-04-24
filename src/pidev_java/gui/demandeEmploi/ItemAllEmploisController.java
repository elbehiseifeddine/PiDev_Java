/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev_java.gui.demandeEmploi;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import pidev_java.entities.offreEmploi;
import pidev_java.gui.offreEmploi.ConsulterOffreEmploiController;
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

    /**
     * Initializes the controller class.
     * 
     * 
     *
     */
    emploiService ss=new emploiService();
    ConsulterEmploiFreelancerController co;
    private offreEmploi offre;
    
    
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
        
        this.idDTexpr.setText(String.valueOf(os.getDateExpiration()));
     }
    
}
