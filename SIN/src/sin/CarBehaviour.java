/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sin;

import jade.core.behaviours.Behaviour;
import jade.core.behaviours.CyclicBehaviour;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author Milan
 */
public class CarBehaviour extends CyclicBehaviour {
    private static PaintPanel Panel;
    private int posX;
    private int posY;
    private final String name;
  
    public CarBehaviour(String name) {
       this.name = name;
       
    }
    @Override
    public void action() {
       // Panel.updateVehicles(this.posX,this.posY);
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException ex) {
            Logger.getLogger(CarBehaviour.class.getName()).log(Level.SEVERE, null, ex);
        }
        //posX+=5;
        for (int i = 0; i < MainAgent.AgentList.size(); i++) {
             
            if(MainAgent.AgentList.get(i).name.equals(name)) {
                
                MainAgent.AgentList.get(i).x+=5;
                break;
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
