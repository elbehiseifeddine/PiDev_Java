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
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;
import javax.swing.JOptionPane;
import pidev_java.entities.Freelancer;
import pidev_java.services.FreelancerService;

/**
 * FXML Controller class
 *
 * @author ahmed
 */
public class ListeFreelancersSuperAdminController implements Initializable {

    
    ObservableList<Freelancer> ListFreelancer = FXCollections.observableArrayList();
    @FXML
    private TableView<Freelancer> TableFreelancer;
    @FXML
    private TableColumn<Freelancer, Integer> id_freelancer;
    @FXML
    private TableColumn<Freelancer, String> nom;
    @FXML
    private TableColumn<Freelancer, String> prenom;
    @FXML
    private TableColumn<Freelancer, String> email;
    @FXML
    private TableColumn<Freelancer, String> adresse;
    @FXML
    private TableColumn<Freelancer, Integer> etat;
    @FXML
    private TableColumn<Freelancer, String> Competance;
    @FXML
    private TableColumn<Freelancer, String> sexe;
    @FXML
    private TableColumn<Freelancer, String> date_creation;
    @FXML
    private TableColumn<Freelancer, String> actions;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        loadDataFreelancer();
    }    
    private void refrechFreelancer() {
        ListFreelancer.clear();
        FreelancerService service = new FreelancerService();
        ListFreelancer.addAll(service.getAll());
        

    }
    
    private void loadDataFreelancer() {
        refrechFreelancer();
        FreelancerService Service = new FreelancerService();
        id_freelancer.setCellValueFactory(new PropertyValueFactory<>("id"));
        nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        prenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        email.setCellValueFactory(new PropertyValueFactory<>("email"));
        adresse.setCellValueFactory(new PropertyValueFactory<>("adresse"));
        Competance.setCellValueFactory(new PropertyValueFactory<>("competences"));
        etat.setCellValueFactory(new PropertyValueFactory<>("etat"));
        sexe.setCellValueFactory(new PropertyValueFactory<>("sexe"));
        date_creation.setCellValueFactory(new PropertyValueFactory<>("date_creation"));

        Callback<TableColumn<Freelancer, String>, TableCell<Freelancer, String>> cellFactory
                = (TableColumn<Freelancer, String> param) -> {
                    // make cell containing buttons
                    final TableCell<Freelancer, String> cell = new TableCell<Freelancer, String>() {
                @Override
                protected void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);
                    if (empty) {
                        setGraphic(null);
                        setText(null);

                    } else {
                        Button Activate = new Button("Activate");
                        Button Deactivate = new Button("Deactivate");
                        Activate.setStyle(
                                " -fx-cursor: hand ;"
                                + "-glyph-size:28px;"
                                + "-fx-fill:#ff1744;"
                        );
                        Deactivate.setStyle(
                                " -fx-cursor: hand ;"
                                + "-glyph-size:28px;"
                                + "-fx-fill:#00E676;"
                        );
                        Activate.setOnMouseClicked((event) -> {
                            Freelancer a = TableFreelancer.getSelectionModel().getSelectedItem();
                            System.out.println(a.getId()+"aaaaaaaaaaaaaaa");
                            Service.ActivateFreelancer(a.getId());
                            refrechFreelancer();
                        });
                        Deactivate.setOnMouseClicked((event) -> {
                            Freelancer a = TableFreelancer.getSelectionModel().getSelectedItem();
                            Service.DeactivateFreelancer(a.getId());
                            refrechFreelancer();

                        });
                        HBox managebtn = new HBox(Activate, Deactivate);
                        managebtn.setStyle("-fx-alignment:center");
                        HBox.setMargin(Activate, new Insets(2, 2, 0, 3));
                        HBox.setMargin(Deactivate, new Insets(2, 3, 0, 2));
                        setGraphic(managebtn);

                        setText(null);
                    }
                }
            };
                    return cell;
                };
        actions.setCellFactory(cellFactory);
        TableFreelancer.setItems(ListFreelancer);

    }
    
}
