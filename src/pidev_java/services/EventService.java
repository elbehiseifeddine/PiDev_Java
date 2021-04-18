/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev_java.services;

import pidev_java.entities.EventLoisir;
import pidev_java.entities.Formation;


import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import pidev_java.interfaces.IServiceEvent;
import pidev_java.utils.MaConnection;

/**
 *
 * @author ASUS
 */
public class EventService implements IServiceEvent<EventLoisir> {
     private Connection con = MaConnection.getInstance().getCnx();

    @Override
    public void Ajouter(EventLoisir e) {
         try{
		PreparedStatement preparedStmt = con.prepareStatement("insert into event_loisir (description,date_debut,date_fin,lieu,domaine,imagee,nb_participant,labelle,etat,lng,lat,id_fr_id) values (?,?,?,?,?,?,?,?,?,?,?,?)");
		preparedStmt.setString(1,e.getDescription());
		preparedStmt.setTimestamp(2,e.getDateDebut());
                preparedStmt.setTimestamp(3,e.getDateFin());
		preparedStmt.setString(4,e.getLieu());
		preparedStmt.setString(5,e.getDomaine());
                preparedStmt.setString(6,e.getImageE());
                preparedStmt.setFloat(7, e.getNbParticipant());
                preparedStmt.setString(8, e.getLabelle());
                preparedStmt.setBoolean(9, e.isEtat());
                preparedStmt.setLong(10, 1);
                preparedStmt.setLong(11, 1);
                preparedStmt.setInt(12, 1);
                preparedStmt.execute();
                System.out.println("Insertion Avec Succes");
            }
        catch (Exception ex) { 
                ex.printStackTrace();
	           } 
    }

    @Override
    public void Supprimer(EventLoisir e) {
          try {
            PreparedStatement preparedStmt = con.prepareStatement(" delete from event_loisir where id= ?");
	    preparedStmt.setInt(1,e.getId());
            preparedStmt.executeUpdate();
            } 
        catch (Exception ex) {
	    System.err.println(ex.getMessage());
            }
    }

    @Override
    public void Modifier(EventLoisir e) {
          try {
            PreparedStatement preparedStmt = con.prepareStatement("update event_loisir set  description=? ,date_debut=?,date_fin=? ,lieu=? ,labelle=? ,domaine=? ,nb_participant=? ,imagee=?  where id=?");
	    preparedStmt.setString(1,e.getDescription());
	    preparedStmt.setTimestamp(2,e.getDateDebut());
	    preparedStmt.setTimestamp(3,e.getDateFin());
	    preparedStmt.setString(4,e.getLieu());
	    preparedStmt.setString(5,e.getLabelle());
            preparedStmt.setString(6, e.getDomaine());
            preparedStmt.setFloat(7, e.getNbParticipant());
            preparedStmt.setString(8, e.getImageE());
            preparedStmt.setInt(9, e.getId());
            preparedStmt.execute();
            } catch (Exception ex) {
	    System.err.println(ex.getMessage());
            }
    }

    @Override
    public ArrayList<EventLoisir> Lister() {
         ArrayList<EventLoisir> res = new ArrayList<EventLoisir>();
        try {
            Statement stmt = con.createStatement();
            String sql = "SELECT * FROM event_loisir";
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
            System.err.println(e.getMessage());
        }
        return res;
    }

    
    
}
