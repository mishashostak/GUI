package shape;
import java.awt.*;

/*
This class defines all dependent attributes of a desired shape; Rectangle
*/
public class Rectangle extends FillableShape{
    public static int rectCount = 0;
    
    // Parameterized constructor method
    public Rectangle(int initx1,int inity1,int initx2,int inity2, Color initColor, boolean initFilled){
        super(initx1,inity1,initx2,inity2, initColor, initFilled);
        rectCount++;
    }

    // Parameterized constructor method for image
    public Rectangle(int initx1,int inity1,int initx2,int inity2, Image img){
        super(initx1,inity1,initx2,inity2,null,null);
        ImageRectangle imgRect = new ImageRectangle(img,this);
        rectCount++;
    }

    // No-argument constructor method
    public Rectangle(){
        this(0,0,0, 0, Color.BLACK, false);
    }

    
    /** 
     * @return int
     */
    // Accessor for rectCount instance variable
    public static int getRectCount() {
        return rectCount;
    }
    
    
    /** 
     * @return boolean
     */
    //Method to return the boolean value of rectangular subset: square
    public boolean isSquare() {
        return (getWidth() == getHeight());
    }
    
    
    /** 
     * @return double
     */
    //Returns the calculated Area of the Oval/Circle
    @Override
    public double calcArea() {
        return getWidth()*getHeight();
    }

    
    /** 
     * @param g
     */
    public void draw( Graphics g ) {
        g.setColor(getColor());
        if(getFilled()) g.fillRect(getX1(), getX2(), getY1(), getY2());

        else g.drawRect(getX1(), getX2(), getY1(), getY2());
    } 
}

class ImageRectangle {

    private final Image image;

    private Rectangle rectangle;

    public ImageRectangle(Image image, Rectangle rectangle) {
        this.image = image;
        this.rectangle = rectangle;
    }

    public Rectangle getRectangle() {
        return rectangle;
    }

    public void setRectangle(Rectangle rectangle) {
        this.rectangle = rectangle;
    }

    public Image getImage() {
        return image;
    }

}