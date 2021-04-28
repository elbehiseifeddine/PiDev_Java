/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev_java.gui.demandeEmploi;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import pidev_java.entities.DemandeEmploi;
import pidev_java.entities.Freelancer;
import pidev_java.entities.Societe;
import pidev_java.entities.offreEmploi;
import pidev_java.entities.offreStage;
import pidev_java.services.DemandeEmploiService;
import pidev_java.utils.MaConnection;

/**
 * FXML Controller class
 *
 * @author ely
 */
public class AfficherDemandeSocController implements Initializable {

    @FXML
    private ListView<HBox> listDemande;
     @FXML
    private ListView<HBox> listOffre;
    @FXML
    private Button btnList;
    @FXML
    private Button btnDissaprouve;
    @FXML
    private Button btnApprouver;
   String query = null;
    Connection connection = null ;
    Connection cnx = MaConnection.getInstance().getCnx();
    PreparedStatement preparedStatement = null ;
    ResultSet resultSet = null ;
    DemandeEmploi demande = null ;
     ArrayList<DemandeEmploi>  DemandeList = new ArrayList<DemandeEmploi>();
     ArrayList<offreEmploi>  offreList = new ArrayList<offreEmploi>();
     ArrayList<offreStage>  offreSList = new ArrayList<offreStage>();
   
     DemandeEmploiService Cls =DemandeEmploiService.getInstance();
     DemandeEmploi D  = new DemandeEmploi();
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         Alert alert = new Alert(AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setHeaderText("vous voulez supprimer une demande?");
            alert.setContentText("Please choose an option.");

            ButtonType yesButton = new ButtonType("Yes");
            ButtonType noButton = new ButtonType("No");
            

            alert.getButtonTypes().setAll(yesButton, noButton);

           Alert alert1 = new Alert(Alert.AlertType.CONFIRMATION);
        ArrayList<offreEmploi> List = new ArrayList<offreEmploi>();
        ArrayList<offreStage> List2 = new ArrayList<offreStage>();
        List= loadOFFDataFromDatabase();
        List2= loadOFFSDataFromDatabase();
        for(offreEmploi d : List){
            Label lb = new Label(d.getNomProjet());
            Label id = new Label(String.valueOf(d.getId()));
            
            id.setTranslateX(25);
            id.setOpacity(0);
            
             lb.setTranslateX(15.0);
            
            Label lb2 = new Label();
              try {
                  lb2.setGraphic( new ImageView(new Image(new FileInputStream("C:\\Users\\ely\\Desktop\\esprit y3\\semestre 2\\project\\javaFX\\PiDev_Java\\src\\pidev_java\\assets/iconD.png"))));
              } catch (FileNotFoundException ex) {
                  Logger.getLogger(DemandeEmploiController.class.getName()).log(Level.SEVERE, null, ex);
              }
            HBox h = new HBox(lb2,lb,id);
           
            
            
          listOffre.getItems().add(h);
        }
        
        
        /* for(offreStage d : List2){
        Label lb0 = new Label(d.getNomProjet());
        Label id0 = new Label(String.valueOf(d.getId()));
        
        id0.setTranslateX(25);
        id0.setOpacity(0);
        
        lb0.setTranslateX(15.0);
        
        Label lb3 = new Label();
        try {
        lb3.setGraphic( new ImageView(new Image(new FileInputStream("C:\\Users\\ely\\Desktop\\esprit y3\\semestre 2\\project\\javaFX\\PiDev_Java\\src\\pidev_java\\assets/iconD.png"))));
        } catch (FileNotFoundException ex) {
        Logger.getLogger(AfficherDemandeSocController.class.getName()).log(Level.SEVERE, null, ex);
        }
        HBox h1 = new HBox(lb3,lb0,id0);
        
        
        
        listOffre.getItems().add(h1);
        }*/
        
           /*      btnApprouver.setOnAction(xx->{
           Optional<ButtonType> result = alert.showAndWait();
           if(result.get() == yesButton)
           {
           supprimerDemande();
           JOptionPane.showMessageDialog(null, "Demande supprimer !");
           }
           else if(result.get() == noButton)
           {
           
           }
           
           
           
           });*/
           /*   btnDissaprouve.setOnAction(xx->{
           Optional<ButtonType> result = alert.showAndWait();
           if(result.get() == yesButton)
           { Boolean test;
           test= updateDemande(D);
           if(test==true)
           JOptionPane.showMessageDialog(null, "Demande modifier !");
           }
           else if(result.get() == noButton)
           {
           
           }
           
           });*/
    }    

       private ArrayList<offreEmploi> loadOFFDataFromDatabase() {
      
          try {
              Connection cnx = MaConnection.getInstance().getCnx();
          
              Societe s = Societe.getInstance();
          query = "SELECT * FROM `offre_emploi`WHERE societe_id=?";
            preparedStatement = cnx.prepareStatement(query);
            preparedStatement.setInt(1,s.getId());
        
            resultSet = preparedStatement.executeQuery();
            
            while (resultSet.next()){
                
                offreList.add(new  offreEmploi(resultSet.getInt("id") ,resultSet.getString("nom_projet")));
                        
                        
                        
           
                
            
             
            }
            
             
            
           
        } catch (SQLException ex) {
            Logger.getLogger(AfficherDemandeSocController.class.getName()).log(Level.SEVERE, null, ex);
        }
return offreList;
    }
      private ArrayList<offreStage> loadOFFSDataFromDatabase() {
      
          try {
              Connection cnx = MaConnection.getInstance().getCnx();
          
              Societe s = Societe.getInstance();
          query = "SELECT * FROM `offre_stage`WHERE societe_id=?";
            preparedStatement = cnx.prepareStatement(query);
            preparedStatement.setInt(1,s.getId());
        
            resultSet = preparedStatement.executeQuery();
            
            while (resultSet.next()){
                
                offreList.add(new  offreEmploi(resultSet.getInt("id") ,resultSet.getString("nom_projet")));
                        
                        
                        
           
                
            
             
            }
            
             
            
           
        } catch (SQLException ex) {
            Logger.getLogger(AfficherDemandeSocController.class.getName()).log(Level.SEVERE, null, ex);
        }
return offreSList;
    }
    
}
