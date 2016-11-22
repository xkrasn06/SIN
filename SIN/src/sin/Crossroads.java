/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sin;

import jade.core.Agent;

/**
 *
 * @author Milan
 */
public class Crossroads extends Agent{
    private static int WESTtoEAST = 0;
    private static int WESTtoNORTH = 0;
    private static int WESTtoEASTcars = 0;
    private static int WESTtoNORTHcars= 0;
    
     protected void setup() {
        
       }
    
     public static int getWestToEast() {
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
}
