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
    
    private int from;
    private int to;
    private int type;
    private String name;
    protected void setup() {
          System.out.println("Vehicle Agent "+ getAID().getName()+ " has started");
          Object[] args = getArguments();
          int from = (int) args[0];
          int to = (int) args[1];
          int type = (int) args[2];
          System.out.println("Vehicle Agent path "+ from + to);
          
         System.out.println("True "+ from + to);
         int x =0,y=0;
         if(from == MainAgent.EAST) {      
                x = 1080;
                y = 420;
         } else if (from==MainAgent.WEST) {
              x = 20;
              y = 420;
         } else if (from==MainAgent.NORTH) {
              x = 700;
              y = 20;
         } else if (from==MainAgent.SOUTH) {
              x = 700;
              y = 680;
         }
         String s = getAID().getName();
         MainAgent.AgentListElement a;
         a = new MainAgent.AgentListElement(x,y,type,s);
         MainAgent.AgentList.add(a);
         
         addBehaviour(new CarBehaviour(s));
         
          
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
