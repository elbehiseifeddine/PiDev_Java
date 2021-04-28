/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev_java.interfaces;

import java.util.ArrayList;
import pidev_java.entities.Admin;
import pidev_java.entities.offreEmploi;
import pidev_java.entities.offreStage;

/**
 *
 * @author ahmed
 */
public interface IAdminEmploiService {

    public void SendOffreEmploiToAdminEmploi(int idOffreEmploi);

    public void SendOffreStageToAdminEmploi(int idOffreStage);

    public void ActivateOffreEmploi(offreEmploi offreEmploi, Admin admin);

    public void DeactivateOffreEmploi(offreEmploi offreEmploi);

    public void ActivateOffreStage(offreStage offreStage, Admin admin);

    public void DeactivateOffreStage(offreStage offreStage);

    public ArrayList<offreEmploi> historiqueOffreEmploi(Admin admin);

    public ArrayList<offreStage> historiqueOffreStage(Admin admin);
}
