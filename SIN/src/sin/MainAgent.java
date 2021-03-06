/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sin;
import jade.core.Agent;
import jade.core.AID;
import jade.core.AgentContainer;
import jade.core.Runtime;
import jade.core.behaviours.OneShotBehaviour;
import jade.wrapper.AgentController;
import jade.wrapper.StaleProxyException;
import jade.core.Profile;
import jade.core.ProfileImpl;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import static sin.PaintPanel.fifthX;
import static sin.PaintPanel.firstX;
import static sin.PaintPanel.hordownY;
import static sin.PaintPanel.horupY;
import static sin.PaintPanel.secX;
import static sin.PaintPanel.vertDiff;

/**
 * @author Milan
 */
public class MainAgent extends Agent{
        public static PaintPanel paint;
        private static int vehicleAgents = 0;
        private jade.wrapper.AgentContainer carAgentContainer;
        public static final int WEST = 0;
        public static final int NORTH = 1;
        public static final int EAST = 2;
        public static final int SOUTH = 3;
        
        public static boolean CREATE = false;
        public static boolean CREATEWEST = false;
        public static boolean CREATEEAST = false;
        public static boolean CREATESOUTH = false;
        public static boolean CREATENORTH = false;
        
        public static final int WESTLINE = firstX+secX-50;
        public static final int EASTLINE = fifthX-firstX-secX;
        public static final int SOUTHLINE = hordownY+vertDiff;
        public static final int NORTHLINE = horupY-vertDiff*2;
        public static final int NORTHLINE2 = horupY-vertDiff;
        
        
        // zoznam agentov vozidiel
        public static class AgentListElement{
            public int  x;
            public int y;
            public int type;
            public AID name;
            public boolean bus = false;
            
            
            public AgentListElement(int x, int y, int type, AID name, boolean bus) {
                this.x=x;
                this.y=y;
                this.type=type;
                this.name=name;
                this.bus = bus;
            }
        }
        public static ArrayList<AgentListElement> AgentList = new ArrayList<AgentListElement>();
        
        
    protected void setup() {
          //  System.out.println("MainAgent "+ getAID().getName()+ " has started");
            String s= getAID().getName();
            System.out.println(s);
            Profile p = new ProfileImpl();
            Runtime rt = Runtime.instance();
            carAgentContainer =  rt.createAgentContainer(p);
           
            MainWindow.main();
            final int from = this.WEST;
            final int to = this.EAST;
            createCrossroads();
            // vytvorenie prvych dvoch vozidiel
            // aby sa nieco dialo hned po zapnuti
            // pozostatok z prvych fazi vyvoja, ponechane
            createNewVehicle(from,to);
            createNewVehicle(to,from);
            
            // vytvaranie vozidiel cez GUI
            createVeh();
           
           
    }
    
   
    protected void takeDown() {
        System.out.println("MainAgent "+ getAID().getName()+ " has terminated"); 
    }
    
    public static void setPanel(PaintPanel p) {
        paint = p;
        
    }
    
    // vytvaranie vozidiel cez GUI
    public void createVeh() {
        addBehaviour(new CyclicBehaviour() {

            @Override
            public void action() {
                    if (!MainAgent.CREATE) return;
                    int type = 0;
                    int endpointFromName = 0; 
                    int endpointToName =0;
                    
                    // nahodnie sa vyberie cielovy smer, rovnomerne
                    Random randomGenerator = new Random();
                    int randomInt = randomGenerator.nextInt(100);
                    if (CREATEEAST) {
                        endpointFromName=MainAgent.EAST;
                        if (randomInt < 33) endpointToName=MainAgent.WEST;
                        else if (randomInt < 66) endpointToName=MainAgent.NORTH;
                        else endpointToName=MainAgent.SOUTH;
                       // endpointToName=MainAgent.NORTH;
                        CREATEEAST=false;
                        
                    } else
                    if (CREATESOUTH) {
                       endpointFromName=MainAgent.SOUTH;
                       if (randomInt < 33) endpointToName=MainAgent.NORTH;
                        else if (randomInt < 66) endpointToName=MainAgent.WEST;
                        else endpointToName=MainAgent.EAST;
                       //endpointToName=MainAgent.EAST;
                       CREATESOUTH=false;
                    } else
                    if (CREATEWEST) {
                       endpointFromName=MainAgent.WEST;
                       if (randomInt < 33) endpointToName=MainAgent.EAST;
                       else if (randomInt < 66) endpointToName=MainAgent.NORTH;
                       else endpointToName=MainAgent.SOUTH;
                      // endpointToName=MainAgent.SOUTH;
                       CREATEWEST=false;
                    } else
                    if (CREATENORTH) {
                       endpointFromName=MainAgent.NORTH;
                       if (randomInt < 33) endpointToName=MainAgent.SOUTH;
                       else if (randomInt < 66) endpointToName=MainAgent.WEST;
                       else endpointToName=MainAgent.EAST;
                      // endpointToName=MainAgent.WEST;
                       CREATENORTH=false;
                    } 
                    
                    // ina orientacia vozidla
                    if ((endpointFromName==MainAgent.SOUTH) || (endpointFromName==MainAgent.NORTH))
                        type = 1;
                    boolean bus = false;
                    
                    // 7 percenta sanca na to ze vozidlo je bus
                    if (randomInt > 93) bus = true;
                    Object args[] = { endpointFromName, endpointToName, type, bus};
                    
                    // samotne vytvorenie
                    try {   
                        AgentController agent = carAgentContainer.createNewAgent("car-" + vehicleAgents, VehicleAgent.class.getCanonicalName(), args);
                        agent.start();
                        vehicleAgents++;
                    } catch (StaleProxyException e) {
                        System.err.println("Error creating car agents");
                        e.printStackTrace();
                    }
                    
                    // dalsie sa nevytvori kym nie je stlacene tlacidlo
                    MainAgent.CREATE = false;
            }

        });
    }
    
    // deprecated
    public void createNewVehicle(final int endpointFromName, final int endpointToName) {
	addBehaviour(new OneShotBehaviour() {

            @Override
            public void action() {
                    int type = 0;
                     boolean bus = false;
                    Object args[] = { endpointFromName, endpointToName, type,bus};
                
                    try {   
                        AgentController agent = carAgentContainer.createNewAgent("car-" + vehicleAgents, VehicleAgent.class.getCanonicalName(), args);
                        agent.start();
                        vehicleAgents++;
                    } catch (StaleProxyException e) {
                        System.err.println("Error creating car agents");
                        e.printStackTrace();
                    }
            }

        });
    }
    
    // vytvorenie krizovatky
    public void createCrossroads() {
	addBehaviour(new OneShotBehaviour() {

            @Override
            public void action() {
                    int type = 0;
                    Object args[] = {};
                
                    try {   
                        AgentController agent = carAgentContainer.createNewAgent("Crossroad-", Crossroads.class.getCanonicalName(), args);
                        agent.start();
                       // vehicleAgents++;
                    } catch (StaleProxyException e) {
                        System.err.println("Error creating crossroad agents");
                        e.printStackTrace();
                    }
            }

        });
    }
}
