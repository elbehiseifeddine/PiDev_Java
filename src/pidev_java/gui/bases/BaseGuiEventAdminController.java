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
public class BaseGuiEventAdminController implements Initializable {

    @FXML
    private Button btn_home;
    Stage primaryStage;
    @FXML
    private Button btn_eventformapp;
    @FXML
    private Button btn_eventform;
    @FXML
    private Button btn_statistique;
    @FXML
    private Button sign_out_btn;
    @FXML
    private ScrollPane scroll_pane;
    @FXML
    private Parent fxml; 

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            fxml = FXMLLoader.load(getClass().getResource("/pidev_java/gui/back/AccueilAdminEvenement.fxml"));
            scroll_pane.setContent(fxml);
        } catch (IOException e) {

        }
    }    

    @FXML
    private void Accueil(ActionEvent event) {
        try {
            fxml = FXMLLoader.load(getClass().getResource("/pidev_java/gui/back/AccueilAdminEvenement.fxml"));
            scroll_pane.setContent(fxml);
        } catch (IOException e) {

        }
    }

    @FXML
    private void EventFormApp(ActionEvent event) {
        try {
            fxml = FXMLLoader.load(getClass().getResource("/pidev_java/gui/back/ListEventFormApp.fxml"));
            scroll_pane.setContent(fxml);
        } catch (IOException e) {

        }
    }

    @FXML
    private void EventForm(ActionEvent event) {
        try {
            fxml = FXMLLoader.load(getClass().getResource("/pidev_java/gui/back/ListeEventForm.fxml"));
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
