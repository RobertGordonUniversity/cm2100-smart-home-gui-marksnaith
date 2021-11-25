/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.ac.rgu.cm2100.gui.mvc;

/**
 *
 * @author mark
 */
public abstract class Controller<T extends Model> {
    
    protected T model;
    
    public abstract void setModel(T model);
    
    public T getModel(){
        return this.model;
    }
    
}
