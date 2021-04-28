package pidev_java.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import pidev_java.entities.List_reponses_condidat;
import pidev_java.utils.MaConnection;

/**
 *
 * @author Ayari Ghaith
 */
public class ListReponseService {
   
    Connection conn;
    
    public  ListReponseService(){
        conn = MaConnection.getInstance().getCnx();
    }
    
     public int addListAndGetItsId(List_reponses_condidat listReponse) throws SQLException{
        
        String sql="INSERT INTO list_reponses_condidat (quiz_id, candidature_id, score) "
                + "VALUES ( ?, null,?)";
            
        String generatedColumns[] = { "ID" };
        PreparedStatement statement = conn.prepareStatement(sql, generatedColumns);
        statement.setInt(1, listReponse.getQuiz_id());
        statement.setInt(2, listReponse.getScore());
//        statement.setInt(2, listReponse.getCondidtaure_id());
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
    
    public void addList_reponses_condidat(List_reponses_condidat listReponse){
        
        String sql="INSERT INTO list_reponses_condidat (quiz_id, condidature_id, score) VALUES ("+listReponse.getCondidtaure_id()+","+listReponse.getCondidtaure_id()+","+listReponse.getScore()+")";
            
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
    
    public void updateList_reponses_condidat ( List_reponses_condidat listReponse ){
        String sql;
        if(listReponse.getCondidtaure_id() != 0){
        sql="UPDATE list_reponses_condidat SET  quiz_id="+listReponse.getQuiz_id()+", condidature_id ="+listReponse.getCondidtaure_id()+", score="+listReponse.getScore()+" WHERE id="+listReponse.getId();
        }
        else
         sql="UPDATE list_reponses_condidat SET  quiz_id="+listReponse.getQuiz_id()+", score="+listReponse.getScore()+" WHERE id="+listReponse.getId();
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
    
    public void deleteList_reponses_condidat (List_reponses_condidat listReponse){
        String sql = "DELETE FROM list_reponses_condidat WHERE id ="+listReponse.getId();
        Statement st ;
            try{
                st=conn.createStatement();
                st.executeUpdate(sql);
                System.out.println("Suppression reussite");
            }catch (SQLException ex){
                System.out.println("Probleme de suppression !!!");
                }
    }
    
    
    
    
    
    public List_reponses_condidat getList_reponses_condidat( int id ) throws SQLException{
        String sql="SELECT * FROM list_reponses_condidat where id ="+id;
        Statement st=conn.createStatement();
        ResultSet res= st.executeQuery(sql);
        List_reponses_condidat listReponse = null;
        if (res.next())
        {
            int quiz_id = res.getInt("quiz_id");
            int condidature_id = res.getInt("condidature_id");
            int score = res.getInt("score");

            listReponse = new List_reponses_condidat (id,quiz_id , condidature_id, score);
        }
            return listReponse;
    }
    
    
    public List<List_reponses_condidat> getAllList_reponses_condidat() throws SQLException{
        
        List<List_reponses_condidat> List_reponses_condidat = new ArrayList();
        String sql="SELECT * FROM list_reponses_condidat";
        Statement st=conn.createStatement();
        ResultSet res= st.executeQuery(sql);
        while (res.next())
        {
            int id = res.getInt("id");
            int quiz_id = res.getInt("quiz_id");
            int condidature_id = res.getInt("condidature_id");
            int score = res.getInt("score");

            List_reponses_condidat listReponse = new List_reponses_condidat (id,quiz_id , condidature_id, score);
            List_reponses_condidat.add(listReponse);
        }
            return List_reponses_condidat;
    }
}

    

