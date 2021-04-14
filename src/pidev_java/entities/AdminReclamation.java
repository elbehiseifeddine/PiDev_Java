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
public class AdminReclamation {
    private int id, id_a_r , id_reclamation;

    public AdminReclamation() {
    }

    public AdminReclamation(int id_a_r, int id_reclamation) {
        this.id_a_r = id_a_r;
        this.id_reclamation = id_reclamation;
    }

    public int getId_a_r() {
        return id_a_r;
    }

    public void setId_a_r(int id_a_r) {
        this.id_a_r = id_a_r;
    }

    public int getId_reclamation() {
        return id_reclamation;
    }

    public void setId_reclamation(int id_reclamation) {
        this.id_reclamation = id_reclamation;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    
    
}
