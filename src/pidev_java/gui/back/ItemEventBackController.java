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
import pidev_java.entities.EventLoisir;
import pidev_java.services.AdminEventService;

/**
 * FXML Controller class
 *
 * @author ahmed
 */
public class ItemEventBackController implements Initializable {

    @FXML
    private VBox Vboxadd;
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
    @FXML
    private ImageView img;

    private EventLoisir event;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void Approuve(MouseEvent event1) {
        AdminEventService aes = new AdminEventService();
        
        aes.ActivateEvenement(event, Admin.getInstance());
    }

    @FXML
    private void delete(MouseEvent event1) {
        AdminEventService aes = new AdminEventService();
        
        aes.DeactivateEvenement(event);
    }
    
     public void setData(EventLoisir event) {
        this.event = event;
        labelle.setText(event.getLabelle());
        description.setText(event.getDescription());
        dated.setText(String.valueOf(event.getDateDebut()));
        datef.setText(String.valueOf(event.getDateFin()));
           lieu.setText(event.getLieu());
        domaine.setText(event.getDomaine());
        montant.setText(String.valueOf(event.getNbParticipant()));

        String imgg= event.getImageE();
        String ch="/pidev_java/assets/";
        String imgF= ch+imgg;
        
        Image imageF = new Image(getClass().getResourceAsStream(imgF));
        img.setImage(imageF);
        
    }

    
}
