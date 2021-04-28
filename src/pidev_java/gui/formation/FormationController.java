/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev_java.gui.formation;

import pidev_java.entities.Formation;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import java.net.URL;

import java.util.List;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import java.io.IOException;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
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
import pidev_java.utils.JavaMail;
import pidev_java.utils.Javamailform;
import pidev_java.utils.TwilioSMS;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class FormationController implements Initializable {

    @FXML
    private Tab tabMesFormation;
    @FXML
    private Tab tabFormation;
    @FXML
    private ScrollPane scrolFormation;
    @FXML
    private GridPane gridFormation;
    @FXML
    private ScrollPane scrolMesFormation;
    @FXML
    private GridPane gridMesFormation;

    /**
     * Initializes the controller class.
     */
    ParticipantService ps = new ParticipantService();
    FormationService fs = new FormationService();
    @FXML
    private Pane paneMesFormation;
    @FXML
    private FontAwesomeIconView ajoutFormation;
    @FXML
    private Tab tabFormation1;
    @FXML
    private ScrollPane scrolPartF;
    @FXML
    private GridPane gridPartF;
    @FXML
    private Button btnFormationAux;

    private Freelancer Fcon;
    private Societe Scon;
    private int iducon;
    private String typeucon;
    private String emailCon;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        Fcon = Freelancer.getInstance();
        Scon = Societe.getInstance();
        if (Fcon != null) {
            iducon = Fcon.getId();
            typeucon = "freelancer";
            this.emailCon=Fcon.getEmail();
        } else {
            iducon = Scon.getId();
            typeucon = "societe";
            this.emailCon=Scon.getEmail();
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

            List<Formation> MesFormation = fs.ListerparU(iducon, typeucon);
            for (int i = 0; i < MesFormation.size(); i++) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/pidev_java/gui/formation/item.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();

                ItemController itemController = fxmlLoader.getController();
                System.out.println(MesFormation.get(i).getLabelle());
                itemController.setData(MesFormation.get(i), this);

                if (columnMesForm == 2) {
                    columnMesForm = 0;
                    rowMesForm++;
                }

                gridMesFormation.add(anchorPane, columnMesForm, rowMesForm++); //(child,column,row)
                //set grid width
                gridMesFormation.setMinWidth(Region.USE_COMPUTED_SIZE);
                gridMesFormation.setPrefWidth(Region.USE_COMPUTED_SIZE);
                gridMesFormation.setMaxWidth(Region.USE_PREF_SIZE);

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

            List<Formation> Formation = fs.Lister(iducon, typeucon);
            for (int i = 0; i < Formation.size(); i++) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/pidev_java/gui/formation/itemF.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();

                ItemFController itemFController = fxmlLoader.getController();
                System.out.println(Formation.get(i).getLabelle());
                itemFController.setData(Formation.get(i), this);

                if (columnForm == 2) {
                    columnForm = 0;
                    rowForm++;
                }

                gridFormation.add(anchorPane, columnForm, rowForm++); //(child,column,row)
                //set grid width
                gridFormation.setMinWidth(Region.USE_COMPUTED_SIZE);
                gridFormation.setPrefWidth(Region.USE_COMPUTED_SIZE);
                gridFormation.setMaxWidth(Region.USE_PREF_SIZE);

                //set grid height
                gridFormation.setMinHeight(Region.USE_COMPUTED_SIZE);
                gridFormation.setPrefHeight(Region.USE_COMPUTED_SIZE);
                gridFormation.setMaxHeight(Region.USE_PREF_SIZE);

                GridPane.setMargin(anchorPane, new Insets(10));
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());

        }

        try {
            List<Formation> MesPartF = new ArrayList<>();
            List<Participant> MesParticipation = ps.Lister(typeucon, "formation", iducon);
            for (int i = 0; i < MesParticipation.size(); i++) {

                Formation F = fs.FindParId(MesParticipation.get(i).getFormation().getId());

                MesPartF.add(F);
            }
            for (int i = 0; i < MesPartF.size(); i++) {

                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/pidev_java/gui/formation/itemPartF.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();

                ItemPartFController itemPController = fxmlLoader.getController();

                itemPController.setData(MesPartF.get(i), this);

                if (columnPartF == 2) {
                    columnPartF = 0;
                    rowPartF++;
                }

                gridPartF.add(anchorPane, columnPartF, rowPartF++); //(child,column,row)
                //set grid width
                gridPartF.setMinWidth(Region.USE_COMPUTED_SIZE);
                gridPartF.setPrefWidth(Region.USE_COMPUTED_SIZE);
                gridPartF.setMaxWidth(Region.USE_PREF_SIZE);

                //set grid height
                gridPartF.setMinHeight(Region.USE_COMPUTED_SIZE);
                gridPartF.setPrefHeight(Region.USE_COMPUTED_SIZE);
                gridPartF.setMaxHeight(Region.USE_PREF_SIZE);

                GridPane.setMargin(anchorPane, new Insets(10));
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());

        }

    }

    public void delete(Formation Form) {
        ParticipantService pser = new ParticipantService();
        FreelancerService FS = new FreelancerService();
        SocieteService SS = new SocieteService();
        String email = "";
        List<Participant> Part = pser.ListerParEvent("formation", Form.getId());
        if (Part != null) {
            for (int l = 0; l < Part.size(); l++) {
                if (Part.get(l).getTypeU().equals("freelancer")) {
                    email = FS.FindparID(Part.get(l).getF().getId()).getEmail();
                } else {
                    email = SS.FindparID(Part.get(l).getS().getId()).getEmail();
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
            List<Formation> MesFormation = fs.ListerparU(iducon, typeucon);

            for (int i = 0; i < MesFormation.size(); i++) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/pidev_java/gui/formation/item.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();

                ItemController itemController = fxmlLoader.getController();

                itemController.setData(MesFormation.get(i), this);

                if (columnMesForm == 2) {
                    columnMesForm = 0;
                    rowMesForm++;
                }

                gridMesFormation.add(anchorPane, columnMesForm, rowMesForm++); //(child,column,row)
                //set grid width
                gridMesFormation.setMinWidth(Region.USE_COMPUTED_SIZE);
                gridMesFormation.setPrefWidth(Region.USE_COMPUTED_SIZE);
                gridMesFormation.setMaxWidth(Region.USE_PREF_SIZE);

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

            List<Formation> Formation = fs.Lister(iducon, typeucon);
            for (int i = 0; i < Formation.size(); i++) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/pidev_java/gui/formation/itemF.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();

                ItemFController itemFController = fxmlLoader.getController();
                System.out.println(Formation.get(i).getLabelle());
                itemFController.setData(Formation.get(i), this);

                if (columnForm == 2) {
                    columnForm = 0;
                    rowForm++;
                }

                gridFormation.add(anchorPane, columnForm, rowForm++); //(child,column,row)
                //set grid width
                gridFormation.setMinWidth(Region.USE_COMPUTED_SIZE);
                gridFormation.setPrefWidth(Region.USE_COMPUTED_SIZE);
                gridFormation.setMaxWidth(Region.USE_PREF_SIZE);

                //set grid height
                gridFormation.setMinHeight(Region.USE_COMPUTED_SIZE);
                gridFormation.setPrefHeight(Region.USE_COMPUTED_SIZE);
                gridFormation.setMaxHeight(Region.USE_PREF_SIZE);

                GridPane.setMargin(anchorPane, new Insets(10));
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());

        }

    }

    public void Ajouter(Formation Form) {
        fs.Ajouter(Form, iducon, typeucon);
        gridMesFormation.getChildren().clear();
        gridFormation.getChildren().clear();
        int columnMesForm = 0;
        int rowMesForm = 1;
        int columnForm = 0;
        int rowForm = 1;

        try {
            List<Formation> MesFormation = fs.ListerparU(iducon, typeucon);

            for (int i = 0; i < MesFormation.size(); i++) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/pidev_java/gui/formation/item.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();

                ItemController itemController = fxmlLoader.getController();

                itemController.setData(MesFormation.get(i), this);

                if (columnMesForm == 2) {
                    columnMesForm = 0;
                    rowMesForm++;
                }

                gridMesFormation.add(anchorPane, columnMesForm, rowMesForm++); //(child,column,row)
                //set grid width
                gridMesFormation.setMinWidth(Region.USE_COMPUTED_SIZE);
                gridMesFormation.setPrefWidth(Region.USE_COMPUTED_SIZE);
                gridMesFormation.setMaxWidth(Region.USE_PREF_SIZE);

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

            List<Formation> Formation = fs.Lister(iducon, typeucon);
            for (int i = 0; i < Formation.size(); i++) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/pidev_java/gui/formation/itemF.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();

                ItemFController itemFController = fxmlLoader.getController();
                System.out.println(Formation.get(i).getLabelle());
                itemFController.setData(Formation.get(i), this);

                if (columnForm == 2) {
                    columnForm = 0;
                    rowForm++;
                }

                gridFormation.add(anchorPane, columnForm, rowForm++); //(child,column,row)
                //set grid width
                gridFormation.setMinWidth(Region.USE_COMPUTED_SIZE);
                gridFormation.setPrefWidth(Region.USE_COMPUTED_SIZE);
                gridFormation.setMaxWidth(Region.USE_PREF_SIZE);

                //set grid height
                gridFormation.setMinHeight(Region.USE_COMPUTED_SIZE);
                gridFormation.setPrefHeight(Region.USE_COMPUTED_SIZE);
                gridFormation.setMaxHeight(Region.USE_PREF_SIZE);

                GridPane.setMargin(anchorPane, new Insets(10));
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());

        }

    }

    public void Update(Formation Form) {
        fs.Modifier(Form);
        gridMesFormation.getChildren().clear();
        int columnMesForm = 0;
        int rowMesForm = 1;
        int columnForm = 0;
        int rowForm = 1;

        try {
            List<Formation> MesFormation = fs.ListerparU(iducon, typeucon);

            for (int i = 0; i < MesFormation.size(); i++) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/pidev_java/gui/formation/item.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();

                ItemController itemController = fxmlLoader.getController();

                itemController.setData(MesFormation.get(i), this);

                if (columnMesForm == 2) {
                    columnMesForm = 0;
                    rowMesForm++;
                }

                gridMesFormation.add(anchorPane, columnMesForm, rowMesForm++); //(child,column,row)
                //set grid width
                gridMesFormation.setMinWidth(Region.USE_COMPUTED_SIZE);
                gridMesFormation.setPrefWidth(Region.USE_COMPUTED_SIZE);
                gridMesFormation.setMaxWidth(Region.USE_PREF_SIZE);

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

            List<Formation> Formation = fs.Lister(iducon, typeucon);
            for (int i = 0; i < Formation.size(); i++) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/pidev_java/gui/formation/itemF.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();

                ItemFController itemFController = fxmlLoader.getController();
                System.out.println(Formation.get(i).getLabelle());
                itemFController.setData(Formation.get(i), this);

                if (columnForm == 2) {
                    columnForm = 0;
                    rowForm++;
                }

                gridFormation.add(anchorPane, columnForm, rowForm++); //(child,column,row)
                //set grid width
                gridFormation.setMinWidth(Region.USE_COMPUTED_SIZE);
                gridFormation.setPrefWidth(Region.USE_COMPUTED_SIZE);
                gridFormation.setMaxWidth(Region.USE_PREF_SIZE);

                //set grid height
                gridFormation.setMinHeight(Region.USE_COMPUTED_SIZE);
                gridFormation.setPrefHeight(Region.USE_COMPUTED_SIZE);
                gridFormation.setMaxHeight(Region.USE_PREF_SIZE);

                GridPane.setMargin(anchorPane, new Insets(10));
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());

        }

    }

    @FXML
    private void AjoutFormation(MouseEvent event) {
        try {
            FXMLLoader loader1 = new FXMLLoader();
            loader1.setLocation(getClass().getResource("/pidev_java/gui/formation/AjoutFormation.fxml"));

            Parent parent = (Parent) loader1.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(parent));
            stage.show();

            AjoutFormationController afc = loader1.getController();
            afc.init(this);

        } catch (IOException ex) {
            Logger.getLogger(FormationController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void participate(Formation F,String Lebelle) {
        int columnPartF = 0;
        int rowPartF = 1;
        gridPartF.getChildren().clear();
        ParticipantService ps = new ParticipantService();
        EventLoisir el = new EventLoisir();
        Societe s = new Societe();
        Freelancer Fr = new Freelancer();

        if (typeucon.equals("freelancer")) {
            Fr = new Freelancer(iducon);
        } else {
            s = new Societe(iducon);
        }

        Participant P = new Participant("formation", typeucon, Fr, s, F, el);
        boolean exist=false;
        ps.Ajouter(P);
        if(exist){
        try {
            List<Formation> MesPartF = new ArrayList<>();
            List<Participant> MesParticipation = ps.Lister(typeucon, "formation", iducon);
            for (int i = 0; i < MesParticipation.size(); i++) {

                Formation Fo = fs.FindParId(MesParticipation.get(i).getFormation().getId());
                MesPartF.add(Fo);
            }
            for (int i = 0; i < MesPartF.size(); i++) {

                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/pidev_java/gui/formation/itemPartF.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();

                ItemPartFController itemPController = fxmlLoader.getController();

                itemPController.setData(MesPartF.get(i), this);

                if (columnPartF == 2) {
                    columnPartF = 0;
                    rowPartF++;
                }

                gridPartF.add(anchorPane, columnPartF, rowPartF++); //(child,column,row)
                //set grid width
                gridPartF.setMinWidth(Region.USE_COMPUTED_SIZE);
                gridPartF.setPrefWidth(Region.USE_COMPUTED_SIZE);
                gridPartF.setMaxWidth(Region.USE_PREF_SIZE);

                //set grid height
                gridPartF.setMinHeight(Region.USE_COMPUTED_SIZE);
                gridPartF.setPrefHeight(Region.USE_COMPUTED_SIZE);
                gridPartF.setMaxHeight(Region.USE_PREF_SIZE);

                GridPane.setMargin(anchorPane, new Insets(10));
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());

        }
//            try {
//
//                JavaMail mail = new JavaMail();
//                mail.recipient = emailCon;
//                mail.type = "EmailConfirmationParticipant";
//                mail.nomform = Lebelle;
//                mail.start();
//
//            } catch (Exception ex) {
//            }
        }
        else{
             
             Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("erreur");

            alert.setContentText("Vous avez deja participer");
            alert.showAndWait();
            
        
        }
        //Javamailform.send("nadebessioud20@gmail.com","LAsny1920", "naderbessioud98@gmail.com", "confirmation", "confirmation de particiation à la formation"+F.getLabelle());
        TwilioSMS TS = new TwilioSMS();
        TS.sendSMS("Confirmation", "+21629658549");

    }

    public void deleteP(Formation Fo) {
        int columnPartF = 0;
        int rowPartF = 1;
        Participant P=new Participant();
        gridPartF.getChildren().clear();
        if(typeucon.equals("freelancer")){
                     P = new Participant("formation", typeucon, new Freelancer(iducon), new Societe(), Fo, new EventLoisir());

        }
        else{
             
                     P = new Participant("formation", typeucon, new Freelancer(), new Societe(iducon), Fo, new EventLoisir());
                       System.out.println(P.getTypeU());
        }
        ps.Supprimer(P);
        try {
            List<Formation> MesPartF = new ArrayList<>();
            List<Participant> MesParticipation = ps.Lister(typeucon,"formation", iducon);
            for (int i = 0; i < MesParticipation.size(); i++) {

                Formation F = fs.FindParId(MesParticipation.get(i).getFormation().getId());
                MesPartF.add(F);
            }
            for (int i = 0; i < MesPartF.size(); i++) {

                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/pidev_java/gui/formation/itemPartF.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();

                ItemPartFController itemPController = fxmlLoader.getController();

                itemPController.setData(MesPartF.get(i), this);

                if (columnPartF == 2) {
                    columnPartF = 0;
                    rowPartF++;
                }

                gridPartF.add(anchorPane, columnPartF, rowPartF++); //(child,column,row)
                //set grid width
                gridPartF.setMinWidth(Region.USE_COMPUTED_SIZE);
                gridPartF.setPrefWidth(Region.USE_COMPUTED_SIZE);
                gridPartF.setMaxWidth(Region.USE_PREF_SIZE);

                //set grid height
                gridPartF.setMinHeight(Region.USE_COMPUTED_SIZE);
                gridPartF.setPrefHeight(Region.USE_COMPUTED_SIZE);
                gridPartF.setMaxHeight(Region.USE_PREF_SIZE);

                GridPane.setMargin(anchorPane, new Insets(10));
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());

        }
    }

    @FXML
    private void showFormationAux(MouseEvent event) {
        List<Formation> MesFormationD = fs.ListerParDate();
        try {
            FXMLLoader loader1 = new FXMLLoader();
            loader1.setLocation(getClass().getResource("/pidev_java/gui/formation/MapFaux.fxml"));

            Parent parent = (Parent) loader1.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(parent));
            stage.show();

            MapFauxController auxc = loader1.getController();
            auxc.inti(MesFormationD, this);

        } catch (IOException ex) {
            Logger.getLogger(FormationController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
