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
public class Question {
    private int id;
    private int rep_just_id;
    private int quiz_id_id;
    private String contenu_ques;
    private int nomb_rep;
    private int duree;

    public Question() {
    }

    public Question(int id, int rep_just_id, int quiz_id_id, String contenu_ques, int nomb_rep, int duree) {
        this.id = id;
        this.rep_just_id = rep_just_id;
        this.quiz_id_id = quiz_id_id;
        this.contenu_ques = contenu_ques;
        this.nomb_rep = nomb_rep;
        this.duree = duree;
    }

    public Question(int rep_just_id, int quiz_id_id, String contenu_ques, int nomb_rep, int duree) {
        this.rep_just_id = rep_just_id;
        this.quiz_id_id = quiz_id_id;
        this.contenu_ques = contenu_ques;
        this.nomb_rep = nomb_rep;
        this.duree = duree;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRep_just_id() {
        return rep_just_id;
    }

    public void setRep_just_id(int rep_just_id) {
        this.rep_just_id = rep_just_id;
    }

    public int getQuiz_id_id() {
        return quiz_id_id;
    }

    public void setQuiz_id_id(int quiz_id_id) {
        this.quiz_id_id = quiz_id_id;
    }

    public String getContenu_ques() {
        return contenu_ques;
    }

    public void setContenu_ques(String contenu_ques) {
        this.contenu_ques = contenu_ques;
    }

    public int getNomb_rep() {
        return nomb_rep;
    }

    public void setNomb_rep(int nomb_rep) {
        this.nomb_rep = nomb_rep;
    }

    public int getDuree() {
        return duree;
    }

    public void setDuree(int duree) {
        this.duree = duree;
    }

    @Override
    public String toString() {
        return "Question{" + "id=" + id + ", rep_just_id=" + rep_just_id + ", quiz_id_id=" + quiz_id_id + ", contenu_ques=" + contenu_ques + ", nomb_rep=" + nomb_rep + ", duree=" + duree + '}';
    }
    
    
}