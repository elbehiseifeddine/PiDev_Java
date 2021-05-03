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
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import pidev_java.entities.Freelancer;
import pidev_java.entities.Societe;

/**
 * FXML Controller class
 *
 * @author seifeddine
 */
public class BaseGuiSuperAdminController implements Initializable {

    @FXML
    private Button btn_home;
    private Parent fxml;    
    @FXML
    private Button btn_statistique;
    @FXML
    private Button btn_publication;
    @FXML
    private Button btn_listeFreelancers;
    @FXML
    private Button btn_listeSociete;
    @FXML
    private Button sign_out_btn;
    @FXML
    private ScrollPane scroll_pane;
    Stage primaryStage;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            fxml = FXMLLoader.load(getClass().getResource("/pidev_java/gui/back/AccueilSuperAdmin.fxml"));
            scroll_pane.setContent(fxml);
        } catch (IOException e) {

        }
    }    

    @FXML
    private void Accureil(ActionEvent event) {
        try {
            fxml = FXMLLoader.load(getClass().getResource("/pidev_java/gui/back/AccueilSuperAdmin.fxml"));
            scroll_pane.setContent(fxml);
        } catch (IOException e) {

        }
    }

    @FXML
    private void Statistique(ActionEvent event) {
        try {
            fxml = FXMLLoader.load(getClass().getResource("/pidev_java/gui/back/Statistique.fxml"));
            scroll_pane.setContent(fxml);
        } catch (IOException e) {

        }
    }

    @FXML
    private void Publication(ActionEvent event) {
        try {
            fxml = FXMLLoader.load(getClass().getResource("/pidev_java/gui/back/Publication.fxml"));
            scroll_pane.setContent(fxml);
        } catch (IOException e) {

        }
    }

    private void EventForm(ActionEvent event) {
        try {
            fxml = FXMLLoader.load(getClass().getResource("/pidev_java/gui/back/EventFormSuperAdmin.fxml"));
            scroll_pane.setContent(fxml);
        } catch (IOException e) {

        }
    }

    private void DemandeNonApprouve(ActionEvent event) {
        try {
            fxml = FXMLLoader.load(getClass().getResource("/pidev_java/gui/back/DemandeNonAppSuperAdmin.fxml"));
            scroll_pane.setContent(fxml);
        } catch (IOException e) {

        }
    }

    private void OffreNonApprouve(ActionEvent event) {
        try {
            fxml = FXMLLoader.load(getClass().getResource("/pidev_java/gui/back/OffreNonAppSuperAdmin.fxml"));
            scroll_pane.setContent(fxml);
        } catch (IOException e) {

        }
    }

    @FXML
    private void ListeFreelancers(ActionEvent event) {
        try {
            fxml = FXMLLoader.load(getClass().getResource("/pidev_java/gui/back/ListeFreelancersSuperAdmin.fxml"));
            scroll_pane.setContent(fxml);
        } catch (IOException e) {

        }
    }

    @FXML
    private void ListeSociete(ActionEvent event) {
        try {
            fxml = FXMLLoader.load(getClass().getResource("/pidev_java/gui/back/ListeSocieteSuperAdmin.fxml"));
            scroll_pane.setContent(fxml);
        } catch (IOException e) {

        }
    }

    @FXML
    private void Deconnect(ActionEvent event) {
        try {
            

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
    
}
