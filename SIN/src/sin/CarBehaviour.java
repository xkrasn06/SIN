/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sin;

import jade.core.behaviours.Behaviour;
import static sin.MainAgent.paint;

/**
 *
 * @author Milan
 */
public class CarBehaviour extends Behaviour {
    private static PaintPanel Panel;
    private int posX;
    private int posY;
    public CarBehaviour(PaintPanel p, int x, int y) {
       this.Panel = p; 
       this.posX = x;
       this.posY = y;
    }
    @Override
    public void action() {
        Panel.updateVehicles(this.posX,this.posY);
        posX++;
       
        
    }

    @Override
    public boolean done() {
       return true;
    }
    
    public void setPanel(PaintPanel p) {
        Panel = p;
        
    }
    
}
