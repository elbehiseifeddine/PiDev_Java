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
import java.util.ArrayList;
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
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Border;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javax.swing.JOptionPane;
import jdk.nashorn.internal.objects.Global;

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
    private Button btnCreateAdmin;

    @FXML
    private Button btn_refresh;
    @FXML
    private HBox AdminRecHbox;
    @FXML
    private HBox AdminEmploiHbox;
    @FXML
    private HBox AdminEventHbox;
    @FXML
    private Label LabelReclamationVide;
    @FXML
    private Label LabelVideEmploi;
    @FXML
    private Label LabelVideEvent;

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

        ArrayList<Admin> liste = adminService.getAllAdminReclamation();
        if (liste.isEmpty()) {
            AdminRecHbox.setVisible(false);
            LabelReclamationVide.setText("Aucun admin disponible, pensez à lui créer ! ");

        } else {
            for (Admin admin : liste) {
                ImageView image = new ImageView("./pidev_java/assets/img-1.jpg");
                Label name = new Label(admin.getNom() + " " + admin.getPrenom());
                Label Labelapprouve = new Label(admin.getApprouve() + " Approuvé");
                Label Labelnonapprouve = new Label(admin.getNonApprouve() + " Non Approuvé");

                FontAwesomeIconView deletebtn = new FontAwesomeIconView(FontAwesomeIcon.TRASH_ALT);
                FontAwesomeIconView editbtn = new FontAwesomeIconView(FontAwesomeIcon.EDIT);

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
                deletebtn.setOnMouseClicked((MouseEvent event) -> {

                    int reponse = JOptionPane.showConfirmDialog(null, "Vous voulez vraiment supprimer " + admin.getNom() + " " + admin.getPrenom() + "?", "Select an Option...",
                            JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.ERROR_MESSAGE);
                    if (reponse == JOptionPane.YES_OPTION) {
                        adminService.delete(admin);
                        refrechAdminReclamation();
                    }
                });
                editbtn.setOnMouseClicked((event) -> {

                    FXMLLoader loader = new FXMLLoader();
                    loader.setLocation(getClass().getResource("/pidev_java/gui/back/CreateAdmin.fxml"));
                    try {
                        loader.load();
                    } catch (IOException ex) {
                        Logger.getLogger(AccueilSuperAdminController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    CreateAdminController createAdminController = loader.getController();
                    createAdminController.setUpdate(true);
                    createAdminController.setTextField(admin);
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
                VBox vbox = new VBox(image, name, Labelapprouve, Labelnonapprouve, managebtn);
                vbox.setMaxWidth(1501 / liste.size());
                vbox.setSpacing(13);
                vbox.setPadding(new Insets(0, 20, 0, 20));
                AdminRecHbox.getChildren().add(vbox);
            }
        }
        AdminRecHbox.setSpacing(20);
        AdminRecHbox.prefWidth(1585);
    }

    private void loadDataEmploi() {
        refrechAdminEmploi();
        AdminService adminService = new AdminService();
        ArrayList<Admin> liste = adminService.getAllAdminEmploi();
        if (liste.isEmpty()) {
            AdminEmploiHbox.setVisible(false);

            LabelVideEmploi.setText("Aucun admin disponible, pensez à lui créer ! ");
        } else {
            for (Admin admin : liste) {
                ImageView image = new ImageView("./pidev_java/assets/img-1.jpg");
                Label name = new Label(admin.getNom() + " " + admin.getPrenom());
                Label Labelapprouve = new Label(admin.getApprouve() + " Approuvé");
                Label Labelnonapprouve = new Label(admin.getNonApprouve() + " Non Approuvé");

                FontAwesomeIconView deletebtn = new FontAwesomeIconView(FontAwesomeIcon.TRASH_ALT);
                FontAwesomeIconView editbtn = new FontAwesomeIconView(FontAwesomeIcon.EDIT);

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
                deletebtn.setOnMouseClicked((MouseEvent event) -> {

                    int reponse = JOptionPane.showConfirmDialog(null, "Vous voulez vraiment supprimer " + admin.getNom() + " " + admin.getPrenom() + "?", "Select an Option...",
                            JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.ERROR_MESSAGE);
                    if (reponse == JOptionPane.YES_OPTION) {
                        adminService.delete(admin);
                        refrechAdminReclamation();
                    }
                });
                editbtn.setOnMouseClicked((event) -> {

                    FXMLLoader loader = new FXMLLoader();
                    loader.setLocation(getClass().getResource("/pidev_java/gui/back/CreateAdmin.fxml"));
                    try {
                        loader.load();
                    } catch (IOException ex) {
                        Logger.getLogger(AccueilSuperAdminController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    CreateAdminController createAdminController = loader.getController();
                    createAdminController.setUpdate(true);
                    createAdminController.setTextField(admin);
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
                VBox vbox = new VBox(image, name, Labelapprouve, Labelnonapprouve, managebtn);
                vbox.setMaxWidth(1501 / liste.size());
                vbox.setSpacing(13);
                vbox.setPadding(new Insets(0, 20, 0, 20));
                AdminEmploiHbox.getChildren().add(vbox);
            }
        }
        AdminEmploiHbox.setSpacing(20);
        AdminEmploiHbox.prefWidth(1585);
    }

    private void loadDataEvent() {
        refrechAdminEvents();
        AdminService adminService = new AdminService();
        ArrayList<Admin> liste = adminService.getAllAdminEvent();
        if (liste.isEmpty()) {
            AdminEventHbox.setVisible(false);

            LabelVideEvent.setText("Aucun admin disponible, pensez à lui créer ! ");
        } else {
            for (Admin admin : liste) {
                ImageView image = new ImageView("./pidev_java/assets/img-1.jpg");
                Label name = new Label(admin.getNom() + " " + admin.getPrenom());
                Label Labelapprouve = new Label(admin.getApprouve() + " Approuvé");
                Label Labelnonapprouve = new Label(admin.getNonApprouve() + " Non Approuvé");

                FontAwesomeIconView deletebtn = new FontAwesomeIconView(FontAwesomeIcon.TRASH_ALT);
                FontAwesomeIconView editbtn = new FontAwesomeIconView(FontAwesomeIcon.EDIT);

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
                deletebtn.setOnMouseClicked((MouseEvent event) -> {

                    int reponse = JOptionPane.showConfirmDialog(null, "Vous voulez vraiment supprimer " + admin.getNom() + " " + admin.getPrenom() + "?", "Select an Option...",
                            JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.ERROR_MESSAGE);
                    if (reponse == JOptionPane.YES_OPTION) {
                        adminService.delete(admin);
                        refrechAdminReclamation();
                    }
                });
                editbtn.setOnMouseClicked((event) -> {

                    FXMLLoader loader = new FXMLLoader();
                    loader.setLocation(getClass().getResource("/pidev_java/gui/back/CreateAdmin.fxml"));
                    try {
                        loader.load();
                    } catch (IOException ex) {
                        Logger.getLogger(AccueilSuperAdminController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    CreateAdminController createAdminController = loader.getController();
                    createAdminController.setUpdate(true);
                    createAdminController.setTextField(admin);
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
                VBox vbox = new VBox(image, name, Labelapprouve, Labelnonapprouve, managebtn);
                vbox.setMaxWidth(1501 / liste.size());
                vbox.setSpacing(13);
                vbox.setPadding(new Insets(0, 20, 0, 20));
                AdminEventHbox.getChildren().add(vbox);
            }
        }
        AdminEventHbox.setSpacing(20);
        AdminEventHbox.prefWidth(1585);
    }

    @FXML
    private void CreateAdmin(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/pidev_java/gui/back/CreateAdmin.fxml"));
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

    @FXML
    private void Refresh(ActionEvent event) {
        refrechAdminEmploi();
        refrechAdminEvents();
        refrechAdminReclamation();
    }
}
