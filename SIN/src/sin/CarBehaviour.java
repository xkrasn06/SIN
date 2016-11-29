/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sin;

import jade.core.AID;
import jade.core.behaviours.Behaviour;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author Milan
 */
public class CarBehaviour extends CyclicBehaviour {
    private static PaintPanel Panel;
    private int from;
    private int to;
    private final AID name;
    private boolean stopped = false;
    private boolean passed = false;
    private boolean sent = false;
    private int quenum = 0;
    public CarBehaviour(AID name, int from, int to) {
       this.name = name;
       this.from = from;
       this.to = to;
       
    }
    @Override
    public void action() {
       // Panel.updateVehicles(this.posX,this.posY);
       //System.out.println(Crossroads.getWestToEastCars());
       ACLMessage msg = myAgent.receive();
        if (msg != null) {
            String lang = msg.getLanguage();
            String con = msg.getContent();
            
        }
        else {
          
        }
       if(from==MainAgent.WEST && to==MainAgent.EAST) {
            
            try {
                TimeUnit.MILLISECONDS.sleep(50);
            } catch (InterruptedException ex) {
                Logger.getLogger(CarBehaviour.class.getName()).log(Level.SEVERE, null, ex);
            }
            for (int i = 0; i < MainAgent.AgentList.size(); i++) {
             
                if(MainAgent.AgentList.get(i).name.equals(name)) {
                    // pozicia zastavenia ciara krizovatky plus pocet aut
                    int newPos=MainAgent.AgentList.get(i).x+Crossroads.getWestToEastCars()*50;
                    if ((newPos > MainAgent.WESTLINE) && (Crossroads.getWestToEast() == 0) && (!passed)) {
                        if (!stopped) Crossroads.WestToEastCarsInc();
                        stopped = true;
                        break;
                    }
                    else {
                        
                        MainAgent.AgentList.get(i).x+=5;
                        //if (stopped) { 
                            
                      //      Crossroads.WestToEastCarsDec();
                     //   }
                        if(MainAgent.AgentList.get(i).x>MainAgent.WESTLINE) {
                            passed = true;
                            if(!sent) {
                                sendWelcomeMessage(from,to, false);
                                sent = true;
                            }
                        }
                        if(MainAgent.AgentList.get(i).x>PaintPanel.fifthX)
                            myAgent.doDelete();
                    }
                    break;
                }
            }
       }
       MainAgent.paint.repaint();
       
        
    }

  /*  @Override
    public boolean done() {
       return true;
    }*/
    
    public void setPanel(PaintPanel p) {
        Panel = p;
        
    }
    
    public void sendWelcomeMessage(int from, int to, boolean dir) {
        
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
        if (dir==true)
            msg.setContent(fromS+"to"+toS);
        else msg.setContent(fromS+"exit"+toS);
        myAgent.send(msg);
    }
    
}
