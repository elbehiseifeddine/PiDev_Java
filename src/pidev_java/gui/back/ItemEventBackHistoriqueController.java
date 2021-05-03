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
import pidev_java.entities.EventLoisir;
import pidev_java.services.AdminEventService;

/**
 * FXML Controller class
 *
 * @author ahmed
 */
public class ItemEventBackHistoriqueController implements Initializable {

    @FXML
    private ImageView img;
    @FXML
    private Label labelle;
    @FXML
    private Label dated;
    @FXML
    private Label datef;
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

    private EventLoisir event;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
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
        String ch="ftp://user:123456789@192.168.1.52/";
        String imgF= ch+imgg;
        
        Image imageF = new Image(imgF);
        img.setImage(imageF);
        
    }
}
