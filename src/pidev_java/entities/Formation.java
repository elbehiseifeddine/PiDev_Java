/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev_java.entities;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDateTime;

/**
 *
 * @author ASUS
 */
public class Formation {
    public int id;
    public String Labelle;
    public String Description;
    public String Lieu;
    public Timestamp DateDebut;
    public Timestamp DateFin;
    public String Domaine;
    public float Montant;
    public boolean Etat;
    public long Lng;
    public long Lat;
    public String imageF;

    public Formation() {
    }

    public Formation(int id, String Labelle, String Description, String Lieu, Timestamp DateDebut, Timestamp DateFin, String Domaine, float Montant, boolean Etat, long Lng, long Lat,String image) {
        this.id = id;
        this.Labelle = Labelle;
        this.Description = Description;
        this.Lieu = Lieu;
        this.DateDebut = DateDebut;
        this.DateFin = DateFin;
        this.Domaine = Domaine;
        this.Montant = Montant;
        this.Etat = Etat;
        this.Lng = Lng;
        this.Lat = Lat;
        this.imageF=image;
    }

    public Formation(String Labelle, String Description, String Lieu, Timestamp DateDebut, Timestamp DateFin, String Domaine, float Montant, boolean Etat, long Lng, long Lat,String image) {
        this.Labelle = Labelle;
        this.Description = Description;
        this.Lieu = Lieu;
        this.DateDebut = DateDebut;
        this.DateFin = DateFin;
        this.Domaine = Domaine;
        this.Montant = Montant;
        this.Etat = Etat;
        this.Lng = Lng;
        this.Lat = Lat;
        this.imageF=image;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLabelle() {
        return Labelle;
    }

    public void setLabelle(String Labelle) {
        this.Labelle = Labelle;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String Description) {
        this.Description = Description;
    }

    public String getLieu() {
        return Lieu;
    }

    public void setLieu(String Lieu) {
        this.Lieu = Lieu;
    }

    public Timestamp getDateDebut() {
        return DateDebut;
    }

    public void setDateDebut(Timestamp DateDebut) {
        this.DateDebut = DateDebut;
    }

    public Timestamp getDateFin() {
        return DateFin;
    }

    public void setDateFin(Timestamp DateFin) {
        this.DateFin = DateFin;
    }

    public String getDomaine() {
        return Domaine;
    }

    public void setDomaine(String Domaine) {
        this.Domaine = Domaine;
    }

    public float getMontant() {
        return Montant;
    }

    public void setMontant(float Montant) {
        this.Montant = Montant;
    }

    public boolean isEtat() {
        return Etat;
    }

    public void setEtat(boolean Etat) {
        this.Etat = Etat;
    }

    public long getLng() {
        return Lng;
    }

    public void setLng(long Lng) {
        this.Lng = Lng;
    }

    public long getLat() {
        return Lat;
    }

    public void setLat(long Lat) {
        this.Lat = Lat;
    }

    public String getImageF() {
        return imageF;
    }

    public void setImageF(String imageF) {
        this.imageF = imageF;
    }
    
    
    
    
}
