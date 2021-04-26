/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev_java.gui.utilisateur;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import pidev_java.entities.Freelancer;
import pidev_java.entities.Societe;
import pidev_java.services.FreelancerService;
import pidev_java.utils.FTPConnection;

/**
 * FXML Controller class
 *
 * @author seifeddine
 */
public class FreelancerProfileController implements Initializable {

    @FXML
    private Circle image;
    @FXML
    private Label nomprenom;
    @FXML
    private Label email;
    @FXML
    private Label adresse;
    @FXML
    private Label competences;
    @FXML
    private Label langues;
    @FXML
    private Label sexe;
    @FXML
    private Label vues;
    @FXML
    private TextField tf_nom;
    @FXML
    private TextField tf_prenom;
    @FXML
    private TextField tf_email;
    @FXML
    private TextField tf_adresse;
    @FXML
    private TextField tf_linkedin;
    @FXML
    private TextField tf_facebook;
    @FXML
    private TextField tf_twitter;
    @FXML
    private ChoiceBox tf_sexe;
    @FXML
    private TextField tf_competance;
    @FXML
    private TextField tf_langues;
    @FXML
    private Button btn_save;
    @FXML
    private Label pic;
    
    private String name;
    private String url;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        Freelancer f = Freelancer.getInstance();
        File file= new File(f.getPhoto_de_profile());
        Image img = new Image("ftp://user:123456789@192.168.1.52/"+f.getPhoto_de_profile());
        image.setFill(new ImagePattern(img));
        
        //image.setEffect(new DropShadow(+10d, 0d, +2d, Color.BLACK));
        System.out.println("SocieteProfile.fxmlaaaaaaaaaaaaaaa");
        nomprenom.setText(f.getNom() + f.getPrenom());
        email.setText(f.getEmail());
        adresse.setText(f.getAdresse());
        competences.setText(f.getCompetences());
        langues.setText(f.getLangues());
        sexe.setText(f.getSexe());
        vues.setText(String.valueOf(f.getViews_nb()));
        pic.setText(f.getPhoto_de_profile());
        //---------------------------------------------------
        tf_nom.setText(f.getNom());
        tf_prenom.setText(f.getPrenom());
        tf_email.setText(f.getEmail());
        tf_adresse.setText(f.getAdresse());
        tf_linkedin.setText(f.getCompte_linkedin());
        tf_facebook.setText(f.getCompte_facebook());
        tf_twitter.setText(f.getCompte_twitter());
        if (f.getSexe().equals("Add sexe")) {
            tf_sexe.setValue("Add sexe");
            tf_sexe.getItems().addAll("Add sexe", "Homme", "Femme");
        } else if (f.getSexe().equals("Homme")) {
            tf_sexe.setValue("Homme");
            tf_sexe.getItems().addAll("Homme", "Femme");
        } else {
            tf_sexe.setValue("Femme");
            tf_sexe.getItems().addAll("Femme", "Homme");
        }

        tf_competance.setText(f.getCompetences());
        tf_langues.setText(f.getLangues());

    }

    @FXML
    private void Save(ActionEvent event) {

        Freelancer fe = Freelancer.getInstance();
        FTPConnection cnx=new FTPConnection();
        cnx.Upload(url,name);
        boolean test = new FreelancerService().UpdateFreelancer(tf_nom.getText(),
                tf_prenom.getText(), tf_email.getText(), tf_adresse.getText(), tf_linkedin.getText(),
                tf_facebook.getText(), tf_twitter.getText(), (String) tf_sexe.getValue(),
                tf_competance.getText(), tf_langues.getText(), pic.getText());

        Freelancer f = new Freelancer(tf_nom.getText(),
                tf_prenom.getText(), tf_adresse.getText(), tf_email.getText(), pic.getText(),
                (String) tf_sexe.getValue(), tf_competance.getText(), tf_langues.getText(),
                tf_facebook.getText(), tf_linkedin.getText(), tf_twitter.getText(), fe.getId(), fe.getViews_nb());
            Image img = new Image("ftp://user:123456789@192.168.1.52/"+f.getPhoto_de_profile());
            image.setFill(new ImagePattern(img));
        if (test == true) {
            Freelancer.setInstance(f);
            nomprenom.setText(tf_nom.getText() + tf_prenom.getText());
            email.setText(tf_email.getText());
            adresse.setText(tf_adresse.getText());
            competences.setText(tf_competance.getText());
            langues.setText(tf_langues.getText());
            sexe.setText((String) tf_sexe.getValue());
            image.setFill(new ImagePattern(img));

        }
    }

    @FXML
    private void AttachPic(ActionEvent event) {
        FileChooser chooser= new FileChooser();
        File file = chooser.showOpenDialog(null);
        
        String files=file.getAbsolutePath();
        name=file.getName();
         
        url=files.replace("\\", "\\\\");
        
        pic.setText(name);
        
    }
}
