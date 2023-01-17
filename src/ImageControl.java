import java.awt.Graphics2D;
import java.awt.color.ColorSpace;
import java.awt.image.BufferedImage;
import java.awt.image.ColorConvertOp;
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
        new Colouring(oImg, oImg.getWidth(), oImg.getHeight());
    }

    public BufferedImage OutlineImage(File file) {
        try {
            srcImg = ImageIO.read(file);
        } catch (IOException e) {e.printStackTrace();}
        Graphics2D g = srcImg.createGraphics();
        g.drawImage(srcImg, null, 0, 0);
			BufferedImage grayImage = new BufferedImage(srcImg.getWidth(), srcImg.getHeight(), BufferedImage.TYPE_BYTE_GRAY);
			g = grayImage.createGraphics();
			g.drawImage(srcImg, 0, 0, null);

            ColorConvertOp op = new ColorConvertOp(ColorSpace.getInstance(ColorSpace.CS_GRAY), null);
            op.filter(grayImage,grayImage);

            BufferedImage blackWhite = new BufferedImage(srcImg.getWidth(), srcImg.getHeight(), BufferedImage.TYPE_BYTE_BINARY);
            g = blackWhite.createGraphics();
            g.drawImage(grayImage, null, 0, 0);
            g.dispose();
        /*
        int[][] src2d = new int[srcImg.getHeight()][srcImg.getWidth()];
        for(int i = 0; i < 2048; i++)
            for(int j = 0; j < 2048; j++)
                src2d[i][j] = srcImg.getRGB(i, j);
        */

        return blackWhite;
    }
}
