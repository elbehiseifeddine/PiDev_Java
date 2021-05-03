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
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.NodeOrientation;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javax.swing.JOptionPane;
import pidev_java.entities.DemandeEmploi;
import pidev_java.entities.Freelancer;
import pidev_java.services.DemandeEmploiService;
import pidev_java.utils.MaConnection;

/**
 * FXML Controller class
 *
 * @author ely
 */
public class DemandeEmploiController implements Initializable {

    @FXML
    private ListView<HBox> listDemande;
    @FXML
    private Button btnModifier;
    @FXML
    private Button btnValider;
    @FXML
    private Button btnsupp;
    @FXML
    private Button btnRetour;
      @FXML
    private TextField tfDescription;
    @FXML
    private TextField tfDomaine;
    @FXML
    private TextField tfDiplome;
    @FXML
    private TextArea tflettre;
    @FXML
    private TextField tfSalaire;
    
     @FXML
    private Button btnFile;

     String query = null;
    Connection connection = null ;
    Connection cnx = MaConnection.getInstance().getCnx();
    PreparedStatement preparedStatement = null ;
    ResultSet resultSet = null ;
    DemandeEmploi demande = null ;
     ArrayList<DemandeEmploi>  DemandeList = new ArrayList<DemandeEmploi>();
   
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
        ArrayList<DemandeEmploi> List = new ArrayList<DemandeEmploi>();
        List= loadDataFromDatabase();
        for(DemandeEmploi d : List){
            Label lb = new Label(d.getDescription());
            Label id = new Label(String.valueOf(d.getId()));
            Label lb1 = new Label(d.getDiplome());
            id.setTranslateX(25);
            id.setOpacity(0);
            lb1.setTranslateX(20);
             lb.setTranslateX(15.0);
            
            Label lb2 = new Label();
              try {
                  lb2.setGraphic( new ImageView(new Image(new FileInputStream("C:\\Users\\seifeddine\\Documents\\NetBeansProjects\\PiDev_Java\\src\\pidev_java\\assets\\iconD.png"))));
              } catch (FileNotFoundException ex) {
                  Logger.getLogger(DemandeEmploiController.class.getName()).log(Level.SEVERE, null, ex);
              }
            HBox h = new HBox(lb2,lb,lb1,id);
           
            
            
          listDemande.getItems().add(h);
        }
        
              btnsupp.setOnAction(xx->{
          Optional<ButtonType> result = alert.showAndWait();
            if(result.get() == yesButton)
            {
                supprimerDemande();
                JOptionPane.showMessageDialog(null, "Demande supprimer !");
            }
            else if(result.get() == noButton)
            {
               
            }
           
           
           
        });
                btnValider.setOnAction(xx->{
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
                   
        });
    }    

    @FXML
    private void modifierDemande(ActionEvent event) {
        
                   ObservableList<HBox> selectedRows, allDemande;
        allDemande = listDemande.getItems();
        
        
        //this gives us the rows that were selected
        selectedRows = listDemande.getSelectionModel().getSelectedItems();
        
           for (HBox d: selectedRows)
        {
           Label l=(Label) d.getChildren().get(3);
            
       
        DemandeEmploiService Cls = DemandeEmploiService.getInstance();
        int id =Integer.valueOf(l.getText());
           D= searchDemande(id);
           
          tfDescription.setText(D.getDescription());
        tfDiplome.setText(D.getDiplome());
        tfDomaine.setText(D.getDomaine());
        
       tfSalaire.setText(String.valueOf(D.getSalaire()));
       tflettre.setText(D.getLettre());
        } 
    }

    @FXML
    private void SupprimerDemande(ActionEvent event) {
    }

    @FXML
    private void Retour(ActionEvent event) {
    }
    
     private ArrayList<DemandeEmploi> loadDataFromDatabase() {
      
          try {
              Connection cnx = MaConnection.getInstance().getCnx();
          
             Freelancer f = Freelancer.getInstance();
          query = "SELECT * FROM `demande_emploi`WHERE freelancer_id=?";
            preparedStatement = cnx.prepareStatement(query);
            preparedStatement.setInt(1,f.getId());
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
     
     
       private void supprimerDemande() {
        ObservableList<HBox> selectedRows, allDemande;
        allDemande = listDemande.getItems();
        
        //this gives us the rows that were selected
        selectedRows = listDemande.getSelectionModel().getSelectedItems();
        
        //loop over the selected rows and remove the Person objects from the table
          for (HBox d: selectedRows)
        {
           Label l=(Label) d.getChildren().get(3);
            
        allDemande.remove(d);
        DemandeEmploiService Cls = DemandeEmploiService.getInstance();
        int id =Integer.valueOf(l.getText());
        Cls.supprimer(id);
        
        }
        
     
    
    }
       
       private Boolean updateDemande(DemandeEmploi d){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setHeaderText("Form non valide !");
            alert.setContentText("verifie les champs !");
             Boolean valid =true;
        if(!(tfDescription.getText().isEmpty()) ||!(tfDescription.getText().length()>=30)){
            d.setDescription(tfDescription.getText());
        }else{
            valid=false;
          
        }
        if(!(tfDiplome.getText().isEmpty())){
           d.setDiplome(tfDiplome.getText()); 
        }else
        {
            valid=false;
        }
        if(!tfDomaine.getText().isEmpty()){
            d.setDomaine(tfDomaine.getText());
        }else
        {
            valid=false;
        }
        if(!tflettre.getText().isEmpty()){
             d.setLettre(tflettre.getText());
        }else{
            valid=false;
        }
            
       if(!tfSalaire.getText().isEmpty()){
            String s = tfSalaire.getText();
            try {
              
                d.setSalaire(Float.parseFloat(s));
           } catch (NumberFormatException  e) {
               valid=false;
           }
       
       }else
       {
           valid=false;
       }
       
       if(valid==true){
           DemandeEmploiService Cls = DemandeEmploiService.getInstance();
          Cls.modifier(d);
           tfDescription.setText("");
        tfDiplome.setText("");
        tfDomaine.setText("");
        tflettre.setText("");
        tfSalaire.setText("");
        
       }else
       {
           alert.showAndWait();
       }
        
       return valid;
  
           
           
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
}