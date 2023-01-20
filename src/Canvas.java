import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;

import javax.imageio.ImageIO;
import javax.swing.JComponent;
import javax.swing.event.MouseInputAdapter;

/**
 * This class is the canvas for the Colouring class
 * 
 * @author Misha Shostak
 * 
 * @version 1/19/2023
 */
public class Canvas extends JComponent {
	private int X1, Y1, X2, Y2;
	private Graphics2D g;
	private BufferedImage img, background, undoTemp, redoTemp;
	private final SizedStack<Image> undoStack = new SizedStack<>(12);
	private final SizedStack<Image> redoStack = new SizedStack<>(12);
	private Rectangle shape;
	private MouseMotionListener motion;
	private MouseListener listener;
	private FillMouseListener fml;
	private Color chosCol = Color.BLACK;
	
	/** 
	 * Saves desired image as a file
	 * 
	 * @param file
	 */
	public void save(File file) {
		try {
			ImageIO.write((RenderedImage) img, "PNG", file);
		} catch (IOException ex) {
		}
	}

	/** 
	 * Loads desired file as an image
	 * 
	 * @param file 
	 */
	public void load(File file) {
		try {
			img = ImageIO.read(file);
			g = (Graphics2D) img.getGraphics();
		}

		catch (IOException ex) {
		}
	}

	/** 
	 * Typical paintComponent, it paints
	 * 
	 * @param g1
	 */
	protected void paintComponent(Graphics g1) {
		if (img == null) {
			img = (BufferedImage) createImage(getSize().width, getSize().height);
			g = (Graphics2D) img.getGraphics();
			g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
					RenderingHints.VALUE_ANTIALIAS_ON);

			clear();
			
		}
		g1.drawImage(img, 0, 0, null);
		
