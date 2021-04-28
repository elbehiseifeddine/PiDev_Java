/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev_java.gui.evenement;

import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXTimePicker;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.Window;
import javafx.util.Callback;
import javax.imageio.ImageIO;
import org.apache.commons.lang3.RandomStringUtils;
import pidev_java.entities.EventLoisir;
import pidev_java.entities.Freelancer;
import pidev_java.entities.Societe;

import pidev_java.services.EventService;
import pidev_java.utils.FTPConnection;
import pidev_java.utils.JavaMail;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class AjoutEventController implements Initializable {

    @FXML
    private JFXDatePicker textdated;
    @FXML
    private JFXDatePicker textdatef;
    @FXML
    private JFXTimePicker heured;
    @FXML
    private JFXTimePicker heuref;
    @FXML
    private JFXTextField labelle;
    @FXML
    private JFXTextField description;
    @FXML
    private JFXTextField domaine;
    @FXML
    private JFXTextField lieu;
    @FXML
    private Button btnAnnuler;
    @FXML
    private JFXTextField image;
    @FXML
    private JFXTextField nbpart;
    @FXML
    private Button BtnAjoutE;

    String url, nom;

    private EventService fs = new EventService();
    private EvenementController Econtroller;
    private EventLoisir event;
    private double lat = 0;
    private double lng = 0;
    @FXML
    private ImageView btnimage;
    @FXML
    private Label labelerreur;

    private boolean valide = false;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        DatePicker minDate = new DatePicker();
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
        textdated.setDayCellFactory(dayCellFactory);
        textdatef.setDayCellFactory(dayCellFactory);
    }

    public void init(EvenementController ec) {
        this.Econtroller = ec;

    }

    public void initUpdate(EvenementController ec, EventLoisir E) {
        this.event = E;
        this.Econtroller = ec;
        labelle.setText(E.getLabelle());
        description.setText(E.getDescription());
        lieu.setText(E.getLieu());
        domaine.setText(E.getDomaine());
        textdated.setValue(LocalDate.parse(E.getDateDebut().toString().split(" ")[0]));
        textdatef.setValue(LocalDate.parse(E.getDateFin().toString().split(" ")[0]));
        nbpart.setText(String.valueOf(E.getNbParticipant()));
        image.setText(E.getImageE());
        heured.setValue(LocalTime.parse(E.getDateDebut().toString().split(" ")[1]));
        heuref.setValue(LocalTime.parse(E.getDateFin().toString().split(" ")[1]));
        BtnAjoutE.setText("Update");

    }

    @FXML
    private void sjhowMap(MouseEvent event) {
        try {
            FXMLLoader loader1 = new FXMLLoader();
            loader1.setLocation(getClass().getResource("/pidev_java/gui/evenement/MapE.fxml"));

            Parent parent = (Parent) loader1.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(parent));
            stage.show();

            MapEController mc = loader1.getController();
            mc.init(this);

        } catch (IOException ex) {
            System.out.println("erreur");
        }

    }

    @FXML
    private void uploadImage(MouseEvent event) {
        FileChooser fc = new FileChooser();

        FileChooser.ExtensionFilter extFilterJPG = new FileChooser.ExtensionFilter("JPG files (*.jpg)", "*.JPG");
        FileChooser.ExtensionFilter extFilterPNG = new FileChooser.ExtensionFilter("PNG files (*.png)", "*.PNG");
        fc.getExtensionFilters().addAll(extFilterJPG, extFilterPNG);
        File selectedFile = fc.showOpenDialog(null);
        url = selectedFile.getAbsolutePath();
        nom = selectedFile.getName();

        this.image.setText(nom);
        FTPConnection cnx = new FTPConnection();
        cnx.Upload(url, nom);


        /*  FileChooser fc = new FileChooser();
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
        image.setText("");
            }else
            image.setText(selected.getName());
        }*/
    }

    @FXML
    private void AjouterEvent(MouseEvent event) {
        if ((labelle.getText().isEmpty()) || (description.getText().isEmpty()) || (domaine.getText().isEmpty()) || (lieu.getText().isEmpty()) || (nbpart.getText().isEmpty()) || (image.getText().isEmpty())) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("erreur");

            alert.setContentText("veuillez remplir tous les champs");
            alert.showAndWait();
        } else {
            if (!valide) {
                Alert alert1 = new Alert(Alert.AlertType.WARNING);
                alert1.setTitle("erreur");

                alert1.setContentText("Entrer un nombre participant valide");
                alert1.showAndWait();
            } else {

                String DateDeb = textdated.getValue().toString().replace('/', '-') + " " + heured.getValue().toString().split(" ")[0] + ":00";
                String DateFin = textdatef.getValue().toString().replace('/', '-') + " " + heuref.getValue().toString().split(" ")[0] + ":00";
                System.out.println(DateDeb);
                if (Timestamp.valueOf(DateFin).before(Timestamp.valueOf(DateDeb))) {
                    Alert alert1 = new Alert(Alert.AlertType.WARNING);
                    alert1.setTitle("erreur");

                    alert1.setContentText("date debut supperieur à la date fin");
                    alert1.showAndWait();

                } else {
                    if (BtnAjoutE.getText().equals("Ajouter")) {

                        Econtroller.Ajouter(E);
                    } else if (BtnAjoutE.getText().equals("Update")) {
                        System.out.println("test1");
                        EventLoisir E = new EventLoisir(this.event.getId(), labelle.getText(), description.getText(), lieu.getText(), Timestamp.valueOf(DateDeb), Timestamp.valueOf(DateFin), domaine.getText(), Integer.parseInt(nbpart.getText()), true, this.lng, this.lat, image.getText());
                        if ((E.getDateDebut().compareTo(this.event.getDateDebut()) != 0) || (E.getDateFin().compareTo(this.event.getDateFin()) != 0)
                                || !(E.getLieu().equals(this.event.getLieu()))) {
                            System.out.println("test2");
                            try {
                                String emailCon = "";
                                if (Freelancer.getInstance() != null) {
                                    emailCon = Freelancer.getInstance().getEmail();
                                } else if (Societe.getInstance() != null) {
                                    emailCon = Societe.getInstance().getEmail();

                                }
                                JavaMail mail = new JavaMail();
                                mail.recipient = emailCon;
                                mail.type = "EmailEditParticipantEvent";
                                mail.nomform = labelle.getText();
                                mail.start();

                            } catch (Exception ex) {
                            }
                        }
                        Econtroller.Update(E);

                        EventLoisir E = new EventLoisir(labelle.getText(), description.getText(), lieu.getText(), Timestamp.valueOf(DateDeb), Timestamp.valueOf(DateFin), domaine.getText(), Integer.parseInt(nbpart.getText()), true, this.lng, this.lat, image.getText());
                    }
                    Window window = ((Node) (event.getSource())).getScene().getWindow();
                    if (window instanceof Stage) {
                        ((Stage) window).close();
                    }

                }
            }
        }
    }

    @FXML
    private void AnnulerE(MouseEvent event) {
        Window window = ((Node) (event.getSource())).getScene().getWindow();
        if (window instanceof Stage) {
            ((Stage) window).close();
        }
    }

    public void setCoordnate(double lat, double lng, String place) {
        this.lat = lat;
        this.lng = lng;
        lieu.setText(place);
    }

    public static String saveToFileImageNormal(Image image) throws SQLException {

        String ext = "jpg";
        //  File dir = new File("C:/Users/USER/Document/GitHub/5/Oxyvia-Tours/public/picture");
        File dir1 = new File("C:\\Users\\seifeddine\\Documents\\NetBeansProjects\\PiDev_Java\\src\\pidev_java\\assets");
        //String name = String.format("%s.%s", RandomStringUtils.randomAlphanumeric(😎, ext);
        String name1 = String.format("%s.%s", RandomStringUtils.randomAlphanumeric(8), ext);
        //  File outputFile = new File(dir, name);
        File outputFile1 = new File(dir1, name1);
        BufferedImage bImage = SwingFXUtils.fromFXImage(image, null);
        BufferedImage bImage1 = SwingFXUtils.fromFXImage(image, null);
        try {
            //   ImageIO.write(bImage, "png", outputFile);
            ImageIO.write(bImage1, "png", outputFile1);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return name1;
    }

    @FXML
    private void ControleNb(KeyEvent event) {
        EventService Es = new EventService();
        if (nbpart.getText().isEmpty()) {
            labelerreur.setText("");
            valide = false;
        } else {
            if (Es.estUnEntier(nbpart.getText())) {
                labelerreur.setText("valide");
                labelerreur.setTextFill(Color.web("#00ff09", 1.0));
                valide = true;
            } else {
                labelerreur.setText("non valide");
                labelerreur.setTextFill(Color.web("#ff0000", 1.0));
                valide = false;
            }
        }
    }
}
