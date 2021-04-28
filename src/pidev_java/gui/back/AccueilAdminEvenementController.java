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
import pidev_java.entities.EventLoisir;
import pidev_java.entities.Formation;
import pidev_java.entities.offreEmploi;
import pidev_java.services.AdminEmploiService;
import pidev_java.services.AdminEventService;
import pidev_java.services.FreelancerService;
import pidev_java.services.SocieteService;

/**
 * FXML Controller class
 *
 * @author ahmed
 */
public class AccueilAdminEvenementController implements Initializable {

    @FXML
    private TableColumn<EventLoisir, String> ownerEvent;
    @FXML
    private TableColumn<EventLoisir, String> labelleEvent;
    @FXML
    private TableColumn<EventLoisir, String> descriptionEvent;
    @FXML
    private TableColumn<EventLoisir, String> domaineEvent;
    @FXML
    private TableColumn<EventLoisir, String> lieuEvent;
    @FXML
    private TableColumn<EventLoisir, Date> dateEvent;
    @FXML
    private TableColumn<EventLoisir, String> actionsEvent;
    @FXML
    private TableColumn<Formation, String> ownerFormation;
    @FXML
    private TableColumn<Formation, String> descriptionFormation;
    @FXML
    private TableColumn<Formation, String> domaineFormation;
    @FXML
    private TableColumn<Formation, String> lieuFormation;
    @FXML
    private TableColumn<Formation, String> dateFormation;
    @FXML
    private TableView<EventLoisir> TabEvent;
    @FXML
    private TableView<Formation> TabFormation;
    @FXML
    private TableColumn<EventLoisir, Integer> Id_event;
    @FXML
    private TableColumn<Formation, Integer> id_formation;
    @FXML
    private Label EventVide;
    @FXML
    private Label FormationVide;

    private SocieteService sos;
    private FreelancerService frs;

    ObservableList<EventLoisir> Listevent = FXCollections.observableArrayList();
    ObservableList<Formation> Listformation = FXCollections.observableArrayList();
    @FXML
    private TableColumn<Formation,String> labelleFormation;
    @FXML
    private TableColumn<Formation,String> actionsFormation;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        loadDataEvent();
        loadDataFormation();
    }

    void refreshEvent() {
        Listevent.clear();
        Listevent.addAll(new AdminEventService().getAllEventLoisirNonApprouve());
    }

    void refreshFormation() {
        Listformation.clear();
        Listformation.addAll(new AdminEventService().getAllFormationNonApprouve());
    }

    void loadDataEvent() {
        refreshEvent();
        if (Listevent.isEmpty()) {
            TabEvent.setVisible(false);
            EventVide.setVisible(true);
        } else {
            Id_event.setCellValueFactory(new PropertyValueFactory<>("id"));

//            ownerEvent.setCellValueFactory(data -> {
//                String cellValue = "";
//                if (data.getValue().getidFr != 0) {
//                    cellValue = frs.FindparID(data.getValue().getId()).getNom() + " : freelancer";
//                } else {
//                    cellValue = sos.FindparID(data.getValue().getId()).getNom() + " : Societe";
//                }
//                return new SimpleStringProperty(cellValue);
//            });

            labelleEvent.setCellValueFactory(new PropertyValueFactory<>("Labelle"));
            descriptionEvent.setCellValueFactory(new PropertyValueFactory<>("Description"));
            domaineEvent.setCellValueFactory(new PropertyValueFactory<>("Domaine"));
            lieuEvent.setCellValueFactory(new PropertyValueFactory<>("Lieu"));
            dateEvent.setCellValueFactory(new PropertyValueFactory<>("DateDebut"));
            
            Callback<TableColumn<EventLoisir, String>, TableCell<EventLoisir, String>> cellFactory
                = (TableColumn<EventLoisir, String> param) -> {
                    // make cell containing buttons
                    final TableCell<EventLoisir, String> cell = new TableCell<EventLoisir, String>() {
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
                            EventLoisir e = TabEvent.getSelectionModel().getSelectedItem();
                            new AdminEventService().DeactivateEvenement(e);
                            refreshEvent();
                        });
                        editbtn.setOnMouseClicked((event) -> {
                            EventLoisir e = TabEvent.getSelectionModel().getSelectedItem();
                            new AdminEventService().ActivateEvenement(e,Admin.getInstance());
                            refreshEvent();
                        });
                        viewbtn.setOnMouseClicked((event) -> {
                            EventLoisir e = TabEvent.getSelectionModel().getSelectedItem();
                            FXMLLoader loader = new FXMLLoader();
                            loader.setLocation(getClass().getResource("/pidev_java/gui/back/ItemEventBack.fxml"));
                            try {
                                loader.load();
                            } catch (IOException ex) {
                                System.out.println(ex.getMessage());                            }
                            ItemEventBackController itemEventBackController = loader.getController();
                            itemEventBackController.setData(e);
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
        actionsEvent.setCellFactory(cellFactory);

        }
    }
    
     void loadDataFormation() {
        refreshFormation();
        if (Listevent.isEmpty()) {
            TabFormation.setVisible(false);
            FormationVide.setVisible(true);
        } else {
            id_formation.setCellValueFactory(new PropertyValueFactory<>("id"));

//            ownerFormation.setCellValueFactory(data -> {
//                String cellValue = "";
//                if (data.getValue().getidFr != 0) {
//                    cellValue = frs.FindparID(data.getValue().getId()).getNom() + " : freelancer";
//                } else {
//                    cellValue = sos.FindparID(data.getValue().getId()).getNom() + " : Societe";
//                }
//                return new SimpleStringProperty(cellValue);
//            });

            labelleFormation.setCellValueFactory(new PropertyValueFactory<>("Labelle"));
            descriptionFormation.setCellValueFactory(new PropertyValueFactory<>("Description"));
            domaineFormation.setCellValueFactory(new PropertyValueFactory<>("Domaine"));
            lieuFormation.setCellValueFactory(new PropertyValueFactory<>("Lieu"));
            dateFormation.setCellValueFactory(new PropertyValueFactory<>("DateDebut"));
            
            Callback<TableColumn<Formation, String>, TableCell<Formation, String>> cellFactory
                = (TableColumn<Formation, String> param) -> {
                    // make cell containing buttons
                    final TableCell<Formation, String> cell = new TableCell<Formation, String>() {
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
                            Formation e = TabFormation.getSelectionModel().getSelectedItem();
                            new AdminEventService().DeactivateFormation(e);
                            refreshFormation();
                        });
                        editbtn.setOnMouseClicked((event) -> {
                            Formation e = TabFormation.getSelectionModel().getSelectedItem();
                            new AdminEventService().ActivateFormation(e,Admin.getInstance());
                            refreshFormation();
                        });
                        viewbtn.setOnMouseClicked((event) -> {
                            Formation e = TabFormation.getSelectionModel().getSelectedItem();
                            FXMLLoader loader = new FXMLLoader();
                            loader.setLocation(getClass().getResource("/pidev_java/gui/back/ItemFormationBack.fxml"));
                            try {
                                loader.load();
                            } catch (IOException ex) {
                                System.out.println(ex.getMessage());                            }
                            ItemFormationBackController itemFormationBackController = loader.getController();
                            itemFormationBackController.setData(e);
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
        actionsFormation.setCellFactory(cellFactory);

        }
    }

}
