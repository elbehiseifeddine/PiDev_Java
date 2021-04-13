/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev_java.gui.utilisateur;

import java.net.URL;
import javafx.fxml.FXML;
import java.io.IOException;
import javafx.fxml.FXMLLoader;
import java.util.ResourceBundle;
import java.util.prefs.BackingStoreException;
import java.util.prefs.Preferences;
import javafx.animation.TranslateTransition;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.layout.VBox;
import javafx.util.Duration;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.stage.Stage;



/**
 * FXML Controller class
 *
 * @author seifeddine
 */
public class MainController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    @FXML
    private VBox vbox;
    
    private Parent fxml;
    
    Stage stage;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        TranslateTransition t= new TranslateTransition(Duration.seconds(1),vbox);
        t.setToX(vbox.getLayoutX()*17);
        t.play();
        t.setOnFinished((e)->{
            try{
                fxml = FXMLLoader.load(getClass().getResource("SignIn.fxml"));
                vbox.getChildren().removeAll();
                vbox.getChildren().setAll(fxml);
            }catch(IOException ex){
                System.out.println(ex);
            }
        });

        
    } 
    
    @FXML
    private void open_signin(ActionEvent event){
          TranslateTransition t = new TranslateTransition(Duration.seconds(1), vbox);
        t.setToX(vbox.getLayoutX() * 17);
        t.play();
        t.setOnFinished((e) ->{
            try{
                fxml = FXMLLoader.load(getClass().getResource("SignIn.fxml"));
                vbox.getChildren().removeAll();
                vbox.getChildren().setAll(fxml);
            }catch(IOException ex){
                System.out.println(ex);
            }
        });
        Preferences userPreferences = Preferences.userRoot();
        userPreferences.put("nom","aaaaaaaaaa");
    } 
    
    @FXML
    private void open_signupFreelancer(ActionEvent event){
          TranslateTransition t = new TranslateTransition(Duration.seconds(1), vbox);
        t.setToX(0);
        t.play();
        t.setOnFinished((e) ->{
            try{
                fxml = FXMLLoader.load(getClass().getResource("SignUpFreelancer.fxml"));
                vbox.getChildren().removeAll();
                vbox.getChildren().setAll(fxml);
            }catch(IOException ex){
                System.out.println(ex);
            }
        });
    } 
    
    @FXML
    private void open_signupSociete(ActionEvent event){
          TranslateTransition t = new TranslateTransition(Duration.seconds(1), vbox);
        t.setToX(0);
        t.play();
        t.setOnFinished((e) ->{
            try{
                fxml = FXMLLoader.load(getClass().getResource("SignUpSociete.fxml"));
                vbox.getChildren().removeAll();
                vbox.getChildren().setAll(fxml);
            }catch(IOException ex){
                System.out.println(ex);
            }
        });
    } 
    
    @FXML
    private void Exit(ActionEvent event){
         stage= (Stage)((Button)event.getSource()).getScene().getWindow();
         stage.close();
    } 
    
    
}
