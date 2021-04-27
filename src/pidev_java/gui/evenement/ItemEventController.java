/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev_java.gui.evenement;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
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
import pidev_java.entities.EventLoisir;
import pidev_java.services.EventService;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class ItemEventController implements Initializable {

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
    
   
    @FXML
    private Label domaine;
    @FXML
    private Label montant;
    @FXML
    private Label lieu;
  
    @FXML
    private FontAwesomeIconView updateEvent;
    
      private EventService fs=new EventService();
    private EvenementController Econtroller;
    private EventLoisir event;
    @FXML
    private FontAwesomeIconView btndeleteE;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
     public void setData(EventLoisir event,EvenementController ec) {
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
                 loader1.setLocation(getClass().getResource("/pidev_java/gui/evenement/AjoutEvent.fxml"));
                
                 Parent  parent = (Parent)loader1.load();
                  Stage stage = new Stage();
                 stage.setScene(new Scene(parent));
                  stage.show();
                   
                  AjoutEventController afc=loader1.getController();
                 afc.initUpdate(this.Econtroller,this.event);
                
             } catch (Exception ex) {
              System.out.println("erreur");
             }
    }

    @FXML
    private void deleteEvent(MouseEvent event) {
       Econtroller.delete(this.event);
    }
   

   
    
}
