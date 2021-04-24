/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev_java.gui.back;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import pidev_java.entities.Admin;
import pidev_java.entities.Freelancer;
import pidev_java.gui.utilisateur.FreelancerProfileController;
import pidev_java.services.AdminService;
import pidev_java.services.FreelancerService;
//import pidev_java.utils.PdfGeneration;

/**
 * FXML Controller class
 *
 * @author ahmed
 */
public class ListeFreelancersSuperAdminController implements Initializable {

    
    ObservableList<Freelancer> ListFreelancer = FXCollections.observableArrayList();
    private TableView<Freelancer> TableFreelancer;
    private TableColumn<Freelancer, Integer> id_freelancer;
    private TableColumn<Freelancer, String> nom;
    private TableColumn<Freelancer, String> prenom;
    private TableColumn<Freelancer, String> email;
    private TableColumn<Freelancer, String> adresse;
    private TableColumn<Freelancer, Integer> etat;
    private TableColumn<Freelancer, String> Competance;
    private TableColumn<Freelancer, String> sexe;
    private TableColumn<Freelancer, String> date_creation;
    private TableColumn<Freelancer, String> actions;
    @FXML
    private AnchorPane NavBar;
    @FXML
    private AnchorPane Admins;
    @FXML
    private HBox AdminRecHbox;
    @FXML
    private Label LabelReclamationVide;
    @FXML
    private Label pdfLab;
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
        FreelancerService freelancerService = new FreelancerService();

        ArrayList<Freelancer> liste = freelancerService.getAll();
        if (liste.isEmpty()) {
            AdminRecHbox.setVisible(false);
            LabelReclamationVide.setText("Aucun admin disponible, pensez à lui créer ! ");

        } else {
            for (Freelancer freelancer : liste) {
                System.out.println(freelancer.getPhoto_de_profile());
                System.out.println(freelancer.getNom());
                System.out.println(freelancer);
                System.out.println("aaaaaaaaaaaaaaaaa");
                File file= new File(freelancer.getPhoto_de_profile());
                Image img = new Image(file.toURI().toString());
                Circle image=new Circle();
                image.setFill(new ImagePattern(img));
               
                
                Label name = new Label(freelancer.getNom() + " " + freelancer.getPrenom());
                name.setStyle("-fx-font-weight: bold;");
                Label LabelEmail = new Label(freelancer.getEmail());
                Label lock = new Label();
                Button activatebtn = new Button();

                if(freelancer.getEtat()==0){
                    activatebtn.setStyle(
                        " -fx-cursor: hand ;"
                        + "-glyph-size:28px;"
                        + "-fx-fill:#00E676;"
                        + "-fx-background-radius: 5px;"
                        + "-fx-background-color: green;"
                        + "-fx-text-fill: whitesmoke;" 
                        + "-fx-font-size: 19;" 
                        + "-fx-font-weight: 600;"
                    );
                    activatebtn.setText("Activated");                    
                }else{
                    activatebtn.setStyle(
                        " -fx-cursor: hand ;"
                        + "-glyph-size:28px;"
                        + "-fx-fill:#00E676;"
                        + "-fx-background-radius: 5px;"
                        + "-fx-background-color: red;"
                        + "-fx-text-fill: whitesmoke;" 
                        + "-fx-font-size: 19;" 
                        + "-fx-font-weight: 600;"
                    );
                    activatebtn.setText("Deactivated");  
                }
                
                activatebtn.setOnMouseClicked((event) -> {
                    if(freelancer.getEtat()==1){
                        freelancerService.ActivateFreelancer(freelancer.getId());
                        activatebtn.setStyle(
                            " -fx-cursor: hand ;"
                            + "-glyph-size:28px;"
                            + "-fx-fill:#00E676;"
                            + "-fx-background-radius: 5px;"
                            + "-fx-background-color: green;"
                            + "-fx-text-fill: whitesmoke;" 
                            + "-fx-font-size: 19;" 
                            + "-fx-font-weight: 600;"
                        );
                        activatebtn.setText("Activated");   
                    }else{
                        freelancerService.DeactivateFreelancer(freelancer.getId());
                        activatebtn.setStyle(
                            " -fx-cursor: hand ;"
                            + "-glyph-size:28px;"
                            + "-fx-fill:#00E676;"
                            + "-fx-background-radius: 5px;"
                            + "-fx-background-color: red;"
                            + "-fx-text-fill: whitesmoke;" 
                            + "-fx-font-size: 19;" 
                            + "-fx-font-weight: 600;"
                        );
                        activatebtn.setText("Deactivated");
                    }
                        
                });
                HBox managebtn = new HBox(activatebtn);
                managebtn.setStyle("-fx-alignment:center");
                HBox.setMargin(activatebtn, new Insets(2, 3, 0, 2));
                VBox vbox = new VBox(image, name, LabelEmail, lock, managebtn);
                vbox.setMaxWidth(1501 / liste.size());
                vbox.setPrefWidth(250);
                vbox.setSpacing(8);
                vbox.setPadding(new Insets(0, 20, 0, 20));
                vbox.setAlignment(Pos.CENTER);
                vbox.setStyle("-fx-background-color: white;"+"-fx-background-radius: 20px;");
                AdminRecHbox.getChildren().add(vbox);
            }
        }
        AdminRecHbox.setSpacing(20);
        AdminRecHbox.prefWidth(1585);

    }

    @FXML
    private void PDFGenerator(ActionEvent event) {
        FreelancerService freelancerService = new FreelancerService();
        ArrayList<Freelancer> List = freelancerService.getAll();
        //PdfGeneration.FreelancerListPdf(List);
        pdfLab.setVisible(true);
    }
    
}
