package sin;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionListener;
import static java.lang.Math.PI;
import java.util.Random;
import java.awt.geom.AffineTransform;
import static java.awt.geom.AffineTransform.*;
import javax.swing.JPanel;
import javax.swing.Timer;

public class PaintPanel extends JPanel {
       // int SB3 = MainWindow.getSB3();
      //  int SB2 = MainWindow.getSB2();
      //  int SB1 = MainWindow.getSB1();
        static final int x = 705;
        static final int y = 405;
        
        // povodne malo byt vykreslovanie parametricke
        // ale trochu sa to vymklo z ruk
        
        // Y suradnice cesty zlava
        public static final int vertWidth = 150;
        public static final int vertDiff = 50;
        
        public static final int hordownY = 400;
        public static final int horupY = hordownY - vertWidth;
        
        // Y suradnica spodneho okraja
        public static final int downY = 800;
        public static final int secY = 50;
        public static final int thirdY = 100;
        
        // X suradnica laveho okraja
        public static final int startX = 0;
        public static final int diffX = 100;
        // X suradnica prve prerusenie
        public static final int firstX = 200;
         
        // X suradnica druhe prerusenie (zaciatok)
        public static final int secX = 350;
        
        //X suradnica krizovatka
        public static final int thirdX = 605;
        
        // sirka krizovatky
        public static final int Xdiff = 200;
        
        // pravy okraj
        public static final int fifthX = 1400;
        
        int N = 4;
        double length = 30;
        int dx = (int) length;
        int dy = (int) length;
        double I = 0;
        double red = 0.0;
        double green= 0.0;
        double blue = 0.0;
        boolean SKY = true;
        private final int ARR_SIZE = 6;
        private Graphics g;
        private boolean paintBackground = true;
        private int posX = 0;
        private int posY = 0;
        public boolean getSKY() {
            return SKY;
        }
        
        // vykreslenie sipok na cestach
        void drawArrow(Graphics g1, int x1, int y1, int x2, int y2) {
                Graphics2D g = (Graphics2D) g1.create();

                double dx = x2 - x1, dy = y2 - y1;
                double angle = Math.atan2(dy, dx);
                int len = (int) Math.sqrt(dx*dx + dy*dy);
                AffineTransform at = AffineTransform.getTranslateInstance(x1, y1);
                at.concatenate(AffineTransform.getRotateInstance(angle));
                g.transform(at);

                // Draw horizontal arrow starting in (0, 0)
                g.drawLine(0, 0, len, 0);
                g.fillPolygon(new int[] {len, len-ARR_SIZE, len-ARR_SIZE, len},
                              new int[] {0, -ARR_SIZE, ARR_SIZE, 0}, 4);
        }
        
        // vykreslenie vozidiel
        public void paintVehicle(Graphics g) {
            Graphics2D g2D = (Graphics2D) g;
          //  super.paintComponent(g);
            g2D.setColor(Color.GREEN);
             for (int i = 0; i < MainAgent.AgentList.size(); i++) {
                 
                 int x = MainAgent.AgentList.get(i).x;
                 int y = MainAgent.AgentList.get(i).y;
                 int type = MainAgent.AgentList.get(i).type;
                 boolean bus = MainAgent.AgentList.get(i).bus;
                 if (bus ) g2D.setColor(Color.ORANGE);
                 if (type==0)
                    g2D.fillRect(x, y, 40, 27);
                 else g2D.fillRect(x, y, 27, 40);
                 g2D.setColor(Color.GREEN);
             }
            //g2D.fillRect(posX, posY, 15, 10);
             //System.out.println("PaintVehicle");
            
        }
        
