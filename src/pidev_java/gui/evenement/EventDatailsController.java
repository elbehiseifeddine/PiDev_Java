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
import pidev_java.gui.formation.FormationController;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class EventDatailsController implements Initializable {

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
    private Button btnParticiperF;
    
    private EventLoisir Event;
    private EvenementController Ec;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    
     public void init(EventLoisir form,EvenementController Fc){
        this.Event=form;
        this.Ec=Fc;
        labelle.setText(form.getLabelle());
        description.setText(form.getDescription());
        dated.setText(String.valueOf(form.getDateDebut()));
        datef.setText(String.valueOf(form.getDateFin()));
          lieu.setText(form.getLieu());
        domaine.setText(form.getDomaine());
        montant.setText(String.valueOf(form.getNbParticipant()));
        

        String imgg= form.getImageE();
        String ch="/pidev_java/assets/";
        String imgF= ch+imgg;
        
        Image imageF = new Image(getClass().getResourceAsStream(imgF));
        img.setImage(imageF);
                
    }

    @FXML
    private void ParticiperForm(MouseEvent event) {
         EventLoisir ev=new EventLoisir(this.Event.getId());
        
        this.Ec.participate(ev,this.Event.getLabelle());
    }
}