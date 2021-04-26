/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev_java.entities;

import java.sql.Date;

/**
 *
 * @author Ghassen Riahi
 */
public class offreEmploi {
    private int id;
    private String nomProjet;
    private String competence;
    private String description;
    private String domaine;
    private Float salaire;
    private Date dateCreation;
    private Date dateExpiration;
    private int etat;
    private int idSociete;
    private String devise;

    public offreEmploi() {
    }

    
   

    public offreEmploi(int id, String nomProjet, String competence, String description, String domaine, Float salaire, Date dateCreation, Date dateExpiration, int etat) {
        this.id = id;
        this.nomProjet = nomProjet;
        this.competence = competence;
        this.description = description;
        this.domaine = domaine;
        this.salaire = salaire;
        this.dateCreation = dateCreation;
        this.dateExpiration = dateExpiration;
        this.etat = etat;
    }

    public offreEmploi(String nomProjet, String competence, String description, String domaine, Float salaire, Date dateCreation, Date dateExpiration, int etat) {
        this.nomProjet = nomProjet;
        this.competence = competence;
        this.description = description;
        this.domaine = domaine;
        this.salaire = salaire;
        this.dateCreation = dateCreation;
        this.dateExpiration = dateExpiration;
        this.etat = etat;
    }

    public offreEmploi(int id, String nomProjet, String competence, String description, String domaine, Float salaire, Date dateCreation, Date dateExpiration, int etat, int idSociete, String devise) {
        this.id = id;
        this.nomProjet = nomProjet;
        this.competence = competence;
        this.description = description;
        this.domaine = domaine;
        this.salaire = salaire;
        this.dateCreation = dateCreation;
        this.dateExpiration = dateExpiration;
        this.etat = etat;
        this.idSociete = idSociete;
        this.devise = devise;
    }

    public offreEmploi(String nomProjet, String competence, String description, String domaine, Float salaire, Date dateCreation, Date dateExpiration, int etat, int idSociete, String devise) {
        this.nomProjet = nomProjet;
        this.competence = competence;
        this.description = description;
        this.domaine = domaine;
        this.salaire = salaire;
        this.dateCreation = dateCreation;
        this.dateExpiration = dateExpiration;
        this.etat = etat;
        this.idSociete = idSociete;
        this.devise = devise;
    }

    public offreEmploi(int id, String nomProjet, String competence, String description, String domaine, Float salaire, Date dateCreation, Date dateExpiration, int etat, String devise) {
        this.id = id;
        this.nomProjet = nomProjet;
        this.competence = competence;
        this.description = description;
        this.domaine = domaine;
        this.salaire = salaire;
        this.dateCreation = dateCreation;
        this.dateExpiration = dateExpiration;
        this.etat = etat;
        this.devise = devise;
    }

    public offreEmploi(String nomProjet, String competence, String description, String domaine, Float salaire, Date dateCreation, Date dateExpiration, int etat, String devise) {
        this.nomProjet = nomProjet;
        this.competence = competence;
        this.description = description;
        this.domaine = domaine;
        this.salaire = salaire;
        this.dateCreation = dateCreation;
        this.dateExpiration = dateExpiration;
        this.etat = etat;
        this.devise = devise;
    }

    public offreEmploi(int id, String nomProjet, String competence, String description, String domaine, Float salaire, Date dateCreation, Date dateExpiration, String devise) {
        this.id = id;
        this.nomProjet = nomProjet;
        this.competence = competence;
        this.description = description;
        this.domaine = domaine;
        this.salaire = salaire;
        this.dateCreation = dateCreation;
        this.dateExpiration = dateExpiration;
        this.devise = devise;
    }

    public offreEmploi(String nomProjet, String competence, String description, String domaine, Float salaire, Date dateCreation, Date dateExpiration, String devise) {
        this.nomProjet = nomProjet;
        this.competence = competence;
        this.description = description;
        this.domaine = domaine;
        this.salaire = salaire;
        this.dateCreation = dateCreation;
        this.dateExpiration = dateExpiration;
        this.devise = devise;
    }
    
    

    public int getEtat() {
        return etat;
    }

    public void setEtat(int etat) {
        this.etat = etat;
    }
    
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNomProjet() {
        return nomProjet;
    }

    public void setNomProjet(String nomProjet) {
        this.nomProjet = nomProjet;
    }

    public String getCompetence() {
        return competence;
    }

    public void setCompetence(String competence) {
        this.competence = competence;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDomaine() {
        return domaine;
    }

    public void setDomaine(String domaine) {
        this.domaine = domaine;
    }

    public Float getSalaire() {
        return salaire;
    }

    public void setSalaire(Float salaire) {
        this.salaire = salaire;
    }

    public Date getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(Date dateCreation) {
        this.dateCreation = dateCreation;
    }

    public Date getDateExpiration() {
        return dateExpiration;
    }

    public void setDateExpiration(Date dateExpiration) {
        this.dateExpiration = dateExpiration;
    }

    public int getIdSociete() {
        return idSociete;
    }

    public void setIdSociete(int idSociete) {
        this.idSociete = idSociete;
    }
 public String getDevise() {
        return devise;
    }

    public void setDevise(String devise) {
        this.devise = devise;
    }    
    
    
    
}