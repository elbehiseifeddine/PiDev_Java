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
import pidev_java.entities.Formation;
import pidev_java.gui.formation.FormationController;
import pidev_java.services.AdminEventService;

/**
 * FXML Controller class
 *
 * @author ahmed
 */
public class ItemFormationBackController implements Initializable {

    @FXML
    private VBox Vboxadd;
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
    private FontAwesomeIconView updateEvent;
    @FXML
    private FontAwesomeIconView btndeleteE;

    private Formation form;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void Approuve(MouseEvent event) {
        AdminEventService aes = new AdminEventService();
        aes.ActivateFormation(form,Admin.getInstance());
    }

    @FXML
    private void delete(MouseEvent event) {
        AdminEventService aes = new AdminEventService();
        aes.DeactivateFormation(form);
    }
     public void setData(Formation form) {
        this.form = form;
        labelle.setText(form.getLabelle());
        description.setText(form.getDescription());
        dated.setText(String.valueOf(form.getDateDebut()));
        datef.setText(String.valueOf(form.getDateFin()));
          lieu.setText(form.getLieu());
        domaine.setText(form.getDomaine());
        montant.setText(String.valueOf(form.getMontant()));
        

        String imgg= form.getImageF();
        String ch="/pidev_java/assets/";
        String imgF= ch+imgg;
        
        Image imageF = new Image(getClass().getResourceAsStream(imgF));
        img.setImage(imageF);
        
    }
    
}
