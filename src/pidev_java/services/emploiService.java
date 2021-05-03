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
        
        String sql = "insert into offre_emploi (nom_projet, competences, description, domaine,fichier, salaire,devise, date_creation, date_expiration,etat,societe_id,id)"
                + " values (?,?, ?, ?, ?,?, ?, ?,?,?,?,?)";
        
        PreparedStatement  ps =  cnx.prepareStatement(sql);
            ps.setString(1, entity.getNomProjet());
            ps.setString(2, entity.getCompetence());
            ps.setString(3, entity.getDescription());
            ps.setString(4, entity.getDomaine());
            ps.setString(5, "fichier");
            ps.setFloat(6, entity.getSalaire());
            ps.setString(7, entity.getDevise());
            ps.setDate(8,  entity.getDateCreation());
            ps.setDate(9,  entity.getDateExpiration());
            ps.setInt(10,0);
            ps.setInt(11,entity.getIdSociete());
            ps.setInt(12,entity.getId());
            
           
           
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
            String sql = "SELECT * FROM offre_emploi where etat=1";
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                 int idSoc = rs.getInt("societe_id");
    
                int id = rs.getInt("id");
                String nom = rs.getString("nom_projet");
                String comp = rs.getString("competences");
                String description = rs.getString("description");
                String domaine=rs.getString("domaine");
                Float salaire=rs.getFloat("salaire");
                Date dtc=rs.getDate("date_creation");
                Date dtE=rs.getDate("date_expiration");
                String devise=rs.getString("devise");
               
                
                offreEmploi F = new offreEmploi (id,nom,comp,description,domaine,salaire,dtc,dtE,devise);
                F.setIdSociete(idSoc);
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
            PreparedStatement preparedStmt = cnx.prepareStatement("update offre_emploi set  nom_projet=? ,competences=?,description=?,domaine=?,salaire=?,devise=?,date_expiration=?  where id=?");
	    preparedStmt.setString(1,entity.getNomProjet());
	    preparedStmt.setString(2,entity.getCompetence());
            preparedStmt.setString(3,entity.getDescription());
            preparedStmt.setString(4,entity.getDomaine());
            preparedStmt.setFloat(5,entity.getSalaire());
            preparedStmt.setString(6,entity.getDevise());
            preparedStmt.setDate(7,entity.getDateExpiration());
            // preparedStmt.setInt(7, 0);
            preparedStmt.setInt(8, entity.getId());
	   
            preparedStmt.execute();
            } catch (Exception ex) {
	    System.err.println(ex.getMessage());
            }   
    }

    @Override
    public List<offreEmploi> getOwn(int ids) {
       ArrayList<offreEmploi> res = new ArrayList<offreEmploi>();
        try {
            
            Statement stmt = cnx.createStatement();
            String sql = "SELECT * FROM offre_emploi where societe_id="+ids;
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                 int idSoc = rs.getInt("societe_id");
    
                int id = rs.getInt("id");
                String nom = rs.getString("nom_projet");
                String comp = rs.getString("competences");
                String description = rs.getString("description");
                String domaine=rs.getString("domaine");
                Float salaire=rs.getFloat("salaire");
                Date dtc=rs.getDate("date_creation");
                Date dtE=rs.getDate("date_expiration");
                String devise=rs.getString("devise");
               
                
                offreEmploi F = new offreEmploi (id,nom,comp,description,domaine,salaire,dtc,dtE,devise);
                F.setIdSociete(idSoc);
                res.add(F);
            }
            rs.close();
            } 
        catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return res;   
    }

  
    public List<offreEmploi> Trie(String ord){
        List<offreEmploi> listC = new ArrayList();
        
        try{
             Statement stmt = cnx.createStatement();
        String sql="select * from offre_emploi order by salaire "+ord;
        ResultSet rs = stmt.executeQuery(sql);
         
            while (rs.next()) {
                 
    
                int id = rs.getInt("id");
                String nom = rs.getString("nom_projet");
                String comp = rs.getString("competences");
                String description = rs.getString("description");
                String domaine=rs.getString("domaine");
                Float salaire=rs.getFloat("salaire");
                Date dtc=rs.getDate("date_creation");
                Date dtE=rs.getDate("date_expiration");
                String devise=rs.getString("devise");
               
                
                offreEmploi F = new offreEmploi (id,nom,comp,description,domaine,salaire,dtc,dtE,devise);
                listC.add(F);
            }
            rs.close();
            } catch(Exception ex){
                System.out.println(ex.getMessage());
            
            }
        return listC;
    }
    
    
    
    public int maxId(){
        int max = 0;
         try {
             Statement stmt = cnx.createStatement();
             String sql = "SELECT MAX(id) FROM offre_emploi;";
             ResultSet rs6 = stmt.executeQuery(sql);
             rs6.first();
             max=rs6.getInt(1);
             rs6.close();
         } catch (SQLException ex) {
             Logger.getLogger(stageService.class.getName()).log(Level.SEVERE, null, ex);
         }
        return max;
    }

    int countOffreEmploiNonApprouve() {
int count = 0;
         try {
             Statement stmt = cnx.createStatement();
             String sql = "SELECT COUNT(*) FROM offre_emploi where etat = 0;";
             ResultSet rs6 = stmt.executeQuery(sql);
             rs6.first();
             count=rs6.getInt(1);
             rs6.close();
         } catch (SQLException ex) {
             Logger.getLogger(stageService.class.getName()).log(Level.SEVERE, null, ex);
         }
        return count;    
    }
    public offreEmploi FindById(int idOffre){
      offreEmploi F = new offreEmploi();
      try {
            Statement stmt = cnx.createStatement();
            String sql = "SELECT * FROM offre_emploi where id ="+idOffre;
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                 
    
                int id = rs.getInt("id");
                F.setNomProjet(rs.getString("nom_projet"));
                F.setCompetence(rs.getString("competences"));
                F.setDescription(rs.getString("description"));
                F.setDomaine(rs.getString("domaine"));
                F.setSalaire(rs.getFloat("salaire"));
                F.setDateCreation(rs.getDate("date_creation"));
                F.setDateExpiration(rs.getDate("date_expiration"));
                F.setDevise(rs.getString("devise"));
               
                
                
                
            }
            rs.close();
            } 
        catch (Exception e) {
            System.err.println(e.getMessage());
        }
      return F;
  }
}

   
    

