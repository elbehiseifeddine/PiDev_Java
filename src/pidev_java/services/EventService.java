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
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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
                preparedStmt.setDouble(10, e.getLng());
                preparedStmt.setDouble(11, e.getLat());
                preparedStmt.setInt(12, 1);
                preparedStmt.execute();
                System.out.println("Insertion Avec Succes");
            }
        catch (Exception ex) { 
                ex.printStackTrace();
	           } 
    }
    
     public void Ajouter(EventLoisir e,int idu,String typeU) {
         if(typeU.equals("freelancer")){
              try{
		PreparedStatement preparedStmt = con.prepareStatement("insert into event_loisir (description,date_debut,date_fin,lieu,domaine,imagee,nb_participant,labelle,etat,lng,lat,id_fr_id,id) values (?,?,?,?,?,?,?,?,?,?,?,?,?)");
		preparedStmt.setString(1,e.getDescription());
		preparedStmt.setTimestamp(2,e.getDateDebut());
                preparedStmt.setTimestamp(3,e.getDateFin());
		preparedStmt.setString(4,e.getLieu());
		preparedStmt.setString(5,e.getDomaine());
                preparedStmt.setString(6,e.getImageE());
                preparedStmt.setFloat(7, e.getNbParticipant());
                preparedStmt.setString(8, e.getLabelle());
                preparedStmt.setBoolean(9, e.isEtat());
                preparedStmt.setDouble(10, e.getLng());
                preparedStmt.setDouble(11, e.getLat());
                preparedStmt.setInt(12, idu);
                preparedStmt.setInt(13, e.getId());
                preparedStmt.execute();
                System.out.println("Insertion Avec Succes");
            }
        catch (Exception ex) { 
                ex.printStackTrace();
	           } 
             
         }
         else if(typeU.equals("societe")){
              try{
		PreparedStatement preparedStmt = con.prepareStatement("insert into event_loisir (description,date_debut,date_fin,lieu,domaine,imagee,nb_participant,labelle,etat,lng,lat,id_so_id,id) values (?,?,?,?,?,?,?,?,?,?,?,?,?)");
		preparedStmt.setString(1,e.getDescription());
		preparedStmt.setTimestamp(2,e.getDateDebut());
                preparedStmt.setTimestamp(3,e.getDateFin());
		preparedStmt.setString(4,e.getLieu());
		preparedStmt.setString(5,e.getDomaine());
                preparedStmt.setString(6,e.getImageE());
                preparedStmt.setFloat(7, e.getNbParticipant());
                preparedStmt.setString(8, e.getLabelle());
                preparedStmt.setBoolean(9, e.isEtat());
                preparedStmt.setDouble(10, e.getLng());
                preparedStmt.setDouble(11, e.getLat());
                preparedStmt.setInt(12, idu);
                preparedStmt.setInt(13, e.getId());
                preparedStmt.execute();
                System.out.println("Insertion Avec Succes");
            }
        catch (Exception ex) { 
                ex.printStackTrace();
	           } 
             
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
            PreparedStatement preparedStmt = con.prepareStatement("update event_loisir set  description=? ,date_debut=?,date_fin=? ,lieu=? ,labelle=? ,domaine=? ,nb_participant=? ,imagee=? ,lng=? ,lat=?  where id=?");
	    preparedStmt.setString(1,e.getDescription());
	    preparedStmt.setTimestamp(2,e.getDateDebut());
	    preparedStmt.setTimestamp(3,e.getDateFin());
	    preparedStmt.setString(4,e.getLieu());
	    preparedStmt.setString(5,e.getLabelle());
            preparedStmt.setString(6, e.getDomaine());
            preparedStmt.setFloat(7, e.getNbParticipant());
            preparedStmt.setString(8, e.getImageE());
            preparedStmt.setDouble(9, e.getLng());
            preparedStmt.setDouble(10, e.getLat());
            preparedStmt.setInt(11, e.getId());
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
    
    
    
     public ArrayList<EventLoisir> Lister(int idu,String typeU) {
         ArrayList<EventLoisir> res = new ArrayList<EventLoisir>();
         if(typeU.equals("freelancer")){
              try {
            Statement stmt = con.createStatement();
            String sql = "SELECT * FROM event_loisir where id_fr_id !="+idu;
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
             
         }
         else if(typeU.equals("societe")){
                try {
            Statement stmt = con.createStatement();
            String sql = "SELECT * FROM event_loisir where id_so_id !="+idu;
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
             
         }
       
        return res;
    }
    
    
      public ArrayList<EventLoisir> ListerparU(int idu,String typeU) {
         ArrayList<EventLoisir> res = new ArrayList<EventLoisir>();
         if(typeU.equals("freelancer")){
             try {
            Statement stmt = con.createStatement();
            String sql = "SELECT * FROM event_loisir where id_fr_id="+idu;
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
         }
         else{
             try {
            Statement stmt = con.createStatement();
            String sql = "SELECT * FROM event_loisir where id_so_id="+idu;
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
             
         }
        
        return res;
    }

    
    
    
     public ArrayList<EventLoisir> ListerParDate() {
         ArrayList<EventLoisir> res = new ArrayList<EventLoisir>();
        
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");  
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime after = LocalDateTime.now().plusDays(7);
         String d1=dtf.format(now);  
         String d2=dtf.format(after); 
        try {
            Statement stmt = con.createStatement();
            String sql = "SELECT * FROM event_loisir where date_debut between '"+d1+"' and '"+d2+"'";
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

    public boolean estUnEntier(String text) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
     public int maxId(){
        int max = 0;
         try {
             Statement stmt = con.createStatement();
             String sql = "SELECT MAX(id) FROM event_loisir;";
             ResultSet rs6 = stmt.executeQuery(sql);
             rs6.first();
             max=rs6.getInt(1);
             rs6.close();
         } catch (SQLException ex) {
             System.out.println("this is max Id in eventLoisirService");         }
        return max;
    }

    int countEventsLoisirNonApprouve() {
int count = 0;
         try {
             Statement stmt = con.createStatement();
             String sql = "SELECT COUNT(*) FROM event_loisir where etat = 0;";
             ResultSet rs6 = stmt.executeQuery(sql);
             rs6.first();
             count=rs6.getInt(1);
             rs6.close();
         } catch (SQLException ex) {
             System.out.println(" this is Count event loisir dans eventLoisirService");         }
        return count;    }
    
}
