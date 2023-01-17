import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ImageControl {
    public BufferedImage[] binImgs;
    private BufferedImage srcImg;

    public ImageControl(){
        File path = new File("bin");
        File[] binArr = path.listFiles();
        binImgs = new BufferedImage[path.listFiles().length];
        for(int i = 0; i < path.listFiles().length; i++){
            try {
                binImgs[i] = ImageIO.read(binArr[i]);
            } catch (IOException e) {e.printStackTrace();}
        }
    }

    public ImageControl(File file) {
        BufferedImage oImg = OutlineImage(file);
        Colouring draw = new Colouring();
        draw.setWH(oImg.getWidth(), oImg.getHeight());
    }

    public BufferedImage OutlineImage(File file) {
        try {
            srcImg = ImageIO.read(file);
        } catch (IOException e) {e.printStackTrace();}

        int[][] src2d = new int[srcImg.getHeight()][srcImg.getWidth()];
        for(int i = 0; i < 2048; i++)
            for(int j = 0; j < 2048; j++)
                src2d[i][j] = srcImg.getRGB(i, j);

        return null;
    }
}
