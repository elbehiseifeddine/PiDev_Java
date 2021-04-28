/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev_java.entities;

/**
 *
 * @author seifeddine
 */
public class Freelancer {
    
    private String nom,prenom,adresse,email,mot_de_passe,photo_de_profile,sexe,competences
            	,langues,compte_facebook,compte_linkedin,compte_twitter,date_creation;
    private int id,etat,views_nb;
    private static Freelancer instance;
    
    public Freelancer() {
    }
    
      public Freelancer(int id) {
        this.id = id;
    }

    public Freelancer(String nom, String prenom, String adresse, String email, String mot_de_passe, String photo_de_profile, String sexe, String competences, String langues, String compte_facebook, String compte_linkedin, String compte_twitter, String date_creation, int etat, int views_nb) {
        this.nom = nom;
        this.prenom = prenom;
        this.adresse = adresse;
        this.email = email;
        this.mot_de_passe = mot_de_passe;
        this.photo_de_profile = photo_de_profile;
        this.sexe = sexe;
        this.competences = competences;
        this.langues = langues;
        this.compte_facebook = compte_facebook;
        this.compte_linkedin = compte_linkedin;
        this.compte_twitter = compte_twitter;
        this.date_creation = date_creation;
        this.etat = etat;
        this.views_nb = views_nb;
    }

    public Freelancer(String nom, String prenom, String adresse, String email, String mot_de_passe, String photo_de_profile, String sexe, String competences, String langues, String compte_facebook, String compte_linkedin, String compte_twitter, String date_creation, int id, int etat, int views_nb) {
        this.nom = nom;
        this.prenom = prenom;
        this.adresse = adresse;
        this.email = email;
        this.mot_de_passe = mot_de_passe;
        this.photo_de_profile = photo_de_profile;
        this.sexe = sexe;
        this.competences = competences;
        this.langues = langues;
        this.compte_facebook = compte_facebook;
        this.compte_linkedin = compte_linkedin;
        this.compte_twitter = compte_twitter;
        this.date_creation = date_creation;
        this.id = id;
        this.etat = etat;
        this.views_nb = views_nb;
    }

    public Freelancer(String nom, String prenom, String adresse, String email, String photo_de_profile, String sexe, String competences, String langues, String compte_facebook, String compte_linkedin, String compte_twitter,  int id, int views_nb) {
        this.nom = nom;
        this.prenom = prenom;
        this.adresse = adresse;
        this.email = email;
        this.photo_de_profile = photo_de_profile;
        this.sexe = sexe;
        this.competences = competences;
        this.langues = langues;
        this.compte_facebook = compte_facebook;
        this.compte_linkedin = compte_linkedin;
        this.compte_twitter = compte_twitter;
        this.id = id;
        this.views_nb = views_nb;
    }
    
    
    
     public static void setInstance(Freelancer f) {
        instance=f;
     }
     
     public static Freelancer getInstance() {
        return instance;
    }
     
    public static void cleanFreelancer() {
        instance=null;
    }

//    public Freelancer(int iducon) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public String getAdresse() {
        return adresse;
    }

    public String getEmail() {
        return email;
    }

    public String getMot_de_passe() {
        return mot_de_passe;
    }

    public String getPhoto_de_profile() {
        return photo_de_profile;
    }

    public String getSexe() {
        return sexe;
    }

    public String getCompetences() {
        return competences;
    }

    public String getLangues() {
        return langues;
    }

    public String getCompte_facebook() {
        return compte_facebook;
    }

    public String getCompte_linkedin() {
        return compte_linkedin;
    }

    public String getCompte_twitter() {
        return compte_twitter;
    }

    public String getDate_creation() {
        return date_creation;
    }

    public int getId() {
        return id;
    }

    public int getEtat() {
        return etat;
    }

    public int getViews_nb() {
        return views_nb;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setMot_de_passe(String mot_de_passe) {
        this.mot_de_passe = mot_de_passe;
    }

    public void setPhoto_de_profile(String photo_de_profile) {
        this.photo_de_profile = photo_de_profile;
    }

    public void setSexe(String sexe) {
        this.sexe = sexe;
    }

    public void setCompetences(String competences) {
        this.competences = competences;
    }

    public void setLangues(String langues) {
        this.langues = langues;
    }

    public void setCompte_facebook(String compte_facebook) {
        this.compte_facebook = compte_facebook;
    }

    public void setCompte_linkedin(String compte_linkedin) {
        this.compte_linkedin = compte_linkedin;
    }

    public void setCompte_twitter(String compte_twitter) {
        this.compte_twitter = compte_twitter;
    }

    public void setDate_creation(String date_creation) {
        this.date_creation = date_creation;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setEtat(int etat) {
        this.etat = etat;
    }

    public void setViews_nb(int views_nb) {
        this.views_nb = views_nb;
    }

    @Override
    public String toString() {
        return "Freelancer{" + "nom=" + nom + ", prenom=" + prenom + ", adresse=" + adresse + ", email=" + email + ", mot_de_passe=" + mot_de_passe + ", photo_de_profile=" + photo_de_profile + ", sexe=" + sexe + ", competences=" + competences + ", langues=" + langues + ", compte_facebook=" + compte_facebook + ", compte_linkedin=" + compte_linkedin + ", compte_twitter=" + compte_twitter + ", date_creation=" + date_creation + ", id=" + id + ", etat=" + etat + ", views_nb=" + views_nb + '}';
    }
    
    
}

