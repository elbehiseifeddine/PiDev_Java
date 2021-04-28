///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package pidev_java.services;
//
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.Statement;
//import java.sql.Timestamp;
//import java.util.ArrayList;
//import pidev_java.entities.EventLoisir;
//import pidev_java.entities.Formation;
//import pidev_java.entities.Freelancer;
//import pidev_java.entities.Participant;
//import pidev_java.entities.Societe;
//import pidev_java.interfaces.IServiceEvent;
//import pidev_java.utils.MaConnection;
//
///**
// *
// * @author ASUS
// */
//public class ParticipantService implements IServiceEvent<Participant>{
//private Connection con = MaConnection.getInstance().getCnx();
//    @Override
//    public void Ajouter(Participant e) {
//        try{
//            if(e.getTypeU().equals("freelancer")){
//                if(e.getTypeE().equals("formation")){
//                PreparedStatement preparedStmt = con.prepareStatement("insert into  participant (id_f_id,id_fo_id,type_u,type_e) values (?,?,?,?)");
//                    preparedStmt.setInt(1,e.getF().getId());
//                 preparedStmt.setInt(2,e.getFormation().getId());
//                preparedStmt.setString(3,e.getTypeU());
//		preparedStmt.setString(4,e.getTypeE());
//                preparedStmt.execute();
//                System.out.println("Insertion Avec Succes");
//                
//                
//                
//                }
//                else{
//                    PreparedStatement preparedStmt = con.prepareStatement("insert into  participant (id_f_id,id_e_id,type_u,type_e) values (?,?,?,?)");
//                    preparedStmt.setInt(1,e.getF().getId());
//                 preparedStmt.setInt(2,e.getEl().getId());
//                preparedStmt.setString(3,e.getTypeU());
//		preparedStmt.setString(4,e.getTypeE());
//                preparedStmt.execute();
//                System.out.println("Insertion Avec Succes");
//                    
//                }
//                
//            }
//           
//            
//            else if(e.getTypeU().equals("societe")){
//                if(e.getTypeE().equals("formation")){
//                PreparedStatement preparedStmt = con.prepareStatement("insert into  participant (id_s_id,id_fo_id,type_u,type_e) values (?,?,?,?)");
//                    preparedStmt.setInt(1,e.getS().getId());
//                 preparedStmt.setInt(2,e.getFormation().getId());
//                preparedStmt.setString(3,e.getTypeU());
//		preparedStmt.setString(4,e.getTypeE());
//                preparedStmt.execute();
//                System.out.println("Insertion Avec Succes");
//                
//                
//                
//                }
//                else{
//                    PreparedStatement preparedStmt = con.prepareStatement("insert into  participant (id_s_id,id_e_id,type_u,type_e) values (?,?,?,?)");
//                    preparedStmt.setInt(1,e.getS().getId());
//                 preparedStmt.setInt(2,e.getEl().getId());
//                preparedStmt.setString(3,e.getTypeU());
//		preparedStmt.setString(4,e.getTypeE());
//                preparedStmt.execute();
//                System.out.println("Insertion Avec Succes");
//                    
//                }
//                
//            }
//            
//     
//            }
//        catch (Exception ex) { 
//                ex.printStackTrace();
//	           } 
//        
//    }
//
//    @Override
//    public void Supprimer(Participant e) {
//         try {
//             if(e.getTypeU().equals("freelancer")){
//                 if(e.getTypeE().equals("formation")){
//                       PreparedStatement preparedStmt = con.prepareStatement(" delete from participant where id_f_id= ? and  id_fo_id=?");
//	    preparedStmt.setInt(1,e.getF().getId());
//            preparedStmt.setInt(2,e.getFormation().getId());
//            preparedStmt.executeUpdate();
//                 }
//                 else if(e.getTypeE().equals("evenement")){
//                     PreparedStatement preparedStmt = con.prepareStatement(" delete from participant where id_f_id= ? and  id_e_id=?");
//	    preparedStmt.setInt(1,e.getF().getId());
//            preparedStmt.setInt(2,e.getEl().getId());
//            preparedStmt.executeUpdate();
//                 }
//             }
//             else if(e.getTypeU().equals("societe")){
//                 if(e.getTypeE().equals("formation")){
//                     PreparedStatement preparedStmt = con.prepareStatement(" delete from participant where id_s_id= ? and  id_fo_id=?");
//	    preparedStmt.setInt(1,e.getF().getId());
//            preparedStmt.setInt(2,e.getFormation().getId());
//            preparedStmt.executeUpdate();
//                 }
//                 else if(e.getTypeE().equals("evenement")){
//                     PreparedStatement preparedStmt = con.prepareStatement(" delete from participant where id_s_id= ? and  id_e_id=?");
//	    preparedStmt.setInt(1,e.getF().getId());
//            preparedStmt.setInt(2,e.getFormation().getId());
//            preparedStmt.executeUpdate();
//                 }
//             }
//             
//             
//            
//            } 
//        catch (Exception ex) {
//	    System.err.println(ex.getMessage());
//            }
//        
//        
//        
//    }
//    
//    
//     public void SupprimerParEvent(String TypeE,int ide) {
//         try {
//           
//                 if(TypeE.equals("formation")){
//                       PreparedStatement preparedStmt = con.prepareStatement(" delete from participant where id_fo_id=? and type_e=?");
//	    preparedStmt.setInt(1,ide);
//            preparedStmt.setString(2,TypeE);
//            preparedStmt.executeUpdate();
//                 }
//                 else if(TypeE.equals("evenement")){
//                     PreparedStatement preparedStmt = con.prepareStatement(" delete from participant where id_e_id=? and type_e=?");
//	    preparedStmt.setInt(1,ide);
//            preparedStmt.setString(2,TypeE);
//            preparedStmt.executeUpdate();
//                 }
//             
//        
//             
//             
//          
//            } 
//        catch (Exception ex) {
//	    System.err.println(ex.getMessage());
//            }
//        
//        
//        
//    }
//
//
//    @Override
//    public void Modifier(Participant e) {
//        
//      
//    }
//
//    
//    public ArrayList<Participant> Lister(String typeU,String typeE,int idu) {
//         ArrayList<Participant> res = new ArrayList<Participant>();
//         String sql="";
//         if(typeU.equals("freelancer")){
//             if(typeE.equals("formation")){
//                  sql = "SELECT * FROM participant where id_f_id="+idu+" and type_e='formation'";
//                 
//             }
//             else{
//                  sql = "SELECT * FROM participant where id_f_id="+idu+" and type_e='evenement'";
//             }
//         }
//         else if(typeU.equals("societe")){
//              if(typeE.equals("formation")){
//                 sql = "SELECT * FROM participant where id_s_id="+idu+" and type_e='formation'";
//                 
//             }
//             else{
//                  sql = "SELECT * FROM participant where id_s_id="+idu+" and type_e='evenement'";
//             }
//             
//         }
//        try {
//            Statement stmt = con.createStatement();
//           
//            ResultSet rs = stmt.executeQuery(sql);
//            while (rs.next()) {
//                int id = rs.getInt("id_p");
//                int idf = rs.getInt("id_f_id");
//                int ids = rs.getInt("id_s_id");
//                int idev = rs.getInt("id_e_id");
//                int idfo=rs.getInt("id_fo_id");
//                String typeUs=rs.getString("type_u");
//                String typeEv=rs.getString("type_e");
//                
//                Formation F=new Formation(idfo);
//                EventLoisir Ev=new EventLoisir(idev);
//                Freelancer Fr=new Freelancer(idf);
//                Societe S=new Societe(ids);
//                Participant P=new Participant(id, typeEv, typeUs, Fr, S, F, Ev);
//                res.add(P);
//            }
//            rs.close();
//            } 
//        catch (Exception e) {
//            System.err.println(e.getMessage());
//        }
//        return res;
//    }
//    
//    
//    
//     public ArrayList<Participant> ListerParEvent(String typeE,int idu) {
//         ArrayList<Participant> res = new ArrayList<Participant>();
//         String sql="";
//         
//             if(typeE.equals("formation")){
//                  sql = "SELECT * FROM participant where id_fo_id="+idu+" and type_e='formation'";
//                 
//             }
//             else{
//                  sql = "SELECT * FROM participant where id_e_id="+idu+" and type_e='evenement'";
//             }
//         
//    
//        try {
//            Statement stmt = con.createStatement();
//           
//            ResultSet rs = stmt.executeQuery(sql);
//            while (rs.next()) {
//                int id = rs.getInt("id_p");
//                int idf = rs.getInt("id_f_id");
//                int ids = rs.getInt("id_s_id");
//                int idev = rs.getInt("id_e_id");
//                int idfo=rs.getInt("id_fo_id");
//                String typeUs=rs.getString("type_u");
//                String typeEv=rs.getString("type_e");
//                
//                Formation F=new Formation(idfo);
//                EventLoisir Ev=new EventLoisir(idev);
//                Freelancer Fr=new Freelancer(idf);
//                Societe S=new Societe(ids);
//                Participant P=new Participant(id, typeEv, typeUs, Fr, S, F, Ev);
//                res.add(P);
//            }
//            rs.close();
//            } 
//        catch (Exception e) {
//            System.err.println(e.getMessage());
//        }
//        return res;
//    }
//
//    @Override
//    public ArrayList<Participant> Lister() {
//       return null;
//    }
//    
//}

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import pidev_java.entities.EventLoisir;
import pidev_java.entities.Formation;
import pidev_java.entities.Freelancer;
import pidev_java.entities.Participant;
import pidev_java.entities.Societe;
import pidev_java.interfaces.IServiceEvent;
import pidev_java.utils.MaConnection;

