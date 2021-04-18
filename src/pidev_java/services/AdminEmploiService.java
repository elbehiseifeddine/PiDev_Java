/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev_java.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import pidev_java.entities.Admin;
import pidev_java.interfaces.IAdminEmploiService;
import pidev_java.utils.MaConnection;

/**
 *
 * @author ahmed
 */
public class AdminEmploiService implements IAdminEmploiService{

    Connection cnx = MaConnection.getInstance().getCnx();

    /**
     * Envoyer l'offre d"emploi vers l'admin
     *
     * @param idOffreEmploi
     */
    @Override
    public void SendOffreEmploiToAdminEmploi(int idOffreEmploi) {

        AdminService adminService = new AdminService();
        ArrayList<Admin> ListAdminEmploi = adminService.getAllAdminEmploi();

        try {
           
            for (Admin admin : ListAdminEmploi) {

                String incrementNonApprouve = "UPDATE admin SET nonapprouve =" + admin.getNonApprouve() + 1 + " WHERE id=" + admin.getId();
                PreparedStatement st0 = cnx.prepareStatement(incrementNonApprouve);
                st0.executeUpdate();

                String find = "SELECT * FROM admin_emploi WHERE id_a_e = " + admin.getId();
                Statement statement = cnx.createStatement();
                ResultSet rs = statement.executeQuery(find);
                if (rs.first()) {
                    if (rs.getInt("id_offre_emploi") == 0) {
                        String req = "UPDATE admin_emploi SET id_offre_emploi = " + idOffreEmploi + " WHERE id_a_e = " + admin.getId();
                        PreparedStatement st = cnx.prepareStatement(req);
                        st.executeUpdate();
                    } else {
                        while (rs.next()) {
                            if (rs.getInt("id_offre_emploi") == 0) {
                                String req = "UPDATE admin_emploi SET id_offre_emploi = " + idOffreEmploi + " WHERE id_a_e = " + admin.getId();
                                PreparedStatement st = cnx.prepareStatement(req);
                                st.executeUpdate();
                            }
                        }
                    }
                } else {

                    String req = "INSERT INTO admin_emploi (id_a_e,id_offre_emploi) VALUES (?,?)";
                    PreparedStatement st = cnx.prepareStatement(req);
                    st.setInt(1, admin.getId());
                    st.setInt(2, idOffreEmploi);
                    st.executeUpdate();
                }
            }

        } catch (SQLException ex) {
            System.out.println("Connexion à la base de données impossible , " + ex.getMessage());
        } 

    }

    /**
     * Envoyer l'offre de stage vers l'admin
     *
     * @param idOffreStage
     */
    @Override
    public void SendOffreStageToAdminEmploi(int idOffreStage) {

        AdminService adminService = new AdminService();
        ArrayList<Admin> ListAdminEmploi = adminService.getAllAdminEmploi();

        try {
            
            for (Admin admin : ListAdminEmploi) {

                String incrementNonApprouve = "UPDATE admin SET nonapprouve =" + admin.getNonApprouve() + 1 + " WHERE id=" + admin.getId();
                PreparedStatement st0 = cnx.prepareStatement(incrementNonApprouve);
                st0.executeUpdate();

                String find = "SELECT * FROM admin_emploi WHERE id_a_e = " + admin.getId();
                Statement statement = cnx.createStatement();
                ResultSet rs = statement.executeQuery(find);
                if (rs.first()) {
                    if (rs.getInt("id_offre_emploi") == 0) {
                        String req = "UPDATE admin_emploi SET id_offre_stage = " + idOffreStage + " WHERE id_a_e = " + admin.getId();
                        PreparedStatement st = cnx.prepareStatement(req);
                        st.executeUpdate();
                    } else {
                        while (rs.next()) {
                            if (rs.getInt("id_offre_emploi") == 0) {
                                String req = "UPDATE admin_emploi SET id_offre_stage = " + idOffreStage + " WHERE id_a_e = " + admin.getId();
                                PreparedStatement st = cnx.prepareStatement(req);
                                st.executeUpdate();
                            }
                        }
                    }
                } else {
                    String req = "INSERT INTO admin_emploi (id_a_e,id_offre_stage) VALUES (?,?)";
                    PreparedStatement st = cnx.prepareStatement(req);
                    st.setInt(1, admin.getId());
                    st.setInt(2, idOffreStage);
                    st.executeUpdate();
                }
            }

        } catch (SQLException ex) {
            System.out.println("Connexion à la base de données impossible , " + ex.getMessage());
        } 

    }

