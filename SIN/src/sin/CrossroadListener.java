/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sin;

import jade.core.behaviours.CyclicBehaviour;
import jade.core.behaviours.TickerBehaviour;
import jade.core.behaviours.WakerBehaviour;
import jade.lang.acl.ACLMessage;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

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
           // System.out.println(path);
            if (path.equals("WESTtoEAST")) {
                Crossroads.WestToEastCarsInc();
                //System.out.println("WESTtoEAST");
            } else
            if (path.equals("WESTtoSOUTHT")) {
                Crossroads.WestToSouthCarsInc();
                //System.out.println("WESTtoEAST");
            } 
            else
            if (path.equals("WESTtoNORTH")) {
                Crossroads.WestToNorthCarsInc();
               // System.out.println("WESTtoEAST");
            } 
            else
            if (path.equals("EASTtoWEST")) {
                Crossroads.EastToWestCarsInc();
               // System.out.println("WESTtoEAST");
            } else
            if (path.equals("EASTtoSOUTH")) {
                Crossroads.EastToSouthCarsInc();
               // System.out.println("WESTtoEAST");
            } else
            if (path.equals("EASTtoNORTH")) {
                Crossroads.EastToNorthCarsInc();
               // System.out.println("WESTtoEAST");
            } else
            if (path.equals("SOUTHtoNORTH")) {
                Crossroads.SouthToNorthCarsInc();
               // System.out.println("WESTtoEAST");
            } else
            if (path.equals("SOUTHtoEAST")) {
                Crossroads.SouthToEastCarsInc();
               // System.out.println("WESTtoEAST");
            } else
            if (path.equals("SOUTHtoWEST")) {
                Crossroads.SouthToWestCarsInc();
               // System.out.println("WESTtoEAST");
            } else
            if (path.equals("NORTHtoSOUTH")) {
                Crossroads.NorthToSouthCarsInc();
               // System.out.println("WESTtoEAST");
            } else
            if (path.equals("NORTHtoEAST")) {
                Crossroads.NorthToEastCarsInc();
               // System.out.println("WESTtoEAST");
            } else
            if (path.equals("NORTHtoWEST")) {
                Crossroads.NorthToWestCarsInc();
               // System.out.println("WESTtoEAST");
            } 
            
            ////////////////////////////////////////////
            if (path.equals("WESTexitEAST")) {
                Crossroads.WestToEastCarsDec();
                //System.out.println("WESTtoEAST");
            } else
            if (path.equals("WESTexitSOUTHT")) {
                Crossroads.WestToSouthCarsDec();
                //System.out.println("WESTtoEAST");
            } 
            else
            if (path.equals("WESTexitNORTH")) {
                Crossroads.WestToNorthCarsDec();
               // System.out.println("WESTtoEAST");
            } 
            else
            if (path.equals("EASTexitWEST")) {
                Crossroads.EastToWestCarsDec();
               // System.out.println("WESTtoEAST");
            } else
            if (path.equals("EASTexitSOUTH")) {
                Crossroads.EastToSouthCarsDec();
               // System.out.println("WESTtoEAST");
            } else
            if (path.equals("EASTexitNORTH")) {
                Crossroads.EastToNorthCarsDec();
               // System.out.println("WESTtoEAST");
            } else
            if (path.equals("SOUTHexitNORTH")) {
                Crossroads.SouthToNorthCarsDec();
               // System.out.println("WESTtoEAST");
            } else
            if (path.equals("SOUTHexitEAST")) {
                Crossroads.SouthToEastCarsDec();
               // System.out.println("WESTtoEAST");
            } else
            if (path.equals("SOUTHexitWEST")) {
                Crossroads.SouthToWestCarsDec();
               // System.out.println("WESTtoEAST");
            } else
            if (path.equals("NORTHexitSOUTH")) {
                Crossroads.NorthToSouthCarsDec();
               // System.out.println("WESTtoEAST");
            } else
            if (path.equals("NORTHexitEAST")) {
                Crossroads.NorthToEastCarsDec();
               // System.out.println("WESTtoEAST");
            } else
            if (path.equals("NORTHexitWEST")) {
                Crossroads.NorthToWestCarsDec();
               // System.out.println("WESTtoEAST");
            } 
            
        }
        else {
          
        }
        
        
        
       /* myAgent.addBehaviour(new WakerBehaviour(myAgent, 5000) {
            public void handleElapsedTimeout() {
                
                    setZero(); 
                    
                if (Crossroads.STATE == 0) {
                   
                    Crossroads.WESTtoEAST = Crossroads.WESTtoNORTH = 1;
                    Crossroads.STATE = 1;
                }
                if (Crossroads.STATE == 1) {
                    
                    Crossroads.EASTtoWEST= Crossroads.EASTtoSOUTH = 1;
                    Crossroads.STATE = 0;
                }
                
            }
            
            private void setZero() {
               Crossroads.WESTtoEAST = Crossroads.WESTtoNORTH = 0;
               Crossroads.EASTtoWEST= Crossroads.EASTtoSOUTH = 0;
            }
        });*/
        
        
    }
    
    public void sendChanges() {
        ACLMessage msg = new ACLMessage(ACLMessage.INFORM);
        for(int i=0; i<MainAgent.AgentList.size(); i++)
            msg.addReceiver(Crossroads.crossroadsAID);
    }
    
}
