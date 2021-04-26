/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev_java.services;

import pidev_java.entities.offreEmploi;
import pidev_java.entities.offreStage;
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
public class stageService implements IServiceOffre<offreStage> {
     Connection cnx = MaConnection.getInstance().getCnx();

    @Override
    public void add(offreStage entity) {
       try{
        
        String sql = "insert into offre_stage (nom_projet, competences, description, domaine,fichier, duree, type_stage,date_creation, date_expiration,etat)"
                + " values (?, ?, ?, ?,?, ?, ?,?,?,?)";
        
        PreparedStatement  ps =  cnx.prepareStatement(sql);
            ps.setString(1, entity.getNomProjet());
            ps.setString(2, entity.getCompetence());
            ps.setString(3, entity.getDescription());
            ps.setString(4, entity.getDomaine());
            ps.setString(5, "fichier");
            ps.setString(6, entity.getDuree());
             ps.setString(7, entity.getTypeStage());
            ps.setDate(8,  entity.getDateCreation());
            ps.setDate(9,  entity.getDateExpiration());
            ps.setInt(10,1);
           
           
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
            String requete="DELETE FROM offre_stage where id=?";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setInt(1, id);
            pst.executeUpdate();
           //  JavaMailUtil.sendMail(getUserEmailFromReclamationId(id),"Retour de réclamation", " réclamation traité");
        } catch (SQLException ex) {
            Logger.getLogger(emploiService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    

    @Override
    public List<offreStage> getAll() {
        
        ArrayList<offreStage> res = new ArrayList<offreStage>();
        try {
            Statement stmt = cnx.createStatement();
            String sql = "SELECT * FROM offre_stage where etat=1";
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                 
    
                int id = rs.getInt("id");
                String nom = rs.getString("nom_projet");
                String comp = rs.getString("competences");
                String description = rs.getString("description");
                String domaine=rs.getString("domaine");
                String duree=rs.getString("duree");
                String type=rs.getString("type_stage");
                Date dtc=rs.getDate("date_creation");
                Date dtE=rs.getDate("date_expiration");
               
                
                offreStage F = new offreStage (id,nom,comp,description,domaine,duree,type,dtc,dtE);
                res.add(F);
            }
            rs.close();
            } 
        catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return res;    
    }

    @Override
    public void update(offreStage entity) {
        try {
            PreparedStatement preparedStmt = cnx.prepareStatement("update offre_stage set  nom_projet=? ,competences=?,description=?,domaine=?,duree=?,type_stage=?,date_expiration=?   where id=?");
	    preparedStmt.setString(1,entity.getNomProjet());
	   preparedStmt.setString(2,entity.getCompetence());
            preparedStmt.setString(3,entity.getDescription());
            preparedStmt.setString(4,entity.getDomaine());
            preparedStmt.setString(5,entity.getDuree());
            preparedStmt.setString(6,entity.getTypeStage());
            preparedStmt.setDate(7,entity.getDateExpiration());
            preparedStmt.setInt(8, entity.getId());
	   
            preparedStmt.execute();
            } catch (Exception ex) {
	    System.err.println(ex.getMessage());
            }   
    }

    @Override
    public List<offreStage> getOwn() {
         ArrayList<offreStage> res = new ArrayList<offreStage>();
        try {
            Statement stmt = cnx.createStatement();
            String sql = "SELECT * FROM offre_stage  where societe_id=9";
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                 
    
                int id = rs.getInt("id");
                String nom = rs.getString("nom_projet");
                String comp = rs.getString("competences");
                String description = rs.getString("description");
                String domaine=rs.getString("domaine");
                String duree=rs.getString("duree");
                String type=rs.getString("type_stage");
                Date dtc=rs.getDate("date_creation");
                Date dtE=rs.getDate("date_expiration");
               
                
                offreStage F = new offreStage (id,nom,comp,description,domaine,duree,type,dtc,dtE);
                res.add(F);
            }
            rs.close();
            } 
        catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return res;    
    }
    
    public int maxId(){
        int max = 0;
         try {
             Statement stmt = cnx.createStatement();
             String sql = "SELECT MAX(id) FROM offre_stage;";
             ResultSet rs6 = stmt.executeQuery(sql);
             rs6.first();
             max=rs6.getInt(1);
             rs6.close();
         } catch (SQLException ex) {
             Logger.getLogger(stageService.class.getName()).log(Level.SEVERE, null, ex);
         }
        return max;
    }
    
    public offreStage FindById(int idStage){
        offreStage F = new offreStage();
        try {
            Statement stmt = cnx.createStatement();
            String sql = "SELECT * FROM offre_stage";
            ResultSet rs11 = stmt.executeQuery(sql);
            while (rs11.next()) {
                 
    
                F.setId(rs11.getInt("id"));
                F.setNomProjet(rs11.getString("nom_projet"));
                F.setCompetence(rs11.getString("competences"));
                F.setDescription(rs11.getString("description"));
                F.setDomaine(rs11.getString("domaine"));
                F.setDuree(rs11.getString("duree"));
                F.setTypeStage(rs11.getString("type_stage"));
                F.setDateCreation(rs11.getDate("date_creation"));
                F.setDateExpiration(rs11.getDate("date_expiration"));
               
                
                
            }
            rs11.close();
            } 
        catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return F;
    }

    int countOffreStageNonApprouve() {
int count = 0;
         try {
             Statement stmt = cnx.createStatement();
             String sql = "SELECT COUNT(*) FROM offre_stage where etat =0;";
             ResultSet rs6 = stmt.executeQuery(sql);
             rs6.first();
             count=rs6.getInt(1);
             rs6.close();
         } catch (SQLException ex) {
             Logger.getLogger(stageService.class.getName()).log(Level.SEVERE, null, ex);
         }
        return count;    }
}
