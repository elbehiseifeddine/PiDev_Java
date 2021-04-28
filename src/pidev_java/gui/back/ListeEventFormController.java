/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev_java.gui.back;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Tab;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import pidev_java.entities.EventLoisir;
import pidev_java.entities.Formation;
import pidev_java.entities.Freelancer;
import pidev_java.entities.Participant;
import pidev_java.entities.Societe;
import pidev_java.gui.evenement.ItemEController;
import pidev_java.gui.evenement.ItemEventController;
import pidev_java.gui.formation.ItemController;
import pidev_java.gui.formation.ItemFController;
import pidev_java.services.EventService;
import pidev_java.services.FormationService;
import pidev_java.services.FreelancerService;
import pidev_java.services.ParticipantService;
import pidev_java.services.SocieteService;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class ListeEventFormController implements Initializable {

    @FXML
    private Tab tabMesFormation;
    @FXML
    private ScrollPane scrolFormation;
    @FXML
    private GridPane gridFormation;
    @FXML
    private Tab tabFormation;
    @FXML
    private ScrollPane scrolEvent;
    @FXML
    private GridPane gridEvent;
    
     private Freelancer Fcon;
    private Societe Scon;
    private int iducon;
    private String typeucon;
       FormationService fs = new FormationService();
       EventService es=new EventService();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
         Fcon=Freelancer.getInstance();
        Scon=Societe.getInstance();
        if(Fcon != null){
            iducon=Fcon.getId();
            typeucon="freelancer";
        }
        else{
            iducon=Scon.getId();
            typeucon="societe";
        }
      
            
            scrolFormation.setVisible(true);
         gridFormation.getChildren().clear();
         
         scrolEvent.setVisible(true);
         gridEvent.getChildren().clear();
         int columnEvent = 0;
        int rowEvent = 1;
         int columnForm = 0;
        int rowForm = 1;
        
        
         try {
            
           List<Formation> MesFormation=fs.Lister();
            for (int i = 0; i < MesFormation.size(); i++) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/pidev_java/gui/back/itemFBack.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();

                ItemFBackController itemController = fxmlLoader.getController();
                System.out.println(MesFormation.get(i).getLabelle());
                itemController.setData(MesFormation.get(i),this);

                 

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
            
           List<EventLoisir> MesEvent=es.Lister();
            for (int i = 0; i < MesEvent.size(); i++) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/pidev_java/gui/back/itemEBack.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();

                ItemEBackController itemFController = fxmlLoader.getController();
                
                itemFController.setData(MesEvent.get(i),this);


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
        // TODO
    }

public void udateListForm(){
       scrolFormation.setVisible(true);
         gridFormation.getChildren().clear();
       int columnForm = 0;
        int rowForm = 1;
        
        
         try {
            
           List<Formation> MesFormation=fs.Lister();
            for (int i = 0; i < MesFormation.size(); i++) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/pidev_java/gui/back/itemFBack.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();

                ItemFBackController itemController = fxmlLoader.getController();
                System.out.println(MesFormation.get(i).getLabelle());
                itemController.setData(MesFormation.get(i),this);

                 

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

public void updateListEvent(){
      
         scrolEvent.setVisible(true);
         gridEvent.getChildren().clear();
         int columnEvent = 0;
        int rowEvent = 1;
     try {
            
           List<EventLoisir> MesEvent=es.Lister();
            for (int i = 0; i < MesEvent.size(); i++) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/pidev_java/gui/back/itemEBack.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();

                ItemEBackController itemFController = fxmlLoader.getController();
                
                itemFController.setData(MesEvent.get(i),this);


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

public void deleteFormation(Formation Form){
        ParticipantService pser=new ParticipantService();
        FreelancerService FS=new FreelancerService();
        SocieteService SS=new SocieteService();
        String email="";
        List<Participant> Part=pser.ListerParEvent("formation", Form.getId());
        if(Part != null){
            for(int l=0;l<Part.size();l++){
                if(Part.get(l).getTypeU().equals("freelancer")){
                    email=FS.FindparID(Part.get(l).getF().getId()).getEmail();
                }
                else{
                    email=SS.FindparID(Part.get(l).getS().getId()).getEmail();
                }
                
                //envoie du l'email
                
            }
            pser.SupprimerParEvent("formation", Form.getId());
            
        }
        
         fs.Supprimer(Form);
         this.udateListForm();
}

 public void UpdateFormation(Formation Form){
         fs.Modifier(Form);
        
 }
 
  public void deleteEvent(EventLoisir event){
          ParticipantService pser=new ParticipantService();
        FreelancerService FS=new FreelancerService();
        SocieteService SS=new SocieteService();
        String email="";
        List<Participant> Part=pser.ListerParEvent("evenement", event.getId());
        if(Part != null){
            for(int l=0;l<Part.size();l++){
                if(Part.get(l).getTypeU().equals("freelancer")){
                    email=FS.FindparID(Part.get(l).getF().getId()).getEmail();
                }
                else{
                    email=SS.FindparID(Part.get(l).getS().getId()).getEmail();
                }
                
                //envoie du l'email
                
            }
            pser.SupprimerParEvent("evenement", event.getId());
            
        }
         es.Supprimer(event);
        this.updateListEvent();
    }
  
    public void UpdateEvent(EventLoisir event){
         es.Modifier(event);
       this.updateListEvent();
    }
    
}
