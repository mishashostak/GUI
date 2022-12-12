import java.awt.Color;
/*
This class defines all dependent attributes of a desired shape; Oval
*/
public class Oval extends FillableShape{
    public static int ovalCount = 0;
    
    // Parameterized constructor method
    public Oval(int initx1, int inity1, int initx2, int inity2, Color initColor, boolean initFilled){
        super(initx1,inity1,initx2,inity2, initColor, initFilled);
        ovalCount++;
    }
    // No-argument constructor method
    public Oval(){
        this(0,0,0, 0, Color.BLACK, false);
    }
    
    // Accessor for ovalCount instance variable
    public static int getOvalCount() {
        return ovalCount;
    }   

    //Method to return the boolean value of ovular subset: circle
    public boolean isCircle() {
        return (getWidth() == getHeight());
    }

    //Returns the calculated Area of the Oval/Circle
    @Override
    public double calcArea() {
        if (isCircle()){
            return Math.PI * Math.pow(getWidth()/2,2);
        }
        else {
            return Math.PI * (getWidth()/2) * (getHeight()/2);
        }
    }
}
