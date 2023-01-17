package shape;
import java.awt.*;

/*
This abstract class defines all basic independent attributes of a shape
*/
public abstract class FillableShape extends Shape{
    private boolean filled;
    
    //Constructor
    public FillableShape(int initx1,int inity1,int initx2,int inity2, Color initColor,boolean initFilled) {
        super(initx1, inity1, initx2, inity2, initColor);
        setFilled(initFilled);
    }
    
    /** 
     * @return int
     */
    // Method to return the top-left-most x coordinate of the fillable shape
    public int getTopLeftX() {
        return Math.min(getX1(), getX2());
    }
    
    /** 
     * @return int
     */
    // Method to return the top-left-most y coordinate of the fillable shape
    public int getTopLeftY() {
        return Math.min(getY1(), getY2());
    }
    
    /** 
     * @return int
     */
    // Method to return the Width of the fillable shape
    public int getWidth() {
        return Math.abs(getX1() - getX2());
    }
    
    /** 
     * @return int
     */
    // Method to return the Height of the fillable shape
    public int getHeight() {
        return Math.abs(getY1() - getY2());
    }

    
    /** 
     * @return boolean
     */
    // Accessor to return the value of the filled instance variable as a boolean object
    public boolean getFilled() {
        return filled;
    }

    
    /** 
     * @param newFilled
     */
    // Mutator for filled instance variable
    public void setFilled(boolean newFilled){
        filled = newFilled;
    }
    
    
    
    /** 
     * @param a
     * @param b
     * @return double
     */
    /** 
     * @param a
     * @param b
     * @return double
     */
    //An abstract method to Calculate the Area of the fillable shape
    public abstract double calcArea();

    
    /** 
     * @param a
     * @param b
     * @return boolean
     */
    //Method to return the boolean value of overlapping shapes
    public boolean isOverlapping (FillableShape a, FillableShape b) {
        return (Math.max(Math.min(a.getX2(), b.getX2())
        - Math.max(a.getX1(), b.getX1()), 0)
        * Math.max(Math.min(a.getY2(), b.getY2())
        - Math.max(a.getY1(), b.getY1()), 0) > 0);
    }   

    
    /** 
     * @return String
     */
    //Standard toString() method
    public String toString() {
        return "("+getX1()+","+getY1()+"), ("+getX2()+","+getY2()+"), filled = " + filled;
    } 
}