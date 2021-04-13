/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev_java.gui.bases;

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
public class BaseGuiSocieteController implements Initializable {

    @FXML
    private Button profile;
    @FXML
    private Circle myCircle;
    @FXML
    private Label nom;
    @FXML
    private Label email;
    @FXML
    private Button btn_home;
    @FXML
    private Button btn_evenments;
    @FXML
    private Button btn_formation;
    @FXML
    private Button btn_emploi;
    @FXML
    private Button btn_emploi1;
    @FXML
    private Button btn_emploi2;
    @FXML
    private Button btn_emploi11;
    @FXML
    private Button btn_reclamation;
    @FXML
    private Button sign_out_btn;
    @FXML
    private ScrollPane scroll_pane;
    private Parent fxml;
    Stage primaryStage;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            nom.setText(Societe.getInstance().getNom());
            email.setText(Societe.getInstance().getEmail());
            Image im=new Image("/pidev_java/assets/"+Societe.getInstance().getPhoto_de_profile(),false);
            myCircle.setFill(new ImagePattern(im));
            System.out.println("SocieteProfile.fxmlaaaaaaaaaaaaaaa");
            fxml = FXMLLoader.load(getClass().getResource("/pidev_java/gui/utilisateur/SocieteProfile.fxml"));
            System.out.println("SocieteProfile.fxml");
            scroll_pane.setContent(fxml);
        } catch (IOException e) {
        }

    }

    @FXML
    private void Deconnect(ActionEvent event) {
        try {
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
            fxml = FXMLLoader.load(getClass().getResource("/pidev_java/gui/utilisateur/SocieteProfile.fxml"));
            scroll_pane.setContent(fxml);
        } catch (IOException e) {

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
    private void OffreEmploi(ActionEvent event) {
        try {
            fxml = FXMLLoader.load(getClass().getResource("/pidev_java/gui/offreEmploi/offreEmploi.fxml"));
            scroll_pane.setContent(fxml);
        } catch (IOException e) {

        }

    }

    @FXML
    private void ConsulterOffreEmploi(ActionEvent event) {
        try {
            fxml = FXMLLoader.load(getClass().getResource("/pidev_java/gui/offreEmploi/consulterOffreEmploi.fxml"));
            scroll_pane.setContent(fxml);
        } catch (IOException e) {

        }

    }

    @FXML
    private void OffreStage(ActionEvent event) {
        try {
            fxml = FXMLLoader.load(getClass().getResource("/pidev_java/gui/offreStage/offreStage.fxml"));
            scroll_pane.setContent(fxml);
        } catch (IOException e) {

        }

    }

    @FXML
    private void ConsulterOffreStage(ActionEvent event) {
        try {
            fxml = FXMLLoader.load(getClass().getResource("/pidev_java/gui/offreStage/consulterOffreStage.fxml"));
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
