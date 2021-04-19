/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev_java.entities;

import java.sql.Date;
import java.sql.Timestamp;

/**
 *
 * @author ASUS
 */
public class EventLoisir {
    public int id;
    public String Labelle;
    public String Description;
    public String Lieu;
    public Timestamp DateDebut;
    public Timestamp DateFin;
    public String Domaine;
    public int nbParticipant;
    public boolean Etat;
    public long Lng;
    public long Lat;
    private String imageE;

    public EventLoisir() {
    }

    public EventLoisir(int id, String Labelle, String Description, String Lieu, Timestamp DateDebut, Timestamp DateFin, String Domaine, int nbParticipant, boolean Etat, long Lng, long Lat,String image) {
        this.id = id;
        this.Labelle = Labelle;
        this.Description = Description;
        this.Lieu = Lieu;
        this.DateDebut = DateDebut;
        this.DateFin = DateFin;
        this.Domaine = Domaine;
        this.nbParticipant = nbParticipant;
        this.Etat = Etat;
        this.Lng = Lng;
        this.Lat = Lat;
        this.imageE=image;
    }

    public EventLoisir(String Labelle, String Description, String Lieu, Timestamp DateDebut, Timestamp DateFin, String Domaine, int nbParticipant, boolean Etat, long Lng, long Lat,String image) {
        this.Labelle = Labelle;
        this.Description = Description;
        this.Lieu = Lieu;
        this.DateDebut = DateDebut;
        this.DateFin = DateFin;
        this.Domaine = Domaine;
        this.nbParticipant = nbParticipant;
        this.Etat = Etat;
        this.Lng = Lng;
        this.Lat = Lat;
        this.imageE=image;
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

    public int getNbParticipant() {
        return nbParticipant;
    }

    public void setNbParticipant(int nbParticipant) {
        this.nbParticipant = nbParticipant;
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

    public String getImageE() {
        return imageE;
    }

    public void setImageE(String imageE) {
        this.imageE = imageE;
    }
    
    
    
}

