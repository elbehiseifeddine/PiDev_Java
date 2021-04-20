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
    
    
}
