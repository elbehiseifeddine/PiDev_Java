package pidev_java.entities;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Ayari Ghaith
 */
public class List_reponses_condidat {
    

private int id;
    private int quiz_id;
    private int condidtaure_id;
    private int score;

    public List_reponses_condidat() {
    }

    public List_reponses_condidat(int id, int quiz_id, int condidtaure_id, int score) {
        this.id = id;
        this.quiz_id = quiz_id;
        this.condidtaure_id = condidtaure_id;
        this.score = score;
    }

    public List_reponses_condidat(int quiz_id, int condidtaure_id, int score) {
        this.quiz_id = quiz_id;
        this.condidtaure_id = condidtaure_id;
        this.score = score;
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

    public int getCondidtaure_id() {
        return condidtaure_id;
    }

    public void setCondidtaure_id(int condidtaure_id) {
        this.condidtaure_id = condidtaure_id;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    @Override
    public String toString() {
        return "List_reponses_condidat{" + "id=" + id + ", quiz_id=" + quiz_id + ", condidtaure_id=" + condidtaure_id + ", score=" + score + '}';
    }
}    