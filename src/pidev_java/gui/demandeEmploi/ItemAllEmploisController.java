/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev_java.gui.demandeEmploi;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import org.controlsfx.control.Rating;
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
    @FXML
    private Label idDevise;
    @FXML
    private Rating ratingEmploi;
    @FXML
    private ImageView aa;
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }   
    
    public void setData(offreEmploi os,ConsulterEmploiFreelancerController fc) {
         this.co=fc;
        this.offre = os;
        
          String t = String.valueOf(os.getIdSociete());
        System.out.println("/pidev_java/"+t+".png");
        Image i = new Image("/pidev_java/"+t+".png");
        
        this.idNom.setText(os.getNomProjet());
        this.idComp.setText(os.getCompetence());
        this.idDesc.setText(os.getDescription());
        this.idDomaine.setText(String.valueOf(os.getDomaine()));
        this.idSalaire.setText(String.valueOf(os.getSalaire()));
       this.idDevise.setText(String.valueOf(os.getDevise()));
        this.idDTexpr.setText(String.valueOf(os.getDateExpiration()));
        this.aa.setImage(i);
     }

    @FXML
    private void Rating(MouseEvent event) {
        this.ratingEmploi.getRating();
    }
    
}
