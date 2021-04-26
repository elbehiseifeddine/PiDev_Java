/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev_java.gui.back;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;
import pidev_java.entities.Admin;
import pidev_java.entities.offreEmploi;
import pidev_java.entities.offreStage;
import pidev_java.services.AdminEmploiService;
import pidev_java.services.SocieteService;

/**
 * FXML Controller class
 *
 * @author ahmed
 */
public class AccueilAdminOffreController implements Initializable {

    @FXML
    private TableColumn<offreEmploi, Integer> Id_Offre_Emploi;
    @FXML
    private TableView<offreEmploi> TabOffreEmploi;
    @FXML
    private TableColumn<offreEmploi, String> ownerEmploi;
    @FXML
    private TableColumn<offreEmploi, String> nomProjetEmploi;
    @FXML
    private TableColumn<offreEmploi, String> descriptionEmploi;
    @FXML
    private TableColumn<offreEmploi, String> domaineEmploi;
    @FXML
    private TableColumn<offreEmploi, String> competanceEmploi;
    @FXML
    private TableColumn<offreEmploi, String> salaireEmploi;
    @FXML
    private TableColumn<offreEmploi, Date> dateCreationEmploi;
    @FXML
    private TableColumn<offreEmploi, String> actionsEmploi;
    @FXML
    private TableColumn<offreStage, Integer> id_offre_stage;
    @FXML
    private TableColumn<offreStage, String> ownerStage;
    @FXML
    private TableColumn<offreStage, String> nomProjetStage;
    @FXML
    private TableColumn<offreStage, String> descriptionStage;
    @FXML
    private TableColumn<offreStage, String> domaineStage;
    @FXML
    private TableColumn<offreStage, String> competanceStage;
    @FXML
    private TableColumn<offreStage, String> typeStage;
    @FXML
    private TableColumn<offreStage, String> dureeStage;
    @FXML
    private TableColumn<offreStage, Date> dateCreationStage;
     @FXML
    private TableColumn<offreStage, String> actionsStage;
    @FXML
    private TableView<offreStage> TabOffreStage;

