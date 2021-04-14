/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev_java.services;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import pidev_java.utils.MaConnection;

/**
 *
 * @author ahmed
 */
public class ReclamationService {

    Connection cnx = MaConnection.getInstance().getCnx();

    public int countReclamationNonApprouve() {
        int CountReclamationNonApprouve = 0;
        System.out.println("from heeere");
        try {
            
            

            String req ="SELECT COUNT(*) FROM reclamation WHERE etat=0;";
            System.out.println(req);
            Statement st2 = cnx.createStatement();

            ResultSet rs2 = st2.executeQuery(req);
            rs2.first();
                System.out.println(rs2.getInt(1));
            CountReclamationNonApprouve = rs2.getInt(1);
            rs2.close();
            
        } catch (SQLException ex) {
            System.out.println("this is count relamation non approuve || Connexion à la base de données impossible , " + ex.getMessage());
        } 
        return CountReclamationNonApprouve;
        
    }
    
    public int CountReclamation(){
        int CountReclamation = 0;
        try {
            
            

            String req ="SELECT COUNT(*) FROM reclamation;";
            Statement st2 = cnx.createStatement();

            ResultSet rs2ResultSet = st2.executeQuery(req);
            rs2ResultSet.first();
                System.out.println(rs2ResultSet.getInt(1));
            CountReclamation = rs2ResultSet.getInt(1);
            rs2ResultSet.close();
            
        } catch (SQLException ex) {
            System.out.println("this is count relamation non approuve || Connexion à la base de données impossible , " + ex.getMessage());
        } 
        return CountReclamation;
    }
    
    

}
