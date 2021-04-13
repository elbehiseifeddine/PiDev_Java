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
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;

/**
 * FXML Controller class
 *
 * @author seifeddine
 */
public class BaseGuiOffreAdminController implements Initializable {

    @FXML
    private Button btn_home;
    @FXML
    private Button btn_emploistage;
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
            fxml = FXMLLoader.load(getClass().getResource("/pidev_java/gui/back/AccueilAdminOffre.fxml"));
            scroll_pane.setContent(fxml);
        } catch (IOException e) {

        }
    }    

    @FXML
    private void Accueil(ActionEvent event) {
        try {
            fxml = FXMLLoader.load(getClass().getResource("/pidev_java/gui/back/AccueilAdminOffre.fxml"));
            scroll_pane.setContent(fxml);
        } catch (IOException e) {

        }
    }

    @FXML
    private void ListeEmploiStage(ActionEvent event) {
        try {
            fxml = FXMLLoader.load(getClass().getResource("/pidev_java/gui/back/ListeEmploiStage.fxml"));
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
    }
    
}
