/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev_java.gui.utilisateur;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javax.swing.JFileChooser;
import pidev_java.entities.Societe;
import pidev_java.services.SocieteService;
import pidev_java.utils.FTPConnection;

/**
 * FXML Controller class
 *
 * @author seifeddine
 */
public class SocieteProfileController implements Initializable {

    @FXML
    private Circle image;
    @FXML
    private Label nomprenom;
    @FXML
    private Label email;
    @FXML
    private Label adresse;
    @FXML
    private Label status;
    @FXML
    private Label vues;
    
    @FXML
    private TextField tf_nom;
    @FXML
    private TextField tf_email;
    @FXML
    private TextField tf_adresse;
    @FXML
    private TextField tf_status;
    @FXML
    private Label pic;
    @FXML
    private Button btn_save;
    private String name;
    private String url;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        Societe s=Societe.getInstance();
        
        Image im = new Image("ftp://user:123456789@192.168.1.52/" + s.getPhoto_de_profile());
        image.setFill(new ImagePattern(im));
        //image.setEffect(new DropShadow(+10d, 0d, +2d, Color.BLACK));
        nomprenom.setText(s.getNom());
        email.setText(s.getEmail());
        adresse.setText(s.getAdresse());
        status.setText(s.getStatus_juridique());
        vues.setText(String.valueOf(s.getViews_nb()));
        pic.setText(s.getPhoto_de_profile());
        //---------------------------------------------------
        tf_nom.setText(s.getNom());
        tf_email.setText(s.getEmail());
        tf_adresse.setText(s.getAdresse());
        tf_status.setText(s.getStatus_juridique());
    }    

    @FXML
    private void Save(ActionEvent event) {
        Societe se=  Societe.getInstance();
        FTPConnection cnx=new FTPConnection();
        //cnx.Upload(url,name);
        boolean test= new SocieteService().UpdateSociete(tf_nom.getText()
        ,tf_email.getText(),tf_adresse.getText(),tf_status.getText(),pic.getText());
        
        Societe s=new Societe(tf_nom.getText(),
        tf_adresse.getText(),tf_email.getText(),pic.getText()
        ,tf_status.getText(),se.getId(),se.getViews_nb());
        Image img = new Image("ftp://user:123456789@192.168.1.52/"+s.getPhoto_de_profile());
        image.setFill(new ImagePattern(img));
        if(test==true){
            Societe.setInstance(s);
            nomprenom.setText(tf_nom.getText());
            email.setText(tf_email.getText());
            adresse.setText(tf_adresse.getText());
            status.setText(tf_status.getText());
            vues.setText(String.valueOf(s.getViews_nb()));  
            image.setFill(new ImagePattern(img));
        }
    }
    
    @FXML
    private void AttachPic(ActionEvent event) {
        JFileChooser chooser= new JFileChooser();
        chooser.showOpenDialog(null);
        File f=chooser.getSelectedFile();
        String file=f.getAbsolutePath();
        name=f.getName();
         
        url=file.replace("\\", "\\\\");
        
        pic.setText(name);
        
    }
}