        // vykreslenie krizovatky, hranice, ciary, semafory
        @Override
	public void paintComponent(Graphics g){
                Graphics2D g2D = (Graphics2D) g;
                this.g = g;
                super.paintComponent(g);
              //  if (paintBackground) {
                g.setColor(Color.black);
                 g2D.setStroke(new BasicStroke(5));
                 
                 // spodna horizontalna  linia nalavo
              //  g2D.drawLine(startX,hordownY,firstX, hordownY); 
                g2D.drawLine(firstX+secX,hordownY,thirdX, hordownY);
                // horna horizontalna  linia nalavo
                g2D.drawLine(startX,horupY,thirdX, horupY);
                
                // spodna horizontalna linia napravo
                 g2D.drawLine(thirdX+Xdiff,hordownY,fifthX, hordownY);
                 
                 // horna horizontalna lina napravo
                 //g2D.drawLine(fifthX,horupY,fifthX-firstX, horupY);
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
                  
                 // odbocovak zapad-juh
                 g2D.drawLine(0,hordownY+vertDiff,firstX+diffX, hordownY+vertDiff);
                 g2D.drawLine(firstX+diffX,hordownY+vertDiff,firstX+secX-vertDiff, hordownY+vertDiff);
                 g2D.drawLine(firstX+secX-vertDiff,hordownY+vertDiff,thirdX, hordownY+secY+thirdY);
                 g2D.drawLine(firstX+secX,hordownY,thirdX, hordownY+secY);
                   
                 // odbocovak vychod-sever
                 g2D.drawLine(fifthX,horupY-vertDiff,fifthX-firstX-diffX, horupY-vertDiff); 
                 g2D.drawLine(fifthX-firstX-diffX,horupY-vertDiff,fifthX-firstX-secX+vertDiff, horupY-vertDiff);
                 g2D.drawLine(fifthX-firstX-secX+vertDiff,horupY-vertDiff,thirdX+Xdiff, horupY-secY-thirdY);  
                 g2D.drawLine(thirdX+Xdiff,horupY-secY,fifthX-firstX-secX,horupY);
                 
                 g.setColor(Color.white);
                 float dash1[] = {10.0f};
                 g2D.setStroke(new BasicStroke(3.0f,
                        BasicStroke.CAP_BUTT,
                        BasicStroke.JOIN_MITER,
                        10.0f, dash1, 0.0f));
                 // ciary nalavo
                 g2D.drawLine(0,hordownY,firstX+secX, hordownY);
                 g2D.drawLine(startX,hordownY-vertDiff,firstX+secX, hordownY-vertDiff);
                 g2D.setStroke(new BasicStroke(3));
                 g2D.drawLine(startX,hordownY-vertDiff*2,firstX+secX, hordownY-vertDiff*2);
                 g2D.drawLine(firstX+secX,hordownY-2,firstX+secX, hordownY-vertDiff*2);
                 
                 // ciary napravo
                 g2D.setStroke(new BasicStroke(3.0f,
                        BasicStroke.CAP_BUTT,
                        BasicStroke.JOIN_MITER,
                        10.0f, dash1, 0.0f));
                 g2D.drawLine(fifthX,horupY,fifthX-firstX-secX, horupY);
                 g2D.drawLine(fifthX,horupY+vertDiff,fifthX-firstX-secX, horupY+vertDiff);
                 g2D.setStroke(new BasicStroke(3));
                 g2D.drawLine(fifthX,horupY+vertDiff*2,fifthX-firstX-secX, horupY+vertDiff*2);
                 g2D.drawLine(fifthX-firstX-secX,horupY+2,fifthX-firstX-secX, horupY+vertDiff*2);
                 
                 //ciary dole
                 g2D.setStroke(new BasicStroke(3.0f,
                        BasicStroke.CAP_BUTT,
                        BasicStroke.JOIN_MITER,
                        10.0f, dash1, 0.0f));
                 g2D.drawLine(thirdX+Xdiff-vertDiff,hordownY+vertDiff,thirdX+Xdiff-vertDiff, downY);
                 g2D.drawLine(thirdX+Xdiff-vertDiff*2,hordownY+vertDiff,thirdX+Xdiff-vertDiff*2, downY);
                 g2D.setStroke(new BasicStroke(3));
                 g2D.drawLine(thirdX+Xdiff-vertDiff*3,hordownY+vertDiff,thirdX+Xdiff-vertDiff*3, downY);
                 g2D.drawLine(thirdX+Xdiff-vertDiff*3,hordownY+vertDiff,thirdX+Xdiff, hordownY+vertDiff);
                 
                 // ciary hore
                 g2D.setStroke(new BasicStroke(3.0f,
                        BasicStroke.CAP_BUTT,
                        BasicStroke.JOIN_MITER,
                        10.0f, dash1, 0.0f));
                g2D.drawLine(thirdX+vertDiff,horupY-vertDiff,thirdX+vertDiff,0);
                g2D.drawLine(thirdX+vertDiff*2,horupY-+vertDiff,thirdX+vertDiff*2,0);
                g2D.setStroke(new BasicStroke(3));
                g2D.drawLine(thirdX+vertDiff*3,horupY-vertDiff,thirdX+vertDiff*3,0);
                g2D.drawLine(thirdX+vertDiff*3,horupY-vertDiff,thirdX,horupY-vertDiff);
                
                // sipky nalavo
                drawArrow(g, startX+10, hordownY-vertDiff/2, firstX-vertDiff*3, hordownY-vertDiff/2);
                drawArrow(g, startX+10, hordownY+vertDiff/2, firstX-vertDiff*3, hordownY+vertDiff-10);
                
                drawArrow(g, firstX-vertDiff, hordownY-vertDiff/2, firstX, hordownY-vertDiff/2);
                drawArrow(g, firstX-vertDiff, hordownY+vertDiff/2, firstX, hordownY+vertDiff-10);
                
                drawArrow(g, firstX+vertDiff*2, hordownY-vertDiff/2, firstX+vertDiff*3, hordownY-vertDiff/2);
                drawArrow(g, firstX+vertDiff*5, hordownY-vertDiff/2, firstX+vertDiff*6, hordownY-vertDiff/2);
                
                drawArrow(g, firstX+vertDiff*2, hordownY+vertDiff/2, firstX+vertDiff*3, hordownY+vertDiff-10);
                drawArrow(g, firstX+vertDiff*4, hordownY+vertDiff/2, firstX+vertDiff*5, hordownY+vertDiff-10);
                drawArrow(g, firstX+vertDiff*6, hordownY+vertDiff/2, firstX+vertDiff*7, hordownY+vertDiff);
                
                drawArrow(g, firstX+vertDiff*3, hordownY-vertDiff-vertDiff/2, firstX+vertDiff*4, hordownY-vertDiff-vertDiff+10);
                drawArrow(g, firstX+vertDiff*5, hordownY-vertDiff-vertDiff/2, firstX+vertDiff*6, hordownY-vertDiff-vertDiff+10);
                
                // sipky napravo
                drawArrow(g, fifthX-10,horupY+vertDiff/2,fifthX-vertDiff*2, horupY+vertDiff/2);
                //drawArrow(g, fifthX-10,horupY,fifthX-vertDiff*2, horupY+vertDiff/5);
                drawArrow(g, fifthX-10,horupY-vertDiff/2,fifthX-vertDiff*2, horupY-vertDiff+10);
                
                drawArrow(g, fifthX-firstX,horupY+vertDiff/2,fifthX-firstX-vertDiff*2, horupY+vertDiff/2);
              //  drawArrow(g, fifthX-firstX,horupY+vertDiff/2,fifthX-firstX-vertDiff*2, horupY+vertDiff/5);
                drawArrow(g, fifthX-firstX,horupY-vertDiff/2,fifthX-firstX-vertDiff*2, horupY-vertDiff+10);
                
                drawArrow(g, fifthX-firstX-vertDiff*4,horupY+vertDiff/2,fifthX-firstX-vertDiff*5, horupY+vertDiff/2);
                drawArrow(g, fifthX-firstX-vertDiff*4,horupY-vertDiff/2,fifthX-firstX-vertDiff*5, horupY-vertDiff+10);
                
                drawArrow(g, fifthX-firstX-vertDiff*4,horupY+vertDiff/2+vertDiff,fifthX-firstX-vertDiff*5, horupY+vertDiff/2+vertDiff+10);
               // sipky dole 
               drawArrow(g,thirdX+Xdiff-vertDiff/2,hordownY+vertDiff*3,thirdX+Xdiff-vertDiff/5, hordownY+vertDiff+10);
               drawArrow(g,thirdX+Xdiff-vertDiff/2-vertDiff,hordownY+vertDiff*3,thirdX+Xdiff-vertDiff/2-vertDiff, hordownY+vertDiff+10);
               drawArrow(g,thirdX+Xdiff-vertDiff/2-vertDiff*2,hordownY+vertDiff*3,thirdX+Xdiff-vertDiff/2-vertDiff*2-20, hordownY+vertDiff+10);
               
               // sipky hore
               drawArrow(g,thirdX+vertDiff/2,horupY-vertDiff*3,thirdX+vertDiff/2-15,horupY-vertDiff-10);
               drawArrow(g,thirdX+vertDiff/2+vertDiff,horupY-vertDiff*3,thirdX+vertDiff/2+vertDiff,horupY-vertDiff-10);
               drawArrow(g,thirdX+vertDiff/2+vertDiff*2,horupY-vertDiff*3,thirdX+vertDiff/2+vertDiff*2+15,horupY-vertDiff-10);
             //  this.paintBackground = false;
             //  } 
            
                if (Crossroads.WESTtoEAST == 0) {
                    g.setColor(Color.red);
                    
                }
                else g.setColor(Color.green);
               g2D.fillOval(firstX+secX+vertDiff/2, hordownY-vertDiff/2-10, 15,15 );
               if (Crossroads.WESTtoNORTH == 0) {
                    g.setColor(Color.red);
                    
                } else g.setColor(Color.green);
               g2D.fillOval(firstX+secX+vertDiff/2, hordownY-vertDiff-vertDiff/2-10, 15,15 );
              // System.out.println(Crossroads.EASTtoWEST);
               if (Crossroads.EASTtoWEST == 0) {
                    g.setColor(Color.red);
                    
                } else g.setColor(Color.green);
               g2D.fillOval(fifthX-firstX-secX-vertDiff/2, horupY+vertDiff/2, 15,15 );
               if (Crossroads.EASTtoSOUTH == 0) {
                    g.setColor(Color.red);
                    
                } else g.setColor(Color.green);
               g2D.fillOval(fifthX-firstX-secX-vertDiff/2, horupY+vertDiff+vertDiff/2, 15,15 );
               if (Crossroads.SOUTHtoEAST == 0) {
                    g.setColor(Color.red);
                    
                } else g.setColor(Color.green);
               g2D.fillOval(thirdX+Xdiff-vertDiff/2,hordownY+vertDiff/2, 15,15 );
               if (Crossroads.SOUTHtoNORTH == 0) {
                    g.setColor(Color.red);
                    
                } else g.setColor(Color.green);
               g2D.fillOval(thirdX+Xdiff-vertDiff-vertDiff/2,hordownY+vertDiff/2, 15,15 );
               if (Crossroads.SOUTHtoWEST == 0) {
                    g.setColor(Color.red);
                    
                } else g.setColor(Color.green);
               g2D.fillOval(thirdX+Xdiff-vertDiff*2-vertDiff/2,hordownY+vertDiff/2, 15,15 );
               
               if (Crossroads.NORTHtoWEST == 0) {
                    g.setColor(Color.red);
                    
                } else g.setColor(Color.green);
               g2D.fillOval(thirdX+vertDiff/2,horupY-vertDiff/2, 15,15 );
               if (Crossroads.NORTHtoSOUTH == 0) {
                    g.setColor(Color.red);
                    
                } else g.setColor(Color.green);
               g2D.fillOval(thirdX+vertDiff/2+vertDiff,horupY-vertDiff/2, 15,15 );
               if (Crossroads.NORTHtoEAST == 0) {
                    g.setColor(Color.red);
                    
                } else g.setColor(Color.green);
               g2D.fillOval(thirdX+vertDiff/2+vertDiff*2,horupY-vertDiff/2, 15,15 );
               g.setColor(Color.green);
               paintVehicle(g);
              
	}
        /*public void updateVehicles(int x, int y) {
            this.posX = x;
            this.posY = y;
            System.out.format("Vehicle Pos%n" + x,y);
            repaint();
        }*/
	
        public void updateValues() {
          repaint();
          
        }
    
     
       
}

