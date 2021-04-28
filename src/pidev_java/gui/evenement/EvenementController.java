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
import java.io.IOException;
import java.net.URL;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Tab;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Callback;
import pidev_java.entities.Freelancer;
import pidev_java.entities.Participant;
import pidev_java.entities.Societe;
import pidev_java.gui.formation.ItemPartFController;
import pidev_java.services.AdminEventService;
import pidev_java.services.EventService;
import pidev_java.services.FreelancerService;
import pidev_java.services.ParticipantService;
import pidev_java.services.SocieteService;
import pidev_java.utils.JavaMail;
import pidev_java.utils.Javamailform;
import pidev_java.utils.TwilioSMS;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class EvenementController implements Initializable {

    @FXML
    private Tab tabmesEvent;
    @FXML
    private GridPane gridMesevent;
    @FXML
    private Tab tabEvent;
    @FXML
    private GridPane gridEvent;
    @FXML
    private ScrollPane scrolEvent;
    @FXML
    private FontAwesomeIconView iconAjoutEvent;
    @FXML
    private ScrollPane scrollMesEvent;
    private EventService es = new EventService();
    @FXML
    private Tab tabEvent1;
    @FXML
    private ScrollPane scrolPartE;
    @FXML
    private GridPane gridPartE;

    private EventService evs = new EventService();
    @FXML
    private Button btnEventAux;

    private Freelancer Fcon;
    private Societe Scon;
    private int iducon;
    private String typeucon;
    private String emailCon;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        Fcon = Freelancer.getInstance();
        Scon = Societe.getInstance();
        if (Fcon != null) {
            iducon = Fcon.getId();
            typeucon = "freelancer";
            emailCon = Fcon.getEmail();
        } else {
            iducon = Scon.getId();
            typeucon = "societe";
            emailCon = Scon.getEmail();
        }
        scrollMesEvent.setVisible(true);
        gridMesevent.getChildren().clear();
        scrolEvent.setVisible(true);
        gridEvent.getChildren().clear();

        ParticipantService ps = new ParticipantService();
        int columnPartF = 0;
        int rowPartF = 1;
        gridPartE.getChildren().clear();

        int columnMesEvent = 0;
        int rowMesEvent = 1;
        int columnEvent = 0;
        int rowEvent = 1;

        try {

            List<EventLoisir> MesEvent = es.ListerparU(iducon, typeucon);
            for (int i = 0; i < MesEvent.size(); i++) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/pidev_java/gui/evenement/itemEvent.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();

                ItemEventController itemeController = fxmlLoader.getController();

                itemeController.setData(MesEvent.get(i), this);

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

            List<EventLoisir> Event = es.Lister(iducon, typeucon);
            for (int i = 0; i < Event.size(); i++) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/pidev_java/gui/evenement/itemE.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();

                ItemEController itemEController = fxmlLoader.getController();

                itemEController.setData(Event.get(i), this);

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

        try {
            List<EventLoisir> MesPartE = new ArrayList<>();
            List<Participant> MesParticipation = ps.Lister(typeucon, "evenement", iducon);
            for (int i = 0; i < MesParticipation.size(); i++) {

                EventLoisir E = evs.FindParId(MesParticipation.get(i).getEl().getId());
                MesPartE.add(E);
            }
            for (int i = 0; i < MesPartE.size(); i++) {

                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/pidev_java/gui/evenement/itemPartE.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();

                ItemPartEController itemEController = fxmlLoader.getController();

                itemEController.setData(MesPartE.get(i), this);

                if (columnPartF == 2) {
                    columnPartF = 0;
                    rowPartF++;
                }

                gridPartE.add(anchorPane, columnPartF, rowPartF++); //(child,column,row)
                //set grid width
                gridPartE.setMinWidth(Region.USE_COMPUTED_SIZE);
                gridPartE.setPrefWidth(Region.USE_COMPUTED_SIZE);
                gridPartE.setMaxWidth(Region.USE_PREF_SIZE);

                //set grid height
                gridPartE.setMinHeight(Region.USE_COMPUTED_SIZE);
                gridPartE.setPrefHeight(Region.USE_COMPUTED_SIZE);
                gridPartE.setMaxHeight(Region.USE_PREF_SIZE);

                GridPane.setMargin(anchorPane, new Insets(10));
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());

        }

    }

    public void delete(EventLoisir event) {
        ParticipantService pser = new ParticipantService();
        FreelancerService FS = new FreelancerService();
        SocieteService SS = new SocieteService();
        String email = "";
        List<Participant> Part = pser.ListerParEvent("evenement", event.getId());
        if (Part != null) {
            for (int l = 0; l < Part.size(); l++) {
                if (Part.get(l).getTypeU().equals("freelancer")) {
                    email = FS.FindparID(Part.get(l).getF().getId()).getEmail();
                } else {
                    email = SS.FindparID(Part.get(l).getS().getId()).getEmail();
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

            List<EventLoisir> MesEvent = es.ListerparU(iducon, typeucon);
            for (int i = 0; i < MesEvent.size(); i++) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/pidev_java/gui/evenement/itemEvent.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();

                ItemEventController itemeController = fxmlLoader.getController();

                itemeController.setData(MesEvent.get(i), this);

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

            List<EventLoisir> Event = es.Lister(iducon, typeucon);
            for (int i = 0; i < Event.size(); i++) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/pidev_java/gui/evenement/itemE.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();

                ItemEController itemEController = fxmlLoader.getController();

                itemEController.setData(Event.get(i), this);

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

    public void Ajouter(EventLoisir event) {
        es.Ajouter(event, iducon, typeucon);
new AdminEventService().SendEvenementToAdminEmploi(event.getId());
        gridMesevent.getChildren().clear();
        gridEvent.getChildren().clear();

        int columnMesEvent = 0;
        int rowMesEvent = 1;
        int columnEvent = 0;
        int rowEvent = 1;

        try {

            List<EventLoisir> MesEvent = es.ListerparU(iducon, typeucon);
            for (int i = 0; i < MesEvent.size(); i++) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/pidev_java/gui/evenement/itemEvent.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();

                ItemEventController itemeController = fxmlLoader.getController();

                itemeController.setData(MesEvent.get(i), this);

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

            List<EventLoisir> Event = es.Lister(iducon, typeucon);
            for (int i = 0; i < Event.size(); i++) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/pidev_java/gui/evenement/itemE.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();

                ItemEController itemEController = fxmlLoader.getController();

                itemEController.setData(Event.get(i), this);

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

    public void Update(EventLoisir event) {
        es.Modifier(event);
        gridMesevent.getChildren().clear();
        gridEvent.getChildren().clear();
        int columnMesEvent = 0;
        int rowMesEvent = 1;
        int columnEvent = 0;
        int rowEvent = 1;

        try {

            List<EventLoisir> MesEvent = es.ListerparU(iducon, typeucon);
            for (int i = 0; i < MesEvent.size(); i++) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/pidev_java/gui/evenement/itemEvent.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();

                ItemEventController itemeController = fxmlLoader.getController();

                itemeController.setData(MesEvent.get(i), this);

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

            List<EventLoisir> Event = es.Lister(iducon, typeucon);
            for (int i = 0; i < Event.size(); i++) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/pidev_java/gui/evenement/itemE.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();

                ItemEController itemEController = fxmlLoader.getController();

                itemEController.setData(Event.get(i), this);

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
    private void AjoutEvent(MouseEvent event) {
        try {
            FXMLLoader loader1 = new FXMLLoader();
            loader1.setLocation(getClass().getResource("/pidev_java/gui/evenement/AjoutEvent.fxml"));

            Parent parent = (Parent) loader1.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(parent));
            stage.show();

            AjoutEventController aec = loader1.getController();
            aec.init(this);

        } catch (IOException ex) {
            System.out.println("errr");
        }
    }

    public void deleteP(EventLoisir ev) {
        ParticipantService ps = new ParticipantService();
        int columnPartF = 0;
        int rowPartF = 1;
        gridPartE.getChildren().clear();
        Participant P = new Participant();
        if (typeucon.equals("freelancer")) {
            P = new Participant("evenement", typeucon, new Freelancer(iducon), new Societe(), new Formation(), ev);

        } else {

            P = new Participant("evenement", typeucon, new Freelancer(), new Societe(iducon), new Formation(), ev);

        }
        ps.Supprimer(P);
        try {
            List<EventLoisir> MesPartE = new ArrayList<>();
            List<Participant> MesParticipation = ps.Lister(typeucon, "evenement", 1);
            for (int i = 0; i < MesParticipation.size(); i++) {

                EventLoisir E = evs.FindParId(MesParticipation.get(i).getEl().getId());
                MesPartE.add(E);
            }
            for (int i = 0; i < MesPartE.size(); i++) {

                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/pidev_java/gui/evenement/itemPartE.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();

                ItemPartEController itemEController = fxmlLoader.getController();

                itemEController.setData(MesPartE.get(i), this);

                if (columnPartF == 2) {
                    columnPartF = 0;
                    rowPartF++;
                }

                gridPartE.add(anchorPane, columnPartF, rowPartF++); //(child,column,row)
                //set grid width
                gridPartE.setMinWidth(Region.USE_COMPUTED_SIZE);
                gridPartE.setPrefWidth(Region.USE_COMPUTED_SIZE);
                gridPartE.setMaxWidth(Region.USE_PREF_SIZE);

                //set grid height
                gridPartE.setMinHeight(Region.USE_COMPUTED_SIZE);
                gridPartE.setPrefHeight(Region.USE_COMPUTED_SIZE);
                gridPartE.setMaxHeight(Region.USE_PREF_SIZE);

                GridPane.setMargin(anchorPane, new Insets(10));
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());

        }
    }

    public void participate(EventLoisir E, String Labelle) {

        int columnPartF = 0;
        int rowPartF = 1;
        gridPartE.getChildren().clear();
        ParticipantService ps = new ParticipantService();
        Formation F = new Formation();
        Societe s = new Societe();
        Freelancer Fr = new Freelancer();
        if (typeucon.equals("freelancer")) {
            Fr = new Freelancer(iducon);
        } else {
            s = new Societe(iducon);
        }

        Participant P = new Participant("evenement", typeucon, Fr, s, F, E);
        boolean exist = false;
        exist = ps.AjouterP(P);
        if (exist) {
            try {
                List<EventLoisir> MesPartE = new ArrayList<>();
                List<Participant> MesParticipation = ps.Lister(typeucon, "evenement", iducon);
                for (int i = 0; i < MesParticipation.size(); i++) {

                    EventLoisir Ev = evs.FindParId(MesParticipation.get(i).getEl().getId());
                    MesPartE.add(Ev);
                }
                for (int i = 0; i < MesPartE.size(); i++) {

                    FXMLLoader fxmlLoader = new FXMLLoader();
                    fxmlLoader.setLocation(getClass().getResource("/pidev_java/gui/evenement/itemPartE.fxml"));
                    AnchorPane anchorPane = fxmlLoader.load();

                    ItemPartEController itemEController = fxmlLoader.getController();

                    itemEController.setData(MesPartE.get(i), this);

                    if (columnPartF == 2) {
                        columnPartF = 0;
                        rowPartF++;
                    }

                    gridPartE.add(anchorPane, columnPartF, rowPartF++); //(child,column,row)
                    //set grid width
                    gridPartE.setMinWidth(Region.USE_COMPUTED_SIZE);
                    gridPartE.setPrefWidth(Region.USE_COMPUTED_SIZE);
                    gridPartE.setMaxWidth(Region.USE_PREF_SIZE);

                    //set grid height
                    gridPartE.setMinHeight(Region.USE_COMPUTED_SIZE);
                    gridPartE.setPrefHeight(Region.USE_COMPUTED_SIZE);
                    gridPartE.setMaxHeight(Region.USE_PREF_SIZE);

                    GridPane.setMargin(anchorPane, new Insets(10));
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());

            }
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("erreur");

            alert.setContentText("Vous avez deja participer");
            alert.showAndWait();

        }
        try {

            JavaMail mail = new JavaMail();
            mail.recipient = emailCon;
            mail.type = "EmailConfirmationParticipantEvent";
            mail.nomform = Labelle;
            mail.start();

        } catch (Exception ex) {
        }
        //Javamailform.send("nadebessioud20@gmail.com","LAsny1920", "naderbessioud98@gmail.com", "confirmation", "confirmation de particiation à event"+E.getLabelle());
        TwilioSMS TS = new TwilioSMS();
        TS.sendSMS("Confirmation participation Event", "+21629658549");

    }

    @FXML
    private void showEventAux(MouseEvent event) {
        List<EventLoisir> MesEventAux = es.ListerParDate();
        try {
            FXMLLoader loader1 = new FXMLLoader();
            loader1.setLocation(getClass().getResource("/pidev_java/gui/evenement/MapEAux.fxml"));

            Parent parent = (Parent) loader1.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(parent));
            stage.show();

            MapEAuxController auxc = loader1.getController();
            auxc.inti(MesEventAux, this);

        } catch (IOException ex) {
            System.out.println("erreur");
        }
    }

}
