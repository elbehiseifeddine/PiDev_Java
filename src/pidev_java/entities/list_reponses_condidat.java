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
public class list_reponses_condidat {
    private int id;
    private int quiz_id;

    public list_reponses_condidat() {
    }

    public list_reponses_condidat(int id, int quiz_id) {
        this.id = id;
        this.quiz_id = quiz_id;
    }

    public list_reponses_condidat(int quiz_id) {
        this.quiz_id = quiz_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getQuiz_id() {
        return quiz_id;
    }

    public void setQuiz_id(int quiz_id) {
        this.quiz_id = quiz_id;
    }

    @Override
    public String toString() {
        return "list_reponses_condidat{" + "id=" + id + ", quiz_id=" + quiz_id + '}';
    }

}
