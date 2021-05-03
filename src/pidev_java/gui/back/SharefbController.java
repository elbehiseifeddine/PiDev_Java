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
         
        String ch="ftp://user:123456789@192.168.1.52/";
        String imgF= ch+image;
        
        Image imageF = new Image(imgF);
        titreimg.setText(image);
        imgPost.setImage(imageF);
        TextPostfield.setText(content);
    }

    @FXML
    private void imagepost(MouseEvent event) {
    }

    @FXML
    private void Partager(MouseEvent event) {
        String token = "EAAK9nssgZAZBEBAAYFvzgiAGuJP3ZAvYaa7kSO0ZCZATd7zlO1UnAuqNe62wItQP6ZCuCzWMTZBRvkI9T4I30gaHnvvgUaSR4pbt5p8JfxPODJbX6RZCZB5dOYpwG3bxYtXGlIkZB02Kc6XqD4AcUZCaiFysjmHnuTBWhzPN6F70JPrZCBxhiGu9SQAL9dYaPXniiXMTm4LbzYQSvIygkuULteCLYZAnUAIsSui9FIggo9nzOuiNalS2vFRNt";
FacebookClient FbClient = new DefaultFacebookClient(token);
        try {
            
           
        
       
       
  FileInputStream fi= new FileInputStream (new File("C:\\Users\\seifeddine\\Documents\\NetBeansProjects\\PiDev_Java\\src\\pidev_java\\assets\\" +titreimg.getText()));
FacebookType response= FbClient.publish("me/photos",FacebookType.class,
        BinaryAttachment.with(titreimg.getText(),fi),Parameter.with("message",TextPostfield.getText()));
        } catch (Exception e) {
            System.out.println(e);
        }
        ((Node)(event.getSource())).getScene().getWindow().hide();
    }
    
}