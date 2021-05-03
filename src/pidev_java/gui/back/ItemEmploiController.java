/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev_java.gui.back;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import pidev_java.entities.Admin;
import pidev_java.entities.offreEmploi;
import pidev_java.gui.offreEmploi.ConsulterOffreEmploiController;
import pidev_java.services.AdminEmploiService;
import pidev_java.services.emploiService;

/**
 * FXML Controller class
 *
 * @author ahmed
 */
public class ItemEmploiController implements Initializable {

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
    private FontAwesomeIconView iconEdit;
    @FXML
    private FontAwesomeIconView icondelete;
    @FXML
    private Label idDevise;
    @FXML
    private Label id_Offre_Emploi;

    private offreEmploi offre;
    @FXML
    private VBox Vboxadd;
    @FXML
    private ImageView aa;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
      
    }    

     public void setData(offreEmploi os) {
         
        this.offre = os;
        String t = String.valueOf(os.getIdSociete());
        Image i = new Image("/pidev_java/"+t+".png");
         
        
        this.id_Offre_Emploi.setText(""+os.getId());
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
    private void Approuver(MouseEvent event) {
        AdminEmploiService aes = new AdminEmploiService();
        
        aes.ActivateOffreEmploi(new emploiService().FindById(Integer.parseInt(id_Offre_Emploi.getText())), Admin.getInstance());
    }

    @FXML
    private void Supprimer(MouseEvent event) {
        AdminEmploiService aes = new AdminEmploiService();
        aes.DeactivateOffreEmploi(new emploiService().FindById(Integer.parseInt(id_Offre_Emploi.getText())));
         
    }
    
}