/**
 *
 * @author ASUS
 */
public class ParticipantService implements IServiceEvent<Participant>{
private Connection con = MaConnection.getInstance().getCnx();
    
    public boolean AjouterP(Participant e) {
        String sql="";
         try {
             if(e.getTypeU().equals("freelancer")){
                 if(e.getTypeE().equals("formation")){
                     sql="select * from participant where id_f_id="+e.getF().getId()+" and id_fo_id="+e.getFormation().getId();
                 }
                 else{
                                          sql="select * from participant where id_f_id="+e.getF().getId()+" and id_e_id="+e.getEl().getId();

                 }
             }
             else{
                  if(e.getTypeE().equals("formation")){
                     sql="select * from participant where id_s_id="+e.getS().getId()+" and id_fo_id="+e.getFormation().getId();
                 }
                 else{
                                          sql="select * from participant where id_s_id="+e.getS().getId()+" and id_e_id="+e.getEl().getId();

                 }
             }
            Statement stmt = con.createStatement();
           
            ResultSet rs = stmt.executeQuery(sql);
                if(rs.next()){
                    return false;
                }
            } 
        catch (Exception ex) {
            System.err.println(ex.getMessage());
        }
       
        try{
            if(e.getTypeU().equals("freelancer")){
                if(e.getTypeE().equals("formation")){
                PreparedStatement preparedStmt = con.prepareStatement("insert into  participant (id_f_id,id_fo_id,type_u,type_e) values (?,?,?,?)");
                    preparedStmt.setInt(1,e.getF().getId());
                 preparedStmt.setInt(2,e.getFormation().getId());
                preparedStmt.setString(3,e.getTypeU());
		preparedStmt.setString(4,e.getTypeE());
                preparedStmt.execute();
                System.out.println("Insertion Avec Succes");
                
                
                
                }
                else{
                    PreparedStatement preparedStmt = con.prepareStatement("insert into  participant (id_f_id,id_e_id,type_u,type_e) values (?,?,?,?)");
                    preparedStmt.setInt(1,e.getF().getId());
                 preparedStmt.setInt(2,e.getEl().getId());
                preparedStmt.setString(3,e.getTypeU());
		preparedStmt.setString(4,e.getTypeE());
                preparedStmt.execute();
                System.out.println("Insertion Avec Succes");
                    
                }
                
            }
           
            
            else if(e.getTypeU().equals("societe")){
                if(e.getTypeE().equals("formation")){
                PreparedStatement preparedStmt = con.prepareStatement("insert into  participant (id_s_id,id_fo_id,type_u,type_e) values (?,?,?,?)");
                    preparedStmt.setInt(1,e.getS().getId());
                 preparedStmt.setInt(2,e.getFormation().getId());
                preparedStmt.setString(3,e.getTypeU());
		preparedStmt.setString(4,e.getTypeE());
                preparedStmt.execute();
                System.out.println("Insertion Avec Succes");
                
                
                
                }
                else{
                    PreparedStatement preparedStmt = con.prepareStatement("insert into  participant (id_s_id,id_e_id,type_u,type_e) values (?,?,?,?)");
                    preparedStmt.setInt(1,e.getS().getId());
                 preparedStmt.setInt(2,e.getEl().getId());
                preparedStmt.setString(3,e.getTypeU());
		preparedStmt.setString(4,e.getTypeE());
                preparedStmt.execute();
                System.out.println("Insertion Avec Succes");
                    
                }
                
            }
            
     
            }
        catch (Exception ex) { 
                ex.printStackTrace();
	           } 
        return true;
        
    }

