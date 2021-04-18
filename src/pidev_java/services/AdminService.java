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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import pidev_java.entities.Admin;
import pidev_java.interfaces.IAdminService;
import pidev_java.utils.MaConnection;

/**
 *
 * @author ahmed
 */
public class AdminService implements IAdminService<Admin> {

    Connection cnx = MaConnection.getInstance().getCnx();

    @Override
    public void add(Admin entity) {

        int nonapprouve = 0;
        String requette = "INSERT INTO admin (nom,prenom,login,password,type,etat,approuve,nonapprouve) VALUES (?,?,?,?,?,?,?,?)";
        System.out.println(requette);
        try {

            PreparedStatement st = cnx.prepareStatement(requette);
            st.setString(1, entity.getNom());
            st.setString(2, entity.getPrenom());
            st.setString(3, entity.getLogin());
            st.setString(4, entity.getPass());
            st.setString(5, entity.getType());
            st.setBoolean(6, entity.isEtat());
            st.setInt(7, 0);
            System.out.println(requette);
            if (entity.getType().equals("Admin des reclamations")) {
                ReclamationService rec = new ReclamationService();
                nonapprouve = rec.countReclamationNonApprouve();

            }

//            if (entity.getType().equals("Admin des emplois")) {
//                OffreEmploiService OES = new OffreEmploiService();
//                OffreStageService OSS = new OffreStageService();
//                
//                nonapprouve = OES.countOffreEmploiNonApprouve() + OSS.countOffreStageNonApprouve();
//            }
//
//            if (entity.getType().equals("Admin des events")) {
//                EventLoisirService ELS = new EventLoisirService();
//                FormationService FS = new FormationService();
//                nonapprouve = ELS.countEventsLoisirNonApprouve() + FS.countFormationNonApprouve();
//            }
            st.setInt(8, nonapprouve);
            System.out.println(requette);
            st.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("this is the add function Connexion à la base de données impossible , " + ex.getMessage());
        }

    }

