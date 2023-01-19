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


public class Canvas extends JComponent {
	private int X1, Y1, X2, Y2;
	private Graphics2D g;
	private BufferedImage img, background, undoTemp, redoTemp;
	private final SizedStack<Image> undoStack = new SizedStack<>(12);
	private final SizedStack<Image> redoStack = new SizedStack<>(12);
	private Rectangle shape;
	private MouseMotionListener motion;
	private MouseListener listener;
	protected boolean fill = false;

	
	/** 
	 * @param file
	 */
	public void save(File file) {
		try {
			ImageIO.write((RenderedImage) img, "PNG", file);
		} catch (IOException ex) {
		}
	}

	
	/** 
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

	public Canvas(BufferedImage img, int width, int height) {
		setSize(width,height);
		setBackground(img);
		defaultListener();
	}

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

	public void red() {
		g.setPaint(Color.red);
	}

	public void black() {
		g.setPaint(Color.black);
	}

	public void magenta() {
		g.setPaint(Color.magenta);
	}

	public void green() {
		g.setPaint(Color.green);
	}

	public void blue() {
		g.setPaint(Color.blue);
	}

	public void gray() {
		g.setPaint(Color.GRAY);
	}

	public void orange() {
		g.setPaint(Color.ORANGE);
	}

	public void yellow() {
		g.setPaint(Color.YELLOW);
	}

	public void pink() {
		g.setPaint(Color.PINK);
	}

	public void cyan() {
		g.setPaint(Color.CYAN);
	}

	public void lightGray() {
		g.setPaint(Color.lightGray);
	}

	
	/** 
	 * @param color
	 */
	public void picker(Color color) {
		g.setPaint(color);
	}

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

	public void undo() {
		if (undoStack.size() > 0) {
			undoTemp = (BufferedImage) undoStack.pop();
			redoStack.push(img);
			setImage(undoTemp);
		}
	}

	public void redo() {
		if (redoStack.size() > 0) {
			redoTemp = (BufferedImage) redoStack.pop();
			undoStack.push(img);
			setImage(redoTemp);
		}
	}

	public void pencil() {
		removeMouseListener(listener);
		removeMouseMotionListener(motion);
		defaultListener();
		
	}

	public void rect() {
		removeMouseListener(listener);
		removeMouseMotionListener(motion);
		MyMouseListener ml = new MyMouseListener();
		addMouseListener(ml);
		addMouseMotionListener(ml);
	}

	public void setFill(boolean fill){
		this.fill = fill;
	}

	private void floodFill(BufferedImage img, Point point, Color target, Color replacement) {
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
		Graphics2D g2d = (Graphics2D) this.img.getGraphics();
		g2d.setColor(replacement);
		this.img = copyImage(img);
		repaint();
	}

	/** 
	 * @param img
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
	 * @param img
	 */
	public void setBackground(BufferedImage img) {
		background = copyImage(img);
		setImage(null);
	}

	
	/** 
	 * @param img
	 * @return BufferedImage
	 */
	private BufferedImage copyImage(BufferedImage img) {
		BufferedImage copyOfImage = new BufferedImage(getSize().width,
				getSize().height, BufferedImage.TYPE_INT_RGB);
		Graphics g = copyOfImage.createGraphics();
		g.drawImage(img, 0, 0, getWidth(), getHeight(), null);
		return copyOfImage;
	}

	
	/** 
	 * @param img
	 */
	private void saveToStack(BufferedImage img) {
		undoStack.push(copyImage(img));
	}

	
	/** 
	 * @param thick
	 */
	public void setThickness(int thick) {
		g.setStroke(new BasicStroke(thick));
	}

	class MyMouseListener extends MouseInputAdapter
	{
		private Point startPoint;

		public void mousePressed(MouseEvent e)
		{
			startPoint = e.getPoint();

			if(!fill) shape = new Rectangle();
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
			if (shape.width != 0 || shape.height != 0)
			{
				addRectangle(shape, e.getComponent().getForeground());
			}
			else if(fill) {
                floodFill(copyImage(img),startPoint, Color.WHITE, e.getComponent().getForeground());
			}

			shape = null;
		}
	}
}

	
