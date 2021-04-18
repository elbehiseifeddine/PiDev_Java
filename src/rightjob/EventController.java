/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rightjob;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class EventController implements Initializable {

    @FXML
    private TextField textlabelle;
    @FXML
    private TextField textdecription;
    @FXML
    private TextField textedated;
    @FXML
    private TextField textdatef;
    @FXML
    private TextField textlieu;
    @FXML
    private TextField textdomaine;
    @FXML
    private TextField textnb;
    @FXML
    private Button btnajouterE;
    @FXML
    private Button btnmodifierE;
    @FXML
    private Button btnsuppE;
    @FXML
    private TableView<?> tableevent;
    @FXML
    private TableColumn<?, ?> labelle;
    @FXML
    private TableColumn<?, ?> description;
    @FXML
    private TableColumn<?, ?> dated;
    @FXML
    private TableColumn<?, ?> datef;
    @FXML
    private TableColumn<?, ?> lieu;
    @FXML
    private TableColumn<?, ?> domaine;
    @FXML
    private TableColumn<?, ?> nbPart;
    @FXML
    private TableColumn<?, ?> action;
    @FXML
    private Label textidE;
    @FXML
    private DatePicker textdatefine;
    @FXML
    private DatePicker textdatedebE;
    @FXML
    private TextField textimageE;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void ajouterEvent(MouseEvent event) {
    }

    @FXML
    private void ajouterE(MouseEvent event) {
    }

    @FXML
    private void modifierEvent(MouseEvent event) {
    }

    @FXML
    private void supprimerEvent(MouseEvent event) {
    }

    @FXML
    private void afficherEvent(MouseEvent event) {
    }

    @FXML
    private void importimage(MouseEvent event) {
    }
    
}
