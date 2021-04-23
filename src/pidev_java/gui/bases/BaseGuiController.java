/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev_java.gui.bases;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import pidev_java.entities.Freelancer;
import pidev_java.entities.Societe;

/**
 * FXML Controller class
 *
 * @author seifeddine
 */
public class BaseGuiController implements Initializable {

    @FXML
    private Circle myCircle;
    @FXML
    private Label nom;

    Stage primaryStage;

    @FXML
    private Label email;

    @FXML
    private ScrollPane scroll_pane;
    private Parent fxml;
    @FXML
    private Button btn_home;
    @FXML
    private Button btn_evenments;
    @FXML
    private Button btn_formation;
    @FXML
    private Button btn_emploi;
    @FXML
    private Button btn_stage;
    @FXML
    private Button btn_demande;
    @FXML
    private Button btn_reclamation;
    @FXML
    private Button sign_out_btn;
    @FXML
    private Button profile;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            nom.setText(Freelancer.getInstance().getNom());
            email.setText(Freelancer.getInstance().getEmail());
            File file= new File(Freelancer.getInstance().getPhoto_de_profile());
            Image img = new Image(file.toURI().toString());
            myCircle.setFill(new ImagePattern(img));
            System.out.println("FreelancerProfile.fxmlaaaaaaaaaaaaaaa");
            fxml = FXMLLoader.load(getClass().getResource("/pidev_java/gui/utilisateur/FreelancerProfile.fxml"));
            System.out.println("FreelancerProfile.fxml");
            scroll_pane.setContent(fxml);

        } catch (IOException e) {
            System.out.println(e);
        }
    }

    @FXML
    private void Deconnect(ActionEvent event) {
        try {
            Freelancer.cleanFreelancer();
            Societe.cleanSociete();

            Image img = new Image("pidev_java/assets/Logo_Compact.png");
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/pidev_java/gui/utilisateur/Main.fxml"));
            Parent root = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            primaryStage.close();
            Scene scene = new Scene(root);
            scene.setFill(Color.TRANSPARENT);
            stage.setScene(scene);
            stage.initStyle(StageStyle.TRANSPARENT);
            stage.getIcons().add(img);

            stage.show();

        } catch (IOException e) {

        }
    }

    @FXML
    private void Profile(ActionEvent event) {
        try {
            fxml = FXMLLoader.load(getClass().getResource("/pidev_java/gui/utilisateur/FreelancerProfile.fxml"));
            scroll_pane.setContent(fxml);
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    @FXML
    private void Reclamation(ActionEvent event) {
        try {
            fxml = FXMLLoader.load(getClass().getResource("/pidev_java/gui/reclamation/Reclamation.fxml"));
            scroll_pane.setContent(fxml);
        } catch (IOException e) {

        }
    }

    @FXML
    private void Evenement(ActionEvent event) {
        try {
            fxml = FXMLLoader.load(getClass().getResource("/pidev_java/gui/evenement/Evenement.fxml"));
            scroll_pane.setContent(fxml);
        } catch (IOException e) {

        }

    }

    @FXML
    private void Formation(ActionEvent event) {
        try {
            fxml = FXMLLoader.load(getClass().getResource("/pidev_java/gui/formation/Formation.fxml"));
            scroll_pane.setContent(fxml);
        } catch (IOException e) {

        }

    }

    @FXML
    private void DemandeEmploi(ActionEvent event) {
        try {
            fxml = FXMLLoader.load(getClass().getResource("/pidev_java/gui/demandeEmploi/demandeEmploi.fxml"));
            scroll_pane.setContent(fxml);
        } catch (IOException e) {

        }

    }

    @FXML
    private void DemandeStage(ActionEvent event) {
        try {
            fxml = FXMLLoader.load(getClass().getResource("/pidev_java/gui/demandeStage/demandeStage.fxml"));
            scroll_pane.setContent(fxml);
        } catch (IOException e) {

        }

    }

    @FXML
    private void Publication(ActionEvent event) {
        try {
            fxml = FXMLLoader.load(getClass().getResource("/pidev_java/gui/publication/Publications.fxml"));
            scroll_pane.setContent(fxml);
        } catch (IOException e) {

        }

    }

}
