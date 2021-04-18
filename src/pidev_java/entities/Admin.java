/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev_java.entities;

/**
 *
 * @author ahmed
 */
public class Admin {
    
    private int id,Approuve,nonApprouve;
    private String nom, prenom,login,pass,type;
    private boolean etat;
    private static Admin instance;

    public Admin(String nom, String prenom, String pass, String type, boolean etat) {
        this.nom = nom;
        this.prenom = prenom;
        this.pass = pass;
        this.type = type;
        this.etat = etat;
    }

    public Admin() {
    }
    
    public static void setInstance(Admin a) {
        instance=a;
     }
     
     public static Admin getInstance() {
        return instance;
    }
     
    public static void cleanFreelancer() {
        instance=null;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public boolean isEtat() {
        return etat;
    }

    public void setEtat(boolean etat) {
        this.etat = etat;
    }

    public void setId(int id) {
        this.id = id;
    }
    

    public int getId() {
        return id;
    }

    public int getApprouve() {
        return Approuve;
    }

    public int getNonApprouve() {
        return nonApprouve;
    }

    public void setApprouve(int Approuve) {
        this.Approuve = Approuve;
    }

    public void setNonApprouve(int nonApprouve) {
        this.nonApprouve = nonApprouve;
    }

    @Override
    public String toString() {
        return "Admin{" + "Approuve=" + Approuve + ", nonApprouve=" + nonApprouve + ", nom=" + nom + ", prenom=" + prenom + ", login=" + login + ", type=" + type + '}';
    }
    
    
    
}
