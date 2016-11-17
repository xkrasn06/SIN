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
        int x = 705;
        int y = 405;
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
                if (!SKY) {
                    spectral_color((double) SB3);
                    g.setColor(Color.black);
              //  g.drawRect(50, 100,900, 600);
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
                //g2D.setStroke(dashed);
               
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
                    //  System.out.format("The value of sup is: %g%n", (1 + Math.pow(Math.cos(Math.abs(i)*Math.PI/180),2)));
                            sine=   Math.sin(Math.toRadians( (double) i ))*length2;
                            cosine =  Math.cos(Math.toRadians ((double) i)  )*length2;
                            sinx = (int) Math.round(sine);
                            cosy = (int) Math.round(cosine);
                            i = j;
                        }
                        else {
                            length2 = (1 + Math.pow(Math.cos(Math.abs(i)*Math.PI/180),2))*length;
                            //  System.out.format("The value of sup is: %g%n", (1 + Math.pow(Math.cos(Math.abs(i)*Math.PI/180),2)));
                            sine=   Math.sin(Math.toRadians( (double) i ))*length2;
                            cosine =  Math.cos(Math.toRadians ((double) i)  )*length2;
                            sinx = (int) Math.round(sine);
                            cosy = (int) Math.round(cosine);
                        }
                     
                   // System.out.format("The value of l is: %g%n", length2);
                   /* System.out.format("The value of sin is: %d %d%n",i, sinx);
                    System.out.format("The value of cos is: %d %d%n",i, cosy);
                     System.out.format("The value of cos is: %n");*/
                   //length = cosy*length;
                   // dx = (int) length;
                   // dy = (int) length;
                        g2D.drawLine(x+dx2,y+dy2, x+cosy+dx2,y+sinx+dy2);
                        if (N == 1) break;
                    
                    }
                    System.out.format("TTTTTTTTTTTTTTTTTTT%n");
                    /*
                    g2D.drawLine(x+dx2,y+dy2, x+dx+dx2,y+dy+dy2);
                    g2D.drawLine(x+dx2,y+dy2, x-dx+dx2,y+dy+dy2);
                    g2D.drawLine(x+dx2,y+dy2, x-dx+dx2,y-dy+dy2);
                    g2D.drawLine(x+dx2,y+dy2, x+dx+dx2,y-dy+dy2);
                    g2D.drawLine(x+dx2,y+dy2, x+dx2,y+dy+dy2);
                    g2D.drawLine(x+dx2,y+dy2, x+dx2,y-dy+dy2);
                    g2D.drawLine(x+dx2,y+dy2, x+dx+dx2,y+dy2);
                    g2D.drawLine(x+dx2,y+dy2, x-dx+dx2,y+dy2);*/
                    g.setColor(Color.black);
                    g.fillOval(x, y, 30, 30);
                    //g.fillRect(75,305, 300, 5);
                } else {
                    g.setColor(Color.green);
                    g.fillRect(40, 725, 1400, 725);
                    g.setColor(Color.black);
                 // g.drawRect(5,5,190,190);
                 // the head
                    g.drawOval(650,650,20,20);
        
                    // the syntax of drawLine is (x1,y1,x2,y2);
                     // to draw a line from point (x1,y1) to (x2,y2)
       
                     // the body
                    g.drawLine(660,670,660,680);
       
                     // the hands
                    g.drawLine(660,680,640,680);
                    g.drawLine(660,680,680,665);
       
                     // the legs
                    g.drawLine(660,680,645,725);
                    g.drawLine(660,680,675,725);
       
                     // the greeting
                    g.setFont(new Font("Courier New", Font.BOLD, 20));
                   
                   
                    g.drawString("DISABLED", 1170,15);
                    g.drawString("DISABLED", 120,15);
                    g.drawString("DISABLED", 840,15);
                    g.drawString("Value: " + SB2, 490,15);
                    g.setFont(new Font("Courier New", Font.BOLD, 20));
                    g.drawString("FYO Student", 600, 630);
                    g.drawString("Sunlight", 20, 80);
                    g.drawString("Blue Light Laterally Scattered", 380, 250);
                    g.drawString("Unscattered Yellow/Orange/Red", 940, 250);
                    g.drawString("Earth", 100, 745);
                    g.drawString("Atmosphere", 100, 710);
                    g.drawString("BLUE SKY", 670, 60);
                    int diameter = 7;
                    int coords[] = {500, 500, 200, 423, 700, 120, 1000, 600, 800, 340};
                    g.setColor(Color.white);
                    g.fillRect(0, 100, 40, 700);
                    g.setColor(Color.black);
                    g.drawLine(coords[0],coords[1] - 30,500,260);
                    g.drawLine(coords[2]+20,coords[3] - 30,500,260);
                    g.drawLine(coords[6],coords[7] - 30,500,260);
                    g.drawLine(coords[8]+200,coords[9] - 10,1000,260);
                    g.drawLine(coords[0]+600,coords[1] - 10,1000,260);
                    g.drawLine(coords[2]+700,coords[3] - 10,1000,260);
                    g.drawArc(40, 100, 1340,1320, 0, 180);
                    g.fillOval(coords[0], coords[1], diameter, diameter);
                    g.fillOval(coords[2], coords[3], diameter, diameter);
                    g.fillOval(coords[4], coords[5], diameter, diameter);
                    g.fillOval(coords[6], coords[7], diameter, diameter);
                    g.fillOval(coords[8], coords[9], diameter, diameter);
                     
                    for (int i=0; i<10; i+=2) {
                        g.setColor(Color.white);
                        g2D.setStroke(new BasicStroke(3));
                        g.drawLine(40,coords[i+1]+diameter/2, coords[i], coords[i+1]+diameter/2);
                        Random randomGenerator = new Random();
                        int r = randomGenerator.nextInt(80);
                        r+=580;
                        System.out.format("The value of r is: %g%n", (double) r);
                        spectral_color((double) r);
                        /*red = 0;
                        green = 0;
                        blue = 0;*/
                        Color c = new Color((float) red, (float) green, (float)blue);
                        g.setColor(c);
                        /* if (r == 1)
                            g.setColor(Color.red);
                         else g.setColor(Color.orange);*/
                        g2D.setStroke(new BasicStroke(3));
                        g.drawLine(coords[i]+diameter,coords[i+1]+diameter/2, 1380, coords[i+1]+diameter/2);
                         
                        for (int f = 0; f < this.SB2; f++) {
                         
                            int randomInt = randomGenerator.nextInt(360);
                            int j = i;
                            i = randomInt;
                            int length = 30;
                            double length2 = (1 + Math.pow(Math.cos(Math.abs(i)*Math.PI/180),2))*length;
                        //  System.out.format("The value of sup is: %g%n", (1 + Math.pow(Math.cos(Math.abs(i)*Math.PI/180),2)));
                            double sine=   Math.sin(Math.toRadians( (double) i ))*length2;
                            double cosine =  Math.cos(Math.toRadians ((double) i)  )*length2;
                            int    sinx = (int) Math.round(sine);
                            int cosy = (int) Math.round(cosine);
                         
                            i = j;
                            g.setColor(Color.blue);
                            g2D.drawLine(coords[i]+diameter/2,coords[i+1]+diameter/2, coords[i]+cosy,coords[i+1]+sinx);
                            g.setColor(c);
                        }
                    }
                }       
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

