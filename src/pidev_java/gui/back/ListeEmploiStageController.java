/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev_java.gui.back;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import pidev_java.entities.Admin;
import pidev_java.entities.offreEmploi;
import pidev_java.entities.offreStage;
import pidev_java.gui.offreEmploi.ItemEmpController;
import pidev_java.services.AdminEmploiService;
import pidev_java.services.emploiService;

/**
 * FXML Controller class
 *
 * @author ahmed
 */
public class ListeEmploiStageController implements Initializable {

    @FXML
    private Button btnEmploi;
    @FXML
    private Button btnStage;
    @FXML
    private GridPane gridpane;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        loadEmploiData();
    }    
    
    @FXML
    public void loadEmploiData(){
        gridpane.getChildren().clear();
         int columnMesForm = 0;
        int rowMesForm = 1;
         
        
         try {
            
           List<offreEmploi> MesOffre=new AdminEmploiService().historiqueOffreEmploi(Admin.getInstance());
            for (int i = 0; i < MesOffre.size(); i++) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/pidev_java/gui/back/PetitItemEmploi.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();

                PetitItemEmploiController itemController = fxmlLoader.getController();
               
                itemController.setData(MesOffre.get(i),this);

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
                        System.out.println("Load Emploi Data methode ListeEmploiStageController, "+e.getMessage());

           }
    }
    
    @FXML
    public void loadStageData(){
        gridpane.getChildren().clear();
         int columnMesForm = 0;
        int rowMesForm = 1;
         
        
         try {
            
           List<offreStage> MesOffre=new AdminEmploiService().historiqueOffreStage(Admin.getInstance());
            for (int i = 0; i < MesOffre.size(); i++) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/pidev_java/guiback/PetitItemStage.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();

                PetitItemStageController itemController = fxmlLoader.getController();
               
                itemController.setData(MesOffre.get(i),this);

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
                        System.out.println("Load Stage Data methode ListeEmploiStageController, "+e.getMessage());

           }
    }
    
}
