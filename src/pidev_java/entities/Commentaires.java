/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev_java.entities;

/**
 *
 * @author ksemt
 */
public class Commentaires {
    
    private int id;
    private String description;
    private String date_commentaire;
    private int id_pub;
    private int freelancer_id;
    private int societe_id;
    private String nom_util;
    private String prenom_util;
    
    public Commentaires(String description, String date_commentaire, int id_pub, int freelancer_id, int societe_id) {
        this.description = description;
        this.date_commentaire = date_commentaire;
        this.id_pub = id_pub;
        this.freelancer_id = freelancer_id;
        this.societe_id = societe_id;
    }
    
    public Commentaires(int id ,String description, String date_commentaire, int id_pub, int freelancer_id, int societe_id, String nom_util, String prenom_util) {
        this.id = id;
        this.description = description;
        this.date_commentaire = date_commentaire;
        this.id_pub = id_pub;
        this.freelancer_id = freelancer_id;
        this.societe_id = societe_id;
        this.nom_util = nom_util;
        this.prenom_util = prenom_util;
    }

    public Commentaires() {}
    
    public int getId() {
        return id;
    }

    public void setId(int id_pub) {
        this.id = id_pub;
    }
    
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    
    public String getDate_commentaire() {
        return date_commentaire;
    }

    public void setDate_commentaire(String date_commentaire) {
        this.date_commentaire = date_commentaire;
    }
    
    public int getId_pub() {
        return id_pub;
    }

    public void setId_pub(int id_pub) {
        this.id_pub = id_pub;
    }
    
    public int getFreelancer_id() {
        return freelancer_id;
    }

    public void setFreelancer_id(int freelancer_id) {
        this.freelancer_id = freelancer_id;
    }
    
    public int getSociete_id() {
        return societe_id;
    }

    public void setSociete_id(int societe_id) {
        this.societe_id = societe_id;
    }
    
    public String getNomUtil() {
        return nom_util;
    }

    public void setNomUtil(String nom_util) {
        this.nom_util = nom_util;
    }
    
    public String getPrenomUtil() {
        return prenom_util;
    }

    public void setPrenomUtil(String prenom_util) {
        this.prenom_util = prenom_util;
    }
    
    @Override
    public String toString() {
        return "Commentaires{" + "id=" + id + ", description=" + description + ", id_pub=" + id_pub + ", date_commentaire=" + date_commentaire + ", freelancer_id=" + freelancer_id + ", societe_id=" + societe_id + ", nom_util=" + nom_util + ", prenom_util=" + prenom_util + '}';
    }
    
}