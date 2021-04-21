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
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javax.swing.JOptionPane;
import pidev_java.entities.Admin;
import pidev_java.services.AdminService;

/**
 * FXML Controller class
 *
 * @author ahmed
 */
public class CreateAdminController implements Initializable {

    ObservableList<String> ListType = FXCollections.
            observableArrayList("Admin des reclamations", "Admin des events", "Admin des emplois");
    ObservableList<String> ListEtat = FXCollections.
            observableArrayList("Active", "Inactive");

    @FXML
    private TextField nom;
    @FXML
    private TextField prenom;
    @FXML
    private TextField login;
    @FXML
    private TextField pass;
    @FXML
    private ChoiceBox<String> type;
    @FXML
    private ChoiceBox<String> etat;
    @FXML
    private Button btnValider;
    @FXML
    private Button btnReset;

    private boolean update;
    int id_admin;
    @FXML
    private Label nom_error;
    @FXML
    private Label prenom_error;
    @FXML
    private Label email_error;
    @FXML
    private Label pass_error;
    @FXML
    private Label type_error;
    @FXML
    private Label etat_error;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        type.setValue("Admin des reclamations");
        type.setItems(ListType);
        etat.setValue("Active");
        etat.setItems(ListEtat);
    }

    @FXML
    private void AjouterAdmin(ActionEvent event) {
        if (update == false) {
            if (nom.getText().equals("")) {
                nom_error.setText("Veuillez saisir le nom");
            } else {
                if (prenom.getText().equals("")) {
                    prenom_error.setText("Veuillez saisir le prenom");
                } else {
                    if (login.getText().equals("")) {
                        email_error.setText("Veuillez saisir un email");
                    } else {
                        if (pass.getText().equals("")) {
                            pass_error.setText("Veuillez saisir le mot de passe");
                        } else {
                            if (type.getSelectionModel().getSelectedItem().equals("")) {
                                type_error.setText("Veuillez schoisir un type");
                            } else {
                                if (etat.getSelectionModel().getSelectedItem().equals("")) {
                                    etat_error.setText("Veuillez schoisir un type");
                                } else {
                                    AdminService service = new AdminService();
                                    if (service.AdminExiste(login.getText())) {
                                        JOptionPane.showMessageDialog(null, "Admin existe déja !");
                                    } else {
                                        Admin a = new Admin();
                                        a.setNom(nom.getText());
                                        a.setPrenom(prenom.getText());
                                        a.setLogin(login.getText());
                                        a.setPass(pass.getText());
                                        a.setType(type.getSelectionModel().getSelectedItem());
                                        if (etat.getSelectionModel().getSelectedItem().equals("Active")) {
                                            a.setEtat(true);
                                        } else {
                                            a.setEtat(false);
                                        }
                                        System.out.println("helooooooooooooooooooooo");

                                        service.add(a);
                                        JOptionPane.showMessageDialog(null, "Admin Ajouté avec succes!");
                                        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                                        stage.close();
                                    }
                                }
                            }
                        }
                    }

                }
            }
        } else {
            System.out.println("bbbbb " + id_admin);
            AdminService service = new AdminService();
            Admin a = service.find(id_admin);
            String OldType = a.getType();
            a.setId(id_admin);
            a.setNom(nom.getText());
            a.setPrenom(prenom.getText());
            a.setLogin(login.getText());
            a.setPass(pass.getText());
            a.setType(type.getSelectionModel().getSelectedItem());
            if (etat.getSelectionModel().getSelectedItem().equals("Active")) {
                a.setEtat(true);
            } else {
                a.setEtat(false);
            }
            System.out.println("aaaaaaa " + a);
            service.update(a, OldType);

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.close();
        }
    }

    @FXML
    private void reset(ActionEvent event) {
        nom.setText("");
        prenom.setText("");
        login.setText("");
        pass.setText("");

    }

    void setTextField(Admin a) {
        System.out.println("aaaaaaaaa " + a.getId());
        id_admin = a.getId();
        nom.setText(a.getNom());
        prenom.setText(a.getPrenom());
        login.setText(a.getLogin());
        pass.setText(a.getPass());
        type.setValue(a.getType());
        if (a.isEtat() == true) {
            etat.setValue("Active");
        } else {
            etat.setValue("Inactive");
        }
    }

    void setUpdate(boolean b) {
        this.update = b;

    }
}
