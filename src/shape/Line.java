package shape;
import java.awt.*;

public class Line extends Shape{

    public Line(int initx1, int inity1, int initx2, int inity2, Color initColor) {
        super(initx1, inity1, initx2, inity2, initColor);
    }
    
    
    /** 
     * @param g
     */
    public void draw( Graphics g ) {
        g.setColor( getColor() );
        g.drawLine( getX1(), getY1(), getX2(), getY2() );
    } 
}
