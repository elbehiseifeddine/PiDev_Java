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
    private String salaire;
    private Date dateCreation;
    private Date dateExpiration;

    public offreEmploi() {
    }

    
    public offreEmploi(String nomProjet, String competence, String description, String domaine, String salaire, Date dateCreation, Date dateExpiration) {
        this.nomProjet = nomProjet;
        this.competence = competence;
        this.description = description;
        this.domaine = domaine;
        this.salaire = salaire;
        this.dateCreation = dateCreation;
        this.dateExpiration = dateExpiration;
    }

    public offreEmploi(int id, String nomProjet, String competence, String description, String domaine, String salaire, Date dateCreation, Date dateExpiration) {
        this.id = id;
        this.nomProjet = nomProjet;
        this.competence = competence;
        this.description = description;
        this.domaine = domaine;
        this.salaire = salaire;
        this.dateCreation = dateCreation;
        this.dateExpiration = dateExpiration;
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

    public String getSalaire() {
        return salaire;
    }

    public void setSalaire(String salaire) {
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
    
    
    
    
}
