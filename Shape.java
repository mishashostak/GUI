import java.awt.*;

public abstract class Shape {
    private int x1;
    private int y1;
    private int x2;
    private int y2;
    private Color color;

    //Constructor
    public Shape(int initx1,int inity1,int initx2,int inity2, Color initColor) {
        setX1(initx1);
        setY1(inity1);
        setX2(initx2);
        setY2(inity2);
        setColor(initColor);
    }

    // Accessor to return the value of the x1 instance variable as an integer
    public int getX1() {
        return x1;
    }
    // Accessor to return the value of the y1 instance variable as an integer
    public int getY1() {
        return y1;
    }
    // Accessor to return the value of the x2 instance variable as an integer
    public int getX2() {
        return x2;
    }
    // Accessor to return the value of the y2 instance variable as an integer
    public int getY2() {
        return y2;
    }
    // Accessor to return the value of the color instance variable as a Color
    public Color getColor() {
        return color;
    }

    // Mutator for x1 instance variable
    public void setX1(int newx1) {
        if (newx1 < 0) {
            System.err.println( "Attempt to set x1 coordinate negative ignored, setting to 0 by default." );
            x1 = 0;
        }
        else {
            x1 = newx1;
        }
    }
    // Mutator for y1 instance variable
    public void setY1(int newy1) {
        if (newy1 < 0) {
            System.err.println( "Attempt to set y1 coordinate negative ignored, setting to 0 by default." );
            y1 = 0;
        }
        else {
            y1 = newy1;
        }
    }
    // Mutator for x2 instance variable
    public void setX2(int newx2) {
        if (newx2 < 0) {
            System.err.println( "Attempt to set x2 coordinate negative ignored, setting to 0 by default." );
            x2 = 0;
        }
        else {
            x2 = newx2;
        }
    }
    // Mutator for y2 instance variable
    public void setY2(int newy2) {
        if (newy2 < 0) {
            System.err.println( "Attempt to set y2 coordinate negative ignored, setting to 0 by default." );
            y2 = 0;
        }
        else {
            y2 = newy2;
        }
    }

    // Mutator for x1 instance variable
    public void setColor(Color newColor) {
        color = newColor;
    }
}
