/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev_java.interfaces;

import java.util.ArrayList;

/**
 *
 * @author ASUS
 */
public interface IServiceEvent <T> {
    public void Ajouter(T e);
    public void Supprimer(T e);
    public void Modifier(T e);
    public ArrayList<T> Lister();
    
}
