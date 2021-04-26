/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev_java.gui.offreStage;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import pidev_java.entities.offreStage;
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
    
}