/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sin;

import jade.core.Agent;

/**
 *
 * @author Milan
 */
public class VehicleAgent extends Agent {
    protected void setup() {
          System.out.println("Vehicle Agent "+ getAID().getName()+ " has started");
    }
    
    protected void takeDown() {
        System.out.println("Vehicle Agent "+ getAID().getName()+ " has terminated"); 
    }
}
