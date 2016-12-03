/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sin;

import jade.core.AID;
import jade.core.Agent;
import jade.lang.acl.ACLMessage;
import static sin.PaintPanel.Xdiff;
import static sin.PaintPanel.downY;
import static sin.PaintPanel.thirdX;
import static sin.PaintPanel.vertDiff;

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
      //    System.out.println("Vehicle Agent "+ getAID().getName()+ " has started");
          Object[] args = getArguments();
          int from = (int) args[0];
          int to = (int) args[1];
          int type = (int) args[2];
          boolean bus = (boolean) args[3];
         // System.out.println("Vehicle Agent path "+ from + to);
          
       //  System.out.println("True "+ from + to);
         int x =0,y=0;
         if((from == MainAgent.EAST) && (to==MainAgent.WEST)) {      
                x = PaintPanel.fifthX-80;
                y = 260;
         } else if ((from==MainAgent.WEST) && (to==MainAgent.EAST)) {
              x = 20;
              y = 365;
         } else if ((from==MainAgent.NORTH)  && (to == MainAgent.SOUTH)){
              x = 670;
              y = 20;
         } else if ((from==MainAgent.SOUTH) && (to == MainAgent.NORTH)) {
              x = downY-80;
              y = thirdX+Xdiff-vertDiff;
         } else if ((from==MainAgent.WEST) && (to==MainAgent.NORTH)) {
              x = 20;
              y = 315;
         } else if((from == MainAgent.EAST) && (to==MainAgent.SOUTH)) {      
                x = PaintPanel.fifthX-80;
                y = 310;
         } else if ((from==MainAgent.SOUTH) && (to == MainAgent.WEST)) {
              x = downY-80-vertDiff;
              y = thirdX+Xdiff-vertDiff;
         } else if ((from==MainAgent.SOUTH) && (to == MainAgent.EAST)) {
              x = downY-80+vertDiff;
              y = thirdX+Xdiff-vertDiff;
         } else if ((from==MainAgent.NORTH)  && (to == MainAgent.EAST)){
              x = 720;
              y = 20;
         }
         else if ((from==MainAgent.NORTH)  && (to == MainAgent.WEST)){
              x = 620;
              y = 20;
         }else if ((from==MainAgent.WEST) && (to==MainAgent.SOUTH)) {
              x = 20;
              y = 415;
         } else if((from == MainAgent.EAST) && (to==MainAgent.NORTH)) {      
                x = PaintPanel.fifthX-80;
                y = 210;
         }
         AID s = getAID();
         MainAgent.AgentListElement a;
         a = new MainAgent.AgentListElement(x,y,type,s,bus);
         MainAgent.AgentList.add(a);
         sendWelcomeMessage(from,to, true, bus);
         addBehaviour(new CarBehaviour(s, from, to, bus));
         
          
    }
    
    protected void takeDown() {
      /*   for (int i = 0; i < MainAgent.AgentList.size(); i++) {
              System.out.println("WRITING " + MainAgent.AgentList.get(i).name);
         }*/
            
        for (int i = 0; i < MainAgent.AgentList.size(); i++) {
      //       System.out.println("DESTROYING " + getAID().getName());
            if(MainAgent.AgentList.get(i).name.equals(getAID().getName())) {
                MainAgent.AgentList.remove(i);
         //       System.out.println("DESTROYED "+getAID().getName());
                break;
            }
	}
      //  System.out.println("Vehicle Agent "+ getAID().getName()+ " has terminated"); 
    }
    
    public void sendWelcomeMessage(int from, int to, boolean dir, boolean bus) {
        
        ACLMessage msg = new ACLMessage(ACLMessage.INFORM);
        msg.addReceiver(Crossroads.crossroadsAID);
        String fromS = null, toS = null;
        if (bus) msg.setLanguage("bus"); 
        else msg.setLanguage("nobus");
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
        if (dir==true) {
           
            msg.setContent(fromS+"to"+toS);
        }
        else {
            
           
           msg.setContent(fromS+"exit"+toS);
        }
        send(msg);
    }
}
