/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev_java.services;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import pidev_java.entities.DemandeStage;
import pidev_java.interfaces.IServiceDemande;

import pidev_java.utils.MaConnection;

/**
 *
 * @author ely
 */
public class DemandeStageService implements IServiceDemande<DemandeStage> {
Connection cnx = MaConnection.getInstance().getCnx();
 private static DemandeStageService instance;
    public static DemandeStageService getInstance() {
        if(instance==null) 
            instance=new DemandeStageService();
        return instance;
    }

    
    @Override
    public void ajouter(DemandeStage t) {
        try {
            /* Date date1 = Date.from(Instant.MIN);*/
           

LocalDate locald = LocalDate.now();
Date date = Date.valueOf(locald);
            String requete = "INSERT INTO demande_stage (type,duree,etude,description,date_creation,domaine,freelancer_id,lettre,offre_stage_id) VALUES (?,?,?,?,?,?,?,?,?)";
            PreparedStatement pst = cnx.prepareStatement(requete);
            LocalDate date1 = LocalDate.now();
            pst.setString(1,t.getType());
           
             pst.setInt(2,t.getDuree()); 
             pst.setString(3,t.getEtude());
             pst.setString(4, t.getDescription());
              pst.setDate(5,date);
             pst.setString(6,t.getDomaine());
             pst.setInt(7,t.getFreelancer_id());
             pst.setString(8, t.getLettre());
           pst.setInt(9, t.getOffre_stage_id());
           
            pst.executeUpdate();
            System.out.println("Demande ajoutée !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    @Override
    public void supprimer( int id) {
        try {
            String requete = "DELETE FROM demande_stage WHERE id=?";
            PreparedStatement pst = cnx.prepareStatement(requete);
            
            pst.setInt(1, id);
            pst.executeUpdate();
            System.out.println(" Demande supprimée !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    @Override
    public void modifier(DemandeStage t) {
           try {
               
            String requete = "UPDATE demande_emploi SET etude=?,description=?,domaine=?,type=?,duree=?,lettre=? WHERE id=?";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setInt(7, t.getId());
            pst.setString(1, t.getEtude());
            
            pst.setString(2, t.getDescription());
            /*pst.setDate(2, t.convertUtilToSql(date1));*/
            pst.setString(3, t.getDomaine());
            pst.setString(4, t.getType());
            pst.setInt(5, t.getDuree());
            pst.setString(6, t.getLettre());
           
            
            pst.executeUpdate();
            System.out.println("Demande modifiée !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    @Override
    public List<DemandeStage> afficher() {
         List<DemandeStage> Dlist = new ArrayList<>();
      try {
             DemandeStage D = new DemandeStage();
            String requete = "SELECT * FROM `demande_stage`";
            PreparedStatement pst = cnx.prepareStatement(requete);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
              
                 Date d1=rs.getDate("date_creation");
                    
                      
                      D.setId(rs.getInt("id"));
                      D.setFreelancer_id(rs.getInt("freelancer_id"));
                      
                       D.setDate_creation(d1);
                       
                       D.setDescription(rs.getString("description"));
                       D.setDomaine(rs.getString("domaine"));
                       D.setType(rs.getString("type"));
                        D.setEtude(rs.getString("etude"));
                        D.setLettre(rs.getString("lettre"));
                       D.setDuree(rs.getInt("duree"));
                       D.setNom_societe(rs.getString("nomsociete"));
                Dlist.add(D);
            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

        return Dlist;
     
         

    }
    public void app(int id) throws SQLException{
           try {
               
            String requete = "UPDATE demande_stage SET etat=? WHERE id=?";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setInt(2, id);
            pst.setInt(1, 1);
          
           
            
            pst.executeUpdate();
            System.out.println("Demande approuver !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
    
     public void dapp(int id) throws SQLException{
           try {
               
            String requete = "UPDATE demande_stage SET etat=? WHERE id=?";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setInt(2, id);
            pst.setInt(1, -1);
          
           
            
            pst.executeUpdate();
            System.out.println("Demande approuver !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
    
}
