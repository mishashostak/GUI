import java.awt.Dimension;
import javax.swing.JFrame;

/**
 * Author: Misha Shostak; ICS4U1
 * GUI
 * Standard Main class **(utilised exclusively for Debugging)**
 */
public class Main {
    
    /** 
     * @param args
     */
    public static void main(String[] args) {
        System.out.printf("%n");

        JFrame randImg = new JFrame( "random" );
        
        DrawPanel panel = new DrawPanel();
        randImg.add( panel );
        
        randImg.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        randImg.setPreferredSize(new Dimension(600, 600));
        randImg.setLocationRelativeTo(null);
        randImg.pack();
        randImg.setVisible(true); 
        /* 
        Rectangle r = new Rectangle();
        r.setX1(4);
        r.setY1(4);
        r.setX2(50);
        r.setY2(50);
        r.setFilled(true);
        System.out.printf("%n%s", r.toString());
        System.out.printf("%nrect count: %d", Rectangle.getRectCount());
        System.out.printf("%nrect upper left x: %d", r.getTopLeftX());
        System.out.printf("%nrect upper left y: %d", r.getTopLeftY());
        System.out.printf("%nrect width: %d", r.getWidth());
        System.out.printf("%nrect height: %d", r.getHeight());
        System.out.printf("%nrect area: %f", r.calcArea());
        System.out.printf("%nrect1 is square?: %b%n", r.isSquare());

        Rectangle r1 = new Rectangle(70,70,190,190,null, true);
        System.out.printf("%n%s", r1.toString());
        System.out.printf("%nrect1 count: %d", Rectangle.getRectCount());
        System.out.printf("%nrect1 upper left x: %d", r1.getTopLeftX());
        System.out.printf("%nrect1 upper left y: %d", r1.getTopLeftY());
        System.out.printf("%nrect1 width: %d", r1.getWidth());
        System.out.printf("%nrect1 height: %d", r1.getHeight());
        System.out.printf("%nrect1 area: %f", r1.calcArea());
        System.out.printf("%nrect1 is square?: %b", r1.isSquare());
        System.out.printf("%nrect1 is overlapping rect?: %b%n", r1.isOverlapping(r, r1));

        Oval o = new Oval();
        r.setX1(200);
        r.setY1(200);
        r.setX2(310);
        r.setY2(310);
        r.setFilled(true);
        System.out.printf("%n%s", o.toString());
        System.out.printf("%noval count: %d", Oval.getOvalCount());
        System.out.printf("%noval upper left x: %d", o.getTopLeftX());
        System.out.printf("%noval upper left y: %d", o.getTopLeftY());
        System.out.printf("%noval width: %d", o.getWidth());
        System.out.printf("%noval height: %d", o.getHeight());
        System.out.printf("%noval area: %f", o.calcArea());
        System.out.printf("%noval is circle?: %b%n", o.isCircle());

        Oval o1 = new Oval(300,300,400,450,null, false);
        System.out.printf("%n%s", o1.toString());
        System.out.printf("%noval count: %d", Oval.getOvalCount());
        System.out.printf("%noval upper left x: %d", o1.getTopLeftX());
        System.out.printf("%noval upper left y: %d", o1.getTopLeftY());
        System.out.printf("%noval width: %d", o1.getWidth());
        System.out.printf("%noval height: %d", o1.getHeight());
        System.out.printf("%noval area: %f", o1.calcArea());
        System.out.printf("%noval is circle?: %b", o1.isCircle());
        System.out.printf("%noval is overlapping?: %b%n%n", o1.isOverlapping(o, o1));

        */
    }
}