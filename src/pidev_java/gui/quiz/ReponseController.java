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
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import pidev_java.entities.Question;
import pidev_java.entities.Quiz;
import pidev_java.entities.Reponse;
import pidev_java.services.QuestionService;
import pidev_java.services.ReponseService;

/**
 * FXML Controller class
 *
 * @author Ayari Ghaith
 */
public class ReponseController implements Initializable {

    @FXML
    private VBox vbox;
    @FXML
    private ComboBox<String> cb;
    
    private Quiz quiz;
    
    private Question ques;
    
    private ReponseService rs = new ReponseService();
    
    private QuestionService qs = new QuestionService();

    /**
     * Initializes the controller class.
     */
    
    
    public void initData(Question ques, Quiz quiz){
        this.quiz = quiz;
        this.ques = ques;
        for(int i = 0; i< ques.getNomb_rep(); i++){
            vbox.getChildren().add(new TextField());
            cb.getItems().add(String.valueOf(i+1));
        }
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void next(ActionEvent event) throws SQLException {
        
        for(int i =0; i < vbox.getChildren().size(); i++){
            Node node = vbox.getChildren().get(i);
            Reponse rp = new Reponse(ques.getId(), ((TextField)node).getText());
            int id = rs.addReponseAndGetItsId(rp);
            if( i+1 == Integer.parseInt(cb.getValue())){
                ques.setRep_just_id(id);
                qs.updateQuestion(ques);
            }
            
            
        }
        quiz.setNomb_question(quiz.getNomb_question()-1);
        if(quiz.getNomb_question() == 0){
            System.exit(0);
        }
        else{
            Node node = (Node) event.getSource();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("./question.fxml"));
        Stage stage = (Stage) node.getScene().getWindow();
        Scene scene = null;
        try {
            scene = new Scene(loader.load());
        } catch (IOException ex) {

        }
        QuestionController questionController = loader.getController();
        try {

            questionController.initData(quiz);
        } catch (Exception ex) {

        }
        stage.setScene(scene);
        }
    }
    
}