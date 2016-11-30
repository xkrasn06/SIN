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
    private static final int waitPos[] = {MainAgent.WESTLINE-5, MainAgent.WESTLINE-50, MainAgent.WESTLINE-100 }; 
    
    private int from;
    private int to;
    private final AID name;
    private boolean stopped = false;
    private boolean passed = false;
    private boolean sent = false;
    private boolean start = false;
    private boolean red = false;
    private int quenum = 0;
    private int quenumStart;
    private int que = -1;
    private boolean turned = false;
    public CarBehaviour(AID name, int from, int to) {
       this.name = name;
       this.from = from;
       this.to = to;
       
    }
    @Override
    public void action() {
       // Panel.updateVehicles(this.posX,this.posY);
       //System.out.println(Crossroads.getWestToEastCars());
       ACLMessage msg = myAgent.receive();
        if (msg != null) {
            String lang = msg.getLanguage();
            String con = msg.getContent();
           // quenum = Integer.parseInt(con);
        }
        else {
          
        }
       if(from==MainAgent.WEST && to==MainAgent.EAST) {
        
            try {
                TimeUnit.MILLISECONDS.sleep(50);
            } catch (InterruptedException ex) {
                Logger.getLogger(CarBehaviour.class.getName()).log(Level.SEVERE, null, ex);
            }
            //System.out.println(Crossroads.getWestToEastCars());
            for (int i = 0; i < MainAgent.AgentList.size(); i++) {
             
                if(MainAgent.AgentList.get(i).name.equals(name)) {
                    // pozicia zastavenia ciara krizovatky plus pocet aut
                    int newPos=MainAgent.AgentList.get(i).x+5; //+quenum*50;
                    if (Crossroads.getWestToEast() == 0) red= true;
                    else red = false;
                    if (MainAgent.AgentList.get(i).x > MainAgent.WESTLINE) passed = true;
                    if (red) {
                        if (que<0)
                        que = Crossroads.waitWE++;
                         if ((newPos > MainAgent.WESTLINE-que*50) && (!passed)) {
                           break;
                        }
                        else  MainAgent.AgentList.get(i).x+=5;
                    }
                    else {
                        MainAgent.AgentList.get(i).x+=5;
                        if(MainAgent.AgentList.get(i).x>MainAgent.WESTLINE) {
                            passed = true;
                            if(!sent) {
                                Crossroads.waitWE--;
                                sendWelcomeMessage(from,to, false);
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
           if(from==MainAgent.EAST && to==MainAgent.WEST) {
        
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
                    if (Crossroads.getEastToWest() == 0) red= true;
                    else red = false;
                    if (MainAgent.AgentList.get(i).x < MainAgent.EASTLINE) passed = true;
                    if (red) {
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
                                Crossroads.waitEW--;
                                sendWelcomeMessage(from,to, false);
                                sent = true;
                            }
                        }
                        
                    }
                    if(MainAgent.AgentList.get(i).x<0)
                            myAgent.doDelete();
                   // break;
                }
            }
       } else
            if(from==MainAgent.SOUTH && to==MainAgent.NORTH) {
        
            try {
                TimeUnit.MILLISECONDS.sleep(50);
            } catch (InterruptedException ex) {
                Logger.getLogger(CarBehaviour.class.getName()).log(Level.SEVERE, null, ex);
            }
            //System.out.println(Crossroads.getWestToEastCars());
            for (int i = 0; i < MainAgent.AgentList.size(); i++) {
             
                if(MainAgent.AgentList.get(i).name.equals(name)) {
                    // pozicia zastavenia ciara krizovatky plus pocet aut
                    int newPos=MainAgent.AgentList.get(i).y-5; //+quenum*50;
                    if (Crossroads.getSouthToNorth() == 0) red= true;
                        else red = false;
                    if (MainAgent.AgentList.get(i).y < MainAgent.SOUTHLINE) passed = true;
                     if (passed) red = false;
                    if (red) {
                        if (que<0)
                        que = Crossroads.waitSN++;
                         if ((newPos < MainAgent.SOUTHLINE+que*50) && (!passed)) {
                           break;
                        }
                        else  MainAgent.AgentList.get(i).y-=5;
                    }
                    else {
                        MainAgent.AgentList.get(i).y-=5;
                        if ((MainAgent.AgentList.get(i).y<MainAgent.SOUTHLINE) && (MainAgent.AgentList.get(i).y>MainAgent.NORTHLINE2))
                            MainAgent.AgentList.get(i).x+=1;
                        if(MainAgent.AgentList.get(i).y<MainAgent.SOUTHLINE) {
                            passed = true;
                            if(!sent) {
                                Crossroads.waitSN--;
                                sendWelcomeMessage(from,to, false);
                                sent = true;
                            }
                        }
                        
                    }
                    if(MainAgent.AgentList.get(i).y<0)
                            myAgent.doDelete();
                   // break;
                }
            }
       } else
          if(from==MainAgent.NORTH && to==MainAgent.SOUTH) {
        
            try {
                TimeUnit.MILLISECONDS.sleep(50);
            } catch (InterruptedException ex) {
                Logger.getLogger(CarBehaviour.class.getName()).log(Level.SEVERE, null, ex);
            }
            //System.out.println(Crossroads.getWestToEastCars());
            for (int i = 0; i < MainAgent.AgentList.size(); i++) {
             
                if(MainAgent.AgentList.get(i).name.equals(name)) {
                    // pozicia zastavenia ciara krizovatky plus pocet aut
                    int newPos=MainAgent.AgentList.get(i).y+5; //+quenum*50;
                    if (Crossroads.getNorthToSouth() == 0) red= true;
                    else red = false;
                    if (MainAgent.AgentList.get(i).y > MainAgent.NORTHLINE) passed = true;
                    if (passed) red = false;
                    if (red) {
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
                                Crossroads.waitNS--;
                                sendWelcomeMessage(from,to, false);
                                sent = true;
                            }
                        }
                        
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
                    // pozicia zastavenia ciara krizovatky plus pocet aut
                    int newPos=MainAgent.AgentList.get(i).x+5; //+quenum*50;
                    if (Crossroads.getWestToNorth() == 0) red= true;
                    else red = false;
                    if (MainAgent.AgentList.get(i).x > MainAgent.WESTLINE) passed = true;
                    if (passed) red = false;
                    if (red) {
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
                                Crossroads.waitWN--;
                                sendWelcomeMessage(from,to, false);
                                sent = true;
                            }
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
                                Crossroads.waitES--;
                                sendWelcomeMessage(from,to, false);
                                sent = true;
                            }
                        }
                        
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
                    // pozicia zastavenia ciara krizovatky plus pocet aut
                    int newPos=MainAgent.AgentList.get(i).y-5; //+quenum*50;
                    if (Crossroads.getSouthToWest() == 0) red= true;
                        else red = false;
                    if (MainAgent.AgentList.get(i).y < MainAgent.SOUTHLINE) passed = true;
                    if (passed) red=false;
                    if (red) {
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
                                Crossroads.waitSW--;
                                sendWelcomeMessage(from,to, false);
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
            //System.out.println(Crossroads.getWestToEastCars());
            for (int i = 0; i < MainAgent.AgentList.size(); i++) {
             
                if(MainAgent.AgentList.get(i).name.equals(name)) {
                    // pozicia zastavenia ciara krizovatky plus pocet aut
                    int newPos=MainAgent.AgentList.get(i).y-5; //+quenum*50;
                    if (Crossroads.getSouthToEast() == 0) red= true;
                        else red = false;
                    if (MainAgent.AgentList.get(i).y < MainAgent.SOUTHLINE) passed = true;
                    if (passed) red=false;
                    if (red) {
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
                                Crossroads.waitSE--;
                                sendWelcomeMessage(from,to, false);
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
                    // pozicia zastavenia ciara krizovatky plus pocet aut
                    int newPos=MainAgent.AgentList.get(i).y+5; //+quenum*50;
                    if (Crossroads.getNorthToEast() == 0) red= true;
                    else red = false;
                    if (MainAgent.AgentList.get(i).y > MainAgent.NORTHLINE) passed = true;
                    if (passed) red = false;
                    if (red) {
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
                                Crossroads.waitNE--;
                                sendWelcomeMessage(from,to, false);
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
          if(from==MainAgent.NORTH && to==MainAgent.WEST) {
        
            try {
                TimeUnit.MILLISECONDS.sleep(50);
            } catch (InterruptedException ex) {
                Logger.getLogger(CarBehaviour.class.getName()).log(Level.SEVERE, null, ex);
            }
            //System.out.println(Crossroads.getWestToEastCars());
            for (int i = 0; i < MainAgent.AgentList.size(); i++) {
             
                if(MainAgent.AgentList.get(i).name.equals(name)) {
                    // pozicia zastavenia ciara krizovatky plus pocet aut
                    int newPos=MainAgent.AgentList.get(i).y+5; //+quenum*50;
                    if (Crossroads.getNorthToWest() == 0) red= true;
                    else red = false;
                    if (MainAgent.AgentList.get(i).y > MainAgent.NORTHLINE) passed = true;
                    if (passed) red = false;
                    if (red) {
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
                                Crossroads.waitNW--;
                                sendWelcomeMessage(from,to, false);
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
                    // pozicia zastavenia ciara krizovatky plus pocet aut
                    int newPos=MainAgent.AgentList.get(i).x+5; //+quenum*50;
                    if ((Crossroads.getNorthToSouth() == 1) || (Crossroads.getEastToSouth() == 1))  red= true;
                    else red = false;
                    //if (MainAgent.AgentList.get(i).x > thirdX-vertDiff) passed = true;
                    if (passed) red = false;
                    if (red) {
                        if (que<0)
                        que = Crossroads.waitWS++;
                         if ((newPos > MainAgent.WESTLINE-que*50) && (!passed)) {
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
                        
                        if(MainAgent.AgentList.get(i).x>thirdX+Xdiff-vertDiff*4+5) {
                            passed = true;
                            if(!sent) {
                                Crossroads.waitWN--;
                                sendWelcomeMessage(from,to, false);
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
            //System.out.println(Crossroads.getWestToEastCars());
            for (int i = 0; i < MainAgent.AgentList.size(); i++) {
             
                if(MainAgent.AgentList.get(i).name.equals(name)) {
                    // pozicia zastavenia ciara krizovatky plus pocet aut
                    int newPos=MainAgent.AgentList.get(i).x-5; //+quenum*50;
                    if ((Crossroads.getWestToNorth() == 1) || (Crossroads.getSouthToNorth() == 1))  red= true;
                    else red = false;
                   // if (MainAgent.AgentList.get(i).x < MainAgent.EASTLINE) passed = true;
                    if (passed) red = false;
                    if (red) {
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
                                Crossroads.waitEN--;
                                sendWelcomeMessage(from,to, false);
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
    
    public void sendWelcomeMessage(int from, int to, boolean dir) {
        
        ACLMessage msg = new ACLMessage(ACLMessage.INFORM);
        msg.addReceiver(Crossroads.crossroadsAID);
        String fromS = null, toS = null;
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
