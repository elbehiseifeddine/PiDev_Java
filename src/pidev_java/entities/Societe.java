/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev_java.entities;

/**
 *
 * @author seifeddine
 */
public class Societe {
    private String nom,adresse,email,mot_de_pass,photo_de_profile,status_juridique
            ,date_creation;
    private int id,etat,views_nb;
    private static Societe instance;
    public Societe() {
    }

      public Societe(int id) {
        this.id = id;
    }
    
    public Societe(String nom, String adresse, String email, String mot_de_pass, String photo_de_profile, String status_juridique, String date_creation, int id, int etat, int views_nb) {
        this.nom = nom;
        this.adresse = adresse;
        this.email = email;
        this.mot_de_pass = mot_de_pass;
        this.photo_de_profile = photo_de_profile;
        this.status_juridique = status_juridique;
        this.date_creation = date_creation;
        this.id = id;
        this.etat = etat;
        this.views_nb = views_nb;
    }

    public Societe(String nom, String adresse, String email, String photo_de_profile, String status_juridique,int id,int views_nb) {
        this.nom = nom;
        this.adresse = adresse;
        this.email = email;
        this.photo_de_profile = photo_de_profile;
        this.status_juridique = status_juridique;
        this.id = id;
        this.views_nb = views_nb;
    }
    
    
    
     public static void setInstance(Societe s) {
        instance=s;
     }
     
     public static Societe getInstance() {
        return instance;
    }
     
    public static void cleanSociete() {
        instance=null;
    }

    public String getNom() {
        return nom;
    }

    public String getAdresse() {
        return adresse;
    }

    public String getEmail() {
        return email;
    }

    public String getMot_de_pass() {
        return mot_de_pass;
    }

    public String getPhoto_de_profile() {
        return photo_de_profile;
    }

    public String getStatus_juridique() {
        return status_juridique;
    }

    public String getDate_creation() {
        return date_creation;
    }

    public int getId() {
        return id;
    }

    public int getEtat() {
        return etat;
    }

    public int getViews_nb() {
        return views_nb;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setMot_de_pass(String mot_de_pass) {
        this.mot_de_pass = mot_de_pass;
    }

    public void setPhoto_de_profile(String photo_de_profile) {
        this.photo_de_profile = photo_de_profile;
    }

    public void setStatus_juridique(String status_juridique) {
        this.status_juridique = status_juridique;
    }

    public void setDate_creation(String date_creation) {
        this.date_creation = date_creation;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setEtat(int etat) {
        this.etat = etat;
    }

    public void setViews_nb(int views_nb) {
        this.views_nb = views_nb;
    }

    
}
