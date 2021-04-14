/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev_java.interfaces;

import java.util.ArrayList;
import pidev_java.entities.Admin;

/**
 *
 * @author ahmed
 */
public interface IAdminEmploiService {

    public void SendOffreEmploiToAdminEmploi(int idOffreEmploi);

    public void SendOffreStageToAdminEmploi(int idOffreStage);

//    public void ActivateOffreEmploi(OffreEmploi offreEmploi, Admin admin);
//
//    public void DeactivateOffreEmploi(OffreEmploi offreEmploi);
//
//    public void ActivateOffreStage(OffreStage offreStage, Admin admin);
//
//    public void DeactivateOffreStage(OffreStage offreStage);
//
//    public ArrayList<OffreEmploi> historiqueOffreEmploi(Admin admin);
//
//    public ArrayList<OffreStage> historiqueOffreStage(Admin admin);
}
