/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev_java.services;
import pidev_java.entities.Reponse;
import pidev_java.utils.Singleton;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.sql.DataSource;


/**
 *
 * @author Ayari Ghaith
 */
public class ReponseService {
   Connection conn;
    
    public ReponseService(){
         conn = Singleton.getConn();    }
    
    
    public void addReponse(Reponse reponse){
        
        String sql="INSERT INTO reponse (id_ques_id, contenu_rep) VALUES ("+reponse.getId_ques_id()+",'"+reponse.getContenu_rep()+"')";
            
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
    
    public void updateReponse ( Reponse reponse ){
        String sql="UPDATE reponse SET  contenu_rep='"+reponse.getContenu_rep()+"', nomb_question ="+reponse.getId_ques_id()+" WHERE id="+reponse.getId();

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
    
    public void deleteReponse (Reponse reponse){
        String sql = "DELETE FROM reponse WHERE id ='"+reponse.getId()+"'";
        Statement st ;
            try{
                st=conn.createStatement();
                st.executeUpdate(sql);
                System.out.println("Suppression reussite");
            }catch (SQLException ex){
                System.out.println("Probleme de suppression !!!");
                }
    }
    
    
    
    
    
    public Reponse getReponse( int id ) throws SQLException{
        String sql="SELECT * FROM reponse where id ="+id;
        Statement st=conn.createStatement();
        ResultSet res= st.executeQuery(sql);
        Reponse reponse = null;
        if (res.next())
        {
            String contenu_rep = res.getString("contenu_rep");
            int id_ques_id  = res.getInt("id_ques_id ");
            reponse = new Reponse (id,id_ques_id , contenu_rep);
        }
            return reponse;
    }
    
    
    public List<Reponse> getAllReponse() throws SQLException{
        
        List<Reponse> listReponse = new ArrayList();
        String sql="SELECT * FROM reponse";
        Statement st=conn.createStatement();
        ResultSet res= st.executeQuery(sql);
        while (res.next())
        {
            int id = res.getInt("id");
            String contenu_rep = res.getString("contenu_rep");
            int id_ques_id  = res.getInt("id_ques_id ");
            Reponse reponse = new Reponse (id,id_ques_id , contenu_rep);
            listReponse.add(reponse);
        }
            return listReponse;
    }
} 

