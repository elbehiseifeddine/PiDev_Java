/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev_java.gui.publication;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import pidev_java.entities.Publications;
import pidev_java.services.PublicationsService;

/**
 * FXML Controller class
 *
 * @author ksemt
 */
public class PubEditController implements Initializable {

    @FXML
    private Label lb_edit_id;
    @FXML
    private Label lb_edit_date;
    @FXML
    private ImageView img_edit;
    @FXML
    private TextField tf_desc_edit;
    @FXML
    private Button btn_edit_conf;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void ModifierPublication(ActionEvent event) {
    }
    
    public void pubEdit(int id_pub, String desc, String date , String img){
        lb_edit_id.setText(""+id_pub);
        tf_desc_edit.setText(desc);
        img_edit.setImage(new Image( "ftp://user:123456789@192.168.1.52/"+img));
        lb_edit_date.setText(date);
        
        btn_edit_conf.setOnAction(e->{
            
            PublicationsService ser = new PublicationsService();
            try {
                ser.update(id_pub,tf_desc_edit.getText());
            } catch (SQLException ex) {
                Logger.getLogger(PublicationsController.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            
            Stage stage = new Stage();
        stage = (Stage) btn_edit_conf.getScene().getWindow();
        stage.close();
            
        });
    }
}
