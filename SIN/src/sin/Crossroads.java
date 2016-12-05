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
    public static int LASTSTATE = 0;
    public static int LASTSTATE2 = 0;
    public static boolean WAIT = true;
    
    private boolean crossroadChanged = false;
    private static int WESTtoEASTcars = 0;
    private static int WESTtoNORTHcars= 0;
    private static int WESTtoSOUTHcars= 0;
    
    private static int EASTtoWESTcars= 0;
    private static int EASTtoSOUTHcars= 0;
    private static int EASTtoNORTHcars= 0;
    
    private static int SOUTHtoNORTHcars= 0;
    private static int SOUTHtoEASTcars= 0;
    private static int SOUTHtoWESTcars= 0;
    
    private static int NORTHtoSOUTHcars= 0;
    private static int NORTHtoEASTcars= 0;
    private static int NORTHtoWESTcars= 0;
    
    public static int waitEW= 0;
    public static int waitWE= 0;
    public static int waitSN= 0;
    public static int waitNS= 0;
    public static int waitWN= 0;
    public static int waitES= 0;
    public static int waitSE= 0;
    public static int waitSW= 0;
    public static int waitNE= 0;
    public static int waitNW= 0;
    public static int waitWS= 0;
    public static int waitEN= 0;
    public static int crossroadInUseNorth = 0;
    public static int crossroadInUseSouth = 0;
    public static AID crossroadsAID;
    
    public static boolean SMART = false;
    public static int SMARTcount = 0;
    public static int SMARTOffcount = 0;
    public static boolean SMARTtempOFF = false;
    
     protected void setup() {
        // System.out.println("Crosseoads Agent "+ getAID().getName()+ " has started");
         addBehaviour(new CrossroadListener());
         
       //  addBehaviour(new CrossroadController());
        
        crossroadsAID = getAID();
        addBehaviour(new TickerBehaviour(this, 5000) {
            public void onTick() {
                 //System.out.println("TICKd" + Crossroads.SMART);
                    setZero(); 
                if((Crossroads.SMART) && (!Crossroads.SMARTtempOFF)){
                    Crossroads.SMARTcount++;
                    if (Crossroads.SMARTcount == 7) {
                        Crossroads.SMARTtempOFF = true;
                        Crossroads.SMARTcount=0;
                    }
                    int westCars = WESTtoNORTHcars + WESTtoEASTcars + WESTtoSOUTHcars;
                    int eastCars = EASTtoNORTHcars + EASTtoWESTcars + EASTtoSOUTHcars;
                    int southCars = SOUTHtoWESTcars + SOUTHtoNORTHcars + SOUTHtoEASTcars;
                    int northCars = NORTHtoWESTcars + NORTHtoSOUTHcars + NORTHtoEASTcars;
                    int[] numbers = new int[12];
                    numbers[0] = westCars;
                    numbers[1] = eastCars;
                    numbers[2] = southCars;
                    numbers[3] = northCars;
                    numbers[4] = WESTtoNORTHcars + NORTHtoWESTcars + EASTtoSOUTHcars;
                    numbers[5] = WESTtoEASTcars+EASTtoWESTcars;
                    numbers[6] = EASTtoSOUTHcars + SOUTHtoEASTcars + WESTtoNORTHcars + NORTHtoWESTcars;
                    numbers[7] = SOUTHtoNORTHcars + NORTHtoWESTcars + SOUTHtoEASTcars;
                    numbers[8] = SOUTHtoNORTHcars + SOUTHtoEASTcars + NORTHtoWESTcars;
                    numbers[9] = SOUTHtoWESTcars + NORTHtoEASTcars;
                    numbers[10] = NORTHtoSOUTHcars+SOUTHtoNORTHcars + NORTHtoWESTcars + SOUTHtoEASTcars;
                    numbers[11] = NORTHtoWESTcars + WESTtoNORTHcars + SOUTHtoEASTcars;
                    
                    int maxPos = 0;
                   /* int max =Math.max(Math.max(westCars,eastCars),Math.max(southCars,northCars));
                    if (max == eastCars) maxPos = 1;
                    else if (max == southCars) maxPos = 2;
                    else if (max == northCars) maxPos = 3;*/
                   
                    for(int i = 0; i < numbers.length; i++) {
                        if(numbers[i] > numbers[maxPos]) {
                            maxPos = i;
                        }
                    }
                    
                    LASTSTATE2 = LASTSTATE;
                    LASTSTATE = STATE-1;
                    if (LASTSTATE <0) LASTSTATE = 3;
                    
                    if(maxPos != LASTSTATE2) STATE = maxPos;
                    else  {
                        int max;
                        if (maxPos>0) max = 0;
                        else max = 1;
                        for(int i = 0; i < numbers.length; i++) {
                            if(numbers[i] > numbers[max]) {
                                if (i != maxPos) max = i;
                            }
                        
                        }
                        STATE = max;
                    }
                    
                    if(STATE == LASTSTATE) Crossroads.WAIT = false;
                    System.out.println("maxPos " + westCars);
                    System.out.println("maxPos " + eastCars);
                    System.out.println("maxPos " + northCars);
                    System.out.println("maxPos " + southCars);
                }
                if (Crossroads.WAIT) {
                    
                    try {
                        TimeUnit.MILLISECONDS.sleep(3000);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(CarBehaviour.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    Crossroads.WAIT = false;
                    //return;
                }  
                if(Crossroads.SMARTtempOFF) {
                    System.out.println("TEMP OFF");
                    Crossroads.SMARTOffcount++;
                    if (Crossroads.SMARTOffcount == 3) {
                        Crossroads.SMARTtempOFF = false;
                        Crossroads.SMARTOffcount=0;
                    }
                }
                if (Crossroads.STATE == 0) {
                   
                    Crossroads.WESTtoEAST = Crossroads.WESTtoNORTH = Crossroads.NORTHtoWEST = 1;
                    Crossroads.STATE = 1;
                    Crossroads.WAIT = true;
                } else
                if (Crossroads.STATE == 1) {
                   // System.out.println("TICKdfwaeaw");
                    Crossroads.EASTtoWEST = Crossroads.EASTtoSOUTH = Crossroads.SOUTHtoEAST = 1;
                    
                    Crossroads.STATE = 2;
                     Crossroads.WAIT = true;
                } else
                if (Crossroads.STATE == 2) {
                    //System.out.println("TICKdfwaeaw");
                    Crossroads.SOUTHtoWEST = Crossroads.SOUTHtoNORTH = Crossroads.SOUTHtoEAST = 1;
                    Crossroads.STATE = 3;
                     Crossroads.WAIT = true;
                } else
                if (Crossroads.STATE == 3) {
                    //System.out.println("TICKdfwaeaw");
                    Crossroads.NORTHtoWEST = Crossroads.NORTHtoSOUTH = Crossroads.NORTHtoEAST = 1;
                    if (!Crossroads.SMART) Crossroads.STATE = 0;
                    else Crossroads.STATE = 4;
                    Crossroads.WAIT = true;
                } else
                if (Crossroads.STATE == 4) {
                    //System.out.println("TICKdfwaeaw");
                    Crossroads.NORTHtoWEST = Crossroads.WESTtoNORTH = Crossroads.EASTtoSOUTH = 1;
                    if (!Crossroads.SMART) Crossroads.STATE = 0;
                    else Crossroads.STATE = 5;
                    Crossroads.WAIT = true;
                }
                else
                if (Crossroads.STATE == 5) {
                    //System.out.println("TICKdfwaeaw");
                    Crossroads.WESTtoEAST = Crossroads.EASTtoWEST = 1;
                    if (!Crossroads.SMART) Crossroads.STATE = 0;
                    else Crossroads.STATE = 6;
                    Crossroads.WAIT = true;
                } else
                if (Crossroads.STATE == 6) {
                    //System.out.println("TICKdfwaeaw");
                    Crossroads.EASTtoSOUTH = Crossroads.SOUTHtoEAST = Crossroads.WESTtoNORTH = Crossroads.NORTHtoWEST = 1;
                    if (!Crossroads.SMART) Crossroads.STATE = 0;
                    else Crossroads.STATE = 7;
                    Crossroads.WAIT = true;
                } else
                if (Crossroads.STATE == 7) {
                    //System.out.println("TICKdfwaeaw");
                    Crossroads.SOUTHtoNORTH = Crossroads.NORTHtoWEST = Crossroads.SOUTHtoEAST = 1;
                    if (!Crossroads.SMART) Crossroads.STATE = 0;
                    else Crossroads.STATE = 8;
                    Crossroads.WAIT = true;
                } else
                if (Crossroads.STATE == 8) {
                    //System.out.println("TICKdfwaeaw");
                    Crossroads.SOUTHtoNORTH = Crossroads.SOUTHtoEAST = Crossroads.NORTHtoWEST = 1;
                    if (!Crossroads.SMART) Crossroads.STATE = 0;
                    else Crossroads.STATE = 9;
                    Crossroads.WAIT = true;
                } else
                if (Crossroads.STATE == 9) {
                    //System.out.println("TICKdfwaeaw");
                    Crossroads.SOUTHtoWEST = Crossroads.NORTHtoEAST = 1;
                    if (!Crossroads.SMART) Crossroads.STATE = 0;
                    else Crossroads.STATE = 10;
                    Crossroads.WAIT = true;
                } else
                if (Crossroads.STATE == 10) {
                    //System.out.println("TICKdfwaeaw");
                    Crossroads.NORTHtoSOUTH = Crossroads.SOUTHtoNORTH = Crossroads.NORTHtoWEST = Crossroads.SOUTHtoEAST =1;
                    if (!Crossroads.SMART) Crossroads.STATE = 0;
                    else Crossroads.STATE = 11;
                    Crossroads.WAIT = true;
                } else
                if (Crossroads.STATE == 11) {
                    //System.out.println("TICKdfwaeaw");
                    Crossroads.NORTHtoWEST = Crossroads.WESTtoNORTH = Crossroads.SOUTHtoEAST = 1;
                    Crossroads.STATE = 0;
                   
                    Crossroads.WAIT = true;
                }
                //System.out.println("TICKed" + Crossroads.EASTtoWEST);
                
            }
            
            private void setZero() {
                
               Crossroads.WESTtoEAST = 0;Crossroads.WESTtoNORTH = 0;
               Crossroads.EASTtoWEST= 0;Crossroads.EASTtoSOUTH = 0;
               Crossroads.NORTHtoWEST = Crossroads.NORTHtoSOUTH = Crossroads.NORTHtoEAST = 0;
               Crossroads.SOUTHtoWEST = Crossroads.SOUTHtoNORTH = Crossroads.SOUTHtoEAST = 0;
            }
        });
        
       }
     
     
     public static int  getWestToEast() {
         return Crossroads.WESTtoEAST;
     }
     public static int  getEastToWest() {
         return Crossroads.EASTtoWEST;
     }
     public static int  getWestToNorth() {
         return Crossroads.WESTtoNORTH;
     }
     public static int  getEastToSouth() {
         return Crossroads.EASTtoSOUTH;
     }
     public static int  getNorthToSouth() {
         return Crossroads.NORTHtoSOUTH;
     }
     public static int  getNorthToEast() {
         return Crossroads.NORTHtoEAST;
     }
     public static int  getNorthToWest() {
         return Crossroads.NORTHtoWEST;
     }
     public static int  getSouthToNorth() {
         return Crossroads.SOUTHtoNORTH;
     }
     public static int  getSouthToEast() {
         return Crossroads.SOUTHtoEAST;
     }
     public static int  getSouthToWest() {
         return Crossroads.SOUTHtoWEST;
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
     
     public static void WestToSouthCarsInc() {
         Crossroads.WESTtoSOUTHcars++;
     }
     public static void WestToSouthCarsDec() {
         Crossroads.WESTtoSOUTHcars--;
     }
     
     public static void WestToNorthCarsInc() {
         Crossroads.WESTtoNORTHcars++;
     }
     public static void WestToNorthCarsDec() {
         Crossroads.WESTtoNORTHcars--;
     }
     
     public static void EastToWestCarsInc() {
         Crossroads.EASTtoWESTcars++;
     }
     public static void EastToWestCarsDec() {
         Crossroads.EASTtoWESTcars--;
     }
     public static void EastToSouthCarsInc() {
         Crossroads.EASTtoSOUTHcars++;
     }
     public static void EastToSouthCarsDec() {
         Crossroads.EASTtoSOUTHcars--;
     }
     public static void EastToNorthCarsInc() {
         Crossroads.EASTtoNORTHcars++;
     }
     public static void EastToNorthCarsDec() {
         Crossroads.EASTtoNORTHcars--;
     }
     
     public static void SouthToNorthCarsInc() {
         Crossroads.SOUTHtoNORTHcars++;
     }
     public static void SouthToNorthCarsDec() {
         Crossroads.SOUTHtoNORTHcars--;
     }
     
     public static void SouthToEastCarsInc() {
         Crossroads.SOUTHtoEASTcars++;
     }
     public static void SouthToEastCarsDec() {
         Crossroads.SOUTHtoEASTcars--;
     }
     
     public static void SouthToWestCarsInc() {
         Crossroads.SOUTHtoWESTcars++;
     }
     public static void SouthToWestCarsDec() {
         Crossroads.SOUTHtoWESTcars--;
     }
     
     public static void NorthToSouthCarsInc() {
         Crossroads.NORTHtoSOUTHcars++;
     }
     public static void NorthToSouthCarsDec() {
         Crossroads.NORTHtoSOUTHcars--;
     }
     
     public static void NorthToEastCarsInc() {
         Crossroads.NORTHtoEASTcars++;
     }
     public static void NorthToEastCarsDec() {
         Crossroads.NORTHtoEASTcars--;
     }
     
     public static void NorthToWestCarsInc() {
         Crossroads.NORTHtoWESTcars++;
     }
     public static void NorthToWestCarsDec() {
         Crossroads.NORTHtoWESTcars--;
     }
     public static int getWEcars(){
         return WESTtoEASTcars;
     }
     public static int getEWcars(){
         return EASTtoWESTcars;
     } 
      public static int getSNcars(){
         return SOUTHtoNORTHcars;
     }
     public static int getNScars(){
         return NORTHtoSOUTHcars;
     }
      public static int getWNcars(){
         return WESTtoNORTHcars;
     }
     
      public static int getEScars(){
         return EASTtoSOUTHcars;
     }
     
     public static int getSWcars(){
         return SOUTHtoWESTcars;
     }
     
     public static int getSEcars(){
         return SOUTHtoEASTcars;
     }
     
     public static int getNEcars(){
         return NORTHtoEASTcars;
     }
     
      public static int getNWcars(){
         return NORTHtoWESTcars;
     }
      
     public static int getWScars(){
         return WESTtoSOUTHcars;
     }
     
    public static int getENcars(){
         return EASTtoNORTHcars;
     }
     
     public AID getAI() {
         return getAID();
     }
     
     protected void takeDown() {
       // System.out.println("Crossroads "+ getAID().getName()+ " has terminated"); 
    }
}
