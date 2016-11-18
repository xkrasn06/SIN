/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sin;
import jade.core.Agent;
import jade.core.AID;

/**
 * @author Milan
 */
public class MainAgent extends Agent{
    protected void setup() {
        System.out.println("MainnAgent "+ getAID().getName()+ " has started");
        String myStrings[];
        myStrings = new String[] { "One", "Two", "Three" };
        MainWindow.main(myStrings);
    }
    
    
    protected void takeDown() {
        System.out.println("MainnAgent "+ getAID().getName()+ " has terminated"); 
    }
}
