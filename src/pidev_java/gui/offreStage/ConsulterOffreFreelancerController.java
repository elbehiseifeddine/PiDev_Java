/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev_java.gui.offreStage;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import pidev_java.entities.offreStage;
import pidev_java.services.stageService;

/**
 * FXML Controller class
 *
 * @author Ghassen Riahi
 */
public class ConsulterOffreFreelancerController implements Initializable {

    @FXML
    private ScrollPane scrollfree;
    @FXML
    private GridPane gridfree;
     stageService ss=new stageService();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         scrollfree.setVisible(true);
         gridfree.getChildren().clear();
         int columnMesForm = 0;
        int rowMesForm = 1;
        
         try {
            
           List<offreStage> MesOffre=ss.getAll();
            for (int i = 0; i < MesOffre.size(); i++) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/pidev_java/gui/offreStage/itemAllstages.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();

                ItemAllstagesController itemController = fxmlLoader.getController();
               
                itemController.setData(MesOffre.get(i),this);

                 if (columnMesForm == 2) {
                    columnMesForm = 0;
                    rowMesForm++;
                }

                gridfree.add(anchorPane, columnMesForm, rowMesForm++); //(child,column,row)
                //set grid width
                gridfree.setMinWidth(Region.USE_COMPUTED_SIZE);
                gridfree.setPrefWidth(Region.USE_COMPUTED_SIZE);
                gridfree.setMaxWidth(Region.USE_PREF_SIZE);

                //set grid height
                gridfree.setMinHeight(Region.USE_COMPUTED_SIZE);
                gridfree.setPrefHeight(Region.USE_COMPUTED_SIZE);
               gridfree.setMaxHeight(Region.USE_PREF_SIZE);

                GridPane.setMargin(anchorPane, new Insets(10));
            }
        } catch (Exception e) {
                        System.out.println(e.getMessage());

           }
        // TODO
    }    
    
}
