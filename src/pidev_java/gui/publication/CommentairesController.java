/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev_java.gui.publication;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import pidev_java.entities.Commentaires;
import pidev_java.entities.Freelancer;
import pidev_java.entities.Publications;
import pidev_java.services.CommentairesService;
import pidev_java.services.PublicationsService;

/**
 * FXML Controller class
 *
 * @author ksemt
 */
public class CommentairesController implements Initializable {

    @FXML
    private ListView<Commentaires> list_com;
    @FXML
    private Button btn_com_add;
    @FXML
    private TextField tf_com_add;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
    }    

    @FXML
    private void AjouterCommentaire(ActionEvent event) {
    }
    
    public ObservableList<Commentaires> loadE(int id_pub) throws SQLException {
         
        CommentairesService ser = new CommentairesService();
        ResultSet ps = ser.getCommentaires(id_pub);
        ObservableList e = FXCollections.observableArrayList();
        
            while(ps.next()) {
            e.add(new Commentaires(
                  
                    ps.getInt("id"),
                    ps.getString("description"),
                    ps.getString("date_com"),
                    ps.getInt("id_pub_id"),
                    ps.getInt("id_util_id"),
                    ps.getInt("societe_id")
                    
            ));
                    
        }
        list_com.setItems(e);
        return e;
    }
    
    public void showComs( int id_pub) throws SQLException {
        
        try {
                
                list_com.setItems(loadE(id_pub));
            list_com.setCellFactory(commentairesListView -> new commentairesListViewCell());
                
            } catch (SQLException ex) {
                System.out.println(ex);
            }
        Freelancer f = Freelancer.getInstance();
        btn_com_add.setOnAction(e->{
            Commentaires c = new Commentaires();
            c.setDescription(tf_com_add.getText());
            c.setId_pub(id_pub);
            c.setFreelancer_id(f.getId());
            
            new CommentairesService().ajouter(c);
            JOptionPane.showMessageDialog(null, "commentaire Ajouté !");
            tf_com_add.setText("");
            
            
        });
        loadE(id_pub);
    }
    
}
