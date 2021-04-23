/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev_java.gui.offreStage;

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
//import org.controlsfx.control.Notifications;
import pidev_java.entities.offreStage;
import pidev_java.services.stageService;

/**
 * FXML Controller class
 *
 * @author Ghassen Riahi
 */
public class ItemController implements Initializable {

    @FXML
    private Label idNom;
    @FXML
    private Label idComp;
    @FXML
    private Label idDesc;
    @FXML
    private Label idDuree;
    @FXML
    private Label idType;
    @FXML
    private Label idDTexpr;
    @FXML
    private Label idDomaine;
    @FXML
    private FontAwesomeIconView iconEdit;
    @FXML
    private FontAwesomeIconView icondelete;

    
    stageService ss=new stageService();
    ConsulterOffreStageController co;
    private offreStage offre;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }   
    
    
    
     public void setData(offreStage os,ConsulterOffreStageController fc) {
         this.co=fc;
        this.offre = os;
        
        this.idNom.setText(os.getNomProjet());
        this.idComp.setText(os.getCompetence());
        this.idDesc.setText(os.getDescription());
        this.idDomaine.setText(String.valueOf(os.getDomaine()));
        this.idDuree.setText(String.valueOf(os.getDuree()));
        this.idType.setText(String.valueOf(os.getTypeStage()));
        this.idDTexpr.setText(String.valueOf(os.getDateExpiration()));
        
        
        

        
        
    }

    @FXML
    private void editoStage(MouseEvent event) {
        try {
                 FXMLLoader loader1 = new FXMLLoader ();
                 loader1.setLocation(getClass().getResource("/pidev_java/gui/offreStage/ModifieroffreStage.fxml"));
                
                 Parent  parent = (Parent)loader1.load();
                  Stage stage = new Stage();
                 stage.setScene(new Scene(parent));
                  stage.show();
                   
                  ModifieroffreStageController afc=loader1.getController();
                 afc.initUpdate(this.co,this.offre);
                
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
        //notification();
    }
        } catch (Exception ex) {
                                System.out.println("erreur");
                            }
    
}
    
    
//    public void notification(){
//      // Image img = new Image("tt.png");
//        Notifications notificationBuilder = Notifications.create()
//                .title("Succés de suppression")
//                .text("votre offre a été supprimer")
//               .graphic(null)
//                .hideAfter(Duration.seconds(5))
//                .position(Pos.TOP_RIGHT)
//                .onAction(new EventHandler<ActionEvent>() {
//            @Override
//            public void handle(ActionEvent event) {
//                 System.out.println("clicked on notification");
//            }
//        });
//        notificationBuilder.darkStyle();
//        notificationBuilder.showInformation();
//       
//    
//    }
}
