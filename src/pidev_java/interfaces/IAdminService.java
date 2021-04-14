/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev_java.interfaces;

import java.util.ArrayList;

/**
 *
 * @author ahmed
 */
public interface IAdminService<T> {
    public void add(T entity);
    public ArrayList<T> getAll();
    public void update (T entity , String oldType);
    public void delete (T entity);
    public T find(int id);
    public ArrayList<T> findByName(String name);
    public ArrayList<T> getAllAdminReclamation();
    public ArrayList<T> getAllAdminEmploi();
    public ArrayList<T> getAllAdminEvent();

    
    
}