    @Override
    public void Supprimer(Participant e) {
         try {
             if(e.getTypeU().equals("freelancer")){
                 if(e.getTypeE().equals("formation")){
                       PreparedStatement preparedStmt = con.prepareStatement(" delete from participant where id_f_id= ? and  id_fo_id=?");
	    preparedStmt.setInt(1,e.getF().getId());
            preparedStmt.setInt(2,e.getFormation().getId());
            preparedStmt.executeUpdate();
                 }
                 else if(e.getTypeE().equals("evenement")){
                     PreparedStatement preparedStmt = con.prepareStatement(" delete from participant where id_f_id= ? and  id_e_id=?");
	    preparedStmt.setInt(1,e.getF().getId());
            preparedStmt.setInt(2,e.getEl().getId());
            preparedStmt.executeUpdate();
                 }
             }
             else if(e.getTypeU().equals("societe")){
                 if(e.getTypeE().equals("formation")){
                    
                     PreparedStatement preparedStmt = con.prepareStatement(" delete from participant where id_s_id= ? and  id_fo_id=?");
	    preparedStmt.setInt(1,e.getS().getId());
            preparedStmt.setInt(2,e.getFormation().getId());
            preparedStmt.executeUpdate();
                 }
                 else if(e.getTypeE().equals("evenement")){
                     PreparedStatement preparedStmt = con.prepareStatement(" delete from participant where id_s_id= ? and  id_e_id=?");
	    preparedStmt.setInt(1,e.getS().getId());
            preparedStmt.setInt(2,e.getEl().getId());
            preparedStmt.executeUpdate();
                 }
             }
             
             
            
            } 
        catch (Exception ex) {
	    System.err.println(ex.getMessage());
            }
        
        
        
    }
    
    
     public void SupprimerParEvent(String TypeE,int ide) {
         try {
           
                 if(TypeE.equals("formation")){
                       PreparedStatement preparedStmt = con.prepareStatement(" delete from participant where id_fo_id=? and type_e=?");
	    preparedStmt.setInt(1,ide);
            preparedStmt.setString(2,TypeE);
            preparedStmt.executeUpdate();
                 }
                 else if(TypeE.equals("evenement")){
                     PreparedStatement preparedStmt = con.prepareStatement(" delete from participant where id_e_id=? and type_e=?");
	    preparedStmt.setInt(1,ide);
            preparedStmt.setString(2,TypeE);
            preparedStmt.executeUpdate();
                 }
             
        
             
             
          
            } 
        catch (Exception ex) {
	    System.err.println(ex.getMessage());
            }
        
        
        
    }


