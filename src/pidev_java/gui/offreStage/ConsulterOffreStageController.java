/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev_java.gui.offreStage;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TabPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;
import pidev_java.entities.offreStage;
import pidev_java.services.stageService;

/**
 * FXML Controller class
 *
 * @author Ghassen Riahi
 */
public class ConsulterOffreStageController implements Initializable {

    @FXML
    private ScrollPane scrolStage;
    @FXML
    private GridPane gridStage;
    
    stageService ss=new stageService();
    @FXML
    private TabPane idTab;
    @FXML
    private Button btnA;
    @FXML
    private ScrollPane scrolStage1;
    @FXML
    private GridPane gridStage1;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    // if  ( idTab.getSelectionModel().select(1)){
        
        scrolStage.setVisible(true);
         gridStage.getChildren().clear();
         int columnMesForm = 0;
        int rowMesForm = 1;
         
        
         try {
            
           List<offreStage> MesOffre=ss.getAll();
            for (int i = 0; i < MesOffre.size(); i++) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/pidev_java/gui/offreStage/item.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();

                ItemController itemController = fxmlLoader.getController();
               
                itemController.setData(MesOffre.get(i),this);

                 if (columnMesForm == 2) {
                    columnMesForm = 0;
                    rowMesForm++;
                }

                gridStage.add(anchorPane, columnMesForm, rowMesForm++); //(child,column,row)
                //set grid width
                gridStage.setMinWidth(Region.USE_COMPUTED_SIZE);
                gridStage.setPrefWidth(Region.USE_COMPUTED_SIZE);
                gridStage.setMaxWidth(Region.USE_PREF_SIZE);

                //set grid height
                gridStage.setMinHeight(Region.USE_COMPUTED_SIZE);
                gridStage.setPrefHeight(Region.USE_COMPUTED_SIZE);
               gridStage.setMaxHeight(Region.USE_PREF_SIZE);

                GridPane.setMargin(anchorPane, new Insets(10));
            }
        } catch (Exception e) {
                        System.out.println(e.getMessage());

         //  }
     }
        // }TODO
    } 
    
    
    public void updateList(){
        
         gridStage.getChildren().clear();
         int columnMesForm = 0;
        int rowMesForm = 1;
         
        
         try {
            
           List<offreStage> MesOffre=ss.getAll();
            for (int i = 0; i < MesOffre.size(); i++) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/pidev_java/gui/offreStage/item.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();

                ItemController itemController = fxmlLoader.getController();
               
                itemController.setData(MesOffre.get(i),this);

                 if (columnMesForm == 2) {
                    columnMesForm = 0;
                    rowMesForm++;
                }

                gridStage.add(anchorPane, columnMesForm, rowMesForm++); //(child,column,row)
                //set grid width
                gridStage.setMinWidth(Region.USE_COMPUTED_SIZE);
                gridStage.setPrefWidth(Region.USE_COMPUTED_SIZE);
                gridStage.setMaxWidth(Region.USE_PREF_SIZE);

                //set grid height
                gridStage.setMinHeight(Region.USE_COMPUTED_SIZE);
                gridStage.setPrefHeight(Region.USE_COMPUTED_SIZE);
               gridStage.setMaxHeight(Region.USE_PREF_SIZE);

                GridPane.setMargin(anchorPane, new Insets(10));
                
            }
        } catch (Exception e) {
                        System.out.println(e.getMessage());

           }
    }

    @FXML
    private void AjouterOffre(MouseEvent event) {
         try {
                 FXMLLoader loader1 = new FXMLLoader ();
                 loader1.setLocation(getClass().getResource("/pidev_java/gui/offreStage/AjoutoffreStage.fxml"));
                
                 Parent  parent = (Parent)loader1.load();
                  Stage stage = new Stage();
                 stage.setScene(new Scene(parent));
                  stage.show();
                   
                  AjoutoffreStageController afc=loader1.getController();
                 afc.init(this);
                
             } catch (IOException ex) {
                 System.out.println("erreur");
             }
    }
    
    
    
}
