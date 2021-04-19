/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev_java.gui.formation;

import pidev_java.entities.Formation;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import java.net.URL;
import java.sql.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.util.Callback;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import java.io.File;
import java.sql.SQLException;
import static java.time.Instant.MAX;
import static java.time.LocalDate.MAX;
import java.time.Month;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;

import javafx.scene.control.Alert;
import javafx.scene.control.DateCell;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.stage.FileChooser;
import pidev_java.services.FormationService;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class FormationController implements Initializable {

    @FXML
    private Button btnajouter;
    @FXML
    private Button btnmodifier;
    @FXML
    private Button btnsupp;
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
    private TextField textmontant;
    @FXML
    private TableView<Formation> tableformation;
    @FXML
    private TableColumn<Formation, String> labelle;
    @FXML
    private TableColumn<Formation, String> description;
    @FXML
    private TableColumn<Formation, Date> dated;
    @FXML
    private TableColumn<Formation, Date> datef;
    @FXML
    private TableColumn<Formation, String> lieu;
    @FXML
    private TableColumn<Formation, String> domaine;
    @FXML
    private TableColumn<Formation, Float> montant;
    @FXML
    private Label textid;
    @FXML
    private DatePicker textdatedeb;
    @FXML
    private DatePicker textdatefin;
    @FXML
    private TableColumn<Formation, String> action;
    @FXML
    private TextField textimage;
    @FXML
    private Label labellabel;
    @FXML
    private Label labeldesc;
    @FXML
    private Label labeldatedeb;
    @FXML
    private Label labelheuredeb;
    @FXML
    private Label labeldatefin;
    @FXML
    private Label labelheurefin;
    @FXML
    private Label labellieu;
    @FXML
    private Label labeldaomaine;
    @FXML
    private Label labelmontant;
    @FXML
    private Label labelimage;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        FormationService fs = new FormationService();
        DatePicker minDate = new DatePicker(); // DatePicker, used to define max date available, you can also create another for minimum date
minDate.setValue(LocalDate.now()); // Max date available will be 2015-01-01
final Callback<DatePicker, DateCell> dayCellFactory;

dayCellFactory = (final DatePicker datePicker) -> new DateCell() {
    @Override
    public void updateItem(LocalDate item, boolean empty) {
        super.updateItem(item, empty);
        if (item.isBefore(minDate.getValue())) { //Disable all dates after required date
            setDisable(true);
            setStyle("-fx-background-color: #ffc0cb;"); //To set background on different color
        }
    }
};
//Finally, we just need to update our DatePicker cell factory as follow:
textdatedeb.setDayCellFactory(dayCellFactory);
textdatefin.setDayCellFactory(dayCellFactory);
      

        List<Formation> lf = fs.Lister();

        ObservableList<Formation> data
                = FXCollections.observableArrayList(lf);

        labelle.setCellValueFactory(
                new PropertyValueFactory<>("Labelle"));

        description.setCellValueFactory(
                new PropertyValueFactory<>("Description"));

        dated.setCellValueFactory(
                new PropertyValueFactory<>("DateDebut"));

        datef.setCellValueFactory(
                new PropertyValueFactory<>("DateFin"));

        lieu.setCellValueFactory(
                new PropertyValueFactory<>("Lieu"));
        /*domaine.setCellValueFactory(
                new PropertyValueFactory<>("Domaine"));*/
        montant.setCellValueFactory(
                new PropertyValueFactory<>("Montant"));

        Callback<TableColumn<Formation, String>, TableCell<Formation, String>> cellFoctory = (TableColumn<Formation, String> param) -> {
            // make cell containing buttons
            final TableCell<Formation, String> cell = new TableCell<Formation, String>() {
                @Override
                public void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);
                    //that cell created only on non-empty rows
                    if (empty) {
                        setGraphic(null);
                        setText(null);

                    } else {

                        FontAwesomeIconView deleteIcon = new FontAwesomeIconView(FontAwesomeIcon.TRASH);
                        FontAwesomeIconView editIcon = new FontAwesomeIconView(FontAwesomeIcon.PENCIL_SQUARE);

                        deleteIcon.setStyle(
                                " -fx-cursor: hand ;"
                                + "-glyph-size:28px;"
                                + "-fx-fill:#ff1744;"
                        );
                        editIcon.setStyle(
                                " -fx-cursor: hand ;"
                                + "-glyph-size:28px;"
                                + "-fx-fill:#00E676;"
                        );
                        deleteIcon.setOnMouseClicked((MouseEvent event) -> {

                            try {
                                Formation t = tableformation.getSelectionModel().getSelectedItem();
                                FormationService fs = new FormationService();
                                fs.Supprimer(t);
                                List<Formation> lf = fs.Lister();

                                ObservableList<Formation> data
                                        = FXCollections.observableArrayList(lf);
                                tableformation.setItems(data);
                                initForm();

                            } catch (Exception ex) {
                                System.out.println("erreur");
                            }

                        });
                        editIcon.setOnMouseClicked((MouseEvent event) -> {
                            
                            Formation t = tableformation.getSelectionModel().getSelectedItem();
                            FXMLLoader loader = new FXMLLoader ();
                            textid.setText(String.valueOf(t.getId()));
            textlabelle.setText(t.getLabelle());
        textdecription.setText(t.getDescription());
        textlieu.setText(t.getLieu());
        textedated.setText(String.valueOf(t.getDateDebut().toString().split(" ")[1]));
        textdatef.setText(String.valueOf(t.getDateFin().toString().split(" ")[1]));
        textdomaine.setText(t.getDomaine());
        textmontant.setText(String.valueOf(t.getMontant()));
        textdatedeb.setValue(LocalDate.parse(t.getDateDebut().toString().split(" ")[0]));
        textdatefin.setValue(LocalDate.parse(t.getDateFin().toString().split(" ")[0]));
        textimage.setText(t.getImageF());
                           
                         
                            

                           

                        });


                        HBox managebtn = new HBox(editIcon, deleteIcon);
                        managebtn.setStyle("-fx-alignment:center");
                        HBox.setMargin(deleteIcon, new Insets(2, 2, 0, 3));
                        HBox.setMargin(editIcon, new Insets(2, 3, 0, 2));

                        setGraphic(managebtn);

                        setText(null);

                    }
                }

            };

            return cell;
        };
        action.setCellFactory(cellFoctory);

        tableformation.setItems(data);
        
    }

    @FXML
    private void ajouterformation(MouseEvent event) {
        if((textlabelle.getText().isEmpty())||(textdecription.getText().isEmpty())||(textdomaine.getText().isEmpty()
                )||(textlieu.getText().isEmpty())||(textmontant.getText().isEmpty())||(textimage.getText().isEmpty())){
             Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("erreur");
        
        alert.setContentText("veuillez remplir tous les champs"); 
        alert.showAndWait();
        }
        else{
            
        
        String DateDeb = textdatedeb.getValue().toString().replace('/', '-') + " " + textedated.getText();
        String DateFin = textdatefin.getValue().toString().replace('/', '-') + " " + textdatef.getText();
        
                if(Timestamp.valueOf(DateFin).before(Timestamp.valueOf(DateDeb))){
                    Alert alert1 = new Alert(Alert.AlertType.WARNING);
        alert1.setTitle("erreur");
        
        alert1.setContentText("date debut supperieur à la date fin"); 
        alert1.showAndWait();
                    
                }
                else{
        FormationService fs = new FormationService();
        Formation Fo = new Formation(textlabelle.getText(), textdecription.getText(), textlieu.getText(), Timestamp.valueOf(DateDeb), Timestamp.valueOf(DateFin), textdomaine.getText(), Float.parseFloat(textmontant.getText()), true, 1, 1,textimage.getText());
        fs.Ajouter(Fo);
        List<Formation> lf = fs.Lister();

        ObservableList<Formation> data
                = FXCollections.observableArrayList(lf);
        tableformation.setItems(data);
        initForm();
        }
        }

    }

    @FXML
    private void modifierformation(MouseEvent event) {
        if((textlabelle.getText().isEmpty())||(textdecription.getText().isEmpty())||(textdomaine.getText().isEmpty()
                )||(textlieu.getText().isEmpty())||(textmontant.getText().isEmpty())||(textimage.getText().isEmpty())){
             Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("erreur");
        
            alert.setContentText("veuillez remplir tous les champs"); 
            alert.showAndWait();
        }
        else{
        String DateDeb = textdatedeb.getValue().toString().replace('/', '-') + " " + textedated.getText();
        String DateFin = textdatefin.getValue().toString().replace('/', '-') + " " + textdatef.getText();
         if(Timestamp.valueOf(DateFin).before(Timestamp.valueOf(DateDeb))){
                    Alert alert1 = new Alert(Alert.AlertType.WARNING);
        alert1.setTitle("erreur");
        
        alert1.setContentText("date debut supperieur à la date fin"); 
        alert1.showAndWait();
                    
                }
                else{
        FormationService fs = new FormationService();
        Formation Fo = new Formation(Integer.parseInt(textid.getText()), textlabelle.getText(), textdecription.getText(), textlieu.getText(), Timestamp.valueOf(DateDeb), Timestamp.valueOf(DateFin), textdomaine.getText(), Float.parseFloat(textmontant.getText()), true, 1, 1,textimage.getText());
        fs.Modifier(Fo);
        List<Formation> lf = fs.Lister();

        ObservableList<Formation> data
                = FXCollections.observableArrayList(lf);
        tableformation.setItems(data);
        initForm();
        }
        }

    }

    @FXML
    private void supprimerformation(MouseEvent event) {
        Formation t = tableformation.getSelectionModel().getSelectedItem();
        FormationService fs = new FormationService();
        fs.Supprimer(t);
        List<Formation> lf = fs.Lister();

        ObservableList<Formation> data
                = FXCollections.observableArrayList(lf);
        tableformation.setItems(data);
        initForm();

    }

    @FXML
    private void afficherform(MouseEvent event) {
        Formation t = tableformation.getSelectionModel().getSelectedItem();
        textid.setText(String.valueOf(t.getId()));
        textlabelle.setText(t.getLabelle());
        textdecription.setText(t.getDescription());
        textlieu.setText(t.getLieu());
        textedated.setText(String.valueOf(t.getDateDebut().toString().split(" ")[1]));
        textdatef.setText(String.valueOf(t.getDateFin().toString().split(" ")[1]));
        textdomaine.setText(t.getDomaine());
        textmontant.setText(String.valueOf(t.getMontant()));
        textdatedeb.setValue(LocalDate.parse(t.getDateDebut().toString().split(" ")[0]));
        textdatefin.setValue(LocalDate.parse(t.getDateFin().toString().split(" ")[0]));
        textimage.setText(t.getImageF());
    }

    private void initForm() {
        textid.setText("");
        textlabelle.setText("");
        textdecription.setText("");
        textlieu.setText("");
        textedated.setText("");
        textdatef.setText("");
        textdomaine.setText("");
        textmontant.setText("");
        textimage.setText("");
        textdatedeb.setValue(null);
        textdatefin.setValue(null);
    }

    @FXML
    private void importimg(MouseEvent event) {
        FileChooser fc = new FileChooser();
        File selected = fc.showOpenDialog(null);
        if(selected !=null )
        {
            String extension = selected.getAbsolutePath().substring(selected.getAbsolutePath().length()-3,selected.getAbsolutePath().length());
            System.out.println(extension);
            if(!extension.equals( "jpg") && !extension.equals("png"))
            {
                Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("image invalide");
        
        alert.setContentText("Invalid picture format (only jgp/png available)"); 
     
        alert.showAndWait();
        textimage.setText("");
            }else
            textimage.setText(selected.getName());
        }
    }

    @FXML
    private void controlemontant(KeyEvent event) {
        if(textmontant.getText().isEmpty()){
            
        }
        else{
        FormationService fs=new FormationService();
        if(fs.estUnEntier(textmontant.getText())){
            labelmontant.setText("");
        }
        else{
            labelmontant.setText("entrer un entier");
        }
    }
    }

}
