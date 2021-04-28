/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev_java.gui.back;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import pidev_java.entities.Admin;
import pidev_java.entities.EventLoisir;
import pidev_java.entities.Formation;
import pidev_java.services.AdminEventService;

/**
 * FXML Controller class
 *
 * @author ahmed
 */
public class ListEventFormAppController implements Initializable {

    @FXML
    private Button btnEvenement;
    @FXML
    private Button btnFormation;
    @FXML
    private GridPane gridpane;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        loadEventData();
    }    

    @FXML
    private void loadEventData() {
        gridpane.getChildren().clear();
         int columnMesForm = 0;
        int rowMesForm = 1;
         
        
         try {
            
             ArrayList<EventLoisir> Events=new AdminEventService().historiqueEvenement(Admin.getInstance());
            for (int i = 0; i < Events.size(); i++) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/pidev_java/gui/back/ItemEventBackHistorique.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();

                ItemEventBackHistoriqueController itemController = fxmlLoader.getController();
               
                itemController.setData(Events.get(i));

                 if (columnMesForm == 2) {
                    columnMesForm = 0;
                    rowMesForm++;
                }

                gridpane.add(anchorPane, columnMesForm, rowMesForm++); //(child,column,row)
                //set grid width
                gridpane.setMinWidth(Region.USE_COMPUTED_SIZE);
                gridpane.setPrefWidth(Region.USE_COMPUTED_SIZE);
                gridpane.setMaxWidth(Region.USE_PREF_SIZE);

                //set grid height
                gridpane.setMinHeight(Region.USE_COMPUTED_SIZE);
                gridpane.setPrefHeight(Region.USE_COMPUTED_SIZE);
               gridpane.setMaxHeight(Region.USE_PREF_SIZE);

                GridPane.setMargin(anchorPane, new Insets(10));
                
            }
        } catch (Exception e) {
                        System.out.println(e.getMessage());

           }
        
    }

    @FXML
    private void loadFormationData(ActionEvent event) {
        
        gridpane.getChildren().clear();
         int columnMesForm = 0;
        int rowMesForm = 1;
         
        
         try {
            
             ArrayList<Formation> Formations=new AdminEventService().historiqueFormation(Admin.getInstance());
            for (int i = 0; i < Formations.size(); i++) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/pidev_java/gui/back/ItemFormationHistorique.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();

                ItemFormationHistoriqueController itemController = fxmlLoader.getController();
               
                itemController.setData(Formations.get(i));

                 if (columnMesForm == 2) {
                    columnMesForm = 0;
                    rowMesForm++;
                }

                gridpane.add(anchorPane, columnMesForm, rowMesForm++); //(child,column,row)
                //set grid width
                gridpane.setMinWidth(Region.USE_COMPUTED_SIZE);
                gridpane.setPrefWidth(Region.USE_COMPUTED_SIZE);
                gridpane.setMaxWidth(Region.USE_PREF_SIZE);

                //set grid height
                gridpane.setMinHeight(Region.USE_COMPUTED_SIZE);
                gridpane.setPrefHeight(Region.USE_COMPUTED_SIZE);
               gridpane.setMaxHeight(Region.USE_PREF_SIZE);

                GridPane.setMargin(anchorPane, new Insets(10));
                
            }
        } catch (Exception e) {
                        System.out.println(e.getMessage());

           }
    }
    
}
