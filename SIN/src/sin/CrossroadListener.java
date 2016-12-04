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
           boolean bus = false;
          // System.out.println(title);
           if (title.equals("bus")) bus = true;
            if (path.equals("WESTtoEAST")) {
                Crossroads.WestToEastCarsInc();
                if(bus) for(int i=0; i<10; i++) Crossroads.WestToEastCarsInc();
                //System.out.println("WESTtoEAST");
            } else
            if (path.equals("WESTtoSOUTHT")) {
                Crossroads.WestToSouthCarsInc();
                if(bus) for(int i=0; i<10; i++) Crossroads.WestToSouthCarsInc();
                //System.out.println("WESTtoEAST");
            } 
            else
            if (path.equals("WESTtoNORTH")) {
                Crossroads.WestToNorthCarsInc();
                if(bus) for(int i=0; i<10; i++) Crossroads.WestToNorthCarsInc();
               // System.out.println("WESTtoEAST");
            } 
            else
            if (path.equals("EASTtoWEST")) {
                Crossroads.EastToWestCarsInc();
                if(bus) for(int i=0; i<10; i++) Crossroads.EastToWestCarsInc();
               // System.out.println("WESTtoEAST");
            } else
            if (path.equals("EASTtoSOUTH")) {
                Crossroads.EastToSouthCarsInc();
                if(bus) for(int i=0; i<10; i++) Crossroads.EastToSouthCarsInc();
               // System.out.println("WESTtoEAST");
            } else
            if (path.equals("EASTtoNORTH")) {
                Crossroads.EastToNorthCarsInc();
                if(bus) for(int i=0; i<10; i++) Crossroads.EastToNorthCarsInc();
               // System.out.println("WESTtoEAST");
            } else
            if (path.equals("SOUTHtoNORTH")) {
                Crossroads.SouthToNorthCarsInc();
                if(bus) for(int i=0; i<10; i++) Crossroads.SouthToNorthCarsInc();
               // System.out.println("WESTtoEAST");
            } else
            if (path.equals("SOUTHtoEAST")) {
                Crossroads.SouthToEastCarsInc();
                if(bus) for(int i=0; i<10; i++) Crossroads.SouthToEastCarsInc();
               // System.out.println("WESTtoEAST");
            } else
            if (path.equals("SOUTHtoWEST")) {
                Crossroads.SouthToWestCarsInc();
                if(bus) for(int i=0; i<10; i++) Crossroads.SouthToWestCarsInc();
               // System.out.println("WESTtoEAST");
            } else
            if (path.equals("NORTHtoSOUTH")) {
                Crossroads.NorthToSouthCarsInc();
                 if(bus) for(int i=0; i<10; i++) Crossroads.NorthToSouthCarsInc();
               // System.out.println("WESTtoEAST");
            } else
            if (path.equals("NORTHtoEAST")) {
                Crossroads.NorthToEastCarsInc();
                if(bus) for(int i=0; i<10; i++) Crossroads.NorthToEastCarsInc();
               // System.out.println("WESTtoEAST");
            } else
            if (path.equals("NORTHtoWEST")) {
                Crossroads.NorthToWestCarsInc();
                 if(bus) for(int i=0; i<10; i++) Crossroads.NorthToWestCarsInc();
               // System.out.println("WESTtoEAST");
            } 
            
            ////////////////////////////////////////////
            if (path.equals("WESTexitEAST")) {
                Crossroads.WestToEastCarsDec();
                if(bus) for(int i=0; i<10; i++) Crossroads.WestToEastCarsDec();
                //System.out.println("WESTtoEAST");
            } else
            if (path.equals("WESTexitSOUTHT")) {
                Crossroads.WestToSouthCarsDec();
                if(bus) for(int i=0; i<10; i++) Crossroads.WestToSouthCarsDec();
                //System.out.println("WESTtoEAST");
            } 
            else
            if (path.equals("WESTexitNORTH")) {
                Crossroads.WestToNorthCarsDec();
                if(bus) for(int i=0; i<10; i++) Crossroads.WestToNorthCarsDec();
               // System.out.println("WESTtoEAST");
            } 
            else
            if (path.equals("EASTexitWEST")) {
                Crossroads.EastToWestCarsDec();
                if(bus) for(int i=0; i<10; i++) Crossroads.EastToWestCarsDec();
               // System.out.println("WESTtoEAST");
            } else
            if (path.equals("EASTexitSOUTH")) {
                Crossroads.EastToSouthCarsDec();
                if(bus) for(int i=0; i<10; i++) Crossroads.EastToSouthCarsDec();
               // System.out.println("WESTtoEAST");
            } else
            if (path.equals("EASTexitNORTH")) {
                Crossroads.EastToNorthCarsDec();
                if(bus) for(int i=0; i<10; i++) Crossroads.EastToNorthCarsDec();
               // System.out.println("WESTtoEAST");
            } else
            if (path.equals("SOUTHexitNORTH")) {
                Crossroads.SouthToNorthCarsDec();
                if(bus) for(int i=0; i<10; i++) Crossroads.SouthToNorthCarsDec();
               // System.out.println("WESTtoEAST");
            } else
            if (path.equals("SOUTHexitEAST")) {
                Crossroads.SouthToEastCarsDec();
                if(bus) for(int i=0; i<10; i++) Crossroads.SouthToEastCarsDec();
               // System.out.println("WESTtoEAST");
            } else
            if (path.equals("SOUTHexitWEST")) {
                Crossroads.SouthToWestCarsDec();
                if(bus) for(int i=0; i<10; i++) Crossroads.SouthToWestCarsDec();
               // System.out.println("WESTtoEAST");
            } else
            if (path.equals("NORTHexitSOUTH")) {
                Crossroads.NorthToSouthCarsDec();
                if(bus) for(int i=0; i<10; i++) Crossroads.NorthToSouthCarsDec();
               // System.out.println("WESTtoEAST");
            } else
            if (path.equals("NORTHexitEAST")) {
                Crossroads.NorthToEastCarsDec();
                 if(bus) for(int i=0; i<10; i++) Crossroads.NorthToEastCarsDec();
               // System.out.println("WESTtoEAST");
            } else
            if (path.equals("NORTHexitWEST")) {
                Crossroads.NorthToWestCarsDec();
                 if(bus) for(int i=0; i<10; i++) Crossroads.NorthToWestCarsDec();
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
