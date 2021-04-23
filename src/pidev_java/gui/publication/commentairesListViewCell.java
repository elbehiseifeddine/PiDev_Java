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
import pidev_java.services.CommentairesService;

/**
 * Created by Johannes on 23.05.16.
 *
 */

public class commentairesListViewCell extends ListCell<Commentaires> {

    @FXML
    private Label lb_util_id;
    @FXML
    private Label lb_com_desc;
    @FXML
    private Label lb_com_date;
    @FXML
    private GridPane gridPane;
    @FXML
    private FXMLLoader mLLoader;
    @FXML
    private Button btn_com_edit;
    @FXML
    private Button btn_com_supp;
    
    
    @Override
    protected void updateItem(Commentaires commentaires, boolean empty)  {
        
        super.updateItem(commentaires, empty);

        if(empty || commentaires == null) {

            setText(null);
            setGraphic(null);

        } else {
            if (mLLoader == null) {
                mLLoader = new FXMLLoader(getClass().getResource("/pidev_java/gui/publication/ComCell.fxml"));
                mLLoader.setController(this);

                try {
                    mLLoader.load();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }

            lb_util_id.setText(String.valueOf(commentaires.getNomUtil()) + " " + String.valueOf(commentaires.getPrenomUtil()));
            lb_com_desc.setText(commentaires.getDescription());
            
            lb_com_date.setText(commentaires.getDate_commentaire());
            

           

            setText(null);
            setGraphic(gridPane);
            Freelancer f = Freelancer.getInstance();
            if (commentaires.getFreelancer_id() != f.getId()){
                btn_com_supp.setVisible(false) ;
                btn_com_edit.setVisible(false);
            }
            
            btn_com_supp.setOnAction(e->{
                
                if (commentaires.getFreelancer_id() == f.getId()){
                
                   CommentairesService ser = new CommentairesService();
                    try {
                        ser.delete(commentaires.getId());
                    } 
                    catch (SQLException ex) {
                        Logger.getLogger(publicationsListViewCell.class.getName()).log(Level.SEVERE, null, ex);
                    }
    ;


                       JOptionPane.showMessageDialog(null, "Commentaire suprimé !");
                }
                else {
                    JOptionPane.showMessageDialog(null, "Ce commentaire ne vous appartient pas !");
                }
            
            });
            
            btn_com_edit.setOnAction(e->{
                Stage stage = new Stage();
                
                
                Parent root;
                
                if (commentaires.getFreelancer_id() == f.getId()){
                
                   FXMLLoader loader =new FXMLLoader(getClass().getResource("ComEdit.fxml"));
                    try {
                        root = (Parent) loader.load();
                        Scene scene =new Scene(root);
                    
                    ComEditController coms=loader.getController();
                    coms.comEdit(commentaires.getId(),commentaires.getDescription(),commentaires.getDate_commentaire());
        
        
                    stage.setScene(scene);
                    stage.setTitle("Gestion des commentaires");
                    stage.show();
                    } catch (IOException ex) {
                        Logger.getLogger(publicationsListViewCell.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    
                }
                else {
                    JOptionPane.showMessageDialog(null, "Ce commentaire ne vous appartient pas !");
                }
            
            });
        }
        

    }
    
   
}