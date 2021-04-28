/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev_java.services;

import pidev_java.entities.Quiz;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import pidev_java.utils.MaConnection;

/**
 *
 * @author Ayari Ghaith
 */
public class QuizService {
    Connection conn;
    
    public QuizService(){
        conn = MaConnection.getInstance().getCnx();
    }
    
        public int addQuizAndGetItsId(Quiz quiz) throws SQLException{
        
        String sql="INSERT INTO quiz (nom_quiz, nomb_question) VALUES (? ,?)";
        String generatedColumns[] = { "ID" };
        PreparedStatement statement = conn.prepareStatement(sql, generatedColumns);
        statement.setString(1, quiz.getNom_quiz());
        statement.setInt(2, quiz.getNomb_question());
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
    
    
    public void addQuiz(Quiz quiz){
        
        String sql="INSERT INTO quiz (nom_quiz, nomb_question) VALUES ('"+quiz.getNom_quiz()+"',"+quiz.getNomb_question()+")";
            
        Statement st;
            try
            {
                st = conn.createStatement();
                st.executeUpdate(sql);
                System.out.println("Insertion reussite");
            }
            catch (SQLException ex)
            {
                System.out.println("Erreur d'insertion !");
                ex.getMessage();
            }
    }
    
    public void updateQuiz ( Quiz quiz ){
        String sql="UPDATE quiz SET  nom_quiz='"+quiz.getNom_quiz()+"', nomb_question ="+quiz.getNomb_question()+" WHERE id="+quiz.getId();

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
    
    public void deleteQuiz (Quiz quiz){
        String sql = "DELETE FROM quiz WHERE id ='"+quiz.getId()+"'";
        Statement st ;
            try{
                st=conn.createStatement();
                st.executeUpdate(sql);
                System.out.println("Suppression reussite");
            }catch (SQLException ex){
                System.out.println("Probleme de suppression !!!");
                }
    }}
