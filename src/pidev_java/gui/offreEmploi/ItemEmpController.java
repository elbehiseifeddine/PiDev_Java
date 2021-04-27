/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev_java.gui.offreEmploi;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;
import pidev_java.entities.offreEmploi;
import pidev_java.services.emploiService;

/**
 * FXML Controller class
 *
 * @author Ghassen Riahi
 */
public class ItemEmpController implements Initializable {

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
    @FXML
    private FontAwesomeIconView iconEdit;
    @FXML
    private FontAwesomeIconView icondelete;
    
    
    
    emploiService ss=new emploiService();
    ConsulterOffreEmploiController co;
    private offreEmploi offre;
    @FXML
    private Label idDevise;
    @FXML
    private ImageView aa;
        

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         
    }    

    
     public void setData(offreEmploi os,ConsulterOffreEmploiController fc) {
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
     }
    
    @FXML
    private void editoStage(MouseEvent event) {
         try {
                 FXMLLoader loader1 = new FXMLLoader ();
                 loader1.setLocation(getClass().getResource("/pidev_java/gui/offreEmploi/ModifieroffreEmploi.fxml"));
                
                 Parent  parent = (Parent)loader1.load();
                  Stage stage = new Stage();
                 stage.setScene(new Scene(parent));
                  stage.show();
                   
                  ModifieroffreEmploiController afc=loader1.getController();
                 afc.initUpdate(this.co,this.offre);
                // notificationEdit();
                
             } catch (Exception ex) {
                System.out.println("erreur");
             }
    }

    @FXML
    private void DeleteoStage(MouseEvent event) {
         try {
                                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
       alert.setHeaderText(null);
        alert.setContentText("voulez vous supprimer l'offre!");
        
        ButtonType yesButton = new ButtonType("Oui");
          //  ButtonType noButton = new ButtonType("No");
            ButtonType cancelButton = new ButtonType("Annuler", ButtonBar.ButtonData.CANCEL_CLOSE);

            alert.getButtonTypes().setAll(yesButton,cancelButton);
        
 
        Optional<ButtonType> result =alert.showAndWait();
        if(result.get() == cancelButton)
{
    alert.close();
}  else if(result.get() == yesButton){
         this.ss.delete(this.offre.getId());
         notification();
    }
        } catch (Exception ex) {
                                System.out.println("erreur");
                            }
    }
    
     public void notification(){
      // Image img = new Image("tt.png");
        Notifications notificationBuilder = Notifications.create()
                .title("Succés de suppression")
                .text("votre offre a été supprimer avec succés")
              //  .graphic(new ImageView(img))
                .hideAfter(Duration.seconds(5))
                .position(Pos.TOP_RIGHT)
                .onAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                 System.out.println("clicked on notification");
            }
        });
        notificationBuilder.darkStyle();
        notificationBuilder.showInformation();
       
    
    }
     
    
       
    
    
    
}
