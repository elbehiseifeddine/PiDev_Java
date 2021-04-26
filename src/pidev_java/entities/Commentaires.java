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
    private String NomUtil;
    private String PrenomUtil;
    
    public Commentaires(String description, String date_commentaire, int id_pub, int freelancer_id, int societe_id) {
        this.description = description;
        this.date_commentaire = date_commentaire;
        this.id_pub = id_pub;
        this.freelancer_id = freelancer_id;
        this.societe_id = societe_id;
    }
    
    public Commentaires(int id ,String description, String date_commentaire, int id_pub, int freelancer_id, int societe_id, String NomUtil, String PrenomUtil) {
        this.id = id;
        this.description = description;
        this.date_commentaire = date_commentaire;
        this.id_pub = id_pub;
        this.freelancer_id = freelancer_id;
        this.societe_id = societe_id;
        this.NomUtil = NomUtil;
        this.PrenomUtil = PrenomUtil;
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
        return NomUtil;
    }

    public void setNomUtil(String NomUtil) {
        this.NomUtil = NomUtil;
    }
    
    public String getPrenomUtil() {
        return PrenomUtil;
    }

    public void setPrenomUtil(String PrenomUtil) {
        this.PrenomUtil = PrenomUtil;
    }
    
    @Override
    public String toString() {
        return "Commentaires{" + "id=" + id + ", description=" + description + ", id_pub=" + id_pub + ", date_commentaire=" + date_commentaire + ", freelancer_id=" + freelancer_id + ", societe_id=" + societe_id + ", NomUtil=" + NomUtil + ", PrenomUtil=" + PrenomUtil + '}';
    }
    
}