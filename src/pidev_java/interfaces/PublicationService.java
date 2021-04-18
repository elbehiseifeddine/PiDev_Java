/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev_java.interfaces;

import java.util.ArrayList;

/**
 *
 * @author ksemt
 * @param <T>
 */
public interface PublicationService<T> {
    
    void ajouter(T t) ;
    
    ArrayList<T> getAll() ;
    
}
