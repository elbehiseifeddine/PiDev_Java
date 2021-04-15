/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev_java.gui.back;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.util.Callback;
import pidev_java.entities.Societe;
import pidev_java.services.SocieteService;

/**
 * FXML Controller class
 *
 * @author ahmed
 */
public class ListeSocieteSuperAdminController implements Initializable {

        ObservableList<Societe> ListSociete = FXCollections.observableArrayList();

    @FXML
    private TableView<Societe> TableSociete;
        @FXML
    private TableColumn<Societe, Integer> id_societe;
    @FXML
    private TableColumn<Societe, String> nom;
    @FXML
    private TableColumn<Societe, String> email;
    @FXML
    private TableColumn<Societe, String> adresse;
    @FXML
    private TableColumn<Societe, String> statue_juridique;
    @FXML
    private TableColumn<Societe, String> date_creation;
    @FXML
    private TableColumn<Societe, Integer> etat;
    @FXML
    private TableColumn<Societe, String> actions;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        loadDataSociete();
    }    
    private void refrechSociete() {
        ListSociete.clear();
        SocieteService service = new SocieteService();
        ListSociete.addAll(service.getAll());
        
    }
    
     private void loadDataSociete() {
        refrechSociete();
        SocieteService Service = new SocieteService();
        id_societe.setCellValueFactory(new PropertyValueFactory<>("id"));
        nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        statue_juridique.setCellValueFactory(new PropertyValueFactory<>("status_juridique"));
        email.setCellValueFactory(new PropertyValueFactory<>("email"));
        adresse.setCellValueFactory(new PropertyValueFactory<>("adresse"));
        etat.setCellValueFactory(new PropertyValueFactory<>("etat"));
        date_creation.setCellValueFactory(new PropertyValueFactory<>("date_creation"));

        Callback<TableColumn<Societe, String>, TableCell<Societe, String>> cellFactory
                = (TableColumn<Societe, String> param) -> {
                    // make cell containing buttons
                    final TableCell<Societe, String> cell = new TableCell<Societe, String>() {
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
                            Societe a = TableSociete.getSelectionModel().getSelectedItem();
                            Service.ActivateSociete(a.getId());
                            refrechSociete();
                        });
                        Deactivate.setOnMouseClicked((event) -> {
                            Societe a = TableSociete.getSelectionModel().getSelectedItem();
                            Service.DeactivateSociete(a.getId());
                            refrechSociete();

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
        TableSociete.setItems(ListSociete);

    }
    
}
