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

/**
 * @author Milan
 */
public class MainAgent extends Agent{
        public static PaintPanel paint;
        private static int vehicleAgents = 0;
        private jade.wrapper.AgentContainer carAgentContainer;
    
    protected void setup() {
            System.out.println("MainAgent "+ getAID().getName()+ " has started");
            Profile p = new ProfileImpl();
            Runtime rt = Runtime.instance();
            carAgentContainer =  rt.createAgentContainer(p);
           
            MainWindow.main();
            final String from = "West";
            final String to = "East";
        
            createNewVehicle(from,to);
            createNewVehicle(to,from);
        
            
        
    }
    
   
    protected void takeDown() {
        System.out.println("MainnAgent "+ getAID().getName()+ " has terminated"); 
    }
    
    public static void setPanel(PaintPanel p) {
        paint = p;
        paint.updateVehicles(20,30);
    }
    
    public void createNewVehicle(final String endpointFromName, final String endpointToName) {
	addBehaviour(new OneShotBehaviour() {

            @Override
            public void action() {
                    Object args[] = { endpointFromName, endpointToName};
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
