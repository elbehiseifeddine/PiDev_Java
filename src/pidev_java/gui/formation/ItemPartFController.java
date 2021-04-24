/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev_java.gui.formation;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import pidev_java.entities.Formation;
import pidev_java.entities.Participant;
import pidev_java.services.FormationService;
import pidev_java.services.ParticipantService;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class ItemPartFController implements Initializable {

    @FXML
    private ImageView img;
    @FXML
    private Label labelle;
    @FXML
    private Label description;
    @FXML
    private Label dated;
    @FXML
    private Label datef;
    
      
    private ParticipantService ps=new ParticipantService();
    private FormationController fcontroller;
    private Formation Form;
    @FXML
    private Label domaine;
    @FXML
    private Label montant;
    @FXML
    private Label lieu;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
     public void setData(Formation F,FormationController fc) {
         this.fcontroller=fc;
        this.Form = F;
        labelle.setText(F.getLabelle());
        description.setText(F.getDescription());
        dated.setText(String.valueOf(F.getDateDebut()));
        datef.setText(String.valueOf(F.getDateFin()));
              lieu.setText(F.getLieu());
        domaine.setText(F.getDomaine());
        montant.setText(String.valueOf(F.getMontant()));

        String imgg= F.getImageF();
        String ch="/pidev_java/assets/";
        String imgF= ch+imgg;
        
        Image imageF = new Image(getClass().getResourceAsStream(imgF));
        img.setImage(imageF);
        
    }

   

    @FXML
    private void deletePartE(MouseEvent event) {
         fcontroller.deleteP(this.Form);
    }

    
    
}