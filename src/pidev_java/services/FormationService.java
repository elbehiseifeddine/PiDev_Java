/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev_java.services;

import pidev_java.entities.Formation;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
public class FormationService implements IServiceEvent<Formation>{
    private Connection con = MaConnection.getInstance().getCnx();

    @Override
    public void Ajouter(Formation e) {
        try{
		PreparedStatement preparedStmt = con.prepareStatement("insert into formation (description,date_debut,date_fin,lieu,domaine,montant,image,labelle,etat,lng,lat,id_fr_id) values (?,?,?,?,?,?,?,?,?,?,?,?)");
		
                preparedStmt.setString(1,e.getDescription());
                 preparedStmt.setTimestamp(2,e.getDateDebut());
                preparedStmt.setTimestamp(3,e.getDateFin());
		preparedStmt.setString(4,e.getLieu());
		preparedStmt.setString(5,e.getDomaine());
                
                preparedStmt.setFloat(6, e.getMontant());
                preparedStmt.setString(7,e.getImageF());
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
    
    
     public void Ajouter(Formation e,int idu,String typeu) {
         if(typeu.equals("freelancer")){
              try{
		PreparedStatement preparedStmt = con.prepareStatement("insert into formation (description,date_debut,date_fin,lieu,domaine,montant,image,labelle,etat,lng,lat,id_fr_id) values (?,?,?,?,?,?,?,?,?,?,?,?)");
		
                preparedStmt.setString(1,e.getDescription());
                 preparedStmt.setTimestamp(2,e.getDateDebut());
                preparedStmt.setTimestamp(3,e.getDateFin());
		preparedStmt.setString(4,e.getLieu());
		preparedStmt.setString(5,e.getDomaine());
                
                preparedStmt.setFloat(6, e.getMontant());
                preparedStmt.setString(7,e.getImageF());
                preparedStmt.setString(8, e.getLabelle());
                preparedStmt.setBoolean(9, e.isEtat());
                preparedStmt.setDouble(10, e.getLng());
                preparedStmt.setDouble(11, e.getLat());
                preparedStmt.setInt(12, idu);
              
                preparedStmt.execute();
                System.out.println("Insertion Avec Succes");
            }
        catch (Exception ex) { 
                ex.printStackTrace();
	           } 
             
         }
         else if(typeu.equals("societe")){
              try{
		PreparedStatement preparedStmt = con.prepareStatement("insert into formation (description,date_debut,date_fin,lieu,domaine,montant,image,labelle,etat,lng,lat,id_so_id) values (?,?,?,?,?,?,?,?,?,?,?,?)");
		
                preparedStmt.setString(1,e.getDescription());
                 preparedStmt.setTimestamp(2,e.getDateDebut());
                preparedStmt.setTimestamp(3,e.getDateFin());
		preparedStmt.setString(4,e.getLieu());
		preparedStmt.setString(5,e.getDomaine());
                
                preparedStmt.setFloat(6, e.getMontant());
                preparedStmt.setString(7,e.getImageF());
                preparedStmt.setString(8, e.getLabelle());
                preparedStmt.setBoolean(9, e.isEtat());
                preparedStmt.setDouble(10, e.getLng());
                preparedStmt.setDouble(11, e.getLat());
                preparedStmt.setInt(12, idu);
              
                preparedStmt.execute();
                System.out.println("Insertion Avec Succes");
            }
        catch (Exception ex) { 
                ex.printStackTrace();
	           } 
             
         }
       
    }
    

    @Override
    public void Supprimer(Formation e) {
         try {
            PreparedStatement preparedStmt = con.prepareStatement(" delete from formation where id= ?");
	    preparedStmt.setInt(1,e.getId());
            preparedStmt.executeUpdate();
            } 
        catch (Exception ex) {
	    System.err.println(ex.getMessage());
            }
    }

    @Override
    public void Modifier(Formation e) {
        try {
            PreparedStatement preparedStmt = con.prepareStatement("update formation set  description=? ,date_debut=?,date_fin=? ,lieu=? ,labelle=? ,domaine=? ,montant=? ,image=? ,lng=? ,lat=?  where id=?");
	    preparedStmt.setString(1,e.getDescription());
	   preparedStmt.setTimestamp(2,e.getDateDebut());
                preparedStmt.setTimestamp(3,e.getDateFin());
	    preparedStmt.setString(4,e.getLieu());
	    preparedStmt.setString(5,e.getLabelle());
            preparedStmt.setString(6, e.getDomaine());
            preparedStmt.setFloat(7, e.getMontant());
            preparedStmt.setString(8, e.getImageF());
            preparedStmt.setDouble(9, e.getLng());
             preparedStmt.setDouble(10, e.getLat());
            preparedStmt.setInt(11,e.getId());
            preparedStmt.execute();
            } catch (Exception ex) {
	    System.err.println(ex.getMessage());
            }
    }

    @Override
    public ArrayList<Formation> Lister() {
        ArrayList<Formation> res = new ArrayList<Formation>();
        try {
            Statement stmt = con.createStatement();
            String sql = "SELECT * FROM formation";
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
            System.err.println(e.getMessage());
        }
        return res;
    }
    
    
        public ArrayList<Formation> Lister(int idu,String type) {
        ArrayList<Formation> res = new ArrayList<Formation>();
        if(type.equals("freelancer")){
            try {
            Statement stmt = con.createStatement();
            String sql = "SELECT * FROM formation where id_fr_id !="+idu+" or id_fr_id is null";
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
            System.err.println(e.getMessage());
        }
            
        }
        else if(type.equals("societe")){
      
              try {
            Statement stmt = con.createStatement();
            String sql = "SELECT * FROM formation where id_so_id !="+idu+" or  id_so_id is null";
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
            System.err.println(e.getMessage());
        }
            
        }
        
        return res;
    }
    
    
    
    
    
    
    public boolean estUnEntier(String chaine) {
		try {
			Integer.parseInt(chaine);
		} catch (NumberFormatException e){
			return false;
		}
 
		return true;
	}
    
    
        
    public ArrayList<Formation> ListerparU(int idu,String type) {
        ArrayList<Formation> res = new ArrayList<Formation>();
        if(type.equals("freelancer")){
             try {
            Statement stmt = con.createStatement();
            String sql = "SELECT * FROM formation where id_fr_id="+idu;
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
            System.err.println(e.getMessage());
        }
            
        }
        else if(type.equals("societe")){
             try {
            Statement stmt = con.createStatement();
            String sql = "SELECT * FROM formation where id_so_id="+idu;
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
            System.err.println(e.getMessage());
        }
            
        }
        
       
        return res;
    }
    
    
     public Formation FindParId(int idf) {
       Formation F=new Formation();
        try {
            Statement stmt = con.createStatement();
            String sql = "SELECT * FROM formation where id="+idf;
            ResultSet rs = stmt.executeQuery(sql);
            while(rs.next()){
                int id = rs.getInt("id");
                System.out.println(id+" hadha id");
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
                
                 F = new Formation (id,labelle,description,lieu,dateDebut,dateFin,domaine,montant,true,lng,lat,image);
                
            }
            rs.close();
            } 
        catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return F;
    }
     
     public ArrayList<Formation> ListerParDate(){
        ArrayList<Formation> result = new ArrayList<Formation>();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");  
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime after = LocalDateTime.now().plusDays(7);
         String d1=dtf.format(now);  
         String d2=dtf.format(after); 
        try {
            Statement stmt = con.createStatement();
            String sql = "SELECT * FROM formation where date_debut between '"+d1+"' and '"+d2+"'";
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
                double lat=rs.getDouble("lat");
                double lng=rs.getDouble("lng");
                String image=rs.getString("image");
                
                Formation F = new Formation (id,labelle,description,lieu,dateDebut,dateFin,domaine,montant,true,lng,lat,image);
                result.add(F);
            }
            rs.close();
            } 
        catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return result;
        
    }

   
}
