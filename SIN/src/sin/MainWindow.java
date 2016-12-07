package sin;

import static com.sun.management.jmx.Trace.isSelected;
import jade.core.Profile;
import jade.core.ProfileImpl;
import jade.wrapper.AgentController;
import jade.wrapper.ContainerController;
import jade.wrapper.StaleProxyException;
import java.awt.Color;
import java.awt.ComponentOrientation;
import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.AdjustmentListener;

import javax.swing.BoundedRangeModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollBar;
import javax.swing.SwingConstants;


// vytvorenie GUI
public class MainWindow{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	//private static JLabel lbl1;
	//private static JLabel lbl2;
	//private static JLabel lbl3;
        //private static JLabel lbl4;
	private static JScrollBar sb1;
	private static JScrollBar sb2;
	private static JScrollBar sb3;
        //private static JScrollBar sb4;
	public static PaintPanel paint;
        //private static final int maxSize = 40;
       // private static BoundedRangeModel model1;
        private static JCheckBox box1;
        private static JButton btn1;
        private static JButton btn2;
        private static JButton btn3;
        private static JButton btn4;
        
      
	
	final static boolean shouldFill = true;
        final static boolean shouldWeightX = true;
        final static boolean RIGHT_TO_LEFT = false;
    
   /* public static int getSB3() {
        return sb3.getValue();
    }*/
    public static int getSB2() {
        return sb2.getValue();
    }
    
   /* public static void setMax(int max) {
        maxSize = max;
        model1.setRangeProperties(20, 1, 20, maxSize, true);
    }*/
    
    public static int getSB1() {
        return sb1.getValue();
    }
    
    public static boolean getCheck() {
        return box1.isSelected();
    }
    
   /* public static int getButton() {
        return btn1.getValue();
    }*/
    
