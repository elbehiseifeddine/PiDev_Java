/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev_java.entities;

/**
 *
 * @author ASUS
 */
public class Participant {
    private int id;
    private String typeE;
    private String typeU;
    private Freelancer F;
    private Societe S;
    private Formation formation;
    private EventLoisir el;

    public Participant(String typeE, String typeU, Freelancer F, Societe S, Formation formation, EventLoisir el) {
        this.typeE = typeE;
        this.typeU = typeU;
        this.F = F;
        this.S = S;
        this.formation = formation;
        this.el = el;
    }

    public Participant() {
    }

    public Participant(int id, String typeE, String typeU, Freelancer F, Societe S, Formation formation, EventLoisir el) {
        this.id = id;
        this.typeE = typeE;
        this.typeU = typeU;
        this.F = F;
        this.S = S;
        this.formation = formation;
        this.el = el;
    }
    
   

   

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTypeE() {
        return typeE;
    }

    public void setTypeE(String typeE) {
        this.typeE = typeE;
    }

    public String getTypeU() {
        return typeU;
    }

    public void setTypeU(String typeU) {
        this.typeU = typeU;
    }

    public Freelancer getF() {
        return F;
    }

    public void setF(Freelancer F) {
        this.F = F;
    }

    public Societe getS() {
        return S;
    }

    public void setS(Societe S) {
        this.S = S;
    }

    public Formation getFormation() {
        return formation;
    }

    public void setFormation(Formation formation) {
        this.formation = formation;
    }

    public EventLoisir getEl() {
        return el;
    }

    public void setEl(EventLoisir el) {
        this.el = el;
    }
    
    
    
            
            
    
}
