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
import pidev_java.interfaces.IService;
import pidev_java.entities.Personne;
import pidev_java.utils.MaConnection;

/**
 *
 * @author seifeddine
 */
public class PersonneService implements IService<Personne>{
    Connection cnx = MaConnection.getInstance().getCnx();
    
    @Override 
    public void add(Personne entity){
        try{
            String req ="INSERT INTO Personne(nom,prenom) "+"VALUES(?,?)";
            PreparedStatement pst=cnx.prepareStatement(req);
            pst.setString(1,entity.getNom());
            pst.setString(2,entity.getPrenom());
            
            pst.executeUpdate();
        }catch(SQLException ex){
            Logger.getLogger(MaConnection.class.getName()).log(Level.SEVERE,null,ex);
        }
    }
    
    @Override 
    public ArrayList<Personne> getAll(){
        ArrayList<Personne> personnes= new ArrayList<>();
        String req="SELECT * FROM Personne";
        try{
            Statement st=cnx.createStatement();
            ResultSet rst=st.executeQuery(req);
            
            while(rst.next()){
                Personne p= new Personne();
                p.setId (rst.getInt("id"));
                p.setNom(rst.getString("nom"));
                p.setPrenom(rst.getString("prenom"));
                personnes.add(p);
            }
            
        }catch(SQLException ex){
            Logger.getLogger(MaConnection.class.getName()).log(Level.SEVERE,null,ex);
        }
        return (personnes);
    }
    
}
