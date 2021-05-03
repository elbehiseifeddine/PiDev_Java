/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev_java.gui.utilisateur;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import pidev_java.entities.Admin;
import pidev_java.entities.Freelancer;
import pidev_java.entities.Societe;
import pidev_java.services.AdminService;
import pidev_java.services.FreelancerService;
import pidev_java.services.SocieteService;

/**
 * FXML Controller class
 *
 * @author seifeddine
 */
public class SignInController implements Initializable {

    Stage primaryStage;
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
    @FXML
    private VBox vbox;
    private Parent fxml;
    @FXML
    private PasswordField tf_pwd;
    @FXML
    private Label pwd_validator;

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
            pwd_validator.setVisible(false);
            compte_validator.setVisible(false);
            if (tf_pwd.getText().length() < 3) {
                pwd_validator.setText("Votre Mot De Pass doit comporter au moins 3 caractères");
                pwd_validator.setVisible(true);
            } else {
                System.err.println(tf_email.getText());
                Societe s = new SocieteService().getSociete(tf_email.getText());
                if (s.getEmail() != null) {
                    if (s.getMot_de_pass().equals(tf_pwd.getText())) {
                        if (s.getEtat() == 0) {
                            Societe.setInstance(s);
                            try {
                                Image img = new Image("pidev_java/assets/Logo_Compact.png");
                                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/pidev_java/gui/bases/BaseGuiSociete.fxml"));
                                Parent root = (Parent) fxmlLoader.load();
                                Stage stage = new Stage();
                                primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                                primaryStage.close();
                                stage.setScene(new Scene(root));
                                stage.setMaximized(true);
                                stage.getIcons().add(img);
                                stage.setTitle("RightJob");
                                stage.show();
                            } catch (IOException e) {
                                System.err.println(e);
                            }
                        } else {
                            compte_validator.setText("Compte Désactiver");
                            compte_validator.setVisible(true);
                        }

                    } else {
                        compte_validator.setText("Mot de passe est incorrect");
                        compte_validator.setVisible(true);
                    }
                } else {
                    Freelancer f = new FreelancerService().getFreelancer(tf_email.getText());
                    if (f.getEmail() != null) {
                        if (f.getMot_de_passe().equals(tf_pwd.getText())) {
                            if (f.getEtat() == 0) {
                                Freelancer.setInstance(f);
                                try {
                                    Image img = new Image("pidev_java/assets/Logo_Compact.png");
                                    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/pidev_java/gui/bases/BaseGui.fxml"));
                                    Parent root = (Parent) fxmlLoader.load();
                                    Stage stage = new Stage();
                                    primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                                    primaryStage.close();
                                    stage.setScene(new Scene(root));
                                    stage.setMaximized(true);
                                    stage.getIcons().add(img);
                                    stage.setTitle("RightJob");
                                    stage.show();
                                } catch (IOException e) {
                                    System.err.println(e);
                                }
                            } else {
                                compte_validator.setText("Compte Desactiver");
                                compte_validator.setVisible(true);
                            }

                        } else {
                            compte_validator.setText("Mot de passe est incorrect");
                            compte_validator.setVisible(true);
                        }
                    } else {
                        Admin a = new AdminService().findByEmail(tf_email.getText());
                        if (a.getLogin() != null) {
                            if (a.getPass().equals(tf_pwd.getText())) {
                                if (a.isEtat() == true) {
                                    Admin.setInstance(a);
                                    if (a.getType().equals("Admin des reclamations")) {
                                        try {
                                            Image img = new Image("pidev_java/assets/Logo_Compact.png");
                                            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/pidev_java/gui/bases/BaseGuiReclamationAdmin.fxml"));
                                            Parent root = (Parent) fxmlLoader.load();
                                            Stage stage = new Stage();
                                            primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                                            primaryStage.close();
                                            stage.setScene(new Scene(root));
                                            stage.setMaximized(true);
                                            stage.getIcons().add(img);
                                            stage.setTitle("RightJob");
                                            stage.show();
                                        } catch (IOException e) {
                                            System.err.println(e);
                                        }
                                    } else if (a.getType().equals("Admin des events")) {
                                        try {
                                            Image img = new Image("pidev_java/assets/Logo_Compact.png");
                                            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/pidev_java/gui/bases/BaseGuiEventAdmin.fxml"));
                                            Parent root = (Parent) fxmlLoader.load();
                                            Stage stage = new Stage();
                                            primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                                            primaryStage.close();
                                            stage.setScene(new Scene(root));
                                            stage.setMaximized(true);
                                            stage.getIcons().add(img);
                                            stage.setTitle("RightJob");
                                            stage.show();
                                            
                                        } catch (IOException e) {
                                            System.err.println(e);
                                        }
                                    } else if (a.getType().equals("Admin des emplois")) {
                                        try {
                                            Image img = new Image("pidev_java/assets/Logo_Compact.png");
                                            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/pidev_java/gui/bases/BaseGuiOffreAdmin.fxml"));
                                            Parent root = (Parent) fxmlLoader.load();
                                            Stage stage = new Stage();
                                            primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                                            primaryStage.close();
                                            stage.setScene(new Scene(root));
                                            stage.setMaximized(true);
                                            stage.getIcons().add(img);
                                            stage.setTitle("RightJob");
                                            stage.show();
                                        } catch (IOException e) {
                                            System.err.println(e);
                                        }
                                    }
                                } else {
                                    compte_validator.setText("Compte Desactiver");
                                    compte_validator.setVisible(true);
                                }
                            }
                        } else {
                            if (new AdminService().findSuperAdminByEmail(tf_email.getText()).getLogin() != null) {
                                if (new AdminService().findSuperAdminByEmail(tf_email.getText()).getPass().equals(tf_pwd.getText())) {
                                    try {
                                        Image img = new Image("pidev_java/assets/Logo_Compact.png");
                                        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/pidev_java/gui/bases/BaseGuiSuperAdmin.fxml"));
                                        Parent root = (Parent) fxmlLoader.load();
                                        Stage stage = new Stage();
                                        primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                                        primaryStage.close();
                                        stage.setScene(new Scene(root));
                                        stage.setMaximized(true);
                                        stage.getIcons().add(img);
                                        stage.setTitle("RightJob");
                                        stage.show();
                                    } catch (IOException e) {
                                        System.err.println(e);
                                    }
                                }
                            } else {
                                compte_validator.setText("Email n'exist pas");
                                compte_validator.setVisible(true);
                            }
                        }
                    }
                }
            }

        } else {
            pwd_validator.setVisible(false);
            compte_validator.setVisible(false);
            email_validator.setText("Ce n'est pas un email valide");
            email_validator.setVisible(true);
            if (tf_pwd.getText().length() < 3) {
                pwd_validator.setText("Votre Mot De Pass doit comporter au moins 3 caractères");
                pwd_validator.setVisible(true);
            }
        }
    }

    @FXML
    private void PasswordForget(MouseEvent event) throws IOException {
        fxml = FXMLLoader.load(getClass().getResource("ForgetPassword.fxml"));
        vbox.getChildren().removeAll();
        vbox.getChildren().setAll(fxml);
    }

}
