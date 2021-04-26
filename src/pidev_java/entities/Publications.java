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
public class Publications {
    
    private int id;
    private String description;
    private String image;
    private String date_publication;
    private int freelancer_id;
    private int societe_id;
    private String nom_util;
    private String prenom_util;
    
    public Publications(String description, String image, String date_publication, int freelancer_id, int societe_id) {
        this.description = description;
        this.image = image;
        this.date_publication = date_publication;
        this.freelancer_id = freelancer_id;
        this.societe_id = societe_id;
    }
    
    public Publications(int id ,String description, String image, String date_publication, int freelancer_id, int societe_id , String nom_util, String prenom_util) {
        this.id = id;
        this.description = description;
        this.image = image;
        this.date_publication = date_publication;
        this.freelancer_id = freelancer_id;
        this.societe_id = societe_id;
        this.nom_util = nom_util;
        this.prenom_util = prenom_util;
    }

    public Publications() {}
    
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
    
    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
    
    public String getDate_publication() {
        return date_publication;
    }

    public void setDate_publication(String date_publication) {
        this.date_publication = date_publication;
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
    
    @Override
    public String toString() {
        return "Publications{" + "id=" + id + ", description=" + description + ", image=" + image + ", date_publication=" + date_publication + ", freelancer_id=" + freelancer_id + ", societe_id=" + societe_id + ", nom_util=" + nom_util + ", prenom_util=" + prenom_util + '}';
    }
    
}