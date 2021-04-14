/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev_java.interfaces;

import java.util.ArrayList;
import pidev_java.entities.Admin;
import pidev_java.entities.Reclamation;

/**
 *
 * @author ahmed
 */
public interface IAdminReclamationService {
   
    public void SendToAdminReclamation(int idReclamation);
  
    public void Activate(Reclamation reclamation, Admin admin);
   
    public void Deactivate(Reclamation reclamation);
   
    public ArrayList<Reclamation> historique(Admin admin);
   
}
