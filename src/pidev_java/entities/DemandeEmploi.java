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
public class DemandeEmploi {
    private int id;
    private int freelancer_id;
    private int offre_emploi_id;
    private String description;
    private String lettre;
    private String cv_file;
   private Date date_creation;
   private String domaine;
   private String diplome;
   private double salaire;
   private String nom_societe;
   private int etat;

  

  

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

    public int getOffre_emploi_id() {
        return offre_emploi_id;
    }

    public void setOffre_emploi_id(int offre_emploi_id) {
        this.offre_emploi_id = offre_emploi_id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDate_creation() {
        return date_creation;
    }

    public void setDate_creation(Date date_creation) {
        this.date_creation = date_creation;
    }

    public String getDomaine() {
        return domaine;
    }

    public void setDomaine(String domaine) {
        this.domaine = domaine;
    }

    public double getSalaire() {
        return salaire;
    }

    public void setSalaire(double salaire) {
        this.salaire = salaire;
    }

    public String getNom_societe() {
        return nom_societe;
    }

    public void setNom_societe(String nom_societe) {
        this.nom_societe = nom_societe;
    }

    public String getDiplome() {
        return diplome;
    }

    public void setDiplome(String diplome) {
        this.diplome = diplome;
    }

    public String getLettre(){
        return this.lettre;
    }
    public void setLettre( String lettre){
        this.lettre= lettre;
        
    }

   public DemandeEmploi() {
       this.etat=0;
    }

    public String getCv_file() {
        return cv_file;
    }

    public void setCv_file(String cv_file) {
        this.cv_file = cv_file;
    }

    public int getEtat() {
        return etat;
    }

    public void setEtat(int etat) {
        this.etat = etat;
    }

    public DemandeEmploi( int id,int freelancer_id, String description, String lettre, Date date_creation, String domaine, String diplome, double salaire,int offre_id) {
        this.id= id;
        this.freelancer_id = freelancer_id;
        
        this.description = description;
        this.lettre = lettre;
     
        this.date_creation = date_creation;
        this.domaine = domaine;
        this.diplome = diplome;
        this.salaire = salaire;
       this.offre_emploi_id=offre_id;
    }

   
   

    public Date convertUtilToSql(java.util.Date uDate) {
       java.sql.Date sDate = new java.sql.Date(uDate.getTime());
        return sDate;
    }

  

  
   
   
   
}