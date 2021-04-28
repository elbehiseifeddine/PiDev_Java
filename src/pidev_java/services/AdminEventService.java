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
import java.sql.Timestamp;
import java.util.ArrayList;
import pidev_java.entities.Admin;
import pidev_java.entities.EventLoisir;
import pidev_java.entities.Formation;
import pidev_java.interfaces.IAdminEventService;
import pidev_java.utils.JavaMail;
import pidev_java.utils.MaConnection;

/**
 *
 * @author ahmed
 */
public class AdminEventService implements IAdminEventService {

    Connection cnx = MaConnection.getInstance().getCnx();

    @Override
    public void SendEvenementToAdminEmploi(int idEvent) {
        AdminService adminService = new AdminService();
        ArrayList<Admin> ListAdminEvent = adminService.getAllAdminEvent();

        try {

            for (Admin admin : ListAdminEvent) {

                String incrementNonApprouve = "UPDATE admin SET nonapprouve =" + admin.getNonApprouve() + 1 + " WHERE id=" + admin.getId();
                PreparedStatement st0 = cnx.prepareStatement(incrementNonApprouve);
                st0.executeUpdate();

                String find = "SELECT * FROM admin_event WHERE id_a_e = " + admin.getId();
                Statement statement = cnx.createStatement();
                ResultSet rs = statement.executeQuery(find);
                if (rs.first()) {
                    if (rs.getInt("id_event_loisir") == 0) {
                        String req = "UPDATE admin_event SET id_event_loisir = " + idEvent + " WHERE id_a_e = " + admin.getId();
                        PreparedStatement st = cnx.prepareStatement(req);
                        st.executeUpdate();
                    } else {
                        while (rs.next()) {
                            if (rs.getInt("id_event_loisir") == 0) {
                                String req = "UPDATE admin_event SET id_event_loisir = " + idEvent + " WHERE id_a_e = " + admin.getId();
                                PreparedStatement st = cnx.prepareStatement(req);
                                st.executeUpdate();
                                break;
                            }
                        }
                    }
                } else {

                    String req = "INSERT INTO admin_event (id_a_e,id_event_loisir) VALUES (?,?)";
                    PreparedStatement st = cnx.prepareStatement(req);
                    st.setInt(1, admin.getId());
                    st.setInt(2, idEvent);
                    st.executeUpdate();
                }

                JavaMail mail = new JavaMail();
                mail.recipient = admin.getLogin();
                mail.type = "EmailEvenement";
                mail.start();
            }

        } catch (SQLException ex) {
            System.out.println("Send event to admin methode ,Connexion à la base de données impossible , " + ex.getMessage());
        }
    }

    @Override
    public void SendFormationToAdminEmploi(int idFormation) {
        AdminService adminService = new AdminService();
        ArrayList<Admin> ListAdminEvent = adminService.getAllAdminEvent();

        try {

            for (Admin admin : ListAdminEvent) {

                String incrementNonApprouve = "UPDATE admin SET nonapprouve =" + admin.getNonApprouve() + 1 + " WHERE id=" + admin.getId();
                PreparedStatement st0 = cnx.prepareStatement(incrementNonApprouve);
                st0.executeUpdate();

                String find = "SELECT * FROM admin_event WHERE id_a_e = " + admin.getId();
                Statement statement = cnx.createStatement();
                ResultSet rs = statement.executeQuery(find);
                if (rs.first()) {
                    if (rs.getInt("id_formation") == 0) {
                        String req = "UPDATE admin_event SET id_formation = " + idFormation + " WHERE id_a_e = " + admin.getId();
                        PreparedStatement st = cnx.prepareStatement(req);
                        st.executeUpdate();
                    } else {
                        while (rs.next()) {
                            if (rs.getInt("id_formation") == 0) {
                                String req = "UPDATE admin_event SET id_formation = " + idFormation + " WHERE id_a_e = " + admin.getId();
                                PreparedStatement st = cnx.prepareStatement(req);
                                st.executeUpdate();
                                break;
                            }
                        }
                    }
                } else {

                    String req = "INSERT INTO admin_event (id_a_e,id_formation) VALUES (?,?)";
                    PreparedStatement st = cnx.prepareStatement(req);
                    st.setInt(1, admin.getId());
                    st.setInt(2, idFormation);
                    st.executeUpdate();
                }

                JavaMail mail = new JavaMail();
                mail.recipient = admin.getLogin();
                mail.type = "EmailFormation";
                mail.start();
            }

        } catch (SQLException ex) {
            System.out.println("Send formation to admin methode ,Connexion à la base de données impossible , " + ex.getMessage());
        }
    }