    @Override
    public void Modifier(Participant e) {
        
      
    }

    
    public ArrayList<Participant> Lister(String typeU,String typeE,int idu) {
         ArrayList<Participant> res = new ArrayList<Participant>();
         String sql="";
         if(typeU.equals("freelancer")){
             if(typeE.equals("formation")){
                  sql = "SELECT * FROM participant where id_f_id="+idu+" and type_e='formation'";
                 
             }
             else{
                  sql = "SELECT * FROM participant where id_f_id="+idu+" and type_e='evenement'";
             }
         }
         else if(typeU.equals("societe")){
              if(typeE.equals("formation")){
                 sql = "SELECT * FROM participant where id_s_id="+idu+" and type_e='formation'";
                 
             }
             else{
                  sql = "SELECT * FROM participant where id_s_id="+idu+" and type_e='evenement'";
             }
             
         }
        try {
            Statement stmt = con.createStatement();
           
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                int id = rs.getInt("id_p");
                int idf = rs.getInt("id_f_id");
                int ids = rs.getInt("id_s_id");
                int idev = rs.getInt("id_e_id");
                int idfo=rs.getInt("id_fo_id");
                String typeUs=rs.getString("type_u");
                String typeEv=rs.getString("type_e");
                
                Formation F=new Formation(idfo);
                EventLoisir Ev=new EventLoisir(idev);
                Freelancer Fr=new Freelancer(idf);
                Societe S=new Societe(ids);
                Participant P=new Participant(id, typeEv, typeUs, Fr, S, F, Ev);
                res.add(P);
            }
            rs.close();
            } 
        catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return res;
    }
    
    
    
     public ArrayList<Participant> ListerParEvent(String typeE,int idu) {
         ArrayList<Participant> res = new ArrayList<Participant>();
         String sql="";
         
             if(typeE.equals("formation")){
                  sql = "SELECT * FROM participant where id_fo_id="+idu+" and type_e='formation'";
                 
             }
             else{
                  sql = "SELECT * FROM participant where id_e_id="+idu+" and type_e='evenement'";
             }
         
    
        try {
            Statement stmt = con.createStatement();
           
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                int id = rs.getInt("id_p");
                int idf = rs.getInt("id_f_id");
                int ids = rs.getInt("id_s_id");
                int idev = rs.getInt("id_e_id");
                int idfo=rs.getInt("id_fo_id");
                String typeUs=rs.getString("type_u");
                String typeEv=rs.getString("type_e");
                
                Formation F=new Formation(idfo);
                EventLoisir Ev=new EventLoisir(idev);
                Freelancer Fr=new Freelancer(idf);
                Societe S=new Societe(ids);
                Participant P=new Participant(id, typeEv, typeUs, Fr, S, F, Ev);
                res.add(P);
            }
            rs.close();
            } 
        catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return res;
    }

    @Override
    public ArrayList<Participant> Lister() {
       return null;
    }

    @Override
    public void Ajouter(Participant e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
