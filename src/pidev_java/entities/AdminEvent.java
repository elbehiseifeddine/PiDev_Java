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
public class AdminEvent {
    
    private int id, id_a_e,id_event_loisir,id_fromation;

    public AdminEvent(int id_a_e, int id_event_loisir, int id_fromation) {
        this.id_a_e = id_a_e;
        this.id_event_loisir = id_event_loisir;
        this.id_fromation = id_fromation;
    }

    public AdminEvent() {
    }

    public int getId_a_e() {
        return id_a_e;
    }

    public void setId_a_e(int id_a_e) {
        this.id_a_e = id_a_e;
    }

    public int getId_event_loisir() {
        return id_event_loisir;
    }

    public void setId_event_loisir(int id_event_loisir) {
        this.id_event_loisir = id_event_loisir;
    }

    public int getId_fromation() {
        return id_fromation;
    }

    public void setId_fromation(int id_fromation) {
        this.id_fromation = id_fromation;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    
}
