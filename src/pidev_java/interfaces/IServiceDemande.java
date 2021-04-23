/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev_java.interfaces;

import java.util.List;

/**
 *
 * @author aissa
 * @param <T>
 */
public interface IServiceDemande<T> {
    public void ajouter(T t);
    public void supprimer(int id);
    public void modifier(T t);
    public List<T> afficher();
}
