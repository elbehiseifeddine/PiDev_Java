/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev_java.services;

import pidev_java.entities.offreEmploi;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import pidev_java.interfaces.IServiceOffre;
import pidev_java.utils.MaConnection;

/**
 *
 * @author Ghassen Riahi
 */
public class emploiService implements IServiceOffre<offreEmploi>{
    Connection cnx = MaConnection.getInstance().getCnx();

    @Override
    public void add(offreEmploi entity) {
        try{
        
        String sql = "insert into offre_emploi (nom_projet, competences, description, domaine,fichier, salaire, date_creation, date_expiration)"
                + " values (?, ?, ?, ?,?, ?, ?,?)";
        
        PreparedStatement  ps =  cnx.prepareStatement(sql);
            ps.setString(1, entity.getNomProjet());
            ps.setString(2, entity.getCompetence());
            ps.setString(3, entity.getDescription());
            ps.setString(4, entity.getDomaine());
            ps.setString(5, "fichier");
            ps.setString(6, entity.getSalaire());
            ps.setDate(7,  entity.getDateCreation());
            ps.setDate(8,  entity.getDateExpiration());
           
           
            ps.executeUpdate();
            System.out.println("offre ajouté avec succés!");
        } catch (SQLException ex) {
           // System.out.println(ex.getMessage());
            Logger.getLogger(emploiService.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        }

    @Override
    public void delete(int id) {
        try {
            String requete="DELETE FROM offre_emploi where id=?";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setInt(1, id);
            pst.executeUpdate();
           //  JavaMailUtil.sendMail(getUserEmailFromReclamationId(id),"Retour de réclamation", " réclamation traité");
        } catch (SQLException ex) {
            Logger.getLogger(emploiService.class.getName()).log(Level.SEVERE, null, ex);
        }
       
    }

    

    @Override
    public List<offreEmploi> getAll() {
ArrayList<offreEmploi> res = new ArrayList<offreEmploi>();
        try {
            Statement stmt = cnx.createStatement();
            String sql = "SELECT * FROM offre_emploi";
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                 
    
                int id = rs.getInt("id");
                String nom = rs.getString("nom_projet");
                String comp = rs.getString("competences");
                String description = rs.getString("description");
                String domaine=rs.getString("domaine");
                String salaire=rs.getString("salaire");
                Date dtc=rs.getDate("date_creation");
                Date dtE=rs.getDate("date_expiration");
               
                
                offreEmploi F = new offreEmploi (id,nom,comp,description,domaine,salaire,dtc,dtE);
                res.add(F);
            }
            rs.close();
            } 
        catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return res;    }

    @Override
    public void update(offreEmploi entity) {
         try {
            PreparedStatement preparedStmt = cnx.prepareStatement("update offre_emploi set  nom_projet=? ,competences=?,description=?,domaine=?,salaire=?,date_expiration=?   where id=?");
	    preparedStmt.setString(1,entity.getNomProjet());
	   preparedStmt.setString(2,entity.getCompetence());
            preparedStmt.setString(3,entity.getDescription());
            preparedStmt.setString(4,entity.getDomaine());
            preparedStmt.setString(5,entity.getSalaire());
            preparedStmt.setDate(6,entity.getDateExpiration());
	   
            preparedStmt.execute();
            } catch (Exception ex) {
	    System.err.println(ex.getMessage());
            }   
    }

    @Override
    public List<offreEmploi> getOwn() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

  
    
    

   
    
}
