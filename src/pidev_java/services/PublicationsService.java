/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev_java.services;

import pidev_java.utils.MaConnection;
import pidev_java.entities.Publications;
import pidev_java.interfaces.PublicationService;
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
public class PublicationsService implements PublicationService<Publications> {
    private Connection cnx;
    private Statement st;
    private PreparedStatement pst;
    private ResultSet rs;
    
    public PublicationsService() {
       cnx = MaConnection.getInstance().getCnx();
        
    }
    
    @Override
    public void ajouter(Publications p) {
    String req="insert into publications (description,image,freelancer_id) values ('"+p.getDescription()+"','"+p.getImage()+"','"+p.getFreelancer_id()+"')";
        try {
            st = cnx.createStatement();
            st.executeUpdate(req);
        } 
        catch (SQLException ex) {
            Logger.getLogger(PublicationsService.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    @Override
    public ArrayList<Publications> getAll() {
        ArrayList<Publications> pubs = new ArrayList<>();
    String req = "SELECT * FROM publications";
    
    try{
         st=cnx.createStatement();
         rs = st.executeQuery(req);
         while (rs.next()){
          Publications p = new Publications();
          p.setId(rs.getInt("id"));
          p.setDescription(rs.getString("description"));
          p.setImage(rs.getString("image"));
          p.setDate_publication(rs.getString("date_publication"));
          p.setFreelancer_id(rs.getInt("freelancer_id"));
          p.setSociete_id(rs.getInt("societe_id"));
         pubs.add(p);
         }
    
    }
    catch (SQLException ex){
        Logger.getLogger(PublicationsService.class.getName()).log(Level.SEVERE, null, ex);
    }   
    
    
    return pubs ;
    }
    public ResultSet getPublication() throws SQLException{
        st = cnx.createStatement();
        ResultSet ps = st.executeQuery(
                "SELECT id,description,image,date_publication,freelancer_id,societe_id FROM publications");
       
        return ps;
    }
    
    public void delete(int id_pub) throws SQLException {
        st = cnx.createStatement();
        String requeteDelete = "DELETE FROM `Publications` WHERE `id` ='" + id_pub+ "';";
        st.executeUpdate(requeteDelete);
    }
    
    public void update(int id_pub,String desc) throws SQLException {
        st = cnx.createStatement();
        String requeteUpdate = "UPDATE `publications` SET `description` = '" + desc + "' WHERE `publications`.`id` = '" + id_pub + "' ;";
        st.executeUpdate(requeteUpdate);
    }
    
}
