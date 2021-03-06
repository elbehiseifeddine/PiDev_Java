package pidev_java.gui.back;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
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
import pidev_java.entities.EventLoisir;
import pidev_java.gui.evenement.AjoutEventController;
import pidev_java.gui.evenement.EvenementController;
import pidev_java.gui.formation.FormationController;
import pidev_java.services.EventService;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class ItemEBackController implements Initializable {

    @FXML
    private ImageView img;
    @FXML
    private Label labelle;
    @FXML
    private Label dated;
    @FXML
    private Label datef;
    @FXML
    private FontAwesomeIconView updateEvent;
    @FXML
    private Label description;
    @FXML
    private Label domaine;
    @FXML
    private Label montant;
    @FXML
    private Label lieu;
    @FXML
    private FontAwesomeIconView btndeleteE;
     private EventService fs=new EventService();
    private ListeEventFormController Econtroller;
    private EventLoisir event;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
     public void setData(EventLoisir event,ListeEventFormController ec) {
         this.Econtroller=ec;
        this.event = event;
        labelle.setText(event.getLabelle());
        description.setText(event.getDescription());
        dated.setText(String.valueOf(event.getDateDebut()));
        datef.setText(String.valueOf(event.getDateFin()));
           lieu.setText(event.getLieu());
        domaine.setText(event.getDomaine());
        montant.setText(String.valueOf(event.getNbParticipant()));

        String imgg= event.getImageE();
        String ch="ftp://user:123456789@192.168.1.52/";
        String imgF= ch+imgg;
        
        Image imageF = new Image(imgF);
        img.setImage(imageF);
        
    }
    

    @FXML
    private void updateEvent(MouseEvent event) {
        
            try {
                 FXMLLoader loader1 = new FXMLLoader ();
                 loader1.setLocation(getClass().getResource("/pidev_java/gui/back/AjoutEventBack.fxml"));
                
                 Parent  parent = (Parent)loader1.load();
                  Stage stage = new Stage();
                 stage.setScene(new Scene(parent));
                  stage.show();
                   
                  AjoutEventBackController afc=loader1.getController();
                 afc.initUpdate(this.Econtroller,this.event);
                
             } catch (Exception ex) {
              System.out.println("erreur");
             }
    }

    @FXML
    private void deleteEvent(MouseEvent event) {
          Econtroller.deleteEvent(this.event);
    }

    private void shareFb(MouseEvent event) {
            try {
                 FXMLLoader loader1 = new FXMLLoader ();
                 loader1.setLocation(getClass().getResource("/pidev_java/gui/back/Sharefb.fxml"));
                
                 Parent  parent = (Parent)loader1.load();
                  Stage stage = new Stage();
                 stage.setScene(new Scene(parent));
                  stage.show();
                   
                  SharefbController afc=loader1.getController();
                  String cont=this.event.getLabelle()+"\n "+this.event.getDescription();
                  System.out.println(this.event.getImageE());
                  afc.setData(cont,this.event.getImageE());
                
             } catch (IOException ex) {
                 Logger.getLogger(FormationController.class.getName()).log(Level.SEVERE, null, ex);
             }
        
    }
    
}