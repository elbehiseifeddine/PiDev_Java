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
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
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
import javax.swing.JOptionPane;
import pidev_java.entities.DemandeEmploi;
import pidev_java.entities.DemandeStage;
import pidev_java.entities.Freelancer;
import pidev_java.entities.Societe;
import pidev_java.entities.offreEmploi;
import pidev_java.entities.offreStage;
import pidev_java.gui.demandeStage.DemandeStageController;
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
     ArrayList<DemandeStage>  DemandeList2 = new ArrayList<DemandeStage>();
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
        Logger.getLogger(AfficherDemandeSocController.class.getName()).log(Level.SEVERE, null, ex);
        }
        HBox h = new HBox(lb2,lb,id);
        
        
        
        listOffre.getItems().add(h);
        }
        
        
         for(offreStage d : List2){
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
        }
        
         /*    btnApprouver.setOnAction(xx->{
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
         btnList.setOnAction(xx->{
        
        
         loadDemande();
        
         
         
         
         
         });
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
      
        private DemandeEmploi searchDemande(int id){
           DemandeEmploi d = new DemandeEmploi();
             try {
             
            String requete = "SELECT * FROM `demande_emploi`WHERE id=?";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setInt(1,id);
            ResultSet rs = pst.executeQuery();
              
                while (rs.next()){
                 java.sql.Date d1=rs.getDate("date_creation");
                    
                      
                      d.setId(rs.getInt("id"));
                      d.setFreelancer_id(rs.getInt("freelancer_id"));
                      d.setOffre_emploi_id(rs.getInt("offre_emploi_id"));
                       d.setDate_creation(d1);
                       d.setLettre(rs.getString("lettre"));
                       d.setDescription(rs.getString("description"));
                       d.setDomaine(rs.getString("domaine"));
                       d.setSalaire(rs.getDouble("salaire"));
                       d.setDiplome(rs.getString("diplome"));
                       d.setNom_societe(rs.getString("nomsociete"));
                 System.out.println(d.getDescription());
                }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return d;
       }
      private DemandeStage searchDemandeS(int id){
           DemandeStage d = new DemandeStage();
             try {
             
            String requete = "SELECT * FROM `demande_stage`WHERE id=?";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setInt(1,id);
            ResultSet rs = pst.executeQuery();
              
                while (rs.next()){
                 java.sql.Date d1=rs.getDate("date_creation");
                    
                      
                      d.setId(rs.getInt("id"));
                      d.setFreelancer_id(rs.getInt("freelancer_id"));
                      d.setOffre_stage_id(rs.getInt("offre_stage_id"));
                       d.setDate_creation(d1);
                       d.setLettre(rs.getString("lettre"));
                       d.setDescription(rs.getString("description"));
                       d.setDomaine(rs.getString("domaine"));
                       d.setDuree(rs.getInt("duree"));
                       d.setType(rs.getString("type"));
                       d.setNom_societe(rs.getString("nomsociete"));
                       d.setEtude(rs.getString("etude"));
                 System.out.println(d.getDescription());
                }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return d;
       }
      
      private ArrayList<DemandeEmploi> loadDataFromDatabase(int id_off) {
      
          try {
              Connection cnx = MaConnection.getInstance().getCnx();
          
             
          query = "SELECT * FROM `demande_emploi`WHERE offre_emploi_id=?";
            preparedStatement = cnx.prepareStatement(query);
            preparedStatement.setInt(1,id_off);
            resultSet = preparedStatement.executeQuery();
            
            while (resultSet.next()){
                
                DemandeList.add(new  DemandeEmploi( 
                        
                        resultSet.getInt("id"), 
                        resultSet.getInt("freelancer_id"),
                        resultSet.getString("description"), 
                        resultSet.getString("lettre"),
                        resultSet.getDate("date_creation"),
                        resultSet.getString("domaine") ,
                        resultSet.getString("diplome") ,
                        resultSet.getFloat("salaire"),
                        resultSet.getInt("offre_emploi_id")
                    
                ));
                        
                        
                        
           
                
            
             
            }
            
             
            
           
        } catch (SQLException ex) {
            Logger.getLogger(DemandeEmploiController.class.getName()).log(Level.SEVERE, null, ex);
        }
return DemandeList;
    }
      
      private ArrayList<DemandeStage> loadDataSFromDatabase(int id_offre) {
      
          try {
              Connection cnx = MaConnection.getInstance().getCnx();
          
             
          query = "SELECT * FROM `demande_stage`WHERE offre_stage_id=?";
            preparedStatement = cnx.prepareStatement(query);
            preparedStatement.setInt(1,id_offre);
            resultSet = preparedStatement.executeQuery();
            
            while (resultSet.next()){
                
                DemandeList2.add(new  DemandeStage(
                        
                        resultSet.getInt("id"), 
                        resultSet.getInt("freelancer_id"),
                        resultSet.getInt("offre_stage_id"),
                        resultSet.getString("description"), 
                        resultSet.getString("lettre"),
                        resultSet.getDate("date_creation"),
                        
                        resultSet.getString("domaine") ,
                        resultSet.getString("type") ,
                        resultSet.getInt("duree"),
                        resultSet.getString("etude"),
                        resultSet.getString("nom_societe")
                ));
                        
                        
                        
           
                
            
             
            }
            
             
            
           
        } catch (SQLException ex) {
            Logger.getLogger(DemandeStageController.class.getName()).log(Level.SEVERE, null, ex);
        }
return DemandeList2;
    }
      
    private void loadDemande() {
        
                   ObservableList<HBox> selectedRows, allDemande;
        allDemande = listOffre.getItems();
        
        
        //this gives us the rows that were selected
        selectedRows = listOffre.getSelectionModel().getSelectedItems();
        
           for (HBox d: selectedRows)
        {
           Label l=(Label) d.getChildren().get(2);
            
       
        DemandeEmploiService Cls = DemandeEmploiService.getInstance();
        int id =Integer.valueOf(l.getText());
            ArrayList<DemandeStage> List = new ArrayList<DemandeStage>();
           ArrayList<DemandeEmploi> List2 = new ArrayList<DemandeEmploi>();
            List2=loadDataFromDatabase(id);
            List=loadDataSFromDatabase(id);
       
         for(DemandeEmploi f : List2){
            Label lb = new Label(f.getDescription());
            Label id2 = new Label(String.valueOf(f.getId()));
            Label lb1 = new Label(f.getDomaine());
            id2.setTranslateX(25);
            id2.setOpacity(0);
            lb1.setTranslateX(20);
             lb.setTranslateX(15.0);
            
            Label lb2 = new Label();
              try {
                  lb2.setGraphic( new ImageView(new Image(new FileInputStream("C:\\Users\\ely\\Desktop\\esprit y3\\semestre 2\\project\\javaFX\\PiDev_Java\\src\\pidev_java\\assets/iconD.png"))));
              } catch (FileNotFoundException ex) {
                  Logger.getLogger(AfficherDemandeSocController.class.getName()).log(Level.SEVERE, null, ex);
              }
            HBox h = new HBox(lb2,lb,lb1,id2);
           
            
            
          listDemande.getItems().add(h);
        }
         
            for(DemandeStage s : List){
            Label lb = new Label(s.getDescription());
            Label id3 = new Label(String.valueOf(s.getId()));
            Label lb2 = new Label(s.getType());
            id3.setTranslateX(25);
            id3.setOpacity(0);
            lb2.setTranslateX(20);
             lb.setTranslateX(15.0);
            
            Label lb3 = new Label();
              try {
                  lb2.setGraphic( new ImageView(new Image(new FileInputStream("C:\\Users\\ely\\Desktop\\esprit y3\\semestre 2\\project\\javaFX\\PiDev_Java\\src\\pidev_java\\assets/iconD.png"))));
              } catch (FileNotFoundException ex) {
                  Logger.getLogger(AfficherDemandeSocController.class.getName()).log(Level.SEVERE, null, ex);
              }
            HBox h = new HBox(lb3,lb,lb2,id3);
           
            
            
          listDemande.getItems().add(h);
        }
        } 
    }
}
