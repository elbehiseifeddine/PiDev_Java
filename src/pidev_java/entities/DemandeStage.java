/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev_java.entities;

import java.sql.Date;
import java.time.LocalDate;

/**
 *
 * @author ely
 */
public class DemandeStage {
    private int id;
      private int freelancer_id;
    private int offre_stage_id;
    private String description;
    private String lettre;
     private LocalDate date_creation;
      private String domaine;
      private String type;
      private int duree;
      private String etude;
       private String nom_societe;
       private int etat;

    public DemandeStage() {
        this.etat=0;
    }

    public DemandeStage(int id, String description, LocalDate date_creation, String domaine, String type, int duree, String etude, String nom_societe) {
        this.id = id;
        this.description = description;
        this.date_creation = date_creation;
        this.domaine = domaine;
        this.type = type;
        this.duree = duree;
        this.etude = etude;
        this.nom_societe = nom_societe;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getFreelancer_id() {
        return freelancer_id;
    }

    public void setFreelancer_id(int freelancer_id) {
        this.freelancer_id = freelancer_id;
    }

    public int getOffre_stage_id() {
        return offre_stage_id;
    }

    public void setOffre_stage_id(int offre_stage_id) {
        this.offre_stage_id = offre_stage_id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getDate_creation() {
        return date_creation;
    }

    public void setDate_creation(LocalDate date_creation) {
        this.date_creation = date_creation;
    }

    public String getDomaine() {
        return domaine;
    }

    public void setDomaine(String domaine) {
        this.domaine = domaine;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getDuree() {
        return duree;
    }

    public void setDuree(int duree) {
        this.duree = duree;
    }

    public String getEtude() {
        return etude;
    }

    public void setEtude(String etude) {
        this.etude = etude;
    }

    public String getNom_societe() {
        return nom_societe;
    }

    public void setNom_societe(String nom_societe) {
        this.nom_societe = nom_societe;
    }

    @Override
    public String toString() {
        return "DemandeStage{" + "description=" + description + ", date_creation=" + date_creation + ", domaine=" + domaine + ", type=" + type + ", duree=" + duree + ", etude=" + etude + ", nom_societe=" + nom_societe + '}';
    }
       
       
         public Date convertUtilToSql(java.util.Date uDate) {
       java.sql.Date sDate = new java.sql.Date(uDate.getTime());
        return sDate;
    }

      
}
