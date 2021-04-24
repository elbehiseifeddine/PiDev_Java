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
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;


import javafx.scene.control.ScrollPane;
import javafx.scene.control.Tab;
import javafx.scene.input.MouseEvent;

import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import jdk.internal.dynalink.support.TypeConverterFactory;
import pidev_java.entities.EventLoisir;
import pidev_java.entities.Freelancer;
import pidev_java.entities.Participant;
import pidev_java.entities.Societe;

import pidev_java.services.FormationService;
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
    private GridPane gridPartF;
    @FXML
    private Button btnFormationAux;
    
    private Freelancer Fcon;
    private Societe Scon;
    private int iducon;
    private String typeucon;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
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
      
            scrolPartF.setVisible(true);
            gridPartF.getChildren().clear();
            scrolFormation.setVisible(true);
         gridFormation.getChildren().clear();
         
         scrolMesFormation.setVisible(true);
         gridMesFormation.getChildren().clear();
         int columnMesForm = 0;
        int rowMesForm = 1;
         int columnForm = 0;
        int rowForm = 1;
         int columnPartF = 0;
        int rowPartF = 1;
        
         try {
            
           List<Formation> MesFormation=fs.ListerparU(iducon,typeucon);
            for (int i = 0; i < MesFormation.size(); i++) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/pidev_java/gui/formation/item.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();

        List<Formation> lf = fs.Lister();

        ObservableList<Formation> data
                = FXCollections.observableArrayList(lf);

        labelle.setCellValueFactory(
                new PropertyValueFactory<>("Labelle"));

        description.setCellValueFactory(
                new PropertyValueFactory<>("Description"));

        dated.setCellValueFactory(
                new PropertyValueFactory<>("DateDebut"));

           }
         
         
         try {
            
           List<Formation> Formation=fs.Lister(iducon,typeucon);
            for (int i = 0; i < Formation.size(); i++) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/pidev_java/gui/formation/itemF.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();

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

           }
         
         
         try {
            List<Formation> MesPartF=new ArrayList<>();
           List<Participant> MesParticipation=ps.Lister(typeucon,"formation",1);
            for (int i = 0; i < MesParticipation.size(); i++) {
              
            Formation F=fs.FindParId(MesParticipation.get(i).getFormation().getId());
         
            MesPartF.add(F);
            }
            for (int i = 0; i < MesPartF.size(); i++) {
            
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/pidev_java/gui/formation/itemPartF.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();

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

           }
         
         
         
         
         
    }
    
    public void delete(Formation Form){
        ParticipantService pser=new ParticipantService();
        FreelancerService FS=new FreelancerService();
        SocieteService SS=new SocieteService();
        String email="";
        List<Participant> Part=pser.ListerParEvent("formation", Form.getId());
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
            pser.SupprimerParEvent("formation", Form.getId());
            
        }
        
         fs.Supprimer(Form);
         gridMesFormation.getChildren().clear();
         gridFormation.getChildren().clear();
         int columnMesForm = 0;
        int rowMesForm = 1;
         int columnForm = 0;
        int rowForm = 1;
        
        try {
             List<Formation> MesFormation=fs.ListerparU(iducon,typeucon);
          
            for (int i = 0; i < MesFormation.size(); i++) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/pidev_java/gui/formation/item.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();

                        HBox managebtn = new HBox(editIcon, deleteIcon);
                        managebtn.setStyle("-fx-alignment:center");
                        HBox.setMargin(deleteIcon, new Insets(2, 2, 0, 3));
                        HBox.setMargin(editIcon, new Insets(2, 3, 0, 2));

                        setGraphic(managebtn);

                        setText(null);

                //set grid height
                gridMesFormation.setMinHeight(Region.USE_COMPUTED_SIZE);
                gridMesFormation.setPrefHeight(Region.USE_COMPUTED_SIZE);
               gridMesFormation.setMaxHeight(Region.USE_PREF_SIZE);

                GridPane.setMargin(anchorPane, new Insets(10));
            }
        } catch (Exception e) {
                        System.out.println(e.getMessage());

            
        }
        
        
        
          try {
            
           List<Formation> Formation=fs.Lister(iducon,typeucon);
            for (int i = 0; i < Formation.size(); i++) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/pidev_java/gui/formation/itemF.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();

                ItemFController itemFController = fxmlLoader.getController();
                System.out.println(Formation.get(i).getLabelle());
                itemFController.setData(Formation.get(i),this);

                 if (columnForm == 2) {
                    columnForm = 0;
                    rowForm++;
                }

            };

            return cell;
        };
        action.setCellFactory(cellFoctory);

        tableformation.setItems(data);
        
    }
    
    
     public void Ajouter(Formation Form){
         fs.Ajouter(Form,iducon,typeucon);
         gridMesFormation.getChildren().clear();
          gridFormation.getChildren().clear();
         int columnMesForm = 0;
        int rowMesForm = 1;
         int columnForm = 0;
        int rowForm = 1;
        
        try {
             List<Formation> MesFormation=fs.ListerparU(iducon,typeucon);
          
            for (int i = 0; i < MesFormation.size(); i++) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/pidev_java/gui/formation/item.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();

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
        
          try {
            
           List<Formation> Formation=fs.Lister(iducon,typeucon);
            for (int i = 0; i < Formation.size(); i++) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/pidev_java/gui/formation/itemF.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();

                ItemFController itemFController = fxmlLoader.getController();
                System.out.println(Formation.get(i).getLabelle());
                itemFController.setData(Formation.get(i),this);

                 if (columnForm == 2) {
                    columnForm = 0;
                    rowForm++;
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
        
        try {
             List<Formation> MesFormation=fs.ListerparU(iducon,typeucon);
          
            for (int i = 0; i < MesFormation.size(); i++) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/pidev_java/gui/formation/item.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();

                ItemController itemController = fxmlLoader.getController();
       
                itemController.setData(MesFormation.get(i),this);

                 if (columnMesForm == 2) {
                    columnMesForm = 0;
                    rowMesForm++;
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
        
          try {
            
           List<Formation> Formation=fs.Lister(iducon,typeucon);
            for (int i = 0; i < Formation.size(); i++) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/pidev_java/gui/formation/itemF.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();

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
    
    public void participate(Formation F){
          int columnPartF = 0;
        int rowPartF = 1;
        gridPartF.getChildren().clear();
        ParticipantService ps=new ParticipantService();
        EventLoisir el=new EventLoisir();
        Societe s=new Societe();
        Freelancer Fr=new Freelancer();
       
        if(typeucon.equals("freelancer")){
             Fr=new Freelancer(iducon);
        }
        else{
                s=new Societe(iducon);
        }
       
        Participant P=new Participant("formation", typeucon, Fr, s, F, el);
        ps.Ajouter(P);
         try {
            List<Formation> MesPartF=new ArrayList<>();
           List<Participant> MesParticipation=ps.Lister(typeucon,"formation",1);
            for (int i = 0; i < MesParticipation.size(); i++) {
              
            Formation Fo=fs.FindParId(MesParticipation.get(i).getFormation().getId());
         
            MesPartF.add(Fo);
            }
            for (int i = 0; i < MesPartF.size(); i++) {
            
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/pidev_java/gui/formation/itemPartF.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();

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
    
    public void deleteP(Formation Fo){
         int columnPartF = 0;
        int rowPartF = 1;
        gridPartF.getChildren().clear();
        Participant P=new Participant("formation", typeucon, new Freelancer(1), new Societe(), Fo, new EventLoisir());
        ps.Supprimer(P);
         try {
            List<Formation> MesPartF=new ArrayList<>();
           List<Participant> MesParticipation=ps.Lister("freelancer",typeucon,1);
            for (int i = 0; i < MesParticipation.size(); i++) {
            
            Formation F=fs.FindParId(MesParticipation.get(i).getFormation().getId());
            MesPartF.add(F);
            }
            for (int i = 0; i < MesPartF.size(); i++) {
            
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/pidev_java/gui/formation/itemPartF.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();

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

    @FXML
    private void showFormationAux(MouseEvent event) {
        List<Formation> MesFormationD=fs.ListerParDate();
          try {
                 FXMLLoader loader1 = new FXMLLoader ();
                 loader1.setLocation(getClass().getResource("/pidev_java/gui/formation/MapFaux.fxml"));
                
                 Parent  parent = (Parent)loader1.load();
                  Stage stage = new Stage();
                 stage.setScene(new Scene(parent));
                  stage.show();
                   
                  MapFauxController auxc=loader1.getController();
                 auxc.inti(MesFormationD,this);
                
             } catch (IOException ex) {
                 Logger.getLogger(FormationController.class.getName()).log(Level.SEVERE, null, ex);
             }
        
    }

}
