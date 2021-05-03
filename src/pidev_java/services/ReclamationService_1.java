/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev_java.services;

import pidev_java.entities.Reclamation_1;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import pidev_java.entities.Reclamation;
import pidev_java.interfaces.IReclamationService;
import pidev_java.utils.MaConnection;

/**
 *
 * @author Ayari Ghaith
 */
public class ReclamationService_1 implements IReclamationService<Reclamation_1>{
  private Connection con = MaConnection.getInstance().getCnx();
    @Override
    public void Ajouter(Reclamation_1 e) {
 try{
		PreparedStatement preparedStmt = con.prepareStatement("insert into reclamation (type,texte_reclamation,date_reclamation,email_utilisateur,nom_utilisateur,etat,id) values (?,?,?,?,?,?,?)");
		
                preparedStmt.setString(1,e.getType());
                 preparedStmt.setString(2,e.getTextReclamation());
                preparedStmt.setString(3,e.getDateReclamation());
		preparedStmt.setString(4,e.getEmailUser());
		preparedStmt.setString(5,e.getNomUser());
                preparedStmt.setBoolean(6, e.isEtat());
                preparedStmt.setInt(7, e.getId());
                
              
              
                preparedStmt.execute();
                System.out.println("Insertion Avec Succes");
            }
        catch (Exception ex) { 
                ex.printStackTrace();
	           }     }
    public int maxId(){
        int max = 0;
         try {
             Statement stmt = con.createStatement();
             String sql = "SELECT MAX(id) FROM reclamation;";
             ResultSet rs6 = stmt.executeQuery(sql);
             rs6.first();
             max=rs6.getInt(1);
             rs6.close();
         } catch (SQLException ex) {
              System.err.println(ex.getMessage());
         }
        return max;
    }

    @Override
    public void Supprimer(Reclamation_1 e) {
 try {
            PreparedStatement preparedStmt = con.prepareStatement(" delete from reclamation where id= ?");
	    preparedStmt.setInt(1,e.getId());
            preparedStmt.executeUpdate();
            } 
        catch (Exception ex) {
	    System.err.println(ex.getMessage());
            }    }

    @Override
    public void Modifier(Reclamation_1 e) {
 try {
            PreparedStatement preparedStmt = con.prepareStatement("update reclamation set  type=? ,texte_reclamation=?  where id=?");
	    preparedStmt.setString(1,e.getType());
	   preparedStmt.setString(2,e.getTextReclamation());
            preparedStmt.setInt(3,e.getId());
	   
            preparedStmt.execute();
            } catch (Exception ex) {
	    System.err.println(ex.getMessage());
            }    }

    @Override
    public ArrayList<Reclamation_1> Lister() {
 ArrayList<Reclamation_1> res = new ArrayList<Reclamation_1>();
        try {
            Statement stmt = con.createStatement();
            String sql = "SELECT * FROM reclamation";
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                
                int id = rs.getInt("id");
                String type = rs.getString("type");
                String text = rs.getString("texte_reclamation");
                String date = rs.getString("date_reclamation");
                String email=rs.getString("email_utilisateur");
                String nom=rs.getString("nom_utilisateur");
               
                
                Reclamation_1 R = new Reclamation_1 (id,type,text,date,email,nom,true);
                res.add(R);
            }
            rs.close();
            } 
        catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return res;    }
    
}