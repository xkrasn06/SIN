/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sin;

import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Milan
 */
public class CrossroadController extends CyclicBehaviour{

    @Override
    public void action() {
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException ex) {
            Logger.getLogger(CrossroadController.class.getName()).log(Level.SEVERE, null, ex);
        }
        Crossroads.WESTtoEAST = Crossroads.WESTtoNORTH = 1;
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException ex) {
            Logger.getLogger(CrossroadController.class.getName()).log(Level.SEVERE, null, ex);
        }
        Crossroads.EASTtoWEST= Crossroads.EASTtoSOUTH = 1;
        Crossroads.WESTtoEAST = Crossroads.WESTtoNORTH = 0;
    }
    
    public void sendChanges() {
        ACLMessage msg = new ACLMessage(ACLMessage.INFORM);
        for(int i=0; i<MainAgent.AgentList.size(); i++)
            msg.addReceiver(Crossroads.crossroadsAID);
    }
    
}
