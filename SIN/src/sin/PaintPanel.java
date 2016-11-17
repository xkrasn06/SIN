package sin;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionListener;
import static java.lang.Math.PI;
import java.util.Random;

import javax.swing.JPanel;
import javax.swing.Timer;

public class PaintPanel extends JPanel {
        int SB3 = MainWindow.getSB3();
        int SB2 = MainWindow.getSB2();
        int SB1 = MainWindow.getSB1();
        static final int x = 705;
        static final int y = 405;
        // Y suradnice cesty zlava
        static final int vertWidth = 100;
        
        static final int hordownY = 400;
        static final int horupY = hordownY - vertWidth;
        
        // Y suradnica spodneho okraja
        static final int downY = 800;
        static final int secY = 50;
        static final int thirdY = 100;
        
        // X suradnica laveho okraja
        static final int startX = 0;
        
        // X suradnica prve prerusenie
        static final int firstX = 200;
        
        // X suradnica druhe prerusenie (zaciatok)
        static final int secX = 350;
        
        //X suradnica krizovatka
        static final int thirdX = 625;
        
        // sirka krizovatky
        static final int Xdiff = 150;
        
        // pravy okraj
        static final int fifthX = 1400;
        int N = 4;
        double length = 30;
        int dx = (int) length;
        int dy = (int) length;
        double I = 0;
        double red = 0.0;
        double green= 0.0;
        double blue = 0.0;
        boolean SKY = true;
        
        public boolean getSKY() {
            return SKY;
        }
        @Override
	public void paintComponent(Graphics g){
                Graphics2D g2D = (Graphics2D) g;
                super.paintComponent(g);
                g.setColor(Color.black);
                 g2D.setStroke(new BasicStroke(5));
                 
                 // spodna horizontalna  linia nalavo
                g2D.drawLine(startX,hordownY,firstX, hordownY); 
                g2D.drawLine(firstX+secX,hordownY,thirdX, hordownY);
                // horna horizontalna  linia nalavo
                g2D.drawLine(startX,horupY,thirdX, horupY);
                
                // spodna horizontalna linia napravo
                 g2D.drawLine(thirdX+Xdiff,hordownY,fifthX, hordownY);
                 
                 // horna horizontalna lina napravo
                 g2D.drawLine(fifthX,horupY,fifthX-firstX, horupY);
                  g2D.drawLine(fifthX-firstX-secX,horupY,thirdX+Xdiff, horupY); 
                 
                 // spodna vertikalna linia nalavo
                 g2D.drawLine(thirdX,hordownY,thirdX,hordownY+secY);
                 g2D.drawLine(thirdX,hordownY+secY+thirdY,thirdX,downY);
                 // spodna vertikalna linia napravo
                 g2D.drawLine(thirdX+Xdiff,hordownY,thirdX+Xdiff, downY);
                 // horna vertikalna linia nalavo
                 g2D.drawLine(thirdX,horupY,thirdX,0);
                  
                  // horna vertikalna linia napravo
                  g2D.drawLine(thirdX+Xdiff,horupY,thirdX+Xdiff, horupY-secY);
                  g2D.drawLine(thirdX+Xdiff,horupY-secY-thirdY,thirdX+Xdiff,0);
                  
                   
                   
                  
                 
             /*       spectral_color((double) SB3);
                    g.setColor(Color.black);
             
                    g.setFont(new Font("Courier New", Font.BOLD, 20));
                    g.drawString("Value: " + SB3 + "nm", 1170,15);
                    g.drawString("Value: " + SB2, 450,15);
                    g.drawString("Value: " + SB1 + "x10^(-10) m", 80,15);
                    g.drawString("Light Source", 20,380);
                    Color c = new Color((float) red, (float) green, (float)blue);
                    g.setColor(c);
                    float dash1[] = {10.0f};
                    g2D.setStroke(new BasicStroke(5.0f,
                        BasicStroke.CAP_BUTT,
                        BasicStroke.JOIN_MITER,
                        10.0f, dash1, 0.0f));
              
               
                    g2D.drawLine(75,415, x+10+(int) I,y+10+(int) I);
                    g2D.setStroke(new BasicStroke(5));
                    g.setColor(Color.black);
                    g.fillRect(55, 390, 20, 50);
                    g.setColor(c);
                    int dx2 = 15;
                    int dy2 = 15;
                    int step = 360/N;
                    
                    for (int i = 0; i<360; i+=step) {
                        int randomInt;
                        double length2;
                        double sine;
                        double cosine;
                        int sinx;
                        int cosy;
    
                        if (MainWindow.getCheck()) {
                            int j = i;
                            Random randomGenerator = new Random();
                            randomInt = randomGenerator.nextInt(360);
                            i = randomInt;
                            System.out.format("The value of i is: %d%n", i);
                            length2 = (1 + Math.pow(Math.cos(Math.abs(i)*Math.PI/180),2))*length;
                    
                            sine=   Math.sin(Math.toRadians( (double) i ))*length2;
                            cosine =  Math.cos(Math.toRadians ((double) i)  )*length2;
                            sinx = (int) Math.round(sine);
                            cosy = (int) Math.round(cosine);
                            i = j;
                        }
                        else {
                            length2 = (1 + Math.pow(Math.cos(Math.abs(i)*Math.PI/180),2))*length;
                           
                            sine=   Math.sin(Math.toRadians( (double) i ))*length2;
                            cosine =  Math.cos(Math.toRadians ((double) i)  )*length2;
                            sinx = (int) Math.round(sine);
                            cosy = (int) Math.round(cosine);
                        }
                     
                  
                        g2D.drawLine(x+dx2,y+dy2, x+cosy+dx2,y+sinx+dy2);
                        if (N == 1) break;
                    
                    }
                    System.out.format("TTTTTTTTTTTTTTTTTTT%n");
                   
                    g.setColor(Color.black);
                    g.fillOval(x, y, 30, 30);
               */  
	}
	
