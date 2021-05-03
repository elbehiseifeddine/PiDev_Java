/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev_java.services;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import pidev_java.entities.reponse_condidat;
import pidev_java.utils.MaConnection;

/**
 *
 * @author Ayari Ghaith
 */
public class ReponseCondidatService {
    
Connection conn;
    
    public  ReponseCondidatService(){
        conn = MaConnection.getInstance().getCnx();
    }
    
    public void addList_reponses_condidat(reponse_condidat reponse){
        
        String sql="INSERT INTO reponse_condidat (question_id, 	reponse_id, list_reponses_condidat_id) VALUES ("+reponse.getQuestion_id()+","+reponse.getReponse_id()+","+reponse.getList_reponses_condidat_id()+")";
            
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
    
    public void updateList_reponses_condidat ( reponse_condidat reponse ){
        String sql="UPDATE reponse_condidat SET  question_id="+reponse.getQuestion_id()+", reponse_id ="+reponse.getReponse_id()+", list_reponses_condidat_id="+reponse.getList_reponses_condidat_id()+" WHERE id="+reponse.getId();

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
    
    public void deleteList_reponses_condidat (reponse_condidat reponse){
        String sql = "DELETE FROM reponse_condidat WHERE id ="+reponse.getId();
        Statement st ;
            try{
                st=conn.createStatement();
                st.executeUpdate(sql);
                System.out.println("Suppression reussite");
            }catch (SQLException ex){
                System.out.println("Probleme de suppression !!!");
                }
    }
    
    
    
    
    
    public reponse_condidat getList_reponses_condidat( int id ) throws SQLException{
        String sql="SELECT * FROM reponse_condidat where id ="+id;
        Statement st=conn.createStatement();
        ResultSet res= st.executeQuery(sql);
        reponse_condidat listReponse = null;
        if (res.next())
        {
            int question_id = res.getInt("question_id");
            int reponse_id = res.getInt("reponse_id");
            int list_reponses_condidat_id = res.getInt("list_reponses_condidat_id");

            listReponse = new reponse_condidat (id,question_id , reponse_id, list_reponses_condidat_id);
        }
            return listReponse;
    }
    
    
    public List<reponse_condidat> getAllList_reponses_condidat() throws SQLException{
        
        List<reponse_condidat> reponsesCondidat = new ArrayList();
        String sql="SELECT * FROM reponse_condidat";
        Statement st=conn.createStatement();
        ResultSet res= st.executeQuery(sql);
        while (res.next())
        {
            int id = res.getInt("id");
            int question_id = res.getInt("question_id");
            int reponse_id = res.getInt("reponse_id");
            int list_reponses_condidat_id = res.getInt("list_reponses_condidat_id");

            reponse_condidat reponse = new reponse_condidat (id,question_id , reponse_id, list_reponses_condidat_id);
            reponsesCondidat.add(reponse);
        }
            return reponsesCondidat;
    }
}