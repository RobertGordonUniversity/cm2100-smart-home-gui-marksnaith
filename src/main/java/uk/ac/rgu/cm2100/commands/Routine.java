/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.ac.rgu.cm2100.commands;

/**
 *
 * @author mark
 */
public class Routine implements Command{

    Command[] commands;
    
    public Routine(Command... commands){
        this.commands = commands;
    }
    
    
    @Override
    public void execute() {
        for(Command c : this.commands){
            c.execute();
        }
    }
    
}
