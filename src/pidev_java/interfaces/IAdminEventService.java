/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev_java.interfaces;

import java.util.ArrayList;
import pidev_java.entities.Admin;
import pidev_java.entities.EventLoisir;
import pidev_java.entities.Formation;

/**
 *
 * @author ahmed
 */
public interface IAdminEventService {
    public void SendEvenementToAdminEmploi(int idEvent);

    public void SendFormationToAdminEmploi(int idFormation);

    public void ActivateEvenement(EventLoisir evenement, Admin admin);

    public void DeactivateEvenement(EventLoisir evenement);

    public void ActivateFormation(Formation formation, Admin admin);

    public void DeactivateFormation(Formation formation);

    public ArrayList<Formation> historiqueFormation(Admin admin);

    public ArrayList<EventLoisir> historiqueEvenement(Admin admin);

}
