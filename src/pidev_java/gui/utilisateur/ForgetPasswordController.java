/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev_java.gui.utilisateur;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import pidev_java.entities.Freelancer;
import pidev_java.entities.Societe;
import pidev_java.services.FreelancerService;
import pidev_java.services.SocieteService;
import pidev_java.utils.JavaMail;

/**
 * FXML Controller class
 *
 * @author seifeddine
 */
public class ForgetPasswordController implements Initializable {

    @FXML
    private VBox vbox;
    @FXML
    private TextField tf_email;
    @FXML
    private Label email_validator;
    @FXML
    private Label compte_validator;
    @FXML
    private Label compte_validator1;
    @FXML
    private Label compte_validator11;
    private Parent fxml;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void SignIn(ActionEvent event) throws IOException {
        String regex = "^(.+)@(.+)$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(tf_email.getText());
        if (matcher.matches() == true) {
            email_validator.setVisible(false);
            Societe s = new SocieteService().getSociete(tf_email.getText());
            if (s.getEmail() != null) {
                fxml = FXMLLoader.load(getClass().getResource("SignIn.fxml"));
                vbox.getChildren().removeAll();
                vbox.getChildren().setAll(fxml);
                try {
                    JavaMail mail = new JavaMail();
                    mail.recipient = tf_email.getText();
                    mail.type = "ForgetPassword";
                    mail.start();

                } catch (Exception ex) {
                    Logger.getLogger(SignUpFreelancerController.class.getName()).log(Level.SEVERE, null, ex);
                }

            } else {
                Freelancer f = new FreelancerService().getFreelancer(tf_email.getText());
                if (f.getEmail() != null) {
                    fxml = FXMLLoader.load(getClass().getResource("SignIn.fxml"));
                    vbox.getChildren().removeAll();
                    vbox.getChildren().setAll(fxml);
                    try {
                        JavaMail mail = new JavaMail();
                        mail.recipient = tf_email.getText();
                        mail.type = "ForgetPassword";
                        mail.start();

                    } catch (Exception ex) {
                        Logger.getLogger(SignUpFreelancerController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } else {
                    compte_validator.setText("Email n'exist pas");
                    compte_validator.setVisible(true);
                }
            }
        } else {
            email_validator.setText("Ce n'est pas un email valide");
            email_validator.setVisible(true);
        }
    }

    @FXML
    private void PasswordForget(MouseEvent event) throws IOException {
        fxml = FXMLLoader.load(getClass().getResource("SignIn.fxml"));
        vbox.getChildren().removeAll();
        vbox.getChildren().setAll(fxml);
    }

}
