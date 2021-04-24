/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev_java.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author seifeddine
 */
public class MaConnection {
    private String url="jdbc:mysql://localhost:3308/pidev";
    private String userName="root";
    private String password="";
    
    private Connection cnx;
    private static MaConnection instance;
    
    private MaConnection(){
        try{
            String unicode= "?useUnicode=yes&characterEncoding=UTF-8";
            Class.forName("com.mysql.jdbc.Driver");
            cnx= DriverManager.getConnection(url+unicode,userName,password);
            System.out.println("connection établie");
        }catch(Exception ex){
            Logger.getLogger(MaConnection.class.getName()).log(Level.SEVERE,null,ex);
        }
    }
    
    public static MaConnection getInstance(){
        if(instance==null){
            instance = new MaConnection();
        }
        return (instance);
    }
    
    public Connection getCnx(){
        return cnx;
    }
}
