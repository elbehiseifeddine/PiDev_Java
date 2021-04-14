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
public class SuperAdminIndexController implements Initializable {

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

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        loadDataRec();

    }

    private void refrechAdminReclamation() {
        ListAdminReclamation.clear();
        AdminService admin = new AdminService();
        ListAdminReclamation.addAll(admin.getAllAdminReclamation());

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
                                Logger.getLogger(SuperAdminIndexController.class.getName()).log(Level.SEVERE, null, ex);
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

    @FXML
    private void CreateAdmin(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/pidev/gui/CreateAdmin.fxml"));
        try {
            loader.load(); 
        } catch (IOException ex) {
            Logger.getLogger(SuperAdminIndexController.class.getName()).log(Level.SEVERE, null, ex);
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
