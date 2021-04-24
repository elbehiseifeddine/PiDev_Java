/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev_java.gui.formation;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import pidev_java.entities.Formation;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class FormDetailsController implements Initializable {

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
    
    private Formation F;
    private FormationController fc;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    } 
    public void init(Formation form,FormationController Fc){
        this.F=form;
        this.fc=Fc;
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

    @FXML
    private void ParticiperForm(MouseEvent event) {
        Formation For=new Formation(this.F.getId());
        
        this.fc.participate(For);
    }
    
}
