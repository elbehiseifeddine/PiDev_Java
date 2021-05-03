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
public class Reponse {
    private int id;
    private int id_ques_id;
    private String contenu_rep;

    public Reponse() {
    }

    public Reponse(int id, int id_ques_id, String contenu_rep) {
        this.id = id;
        this.id_ques_id = id_ques_id;
        this.contenu_rep = contenu_rep;
    }

    public Reponse(int id_ques_id, String contenu_rep) {
        this.id_ques_id = id_ques_id;
        this.contenu_rep = contenu_rep;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_ques_id() {
        return id_ques_id;
    }

    public void setId_ques_id(int id_ques_id) {
        this.id_ques_id = id_ques_id;
    }

    public String getContenu_rep() {
        return contenu_rep;
    }

    public void setContenu_rep(String contenu_rep) {
        this.contenu_rep = contenu_rep;
    }

    @Override
    public String toString() {
        return "Reponse{" + "id=" + id + ", id_ques_id=" + id_ques_id + ", contenu_rep=" + contenu_rep + '}';
    }
    
}