    public static void addComponentsToPane(Container pane) {
	    if (RIGHT_TO_LEFT) {
	        pane.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
	    }
	 
	    JButton button;
	    pane.setLayout(new GridBagLayout());
	    GridBagConstraints c = new GridBagConstraints();
	    c.insets = new Insets(5, 5, 3, 5);
	    
	    if (shouldFill) {
	    //natural height, maximum width
	    c.fill = GridBagConstraints.HORIZONTAL;
	    }
	    
	    //lbl1 = new JLabel("SPAWN FROM WEST", SwingConstants.CENTER);
	    if (shouldWeightX) {
	  //  c.weightx = 0.5;
	    }
	    c.fill = GridBagConstraints.HORIZONTAL;
	    c.gridx = 0;
	    c.gridy = 0;
	   // pane.add(lbl1, c);
	 
	   // lbl2 = new JLabel("SPAWN FROM SOUTH", SwingConstants.CENTER);
	    c.fill = GridBagConstraints.HORIZONTAL;
	   // c.weightx = 0.2;
	    c.gridx = 1;
	    c.gridy = 0;
	   // pane.add(lbl2, c);
	    
	    box1 = new JCheckBox("SMART",false);
	    box1.setText("SMART");
            //box1.turnCheckBox.setSelected(isSelected);
	    c.fill = GridBagConstraints.HORIZONTAL;
	  //  c.weightx = 0.2;
	    c.gridx = 2;
	    c.gridy = 0;
	    pane.add(box1, c);
	 
	   // lbl3 = new JLabel("SPAWN FROM EAST", SwingConstants.CENTER);
	    c.fill = GridBagConstraints.HORIZONTAL;
	  //  c.weightx = 0.3;
	    c.gridx = 3;
	    c.gridy = 0;
	  //  pane.add(lbl3, c);
            
          
            btn4 = new JButton("SPAWN FROM WEST");
	    c.fill = GridBagConstraints.HORIZONTAL;
	    c.weightx = 0.5;
	    c.gridx = 0;
	    c.gridy = 1;
	    pane.add(btn4, c);
            
            btn3 = new JButton("SPAWN FROM SOUTH");
	    c.fill = GridBagConstraints.HORIZONTAL;
	    c.weightx = 0.5;
	    c.gridx = 1;
	    c.gridy = 1;
	    pane.add(btn3, c);
            
	    btn2 = new JButton("SPAWN FROM NORTH");
	    c.fill = GridBagConstraints.HORIZONTAL;
	    c.weightx = 0.5;
	    c.gridx = 2;
	    c.gridy = 1;
	    pane.add(btn2, c);
            
	    btn1 = new JButton("SPAWN FROM EAST");
	    c.fill = GridBagConstraints.HORIZONTAL;
	    c.weightx = 0.5;
	    c.gridx = 3;
	    c.gridy = 1;
	    pane.add(btn1, c);
            
            
	    
	  
             
            
	    paint = new PaintPanel();
            MainAgent.setPanel(paint);
	    paint.setBackground(Color.DARK_GRAY);
	    c.fill = GridBagConstraints.HORIZONTAL;
	    c.ipady = 750;      //make this component tall
	    c.weightx = 0.0;
	    c.gridwidth = 4;
	    c.gridx = 0;
	    c.gridy = 2;
	    pane.add(paint, c);
            
             AdjustmentListener listener = new MyAdjustmentListener(paint);
 
        
             box1.addActionListener(new ActionListener() {
            	    @Override
            	    public void actionPerformed(ActionEvent event) {
            	      // System.out.println("listener checkbox");
                       Crossroads.SMART = !Crossroads.SMART;
                       //paint.updateValues();
            	    }
            	});
             
             btn1.addActionListener(new ActionListener() {
         	    @Override
         	    public void actionPerformed(ActionEvent event) {
         	       //System.out.println("listener button");
                      // paint.SKY = !paint.SKY;
                      // paint.updateValues();
                       MainAgent.CREATE = true;
                       MainAgent.CREATEEAST= true;
                       //MainAgent.createNewVehicle(0,2);
         	    }
         	});
                btn2.addActionListener(new ActionListener() {
         	    @Override
         	    public void actionPerformed(ActionEvent event) {
         	       //System.out.println("listener button");
                      // paint.SKY = !paint.SKY;
                      // paint.updateValues();
                       MainAgent.CREATE = true;
                       MainAgent.CREATENORTH= true;
                       //MainAgent.createNewVehicle(0,2);
         	    }
         	});
                btn3.addActionListener(new ActionListener() {
         	    @Override
         	    public void actionPerformed(ActionEvent event) {
         	       //System.out.println("listener button");
                      // paint.SKY = !paint.SKY;
                      // paint.updateValues();
                       MainAgent.CREATE = true;
                       MainAgent.CREATESOUTH= true;
                       //MainAgent.createNewVehicle(0,2);
         	    }
         	});
                btn4.addActionListener(new ActionListener() {
         	    @Override
         	    public void actionPerformed(ActionEvent event) {
         	       //System.out.println("listener button");
                      // paint.SKY = !paint.SKY;
                      // paint.updateValues();
                       MainAgent.CREATE = true;
                       MainAgent.CREATEWEST= true;
                       //MainAgent.createNewVehicle(0,2);
         	    }
         	});
             
    }
 
    /**
     * Create the GUI and show it.  For thread safety,
     * this method should be invoked from the
     * event-dispatching thread.
     */
    private static void createAndShowGUI() {
        //Create and set up the window.
        JFrame frame = new JFrame("SIN - CROSSROADS");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
 
        //Set up the content pane.
        addComponentsToPane(frame.getContentPane());
       
        //No variable size window
        frame.setResizable(false);
        frame.setSize(1400, 860);
        frame.setVisible(true);
    }
    
    /**
     *
     * @param args
     */
    public static void main(){
		 javax.swing.SwingUtilities.invokeLater(new Runnable() {
	            public void run() {
	                createAndShowGUI();
	            }
	        });
                 
                 
                 
	    }

    public MainWindow() {
        
    }
	}


