/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.ac.rgu.cm2100.gui.mvc;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

/**
 * Abstract class to model a Model in an MVC architecture
 * Provies property change support to notify controllers of changes
 * @author mark
 */
public abstract class Model {
    
    private final PropertyChangeSupport pcs;
    
    /**
     * Default constructor
     */
    public Model(){
        pcs = new PropertyChangeSupport(this);
    }
    
    /**
     * Add the given PropertyChangeListener to this Model's PropertyChangeSupport
     * @param listener 
     */
    public final void addPropertyChangeSupportListener(PropertyChangeListener listener){
        this.pcs.addPropertyChangeListener(listener);
    }
    
    /**
     * Fire a PropertyChangeEvent for the given property name
     * Events only pass this model and the name, not the old and new values
     * Controllers map property names to their own code for obtaining the updated value(s)
     * @param name 
     */
    protected final void firePropertyChange(String name){
        PropertyChangeEvent evt = new PropertyChangeEvent(this, name, null, null);
        this.pcs.firePropertyChange(evt);
    }
}
