/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev_java.gui.back;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import java.awt.Color;
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
import javafx.geometry.Pos;
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
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Border;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
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
    @FXML
    private AnchorPane NavBar;
    @FXML
    private TextField recherche;
    @FXML
    private AnchorPane Admins;
    @FXML
    private Text Label2;
    @FXML
    private Text Label3;
    @FXML
    private Text Label1;

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
                ImageView image = new ImageView("./pidev_java/assets/img-1-1.png");
                Label name = new Label(admin.getNom() + " " + admin.getPrenom());
                Label Labelapprouve = new Label(admin.getApprouve() + " Approuvé");
                Label Labelnonapprouve = new Label(admin.getNonApprouve() + " Non Approuvé");
                Label lock = new Label();
                if (admin.isEtat() == false) {
                    lock.setStyle("-fx-text-fill: #e10707;"
                            + "-fx-font-weight : Bold ;");
                    lock.setText("This account is locked !");

                }

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
                            JOptionPane.YES_NO_OPTION, JOptionPane.ERROR_MESSAGE);
                    if (reponse == JOptionPane.YES_OPTION) {
                        adminService.delete(admin);
                        refrechpage();
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
                    stage.setTitle("Modifier admin");
                    stage.initStyle(StageStyle.UTILITY);
                    stage.show();

                });
                HBox managebtn = new HBox(editbtn, deletebtn);
                managebtn.setStyle("-fx-alignment:center");
                HBox.setMargin(deletebtn, new Insets(2, 2, 0, 3));
                HBox.setMargin(editbtn, new Insets(2, 3, 0, 2));
                VBox vbox = new VBox(image, name, Labelapprouve, Labelnonapprouve, lock, managebtn);
                vbox.setMaxWidth(1501 / liste.size());
                vbox.setSpacing(8);
                vbox.setPadding(new Insets(0, 20, 0, 20));
                vbox.setAlignment(Pos.CENTER);
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
                ImageView image = new ImageView("./pidev_java/assets/img-1-1.png");
                Label name = new Label(admin.getNom() + " " + admin.getPrenom());
                Label Labelapprouve = new Label(admin.getApprouve() + " Approuvé");
                Label Labelnonapprouve = new Label(admin.getNonApprouve() + " Non Approuvé");
                Label lock = new Label();
                lock.setVisible(false);
                if (admin.isEtat() == false) {
                    lock.setStyle("-fx-text-fill: #e10707 ;"
                            + "-fx-font-weight : Bold ;");
                    lock.setText("This account is locked !");
                    lock.setVisible(true);
                }
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
                            JOptionPane.YES_NO_OPTION, JOptionPane.ERROR_MESSAGE);
                    if (reponse == JOptionPane.YES_OPTION) {
                        adminService.delete(admin);
                        refrechpage();
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
                    stage.setTitle("Modifier admin");
                    stage.initStyle(StageStyle.UTILITY);
                    stage.show();

                });
                HBox managebtn = new HBox(editbtn, deletebtn);
                managebtn.setStyle("-fx-alignment:center");
                HBox.setMargin(deletebtn, new Insets(2, 2, 0, 3));
                HBox.setMargin(editbtn, new Insets(2, 3, 0, 2));
                VBox vbox = new VBox(image, name, Labelapprouve, Labelnonapprouve, lock, managebtn);
                vbox.setMaxWidth(1501 / liste.size());
                vbox.setSpacing(8);
                vbox.setPadding(new Insets(0, 20, 0, 20));
                vbox.setAlignment(Pos.CENTER);
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
                ImageView image = new ImageView("./pidev_java/assets/img-1-1.png");
                Label name = new Label(admin.getNom() + " " + admin.getPrenom());
                Label Labelapprouve = new Label(admin.getApprouve() + " Approuvé");
                Label Labelnonapprouve = new Label(admin.getNonApprouve() + " Non Approuvé");
                Label lock = new Label();
                lock.setVisible(false);
                if (admin.isEtat() == false) {
                    lock.setStyle("-fx-text-fill:#e10707;"
                            + "-fx-font-weight : Bold ;");
                    lock.setText("This account is locked !");
                    lock.setVisible(true);
                }
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
                            JOptionPane.YES_NO_OPTION, JOptionPane.ERROR_MESSAGE);
                    if (reponse == JOptionPane.YES_OPTION) {
                        adminService.delete(admin);
                        refrechpage();
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
                    stage.setTitle("Modifier admin");
                    stage.initStyle(StageStyle.UTILITY);
                    stage.show();

                });
                HBox managebtn = new HBox(editbtn, deletebtn);
                managebtn.setStyle("-fx-alignment:center");
                HBox.setMargin(deletebtn, new Insets(2, 2, 0, 3));
                HBox.setMargin(editbtn, new Insets(2, 3, 0, 2));
                VBox vbox = new VBox(image, name, Labelapprouve, Labelnonapprouve, lock, managebtn);
                vbox.setMaxWidth(1501 / liste.size());
                vbox.setSpacing(8);
                vbox.setPadding(new Insets(0, 20, 0, 20));
                vbox.setAlignment(Pos.CENTER);
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
        stage.setTitle("Créer nouveau admin");
        stage.initStyle(StageStyle.UTILITY);
        stage.show();
    }

    @FXML
    private void Refresh(ActionEvent event) {
        refrechpage();
    }

    void refrechpage() {
        Admins.getChildren().setAll();
        AdminRecHbox.getChildren().setAll();
        AdminEmploiHbox.getChildren().setAll();
        AdminEventHbox.getChildren().setAll();
        loadDataRec();
        loadDataEmploi();
        loadDataEvent();
        recherche.setText("");
        Admins.getChildren().addAll(Label1, AdminRecHbox, LabelReclamationVide, Label2, AdminEmploiHbox, LabelVideEmploi, Label3, AdminEventHbox, LabelVideEvent);
    }

    @FXML
    private void rechercheAdmin(KeyEvent event) {
        recherche(recherche.getText());
        
    }

    public void recherche(String nom){
        Admins.getChildren().setAll();
        System.out.println(recherche.getText());
        AdminService adminService = new AdminService();
        ArrayList<Admin> admins = new ArrayList<>();
        admins = adminService.Recherche(recherche.getText());

        if (admins.isEmpty() == false) {
            HBox hbox = new HBox();
            for (Admin admin : admins) {

                ImageView image = new ImageView("./pidev_java/assets/img-1-1.png");
                Label name = new Label(admin.getNom() + " " + admin.getPrenom());
                Label type = new Label(admin.getType());
                type.setStyle("-fx-text-fill:#00E676;"
                        + "-fx-font-weight : Bold ;"
                        + "-fx-font-size : 15px");
                Label Labelapprouve = new Label(admin.getApprouve() + " Approuvé");
                Label Labelnonapprouve = new Label(admin.getNonApprouve() + " Non Approuvé");
                Label lock = new Label();
                lock.setVisible(false);
                if (admin.isEtat() == false) {
                    lock.setStyle("-fx-text-fill:#e10707;"
                            + "-fx-font-weight : Bold ;");
                    lock.setText("This account is locked !");
                    lock.setVisible(true);
                }
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
                            JOptionPane.YES_NO_OPTION, JOptionPane.ERROR_MESSAGE);
                    if (reponse == JOptionPane.YES_OPTION) {
                        adminService.delete(admin);
                        refrechpage();
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
                    stage.setTitle("Modifier admin");
                    stage.initStyle(StageStyle.UTILITY);
                    stage.show();

                });
                
                
                HBox managebtn = new HBox(editbtn, deletebtn);
                managebtn.setStyle("-fx-alignment:center");
                HBox.setMargin(deletebtn, new Insets(2, 2, 0, 3));
                HBox.setMargin(editbtn, new Insets(2, 3, 0, 2));
                VBox vbox = new VBox(image, name, type, Labelapprouve, Labelnonapprouve, lock, managebtn);
                vbox.setMaxWidth(200);
                vbox.setSpacing(8);
                vbox.setPadding(new Insets(0, 20, 0, 20));
                vbox.setAlignment(Pos.CENTER);
                vbox.autosize();
                hbox.getChildren().add(vbox);
                
            }
            Admins.getChildren().add(hbox);
        } else {
            Label erreur = new Label("Admin n'existe pas !");

            erreur.setStyle("-fx-fill:#e10707;"
                    + "-fx-font-weight : Bold ;");
            Admins.getChildren().add(erreur);
        }
    }

}