    ObservableList<offreEmploi> ListoffreEmploi = FXCollections.observableArrayList();
    ObservableList<offreStage> ListoffreStage = FXCollections.observableArrayList();
    @FXML
    private Label EmploiVide;
    @FXML
    private Label StageVide;
    
   
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        loadEmploiDate();
        //loadStageDate();
    }    
    
    void refreshEmploi(){
        ListoffreEmploi.clear();
        ListoffreEmploi.addAll(new AdminEmploiService().getAllEmploiNonApprouve());
    }
    void loadEmploiDate(){
        refreshEmploi();
        if (ListoffreEmploi.isEmpty()){
            EmploiVide.setVisible(true);
            TabOffreEmploi.setVisible(false);
        }else{
            TabOffreEmploi.setVisible(true);
            EmploiVide.setVisible(false);
        
        SocieteService sos = new SocieteService();
        Id_Offre_Emploi.setCellValueFactory(new PropertyValueFactory<>("id"));
//        ownerEmploi.setCellValueFactory(new Callback<CellDataFeatures<offreEmploi,Integer>,ObservableValue<String>>(){
//            @Override
//            public ObservableValue<String> call(CellDataFeatures<offreEmploi, Integer> param) {
//                return new SimpleStringProperty(sos.FindparID(param.getValue().getIdSociete()).getNom()); //To change body of generated methods, choose Tools | Templates.
//            }
//
//               
//            });
        
        ownerEmploi.setCellValueFactory(data -> {        
        String cellValue= sos.FindparID(data.getValue().getId()).getNom();
        return new SimpleStringProperty(cellValue);
    });
        nomProjetEmploi.setCellValueFactory(new PropertyValueFactory<>("nomProjet"));
        descriptionEmploi.setCellValueFactory(new PropertyValueFactory<>("description"));
        domaineEmploi.setCellValueFactory(new PropertyValueFactory<>("domaine"));
        competanceEmploi.setCellValueFactory(new PropertyValueFactory<>("competence"));
        salaireEmploi.setCellValueFactory(new PropertyValueFactory<>("salaire"));
        dateCreationEmploi.setCellValueFactory(new PropertyValueFactory<>("dateCreation"));
        Callback<TableColumn<offreEmploi, String>, TableCell<offreEmploi, String>> cellFactory
                = (TableColumn<offreEmploi, String> param) -> {
                    // make cell containing buttons
                    final TableCell<offreEmploi, String> cell = new TableCell<offreEmploi, String>() {
                @Override
                protected void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);
                    if (empty) {
                        setGraphic(null);
                        setText(null);

                    } else {
                        FontAwesomeIconView deletebtn = new FontAwesomeIconView(FontAwesomeIcon.TRASH_ALT);
                FontAwesomeIconView editbtn = new FontAwesomeIconView(FontAwesomeIcon.CHECK);
                FontAwesomeIconView viewbtn = new FontAwesomeIconView(FontAwesomeIcon.EYE);
                        deletebtn.setStyle(
                                " -fx-cursor: hand ;"
                                + "-glyph-size:28px;"
                                + "-fx-fill:#ff1744;"
                        );
                        editbtn.setStyle(
                                " -fx-cursor: hand ;"
                                + "-glyph-size:28px;"
                                + "-fx-fill:#00E676;"
                        );
                        viewbtn.setStyle(
                                " -fx-cursor: hand ;"
                                + "-glyph-size:28px;"
                                + "-fx-fill:#d8e004;"
                        );
                        deletebtn.setOnMouseClicked((event) -> {
                            offreEmploi e = TabOffreEmploi.getSelectionModel().getSelectedItem();
                            new AdminEmploiService().DeactivateOffreEmploi(e);
                            refreshEmploi();
                        });
                        editbtn.setOnMouseClicked((event) -> {
                            offreEmploi e = TabOffreEmploi.getSelectionModel().getSelectedItem();
                            new AdminEmploiService().ActivateOffreEmploi(e,Admin.getInstance());
                            refreshEmploi();
                        });
                        viewbtn.setOnMouseClicked((event) -> {
                            offreEmploi e = TabOffreEmploi.getSelectionModel().getSelectedItem();
                            FXMLLoader loader = new FXMLLoader();
                            loader.setLocation(getClass().getResource("/pidev_java/gui/back/ItemEmploi.fxml"));
                            try {
                                loader.load();
                            } catch (IOException ex) {
                                System.out.println(ex.getMessage());                            }
                            ItemEmploiController itemEmploiController = loader.getController();
                            itemEmploiController.setData(e);
                            Parent parent = loader.getRoot();
                            Stage stage = new Stage();
                            stage.setScene(new Scene(parent));
                            stage.initStyle(StageStyle.UTILITY);
                            stage.show();

                        });
                        HBox managebtn = new HBox(viewbtn,editbtn, deletebtn);
                        managebtn.setStyle("-fx-alignment:center");
                        HBox.setMargin(deletebtn, new Insets(2, 2, 0, 3));
                        HBox.setMargin(editbtn, new Insets(2, 3, 0, 2));
                        HBox.setMargin(viewbtn, new Insets(2, 3, 0, 2));
                        setGraphic(managebtn);

                        setText(null);
                    }
                }
            };
                    return cell;
                };
        actionsEmploi.setCellFactory(cellFactory);
        TabOffreEmploi.setItems(ListoffreEmploi);
        }
    }
    
     void loadStageDate(){
        refreshStage();
        if(ListoffreStage.isEmpty()){
            StageVide.setVisible(true);
            TabOffreStage.setVisible(false);
        }else{
            StageVide.setVisible(false);
            TabOffreStage.setVisible(true);
        
        SocieteService sos = new SocieteService();
        id_offre_stage.setCellValueFactory(new PropertyValueFactory<>("id"));
//        ownerStage.setCellValueFactory(new Callback<CellDataFeatures<offreStage,Integer>,ObservableValue<String>>(){
//            @Override
//            public ObservableValue<String> call(CellDataFeatures<offreStage, Integer> param) {
//                return new SimpleStringProperty(sos.FindparID(param.getValue().getIdSociete()).getNom()); //To change body of generated methods, choose Tools | Templates.
//            }
//
//               
//            });
        
        ownerStage.setCellValueFactory(data -> {        
        String cellValue= sos.FindparID(data.getValue().getId()).getNom();
        return new SimpleStringProperty(cellValue);
    });
        nomProjetStage.setCellValueFactory(new PropertyValueFactory<>("nomProjet"));
        descriptionStage.setCellValueFactory(new PropertyValueFactory<>("description"));
        domaineStage.setCellValueFactory(new PropertyValueFactory<>("domaine"));
        competanceStage.setCellValueFactory(new PropertyValueFactory<>("competence"));
        typeStage.setCellValueFactory(new PropertyValueFactory<>("typeStage"));
        dureeStage.setCellValueFactory(new PropertyValueFactory<>("duree"));
        dateCreationStage.setCellValueFactory(new PropertyValueFactory<>("dateCreation"));
        Callback<TableColumn<offreStage, String>, TableCell<offreStage, String>> cellFactory
                = (TableColumn<offreStage, String> param) -> {
                    // make cell containing buttons
                    final TableCell<offreStage, String> cell = new TableCell<offreStage, String>() {
                @Override
                protected void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);
                    if (empty) {
                        setGraphic(null);
                        setText(null);

                    } else {
                        FontAwesomeIconView deletebtn = new FontAwesomeIconView(FontAwesomeIcon.TRASH_ALT);
                FontAwesomeIconView editbtn = new FontAwesomeIconView(FontAwesomeIcon.CHECK);
                FontAwesomeIconView viewbtn = new FontAwesomeIconView(FontAwesomeIcon.EYE_SLASH);
                        deletebtn.setStyle(
                                " -fx-cursor: hand ;"
                                + "-glyph-size:28px;"
                                + "-fx-fill:#ff1744;"
                        );
                        editbtn.setStyle(
                                " -fx-cursor: hand ;"
                                + "-glyph-size:28px;"
                                + "-fx-fill:#00E676;"
                        );
                        viewbtn.setStyle(
                                " -fx-cursor: hand ;"
                                + "-glyph-size:28px;"
                                + "-fx-fill:#d8e004;"
                        );
                        deletebtn.setOnMouseClicked((event) -> {
                            offreStage e = TabOffreStage.getSelectionModel().getSelectedItem();
                            new AdminEmploiService().DeactivateOffreStage(e);
                            refreshStage();
                        });
                        editbtn.setOnMouseClicked((event) -> {
                            offreStage e = TabOffreStage.getSelectionModel().getSelectedItem();
                            new AdminEmploiService().ActivateOffreStage(e,Admin.getInstance());
                            refreshStage();
                        });
                        viewbtn.setOnMouseClicked((event) -> {
                            offreStage e = TabOffreStage.getSelectionModel().getSelectedItem();
                            FXMLLoader loader = new FXMLLoader();
                            loader.setLocation(getClass().getResource("/pidev_java/gui/back/ItemStage.fxml"));
                            try {
                                loader.load();
                            } catch (IOException ex) {
                                System.out.println(ex.getMessage());                            }
                            ItemStageController itemStageController = loader.getController();
                            itemStageController.setData(e);
                            Parent parent = loader.getRoot();
                            Stage stage = new Stage();
                            stage.setScene(new Scene(parent));
                            stage.initStyle(StageStyle.UTILITY);
                            stage.show();

                        });
                        HBox managebtn = new HBox(viewbtn,editbtn, deletebtn);
                        managebtn.setStyle("-fx-alignment:center");
                        HBox.setMargin(deletebtn, new Insets(2, 2, 0, 3));
                        HBox.setMargin(editbtn, new Insets(2, 3, 0, 2));
                        HBox.setMargin(viewbtn, new Insets(2, 3, 0, 2));
                        setGraphic(managebtn);

                        setText(null);
                    }
                }
            };
                    return cell;
                };
        actionsStage.setCellFactory(cellFactory);
        TabOffreStage.setItems(ListoffreStage);
        }
    }

    private void refreshStage() {
        ListoffreStage.clear();
        ListoffreStage.addAll(new AdminEmploiService().getAllStageNonApprouve()); //To change body of generated methods, choose Tools | Templates.
    }
    
}
