/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev_java.gui.back;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;
import pidev_java.entities.Admin;
import pidev_java.services.AdminService;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author ahmed
 */
public class AccueilSuperAdminController implements Initializable {

    ObservableList<Admin> ListAdminReclamation = FXCollections.observableArrayList();
    ObservableList<Admin> ListAdminEmploi = FXCollections.observableArrayList();
    ObservableList<Admin> ListAdminEvent = FXCollections.observableArrayList();
    @FXML
    private TableView<Admin> TableadminReclamation;
    @FXML
    private TableColumn<Admin, Integer> id_a_r;
    @FXML
    private TableColumn<Admin, String> nom;
    @FXML
    private TableColumn<Admin, String> prenom;
    @FXML
    private TableColumn<Admin, String> email;
    @FXML
    private TableColumn<Admin, String> password;
    @FXML
    private TableColumn<Admin, Boolean> etat;
    @FXML
    private TableColumn<Admin, Integer> approuve;
    @FXML
    private TableColumn<Admin, Integer> nonapprouve;
    @FXML
    private TableColumn<Admin, String> actions;
    @FXML
    private Button btnCreateAdmin;
    @FXML
    private TableColumn<Admin, String> nom_emploi;
    @FXML
    private TableColumn<Admin, String> prenom_emploi;
    @FXML
    private TableColumn<Admin, String> email_emploi;
    @FXML
    private TableColumn<Admin, String> password_emploi;
    @FXML
    private TableColumn<Admin, Boolean> etat_emploi;
    @FXML
    private TableColumn<Admin, Integer> approuve_emploi;
    @FXML
    private TableColumn<Admin, Integer> nonapprouve_emploi;
    @FXML
    private TableColumn<Admin, String> actions_emploi;
    @FXML
    private TableColumn<Admin, Integer> id_a_e;
    @FXML
    private TableView<Admin> TableadminEmploi;
    @FXML
    private TableView<Admin> TableadminEvents;
    @FXML
    private TableColumn<Admin, Integer> id_a_event;
    @FXML
    private TableColumn<Admin, String> nom_events;
    @FXML
    private TableColumn<Admin, String> prenom_events;
    @FXML
    private TableColumn<Admin, String> email_events;
    @FXML
    private TableColumn<Admin, String> password_events;
    @FXML
    private TableColumn<Admin, String> etat_events;
    @FXML
    private TableColumn<Admin, Integer> approuve_events;
    @FXML
    private TableColumn<Admin, Integer> nonapprouve_events;
    @FXML
    private TableColumn<Admin, String> actions_events;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        loadDataRec();
        loadDataEmploi();
        loadDataEvent();

    }

    private void refrechAdminReclamation() {
        ListAdminReclamation.clear();
        AdminService admin = new AdminService();
        ListAdminReclamation.addAll(admin.getAllAdminReclamation());

    }
    
    private void refrechAdminEmploi() {
        ListAdminEmploi.clear();
        AdminService admin = new AdminService();
        ListAdminEmploi.addAll(admin.getAllAdminEmploi());

    }
    
     private void refrechAdminEvents() {
        ListAdminEvent.clear();
        AdminService admin = new AdminService();
        ListAdminEvent.addAll(admin.getAllAdminEvent());

    }

    private void loadDataRec() {
        refrechAdminReclamation();
        AdminService adminService = new AdminService();
        id_a_r.setCellValueFactory(new PropertyValueFactory<>("id"));
        nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        prenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        email.setCellValueFactory(new PropertyValueFactory<>("login"));
        password.setCellValueFactory(new PropertyValueFactory<>("password"));
        etat.setCellValueFactory(new PropertyValueFactory<>("etat"));
        approuve.setCellValueFactory(new PropertyValueFactory<>("approuve"));
        nonapprouve.setCellValueFactory(new PropertyValueFactory<>("nonapprouve"));

        Callback<TableColumn<Admin, String>, TableCell<Admin, String>> cellFactory
                = (TableColumn<Admin, String> param) -> {
                    // make cell containing buttons
                    final TableCell<Admin, String> cell = new TableCell<Admin, String>() {
                @Override
                protected void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);
                    if (empty) {
                        setGraphic(null);
                        setText(null);

                    } else {
                        Button deletebtn = new Button("Delete");
                        Button editbtn = new Button("Edit");
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
                        deletebtn.setOnMouseClicked((event) -> {
                            Admin a = TableadminReclamation.getSelectionModel().getSelectedItem();
                            System.out.println(TableadminReclamation.getSelectionModel().getSelectedItem());
                            adminService.delete(a);
                            refrechAdminReclamation();
                        });
                        editbtn.setOnMouseClicked((event) -> {
                            Admin a = TableadminReclamation.getSelectionModel().getSelectedItem();
                            FXMLLoader loader = new FXMLLoader();
                            loader.setLocation(getClass().getResource("/pidev/gui/CreateAdmin.fxml"));
                            try {
                                loader.load();
                            } catch (IOException ex) {
                                Logger.getLogger(AccueilSuperAdminController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            CreateAdminController createAdminController = loader.getController();
                            createAdminController.setUpdate(true);
                            createAdminController.setTextField(a);
                            Parent parent = loader.getRoot();
                            Stage stage = new Stage();
                            stage.setScene(new Scene(parent));
                            stage.initStyle(StageStyle.UTILITY);
                            stage.show();

                        });
                        HBox managebtn = new HBox(editbtn, deletebtn);
                        managebtn.setStyle("-fx-alignment:center");
                        HBox.setMargin(deletebtn, new Insets(2, 2, 0, 3));
                        HBox.setMargin(editbtn, new Insets(2, 3, 0, 2));
                        setGraphic(managebtn);

                        setText(null);
                    }
                }
            };
                    return cell;
                };
        actions.setCellFactory(cellFactory);
        TableadminReclamation.setItems(ListAdminReclamation);

    }

    private void loadDataEmploi() {
        refrechAdminEmploi();
        AdminService adminService = new AdminService();
        id_a_e.setCellValueFactory(new PropertyValueFactory<>("id"));
        nom_emploi.setCellValueFactory(new PropertyValueFactory<>("nom"));
        prenom_emploi.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        email_emploi.setCellValueFactory(new PropertyValueFactory<>("login"));
        password_emploi.setCellValueFactory(new PropertyValueFactory<>("password"));
        etat_emploi.setCellValueFactory(new PropertyValueFactory<>("etat"));
        approuve_emploi.setCellValueFactory(new PropertyValueFactory<>("approuve"));
        nonapprouve_emploi.setCellValueFactory(new PropertyValueFactory<>("nonapprouve"));

        Callback<TableColumn<Admin, String>, TableCell<Admin, String>> cellFactory1
                = (TableColumn<Admin, String> param) -> {
                    // make cell containing buttons
                    final TableCell<Admin, String> cell2 = new TableCell<Admin, String>() {
                @Override
                protected void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);
                    if (empty) {
                        setGraphic(null);
                        setText(null);

                    } else {
                        Button deletebtn = new Button("Delete");
                        Button editbtn = new Button("Edit");
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
                        deletebtn.setOnMouseClicked((event) -> {
                            Admin a = TableadminEmploi.getSelectionModel().getSelectedItem();
                            System.out.println(TableadminEmploi.getSelectionModel().getSelectedItem());
                            adminService.delete(a);
                            refrechAdminEmploi();
                        });
                        editbtn.setOnMouseClicked((event) -> {
                            Admin a = TableadminEmploi.getSelectionModel().getSelectedItem();
                            FXMLLoader loader2 = new FXMLLoader();
                            loader2.setLocation(getClass().getResource("/pidev/gui/CreateAdmin.fxml"));
                            try {
                                loader2.load();
                            } catch (IOException ex) {
                                Logger.getLogger(AccueilSuperAdminController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            CreateAdminController createAdminController = loader2.getController();
                            createAdminController.setUpdate(true);
                            createAdminController.setTextField(a);
                            Parent parent = loader2.getRoot();
                            Stage stage2 = new Stage();
                            stage2.setScene(new Scene(parent));
                            stage2.initStyle(StageStyle.UTILITY);
                            stage2.show();

                        });
                        HBox managebtn2 = new HBox(editbtn, deletebtn);
                        managebtn2.setStyle("-fx-alignment:center");
                        HBox.setMargin(deletebtn, new Insets(2, 2, 0, 3));
                        HBox.setMargin(editbtn, new Insets(2, 3, 0, 2));
                        setGraphic(managebtn2);

                        setText(null);
                    }
                }
            };
                    return cell2;
                };
        actions_emploi.setCellFactory(cellFactory1);
        TableadminEmploi.setItems(ListAdminEmploi);

    }

    private void loadDataEvent() {
        refrechAdminEvents();
        AdminService adminService2 = new AdminService();
        id_a_event.setCellValueFactory(new PropertyValueFactory<>("id"));
        nom_events.setCellValueFactory(new PropertyValueFactory<>("nom"));
        prenom_events.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        email_events.setCellValueFactory(new PropertyValueFactory<>("login"));
        password_events.setCellValueFactory(new PropertyValueFactory<>("password"));
        etat_events.setCellValueFactory(new PropertyValueFactory<>("etat"));
        approuve_events.setCellValueFactory(new PropertyValueFactory<>("approuve"));
        nonapprouve_events.setCellValueFactory(new PropertyValueFactory<>("nonapprouve"));

        Callback<TableColumn<Admin, String>, TableCell<Admin, String>> cellFactory2
                = (TableColumn<Admin, String> param) -> {
                    // make cell containing buttons
                    final TableCell<Admin, String> cell3 = new TableCell<Admin, String>() {
                @Override
                protected void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);
                    if (empty) {
                        setGraphic(null);
                        setText(null);

                    } else {
                        Button deletebtn = new Button("Delete");
                        Button editbtn = new Button("Edit");
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
                        deletebtn.setOnMouseClicked((event) -> {
                            Admin a = TableadminEvents.getSelectionModel().getSelectedItem();
                            System.out.println(TableadminEvents.getSelectionModel().getSelectedItem());
                            adminService2.delete(a);
                            refrechAdminEvents();
                        });
                        editbtn.setOnMouseClicked((event) -> {
                            Admin a = TableadminEvents.getSelectionModel().getSelectedItem();
                            FXMLLoader loader3 = new FXMLLoader();
                            loader3.setLocation(getClass().getResource("/pidev/gui/CreateAdmin.fxml"));
                            try {
                                loader3.load();
                            } catch (IOException ex) {
                                Logger.getLogger(AccueilSuperAdminController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            CreateAdminController createAdminController = loader3.getController();
                            createAdminController.setUpdate(true);
                            createAdminController.setTextField(a);
                            Parent parent = loader3.getRoot();
                            Stage stage3 = new Stage();
                            stage3.setScene(new Scene(parent));
                            stage3.initStyle(StageStyle.UTILITY);
                            stage3.show();

                        });
                        HBox managebtn3 = new HBox(editbtn, deletebtn);
                        managebtn3.setStyle("-fx-alignment:center");
                        HBox.setMargin(deletebtn, new Insets(2, 2, 0, 3));
                        HBox.setMargin(editbtn, new Insets(2, 3, 0, 2));
                        setGraphic(managebtn3);

                        setText(null);
                    }
                }
            };
                    return cell3;
                };
        actions_events.setCellFactory(cellFactory2);
        TableadminEvents.setItems(ListAdminEvent);

    }
    
    @FXML
    private void CreateAdmin(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/pidev/gui/CreateAdmin.fxml"));
        try {
            loader.load(); 
        } catch (IOException ex) {
            Logger.getLogger(AccueilSuperAdminController.class.getName()).log(Level.SEVERE, null, ex);
        }
        CreateAdminController createAdminController = loader.getController();
        createAdminController.setUpdate(false);
        Parent parent = loader.getRoot();
        Stage stage = new Stage();
        stage.setScene(new Scene(parent));
        stage.initStyle(StageStyle.UTILITY);
        stage.show();
    }
}