    @Override
    public void ActivateEvenement(EventLoisir evenement, Admin admin) {
        try {
            for (Admin admin1 : new AdminService().getAllAdminEvent()) {
                int nonApprouve = admin1.getNonApprouve() - 1;
                String NonApprouve = "UPDATE admin SET nonapprouve =" + nonApprouve + " WHERE id=" + admin1.getId();
                PreparedStatement st0 = cnx.prepareStatement(NonApprouve);
                st0.executeUpdate();
            }
            int approuve = admin.getApprouve()+1;
            String NonApprouve = "UPDATE admin SET approuve =" + approuve + " WHERE id=" + admin.getId();
                PreparedStatement st00 = cnx.prepareStatement(NonApprouve);
                st00.executeUpdate();
            
            
            

            String req1 = "UPDATE event_loisir SET etat=1 WHERE id=" + evenement.getId();
//            String req2 = "UPDATE admin_emploi SET id_offre_emploi=" + null + " WHERE id_a_e <>" + admin.getId();
            Statement st = cnx.createStatement();

            st.executeUpdate(req1);
//            st.executeUpdate(req2);

        } catch (SQLException ex) {
            System.out.println("Activate event methode ,Connexion à la base de données impossible , " + ex.getMessage());
        }
    }

    @Override
    public void DeactivateEvenement(EventLoisir evenement) {
try {

    for (Admin admin1 : new AdminService().getAllAdminEvent()) {
                int nonApprouve = admin1.getNonApprouve() - 1;
                String NonApprouve = "UPDATE admin SET nonapprouve =" + nonApprouve + " WHERE id=" + admin1.getId();
                PreparedStatement st0 = cnx.prepareStatement(NonApprouve);
                st0.executeUpdate();
            }
            String req1 = "DELETE FROM event_loisir WHERE id=" + evenement.getId();
            Statement st = cnx.createStatement();
            st.executeUpdate(req1);

            String find = "SELECT * FROM admin_event WHERE id_event_loisir=" + evenement.getId();

            Statement statement = cnx.createStatement();
            ResultSet rs = statement.executeQuery(find);
            while (rs.next()) {
                String req2 = "UPDATE admin_event SET id_event_loisir=0 where id="+rs.getInt("id");
                Statement st2 = cnx.createStatement();
                st2.executeUpdate(req2);
            }
        } catch (SQLException ex) {
            System.out.println("Deactivate event methode ,Connexion à la base de données impossible , " + ex.getMessage());
        }        }

    @Override
    public void ActivateFormation(Formation formation, Admin admin) {
try {
    for (Admin admin1 : new AdminService().getAllAdminEvent()) {
                int nonApprouve = admin1.getNonApprouve() - 1;
                String NonApprouve = "UPDATE admin SET nonapprouve =" + nonApprouve + " WHERE id=" + admin1.getId();
                PreparedStatement st0 = cnx.prepareStatement(NonApprouve);
                st0.executeUpdate();
            }
            int approuve = admin.getApprouve()+1;
            String NonApprouve = "UPDATE admin SET approuve =" + approuve + " WHERE id=" + admin.getId();
                PreparedStatement st00 = cnx.prepareStatement(NonApprouve);
                st00.executeUpdate();

            String req1 = "UPDATE formation SET etat=1 WHERE id=" + formation.getId();
//            String req2 = "UPDATE admin_emploi SET id_offre_emploi=" + null + " WHERE id_a_e <>" + admin.getId();
            Statement st = cnx.createStatement();

            st.executeUpdate(req1);
//            st.executeUpdate(req2);

        } catch (SQLException ex) {
            System.out.println("Activate formation methode ,Connexion à la base de données impossible , " + ex.getMessage());
        }    }

    @Override
    public void DeactivateFormation(Formation formation) {
try {
    
    for (Admin admin1 : new AdminService().getAllAdminEvent()) {
                int nonApprouve = admin1.getNonApprouve() - 1;
                String NonApprouve = "UPDATE admin SET nonapprouve =" + nonApprouve + " WHERE id=" + admin1.getId();
                PreparedStatement st0 = cnx.prepareStatement(NonApprouve);
                st0.executeUpdate();
            }

            String req1 = "DELETE FROM formation WHERE id=" + formation.getId();
            Statement st = cnx.createStatement();
            st.executeUpdate(req1);

            String find = "SELECT * FROM admin_event WHERE id_formation=" + formation.getId();

            Statement statement = cnx.createStatement();
            ResultSet rs = statement.executeQuery(find);
            while (rs.next()) {
                String req2 = "UPDATE admin_event SET id_formation=0 where id="+rs.getInt("id");
                Statement st2 = cnx.createStatement();
                st2.executeUpdate(req2);
            }
        } catch (SQLException ex) {
            System.out.println("Deactivate Formation methode ,Connexion à la base de données impossible , " + ex.getMessage());
        }    }

