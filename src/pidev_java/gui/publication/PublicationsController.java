/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev_java.gui.publication;

import pidev_java.entities.Publications;
import pidev_java.services.PublicationsService;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ListCell;
import javafx.scene.control.SortEvent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import pidev_java.entities.Freelancer;

/**
 * FXML Controller class
 *
 * @author ksemt
 */
public class PublicationsController implements Initializable {
    
    File file;
    
    @FXML
    private TextField tf_pub_desc;
    @FXML
    private Button btn_pub_conf;
    @FXML
    private Button image;
    @FXML
    private ImageView img;
    @FXML
    private ListView<Publications> list_pub;
    
    

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)   {
        try {
                
                list_pub.setItems(loadE());
                list_pub.setCellFactory(publicationsListView -> new publicationsListViewCell());
                
            } catch (SQLException ex) {
                System.out.println(ex);
            }
    }    

    @FXML
    private void ajouterP(ActionEvent event) throws SQLException {
        Freelancer f = Freelancer.getInstance();
        Publications p = new Publications();
            p.setDescription(tf_pub_desc.getText());
            p.setImage(file.toURI().toString());
            p.setFreelancer_id(f.getId());
            
            new PublicationsService().ajouter(p);
            tf_pub_desc.setText("");
            img.setImage(null);
            
            loadE();
    }

    @FXML
    private void imageAdd()throws Exception {
          FileChooser fileChooserr = new FileChooser();
        fileChooserr.setTitle("Select Image");
        fileChooserr.setInitialDirectory(new File("C:"));
        
        
        file = fileChooserr.showOpenDialog(img.getScene().getWindow());
        Image image = new Image(file.toURI().toString());
        img.setImage(image);
    }
    
    public ObservableList<Publications> loadE() throws SQLException {
         
        PublicationsService ser = new PublicationsService();
        ResultSet ps = ser.getPublication();
        ObservableList e = FXCollections.observableArrayList();
        
            while(ps.next()) {
            e.add(new Publications(
                  
                    ps.getInt("id"),
                    ps.getString("description"),
                    ps.getString("image"),
                    ps.getString("date_publication"),
                    ps.getInt("freelancer_id"),
                    ps.getInt("societe_id")
                    
            ));
                    
        }
        list_pub.setItems(e);
        return e;
    }
    
}
