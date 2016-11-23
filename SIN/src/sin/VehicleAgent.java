/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sin;

import jade.core.Agent;
import jade.lang.acl.ACLMessage;

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
              y = 365;
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
         sendWelcomeMessage(from,to);
         addBehaviour(new CarBehaviour(s, from, to));
         
          
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
    
    private void sendWelcomeMessage(int from, int to) {
        
        ACLMessage msg = new ACLMessage(ACLMessage.INFORM);
        msg.addReceiver(Crossroads.crossroadsAID);
        String fromS = null, toS = null;
        if (from == MainAgent.WEST)
            fromS = "WEST";
        else if (from == MainAgent.NORTH)
            fromS = "NORTH";
        else if (from == MainAgent.EAST)
            fromS = "EAST";
        else if (from == MainAgent.SOUTH)
            fromS = "SOUTH";
        
        if (to == MainAgent.WEST)
            toS = "WEST";
        else if (to == MainAgent.NORTH)
            toS = "NORTH";
        else if (to == MainAgent.EAST)
            toS = "EAST";
        else if (to == MainAgent.SOUTH)
            toS = "SOUTH";
        
        msg.setContent(fromS+"to"+toS);
        
        send(msg);
    }
}
