/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev_java.entities;

/**
 *
 * @author ahmed
 */
public class Reclamation {
    
    private int id;
    private boolean etat;
    private String type,texte_reclamation,date_reclamation,email_utilisateur,nom_utilisateur;

    public Reclamation() {
    }

    public boolean isEtat() {
        return etat;
    }

    public void setEtat(boolean etat) {
        this.etat = etat;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTexte_reclamation() {
        return texte_reclamation;
    }

    public void setTexte_reclamation(String texte_reclamation) {
        this.texte_reclamation = texte_reclamation;
    }

    public String getDate_reclamation() {
        return date_reclamation;
    }

    public void setDate_reclamation(String date_reclamation) {
        this.date_reclamation = date_reclamation;
    }

    public String getEmail_utilisateur() {
        return email_utilisateur;
    }

    public void setEmail_utilisateur(String email_utilisateur) {
        this.email_utilisateur = email_utilisateur;
    }

    public String getNom_utilisateur() {
        return nom_utilisateur;
    }

    public void setNom_utilisateur(String nom_utilisateur) {
        this.nom_utilisateur = nom_utilisateur;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    
}
