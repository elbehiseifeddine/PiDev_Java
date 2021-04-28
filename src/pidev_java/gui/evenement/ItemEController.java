/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev_java.gui.evenement;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import pidev_java.entities.EventLoisir;
import pidev_java.entities.Formation;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class ItemEController implements Initializable {

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
    
     private EvenementController econtroller;
    private EventLoisir event;
    @FXML
    private Button btnParticiperE;
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
    
      public void setData(EventLoisir event, EvenementController ec) {
         this.econtroller=ec;
        this.event = event;
        labelle.setText(event.getLabelle());
        description.setText(event.getDescription());
        dated.setText(String.valueOf(event.getDateDebut()));
        datef.setText(String.valueOf(event.getDateFin()));

        String imgg= event.getImageE();
        String ch="/pidev_java/assets/";
        String imgF= ch+imgg;
        
        Image imageF = new Image(getClass().getResourceAsStream(imgF));
        img.setImage(imageF);
        
    }

    @FXML
    private void ParticiperE(MouseEvent event) {
//         EventLoisir Ev=new EventLoisir(this.event.getId());
//        
//        this.econtroller.participate(Ev,this.event.getLabelle());
    }
    
}
