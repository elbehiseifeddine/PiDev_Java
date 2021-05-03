/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev_java.gui.demandeEmploi;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import org.controlsfx.control.Rating;
import pidev_java.entities.offreEmploi;
import pidev_java.gui.offreEmploi.ConsulterOffreEmploiController;
import pidev_java.services.emploiService;

/**
 * FXML Controller class
 *
 * @author Ghassen Riahi
 */
public class ItemAllEmploisController implements Initializable {

    @FXML
    private Label idNom;
    @FXML
    private Label idComp;
    @FXML
    private Label idDesc;
    @FXML
    private Label idSalaire;
    @FXML
    private Label idDTexpr;
    @FXML
    private Label idDomaine;

    /**
     * Initializes the controller class.
     * 
     * 
     *
     */
    emploiService ss=new emploiService();
    ConsulterEmploiFreelancerController co;
    private offreEmploi offre;
    @FXML
    private Label idDevise;
    private Rating ratingEmploi;
    @FXML
    private ImageView aa;
    @FXML
    private Button id_reclamation;
    @FXML
    private Button btn_Demande_Emploi;
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }   
    
    public void setData(offreEmploi os,ConsulterEmploiFreelancerController fc) {
         this.co=fc;
        this.offre = os;
        
          String t = String.valueOf(os.getIdSociete());
        System.out.println("/pidev_java/"+t+".png");
        Image i = new Image("/pidev_java/"+t+".png");
        
        this.idNom.setText(os.getNomProjet());
        this.idComp.setText(os.getCompetence());
        this.idDesc.setText(os.getDescription());
        this.idDomaine.setText(String.valueOf(os.getDomaine()));
        this.idSalaire.setText(String.valueOf(os.getSalaire()));
       this.idDevise.setText(String.valueOf(os.getDevise()));
        this.idDTexpr.setText(String.valueOf(os.getDateExpiration()));
        this.aa.setImage(i);
//        btn_Demande_Emploi.setOnMouseClicked((event) -> {
//              try {
//            FXMLLoader fxmlLoader = new FXMLLoader();
//             
//          
//               fxmlLoader.setLocation(getClass().getResource("/pidev_java/gui/demandeEmploi/AjoutDemandeE.fxml"));
//               Parent parent = fxmlLoader.load();
//        Scene Scene = new Scene(parent);
//         AjoutDemandeEController ajout = fxmlLoader.getController();
//            offreEmploi o = new offreEmploi();
//            
//        ajout.setOffreE(offre);
//            Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
//        
//        window.setScene(Scene);
//        window.show();
//            
//        } catch (IOException ex) {
//                System.out.println(ex.getMessage());        }  
//        });
     }
 
    private void Rating(MouseEvent event) {
        this.ratingEmploi.getRating();
    }

    @FXML
    private void reclamation(ActionEvent event) {
        try {
                 FXMLLoader loader1 = new FXMLLoader ();
                 loader1.setLocation(getClass().getResource("/pidev_java/gui/reclamation/Reclamation.fxml"));
                
                 Parent  parent = (Parent)loader1.load();
                  Stage stage = new Stage();
                 stage.setScene(new Scene(parent));
                  stage.show();
                   
                
             } catch (IOException ex) {
                 System.out.println("erreur");
                
//                ReclamationController controller = loader.getController();
//                controller.SetIdOffreEmploi(idEmploi.gettext);
    }
    }

    @FXML
    private void addD(ActionEvent event) {
        
            try {
            FXMLLoader fxmlLoader = new FXMLLoader();
             
          
               fxmlLoader.setLocation(getClass().getResource("/pidev_java/gui/demandeEmploi/AjoutDemandeE.fxml"));
               fxmlLoader.load();
               AjoutDemandeEController ajout = fxmlLoader.getController();
               Parent parent = fxmlLoader.getRoot();
               ajout.setOffreE(offre);
               Stage stage = new Stage();
                    stage.setScene(new Scene(parent));
                    stage.show();
        
         
            offreEmploi o = new offreEmploi();
            
        
            
            
        } catch (IOException ex) {
                System.out.println(ex.getMessage());        }  
           
    }
    
}
