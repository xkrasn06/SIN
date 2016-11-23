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
    private final String name;
    private boolean stopped = false;
  
    public CarBehaviour(String name, int from, int to) {
       this.name = name;
       this.from = from;
       this.to = to;
       
    }
    @Override
    public void action() {
       // Panel.updateVehicles(this.posX,this.posY);
       ACLMessage msg = myAgent.receive();
        if (msg != null) {
            String lang = msg.getLanguage();
            String con = msg.getContent();
            
        }
        else {
          
        }
       if(from==MainAgent.WEST && to==MainAgent.EAST) {
            
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException ex) {
                Logger.getLogger(CarBehaviour.class.getName()).log(Level.SEVERE, null, ex);
            }
            for (int i = 0; i < MainAgent.AgentList.size(); i++) {
             
                if(MainAgent.AgentList.get(i).name.equals(name)) {
                    // pozicia zastavenia ciara krizovatky plus pocet aut
                    int newPos=MainAgent.AgentList.get(i).x+Crossroads.getWestToEastCars()*50;
                    if ((newPos > MainAgent.WESTLINE) && (Crossroads.getWestToEast())  == 0) {
                        if (!stopped) Crossroads.WestToEastCarsInc();
                        stopped = true;
                        break;
                    }
                    else {
                        
                        MainAgent.AgentList.get(i).x+=40;
                        if (stopped) 
                            Crossroads.WestToEastCarsDec();
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
    
}
