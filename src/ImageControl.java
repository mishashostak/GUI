import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ImageControl {
    public BufferedImage[] binImgs;

    ImageControl(){
        File path = new File("bin");
        File[] binArr = path.listFiles();
        binImgs = new BufferedImage[path.listFiles().length];
        for(int i = 0; i < path.listFiles().length; i++){
            try {
                binImgs[i] = ImageIO.read(binArr[i]);
            } catch (IOException e) {
            }
        }
    }
}
