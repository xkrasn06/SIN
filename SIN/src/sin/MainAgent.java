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
        public static class AgentListElement{
            public int  x;
            public int y;
            public int type;
            public AID name;
            
            
            public AgentListElement(int x, int y, int type, AID name) {
                this.x=x;
                this.y=y;
                this.type=type;
                this.name=name;
            }
        }
        public static ArrayList<AgentListElement> AgentList = new ArrayList<AgentListElement>(); 
    protected void setup() {
            System.out.println("MainAgent "+ getAID().getName()+ " has started");
            String s= getAID().getName();
            System.out.println(s);
            Profile p = new ProfileImpl();
            Runtime rt = Runtime.instance();
            carAgentContainer =  rt.createAgentContainer(p);
           
            MainWindow.main();
            final int from = this.WEST;
            final int to = this.EAST;
            createCrossroads();
            createNewVehicle(from,to);
            createVeh();
           
           
            
            createNewVehicle(to,from);
        
            
        
    }
    
   
    protected void takeDown() {
        System.out.println("MainAgent "+ getAID().getName()+ " has terminated"); 
    }
    
    public static void setPanel(PaintPanel p) {
        paint = p;
        //paint.updateVehicles(20,30);
    }
    
    public void createVeh() {
        addBehaviour(new CyclicBehaviour() {

            @Override
            public void action() {
                    if (!MainAgent.CREATE) return;
                    int type = 0;
                    int endpointFromName = MainAgent.NORTH;
                    int endpointToName = MainAgent.SOUTH;
                    if ((endpointFromName==MainAgent.SOUTH) || (endpointFromName==MainAgent.NORTH))
                        type = 1;
                    Object args[] = { endpointFromName, endpointToName, type};
                
                    try {   
                        AgentController agent = carAgentContainer.createNewAgent("car-" + vehicleAgents, VehicleAgent.class.getCanonicalName(), args);
                        agent.start();
                        vehicleAgents++;
                    } catch (StaleProxyException e) {
                        System.err.println("Error creating car agents");
                        e.printStackTrace();
                    }
                    MainAgent.CREATE = false;
            }

        });
    }
    
    public void createNewVehicle(final int endpointFromName, final int endpointToName) {
	addBehaviour(new OneShotBehaviour() {

            @Override
            public void action() {
                    int type = 0;
                    Object args[] = { endpointFromName, endpointToName, type};
                
                    try {   
                        AgentController agent = carAgentContainer.createNewAgent("C-" + vehicleAgents, VehicleAgent.class.getCanonicalName(), args);
                        agent.start();
                        vehicleAgents++;
                    } catch (StaleProxyException e) {
                        System.err.println("Error creating car agents");
                        e.printStackTrace();
                    }
            }

        });
    }
    
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
