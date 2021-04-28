/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev_java.gui.back;

import com.restfb.BinaryAttachment;
import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.Parameter;
import com.restfb.types.FacebookType;
import java.io.File;
import java.io.FileInputStream;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class SharefbController implements Initializable {

    @FXML
    private ImageView imgProfile;
    @FXML
    private Label username;
    @FXML
    private ImageView imgVerified;
    @FXML
    private Label date;
    @FXML
    private ImageView audience;
    @FXML
    private Label titreimg;
    @FXML
    private HBox TextPost;
    @FXML
    private TextArea TextPostfield;
    @FXML
    private ImageView imgPost;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }  
    
    public void setData(String content,String image){
         
        String ch="/pidev_java/assets/";
        String imgF= ch+image;
        
        Image imageF = new Image(getClass().getResourceAsStream(imgF));
        imgPost.setImage(imageF);
        TextPostfield.setText(content);
    }

    @FXML
    private void imagepost(MouseEvent event) {
    }

    @FXML
    private void Partager(MouseEvent event) {
        String token = "EAAK9nssgZAZBEBAJPMAsCSS4KZAZAQ9CpmXryAzR8oXACMaWk0vZAHON85lZCpcm5c3GwxpnMZAFIdu67GZC8aodGZAxFhtOJNMh4pfrmKwDVwmKUp7FYMJ61P6OvwnjWwgrM5evZBgrZBMzV2hxRV3Yaf50WhuQBgpsvyjzPnHFZAqmSLvHbPnnZB1hzmCxeerop2TuAPBZCBZB2qZBARXJN4WSeIPf";
FacebookClient FbClient = new DefaultFacebookClient(token);
        try {
  FileInputStream fi= new FileInputStream (new File("C:\\Users\\user\\Desktop\\pi dev\\CrudPI\\src\\imgEvent\\"+titreimg.getText()));
FacebookType response= FbClient.publish("me/photos",FacebookType.class,
        BinaryAttachment.with(titreimg.getText(),fi),Parameter.with("message",TextPostfield.getText()));
        } catch (Exception e) {
            System.out.println(e);
        }
        ((Node)(event.getSource())).getScene().getWindow().hide();
    }
    
}
