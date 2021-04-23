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
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import pidev_java.services.CommentairesService;
import pidev_java.services.PublicationsService;

/**
 * FXML Controller class
 *
 * @author ksemt
 */
public class ComEditController implements Initializable {

    @FXML
    private Label lb_com_util;
    @FXML
    private Label lb_com_date;
    @FXML
    private TextField tf_com_desc;
    @FXML
    private Button btn_edit_conf;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    public void comEdit(int id_util, String desc, String date ){
        lb_com_util.setText(""+id_util);
        tf_com_desc.setText(desc);
        lb_com_date.setText(date);
        
        btn_edit_conf.setOnAction(e->{
            
            CommentairesService ser = new CommentairesService();
            try {
                ser.update(id_util,tf_com_desc.getText());
            } catch (SQLException ex) {
                Logger.getLogger(PublicationsController.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            
            Stage stage = new Stage();
        stage = (Stage) btn_edit_conf.getScene().getWindow();
        stage.close();
            
        });
    }
    
}
