import java.awt.Graphics2D;
//import java.awt.Graphics;
import java.awt.Color;
//import java.awt.BorderLayout;
//import java.awt.GridLayout;
import java.awt.image.BufferedImage;
import java.awt.image.RescaleOp;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
/* 
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.SwingUtilities;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
*/

public class ImageControl {
    protected BufferedImage[] binImgs;
    private BufferedImage srcImg, finImg, grayImg;
    private boolean fin = false;

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

    public ImageControl(String file) {
        try{
            srcImg = ImageIO.read(new File(file));
            OutlineImage();
            while(!fin);
            new Colouring(finImg, srcImg.getWidth(), srcImg.getHeight());
        } catch (IOException|NullPointerException e) {e.printStackTrace();}
    }

    public void OutlineImage() {
        /* 
        float[] scales = {2f, 2f, 2f};
        float[] offsets = new float[4];
        RescaleOp rop = new RescaleOp(scales, offsets, null);
        */

        grayImg = new BufferedImage(
            srcImg.getWidth(), srcImg.getHeight(), BufferedImage.TYPE_BYTE_GRAY);
        Graphics2D g = grayImg.createGraphics();
        g.drawImage(srcImg, 0, 0, null);
        BufferedImage blk = OnlyBlack(grayImg);

        /*
        final BufferedImage scaledImage = new BufferedImage(
            srcImg.getWidth(), srcImg.getHeight(), BufferedImage.TYPE_INT_RGB);
        g = scaledImage.createGraphics();
        g.drawImage(srcImg, rop, 0, 0);
        */

        g.dispose();

        finImg = blk;
        fin = true;

        /* 
        Runnable r = new Runnable() {

            @Override
            public void run() {
                JPanel gui = new JPanel(new BorderLayout(2, 2));
                JPanel images = new JPanel(new GridLayout(0, 2, 2, 2));
                gui.add(images, BorderLayout.CENTER);

                final JLabel scaled = new JLabel(new ImageIcon(scaledImage));
                final JLabel bwLabel = new JLabel(new ImageIcon(blk));
                final JSlider brighten = new JSlider(0, 1000, 100);
                gui.add(brighten, BorderLayout.PAGE_START);
                ChangeListener cl = new ChangeListener() {

                    @Override
                    public void stateChanged(ChangeEvent e) {
                        int val = brighten.getValue();
                        float valFloat = val / 1000f;
                        BufferedImage bi = Brighten(srcImg, valFloat);
                        BufferedImage gr = new BufferedImage(
                                srcImg.getWidth(), srcImg.getHeight(), BufferedImage.TYPE_BYTE_GRAY);
                        Graphics g = gr.createGraphics();
                        g.drawImage(bi, 0, 0, null);

                        scaled.setIcon(new ImageIcon(gr));
                    }
                };
                brighten.addChangeListener(cl);

                images.add(bwLabel);
                images.add(scaled);

                String[] opts = {"1", "2"};
                int choice = JOptionPane.showOptionDialog(null, 
                    gui, 
                "image picker", 
                JOptionPane.YES_NO_OPTION, 
                JOptionPane.QUESTION_MESSAGE, 
                null, opts, null);

                if (choice == JOptionPane.YES_OPTION) {
                    ImageIcon icon = (ImageIcon) bwLabel.getIcon();
                    finImg = (BufferedImage) icon.getImage();
                    fin = true;
                }
                else if (choice == JOptionPane.NO_OPTION) {
                    ImageIcon icon = (ImageIcon) scaled.getIcon();
                    finImg = (BufferedImage) icon.getImage();
                    fin = true;
                }
            }
        };
        SwingUtilities.invokeLater(r);
        */
    }

    /**
     * Returns the supplied src image brightened by a float value from 0 to 10.
     * Float values below 1.0f actually darken the source image.
     */
    public static BufferedImage Brighten(BufferedImage src, float level) {
        BufferedImage dst = new BufferedImage(
                src.getWidth(), src.getHeight(), BufferedImage.TYPE_INT_RGB);
        float[] scales = {level, level, level};
        float[] offsets = new float[4];
        RescaleOp rop = new RescaleOp(scales, offsets, null);

        Graphics2D g = dst.createGraphics();
        g.drawImage(src, rop, 0, 0);
        g.dispose();

        return dst;
    }

    
    /** 
     * @param inp
     * @return BufferedImage
     */
    private BufferedImage OnlyBlack(BufferedImage inp) {
        Color BLK = new Color(0,0,0);
        final int BLKRGB = BLK.getRGB();
        //Color WHT = new Color(255,255,255);
        //final int WHTRGB = WHT.getRGB();

        int WIDTH = inp.getWidth();
        int HEIGHT = inp.getHeight();
        int pixels[] = new int[WIDTH*HEIGHT];
        inp.getRGB(0, 0, WIDTH, HEIGHT, pixels, 0, WIDTH);
        for(int i=0; i<pixels.length;i++) {
            if (pixels[i] != BLKRGB) {
                pixels[i] = 0x00ffffff;
            }
        }
        inp.setRGB(0, 0, WIDTH, HEIGHT, pixels, 0, WIDTH);
        return inp;
        }
}
