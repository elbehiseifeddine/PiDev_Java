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
import java.util.prefs.Preferences;
import pidev_java.entities.Societe;
import pidev_java.interfaces.UtilisateurInterface;
import pidev_java.utils.MaConnection;

/**
 *
 * @author seifeddine
 */
public class SocieteService implements UtilisateurInterface<Societe> {
    Connection cnx = MaConnection.getInstance().getCnx();
    
    @Override 
    public boolean add(Societe entity){
        try{
            String testS = "SELECT * FROM Societe WHERE email=" + "\"" + entity.getEmail() + "\"";
            String testF = "SELECT * FROM Freelancer WHERE email=" + "\"" + entity.getEmail() + "\"";
            String testA = "SELECT * FROM admin WHERE login ='"+entity.getEmail()+"' ;";
            Statement stS = cnx.createStatement();
            Statement stF = cnx.createStatement();
            Statement stA = cnx.createStatement();
            ResultSet rstS = stS.executeQuery(testS);
            ResultSet rstF = stF.executeQuery(testF);
            ResultSet rstA = stA.executeQuery(testA);
            if (rstS.next() == false && rstF.next() == false && rstA.next() == false) {
                String req ="INSERT INTO Societe(nom,adresse,email,mot_de_pass,"
                    + "photo_de_profile,status_juridique,"
                    + "views_nb,etat,date_creation) "
                    +"VALUES(?,?,?,?,?,?,?,?,?)";
                PreparedStatement pst=cnx.prepareStatement(req);
                pst.setString(1,entity.getNom());
                pst.setString(2,entity.getAdresse());
                pst.setString(3,entity.getEmail());
                pst.setString(4,entity.getMot_de_pass());
                pst.setString(5,entity.getPhoto_de_profile());
                pst.setString(6,entity.getStatus_juridique());
                pst.setInt(7,entity.getViews_nb());
                pst.setInt(8,entity.getEtat());
                pst.setString(9,entity.getDate_creation());


                pst.executeUpdate();
                return(true);
            }else{
                System.out.println("email exist");
                return(false);
            }
            
        }catch(SQLException ex){
            Logger.getLogger(MaConnection.class.getName()).log(Level.SEVERE,null,ex);
        }
        return(false);
    }
    
    public Societe getSociete(String email){
        Societe s= new Societe();
        String req="SELECT * FROM Societe WHERE email="+"\"" + email + "\"";
        Preferences pref= Preferences.userRoot().node(this.getClass().getName());
        try{
            if(req!=null){
                System.err.println("bbbbbbbbbbbbb");
                Statement st=cnx.createStatement();
                ResultSet rst=st.executeQuery(req);
                System.err.println("aaaaaaaaaaaaaa");
                if(rst.next()){
                    s.setId(rst.getInt("id"));
                    s.setNom(rst.getString("nom"));
                    s.setEmail(rst.getString("email"));
                    s.setMot_de_pass(rst.getString("mot_de_pass"));
                    s.setAdresse(rst.getString("adresse"));
                    s.setStatus_juridique(rst.getString("status_juridique"));
                    s.setPhoto_de_profile(rst.getString("photo_de_profile"));
                    s.setViews_nb(rst.getInt("views_nb"));
                    s.setEtat(rst.getInt("etat"));
                    s.setDate_creation(rst.getString("date_creation"));
                   
            }else{
                System.err.println("bbccccccccbbbbbb");
            }
                }
            //while(rst.next()){
             //   Societe p= new Societe();
             //   p.setId (rst.getInt("id"));
             //   p.setNom(rst.getString("nom"));
             //   personnes.add(p);
            //}
        }catch(SQLException ex){
            Logger.getLogger(MaConnection.class.getName()).log(Level.SEVERE,null,ex);
        }
        return(s);
    }
    
    public boolean UpdateSociete(String nom,String email,String adresse,
            String status_juridique,String photo_de_profile){
        Societe se=  Societe.getInstance();
        
        String req="UPDATE Societe "
                + "SET nom="+"\"" + nom + "\""+", email="+"\"" + email + "\""
                +", adresse="+"\"" + adresse + "\""+", status_juridique="+"\"" + status_juridique + "\""
                +", photo_de_profile="+"\"" + photo_de_profile + "\""
                + " WHERE email="+"\"" + se.getEmail() + "\"";
        
        try{
            PreparedStatement pst=cnx.prepareStatement(req);
            pst.executeUpdate();
            return (true);
        }catch(SQLException ex){
            Logger.getLogger(MaConnection.class.getName()).log(Level.SEVERE,null,ex);
        }
        return (false);
    }

    public ArrayList<Societe> getAll() {
        
        ArrayList<Societe> ListeSocietes = new ArrayList<>();
        String req = "SELECT * FROM societe";
        try {

            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);

            while (rs.next()) {
                Societe a = new Societe();
                a.setId(rs.getInt("id"));
                a.setNom(rs.getString("nom"));
                a.setStatus_juridique(rs.getString("status_juridique"));
                a.setEmail(rs.getString("email"));
                a.setAdresse(rs.getString("adresse"));
                a.setEtat(rs.getInt("etat"));
                a.setDate_creation(rs.getString("date_creation"));
                
                ListeSocietes.add(a);
                
            }
            rs.close();
        } catch (SQLException ex) {
            System.out.println("Connexion à la base de données impossible , " + ex.getMessage());
        }
        return ListeSocietes;
    }

    public void ActivateSociete(int id){
        
        try {
            String req = "UPDATE societe SET etat= 0"
                    + " WHERE id = "+id;
            PreparedStatement pst=cnx.prepareStatement(req);
            pst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(SocieteService.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public void DeactivateSociete(int id){
        
        try {
            String req = "UPDATE societe SET etat= 1"
                    + " WHERE id = "+id;
            PreparedStatement pst=cnx.prepareStatement(req);
            pst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(SocieteService.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    
     public Societe FindparID(int id){
        Societe s= new Societe();
        String req="SELECT * FROM Societe WHERE id="+id;
        
        try{
            if(req!=null){
             
                Statement st=cnx.createStatement();
                ResultSet rst=st.executeQuery(req);
             
                if(rst.next()){
                    s.setId(rst.getInt("id"));
                    s.setNom(rst.getString("nom"));
                    s.setEmail(rst.getString("email"));
                    s.setMot_de_pass(rst.getString("mot_de_pass"));
                    s.setAdresse(rst.getString("adresse"));
                    s.setStatus_juridique(rst.getString("status_juridique"));
                    s.setPhoto_de_profile(rst.getString("photo_de_profile"));
                    s.setViews_nb(rst.getInt("views_nb"));
                    s.setEtat(rst.getInt("etat"));
                    s.setDate_creation(rst.getString("date_creation"));
                   
            }
                }
           
        }catch(SQLException ex){
            Logger.getLogger(MaConnection.class.getName()).log(Level.SEVERE,null,ex);
        }
        return(s);
    }
    
    
    
    

    
}
