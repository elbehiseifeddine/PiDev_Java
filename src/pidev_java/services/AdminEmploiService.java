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
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import pidev_java.entities.Admin;
import pidev_java.entities.offreEmploi;
import pidev_java.entities.offreStage;
import pidev_java.interfaces.IAdminEmploiService;
import pidev_java.utils.JavaMail;
import pidev_java.utils.MaConnection;

/**
 *
 * @author ahmed
 */
public class AdminEmploiService implements IAdminEmploiService {

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

                JavaMail mail = new JavaMail();
                mail.recipient = admin.getLogin();
                mail.type = "EmailOffreEmploi";
                mail.start();
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

                JavaMail mail = new JavaMail();
                mail.recipient = admin.getLogin();
                mail.type = "EmailOffreStage";
                mail.start();
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
    @Override
    public void ActivateOffreEmploi(offreEmploi offreEmploi, Admin admin) {
        try {
            
            for (Admin admin1 : new AdminService().getAllAdminEmploi()) {
                int nonApprouve = admin1.getNonApprouve() - 1;
                String NonApprouve = "UPDATE admin SET nonapprouve =" + nonApprouve + " WHERE id=" + admin1.getId();
                PreparedStatement st0 = cnx.prepareStatement(NonApprouve);
                st0.executeUpdate();
            }
            int approuve = admin.getApprouve()+1;
            String NonApprouve = "UPDATE admin SET approuve =" + approuve + " WHERE id=" + admin.getId();
                PreparedStatement st00 = cnx.prepareStatement(NonApprouve);
                st00.executeUpdate();

            String req1 = "UPDATE offre_emploi SET etat=1 WHERE id=" + offreEmploi.getId();
//            String req2 = "UPDATE admin_emploi SET id_offre_emploi=" + null + " WHERE id_a_e <>" + admin.getId();
            Statement st = cnx.createStatement();

            st.executeUpdate(req1);
//            st.executeUpdate(req2);

        } catch (SQLException ex) {
            System.out.println("Connexion à la base de données impossible , " + ex.getMessage());
        }
    }

    /**
     * Approuver une offre de stage
     *
     * @param offreStage
     * @param admin
     */
    @Override
    public void ActivateOffreStage(offreStage offreStage, Admin admin) {
        try {
            
            for (Admin admin1 : new AdminService().getAllAdminEmploi()) {
                int nonApprouve = admin1.getNonApprouve() - 1;
                String NonApprouve = "UPDATE admin SET nonapprouve =" + nonApprouve + " WHERE id=" + admin1.getId();
                PreparedStatement st0 = cnx.prepareStatement(NonApprouve);
                st0.executeUpdate();
            }
            int approuve = admin.getApprouve()+1;
            String NonApprouve = "UPDATE admin SET approuve =" + approuve + " WHERE id=" + admin.getId();
                PreparedStatement st00 = cnx.prepareStatement(NonApprouve);
                st00.executeUpdate();

            String req1 = "UPDATE offre_stage SET etat=1 WHERE id=" + offreStage.getId();
//            String req2 = "UPDATE admin_emploi SET id_offre_stage=" + null + " WHERE id_a_e <>" + admin.getId();
            Statement st = cnx.createStatement();

            st.executeUpdate(req1);
//            st.executeUpdate(req2);

        } catch (SQLException ex) {
            System.out.println("Connexion à la base de données impossible , " + ex.getMessage());
        }
    }

    /**
     * Supprimer une offre d'emploi
     *
     * @param offreEmploi
     */
    @Override
    public void DeactivateOffreEmploi(offreEmploi offreEmploi) {
        try {
            
            for (Admin admin1 : new AdminService().getAllAdminEmploi()) {
                int nonApprouve = admin1.getNonApprouve() - 1;
                String NonApprouve = "UPDATE admin SET nonapprouve =" + nonApprouve + " WHERE id=" + admin1.getId();
                PreparedStatement st0 = cnx.prepareStatement(NonApprouve);
                st0.executeUpdate();
            }
            

            String req1 = "DELETE FROM offre_emploi WHERE id=" + offreEmploi.getId();
            Statement st = cnx.createStatement();
            st.executeUpdate(req1);

            String find = "SELECT * FROM admin_emploi WHERE id_offre_emploi=" + offreEmploi.getId();

            Statement statement = cnx.createStatement();
            ResultSet rs = statement.executeQuery(find);
            while (rs.next()) {
                String req2 = "UPDATE admin_emploi SET id_offre_emploi=0 where id = "+rs.getInt("id");
                Statement st2 = cnx.createStatement();
                st2.executeUpdate(req2);
            }
        } catch (SQLException ex) {
            System.out.println("Connexion à la base de données impossible , " + ex.getMessage());
        }
    }

    /**
     * Supprimer une offre de stage
     *
     * @param offreStage
     */
    @Override
    public void DeactivateOffreStage(offreStage offreStage) {
        try {

            for (Admin admin1 : new AdminService().getAllAdminEmploi()) {
                int nonApprouve = admin1.getNonApprouve() - 1;
                String NonApprouve = "UPDATE admin SET nonapprouve =" + nonApprouve + " WHERE id=" + admin1.getId();
                PreparedStatement st0 = cnx.prepareStatement(NonApprouve);
                st0.executeUpdate();
            }
            String req1 = "DELETE FROM offre_stage WHERE id=" + offreStage.getId();
            Statement st = cnx.createStatement();
            st.executeUpdate(req1);

            String find = "SELECT * FROM admin_emploi WHERE id_offre_stage=" + offreStage.getId();

            Statement statement = cnx.createStatement();
            ResultSet rs = statement.executeQuery(find);
            while (rs.next()) {
                String req2 = "UPDATE admin_emploi SET id_offre_stage=0 where id = "+rs.getInt("id");
                Statement st2 = cnx.createStatement();
                st2.executeUpdate(req2);
            }
        } catch (SQLException ex) {
            System.out.println("Connexion à la base de données impossible , " + ex.getMessage());
        }
    }

