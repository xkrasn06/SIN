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
import jade.lang.acl.ACLMessage;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import static sin.PaintPanel.firstX;
import static sin.PaintPanel.secX;

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
        
        public static final int WESTLINE = firstX+secX;
        public static class AgentListElement{
            public int  x;
            public int y;
            public int type;
            public String name;
            
            
            public AgentListElement(int x, int y, int type, String name) {
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
        
            createNewVehicle(from,to);
            
            
           
            
            createNewVehicle(to,from);
        
            
        
    }
    
   
    protected void takeDown() {
        System.out.println("MainnAgent "+ getAID().getName()+ " has terminated"); 
    }
    
    public static void setPanel(PaintPanel p) {
        paint = p;
        //paint.updateVehicles(20,30);
    }
    
    public void createVeh() {
        createNewVehicle(MainAgent.WEST, MainAgent.EAST);
    }
    
    public void createNewVehicle(final int endpointFromName, final int endpointToName) {
	addBehaviour(new OneShotBehaviour() {

            @Override
            public void action() {
                    int type = 0;
                    Object args[] = { endpointFromName, endpointToName, type};
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
}
