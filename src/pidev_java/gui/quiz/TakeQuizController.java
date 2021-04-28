/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev_java.gui.quiz;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import pidev_java.entities.List_reponses_condidat;
import pidev_java.entities.Question;
import pidev_java.entities.Quiz;
import pidev_java.entities.Reponse;
import pidev_java.entities.reponse_condidat;
import pidev_java.services.ListReponseService;
import pidev_java.services.QuestionService;
import pidev_java.services.QuizService;
import pidev_java.services.ReponseCondidatService;
import pidev_java.services.ReponseService;

/**
 * FXML Controller class
 *
 * @author Ayari Ghaith
 */
public class TakeQuizController implements Initializable {

    @FXML
    private Button id_reponseCondidat;

    @FXML
    private Text lb_ques;

    @FXML
    private VBox vb;
    
    int score = 0;

    private QuizService qc = new QuizService();

    private QuestionService qs = new QuestionService();

    private ReponseService rs = new ReponseService();

    private ListReponseService lrs = new ListReponseService();

    private List_reponses_condidat lrc;

    Quiz quiz=new Quiz(15, "", 0);
    List<Question> listQ = new ArrayList();
    List<Reponse> ListR;

    int nb = 0;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            // TODO
            lrc = new List_reponses_condidat(15, 0, 0);
            listQ = qs.getQuestionByQuiz(new Quiz(15, "", 0));
            int id = lrs.addListAndGetItsId(lrc);
            lrc.setId(id);
            
            System.out.println(listQ.size());
            lb_ques.setText(listQ.get(0).getContenu_ques());
            ListR = rs.getReponseByQuestion(listQ.get(0));
            for (int i = 0; i < listQ.get(0).getNomb_rep(); i++) {
                RadioButton b = new RadioButton(ListR.get(i).getContenu_rep());
                b.setOnAction(e -> {
                    for (Node node : vb.getChildren()) {
                        ((RadioButton) node).setSelected(false);

                    }
                    ((RadioButton) e.getTarget()).setSelected(true);

                });
                vb.getChildren().add(b);
            }
        } catch (SQLException ex) {
            Logger.getLogger(TakeQuizController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
    public void load(Quiz quiz){
        this.quiz = quiz;
    }

    @FXML
    private void afficherReponse(ActionEvent event) throws SQLException {
        if (true) {
            
            Reponse r = ListR.get(getSelectedIndex());
            reponse_condidat rc = null;
            if(nb<listQ.size()){
                rc = new reponse_condidat(listQ.get(nb).getId(), r.getId(), lrc.getId());
            new ReponseCondidatService().addList_reponses_condidat(rc);}
            if(rc.getReponse_id() == listQ.get(nb).getRep_just_id()){
                score++;
            }
            nb++;
            if (nb < listQ.size()) {
                vb.getChildren().clear();
                listQ = qs.getQuestionByQuiz(quiz);
                System.out.println(listQ.size());
                lb_ques.setText(listQ.get(nb).getContenu_ques());
                ListR = rs.getReponseByQuestion(listQ.get(nb));
                for (int i = 0; i < listQ.get(0).getNomb_rep(); i++) {
                    RadioButton b = new RadioButton(ListR.get(i).getContenu_rep());
                    b.setOnAction(e -> {
                        for (Node node : vb.getChildren()) {
                            ((RadioButton) node).setSelected(false);

                        }
                        ((RadioButton) e.getTarget()).setSelected(true);

                    });
                    vb.getChildren().add(b);
                }
            } else {
                lrc.setScore(score);
                lrs.updateList_reponses_condidat(lrc);
                System.exit(0);
            }

        } else {
            try {
                FXMLLoader loader1 = new FXMLLoader();
                loader1.setLocation(getClass().getResource("/pidev_java/gui/quiz/afficherResult.fxml"));

                Parent parent = (Parent) loader1.load();
                Stage stage = new Stage();
                stage.setScene(new Scene(parent));
                stage.show();

            } catch (IOException ex) {
                System.out.println("erreur");

//                ReclamationController controller = loader.getController();
//                controller.SetIdOffreEmploi(idEmploi.gettext);
            }
        }
    }

    public int getSelectedIndex() {
        for (int i = 0; i < vb.getChildren().size(); i++) {
            if (((RadioButton) vb.getChildren().get(i)).isSelected()) {
                return i;
            }
        }
        return -1;
    }

}
