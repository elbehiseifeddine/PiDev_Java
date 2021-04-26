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
import pidev_java.services.FreelancerService;
import pidev_java.services.ParticipantService;
import pidev_java.services.SocieteService;
import pidev_java.utils.Javamailform;
import pidev_java.utils.TwilioSMS;

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
    private GridPane gridPartE;
    
    private EventService evs=new EventService();
    @FXML
    private Button btnEventAux;
    
      
    private Freelancer Fcon;
    private Societe Scon;
    private int iducon;
    private String typeucon;

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
        
            Fcon=Freelancer.getInstance();
        Scon=Societe.getInstance();
        if(Fcon != null){
            iducon=Fcon.getId();
            typeucon="freelancer";
        }
        else{
            iducon=Scon.getId();
            typeucon="societe";
        }
          scrollMesEvent.setVisible(true);
         gridMesevent.getChildren().clear();
           scrolEvent.setVisible(true);
         gridEvent.getChildren().clear();
         
    ParticipantService ps=new ParticipantService();
         int columnPartF = 0;
        int rowPartF = 1;
        gridPartE.getChildren().clear();
        
        datef.setCellValueFactory(
                new PropertyValueFactory<>("DateFin"));
        
         try {
            
           List<EventLoisir> MesEvent=es.ListerparU(iducon,typeucon);
            for (int i = 0; i < MesEvent.size(); i++) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/pidev_java/gui/evenement/itemEvent.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();

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

           }
           try {
            
           List<EventLoisir> Event=es.Lister(iducon,typeucon);
            for (int i = 0; i < Event.size(); i++) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/pidev_java/gui/evenement/itemE.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();

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

           }
         
         
         try {
            List<EventLoisir> MesPartE=new ArrayList<>();
           List<Participant> MesParticipation=ps.Lister(typeucon,"evenement",1);
            for (int i = 0; i < MesParticipation.size(); i++) {
            
            EventLoisir E=evs.FindParId(MesParticipation.get(i).getEl().getId());
            MesPartE.add(E);
            }
            for (int i = 0; i < MesPartE.size(); i++) {
            
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/pidev_java/gui/evenement/itemPartE.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();

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
    
    
    
     public void delete(EventLoisir event){
          ParticipantService pser=new ParticipantService();
        FreelancerService FS=new FreelancerService();
        SocieteService SS=new SocieteService();
        String email="";
        List<Participant> Part=pser.ListerParEvent("evenement", event.getId());
        if(Part != null){
            for(int l=0;l<Part.size();l++){
                if(Part.get(l).getTypeU().equals("freelancer")){
                    email=FS.FindparID(Part.get(l).getF().getId()).getEmail();
                }
                else{
                    email=SS.FindparID(Part.get(l).getS().getId()).getEmail();
                }
                
                //envoie du l'email
                
            }
            pser.SupprimerParEvent("evenement", event.getId());
            
        }
         es.Supprimer(event);
         gridMesevent.getChildren().clear();
           gridEvent.getChildren().clear();
         
           int columnMesEvent = 0;
        int rowMesEvent = 1;
         int columnEvent = 0;
        int rowEvent = 1;
        
         try {
            
           List<EventLoisir> MesEvent=es.ListerparU(iducon,typeucon);
            for (int i = 0; i < MesEvent.size(); i++) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/pidev_java/gui/evenement/itemEvent.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();

                ItemEventController itemeController = fxmlLoader.getController();
                
                itemeController.setData(MesEvent.get(i),this);

                 if (columnMesEvent == 2) {
                    columnMesEvent = 0;
                    rowMesEvent++;
                }

                gridMesevent.add(anchorPane, columnMesEvent, rowMesEvent++); //(child,column,row)
                //set grid width
                gridMesevent.setMinWidth(Region.USE_COMPUTED_SIZE);
                gridMesevent.setPrefWidth(Region.USE_COMPUTED_SIZE);
                gridMesevent.setMaxWidth(Region.USE_PREF_SIZE);

                //set grid height
                gridMesevent.setMinHeight(Region.USE_COMPUTED_SIZE);
                gridMesevent.setPrefHeight(Region.USE_COMPUTED_SIZE);
               gridMesevent.setMaxHeight(Region.USE_PREF_SIZE);

                GridPane.setMargin(anchorPane, new Insets(10));
            }
        } catch (Exception e) {
                        System.out.println(e.getMessage());

           }
           try {
            
           List<EventLoisir> Event=es.Lister(iducon,typeucon);
            for (int i = 0; i < Event.size(); i++) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/pidev_java/gui/evenement/itemE.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();

                ItemEController itemEController = fxmlLoader.getController();
                
                itemEController.setData(Event.get(i),this);

                 if (columnEvent == 2) {
                    columnEvent = 0;
                    rowEvent++;
                }

                gridEvent.add(anchorPane, columnEvent, rowEvent++); //(child,column,row)
                //set grid width
                gridEvent.setMinWidth(Region.USE_COMPUTED_SIZE);
                gridEvent.setPrefWidth(Region.USE_COMPUTED_SIZE);
                gridEvent.setMaxWidth(Region.USE_PREF_SIZE);

                //set grid height
                gridEvent.setMinHeight(Region.USE_COMPUTED_SIZE);
                gridEvent.setPrefHeight(Region.USE_COMPUTED_SIZE);
               gridEvent.setMaxHeight(Region.USE_PREF_SIZE);

                GridPane.setMargin(anchorPane, new Insets(10));
            }
        } catch (Exception e) {
                        System.out.println(e.getMessage());

           }
    }
    
    
     public void Ajouter(EventLoisir event){
         es.Ajouter(event,iducon,typeucon);
         gridMesevent.getChildren().clear();
                  gridEvent.getChildren().clear();
          
            int columnMesEvent = 0;
        int rowMesEvent = 1;
         int columnEvent = 0;
        int rowEvent = 1;
        
         try {
            
           List<EventLoisir> MesEvent=es.ListerparU(iducon,typeucon);
            for (int i = 0; i < MesEvent.size(); i++) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/pidev_java/gui/evenement/itemEvent.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();

                ItemEventController itemeController = fxmlLoader.getController();
                
                itemeController.setData(MesEvent.get(i),this);

                 if (columnMesEvent == 2) {
                    columnMesEvent = 0;
                    rowMesEvent++;
                }

                gridMesevent.add(anchorPane, columnMesEvent, rowMesEvent++); //(child,column,row)
                //set grid width
                gridMesevent.setMinWidth(Region.USE_COMPUTED_SIZE);
                gridMesevent.setPrefWidth(Region.USE_COMPUTED_SIZE);
                gridMesevent.setMaxWidth(Region.USE_PREF_SIZE);

                //set grid height
                gridMesevent.setMinHeight(Region.USE_COMPUTED_SIZE);
                gridMesevent.setPrefHeight(Region.USE_COMPUTED_SIZE);
               gridMesevent.setMaxHeight(Region.USE_PREF_SIZE);

                GridPane.setMargin(anchorPane, new Insets(10));
            }
        } catch (Exception e) {
                        System.out.println(e.getMessage());

           }
         
         
           try {
            
           List<EventLoisir> Event=es.Lister(iducon,typeucon);
            for (int i = 0; i < Event.size(); i++) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/pidev_java/gui/evenement/itemE.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();

                ItemEController itemEController = fxmlLoader.getController();
                
                itemEController.setData(Event.get(i),this);

                 if (columnEvent == 2) {
                    columnEvent = 0;
                    rowEvent++;
                }

                gridEvent.add(anchorPane, columnEvent, rowEvent++); //(child,column,row)
                //set grid width
                gridEvent.setMinWidth(Region.USE_COMPUTED_SIZE);
                gridEvent.setPrefWidth(Region.USE_COMPUTED_SIZE);
                gridEvent.setMaxWidth(Region.USE_PREF_SIZE);

                //set grid height
                gridEvent.setMinHeight(Region.USE_COMPUTED_SIZE);
                gridEvent.setPrefHeight(Region.USE_COMPUTED_SIZE);
               gridEvent.setMaxHeight(Region.USE_PREF_SIZE);

                GridPane.setMargin(anchorPane, new Insets(10));
            }
        } catch (Exception e) {
                        System.out.println(e.getMessage());

           }
         
    }
     
     
     
     
      public void Update(EventLoisir event){
         es.Modifier(event);
         gridMesevent.getChildren().clear();
                  gridEvent.getChildren().clear();
             int columnMesEvent = 0;
        int rowMesEvent = 1;
         int columnEvent = 0;
        int rowEvent = 1;
        
         try {
            
           List<EventLoisir> MesEvent=es.ListerparU(iducon,typeucon);
            for (int i = 0; i < MesEvent.size(); i++) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/pidev_java/gui/evenement/itemEvent.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();

                ItemEventController itemeController = fxmlLoader.getController();
                
                itemeController.setData(MesEvent.get(i),this);

                 if (columnMesEvent == 2) {
                    columnMesEvent = 0;
                    rowMesEvent++;
                }

                gridMesevent.add(anchorPane, columnMesEvent, rowMesEvent++); //(child,column,row)
                //set grid width
                gridMesevent.setMinWidth(Region.USE_COMPUTED_SIZE);
                gridMesevent.setPrefWidth(Region.USE_COMPUTED_SIZE);
                gridMesevent.setMaxWidth(Region.USE_PREF_SIZE);

                //set grid height
                gridMesevent.setMinHeight(Region.USE_COMPUTED_SIZE);
                gridMesevent.setPrefHeight(Region.USE_COMPUTED_SIZE);
               gridMesevent.setMaxHeight(Region.USE_PREF_SIZE);

                GridPane.setMargin(anchorPane, new Insets(10));
            }
        } catch (Exception e) {
                        System.out.println(e.getMessage());

           }
           try {
            
           List<EventLoisir> Event=es.Lister(iducon,typeucon);
            for (int i = 0; i < Event.size(); i++) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/pidev_java/gui/evenement/itemE.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();

                ItemEController itemEController = fxmlLoader.getController();
                
                itemEController.setData(Event.get(i),this);

                 if (columnEvent == 2) {
                    columnEvent = 0;
                    rowEvent++;
                }

                gridEvent.add(anchorPane, columnEvent, rowEvent++); //(child,column,row)
                //set grid width
                gridEvent.setMinWidth(Region.USE_COMPUTED_SIZE);
                gridEvent.setPrefWidth(Region.USE_COMPUTED_SIZE);
                gridEvent.setMaxWidth(Region.USE_PREF_SIZE);

                //set grid height
                gridEvent.setMinHeight(Region.USE_COMPUTED_SIZE);
                gridEvent.setPrefHeight(Region.USE_COMPUTED_SIZE);
               gridEvent.setMaxHeight(Region.USE_PREF_SIZE);

                GridPane.setMargin(anchorPane, new Insets(10));
            }
        } catch (Exception e) {
                        System.out.println(e.getMessage());

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
    
    public void deleteP(EventLoisir ev){
        ParticipantService ps=new ParticipantService();
         int columnPartF = 0;
        int rowPartF = 1;
        gridPartE.getChildren().clear();
        Participant P=new Participant("evenement", typeucon, new Freelancer(1), new Societe(), new Formation(), ev);
        ps.Supprimer(P);
         try {
            List<EventLoisir> MesPartE=new ArrayList<>();
           List<Participant> MesParticipation=ps.Lister(typeucon,"evenement",1);
            for (int i = 0; i < MesParticipation.size(); i++) {
            
            EventLoisir E=evs.FindParId(MesParticipation.get(i).getEl().getId());
            MesPartE.add(E);
            }
            for (int i = 0; i < MesPartE.size(); i++) {
            
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/pidev_java/gui/evenement/itemPartE.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();

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
    
    
    
     public void participate(EventLoisir E){
       
         int columnPartF = 0;
        int rowPartF = 1;
        gridPartE.getChildren().clear();
        ParticipantService ps=new ParticipantService();
        Formation F=new Formation();
        Societe s=new Societe();
        Freelancer Fr=new Freelancer();
         if(typeucon.equals("freelancer")){
             Fr=new Freelancer(iducon);
        }
        else{
                s=new Societe(iducon);
        }
       
        Participant P=new Participant("evenement", typeucon, Fr, s, F, E);
        ps.Ajouter(P);
         try {
            List<EventLoisir> MesPartE=new ArrayList<>();
           List<Participant> MesParticipation=ps.Lister(typeucon,"evenement",1);
            for (int i = 0; i < MesParticipation.size(); i++) {
            
            EventLoisir Ev=evs.FindParId(MesParticipation.get(i).getEl().getId());
            MesPartE.add(Ev);
            }
            for (int i = 0; i < MesPartE.size(); i++) {
            
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/pidev_java/gui/evenement/itemPartE.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();

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

    @FXML
    private void showEventAux(MouseEvent event) {
         List<EventLoisir> MesEventAux=es.ListerParDate();
          try {
                 FXMLLoader loader1 = new FXMLLoader ();
                 loader1.setLocation(getClass().getResource("/pidev_java/gui/evenement/MapEAux.fxml"));
                
                 Parent  parent = (Parent)loader1.load();
                  Stage stage = new Stage();
                 stage.setScene(new Scene(parent));
                  stage.show();
                   
                  MapEAuxController auxc=loader1.getController();
                 auxc.inti(MesEventAux,this);
                
             } catch (IOException ex) {
                System.out.println("erreur");
             }
    }
    
}
