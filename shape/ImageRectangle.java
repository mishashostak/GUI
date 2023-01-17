package shape;

import java.awt.*;

public class ImageRectangle extends Rectangle{

    private Image image;

    private Rectangle rectangle;

    public ImageRectangle(int initx1,int inity1,int initx2,int inity2, Image img) {
        super(initx1, inity1, initx2, inity2, null, false);
        this.image = img;
        this.rectangle = this;
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
