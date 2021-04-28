/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev_java.entities;

import java.sql.Date;
import javafx.scene.control.TableColumn;

/**
 *
 * @author Ghassen Riahi
 */
public class offreStage {
    private int id;
    private String nomProjet;
    private String competence;
    private String description;
    private String domaine;
    private String duree;
    private String typeStage;
    private Date dateCreation;
    private Date dateExpiration;
private int etat;
private int idSociete;

    public offreStage() {
    }



    public offreStage(String nomProjet, String competence, String description, String domaine, String duree, String typeStage, Date dateCreation, Date dateExpiration) {
        this.nomProjet = nomProjet;
        this.competence = competence;
        this.description = description;
        this.domaine = domaine;
        this.duree = duree;
        this.typeStage = typeStage;
        this.dateCreation = dateCreation;
        this.dateExpiration = dateExpiration;
        
        
    }

    public offreStage(String nomProjet, String competence, String description, String domaine, String duree, String typeStage, Date dateCreation, Date dateExpiration, int etat) {
        this.nomProjet = nomProjet;
        this.competence = competence;
        this.description = description;
        this.domaine = domaine;
        this.duree = duree;
        this.typeStage = typeStage;
        this.dateCreation = dateCreation;
        this.dateExpiration = dateExpiration;
        this.etat = etat;
    }

    public offreStage(int id, String nomProjet, String competence, String description, String domaine, String duree, String typeStage, Date dateCreation, Date dateExpiration) {
        this.id = id;
        this.nomProjet = nomProjet;
        this.competence = competence;
        this.description = description;
        this.domaine = domaine;
        this.duree = duree;
        this.typeStage = typeStage;
        this.dateCreation = dateCreation;
        this.dateExpiration = dateExpiration;
    }

    public offreStage(String nomProjet, String competence, String description, String domaine, String duree, String typeStage, Date dateExpiration) {
        this.nomProjet = nomProjet;
        this.competence = competence;
        this.description = description;
        this.domaine = domaine;
        this.duree = duree;
        this.typeStage = typeStage;
        this.dateExpiration = dateExpiration;
    }

    public offreStage(int id, String nomProjet, String competence, String description, String domaine, String duree, String typeStage, Date dateCreation, Date dateExpiration, int etat, int idSociete) {
        this.id = id;
        this.nomProjet = nomProjet;
        this.competence = competence;
        this.description = description;
        this.domaine = domaine;
        this.duree = duree;
        this.typeStage = typeStage;
        this.dateCreation = dateCreation;
        this.dateExpiration = dateExpiration;
        this.etat = etat;
        this.idSociete = idSociete;
    }

    public offreStage(String nomProjet, String competence, String description, String domaine, String duree, String typeStage, Date dateCreation, Date dateExpiration, int etat, int idSociete) {
        this.nomProjet = nomProjet;
        this.competence = competence;
        this.description = description;
        this.domaine = domaine;
        this.duree = duree;
        this.typeStage = typeStage;
        this.dateCreation = dateCreation;
        this.dateExpiration = dateExpiration;
        this.etat = etat;
        this.idSociete = idSociete;
    }

    
    

    public int getId() {
        return id;
    }

    public String getNomProjet() {
        return nomProjet;
    }

    public String getCompetence() {
        return competence;
    }

    public String getDescription() {
        return description;
    }

    public String getDomaine() {
        return domaine;
    }

    public String getDuree() {
        return duree;
    }

    public String getTypeStage() {
        return typeStage;
    }

    public Date getDateCreation() {
        return dateCreation;
    }

    public Date getDateExpiration() {
        return dateExpiration;
    }

    public int getEtat() {
        return etat;
    }

    public void setEtat(int etat) {
        this.etat = etat;
    }

    public int getIdSociete() {
        return idSociete;
    }

    public void setIdSociete(int idSociete) {
        this.idSociete = idSociete;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNomProjet(String nomProjet) {
        this.nomProjet = nomProjet;
    }

    public void setCompetence(String competence) {
        this.competence = competence;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDomaine(String domaine) {
        this.domaine = domaine;
    }

    public void setDuree(String duree) {
        this.duree = duree;
    }

    public void setTypeStage(String typeStage) {
        this.typeStage = typeStage;
    }

    public void setDateCreation(Date dateCreation) {
        this.dateCreation = dateCreation;
    }

    public void setDateExpiration(Date dateExpiration) {
        this.dateExpiration = dateExpiration;
    }
    
    
}
