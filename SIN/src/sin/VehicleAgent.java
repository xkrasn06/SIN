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
          Object[] args = getArguments();
          String from = (String) args[0];
          String to = (String) args[1];
          System.out.println("Vehicle Agent path "+ from + to);
          
         System.out.println("True "+ from + to);
         int x =0,y=0;
         if(from.equals("East")) {      
                x = 1080;
                y = 420;
         } else if (from.equals("West")) {
              x = 20;
              y = 420;
         } else if (from.equals("North")) {
              x = 700;
              y = 20;
         } else if (from.equals("South")) {
              x = 700;
              y = 680;
         }
         String s = getAID().getName();
         MainAgent.AgentListElement a;
         a = new MainAgent.AgentListElement(x,y,s);
         MainAgent.AgentList.add(a);
              
          
    }
    
    protected void takeDown() {
         for (int i = 0; i < MainAgent.AgentList.size(); i++) {
              System.out.println("WRITING " + MainAgent.AgentList.get(i).name);
         }
            
        for (int i = 0; i < MainAgent.AgentList.size(); i++) {
             System.out.println("DESTROYING " + getAID().getName());
            if(MainAgent.AgentList.get(i).name.equals(getAID().getName())) {
                MainAgent.AgentList.remove(i);
                System.out.println("DESTROYED "+getAID().getName());
                break;
            }
	}
        System.out.println("Vehicle Agent "+ getAID().getName()+ " has terminated"); 
    }
}
