/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sin;

import jade.core.AID;
import jade.core.behaviours.Behaviour;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import static sin.PaintPanel.Xdiff;
import static sin.PaintPanel.thirdX;
import static sin.PaintPanel.vertDiff;


/**
 *
 * @author Milan
 */
public class CarBehaviour extends CyclicBehaviour {
    private static PaintPanel Panel;
     
    
    private final int from;
    private final int to;
    private final AID name;
    private final boolean bus;
    private boolean stopped = false;
    private boolean passed = false;
    private boolean sent = false;
    private boolean red = false;
    private boolean left = false;
    private boolean turned = false;
    private int que = -1;
    
    public CarBehaviour(AID name, int from, int to, boolean bus) {
       this.name = name;
       this.from = from;
       this.to = to;
       this.bus = bus;
    }
    
    @Override
    public void action() {
       
       ACLMessage msg = myAgent.receive();
        if (msg != null) {
            String lang = msg.getLanguage();
            String con = msg.getContent();
        }
        else {
          
        }
       
       // jednotlive smery pohybu auta, analogicke pre vsetky smery
       if(from==MainAgent.WEST && to==MainAgent.EAST) {
           
            // posun auta, 5px za 50ms vyzeralo "prijatelne"
            try {
                TimeUnit.MILLISECONDS.sleep(50);
            } catch (InterruptedException ex) {
                Logger.getLogger(CarBehaviour.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            // najdeme agenta, ktoremu bol priradeny tento behaviour
            for (int i = 0; i < MainAgent.AgentList.size(); i++) {
             
                if(MainAgent.AgentList.get(i).name.equals(name)) {
                    
                    // nova pozicia auta
                    int newPos=MainAgent.AgentList.get(i).x+5; 
                    
                    // kontrola stavu krizovatky
                    if (Crossroads.getWestToEast() == 0) red= true;
                    else red = false;
                    
                    // stojime / budeme stat na cervenej
                    if (red) {
                        
                        // pomocny flag
                        stopped = true;
                        
                        // este nebola priradena pozicia na krizovatke => priradime
                        if (que<0)
                            que = Crossroads.waitWE++;
                        
                        // stojime ak by nova pozicia prekrocila priradene cakacie miesto na krizovatke
                        if ((newPos > MainAgent.WESTLINE-que*50) && (!passed)) {
                          break;
                        }
                        // inak posunieme autom
                        else  MainAgent.AgentList.get(i).x+=5;
                    }
                    // nie je cervena
                    else {
                        MainAgent.AgentList.get(i).x+=5;
                        
                        // presli sme hranicu krizovatky
                        if(MainAgent.AgentList.get(i).x>MainAgent.WESTLINE) {
                            // pomocny flag
                            passed = true;
                            // este sme neposlali spravu krizovatke o prejdeni
                            if(!sent) {
                                // pokial auto stalo
                                if(stopped) { 
                                    //znizuje sa pocet aut v cakani na krizovatke
                                    Crossroads.waitWE--;
                                    // osetrenie bugu
                                    if ( Crossroads.waitWE<0)  Crossroads.waitWE = 0;
                                    stopped = false;
                                }
                                // oznam krizovatke
                                sendWelcomeMessage(from,to, false,bus);
                                sent = true;
                            }
                        }
                        
                    }
                    // znicenie agenta pri opusteni obrazovky
                    if(MainAgent.AgentList.get(i).x>PaintPanel.fifthX)
                            myAgent.doDelete();
                }
            }
       } else
           
           // ostatne smery su analogicke, komentovane su iba zmeny
           if(from==MainAgent.EAST && to==MainAgent.WEST) {
        
            try {
                TimeUnit.MILLISECONDS.sleep(50);
            } catch (InterruptedException ex) {
                Logger.getLogger(CarBehaviour.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            for (int i = 0; i < MainAgent.AgentList.size(); i++) {
             
                if(MainAgent.AgentList.get(i).name.equals(name)) {
                    
                    int newPos=MainAgent.AgentList.get(i).x-5; 
                    if (Crossroads.getEastToWest() == 0) red= true;
                    else red = false;
                    if (MainAgent.AgentList.get(i).x < MainAgent.EASTLINE) passed = true;
                    if (red) {
                        stopped = true;
                        
                        if (que<0)
                        que = Crossroads.waitEW++;
                         if ((newPos < MainAgent.EASTLINE+que*50) && (!passed)) {
                           break;
                        }
                        else  MainAgent.AgentList.get(i).x-=5;
                    }
                    else {
                        MainAgent.AgentList.get(i).x-=5;
                        if(MainAgent.AgentList.get(i).x<MainAgent.EASTLINE) {
                            passed = true;
                            if(!sent) {
                                if(stopped) { 
                                  
                                    Crossroads.waitEW--;
                                    if ( Crossroads.waitEW<0)  Crossroads.waitEW = 0;
                                    stopped = false;
                                }
                                sendWelcomeMessage(from,to, false,bus);
                                sent = true;
                            }
                        }
                        
                    }
                    if(MainAgent.AgentList.get(i).x<0)
                            myAgent.doDelete();
                   
                }
            }
       } else
            if(from==MainAgent.SOUTH && to==MainAgent.NORTH) {
        
            try {
                TimeUnit.MILLISECONDS.sleep(50);
            } catch (InterruptedException ex) {
                Logger.getLogger(CarBehaviour.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            for (int i = 0; i < MainAgent.AgentList.size(); i++) {
             
                if(MainAgent.AgentList.get(i).name.equals(name)) {
                    
                    int newPos=MainAgent.AgentList.get(i).y-5;
                    if (Crossroads.getSouthToNorth() == 0) red= true;
                        else red = false;
                    if (MainAgent.AgentList.get(i).y < MainAgent.SOUTHLINE) passed = true;
                    if (passed) red = false;
                    if (red) {
                        stopped = true;
                       
                        if (que<0)
                        que = Crossroads.waitSN++;
                         if ((newPos < MainAgent.SOUTHLINE+que*50) && (!passed)) {
                           break;
                        }
                        else  MainAgent.AgentList.get(i).y-=5;
                    }
                    else {
                        MainAgent.AgentList.get(i).y-=5;
                        
                        // osetrenie posunu aj v x-ovej osi
                        if ((MainAgent.AgentList.get(i).y<MainAgent.SOUTHLINE) && (MainAgent.AgentList.get(i).y>MainAgent.NORTHLINE2))
                            MainAgent.AgentList.get(i).x+=1;
                        if(MainAgent.AgentList.get(i).y<MainAgent.SOUTHLINE) {
                            passed = true;
                            if(!sent) {
                                 if(stopped) { 
                                  
                                     Crossroads.waitSN--;
                                    if ( Crossroads.waitSN<0)  Crossroads.waitSN = 0;
                                    stopped = false;
                                }
                               
                                Crossroads.crossroadInUseNorth++;
                                sendWelcomeMessage(from,to, false,bus);
                                sent = true;
                            }
                        }
                        
                    }
                    // flag ze je auto v krizovatke, sluzi pre smery ktore obchadzaju semafor
                    if (!left) {
                     if(MainAgent.AgentList.get(i).y<MainAgent.NORTHLINE) {
                            
                                Crossroads.crossroadInUseNorth--;
                                left = true;
                            }
                     }
                    if(MainAgent.AgentList.get(i).y<0)
                            myAgent.doDelete();
                   
                }
            }
       } else
          if(from==MainAgent.NORTH && to==MainAgent.SOUTH) {
        
            try {
                TimeUnit.MILLISECONDS.sleep(50);
            } catch (InterruptedException ex) {
                Logger.getLogger(CarBehaviour.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            for (int i = 0; i < MainAgent.AgentList.size(); i++) {
             
                if(MainAgent.AgentList.get(i).name.equals(name)) {
                    
                    int newPos=MainAgent.AgentList.get(i).y+5; 
                    if (Crossroads.getNorthToSouth() == 0) red= true;
                    else red = false;
                    if (MainAgent.AgentList.get(i).y > MainAgent.NORTHLINE) passed = true;
                    if (passed) red = false;
                    if (red) {
                         stopped = true;
                         //if (Crossroads.waitNS>Crossroads.getNScars()) Crossroads.waitNS = Crossroads.getNScars();
                        if (que<0)
                        que = Crossroads.waitNS++;
                         if ((newPos > MainAgent.NORTHLINE-que*50) && (!passed)) {
                           break;
                        }
                        else  MainAgent.AgentList.get(i).y+=5;
                    }
                    else {
                        MainAgent.AgentList.get(i).y+=5;
                        if ((MainAgent.AgentList.get(i).y>MainAgent.NORTHLINE) && (MainAgent.AgentList.get(i).y<MainAgent.SOUTHLINE))
                            MainAgent.AgentList.get(i).x-=1;
                        if(MainAgent.AgentList.get(i).y>MainAgent.NORTHLINE) {
                            passed = true;
                            if(!sent) {
                                
                                if(stopped) { 
                                  
                                      Crossroads.waitNS--;
                                    if ( Crossroads.waitNS<0)  Crossroads.waitNS = 0;
                                    stopped = false;
                                }
                               
                                Crossroads.crossroadInUseSouth++;
                                sendWelcomeMessage(from,to, false,bus);
                                sent = true;
                            }
                        }
                        
                    }
                    if (!left)
                    if(MainAgent.AgentList.get(i).y>MainAgent.SOUTHLINE) {
                        Crossroads.crossroadInUseSouth--;
                        left = true;
                    }
                            
                    if(MainAgent.AgentList.get(i).y>PaintPanel.downY)
                            myAgent.doDelete();
                   // break;
                }
            }
       } else if(from==MainAgent.WEST && to==MainAgent.NORTH) {
        
            try {
                TimeUnit.MILLISECONDS.sleep(50);
            } catch (InterruptedException ex) {
                Logger.getLogger(CarBehaviour.class.getName()).log(Level.SEVERE, null, ex);
            }
            //System.out.println(Crossroads.getWestToEastCars());
            for (int i = 0; i < MainAgent.AgentList.size(); i++) {
             
                if(MainAgent.AgentList.get(i).name.equals(name)) {
                   
                    int newPos=MainAgent.AgentList.get(i).x+5; 
                    if (Crossroads.getWestToNorth() == 0) red= true;
                    else red = false;
                    if (MainAgent.AgentList.get(i).x > MainAgent.WESTLINE) passed = true;
                    if (passed) red = false;
                    if (red) {
                         stopped = true;
                         //if (Crossroads.waitWN>Crossroads.getWNcars()) Crossroads.waitWN = Crossroads.getWNcars();
                        if (que<0)
                        que = Crossroads.waitWN++;
                        
                        if ((newPos > MainAgent.WESTLINE-que*50) && (!passed)) {
                           break;
                        }
                        else  MainAgent.AgentList.get(i).x+=5;
                    }
                    else {
                        if (MainAgent.AgentList.get(i).x < thirdX+vertDiff*3+5 )
                            MainAgent.AgentList.get(i).x+=5;
                        else {
                            if (!turned) {
                                MainAgent.AgentList.get(i).type = 1;
                                turned = true;
                            }
                            MainAgent.AgentList.get(i).y-=5;
                        }
                        if((MainAgent.AgentList.get(i).x > MainAgent.WESTLINE) && (MainAgent.AgentList.get(i).x < thirdX+vertDiff*3 ))
                            MainAgent.AgentList.get(i).y-=2;
                        
                        if(MainAgent.AgentList.get(i).x>MainAgent.WESTLINE) {
                            passed = true;
                            if(!sent) {
                                if(stopped) { 
                                  
                                    Crossroads.waitWN--;
                                    if ( Crossroads.waitWN<0)  Crossroads.waitWN = 0;
                                    
                                    stopped = false;
                                }
                                
                                Crossroads.crossroadInUseNorth++;
                                sendWelcomeMessage(from,to, false,bus);
                                sent = true;
                            }
                        }
                        
                    }
                    if (!left) {
                        if(MainAgent.AgentList.get(i).y<MainAgent.NORTHLINE) {
                            Crossroads.crossroadInUseNorth--;
                            left = true;
                        }
                           
                    }
                    if(MainAgent.AgentList.get(i).y<0)
                            myAgent.doDelete();
                   // break;
                }
            }
       } else if(from==MainAgent.EAST && to==MainAgent.SOUTH) {
        
            try {
                TimeUnit.MILLISECONDS.sleep(50);
            } catch (InterruptedException ex) {
                Logger.getLogger(CarBehaviour.class.getName()).log(Level.SEVERE, null, ex);
            }
            //System.out.println(Crossroads.getWestToEastCars());
            for (int i = 0; i < MainAgent.AgentList.size(); i++) {
             
                if(MainAgent.AgentList.get(i).name.equals(name)) {
                    // pozicia zastavenia ciara krizovatky plus pocet aut
                    int newPos=MainAgent.AgentList.get(i).x-5; //+quenum*50;
                    if (Crossroads.getEastToSouth() == 0) red= true;
                    else red = false;
                    if (MainAgent.AgentList.get(i).x < MainAgent.EASTLINE) passed = true;
                    if (passed) red = false;
                    if (red) {
                         stopped = true;
                          //if (Crossroads.waitES>Crossroads.getEScars()) Crossroads.waitES = Crossroads.getEScars();
                        if (que<0)
                        que = Crossroads.waitES++;
                        if ((newPos < MainAgent.EASTLINE+que*50) && (!passed)) {
                           break;
                        }
                        else  MainAgent.AgentList.get(i).x-=5;
                    }
                    else {
                        if (MainAgent.AgentList.get(i).x > thirdX+Xdiff-vertDiff*4+5 )
                            MainAgent.AgentList.get(i).x-=5;
                        else {
                            if (!turned) {
                                MainAgent.AgentList.get(i).type = 1;
                                turned = true;
                            }
                            MainAgent.AgentList.get(i).y+=5;
                        }
                        if((MainAgent.AgentList.get(i).x < MainAgent.EASTLINE) && (MainAgent.AgentList.get(i).x > thirdX+Xdiff-vertDiff*4+5 ))
                            MainAgent.AgentList.get(i).y+=1;
                        if(MainAgent.AgentList.get(i).x<MainAgent.EASTLINE) {
                            passed = true;
                            if(!sent) {
                                if(stopped) { 
                                  
                                     Crossroads.waitES--;
                                    if ( Crossroads.waitES<0)  Crossroads.waitES = 0;
                                    
                                    stopped = false;
                                }
                               
                                Crossroads.crossroadInUseSouth++;
                                sendWelcomeMessage(from,to, false,bus);
                                sent = true;
                            }
                        }
                        
                    }
                    if (!left)
                        if(MainAgent.AgentList.get(i).y>MainAgent.SOUTHLINE+10) {
                            Crossroads.crossroadInUseSouth--;
                            left = true;
                        }
                            
                    if(MainAgent.AgentList.get(i).y>PaintPanel.downY)
                            myAgent.doDelete();
                   // break;
                }
            }
       } else  if(from==MainAgent.SOUTH && to==MainAgent.WEST) {
        
            try {
                TimeUnit.MILLISECONDS.sleep(50);
            } catch (InterruptedException ex) {
                Logger.getLogger(CarBehaviour.class.getName()).log(Level.SEVERE, null, ex);
            }
            //System.out.println(Crossroads.getWestToEastCars());
            for (int i = 0; i < MainAgent.AgentList.size(); i++) {
             
                if(MainAgent.AgentList.get(i).name.equals(name)) {
                    
                    int newPos=MainAgent.AgentList.get(i).y-5; //+quenum*50;
                    if (Crossroads.getSouthToWest() == 0) red= true;
                        else red = false;
                    if (MainAgent.AgentList.get(i).y < MainAgent.SOUTHLINE) passed = true;
                    if (passed) red=false;
                    if (red) {
                         stopped = true;
                       // if (Crossroads.waitSW>Crossroads.getSWcars()) Crossroads.waitSW = Crossroads.getSWcars();
                        if (que<0)
                        que = Crossroads.waitSW++;
                         if ((newPos < MainAgent.SOUTHLINE+que*50) && (!passed)) {
                           break;
                        }
                        else  MainAgent.AgentList.get(i).y-=5;
                    }
                    else {
                        if (MainAgent.AgentList.get(i).y > MainAgent.NORTHLINE2+vertDiff+5)
                            MainAgent.AgentList.get(i).y-=5;
                        else {
                            if (!turned) {
                                MainAgent.AgentList.get(i).type = 0;
                                turned = true;
                            }
                            MainAgent.AgentList.get(i).x-=5;
                        }
                        if ((MainAgent.AgentList.get(i).y<MainAgent.SOUTHLINE) && (MainAgent.AgentList.get(i).y>MainAgent.NORTHLINE2+vertDiff+5))
                            MainAgent.AgentList.get(i).x-=1;
                        if(MainAgent.AgentList.get(i).y<MainAgent.SOUTHLINE) {
                            passed = true;
                            if(!sent) {
                                if(stopped) { 
                                  
                                    Crossroads.waitSW--;
                                    if ( Crossroads.waitSW<0)  Crossroads.waitSW = 0;
                                    
                                    stopped = false;
                                }
                                
                                sendWelcomeMessage(from,to, false,bus);
                                sent = true;
                            }
                        }
                        
                    }
                    if(MainAgent.AgentList.get(i).x<0)
                            myAgent.doDelete();
                   // break;
                }
            }
       } else  if(from==MainAgent.SOUTH && to==MainAgent.EAST) {
        
            try {
                TimeUnit.MILLISECONDS.sleep(50);
            } catch (InterruptedException ex) {
                Logger.getLogger(CarBehaviour.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            for (int i = 0; i < MainAgent.AgentList.size(); i++) {
             
                if(MainAgent.AgentList.get(i).name.equals(name)) {
                    
                    int newPos=MainAgent.AgentList.get(i).y-5; //+quenum*50;
                    if (Crossroads.getSouthToEast() == 0) red= true;
                        else red = false;
                    if (MainAgent.AgentList.get(i).y < MainAgent.SOUTHLINE) passed = true;
                    if (passed) red=false;
                    if (red) {
                         stopped = true;
                        // if (Crossroads.waitSE>Crossroads.getSEcars()) Crossroads.waitSE = Crossroads.getSEcars();
                        if (que<0)
                        que = Crossroads.waitSE++;
                         if ((newPos < MainAgent.SOUTHLINE+que*50) && (!passed)) {
                           break;
                        }
                        else  MainAgent.AgentList.get(i).y-=5;
                    }
                    else {
                        if (MainAgent.AgentList.get(i).y > MainAgent.SOUTHLINE-vertDiff*2+5)
                            MainAgent.AgentList.get(i).y-=5;
                        else {
                            if (!turned) {
                                MainAgent.AgentList.get(i).type = 0;
                                turned = true;
                            }
                            MainAgent.AgentList.get(i).x+=5;
                        }
                      /*  if ((MainAgent.AgentList.get(i).y<MainAgent.SOUTHLINE) && (MainAgent.AgentList.get(i).y>MainAgent.NORTHLINE2+vertDiff+5))
                            MainAgent.AgentList.get(i).x-=1;*/
                        if(MainAgent.AgentList.get(i).y<MainAgent.SOUTHLINE) {
                            passed = true;
                            if(!sent) {
                                if(stopped) { 
                                  
                                    Crossroads.waitSE--;
                                    if ( Crossroads.waitSE<0)  Crossroads.waitSE = 0;
                                    
                                    stopped = false;
                                }
                                
                                sendWelcomeMessage(from,to, false,bus);
                                sent = true;
                            }
                        }
                        
                    }
                    if(MainAgent.AgentList.get(i).x>PaintPanel.fifthX)
                            myAgent.doDelete();
                   // break;
                }
            }
       } else
          if(from==MainAgent.NORTH && to==MainAgent.EAST) {
        
            try {
                TimeUnit.MILLISECONDS.sleep(50);
            } catch (InterruptedException ex) {
                Logger.getLogger(CarBehaviour.class.getName()).log(Level.SEVERE, null, ex);
            }
            //System.out.println(Crossroads.getWestToEastCars());
            for (int i = 0; i < MainAgent.AgentList.size(); i++) {
             
                if(MainAgent.AgentList.get(i).name.equals(name)) {
                    
                    int newPos=MainAgent.AgentList.get(i).y+5; //+quenum*50;
                    if (Crossroads.getNorthToEast() == 0) red= true;
                    else red = false;
                    if (MainAgent.AgentList.get(i).y > MainAgent.NORTHLINE) passed = true;
                    if (passed) red = false;
                    if (red) {
                         stopped = true;
                       
                        if (que<0)
                        que = Crossroads.waitNE++;
                         if ((newPos > MainAgent.NORTHLINE-que*50) && (!passed)) {
                           break;
                        }
                        else  MainAgent.AgentList.get(i).y+=5;
                    }
                    else {
                        if (MainAgent.AgentList.get(i).y<MainAgent.SOUTHLINE-vertDiff*2+5) 
                            
                            MainAgent.AgentList.get(i).y+=5;
                        else {
                            if (!turned) {
                                MainAgent.AgentList.get(i).type = 0;
                                turned = true;
                            }
                            MainAgent.AgentList.get(i).x+=5;
                        }
                        if ((MainAgent.AgentList.get(i).y>MainAgent.NORTHLINE) && (MainAgent.AgentList.get(i).y<MainAgent.SOUTHLINE-vertDiff*2+5))
                            MainAgent.AgentList.get(i).x+=1;
                        if(MainAgent.AgentList.get(i).y>MainAgent.NORTHLINE) {
                            passed = true;
                            if(!sent) {
                                 if(stopped) { 
                                  
                                    Crossroads.waitNE--;
                                    if ( Crossroads.waitNE<0)  Crossroads.waitNE = 0;
                                    
                                    stopped = false;
                                }
                              
                                sendWelcomeMessage(from,to, false,bus);
                                sent = true;
                            }
                        }
                        
                    }
                    if(MainAgent.AgentList.get(i).x>PaintPanel.fifthX)
                            myAgent.doDelete();
                   
                }
            }
       } else
          if(from==MainAgent.NORTH && to==MainAgent.WEST) {
        
            try {
                TimeUnit.MILLISECONDS.sleep(50);
            } catch (InterruptedException ex) {
                Logger.getLogger(CarBehaviour.class.getName()).log(Level.SEVERE, null, ex);
            }
            //System.out.println(Crossroads.getWestToEastCars());
            for (int i = 0; i < MainAgent.AgentList.size(); i++) {
             
                if(MainAgent.AgentList.get(i).name.equals(name)) {
                    
                    int newPos=MainAgent.AgentList.get(i).y+5; //+quenum*50;
                    if (Crossroads.getNorthToWest() == 0) red= true;
                    else red = false;
                    if (MainAgent.AgentList.get(i).y > MainAgent.NORTHLINE) passed = true;
                    if (passed) red = false;
                    if (red) {
                         stopped = true;
                       
                        if (que<0)
                        que = Crossroads.waitNW++;
                         if ((newPos > MainAgent.NORTHLINE-que*50) && (!passed)) {
                           break;
                        }
                        else  MainAgent.AgentList.get(i).y+=5;
                    }
                    else {
                        if (MainAgent.AgentList.get(i).y<MainAgent.NORTHLINE2+vertDiff+5) 
                            
                            MainAgent.AgentList.get(i).y+=5;
                        else {
                            if (!turned) {
                                MainAgent.AgentList.get(i).type = 0;
                                turned = true;
                            }
                            MainAgent.AgentList.get(i).x-=5;
                        }
                       /* if ((MainAgent.AgentList.get(i).y>MainAgent.NORTHLINE) && (MainAgent.AgentList.get(i).y<MainAgent.SOUTHLINE-vertDiff*2+5))
                            MainAgent.AgentList.get(i).x+=1;*/
                        if(MainAgent.AgentList.get(i).y>MainAgent.NORTHLINE) {
                            passed = true;
                            if(!sent) {
                                if(stopped) { 
                                  
                                    Crossroads.waitNW--;
                                    if ( Crossroads.waitNW<0)  Crossroads.waitNW = 0;
                                    
                                    stopped = false;
                                }
                               
                                sendWelcomeMessage(from,to, false,bus);
                                sent = true;
                            }
                        }
                        
                    }
                    if(MainAgent.AgentList.get(i).x<0)
                            myAgent.doDelete();
                   // break;
                }
            }
       } else if(from==MainAgent.WEST && to==MainAgent.SOUTH) {
        
            try {
                TimeUnit.MILLISECONDS.sleep(50);
            } catch (InterruptedException ex) {
                Logger.getLogger(CarBehaviour.class.getName()).log(Level.SEVERE, null, ex);
            }
            //System.out.println(Crossroads.getWestToEastCars());
            for (int i = 0; i < MainAgent.AgentList.size(); i++) {
             
                if(MainAgent.AgentList.get(i).name.equals(name)) {
                    
                    int newPos=MainAgent.AgentList.get(i).x+5; //+quenum*50;
                   
                   
                   // namiesto cervenej sa sleduje ci je auto v krizovatke
                    if ((Crossroads.crossroadInUseSouth > 0) && (!passed)) red = true;
                    else red = false;
                    if (MainAgent.AgentList.get(i).x > thirdX-vertDiff) passed = true;
                    if (passed) red = false;
                    if (red) {
                         stopped = true;
                        // if (Crossroads.waitWS>Crossroads.getWScars()) Crossroads.waitWS = Crossroads.getWScars();
                        if (que<0)
                        que = Crossroads.waitWS++;
                         if ((newPos > MainAgent.WESTLINE+vertDiff-que*50) && (!passed)) {
                           break;
                        }
                        else  {
                             MainAgent.AgentList.get(i).x+=5;
                             if (MainAgent.AgentList.get(i).x > MainAgent.WESTLINE-vertDiff)
                                MainAgent.AgentList.get(i).y+=2; 
                         }
                    }
                    else {
                        if (MainAgent.AgentList.get(i).x < thirdX+Xdiff-vertDiff*4+5) {
                            MainAgent.AgentList.get(i).x+=5;
                            if (MainAgent.AgentList.get(i).x > MainAgent.WESTLINE-vertDiff)
                                 MainAgent.AgentList.get(i).y+=2; 
                        }
                            
                        else {
                            if (!turned) {
                                MainAgent.AgentList.get(i).type = 1;
                                turned = true;
                            }
                            MainAgent.AgentList.get(i).y+=5;
                        }
                        /*if((MainAgent.AgentList.get(i).x > MainAgent.WESTLINE) && (MainAgent.AgentList.get(i).x < thirdX+vertDiff*3 ))
                            MainAgent.AgentList.get(i).y-=2;*/
                        
                        if(MainAgent.AgentList.get(i).x>MainAgent.WESTLINE){ 
                            passed = true;
                            if(!sent) {
                                if(stopped) { 
                                  
                                    Crossroads.waitWS--;
                                    if ( Crossroads.waitWS<0)  Crossroads.waitWS = 0;
                                    
                                    stopped = false;
                                }
                                Crossroads.waitWS--;
                                sendWelcomeMessage(from,to, false, bus);
                                sent = true;
                            }
                        }
                        
                    }
                    if(MainAgent.AgentList.get(i).y>PaintPanel.downY)
                            myAgent.doDelete();
                   // break;
                }
            }
       } else if(from==MainAgent.EAST && to==MainAgent.NORTH) {
        
            try {
                TimeUnit.MILLISECONDS.sleep(50);
            } catch (InterruptedException ex) {
                Logger.getLogger(CarBehaviour.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            for (int i = 0; i < MainAgent.AgentList.size(); i++) {
             
                if(MainAgent.AgentList.get(i).name.equals(name)) {
                   
                    int newPos=MainAgent.AgentList.get(i).x-5; //+quenum*50;
                   
                    if (Crossroads.crossroadInUseNorth > 0 && (!passed)) red = true;
                    else red = false;
                   
                    if (passed) red = false;
                    if (red) {
                         stopped = true;
                       // if (Crossroads.waitEN>Crossroads.getENcars()) Crossroads.waitEN = Crossroads.getENcars();
                        if (que<0)
                        que = Crossroads.waitEN++;
                         if ((newPos < MainAgent.EASTLINE+que*50) && (!passed)) {
                           break;
                        }
                        else  {
                             MainAgent.AgentList.get(i).x-=5;
                             if (MainAgent.AgentList.get(i).x < MainAgent.EASTLINE+vertDiff)
                                MainAgent.AgentList.get(i).y-=2;
                         }
                    }
                    else {
                        if (MainAgent.AgentList.get(i).x > thirdX+Xdiff-vertDiff+5 ) {
                            MainAgent.AgentList.get(i).x-=5;
                            if (MainAgent.AgentList.get(i).x < MainAgent.EASTLINE+vertDiff)
                                 MainAgent.AgentList.get(i).y-=2; 
                        }
                        else {
                            if (!turned) {
                                MainAgent.AgentList.get(i).type = 1;
                                turned = true;
                            }
                            MainAgent.AgentList.get(i).y-=5;
                        }
                       /* if((MainAgent.AgentList.get(i).x < MainAgent.EASTLINE) && (MainAgent.AgentList.get(i).x > thirdX+Xdiff-vertDiff*4+5 ))
                            MainAgent.AgentList.get(i).y+=1;*/
                        if(MainAgent.AgentList.get(i).x<PaintPanel.thirdX+Xdiff) {
                            passed = true;
                            if(!sent) {
                                if(stopped) { 
                                  
                                        Crossroads.waitEN--;
                                    if ( Crossroads.waitEN<0)  Crossroads.waitEN = 0;
                                    stopped = false;
                                }
                                
                                sendWelcomeMessage(from,to, false, bus);
                                sent = true;
                            }
                        }
                        
                    }
                    if(MainAgent.AgentList.get(i).y<0)
                            myAgent.doDelete();
                   // break;
                }
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
    
    // oznamenie krizovatke ze ju auto opustilo
    public void sendWelcomeMessage(int from, int to, boolean dir, boolean bus) {
        
        ACLMessage msg = new ACLMessage(ACLMessage.INFORM);
        msg.addReceiver(Crossroads.crossroadsAID);
        String fromS = null, toS = null;
        if (bus) msg.setLanguage("bus"); 
        else msg.setLanguage("nobus");
        if (from == MainAgent.WEST)
            fromS = "WEST";
        else if (from == MainAgent.NORTH)
            fromS = "NORTH";
        else if (from == MainAgent.EAST)
            fromS = "EAST";
        else if (from == MainAgent.SOUTH)
            fromS = "SOUTH";
        
        if (to == MainAgent.WEST)
            toS = "WEST";
        else if (to == MainAgent.NORTH)
            toS = "NORTH";
        else if (to == MainAgent.EAST)
            toS = "EAST";
        else if (to == MainAgent.SOUTH)
            toS = "SOUTH";
        if (dir==true)
            msg.setContent(fromS+"to"+toS);
        else msg.setContent(fromS+"exit"+toS);
        myAgent.send(msg);
    }
    
}
