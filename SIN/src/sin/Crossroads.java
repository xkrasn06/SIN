/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sin;

import jade.core.AID;
import jade.core.Agent;

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
    
    private boolean crossroadChanged = false;
    private static int WESTtoEASTcars = 0;
    private static int WESTtoNORTHcars= 0;
    public static AID crossroadsAID;
    
     protected void setup() {
         System.out.println("Crosseoads Agent "+ getAID().getName()+ " has started");
         addBehaviour(new CrossroadController());
        addBehaviour(new CrossroadListener());
        crossroadsAID = getAID();
        
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
}
