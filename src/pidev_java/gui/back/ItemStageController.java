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
import javafx.scene.input.MouseEvent;
import pidev_java.entities.Admin;
import pidev_java.entities.offreStage;
import pidev_java.services.AdminEmploiService;
import pidev_java.services.stageService;

/**
 * FXML Controller class
 *
 * @author ahmed
 */
public class ItemStageController implements Initializable {

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
    private FontAwesomeIconView iconEdit;
    @FXML
    private FontAwesomeIconView icondelete;
    @FXML
    private Label id_offre_stage;

    private offreStage offre ;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    public void setData(offreStage os) {
         
        this.offre = os;
        this.id_offre_stage.setText(""+os.getId());
        this.idNom.setText(os.getNomProjet());
        this.idComp.setText(os.getCompetence());
        this.idDesc.setText(os.getDescription()); 
        this.idDomaine.setText(String.valueOf(os.getDomaine()));
        this.idDuree.setText(String.valueOf(os.getDuree()));
        this.idType.setText(String.valueOf(os.getTypeStage()));
        
        this.idDTexpr.setText(String.valueOf(os.getDateExpiration())); 
     }
    @FXML
    private void Approuver(MouseEvent event) {
         AdminEmploiService aes = new AdminEmploiService();
        
        aes.ActivateOffreStage(new stageService().FindById(Integer.parseInt(id_offre_stage.getText())), Admin.getInstance());
    
    }

    @FXML
    private void Supprimer(MouseEvent event) {
        AdminEmploiService aes = new AdminEmploiService();
        aes.DeactivateOffreStage(new stageService().FindById(Integer.parseInt(id_offre_stage.getText())));
        
    }
    
}
