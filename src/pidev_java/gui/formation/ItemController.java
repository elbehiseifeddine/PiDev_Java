/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev_java.gui.formation;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import java.io.IOException;
import pidev_java.gui.evenement.*;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import pidev_java.entities.EventLoisir;
import pidev_java.entities.Formation;
import pidev_java.services.FormationService;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class ItemController implements Initializable {

    private Formation form;
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
    private FormationService fs=new FormationService();
    private FormationController fcontroller;
    @FXML
    private Label lieu;
    @FXML
    private Label domaine;
    @FXML
    private Label montant;
    @FXML
    private FontAwesomeIconView deleteForm;
    @FXML
    private FontAwesomeIconView updateForm;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    public void setData(Formation form,FormationController fc) {
         this.fcontroller=fc;
        this.form = form;
        labelle.setText(form.getLabelle());
        description.setText(form.getDescription());
        dated.setText(String.valueOf(form.getDateDebut()));
        datef.setText(String.valueOf(form.getDateFin()));
        lieu.setText(form.getLieu());
        domaine.setText(form.getDomaine());
        montant.setText(String.valueOf(form.getMontant()));
        
        String imgg= form.getImageF();
        String ch="ftp://user:123456789@192.168.1.52/";
        String imgF= ch+imgg;
        
        Image imageF = new Image(imgF);
        img.setImage(imageF);


        
    }

    @FXML
    private void deleteForm(MouseEvent event) {

           
           
            fcontroller.delete(this.form);
            
            
            
            
      
            
       
        
    }

    @FXML
    private void updateForm(MouseEvent event) {
         try {
                 FXMLLoader loader1 = new FXMLLoader ();
                 loader1.setLocation(getClass().getResource("/pidev_java/gui/formation/AjoutFormation.fxml"));
                
                 Parent  parent = (Parent)loader1.load();
                  Stage stage = new Stage();
                 stage.setScene(new Scene(parent));
                  stage.show();
                   
                  AjoutFormationController afc=loader1.getController();
                 afc.initUpdate(this.fcontroller,this.form);
                
             } catch (IOException ex) {
                 Logger.getLogger(FormationController.class.getName()).log(Level.SEVERE, null, ex);
             }
        
    }

  
    
}