        public void updateValues() {
            this.SB3 = MainWindow.getSB3(); // wavelength
            this.SB2 = MainWindow.getSB2(); //
            this.SB1 = MainWindow.getSB1(); //
            N = SB2; // N
          //  MainWindow.setMax(SB3/10);
           // System.out.format("The value of con is: %d%n", N);
            double size =this.SB1/1000.0; // a
            System.out.format("The value of size is: %d%n", this.SB1);
            double particle = Math.pow(size,6);
            double nom = (Math.pow(40,4))*282;
           
           
           /* double con = 8*Math.pow(PI,4)*SB1*1; //0.00000000751;
           System.out.format("The value of con is: %g%n", con);
           
            double cosine = 1.5;
            I = (con*size*cosine)/(1*Math.pow(SB3,4));
              System.out.format("The value of size is: %g%n", particle);
                System.out.format("The value of length is: %g%n", length);*/
            
            
            /*Timer timer = new Timer(20, (ActionListener) this);
            timer.setInitialDelay(190);
            timer.start();*/
           // for (int i =700; i>=400; i-=50) {
            //    SB3 = i;
            length = nom*particle / Math.pow(SB3/10,4);
            length *=1000;
            System.out.format("Length: %g%n", length);
            dx = (int) length;
            dy = (int) length;
            repaint();
         //   }
        }
     // http://stackoverflow.com/questions/3407942/rgb-values-of-visible-spectrum/22681410#22681410   
     
        public  void spectral_color(double l) // RGB <0,1> <- lambda l <400,700> [nm]
    { 
         if (l==400) l = 410;
         if (l==700) l = 690;
        double t;  red=0.0; green=0.0; blue=0.0;
         if ((l>=400.0)&&(l<410.0)) { t=(l-400.0)/(410.0-400.0); red=    +(0.33*t)-(0.20*t*t); }
    else if ((l>=410.0)&&(l<475.0)) { t=(l-410.0)/(475.0-410.0); red=0.14         -(0.13*t*t); }
    else if ((l>=545.0)&&(l<595.0)) { t=(l-545.0)/(595.0-545.0); red=    +(1.98*t)-(     t*t); }
    else if ((l>=595.0)&&(l<650.0)) { t=(l-595.0)/(650.0-595.0); red=0.98+(0.06*t)-(0.40*t*t); }
    else if ((l>=650.0)&&(l<700.0)) { t=(l-650.0)/(700.0-650.0); red=0.65-(0.84*t)+(0.20*t*t); }
         if ((l>=415.0)&&(l<475.0)) { t=(l-415.0)/(475.0-415.0); green=             +(0.80*t*t); }
    else if ((l>=475.0)&&(l<590.0)) { t=(l-475.0)/(590.0-475.0); green=0.8 +(0.76*t)-(0.80*t*t); }
    else if ((l>=585.0)&&(l<639.0)) { t=(l-585.0)/(639.0-585.0); green=0.84-(0.84*t)           ; }
         if ((l>=400.0)&&(l<475.0)) { t=(l-400.0)/(475.0-400.0); blue=    +(2.20*t)-(1.50*t*t); }
    else if ((l>=475.0)&&(l<560.0)) { t=(l-475.0)/(560.0-475.0); blue=0.7 -(     t)+(0.30*t*t); }
    }
}

