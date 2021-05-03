/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev_java.gui.formation;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
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
    @FXML
    private FontAwesomeIconView id_reclamation;
    @FXML
    private FontAwesomeIconView id_payer;

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
        String ch="ftp://user:123456789@192.168.1.52/";
        String imgF= ch+imgg;
        
        Image imageF = new Image(imgF);
        img.setImage(imageF);
        
    }

   

    @FXML
    private void deletePartE(MouseEvent event) {
         fcontroller.deleteP(this.Form);
    }

    @FXML
    private void reclamation(MouseEvent event) {
        try {
                 FXMLLoader loader1 = new FXMLLoader ();
                 loader1.setLocation(getClass().getResource("/pidev_java/gui/reclamation/Reclamation.fxml"));
                
                 Parent  parent = (Parent)loader1.load();
                  Stage stage = new Stage();
                 stage.setScene(new Scene(parent));
                  stage.show();
                   
                
             } catch (IOException ex) {
                 System.out.println("erreur");
                
//                ReclamationController controller = loader.getController();
//                controller.SetIdOffreEmploi(idEmploi.gettext);
    }
    }

    @FXML
    private void payer(MouseEvent event) {
        try {
                 FXMLLoader loader1 = new FXMLLoader ();
                 loader1.setLocation(getClass().getResource("/pidev_java/gui/formation/payer.fxml"));
                
                 Parent  parent = (Parent)loader1.load();
                  Stage stage = new Stage();
                 stage.setScene(new Scene(parent));
                  stage.show();
                   
                
             } catch (IOException ex) {
                 System.out.println("erreur");
                
//                ReclamationController controller = loader.getController();
//                controller.SetIdOffreEmploi(idEmploi.gettext);
    }
    }

    
    
}
