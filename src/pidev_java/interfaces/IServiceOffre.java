/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev_java.interfaces;

import java.util.List;

/**
 *
 * @author Ghassen Riahi
 */
public interface IServiceOffre<T> {
    public void add(T entity);
    public void delete(int id);
    public void update(T entity);
    public List<T> getAll();
    public List<T> getOwn();
    
}