    /**
     * Approuver une offre d'emploi
     *
     * @param offreEmploi
     * @param admin
     */
    
//    @Override
//    public void ActivateOffreEmploi(OffreEmploi offreEmploi, Admin admin) {
//        try {
//            
//
//            String req1 = "UPDATE offre_emploi SET etat=1 WHERE id=" + offreEmploi.getId();
//            String req2 = "UPDATE admin_emploi SET id_offre_emploi=" + null + " WHERE id_a_r <>" + admin.getId();
//            Statement st = cnx.createStatement();
//
//            st.executeUpdate(req1);
//            st.executeUpdate(req2);
//
//        } catch (SQLException ex) {
//            System.out.println("Connexion à la base de données impossible , " + ex.getMessage());
//        } 
//    }
//
//    /**
//     * Approuver une offre de stage
//     *
//     * @param offreStage
//     * @param admin
//     */
//    @Override
//    public void ActivateOffreStage(OffreStage offreStage, Admin admin) {
//        try {
//            
//            String req1 = "UPDATE offre_stage SET etat=1 WHERE id=" + offreStage.getId();
//            String req2 = "UPDATE admin_emploi SET id_offre_stage=" + null + " WHERE id_a_r <>" + admin.getId();
//            Statement st = cnx.createStatement();
//
//            st.executeUpdate(req1);
//            st.executeUpdate(req2);
//
//        } catch (SQLException ex) {
//            System.out.println("Connexion à la base de données impossible , " + ex.getMessage());
//        } 
//    }
//
//    /**
//     * Supprimer une offre d'emploi
//     *
//     * @param offreEmploi
//     */
//    @Override
//    public void DeactivateOffreEmploi(OffreEmploi offreEmploi) {
//        try {
//
//            String req1 = "DELETE FROM offre_emploi WHERE id=" + offreEmploi.getId();
//            Statement st = cnx.createStatement();
//            st.executeUpdate(req1);
//
//            String find = "SELECT * FROM admin_emploi WHERE id_offre_emploi=" + offreEmploi.getId();
//
//            Statement statement = cnx.createStatement();
//            ResultSet rs = statement.executeQuery(find);
//            while (rs.next()) {
//                String req2 = "UPDATE admin_emploi SET id_offre_emploi=null";
//                Statement st2 = cnx.createStatement();
//                st2.executeUpdate(req2);
//            }
//        } catch (SQLException ex) {
//            System.out.println("Connexion à la base de données impossible , " + ex.getMessage());
//        } 
//    }
//
//    /**
//     * Supprimer une offre de stage
//     *
//     * @param offreStage
//     */
//    @Override
//    public void DeactivateOffreStage(OffreStage offreStage) {
//        try {
//            
//            String req1 = "DELETE FROM offre_stage WHERE id=" + offreStage.getId();
//            Statement st = cnx.createStatement();
//            st.executeUpdate(req1);
//
//            String find = "SELECT * FROM admin_emploi WHERE id_offre_stage=" + offreStage.getId();
//
//            Statement statement = cnx.createStatement();
//            ResultSet rs = statement.executeQuery(find);
//            while (rs.next()) {
//                String req2 = "UPDATE admin_emploi SET id_offre_stage=null";
//                Statement st2 = cnx.createStatement();
//                st2.executeUpdate(req2);
//            }
//        } catch (SQLException ex) {
//            System.out.println("Connexion à la base de données impossible , " + ex.getMessage());
//        } 
//    }
//
//    /**
//     * Recuperer liste des offres d'emplois approuvé par cet admin
//     *
//     * @param admin
//     * @return
//     */
//    @Override
//    public ArrayList<OffreEmploi> historiqueOffreEmploi(Admin admin) {
//        ArrayList<OffreEmploi> historique = new ArrayList<>();
//        try {
//            
//            String req1 = "SELECT * FROM admin_emploi WHERE id_a_e=" + admin.getId() + " AND id_offre_emploi <> null";
//            Statement st = cnx.createStatement();
//
//            ResultSet rs = st.executeQuery(req1);
//            while (rs.next()) {
//                String getReclamation = "SELECT * FROM offre_emploi WHERE id =" + rs.getInt("id_offre_emploi");
//                Statement st2 = cnx.createStatement();
//
//                ResultSet rs2 = st2.executeQuery(getReclamation);
//                rs2.first();
//                OffreEmploi emploi = new OffreEmploi();
//                emploi.setId(rs2.getInt("id"));
//                emploi.setSociete_id(rs2.getString("societe_id"));
//                emploi.setNom_projet(rs2.getString("nom_projet"));
//                emploi.setCompetences(rs2.getString("competences"));
//                emploi.setDescription(rs2.getString("description"));
//                emploi.setDomaine(rs2.getString("domaine"));
//                emploi.setSalaire(rs2.getString("salaire"));
//                emploi.setDate_creation(rs2.getString("date_creation"));
//                emploi.setDate_expiration(rs2.getString("date_expiration"));
//
//                historique.add(emploi);
//
//            }
//
//            rs.close();
//        } catch (SQLException ex) {
//            System.out.println("Connexion à la base de données impossible , " + ex.getMessage());
//        } 
//        return historique;
//    }
//
//    /**
//     * Recuperer liste des offres d'emplois approuvé par cet admin
//     *
//     * @param admin
//     * @return
//     */
//    @Override
//    public ArrayList<OffreStage> historiqueOffreStage(Admin admin) {
//        ArrayList<OffreStage> historique = new ArrayList<>();
//        try {
//            
//            String req1 = "SELECT * FROM admin_emploi WHERE id_a_e=" + admin.getId() + " AND id_offre_stage <> null";
//            Statement st = cnx.createStatement();
//
//            ResultSet rs = st.executeQuery(req1);
//            while (rs.next()) {
//                String getReclamation = "SELECT * FROM offre_stage WHERE id =" + rs.getInt("id_offre_stage");
//                Statement st2 = cnx.createStatement();
//
//                ResultSet rs2 = st2.executeQuery(getReclamation);
//                rs2.first();
//                OffreStage stage = new OffreStage();
//                stage.setId(rs2.getInt("id"));
//                stage.setSociete_id(rs2.getString("societe_id"));
//                stage.setNom_projet(rs2.getString("nom_projet"));
//                stage.setCompetences(rs2.getString("competences"));
//                stage.setDescription(rs2.getString("description"));
//                stage.setDomaine(rs2.getString("domaine"));
//                stage.setType_stage(rs2.getString("type_stage"));
//                stage.setDuree(rs2.getString("duree"));
//                stage.setDate_creation(rs2.getString("date_creation"));
//                stage.setDate_expiration(rs2.getString("date_expiration"));
//
//                historique.add(stage);
//
//            }
//
//            rs.close();
//        } catch (SQLException ex) {
//            System.out.println("Connexion à la base de données impossible , " + ex.getMessage());
//        } 
//        return historique;
//    }

}