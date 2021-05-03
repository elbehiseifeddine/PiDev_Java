/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev_java.gui.quiz;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import pidev_java.entities.Quiz;
import pidev_java.services.QuizService;

/**
 * FXML Controller class
 *
 * @author Ayari Ghaith
 */
public class QuizController implements Initializable {

    @FXML
    private TextField lb_nom_quiz;

    @FXML
    private TextField lb_nb_ques;

    private QuizService qs = new QuizService();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    public void creer_quiz(ActionEvent event) throws SQLException {
        
        if(lb_nom_quiz.getText().length() < 4  ){
            
        }
        else{
        
        Quiz q = new Quiz(lb_nom_quiz.getText(), Integer.parseInt(lb_nb_ques.getText()));
        int id = qs.addQuizAndGetItsId(q);
        q.setId(id);
        Node node = (Node) event.getSource();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("./question.fxml"));
        Stage stage = (Stage) node.getScene().getWindow();
        Scene scene = null;
        try {
            scene = new Scene(loader.load());
        } catch (IOException ex) {
//tabdilt e  load
        }
        QuestionController questionController = loader.getController();
        try {

            questionController.initData(q);
//            questionController.load(quiz);
        } catch (Exception ex) {

        }
        stage.setScene(scene);
                }

    }
}