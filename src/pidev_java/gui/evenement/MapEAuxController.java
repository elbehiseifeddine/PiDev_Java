/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev_java.gui.evenement;

import com.sothawo.mapjfx.Coordinate;
import com.sothawo.mapjfx.MapLabel;
import com.sothawo.mapjfx.MapView;
import com.sothawo.mapjfx.Marker;
import com.sothawo.mapjfx.event.MarkerEvent;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javax.imageio.ImageIO;
import pidev_java.entities.EventLoisir;
import pidev_java.entities.Formation;
import pidev_java.gui.formation.FormDetailsController;
import pidev_java.gui.formation.MapFauxController;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class MapEAuxController implements Initializable {

    @FXML
    private Button btnshow;
    @FXML
    private MapView map;
    
    private List<EventLoisir> EventAux;
    private EvenementController Ec;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         map.initialize();

        Coordinate center1 = new Coordinate(35.6262, 10.3989);
        map.setCenter(center1);
         

        map.setZoom(7);
        // TODO
    }    
    
      public void inti(List<EventLoisir> Ea,EvenementController ec){
        this.EventAux=Ea;
        this.Ec=ec;
        for(int i=0;i<EventAux.size();i++){
            try {
                this.resize("C:\\Users\\ASUS\\OneDrive\\Bureau\\PiDev_Java\\src\\pidev_java\\assets\\"+EventAux.get(i).getImageE(), "C:\\Users\\ASUS\\OneDrive\\Bureau\\PiDev_Java\\src\\pidev_java\\assets\\im"+EventAux.get(i).getImageE(), 30, 30);    
            } catch (IOException ex) {
                System.out.println("erreur");
            }
        }
    }
      
       public  boolean resize(String inputImagePath,
            String outputImagePath, int scaledWidth, int scaledHeight)
            throws IOException {
        // reads input image
        File inputFile = new File(inputImagePath);
        BufferedImage inputImage = ImageIO.read(inputFile);
 
        // creates output image
        /*BufferedImage outputImage = new BufferedImage(scaledWidth,
                scaledHeight, BufferedImage.TYPE_INT_BGR);*/
        
        
    Image resultingImage = inputImage.getScaledInstance( scaledWidth, scaledHeight, Image.SCALE_DEFAULT);
    BufferedImage outputImage = new BufferedImage(scaledWidth, scaledHeight, BufferedImage.TYPE_INT_RGB);
    outputImage.getGraphics().drawImage(resultingImage, 0, 0, null);
 
        // scales the input image to the output image
        /*Graphics2D g2d = outputImage.createGraphics();
        g2d.drawImage(inputImage, 0, 0, scaledWidth, scaledHeight, null);
        g2d.dispose();
 */
        // extracts extension of output file
        String formatName = outputImagePath.substring(outputImagePath
                .lastIndexOf(".") + 1);
 
        // writes to output file
       return  ImageIO.write(outputImage, formatName, new File(outputImagePath));
    }

//    @FXML
//    private void showEAux(MouseEvent event) {
//        System.out.println(EventAux.size()+" hadha size EventAux");
//      for (int i=0;i<EventAux.size();i++){
//                 try {
////                     Coordinate FPlace=new Coordinate(EventAux.get(i).getLat(), EventAux.get(i).getLng());
//                     //Marker FormPlace = Marker.createProvided(Marker.Provided.RED).setPosition(FPlace).setVisible(
//                     //true);
//            
//               
//                     
//                     Marker FormPlace= new Marker(getClass().getResource("/pidev_java/assets/im"+EventAux.get(i).getImageE()), 20, 20).setPosition(FPlace)
//                             .setVisible(true);
//                     
//                 
//                     
//                     
//                     
//                     
//                     
//                     map.addMarker(FormPlace);
//                     map.addEventHandler(MarkerEvent.MARKER_CLICKED, eventm -> {
//                       EventLoisir Ev=new EventLoisir();
//                                  
//                         try {
//                             eventm.consume();
//                             for(int j=0;j<EventAux.size();j++){
//                                 if((EventAux.get(j).getLng()==eventm.getMarker().getPosition().getLongitude()) &&(EventAux.get(j).getLat()==eventm.getMarker().getPosition().getLatitude())){
//                                      Ev=EventAux.get(j);
//                                 }
//                             }
//                             
//                             
//                             FXMLLoader loader1 = new FXMLLoader ();
//                             loader1.setLocation(getClass().getResource("/pidev_java/gui/evenement/EventDatails.fxml"));
//                             
//                             Parent  parent = (Parent)loader1.load();
//                             Stage stage = new Stage();
//                             stage.setScene(new Scene(parent));
//                             stage.show();
//                             
//                             EventDatailsController fdc=loader1.getController();
//                             fdc.init(Ev, Ec);
//                             
//                         } catch (IOException ex) {
//                             System.out.println("erreur");
//                         }
//                         
//                         
//                     }); 
//                      map.addEventHandler(MarkerEvent.MARKER_RIGHTCLICKED, eventm -> {
//                         eventm.consume();
//                          EventLoisir Ev=new EventLoisir();
//                 
//                            for(int j=0;j<EventAux.size();j++){
//                                 if((EventAux.get(j).getLng()==eventm.getMarker().getPosition().getLongitude()) &&(EventAux.get(j).getLat()==eventm.getMarker().getPosition().getLatitude())){
//                                      Ev=EventAux.get(j);
//                                 }
//                             }
//                         
//                         MapLabel  LForm=new MapLabel(Ev.getLabelle(), 10, -10).setVisible(true);
//                         Coordinate C=new Coordinate(Ev.getLat(),Ev.getLng());
//                         LForm.setPosition(C);
//                          System.out.println(LForm.getText());
//                          map.addLabel(LForm);
//                         
//        // the attached labels, custom style
//                          
//                            
//                          
//                          
//                     
//                         
//                     });
//                      
//                           map.addEventHandler(MarkerEvent.MARKER_MOUSEUP, eventm -> {
//                         eventm.consume();
//                               System.out.println("c bn mousse vover");
//                            int id =Integer.parseInt(eventm.getMarker().getId().split("-")[1]);
//                             EventLoisir For=EventAux.get(id-1);
//                         
//                         MapLabel LForm=eventm.getMarker().getMapLabel().get();
//                               System.out.println(LForm.getPosition());
//                          System.out.println(LForm.getText());
//                          map.removeLabel(LForm);
//                         
//        // the attached labels, custom style
//                          
//                            
//                          
//                          
//                     
//                         
//                     });
//                 
//                 
//                 
//                 
//                 } catch (Exception ex) {
//                     System.out.println(ex.getMessage());
//                 }
//            
//            
//            }
//            
//        
//    }
        
    
}
