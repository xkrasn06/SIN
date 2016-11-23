/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sin;

import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;

/**
 *
 * @author Milan
 */
public class CrossroadListener extends CyclicBehaviour{

    @Override
    public void action() {
        ACLMessage msg = myAgent.receive();
        if (msg != null) {
            String title = msg.getLanguage();
            String path = msg.getContent();
            System.out.println(path);
            if (path.equals("WESTtoEAST")) {
                Crossroads.WestToEastCarsInc();
                System.out.println("WESTtoEAST");
            } 
        }
        else {
          
        }
        
        
    }
    
}