    /**
     * Recuperer liste des offres d'emplois approuvé par cet admin
     *
     * @param admin
     * @return
     */
    @Override
    public ArrayList<offreEmploi> historiqueOffreEmploi(Admin admin) {
        ArrayList<offreEmploi> historique = new ArrayList<>();
        try {

            String req1 = "SELECT * FROM admin_emploi WHERE id_a_e=" + admin.getId();
            Statement st = cnx.createStatement();

            ResultSet rs = st.executeQuery(req1);
            while (rs.next()) {
                if (rs.getInt("id_offre_emploi")!=0){
                String getReclamation = "SELECT * FROM offre_emploi WHERE id =" + rs.getInt("id_offre_emploi")+" AND etat=1";
                Statement st2 = cnx.createStatement();

                ResultSet rs2 = st2.executeQuery(getReclamation);
                rs2.first();
                offreEmploi emploi = new offreEmploi();
                emploi.setId(rs2.getInt("id"));
                emploi.setIdSociete(rs2.getInt("societe_id"));
                emploi.setNomProjet(rs2.getString("nom_projet"));
                emploi.setCompetence(rs2.getString("competences"));
                emploi.setDescription(rs2.getString("description"));
                emploi.setDomaine(rs2.getString("domaine"));
                emploi.setSalaire(rs2.getFloat("salaire"));
                emploi.setDateCreation(rs2.getDate("date_creation"));
                emploi.setDateExpiration(rs2.getDate("date_expiration"));

                historique.add(emploi);

            
                }
            }
            

            rs.close();
        } catch (SQLException ex) {
            System.out.println("Connexion à la base de données impossible , " + ex.getMessage());
        }
        return historique;
    }

    /**
     * Recuperer liste des offres d'emplois approuvé par cet admin
     *
     * @param admin
     * @return
     */
    @Override
    public ArrayList<offreStage> historiqueOffreStage(Admin admin) {
        ArrayList<offreStage> historique = new ArrayList<>();
        try {

            String req1 = "SELECT * FROM admin_emploi WHERE id_a_e=" + admin.getId();
            Statement st = cnx.createStatement();

            ResultSet rs = st.executeQuery(req1);
            while (rs.next()) {
                if (rs.getInt("id_offre_stage")!= 0){
                String getReclamation = "SELECT * FROM offre_stage WHERE id =" + rs.getInt("id_offre_stage")+" AND etat=1";
                Statement st2 = cnx.createStatement();

                ResultSet rs2 = st2.executeQuery(getReclamation);
                rs2.first();
                offreStage stage = new offreStage();
                stage.setId(rs2.getInt("id"));
                stage.setIdSociete(rs2.getInt("societe_id"));
                stage.setNomProjet(rs2.getString("nom_projet"));
                stage.setCompetence(rs2.getString("competences"));
                stage.setDescription(rs2.getString("description"));
                stage.setDomaine(rs2.getString("domaine"));
                stage.setTypeStage(rs2.getString("type_stage"));
                stage.setDuree(rs2.getString("duree"));
                stage.setDateCreation(rs2.getDate("date_creation"));
                stage.setDateExpiration(rs2.getDate("date_expiration"));

                historique.add(stage);

                }
            }

            rs.close();
        } catch (SQLException ex) {
            System.out.println("Connexion à la base de données impossible , " + ex.getMessage());
        }
        return historique;
    }

    public ArrayList<offreEmploi> getAllEmploiNonApprouve() {
        ArrayList<offreEmploi> res;
        res = new ArrayList<>();
        try {
            Statement stmt = cnx.createStatement();
            String sql = "SELECT * FROM offre_emploi where etat = 0";
            ResultSet rs0 = stmt.executeQuery(sql);
            while (rs0.next()) {

                int id = rs0.getInt("id");
                String nom = rs0.getString("nom_projet");
                String comp = rs0.getString("competences");
                String description = rs0.getString("description");
                String domaine = rs0.getString("domaine");
                Float salaire = rs0.getFloat("salaire");
                Date dtc = rs0.getDate("date_creation");
                Date dtE = rs0.getDate("date_expiration");
                String devise = rs0.getString("devise");

                offreEmploi F = new offreEmploi(id, nom, comp, description, domaine, salaire, dtc, dtE, devise);
                res.add(F);
            }
            rs0.close();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return res;
    }

    public ArrayList<offreStage> getAllStageNonApprouve() {
        ArrayList<offreStage> res;
        res = new ArrayList<>();
        try {
            Statement stmt = cnx.createStatement();
            String sql = "SELECT * FROM offre_stage where etat = 0";
            ResultSet rs0 = stmt.executeQuery(sql);
            while (rs0.next()) {
                offreStage F = new offreStage();
                F.setId(rs0.getInt("id"));
                F.setIdSociete(rs0.getInt("societe_id"));
                F.setNomProjet(rs0.getString("nom_projet"));
                F.setCompetence(rs0.getString("competences"));
                F.setDescription(rs0.getString("description"));
                F.setDomaine(rs0.getString("domaine"));
                F.setDuree(rs0.getString("duree"));
                F.setTypeStage(rs0.getString("type_stage"));
                F.setDateCreation(rs0.getDate("date_creation"));
                F.setDateExpiration(rs0.getDate("date_expiration"));

                res.add(F);
            }
            rs0.close();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return res;
    }

}
