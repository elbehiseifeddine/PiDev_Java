/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev_java.services;

import pidev_java.entities.Question;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import pidev_java.entities.Quiz;
import pidev_java.utils.MaConnection;

/**
 *
 * @author Ayari Ghaith
 */
public class QuestionService {
    Connection conn;
    
    public QuestionService(){
//        conn = Singleton.getConn();
          conn = MaConnection.getInstance().getCnx();
    }
    
    
     public int addQuestionAndGetItsId(Question question) throws SQLException{
        
        String sql="INSERT INTO question ( quiz_id_id, contenu_ques, nomb_rep) "
                + "VALUES ( ?, ?,?)";
            
        String generatedColumns[] = { "ID" };
        PreparedStatement statement = conn.prepareStatement(sql, generatedColumns);
        statement.setString(2, question.getContenu_ques());
//        statement.setInt(1, question.getRep_just_id());
        statement.setInt(1, question.getQuiz_id_id());
        statement.setInt(3, question.getNomb_rep());
        statement.executeUpdate();
         try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
            if (generatedKeys.next()) {
                return generatedKeys.getInt(1);
            }
            else {
                throw new SQLException("Creating quiz failed, no ID obtained.");
            }
        }
    }
    
    public void addQuestion(Question question){
        
        String sql="INSERT INTO question (rep_just_id, quiz_id_id, contenu_ques, nomb_rep, duree) "
                + "VALUES ("+question.getRep_just_id()+","+question.getQuiz_id_id()+",'"+question.getContenu_ques()+"',"+question.getNomb_rep()+","+question.getDuree()+")";
            
        Statement st;
            try
            {
                st = conn.createStatement();
                st.executeUpdate(sql);
                System.out.println("Insertion reussite");
            }
            catch (SQLException ex)
            {
                System.out.println(ex.getMessage());
                ex.getMessage();
            }
    }
    
    public void updateQuestion ( Question question ){
        String sql="UPDATE question SET  "
                + "rep_just_id="+question.getRep_just_id()+", "
                + "quiz_id_id ="+question.getQuiz_id_id()+", "
                + "contenu_ques ='"+question.getContenu_ques()+"', "
                + "nomb_rep ="+question.getNomb_rep()
                + " WHERE id="+question.getId();

        Statement st;
            try {
                st = conn.createStatement();
                st.executeUpdate(sql);
                System.out.println("Edition reussite");
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
                ex.getMessage();
            }
    }
    
    public void deleteQuestion (Question question){
        String sql = "DELETE FROM question WHERE id ='"+question.getId()+"'";
        Statement st ;
            try{
                st=conn.createStatement();
                st.executeUpdate(sql);
                System.out.println("Suppression reussite");
            }catch (SQLException ex){
                System.out.println(ex.getMessage());
                }
    }
    
    
    
    
    
    public Question getQuestion( int id ) throws SQLException{
        String sql="SELECT * FROM question where id ="+id;
        Statement st=conn.createStatement();
        ResultSet res= st.executeQuery(sql);
        Question question = null;
        if (res.next())
        {
            String contenu_ques = res.getString("contenu_ques");
            int rep_just_id = res.getInt("rep_just_id");
            int quiz_id_id = res.getInt("quiz_id_id");
            int nomb_rep = res.getInt("nomb_rep");
            int duree = res.getInt("duree");
            question = new Question (id,rep_just_id, quiz_id_id, contenu_ques, nomb_rep, duree);
        }
            return question;
    }
    
    
    public List<Question> getAllQuestion() throws SQLException{
        
        List<Question> listQuestion = new ArrayList();
        String sql="SELECT * FROM question";
        Statement st=conn.createStatement();
        ResultSet res= st.executeQuery(sql);
        while (res.next())
        {
            String contenu_ques = res.getString("contenu_ques");
            int id = res.getInt("id");
            int rep_just_id = res.getInt("rep_just_id");
            int quiz_id_id = res.getInt("quiz_id_id");
            int nomb_rep = res.getInt("nomb_rep");
            int duree = res.getInt("duree");
            Question question = new Question (id,rep_just_id, quiz_id_id, contenu_ques, nomb_rep, duree);
            listQuestion.add(question);
        }
            return listQuestion;
    }
     public List<Question> getQuestionByQuiz(Quiz quiz) throws SQLException{
        
        List<Question> listQuestion = new ArrayList();
        String sql="SELECT * FROM question where quiz_id_id = "+quiz.getId();
        Statement st=conn.createStatement();
        ResultSet res= st.executeQuery(sql);
        while (res.next())
        {
            String contenu_ques = res.getString("contenu_ques");
            int id = res.getInt("id");
            int rep_just_id = res.getInt("rep_just_id");
            int quiz_id_id = res.getInt("quiz_id_id");
            int nomb_rep = res.getInt("nomb_rep");
            Question question = new Question (id,rep_just_id, quiz_id_id, contenu_ques, nomb_rep, 0);
            listQuestion.add(question);
        }
            return listQuestion;
    }
            
}