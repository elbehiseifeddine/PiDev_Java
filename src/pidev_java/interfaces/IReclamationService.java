/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev_java.interfaces;

import java.util.ArrayList;

/**
 *
 * @author Ayari Ghaith
 */
public interface IReclamationService <T> {
     public void Ajouter(T e);
    public void Supprimer(T e);
    public void Modifier(T e);
    public ArrayList<T> Lister();
    
}