		if (shape != null) {
			Graphics2D g2d = (Graphics2D) g;
			g2d.draw(shape);
		}
	}

	/**
     * Parameterized constructor to begin the paint program
     * 
     * @param img Given image to be set as background
     * @param width 
     * @param height
     */
	public Canvas(BufferedImage img, int width, int height) {
		setSize(width,height);
		setBackground(img);
		defaultListener();
	}

	/**
	 * Sets standard mouse listeners for drawing function
	 */
	public void defaultListener() {
		setDoubleBuffered(false);
		listener = new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				saveToStack(img);
				X2 = e.getX();
				Y2 = e.getY();
			}
		};

		motion = new MouseMotionAdapter() {
			public void mouseDragged(MouseEvent e) {
				X1 = e.getX();
				Y1 = e.getY();

				if (g != null) {
					g.drawLine(X2, Y2, X1, Y1);
					repaint();
					X2 = X1;
					Y2 = Y1;
				}
			}
		};
		addMouseListener(listener);
		addMouseMotionListener(motion);
	}

	/** 
	 * @param rectangle
	 * @param color
	 */
	public void addRectangle(Rectangle rectangle, Color color) {

		Graphics2D g2d = (Graphics2D) img.getGraphics();
		g2d.setColor(color);
		g2d.draw(rectangle);
		repaint();
	}

	/**
	 * ########
	 * 
	 * Color methods to set paint as desired color
	 * 
	 * ########
	 */

	public void red() {
		g.setPaint(Color.red);
		setCol(Color.red);
	}

	public void black() {
		g.setPaint(Color.black);
		setCol(Color.black);
	}

	public void magenta() {
		g.setPaint(Color.magenta);
		setCol(Color.magenta);
	}

	public void green() {
		g.setPaint(Color.green);
		setCol(Color.green);
	}

	public void blue() {
		g.setPaint(Color.blue);
		setCol(Color.blue);
	}

	public void gray() {
		g.setPaint(Color.GRAY);
		setCol(Color.GRAY);
	}

	public void orange() {
		g.setPaint(Color.ORANGE);
		setCol(Color.ORANGE);
	}

	public void yellow() {
		g.setPaint(Color.YELLOW);
		setCol(Color.YELLOW);
	}

	public void pink() {
		g.setPaint(Color.PINK);
		setCol(Color.PINK);
	}

	public void cyan() {
		g.setPaint(Color.CYAN);
		setCol(Color.CYAN);
	}

	public void lightGray() {
		g.setPaint(Color.lightGray);
		setCol(Color.lightGray);
	}

	/** 
	 * @param color Chosen color from JColorChooser
	 */
	public void picker(Color color) {
		g.setPaint(color);
		setCol(color);
	}

	/**
	 * This method removes all altered foreground,
	 * clearing the canvas/image
	 */
	public void clear() {
		if (background != null) {
			setImage(copyImage(background));
		} else {
			g.setPaint(Color.white);
			g.fillRect(0, 0, getSize().width, getSize().height);
			g.setPaint(Color.black);
		}
		repaint();
	}

	/**
	 * Undo function
	 * Uses a SizedStack
	 */
	public void undo() {
		if (undoStack.size() > 0) {
			undoTemp = (BufferedImage) undoStack.pop();
			redoStack.push(img);
			setImage(undoTemp);
		}
	}

	/**
	 * Redo function
	 * Uses a SizedStack
	 */
	public void redo() {
		if (redoStack.size() > 0) {
			redoTemp = (BufferedImage) redoStack.pop();
			undoStack.push(img);
			setImage(redoTemp);
		}
	}

	/**
	 * Serves only to debug
	 */
	public void pencil() {
		removeMouseListener(listener);
		removeMouseMotionListener(motion);
		defaultListener();
	}

	/**
	 * Serves only to debug
	 */
	public void rect() {
		removeMouseListener(listener);
		removeMouseMotionListener(motion);
		MyMouseListener ml = new MyMouseListener();
		addMouseListener(ml);
		addMouseMotionListener(ml);
	}

	/** 
	 * Changes all MouseListeners to either the 
	 * Fill feature or the drawing feature
	 * 
	 * @param fill
	 */
	public void setFill(boolean fill){
		if (fill){
			removeMouseListener(listener);
			removeMouseMotionListener(motion);
			fml = new FillMouseListener();
			addMouseListener(fml);
			addMouseMotionListener(fml);
		}
		else if (!fill){
			removeMouseListener(fml);
			removeMouseMotionListener(fml);
			defaultListener();
		}
	}

	/** 
	 * Replicates a common paint-bucket style fill function,
	 * uses a looped LinkedList as a glorified recursive method
	 * eliminating all non-target pixels from evaluation/change
	 * 
	 * @param point
	 * @param target
	 * @param replacement
	 */
	private void floodFill(Point point, Color target, Color replacement) {
        int width = img.getWidth() - 1;
        int height = img.getHeight() - 1;
        int targetRGB = target.getRGB();
        int replacementRGB = replacement.getRGB();

        Queue<Point> queue = new LinkedList<Point>();
        queue.add( point );

        while (!queue.isEmpty()) {
            Point p = queue.remove();
            int imageRGB = img.getRGB(p.x, p.y);

            if (imageRGB != targetRGB) continue;

            //  Update the image and check surrounding pixels

            img.setRGB(p.x, p.y, replacementRGB);

            if (p.x > 0) queue.add( new Point(p.x - 1, p.y) );
            if (p.x +1 < width) queue.add( new Point(p.x + 1, p.y) );
            if (p.y > 0) queue.add( new Point(p.x, p.y - 1) );
            if (p.y +1 < height) queue.add( new Point(p.x, p.y + 1) );
        }
		repaint();
	}

	/** 
	 * Sets the img instance variable
	 * Also capable of creating blank image if param is set to null
	 * 
	 * @param image
	 */
	private void setImage(BufferedImage image) {
		if (image == null) {
			try {
				image = (BufferedImage) createImage(getSize().width, getSize().height);
				g = image.createGraphics();
				g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
						RenderingHints.VALUE_ANTIALIAS_ON);
				clear();
				img = image;
			} catch(NullPointerException e) {
			}
		} 
		else {
			g = (Graphics2D) image.getGraphics();
			g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
					RenderingHints.VALUE_ANTIALIAS_ON);
			g.setPaint(Color.black);
			img = image;
			repaint();
		}
	}
	
	/** 
	 * Sets background and foreground using setImage
	 * 
	 * @param img
	 */
	public void setBackground(BufferedImage img) {
		background = copyImage(img);
		setImage(null);
	}

	/** 
	 * Copies and returns input image as new object
	 * 
	 * @param img
	 * @return BufferedImage - copy of input image
	 */
	private BufferedImage copyImage(BufferedImage img) {
		BufferedImage copyOfImage = new BufferedImage(getSize().width,
				getSize().height, BufferedImage.TYPE_INT_RGB);
		Graphics g = copyOfImage.createGraphics();
		g.drawImage(img, 0, 0, getWidth(), getHeight(), null);
		return copyOfImage;
	}

	/** 
	 * Saves object (in this case, BufferedImage) to SizedStack for 
	 * undo and redo method purposes
	 * 
	 * @param img
	 */
	private void saveToStack(BufferedImage img) {
		undoStack.push(copyImage(img));
	}
	
	/** 
	 * Sets the thickness of line drawn
	 * 
	 * @param thick
	 */
	public void setThickness(int thick) {
		g.setStroke(new BasicStroke(thick));
	}

	/** 
	 * Set's instance variable chosCol 
	 * 
	 * @param col
	 */
	public void setCol(Color col) {
		chosCol = col;
	}

	/**
	 * Meaningless class, used for debugging purposes
	 * alongside rect()
	 */
	class MyMouseListener extends MouseInputAdapter
	{
		private Point startPoint;

		public void mousePressed(MouseEvent e)
		{
			startPoint = e.getPoint();
			shape = new Rectangle();
		}

		public void mouseDragged(MouseEvent e)
		{
			int x = Math.min(startPoint.x, e.getX());
			int y = Math.min(startPoint.y, e.getY());
			int width = Math.abs(startPoint.x - e.getX());
			int height = Math.abs(startPoint.y - e.getY());

			shape.setBounds(x, y, width, height);
			repaint();
		}

		public void mouseReleased(MouseEvent e)
		{
			//check if shape is null
			if (shape.width != 0 || shape.height != 0) {
				addRectangle(shape, e.getComponent().getForeground());
			}

			//return shape to null
			shape = null;
		}
	}

	/**
	 * Inner class that is used as the fill functions
	 * MouseListener and personal mousePressed() method
	 */
	class FillMouseListener extends MouseInputAdapter
	{
		private Point startPoint;

		public void mousePressed(MouseEvent e)
		{
			startPoint = e.getPoint();
			g = (Graphics2D) img.getGraphics();

			//floodFill call
			floodFill(startPoint, Color.WHITE, chosCol);
		}
	}
}