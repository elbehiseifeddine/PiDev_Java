/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev_java.gui.demandeEmploi;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import pidev_java.entities.offreEmploi;
import pidev_java.gui.offreEmploi.ItemEmpController;
import pidev_java.services.emploiService;

/**
 * FXML Controller class
 *
 * @author Ghassen Riahi
 */
public class ConsulterEmploiFreelancerController implements Initializable {

    @FXML
    private ScrollPane scrollEmploiFreelancer;
    @FXML
    private GridPane gridEmploiFreelancer;
    emploiService ss=new emploiService();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
         scrollEmploiFreelancer.setVisible(true);
         gridEmploiFreelancer.getChildren().clear();
         int columnMesForm = 0;
        int rowMesForm = 1;
         try {
            
           List<offreEmploi> MesOffre=ss.getAll();
            for (int i = 0; i < MesOffre.size(); i++) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/pidev_java/gui/demandeEmploi/itemAllEmplois.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();

                ItemAllEmploisController itemEmpController = fxmlLoader.getController();
               
                itemEmpController.setData(MesOffre.get(i),this);

                 if (columnMesForm == 2) {
                    columnMesForm = 0;
                    rowMesForm++;
                }

                gridEmploiFreelancer.add(anchorPane, columnMesForm, rowMesForm++); //(child,column,row)
                //set grid width
                gridEmploiFreelancer.setMinWidth(Region.USE_COMPUTED_SIZE);
                gridEmploiFreelancer.setPrefWidth(Region.USE_COMPUTED_SIZE);
                gridEmploiFreelancer.setMaxWidth(Region.USE_PREF_SIZE);

                //set grid height
                gridEmploiFreelancer.setMinHeight(Region.USE_COMPUTED_SIZE);
                gridEmploiFreelancer.setPrefHeight(Region.USE_COMPUTED_SIZE);
               gridEmploiFreelancer.setMaxHeight(Region.USE_PREF_SIZE);

                GridPane.setMargin(anchorPane, new Insets(10));
            }
        } catch (Exception e) {
                        System.out.println(e.getMessage());

           }
        // TODO
    }    

    @FXML
    private void Croissant(ActionEvent event) {
         scrollEmploiFreelancer.setVisible(true);
         gridEmploiFreelancer.getChildren().clear();
         int columnMesForm = 0;
        int rowMesForm = 1;
         try {
            
           List<offreEmploi> MesOffre=ss.Trie("Asc");
            for (int i = 0; i < MesOffre.size(); i++) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/pidev_java/gui/demandeEmploi/itemAllEmplois.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();

                ItemAllEmploisController itemEmpController = fxmlLoader.getController();
               
                itemEmpController.setData(MesOffre.get(i),this);

                 if (columnMesForm == 2) {
                    columnMesForm = 0;
                    rowMesForm++;
                }

                gridEmploiFreelancer.add(anchorPane, columnMesForm, rowMesForm++); //(child,column,row)
                //set grid width
                gridEmploiFreelancer.setMinWidth(Region.USE_COMPUTED_SIZE);
                gridEmploiFreelancer.setPrefWidth(Region.USE_COMPUTED_SIZE);
                gridEmploiFreelancer.setMaxWidth(Region.USE_PREF_SIZE);

                //set grid height
                gridEmploiFreelancer.setMinHeight(Region.USE_COMPUTED_SIZE);
                gridEmploiFreelancer.setPrefHeight(Region.USE_COMPUTED_SIZE);
               gridEmploiFreelancer.setMaxHeight(Region.USE_PREF_SIZE);

                GridPane.setMargin(anchorPane, new Insets(10));
            }
        } catch (Exception e) {
                        System.out.println(e.getMessage());

           }
        
    }

    @FXML
    private void decroissant(ActionEvent event) {
        scrollEmploiFreelancer.setVisible(true);
         gridEmploiFreelancer.getChildren().clear();
         int columnMesForm = 0;
        int rowMesForm = 1;
         try {
            
           List<offreEmploi> MesOffre=ss.Trie("Desc");
            for (int i = 0; i < MesOffre.size(); i++) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/pidev_java/gui/demandeEmploi/itemAllEmplois.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();

                ItemAllEmploisController itemEmpController = fxmlLoader.getController();
               
                itemEmpController.setData(MesOffre.get(i),this);

                 if (columnMesForm == 2) {
                    columnMesForm = 0;
                    rowMesForm++;
                }

                gridEmploiFreelancer.add(anchorPane, columnMesForm, rowMesForm++); //(child,column,row)
                //set grid width
                gridEmploiFreelancer.setMinWidth(Region.USE_COMPUTED_SIZE);
                gridEmploiFreelancer.setPrefWidth(Region.USE_COMPUTED_SIZE);
                gridEmploiFreelancer.setMaxWidth(Region.USE_PREF_SIZE);

                //set grid height
                gridEmploiFreelancer.setMinHeight(Region.USE_COMPUTED_SIZE);
                gridEmploiFreelancer.setPrefHeight(Region.USE_COMPUTED_SIZE);
               gridEmploiFreelancer.setMaxHeight(Region.USE_PREF_SIZE);

                GridPane.setMargin(anchorPane, new Insets(10));
            }
        } catch (Exception e) {
                        System.out.println(e.getMessage());

           }
    }
    
}
