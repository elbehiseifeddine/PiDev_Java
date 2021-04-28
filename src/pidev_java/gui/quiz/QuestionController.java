/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev_java.gui.quiz;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import static java.util.Collections.list;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import pidev_java.entities.Question;
import pidev_java.entities.Quiz;
import pidev_java.services.QuestionService;
import pidev_java.services.ReponseService;

/**
 * FXML Controller class
 *
 * @author Ayari Ghaith
 */
public class QuestionController implements Initializable {

    @FXML
    private TextField tf_question;
    @FXML
    private TextField tf_nb_rep;
    private Quiz quiz;

    private QuestionService qs = new QuestionService();
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    public void initData(Quiz quiz){
        this.quiz = quiz;
        System.out.println(quiz.getId());
    }
    
    @FXML
    private void next(ActionEvent event) throws SQLException {
        
        Question ques = new Question(0, quiz.getId(), tf_question.getText(), Integer.parseInt(tf_nb_rep.getText()), 0);
        
       int id = qs.addQuestionAndGetItsId(ques);
       ques.setId(id);
       
       Node node = (Node) event.getSource();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("./reponse.fxml"));
        Stage stage = (Stage) node.getScene().getWindow();
        Scene scene = null;
        try {
            scene = new Scene(loader.load());
        } catch (IOException ex) {

        }
        ReponseController reponsenController = loader.getController();
        try {

            reponsenController.initData(ques,quiz);
        } catch (Exception ex) {

        }
        stage.setScene(scene);
            
    }
    
}
