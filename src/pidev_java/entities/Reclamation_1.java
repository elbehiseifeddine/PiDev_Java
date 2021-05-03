/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev_java.entities;

/**
 *
 * @author Ayari Ghaith
 */
public class Reclamation_1 {
    private int id;
    private String type;
    private String TextReclamation;
    private String DateReclamation;
    private String EmailUser;
    private String nomUser;
    private boolean Etat;
 

    public Reclamation_1() {
    }

    public Reclamation_1(String type, String TextReclamation, String DateReclamation, String EmailUser, String nomUser, boolean Etat) {
        this.type = type;
        this.TextReclamation = TextReclamation;
        this.DateReclamation = DateReclamation;
        this.EmailUser = EmailUser;
        this.nomUser = nomUser;
        this.Etat = Etat;
    }

   
    public Reclamation_1(int id, String type, String TextReclamation, String DateReclamation, String EmailUser, String nomUser, boolean Etat) {
        this.id = id;
        this.type = type;
        this.TextReclamation = TextReclamation;
        this.DateReclamation = DateReclamation;
        this.EmailUser = EmailUser;
        this.nomUser = nomUser;
        this.Etat = Etat;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTextReclamation() {
        return TextReclamation;
    }

    public void setTextReclamation(String TextReclamation) {
        this.TextReclamation = TextReclamation;
    }

    public String getDateReclamation() {
        return DateReclamation;
    }

    public void setDateReclamation(String DateReclamation) {
        this.DateReclamation = DateReclamation;
    }

    public String getEmailUser() {
        return EmailUser;
    }

    public void setEmailUser(String EmailUser) {
        this.EmailUser = EmailUser;
    }

    public String getNomUser() {
        return nomUser;
    }

    public void setNomUser(String nomUser) {
        this.nomUser = nomUser;
    }

    public boolean isEtat() {
        return Etat;
    }

    public void setEtat(boolean Etat) {
        this.Etat = Etat;
    }
    
    
    
    
    
    
}