
package pidev_java.entities;

/**
 *
 * @author Ayari Ghaith
 */
public class reponse_condidat {
    private int id;
    private int question_id;
    private int reponse_id;
    private int list_reponses_condidat_id;

    public reponse_condidat() {
    }

    public reponse_condidat(int id, int question_id, int reponse_id, int list_reponses_condidat_id) {
        this.id = id;
        this.question_id = question_id;
        this.reponse_id = reponse_id;
        this.list_reponses_condidat_id = list_reponses_condidat_id;
    }

    public reponse_condidat(int question_id, int reponse_id, int list_reponses_condidat_id) {
        this.question_id = question_id;
        this.reponse_id = reponse_id;
        this.list_reponses_condidat_id = list_reponses_condidat_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getQuestion_id() {
        return question_id;
    }

    public void setQuestion_id(int question_id) {
        this.question_id = question_id;
    }

    public int getReponse_id() {
        return reponse_id;
    }

    public void setReponse_id(int reponse_id) {
        this.reponse_id = reponse_id;
    }

    public int getList_reponses_condidat_id() {
        return list_reponses_condidat_id;
    }

    public void setList_reponses_condidat_id(int list_reponses_condidat_id) {
        this.list_reponses_condidat_id = list_reponses_condidat_id;
    }

    @Override
    public String toString() {
        return "reponse_condidat{" + "id=" + id + ", question_id=" + question_id + ", reponse_id=" + reponse_id + ", list_reponses_condidat_id=" + list_reponses_condidat_id + '}';
    }
    
}