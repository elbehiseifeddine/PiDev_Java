/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev_java.services;

import java.sql.Connection;
import java.util.List;
import pidev_java.entities.DemandeStage;
import pidev_java.interfaces.IServiceDemande;

import pidev_java.utils.MaConnection;

/**
 *
 * @author ely
 */
public class DemandeStageService implements IServiceDemande<DemandeStage> {
Connection cnx = MaConnection.getInstance().getCnx();
    @Override
    public void ajouter(DemandeStage t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void supprimer(DemandeStage t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void modifier(DemandeStage t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<DemandeStage> afficher() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
