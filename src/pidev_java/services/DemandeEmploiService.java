/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev_java.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import pidev_java.entities.DemandeEmploi;
import pidev_java.utils.MaConnection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import static java.util.Collections.list;

import java.util.List;
import pidev_java.interfaces.IServiceDemande;

import static com.oracle.nio.BufferSecrets.instance;
import java.sql.Date;
import static java.time.LocalDate.now;
import static jdk.nashorn.internal.objects.Global.instance;


/**
 *
 * @author ely
 */
public class DemandeEmploiService implements IServiceDemande <DemandeEmploi>  {
 private static DemandeEmploiService instance;
    public static DemandeEmploiService getInstance() {
        if(instance==null) 
            instance=new DemandeEmploiService();
        return instance;
    }

    Connection cnx = MaConnection.getInstance().getCnx();
    @Override
    public void ajouter(DemandeEmploi t) {
        try {
            /* Date date1 = Date.from(Instant.MIN);*/
           

LocalDate locald = LocalDate.now();
Date date = Date.valueOf(locald);
            String requete = "INSERT INTO demande_emploi (description,date_creation,domaine,salaire,diplome,nomsociete,freelancer_id,lettre) VALUES (?,?,?,?,?,?,?,?)";
            PreparedStatement pst = cnx.prepareStatement(requete);
            LocalDate date1 = LocalDate.now();
            pst.setString(1,t.getDescription());
            pst.setDate(2,date);
             pst.setString(3,t.getDomaine()); 
             pst.setDouble(4,t.getSalaire());
             pst.setString(5, t.getDiplome());
             pst.setString(6,t.getNom_societe());
             pst.setInt(7,t.getFreelancer_id());
             pst.setString(8, t.getLettre());
           
           
            pst.executeUpdate();
            System.out.println("Demande ajoutée !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    @Override
    public void supprimer( int id) {
        try {
            String requete = "DELETE FROM demande_emploi WHERE id=?";
            PreparedStatement pst = cnx.prepareStatement(requete);
            
            pst.setInt(1, id);
            pst.executeUpdate();
            System.out.println(" Demande supprimée !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    @Override
    public void modifier(DemandeEmploi t) {
           try {
               
            String requete = "UPDATE demande_emploi SET description=?,domaine=?,salaire=?,diplome=?,lettre=? WHERE id=?";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setInt(6, t.getId());
            pst.setString(1, t.getDescription());
            /*pst.setDate(2, t.convertUtilToSql(date1));*/
            pst.setString(2, t.getDomaine());
            pst.setDouble(3, t.getSalaire());
            pst.setString(4, t.getDiplome());
            pst.setString(5, t.getLettre());
           
            
            pst.executeUpdate();
            System.out.println("Demande modifiée !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    @Override
    public List<DemandeEmploi> afficher() {
         List<DemandeEmploi> Dlist = new ArrayList<>();
      try {
             DemandeEmploi D = new DemandeEmploi();
            String requete = "SELECT * FROM `demande_emploi`";
            PreparedStatement pst = cnx.prepareStatement(requete);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
              
                 Date d1=rs.getDate("date_creation");
                    
                      
                      D.setId(rs.getInt("id"));
                      D.setFreelancer_id(rs.getInt("freelancer_id"));
                      D.setOffre_emploi_id(rs.getInt("offre_emploi_id"));
                       D.setDate_creation(d1);
                       
                       D.setDescription(rs.getString("description"));
                       D.setDomaine(rs.getString("domaine"));
                       D.setSalaire(rs.getDouble("salaire"));
                       D.setDiplome(rs.getString("diplome"));
                       D.setNom_societe(rs.getString("nomsociete"));
                Dlist.add(D);
            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

        return Dlist;
     
         

    }
    
}
