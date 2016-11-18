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
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.BoundedRangeModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollBar;
import javax.swing.SwingConstants;

public class MainWindow{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private static JLabel lbl1;
	private static JLabel lbl2;
	private static JLabel lbl3;
        private static JLabel lbl4;
	private static JScrollBar sb1;
	private static JScrollBar sb2;
	private static JScrollBar sb3;
        private static JScrollBar sb4;
	public static PaintPanel paint;
        private static int maxSize = 40;
        private static BoundedRangeModel model1;
        private static JCheckBox box1;
        private static JButton btn1;
        private static JButton btn2;
        
      
	
	final static boolean shouldFill = true;
    final static boolean shouldWeightX = true;
    final static boolean RIGHT_TO_LEFT = false;
    
    public static int getSB3() {
        return sb3.getValue();
    }
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
	    
	    lbl1 = new JLabel("Size of scatterer (x10^(-10) m):", SwingConstants.CENTER);
	    if (shouldWeightX) {
	  //  c.weightx = 0.5;
	    }
	    c.fill = GridBagConstraints.HORIZONTAL;
	    c.gridx = 0;
	    c.gridy = 0;
	    pane.add(lbl1, c);
	 
	    lbl2 = new JLabel("Number of scatterers", SwingConstants.CENTER);
	    c.fill = GridBagConstraints.HORIZONTAL;
	   // c.weightx = 0.2;
	    c.gridx = 1;
	    c.gridy = 0;
	    pane.add(lbl2, c);
	    
	    box1 = new JCheckBox("Random Angles",true);
	    box1.setText("Random Angles");
            //box1.turnCheckBox.setSelected(isSelected);
	    c.fill = GridBagConstraints.HORIZONTAL;
	  //  c.weightx = 0.2;
	    c.gridx = 2;
	    c.gridy = 0;
	    pane.add(box1, c);
	 
	    lbl3 = new JLabel("Wavelength (nm)", SwingConstants.CENTER);
	    c.fill = GridBagConstraints.HORIZONTAL;
	  //  c.weightx = 0.3;
	    c.gridx = 3;
	    c.gridy = 0;
	    pane.add(lbl3, c);
            
           /* lbl4 = new JLabel("Wavelength (nm)", SwingConstants.CENTER);
	    c.fill = GridBagConstraints.HORIZONTAL;
	    c.weightx = 0.5;
	    c.gridx = 2;
	    c.gridy = 0;
	    pane.add(lbl4, c);*/
	    
	    
	    
	    sb1 = new JScrollBar(JScrollBar.HORIZONTAL);
	    if (shouldWeightX) {
	        c.weightx = 0.5;
	        }
	    c.fill = GridBagConstraints.HORIZONTAL;
	    c.gridx = 0;
	    c.gridy = 1;
            
            model1 = sb1.getModel();
            model1.setRangeProperties(300, 1, 100, 500, true);
            
	    pane.add(sb1, c);
            
	    
	    sb2 = new JScrollBar(JScrollBar.HORIZONTAL);
	    c.fill = GridBagConstraints.HORIZONTAL;
	    c.weightx = 0.5;
	    c.gridx = 1;
	    c.gridy = 1;
            BoundedRangeModel model2 = sb2.getModel();
            model2.setRangeProperties(16, 4, 1, 64, true);
	    pane.add(sb2, c);
	    
	    btn1 = new JButton("Toggle Blue Sky / Detail");
	    c.fill = GridBagConstraints.HORIZONTAL;
	    c.weightx = 0.5;
	    c.gridx = 2;
	    c.gridy = 1;
	    pane.add(btn1, c);
            
            
	    
	    sb3 = new JScrollBar(JScrollBar.HORIZONTAL);
	    c.fill = GridBagConstraints.HORIZONTAL;
	    c.weightx = 0.5;
	    c.gridx = 3;
	    c.gridy = 1;
            
            //sb3.setMaximum(30);
            BoundedRangeModel model3 = sb3.getModel();
            model3.setRangeProperties(500, 5, 400, 700, true);
            
	    pane.add(sb3, c);
            
            
            
          /*  sb4 = new JScrollBar(JScrollBar.HORIZONTAL);
	    c.fill = GridBagConstraints.HORIZONTAL;
	    c.weightx = 0.5;
	    c.gridx = 1;
	    c.gridy = 2;
	    pane.add(sb4, c);*/
             
            
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
             sb3.addAdjustmentListener(listener);
             sb2.addAdjustmentListener(listener);
             sb1.addAdjustmentListener(listener);
        
             box1.addActionListener(new ActionListener() {
            	    @Override
            	    public void actionPerformed(ActionEvent event) {
            	      // System.out.println("listener checkbox");
                       paint.updateValues();
            	    }
            	});
             
             btn1.addActionListener(new ActionListener() {
         	    @Override
         	    public void actionPerformed(ActionEvent event) {
         	       System.out.println("listener button");
                       paint.SKY = !paint.SKY;
                       paint.updateValues();
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
        JFrame frame = new JFrame("FYO - Scattering: Rayleigh, Mie");
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


