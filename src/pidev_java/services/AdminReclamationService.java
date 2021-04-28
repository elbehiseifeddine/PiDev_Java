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
import pidev_java.entities.Reclamation;
import pidev_java.utils.MaConnection;
import pidev_java.interfaces.IAdminReclamationService;
import pidev_java.utils.JavaMail;

/**
 *
 * @author ahmed
 */
public class AdminReclamationService implements IAdminReclamationService{

    Connection cnx = MaConnection.getInstance().getCnx();

    /**
     * Envoyer la reclamation vers l'admin
     *
     * @param idReclamation
     */
    @Override
    public void SendToAdminReclamation(int idReclamation) {

        AdminService adminService = new AdminService();
        ArrayList<Admin> ListAdminReclamation = adminService.getAllAdminReclamation();

        try {
            
            for (Admin admin : ListAdminReclamation) {

                String incrementNonApprouve = "UPDATE admin SET nonapprouve =" + admin.getNonApprouve() + 1 + " WHERE id=" + admin.getId();
                PreparedStatement st0 = cnx.prepareStatement(incrementNonApprouve);
                st0.executeUpdate();

                String req = "INSERT INTO admin_reclamation (id_a_r,id_reclamation) VALUES (?,?)";
                PreparedStatement st = cnx.prepareStatement(req);
                st.setInt(1, admin.getId());
                st.setInt(2, idReclamation);
                st.executeUpdate();
                
                JavaMail mail = new JavaMail();
                mail.recipient=admin.getLogin();
                mail.type="EmailReclamation";
                mail.start();
            }

        } catch (SQLException ex) {
            System.out.println("Connexion à la base de données impossible , " + ex.getMessage());
        } 
        

    }

    /**
     * Approuver une reclamtion
     *
     * @param reclamation
     * @param admin
     */
    @Override
    public void Activate(Reclamation reclamation, Admin admin) {
        try {
           for (Admin admin1 : new AdminService().getAllAdminReclamation()) {
                int nonApprouve = admin1.getNonApprouve() - 1;
                String NonApprouve = "UPDATE admin SET nonapprouve =" + nonApprouve + " WHERE id=" + admin1.getId();
                PreparedStatement st0 = cnx.prepareStatement(NonApprouve);
                st0.executeUpdate();
            }
           
           int approuve = admin.getApprouve()+1;
            String NonApprouve = "UPDATE admin SET approuve =" + approuve + " WHERE id=" + admin.getId();
                PreparedStatement st00 = cnx.prepareStatement(NonApprouve);
                st00.executeUpdate();
            String req1 = "UPDATE reclamation SET etat=1 WHERE id=" + reclamation.getId();
            String req2 = "DELETE FROM admin_reclamation WHERE id_reclamation=" + reclamation.getId() + " AND id_a_r <>" + admin.getId();
            Statement st = cnx.createStatement();

            st.executeUpdate(req1);
            st.executeUpdate(req2);

        } catch (SQLException ex) {
            System.out.println("Connexion à la base de données impossible , " + ex.getMessage());
        } 
    }

    /**
     * Supprimer une reclamation
     *
     * @param reclamation
     */
    @Override
    public void Deactivate(Reclamation reclamation) {
        try {
            for (Admin admin1 : new AdminService().getAllAdminReclamation()) {
                int nonApprouve = admin1.getNonApprouve() - 1;
                String NonApprouve = "UPDATE admin SET nonapprouve =" + nonApprouve + " WHERE id=" + admin1.getId();
                PreparedStatement st0 = cnx.prepareStatement(NonApprouve);
                st0.executeUpdate();
            }
            String req1 = "DELETE FROM reclamation WHERE id=" + reclamation.getId();
            String req2 = "DELETE FROM admin_reclamation WHERE id_reclamation=" + reclamation.getId();
            Statement st = cnx.createStatement();

            st.executeUpdate(req1);
            st.executeUpdate(req2);

        } catch (SQLException ex) {
            System.out.println("Connexion à la base de données impossible , " + ex.getMessage());
        } 
    }

    /**
     * Recuperer liste des reclamations approuvé par cet admin
     *
     * @param admin
     * @return
     */
    @Override
    public ArrayList<Reclamation> historique(Admin admin) {
        ArrayList<Reclamation> historique = new ArrayList<>();
        try {
            

            String req1 = "SELECT * FROM admin_reclamation WHERE id_a_r=" + admin.getId();
            Statement st = cnx.createStatement();

            ResultSet rs = st.executeQuery(req1);
            while (rs.next()) {
                String getReclamation = "SELECT * FROM reclamation WHERE id =" + rs.getInt("id_reclamation")+" AND etat = 1";
                Statement st2 = cnx.createStatement();

                ResultSet rs2 = st2.executeQuery(getReclamation);
                rs2.first();
                Reclamation rec = new Reclamation();
                rec.setId(rs2.getInt("id"));
                rec.setType(rs2.getString("type"));
                rec.setDate_reclamation(rs2.getString("date_reclamation"));
                rec.setEmail_utilisateur(rs2.getString("email_utilisateur"));
                rec.setEtat(rs2.getBoolean("etat"));
                rec.setTexte_reclamation(rs2.getString("texte_reclamation"));

                historique.add(rec);

            }

            rs.close();
        } catch (SQLException ex) {
            System.out.println("Connexion à la base de données impossible , " + ex.getMessage());
        }
        return historique;
    }
}
