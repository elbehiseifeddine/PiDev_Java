package pidev_java.gui.publication;

import pidev_java.entities.Publications;
import pidev_java.services.PublicationsService;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.layout.GridPane;

import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import pidev_java.entities.Commentaires;
import pidev_java.entities.Freelancer;

/**
 * Created by Johannes on 23.05.16.
 *
 */

public class publicationsListViewCell extends ListCell<Publications> {

    @FXML
    private Label lb_user_id;
    @FXML
    private Label lb_pub_desc;
    @FXML
    private Label lb_pub_date;
    @FXML
    private ImageView img_pub;
    @FXML
    private GridPane gridPane;
    @FXML
    private Button btn_show_coms;
    @FXML
    private Button btn_pub_sup;
    @FXML
    private Button btn_pub_edit;
    private FXMLLoader mLLoader;
        
    
    
    @Override
    protected void updateItem(Publications publications, boolean empty)  {
        super.updateItem(publications, empty);
        
        

        if(empty || publications == null) {

            setText(null);
            setGraphic(null);

        } else {
            if (mLLoader == null) {
                mLLoader = new FXMLLoader(getClass().getResource("/pidev_java/gui/publication/PubCell.fxml"));
                mLLoader.setController(this);

                try {
                    mLLoader.load();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }

            lb_user_id.setText(String.valueOf(publications.getFreelancer_id()));
            lb_pub_desc.setText(publications.getDescription());
            img_pub.setImage(new Image( publications.getImage()));
            lb_pub_date.setText(publications.getDate_publication());
            btn_show_coms.setOnAction(e->{
                Stage stage = new Stage();
                
                
                Parent root;
                try {
                    FXMLLoader loader =new FXMLLoader(getClass().getResource("Commentaires.fxml"));
                    root = (Parent) loader.load();
                    Scene scene =new Scene(root);
                    
                    CommentairesController coms=loader.getController();
                    coms.showComs(publications.getId());
        
        
                    stage.setScene(scene);
                    stage.setTitle("Gestion des commentaires");
                    stage.show();
                } catch (IOException ex) {
                    Logger.getLogger(publicationsListViewCell.class.getName()).log(Level.SEVERE, null, ex);
                } catch (SQLException ex) {
                    Logger.getLogger(publicationsListViewCell.class.getName()).log(Level.SEVERE, null, ex);
                }
                
            
            });

           

            setText(null);
            setGraphic(gridPane);
            
            btn_pub_sup.setOnAction(e->{
                Freelancer f = Freelancer.getInstance();
                
                if (publications.getFreelancer_id() == f.getId()){
                
                   PublicationsService ser = new PublicationsService();
                    try {
                        ser.delete(publications.getId());
                    } 
                    catch (SQLException ex) {
                        Logger.getLogger(publicationsListViewCell.class.getName()).log(Level.SEVERE, null, ex);
                    }
    ;


                       JOptionPane.showMessageDialog(null, "Publication suprimée !");
                }
                else {
                    JOptionPane.showMessageDialog(null, "Cette publication ne vous appartient pas !");
                }
            
            });
            
            btn_pub_edit.setOnAction(e->{
                Freelancer f = Freelancer.getInstance();
                Stage stage = new Stage();
                
                
                Parent root;
                
                if (publications.getFreelancer_id() == f.getId()){
                
                   FXMLLoader loader =new FXMLLoader(getClass().getResource("PubEdit.fxml"));
                    try {
                        root = (Parent) loader.load();
                        Scene scene =new Scene(root);
                    
                    PubEditController pubs=loader.getController();
                    pubs.pubEdit(publications.getId(),publications.getDescription(),publications.getDate_publication(),publications.getImage());
        
        
                    stage.setScene(scene);
                    stage.setTitle("Gestion des commentaires");
                    stage.show();
                    } catch (IOException ex) {
                        Logger.getLogger(publicationsListViewCell.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    
                }
                else {
                    JOptionPane.showMessageDialog(null, "Cette publication ne vous appartient pas !");
                }
            
            });
        }
        

    }
    
   
}