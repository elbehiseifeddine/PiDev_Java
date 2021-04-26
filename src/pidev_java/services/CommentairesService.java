/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev_java.services;

import pidev_java.utils.MaConnection;
import pidev_java.entities.Publications;
import pidev_java.entities.Commentaires;
import pidev_java.interfaces.CommentaireService;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ksemt
 */
public class CommentairesService implements CommentaireService<Commentaires> {
    private Connection cnx;
    private Statement st;
    private PreparedStatement pst;
    private ResultSet rs;
    
    public CommentairesService() {
       cnx = MaConnection.getInstance().getCnx();
    }
    
    @Override
    public void ajouter(Commentaires c) {
    String req="insert into commentaires (description,id_pub_id,id_util_id) values ('"+c.getDescription()+"','"+c.getId_pub()+"','"+c.getFreelancer_id()+"')";
        try {
            st = cnx.createStatement();
            st.executeUpdate(req);
        } 
        catch (SQLException ex) {
            Logger.getLogger(CommentairesService.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    @Override
    public ArrayList<Commentaires> getAll() {
        ArrayList<Commentaires> coms = new ArrayList<>();
    String req = "SELECT * FROM commentaires";
    
    try{
         st=cnx.createStatement();
         rs = st.executeQuery(req);
         while (rs.next()){
          Commentaires c = new Commentaires();
          c.setId(rs.getInt("id"));
          c.setDescription(rs.getString("description"));
          c.setId_pub(rs.getInt("id_pub"));
          c.setDate_commentaire(rs.getString("date_commentaire"));
          c.setFreelancer_id(rs.getInt("freelancer_id"));
          c.setSociete_id(rs.getInt("societe_id"));
         coms.add(c);
         }
    
    }
    catch (SQLException ex){
        Logger.getLogger(CommentairesService.class.getName()).log(Level.SEVERE, null, ex);
    }   
    
    
    return coms ;
    }
    
    public ResultSet getCommentaires(int id_pub) throws SQLException{
        st = cnx.createStatement();
        ResultSet ps = st.executeQuery(
                "SELECT c.id,c.description,c.date_com,c.id_pub_id,c.id_util_id,c.societe_id,f.nom nom,f.prenom la9ab FROM commentaires c INNER JOIN freelancer f ON c.id_util_id=f.id where c.id_pub_id='" + id_pub + "' ;");
       
        return ps;
    }
    
    public ResultSet getComs() throws SQLException{
        st = cnx.createStatement();
        ResultSet ps = st.executeQuery(
                "SELECT c.id,c.description,c.date_com,c.id_pub_id,c.id_util_id,c.societe_id,f.nom nom,f.prenom prenom FROM commentaires c INNER JOIN freelancer f ON c.id_util_id=f.id ");
       
        return ps;
    }
    
    public void delete(int id) throws SQLException {
        st = cnx.createStatement();
        String requeteDelete = "DELETE FROM `Commentaires` WHERE `id` ='" + id+ "';";
        st.executeUpdate(requeteDelete);
    }
    
    public void supprimer(Commentaires c) throws SQLException {
        st = cnx.createStatement();
        String requeteDelete = "DELETE FROM `commentaires` WHERE `id` ='" + c.getId()+ "';";
        st.executeUpdate(requeteDelete);
    }
    
    public void update(int id,String desc) throws SQLException {
        st = cnx.createStatement();
        String requeteUpdate = "UPDATE `Commentaires` SET `description` = '" + desc + "' WHERE `commentaires`.`id` = '" + id + "' ;";
        st.executeUpdate(requeteUpdate);
    }
    
}