    @Override
    public ArrayList<Formation> historiqueFormation(Admin admin) {
ArrayList<Formation> historique = new ArrayList<>();
        try {

            String req1 = "SELECT * FROM admin_event WHERE id_a_e=" + admin.getId();
            Statement st = cnx.createStatement();

            ResultSet rs = st.executeQuery(req1);
            while (rs.next()) {
                if (rs.getInt("id_formation")!=0){
                String getReclamation = "SELECT * FROM formation WHERE id =" + rs.getInt("id_formation")+" AND etat=1";
                Statement st2 = cnx.createStatement();

                ResultSet rs2 = st2.executeQuery(getReclamation);
                rs2.first();
                int id = rs2.getInt("id");
                String labelle = rs2.getString("labelle");
                String lieu = rs2.getString("lieu");
                String domaine = rs2.getString("domaine");
                String description=rs2.getString("description");
                Timestamp dateDebut=rs2.getTimestamp("date_debut");
                Timestamp dateFin=rs2.getTimestamp("date_fin");
                float montant=rs2.getFloat("montant");
                long lat=rs2.getLong("lat");
                long lng=rs2.getLong("lng");
                String image=rs2.getString("image");
                
                Formation F = new Formation (id,labelle,description,lieu,dateDebut,dateFin,domaine,montant,true,lng,lat,image);
                

                historique.add(F);

            
                }
            }
            

            rs.close();
        } catch (SQLException ex) {
            System.out.println("Historique Formation methode ,Connexion à la base de données impossible , " + ex.getMessage());
        }
        return historique;
    }

    @Override
    public ArrayList<EventLoisir> historiqueEvenement(Admin admin) {
ArrayList<EventLoisir> historique = new ArrayList<>();
        try {

            String req1 = "SELECT * FROM admin_event WHERE id_a_e=" + admin.getId();
            Statement st = cnx.createStatement();

            ResultSet rs = st.executeQuery(req1);
            while (rs.next()) {
                if (rs.getInt("id_event_loisir")!=0){
                String getReclamation = "SELECT * FROM event_loisir WHERE id =" + rs.getInt("if_event_loisir")+" AND etat=1";
                Statement st2 = cnx.createStatement();

                ResultSet rs2 = st2.executeQuery(getReclamation);
                rs2.first();
                int id = rs2.getInt("id");
                String labelle = rs2.getString("labelle");
                String lieu = rs2.getString("lieu");
                String domaine = rs2.getString("domaine");
                String description=rs2.getString("description");
                Timestamp dateDebut=rs2.getTimestamp("date_debut");
                Timestamp dateFin=rs2.getTimestamp("date_fin");
                int nbParticipant=rs2.getInt("nb_participant");
                long lat=rs2.getLong("lat");
                long lng=rs2.getLong("lng");
                String image=rs2.getString("imagee");
                
                EventLoisir Ev = new EventLoisir (id,labelle,description,lieu,dateDebut,dateFin,domaine,nbParticipant,true,lng,lat,image);
                

                historique.add(Ev);

            
                }
            }
            

            rs.close();
        } catch (SQLException ex) {
            System.out.println("Historique event methode ,Connexion à la base de données impossible , " + ex.getMessage());
        }
        return historique;
    }
    
    public ArrayList<EventLoisir> getAllEventLoisirNonApprouve(){
        ArrayList<EventLoisir> res = new ArrayList<EventLoisir>();
        try {
            Statement stmt = cnx.createStatement();
            String sql = "SELECT * FROM event_loisir where etat=0;";
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                int id = rs.getInt("id");
                String labelle = rs.getString("labelle");
                String lieu = rs.getString("lieu");
                String domaine = rs.getString("domaine");
                String description=rs.getString("description");
                Timestamp dateDebut=rs.getTimestamp("date_debut");
                Timestamp dateFin=rs.getTimestamp("date_fin");
                int nbParticipant=rs.getInt("nb_participant");
                long lat=rs.getLong("lat");
                long lng=rs.getLong("lng");
                String image=rs.getString("imagee");
                
                EventLoisir Ev = new EventLoisir (id,labelle,description,lieu,dateDebut,dateFin,domaine,nbParticipant,true,lng,lat,image);
                res.add(Ev);
            }
            rs.close();
            } 
        catch (Exception e) {
            System.out.println("get all event non apptouve dans admineventService, "+e.getMessage());
        }
        return res;
    }
    
    public ArrayList<Formation> getAllFormationNonApprouve(){
        ArrayList<Formation> res = new ArrayList<Formation>();
        try {
            Statement stmt = cnx.createStatement();
            String sql = "SELECT * FROM formation where etat=0;";
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                int id = rs.getInt("id");
                String labelle = rs.getString("labelle");
                String lieu = rs.getString("lieu");
                String domaine = rs.getString("domaine");
                String description=rs.getString("description");
                Timestamp dateDebut=rs.getTimestamp("date_debut");
                Timestamp dateFin=rs.getTimestamp("date_fin");
                float montant=rs.getFloat("montant");
                long lat=rs.getLong("lat");
                long lng=rs.getLong("lng");
                String image=rs.getString("image");
                
                Formation F = new Formation (id,labelle,description,lieu,dateDebut,dateFin,domaine,montant,true,lng,lat,image);
                res.add(F);
            }
            rs.close();
            } 
        catch (Exception e) {
            System.out.println("get all formation non apptouve dans admineventService, "+e.getMessage());
        }
        return res;
    }

}
