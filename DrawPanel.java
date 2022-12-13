import java.awt.*;
import java.util.*;
import javax.swing.JPanel;
 
public class DrawPanel extends JPanel {
    // We'll use a shared array to store 10 Line objects
    private ArrayList<Shape> shapes = new ArrayList<Shape>();
    
    // Constructor instantiates an array of 10 Random Line objects
    public DrawPanel() {
        // We'll use the Random class to simplify picking random integers
        Random rand = new Random();   
        setBackground( Color.WHITE );
        
        // Create 10 Line objects with random coordinates and colours
        for ( int i = 0; i < 10; i++ ) {
            // generate random coordinates
            int x1 = rand.nextInt( 300 );
            int y1 = rand.nextInt( 300 );
            int x2 = rand.nextInt( 300 );
            int y2 = rand.nextInt( 300 );
            int filled = rand.nextInt(2);
            // generate a random color
            Color color = new Color( rand.nextInt( 256 ), rand.nextInt( 256 ), rand.nextInt( 256 ) );

            // add the line to the array of lines to be displayed
            switch(rand.nextInt(3)) {
                case 0:
                    shapes.add(new Line( x1, y1, x2, y2, color ));
                    break;
                case 1:
                    shapes.add(new Oval( x1, y1, x2, y2, color, (filled == 1) ));
                    break;
                case 2:
                    shapes.add(new Rectangle( x1, y1, x2, y2, color, (filled == 1) ));
                    break;
            }
        } 
    } 
    
    // This method is called automatically by the JVM when the window needs to be (re)drawn.
    @Override
    public void paintComponent( Graphics g ) {
        super.paintComponent( g );
        
        // Call the draw() method for each Line object in the array
        for ( Shape shape : shapes )
            shape.draw( g );
    } 
} 