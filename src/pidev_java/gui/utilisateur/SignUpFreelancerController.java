/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev_java.gui.utilisateur;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.prefs.Preferences;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import pidev_java.entities.Freelancer;
import pidev_java.services.FreelancerService;
import pidev_java.utils.JavaMail;

/**
 * FXML Controller class
 *
 * @author seifeddine
 */
public class SignUpFreelancerController implements Initializable {

    @FXML
    private TextField tf_nom;
    @FXML
    private TextField tf_prenom;
    @FXML
    private TextField tf_email;
    @FXML
    private PasswordField tf_pwd;
    @FXML
    private Button btn_signup;
    @FXML
    private Label nom_validator;
    @FXML
    private Label prenom_validator;
    @FXML
    private Label email_validator;
    @FXML
    private Label pwd_validator;
    @FXML
    private Label compte_validator;
    
    
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        String regex = "^(.+)@(.+)$";
        
        btn_signup.setOnAction((e)->{
            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(tf_email.getText());
            if(matcher.matches()==true){
                pwd_validator.setVisible(false);
                nom_validator.setVisible(false);
                prenom_validator.setVisible(false);
                compte_validator.setVisible(false);
                email_validator.setVisible(false);
                if(tf_pwd.getText().length()<3 || tf_nom.getText().length()<3 || tf_prenom.getText().length()<3){
                    if(tf_pwd.getText().length()<3){
                        pwd_validator.setText("Votre Mot De Pass doit comporter au moins 3 caractères");
                        pwd_validator.setVisible(true);
                    }
                    if(tf_nom.getText().length()<3){
                        pwd_validator.setText("Votre Nom doit comporter au moins 3 caractères");
                        pwd_validator.setVisible(true);
                    }
                    if(tf_prenom.getText().length()<3){
                        pwd_validator.setText("Votre Prenom doit comporter au moins 3 caractères");
                        pwd_validator.setVisible(true);
                    }
                    
                }else{
                    String date = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date());
                    Freelancer f= new Freelancer();
                    f.setNom(tf_nom.getText());
                    f.setPrenom(tf_prenom.getText());
                    f.setEmail(tf_email.getText());
                    f.setMot_de_passe(tf_pwd.getText());
                    f.setAdresse("Add Adresse");
                    f.setPhoto_de_profile("img-1.jpg");
                    f.setSexe("Add sexe");
                    f.setCompetences("Add Competence");
                    f.setLangues("Add Langues");
                    f.setCompte_facebook("Add Compte Facebook");
                    f.setCompte_linkedin("Add Compte Linkedin");
                    f.setCompte_twitter("Add Compte Twitter");
                    f.setViews_nb(0);
                    f.setEtat(1);
                    f.setDate_creation(date);
                    
                    boolean test=new FreelancerService().add(f);
                    if(test){
                        try {
                            compte_validator.setText("Compte a été créer, un e-mail a été envoyé pour la vérification");
                            compte_validator.setVisible(true); 
                            JavaMail.sendMail(tf_email.getText(),"EmailConfirmation");
                        } catch (Exception ex) {
                            Logger.getLogger(SignUpFreelancerController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }else{
                        compte_validator.setText("Compte déja exist");
                        compte_validator.setVisible(true); 
                    }
                }
            }else{
                pwd_validator.setVisible(false);
                nom_validator.setVisible(false);
                prenom_validator.setVisible(false);
                compte_validator.setVisible(false);
                email_validator.setText("Ce n'est pas un email valide");
                email_validator.setVisible(true);
                if(tf_pwd.getText().length()<3){
                    pwd_validator.setText("Votre Mot De Pass doit comporter au moins 3 caractères");
                    pwd_validator.setVisible(true);
                }
                if(tf_nom.getText().length()<3){
                    nom_validator.setText("Votre Nom doit comporter au moins 3 caractères");
                    nom_validator.setVisible(true);
                }
                if(tf_prenom.getText().length()<3){
                    prenom_validator.setText("Votre Prenom doit comporter au moins 3 caractères");
                    prenom_validator.setVisible(true);
                }
            }
            
        });
    }

    
    
}
