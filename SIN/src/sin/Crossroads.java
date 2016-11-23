/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sin;

import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.core.behaviours.TickerBehaviour;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Milan
 */
public class Crossroads extends Agent{
    public static int WESTtoEAST = 0;
    public static int WESTtoNORTH = 0;
    public static int EASTtoWEST = 0;
    public static int EASTtoSOUTH = 0;
    public static int SOUTHtoWEST = 0;
    public static int SOUTHtoNORTH = 0;
    public static int SOUTHtoEAST = 0;
    public static int NORTHtoWEST = 0;
    public static int NORTHtoSOUTH = 0;
    public static int NORTHtoEAST = 0;
    public static int STATE = 0;
    
    private boolean crossroadChanged = false;
    private static int WESTtoEASTcars = 0;
    private static int WESTtoNORTHcars= 0;
    public static AID crossroadsAID;
    
     protected void setup() {
         System.out.println("Crosseoads Agent "+ getAID().getName()+ " has started");
         addBehaviour(new CrossroadListener());
         
       //  addBehaviour(new CrossroadController());
        
        crossroadsAID = getAID();
        addBehaviour(new TickerBehaviour(this, 5000) {
            public void onTick() {
                    System.out.println("TICKd" + Crossroads.STATE);
                    setZero(); 
                    
                if (Crossroads.STATE == 0) {
                   
                    Crossroads.WESTtoEAST = Crossroads.WESTtoNORTH = 1;
                    Crossroads.STATE = 1;
                } else
                if (Crossroads.STATE == 1) {
                    System.out.println("TICKdfwaeaw");
                    Crossroads.EASTtoWEST = Crossroads.EASTtoSOUTH = 1;
                    Crossroads.STATE = 0;
                }
                System.out.println("TICKed" + Crossroads.EASTtoWEST);
                
            }
            
            private void setZero() {
                
               Crossroads.WESTtoEAST = 0;Crossroads.WESTtoNORTH = 0;
               Crossroads.EASTtoWEST= 0;Crossroads.EASTtoSOUTH = 0;
            }
        });
        
       }
     
     
     public static int  getWestToEast() {
         return Crossroads.WESTtoEAST;
             
     }
     
     public static int getWestToEastCars() {
         return Crossroads.WESTtoEASTcars;
     }
     
     public static void WestToEastCarsInc() {
         Crossroads.WESTtoEASTcars++;
     }
     public static void WestToEastCarsDec() {
         Crossroads.WESTtoEASTcars--;
     }
     public AID getAI() {
         return getAID();
     }
     
     protected void takeDown() {
        System.out.println("Crossroads "+ getAID().getName()+ " has terminated"); 
    }
}
