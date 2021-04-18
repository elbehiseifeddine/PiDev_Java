/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev_java.gui.evenement;

import pidev_java.entities.EventLoisir;
import pidev_java.entities.Formation;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import java.io.File;
import java.net.URL;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.stage.FileChooser;
import javafx.util.Callback;
import pidev_java.services.EventService;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class EvenementController implements Initializable {

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
    private TableColumn<EventLoisir, String> labelle;
    @FXML
    private TableColumn<EventLoisir, String> description;
    @FXML
    private TableColumn<EventLoisir, Timestamp> dated;
    @FXML
    private TableColumn<EventLoisir, Timestamp> datef;
    @FXML
    private TableColumn<EventLoisir, String> lieu;
    @FXML
    private TableColumn<EventLoisir, String> domaine;
    @FXML
    private TableColumn<EventLoisir, Integer> nbPart;
    @FXML
    private TableView<EventLoisir> tableevent;
    @FXML
    private Button btnajouterE;
    @FXML
    private Button btnmodifierE;
    @FXML
    private Button btnsuppE;
    @FXML
    private Label textidE;
    @FXML
    private DatePicker textdatefine;
    @FXML
    private DatePicker textdatedebE;
    @FXML
    private TextField textimageE;
    @FXML
    private TableColumn<EventLoisir, String> action;
  

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         EventService es = new EventService();
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
textdatedebE.setDayCellFactory(dayCellFactory);
textdatefine.setDayCellFactory(dayCellFactory);

         List<EventLoisir> le = es.Lister();
        
          ObservableList<EventLoisir> data =
                 FXCollections.observableArrayList(le); 
        
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
         domaine.setCellValueFactory(
                new PropertyValueFactory<>("Domaine"));
       nbPart.setCellValueFactory(
                new PropertyValueFactory<>("nbParticipant"));
       
        Callback<TableColumn<EventLoisir, String>, TableCell<EventLoisir, String>> cellFoctory = (TableColumn<EventLoisir, String> param) -> {
            // make cell containing buttons
            final TableCell<EventLoisir, String> cell = new TableCell<EventLoisir, String>() {
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
                                EventLoisir t = tableevent.getSelectionModel().getSelectedItem();
                                EventService fs = new EventService();
                                fs.Supprimer(t);
                                List<EventLoisir> lf = fs.Lister();

                                ObservableList<EventLoisir> data
                                        = FXCollections.observableArrayList(lf);
                                tableevent.setItems(data);
                                initForm();

                            } catch (Exception ex) {
                                System.out.println("erreur");
                            }

                        });
                        editIcon.setOnMouseClicked((MouseEvent event) -> {
                            
                            EventLoisir t = tableevent.getSelectionModel().getSelectedItem();
                            FXMLLoader loader = new FXMLLoader ();
                            textidE.setText(String.valueOf(t.getId()));
            textlabelle.setText(t.getLabelle());
        textdecription.setText(t.getDescription());
        textlieu.setText(t.getLieu());
        textedated.setText(String.valueOf(t.getDateDebut().toString().split(" ")[1]));
        textdatef.setText(String.valueOf(t.getDateFin().toString().split(" ")[1]));
        textdomaine.setText(t.getDomaine());
        textnb.setText(String.valueOf(t.getNbParticipant()));
        textdatedebE.setValue(LocalDate.parse(t.getDateDebut().toString().split(" ")[0]));
        textdatefine.setValue(LocalDate.parse(t.getDateFin().toString().split(" ")[0]));
        textimageE.setText(t.getImageE());
        
                           
                         
                            

                           

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
 
        tableevent.setItems(data);
    }    

    @FXML
    private void ajouterEvent(MouseEvent event) {
      
    }

    @FXML
    private void ajouterE(MouseEvent event) {
        if((textlabelle.getText().isEmpty())||(textdecription.getText().isEmpty())||(textdomaine.getText().isEmpty()
                )||(textlieu.getText().isEmpty())||(textnb.getText().isEmpty())||(textimageE.getText().isEmpty())){
             Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("erreur");
        
        alert.setContentText("veuillez remplir tous les champs"); 
        alert.showAndWait();
        }
        else{
        String DateDeb=textdatedebE.getValue().toString().replace('/', '-')+" "+textedated.getText();
        String DateFin=textdatefine.getValue().toString().replace('/', '-')+" "+textdatef.getText();
         if(Timestamp.valueOf(DateFin).before(Timestamp.valueOf(DateDeb))){
                    Alert alert1 = new Alert(Alert.AlertType.WARNING);
        alert1.setTitle("erreur");
        
        alert1.setContentText("date debut supperieur à la date fin"); 
        alert1.showAndWait();
                    
                }
                else{
          EventService es = new EventService();
        EventLoisir Ev=new EventLoisir(textlabelle.getText(),textdecription.getText(),textlieu.getText(),Timestamp.valueOf(DateDeb),Timestamp.valueOf(DateFin),textdomaine.getText(),Integer.parseInt(textnb.getText()),true,1,1,textimageE.getText());
        es.Ajouter(Ev);
        
        List<EventLoisir> le = es.Lister();
        ObservableList<EventLoisir> data =
        FXCollections.observableArrayList(le); 
         tableevent.setItems(data);
         initForm();
        }
        }
    }

    @FXML
    private void modifierEvent(MouseEvent event) {
         if((textlabelle.getText().isEmpty())||(textdecription.getText().isEmpty())||(textdomaine.getText().isEmpty()
                )||(textlieu.getText().isEmpty())||(textnb.getText().isEmpty())||(textimageE.getText().isEmpty())){
             Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("erreur");
        
        alert.setContentText("veuillez remplir tous les champs"); 
        alert.showAndWait();
        }
         else{
        String DateDeb=textdatedebE.getValue().toString().replace('/', '-')+" "+textedated.getText();
        String DateFin=textdatefine.getValue().toString().replace('/', '-')+" "+textdatef.getText();
         if(Timestamp.valueOf(DateFin).before(Timestamp.valueOf(DateDeb))){
                    Alert alert1 = new Alert(Alert.AlertType.WARNING);
        alert1.setTitle("erreur");
        
        alert1.setContentText("date debut supperieur à la date fin"); 
        alert1.showAndWait();
                    
                }
                else{
          EventService es = new EventService();
        EventLoisir Ev=new EventLoisir(Integer.parseInt(textidE.getText()),textlabelle.getText(),textdecription.getText(),textlieu.getText(),Timestamp.valueOf(DateDeb),Timestamp.valueOf(DateFin),textdomaine.getText(),Integer.parseInt(textnb.getText()),true,1,1,textimageE.getText());
        es.Modifier(Ev);
        
        List<EventLoisir> le = es.Lister();
        ObservableList<EventLoisir> data =
        FXCollections.observableArrayList(le); 
         tableevent.setItems(data);
         initForm();
         }
         }
    }

    @FXML
    private void supprimerEvent(MouseEvent event) {
        EventService es = new EventService();
        EventLoisir t = tableevent.getSelectionModel().getSelectedItem(); 
        es.Supprimer(t);
         List<EventLoisir> le = es.Lister();
        ObservableList<EventLoisir> data =
        FXCollections.observableArrayList(le); 
         tableevent.setItems(data);
         initForm();
    }

    @FXML
    private void afficherEvent(MouseEvent event) {
        EventLoisir t = tableevent.getSelectionModel().getSelectedItem();         
        textidE.setText(String.valueOf(t.getId()));
        textlabelle.setText(t.getLabelle());
        textdecription.setText(t.getDescription());
        textlieu.setText(t.getLieu());
        textedated.setText(String.valueOf(t.getDateDebut().toString().split(" ")[1]));
        textdatef.setText(String.valueOf(t.getDateFin().toString().split(" ")[1]));
        textdomaine.setText(t.getDomaine());
        textnb.setText(String.valueOf(t.getNbParticipant()));
         textdatedebE.setValue(LocalDate.parse(t.getDateDebut().toString().split(" ")[0]));
        textdatefine.setValue(LocalDate.parse(t.getDateFin().toString().split(" ")[0]));
        textimageE.setText(t.getImageE());
    }
    
    private void initForm(){
          textidE.setText("");
        textlabelle.setText("");
        textdecription.setText("");
        textlieu.setText("");
        textedated.setText("");
        textdatef.setText("");
        textdomaine.setText("");
        textnb.setText("");
         textdatedebE.setValue(null);
        textdatefine.setValue(null);
        textimageE.setText("");
    }

    @FXML
    private void importimage(MouseEvent event) {
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
        textimageE.setText("");
            }else
            textimageE.setText(selected.getName());
        }
    }
    
}