    @Override
    public ArrayList<Admin> getAll() {
        
        ArrayList<Admin> ListeAdmins = new ArrayList<>();
        String req = "SELECT * FROM admin";
        try {

            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);

            while (rs.next()) {
                Admin a = new Admin();
                a.setId(rs.getInt("id"));
                a.setNom(rs.getString("nom"));
                a.setPrenom(rs.getString("prenom"));
                a.setLogin(rs.getString("login"));
                a.setPass(rs.getString("password"));
                a.setType(rs.getString("type"));
                a.setEtat(rs.getBoolean("etat"));
                a.setApprouve(rs.getInt("approuve"));
                a.setNonApprouve(rs.getInt("nonapprouve"));
                
                ListeAdmins.add(a);
            }
            rs.close();
        } catch (SQLException ex) {
            System.out.println("Connexion à la base de données impossible , " + ex.getMessage());
        }
        return ListeAdmins;
    }

    @Override
    public void update(Admin entity, String OldType) {
        int nonapprouve;
        try {

            if (entity.getType().equals(OldType)) {
                String requette = "UPDATE `admin` SET "
                        + "`nom`= ?,"
                        + "`prenom`= ?,"
                        + "`login`= ?,"
                        + "`password`= ?,"
                        + "`type`= ?,"
                        + "`etat`= ? "
                        + "WHERE id = ?  ";
                PreparedStatement st2 = cnx.prepareStatement(requette);
                st2.setString(1, entity.getNom());
                st2.setString(2, entity.getPrenom());
                st2.setString(3, entity.getLogin());
                st2.setString(4, entity.getPass());
                st2.setString(5, entity.getType());
                st2.setBoolean(6, entity.isEtat());
                st2.setInt(7, entity.getId());
                
                System.out.println(requette);
                st2.executeUpdate();
                                System.out.println(requette);

            } else {
                if (entity.getType().equals("Admin des reclamations")) {
                    ReclamationService rec = new ReclamationService();
                    nonapprouve = rec.countReclamationNonApprouve();

//                    String requette = "UPDATE admin SET 'nom'='" + entity.getNom() + "','prenom'='" + entity.getPrenom()
//                            + "','login'='" + entity.getLogin() + "','password'='" + entity.getPass() + "' , 'type'='" + entity.getType()
//                            + "' , 'etat'=" + entity.isEtat() + " , approuve = 0 , nonapprouve = " + nonapprouve + ""
//                            + " where id = " + entity.getId() + ";";
//                    String requette = "UPDATE `admin` SET "
//                            + "`nom`=" + entity.getNom() + ","
//                            + "`prenom`=" + entity.getPrenom() + ","
//                            + "`login`=" + entity.getLogin()+ ","
//                            + "`password`=" + entity.getPass()+ ","
//                            + "`type`=" + entity.getType()+ ","
//                            + "`etat`=" + entity.isEtat()+ ","
//                            + "WHERE id =" + entity.getId();
//                    Statement st = cnx.createStatement();
//
//                    st.executeUpdate(requette);
                    String requette = "UPDATE `admin` SET "
                            + "`nom`= ? ,"
                            + "`prenom`= ? ,"
                            + "`login`= ? ,"
                            + "`password`= ? ,"
                            + "`type`= ? ,"
                            + "`etat`= ? "
                            + "WHERE id = ? ";
                    PreparedStatement st3 = cnx.prepareStatement(requette);
                    st3.setString(1, entity.getNom());
                    st3.setString(2, entity.getPrenom());
                    st3.setString(3, entity.getLogin());
                    st3.setString(4, entity.getPass());
                    st3.setString(5, entity.getType());
                    st3.setBoolean(6, entity.isEtat());
                st3.setInt(7, entity.getId());

                    st3.executeUpdate();

                }

//                if (entity.getType().equals("Admin des emplois")) {
//                    OffreEmploiService OES = new OffreEmploiService();
//                    OffreStageService OSS = new OffreStageService();
//                    nonapprouve = OES.countOffreEmploiNonApprouve() + OSS.countOffreStageNonApprouve();
//                    String requette = "UPDATE admin SET 'nom'='" + entity.getNom() + "','prenom'='" + entity.getPrenom()
//                            + "','login'='" + entity.getLogin() + "','password'='" + entity.getPass() + "' , 'type'='" + entity.getType()
//                            + "' , 'etat'=" + entity.isEtat() + " , approuve = 0 , nonapprouve = " + nonapprouve + ""
//                            + " where id = " + entity.getId() + ";";
//                    Statement st = cnx.createStatement();
//
//                    st.executeUpdate(requette);
//                }
//
//                if (entity.getType().equals("Admin des events")) {
//                    EventLoisirService ELS = new EventLoisirService();
//                    FormationService FS = new FormationService();
//                    nonapprouve = ELS.countEventsLoisirNonApprouve() + FS.countFormationNonApprouve();
//                    String requette = "UPDATE admin SET 'nom'='" + entity.getNom() + "','prenom'='" + entity.getPrenom()
//                            + "','login'='" + entity.getLogin() + "','password'='" + entity.getPass() + "' , 'type'='" + entity.getType()
//                            + "' , 'etat'=" + entity.isEtat() + " , approuve = 0 , nonapprouve = " + nonapprouve + ""
//                            + " where id = " + entity.getId() + ";";
//                    Statement st = cnx.createStatement();
//
//                    st.executeUpdate(requette);
//                }
            }
        } catch (SQLException ex) {
            System.out.println(" update function || Connexion à la base de données impossible , " + ex.getMessage());
        }
    }

    @Override
    public void delete(Admin entity) {
        try {
            System.out.println(entity.getId());
            String delete;
            if (entity.getType().equals("Admin des reclamations")) {
                delete = "DELETE FROM admin_reclamation where id_a_r =" + entity.getId();
            }

            if (entity.getType().equals("Admin des emplois")) {
                delete = "DELETE FROM admin_emploi where id_a_e =" + entity.getId();
            }

            if (entity.getType().equals("Admin des events")) {
                delete = "DELETE FROM admin_event where id_a_e =" + entity.getId();
            }
            String requette = "DELETE FROM admin where id = " + entity.getId() + ";";
            Statement st = cnx.createStatement();

            st.executeUpdate(requette);
        } catch (SQLException ex) {
            Logger.getLogger(AdminService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public Admin find(int id) {
        Admin admin = new Admin();
        String req = "SELECT * FROM admin WHERE id =" + id + ";";
        try {

            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);

            while (rs.next()) {

                admin.setNom(rs.getString("nom"));
                admin.setPrenom(rs.getString("prenom"));
                admin.setLogin(rs.getString("login"));
                admin.setPass(rs.getString("password"));
                admin.setType(rs.getString("type"));
                admin.setEtat(rs.getBoolean("etat"));
                admin.setApprouve(rs.getInt("approuve"));
                admin.setNonApprouve(rs.getInt("nonapprouve"));

            }
            rs.close();
        } catch (SQLException ex) {
            System.out.println("Connexion à la base de données impossible , " + ex.getMessage());
        }
        return admin;
    }

    @Override
    public ArrayList<Admin> findByName(String name) {
        ArrayList<Admin> ListeAdmins = new ArrayList<>();
        String req = "SELECT * FROM admin WHERE nom LIKE %" + name + "%;";
        try {

            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);

            while (rs.next()) {
                Admin admin = new Admin();
                admin.setNom(rs.getString("nom"));
                admin.setPrenom(rs.getString("prenom"));
                admin.setLogin(rs.getString("login"));
                admin.setPass(rs.getString("password"));
                admin.setType(rs.getString("type"));
                admin.setEtat(rs.getBoolean("etat"));
                admin.setApprouve(rs.getInt("approuve"));
                admin.setNonApprouve(rs.getInt("nonapprouve"));
                ListeAdmins.add(admin);

            }
            rs.close();
        } catch (SQLException ex) {
            System.out.println("Connexion à la base de données impossible , " + ex.getMessage());
        }
        return ListeAdmins;
    }

    @Override
    public ArrayList<Admin> getAllAdminReclamation() {
        ArrayList<Admin> ListeAdmins = new ArrayList<>();
        String req = "SELECT * FROM admin WHERE type = 'Admin des reclamations'";
        try {

            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);

            while (rs.next()) {
                Admin a = new Admin();
                a.setId(rs.getInt("id"));
                a.setNom(rs.getString("nom"));
                a.setPrenom(rs.getString("prenom"));
                a.setLogin(rs.getString("login"));
                a.setPass(rs.getString("password"));
                a.setType(rs.getString("type"));
                a.setEtat(rs.getBoolean("etat"));
                a.setApprouve(rs.getInt("approuve"));
                a.setNonApprouve(rs.getInt("nonapprouve"));

                ListeAdmins.add(a);
            }
            rs.close();
        } catch (SQLException ex) {
            System.out.println("Connexion à la base de données impossible , " + ex.getMessage());
        }
        return ListeAdmins;
    }

    @Override
    public ArrayList<Admin> getAllAdminEmploi() {
        ArrayList<Admin> ListeAdmins = new ArrayList<>();
        String req = "SELECT * FROM admin WHERE type = 'Admin des emplois'";
        try {

            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);

            while (rs.next()) {
                Admin a = new Admin();
                a.setId(rs.getInt("id"));
                a.setNom(rs.getString("nom"));
                a.setPrenom(rs.getString("prenom"));
                a.setLogin(rs.getString("login"));
                a.setPass(rs.getString("password"));
                a.setType(rs.getString("type"));
                a.setEtat(rs.getBoolean("etat"));
                a.setApprouve(rs.getInt("approuve"));
                a.setNonApprouve(rs.getInt("nonapprouve"));

                ListeAdmins.add(a);
            }
            rs.close();
        } catch (SQLException ex) {
            System.out.println("Connexion à la base de données impossible , " + ex.getMessage());
        }
        return ListeAdmins;
    }

    @Override
    public ArrayList<Admin> getAllAdminEvent() {
        ArrayList<Admin> ListeAdmins = new ArrayList<>();
        String req = "SELECT * FROM admin WHERE type = 'Admin des events'";
        try {

            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);

            while (rs.next()) {
                Admin a = new Admin();
                a.setId(rs.getInt("id"));
                a.setNom(rs.getString("nom"));
                a.setPrenom(rs.getString("prenom"));
                a.setLogin(rs.getString("login"));
                a.setPass(rs.getString("password"));
                a.setType(rs.getString("type"));
                a.setEtat(rs.getBoolean("etat"));
                a.setApprouve(rs.getInt("approuve"));
                a.setNonApprouve(rs.getInt("nonapprouve"));

                ListeAdmins.add(a);
            }
            rs.close();
        } catch (SQLException ex) {
            System.out.println("Connexion à la base de données impossible , " + ex.getMessage());
        }
        return ListeAdmins;
    }

    @Override
    public Admin findByEmail(String login){
        Admin a = new Admin();
        try {
            
            String req = "SELECT * FROM admin WHERE login ='"+login+"' ;";
            Statement st = cnx.createStatement();
            ResultSet rs4 = st.executeQuery(req);
            while(rs4.next()){
                a.setId(rs4.getInt("id"));
                a.setLogin(login);
                a.setPass(rs4.getString("password"));
                a.setNom(rs4.getString("nom"));
                a.setPrenom(rs4.getString("prenom"));
                a.setType(rs4.getString("type"));
                a.setEtat(rs4.getBoolean("etat"));
                a.setApprouve(rs4.getInt("approuve"));
                a.setNonApprouve(rs4.getInt("nonapprouve"));
            }
            rs4.close();
            
        } catch (SQLException ex) {
            Logger.getLogger(AdminService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return a;
    }
}