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
import pidev_java.entities.Freelancer;
import pidev_java.interfaces.UtilisateurInterface;
import pidev_java.utils.MaConnection;

/**
 *
 * @author seifeddine
 */
public class FreelancerService implements UtilisateurInterface<Freelancer>{
    Connection cnx = MaConnection.getInstance().getCnx();
    
    @Override 
    public boolean add(Freelancer entity){
        try{
            String test="SELECT * FROM Freelancer WHERE email="+"\""+entity.getEmail()+"\"";
            Statement st=cnx.createStatement();
            ResultSet rst=st.executeQuery(test);
            if(rst.next()==false){
                System.out.println("bbbbbbbbbbbbbbb");
                    System.out.println("aaaaaaaaaaa");
                String req ="INSERT INTO Freelancer(nom,adresse,email,mot_de_passe,"
                    + "photo_de_profile,prenom,sexe,competences,langues,"
                    + "compte_facebook,compte_linkedin,compte_twitter,"
                    + "views_nb,etat,date_creation) "
                    +"VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
                    PreparedStatement pst=cnx.prepareStatement(req);
                    pst.setString(1,entity.getNom());
                    pst.setString(2,entity.getAdresse());
                    pst.setString(3,entity.getEmail());
                    pst.setString(4,entity.getMot_de_passe());
                    pst.setString(5,entity.getPhoto_de_profile());
                    pst.setString(6,entity.getPrenom());
                    pst.setString(7,entity.getSexe());
                    pst.setString(8,entity.getCompetences());
                    pst.setString(9,entity.getLangues());
                    pst.setString(10,entity.getCompte_facebook());
                    pst.setString(11,entity.getCompte_linkedin());
                    pst.setString(12,entity.getCompte_twitter());
                    pst.setInt(13,entity.getViews_nb());
                    pst.setInt(14,entity.getEtat());
                    pst.setString(15,entity.getDate_creation());

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
    
    //@Override 
    public Freelancer getFreelancer(String email){
        Freelancer f= new Freelancer();
        String req="SELECT * FROM Freelancer WHERE email="+"\"" + email + "\"";
        
        try{
            if(req!=null){
                Statement st=cnx.createStatement();
                ResultSet rst=st.executeQuery(req);
                if(rst.next()){
                    f.setId(rst.getInt("id"));
                    f.setNom(rst.getString("nom"));
                    f.setPrenom(rst.getString("prenom"));
                    f.setEmail(rst.getString("email"));
                    f.setMot_de_passe(rst.getString("mot_de_passe"));
                    f.setAdresse(rst.getString("adresse"));
                    f.setPhoto_de_profile(rst.getString("photo_de_profile"));
                    f.setSexe(rst.getString("sexe"));
                    f.setCompetences(rst.getString("competences"));
                    f.setLangues(rst.getString("langues"));
                    f.setCompte_facebook(rst.getString("compte_facebook"));
                    f.setCompte_linkedin(rst.getString("compte_linkedin"));
                    f.setCompte_twitter(rst.getString("compte_twitter"));
                    f.setViews_nb(rst.getInt("views_nb"));
                    f.setEtat(rst.getInt("etat"));
                    f.setDate_creation(rst.getString("date_creation"));
                }
            }
            
        }catch(SQLException ex){
            Logger.getLogger(MaConnection.class.getName()).log(Level.SEVERE,null,ex);
        }
        return (f);
    }
    
    public boolean UpdateFreelancer(String nom,String prenom,String email,String adresse
            ,String compte_linkedin,String compte_facebook,String compte_twitter,
            String sexe,String competences,String langues,String photo_de_profile){
        Freelancer fe=  Freelancer.getInstance();
        System.err.println(nom+" , "+prenom+" , "+email+" , "+adresse+" , "+compte_linkedin+" , "
                +compte_facebook+" , "+compte_twitter+" , "+sexe+" , "+competences+" , "+
                langues+" , "+photo_de_profile);
        String req="UPDATE Freelancer "
                + "SET nom="+"\"" + nom + "\""+", prenom="+"\"" + prenom + "\""+", email="+"\"" + email + "\""
                +", adresse="+"\"" + adresse + "\""+", compte_linkedin="+"\"" + compte_linkedin + "\""
                +", compte_facebook="+"\"" + compte_facebook + "\""+", compte_twitter="+"\"" + compte_twitter + "\""
                +", sexe="+"\"" + sexe + "\""+", competences="+"\"" + competences + "\""+", langues="+"\"" + langues + "\""
                +", photo_de_profile="+"\"" + photo_de_profile + "\""
                + " WHERE email="+"\"" + fe.getEmail() + "\"";
        
        try{
            PreparedStatement pst=cnx.prepareStatement(req);
            pst.executeUpdate();
            return (true);
        }catch(SQLException ex){
            Logger.getLogger(MaConnection.class.getName()).log(Level.SEVERE,null,ex);
        }
        return (false);
    }

}
