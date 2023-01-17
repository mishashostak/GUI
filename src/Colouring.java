import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class Colouring {

	private JComboBox<ImageIcon> colBu;
	private Canvas canvas;
	private ImageControl imgC = new ImageControl();
	private Color color = Color.WHITE;
	private JButton clearButton, saveButton, loadButton,
		saveAsButton, rectangle, pencil, undoButton, redoButton;
	private BufferedImage blueButton, greenButton, redButton,
		magentaButton, grayButton, orangeButton, yellowButton,
			pinkButton, cyanButton, lightGrayButton, colorPicker;
	private JFileChooser fileChooser;
	private File file;
	private int saveCounter = 0;
	private JLabel filenameBar, thicknessStat;
	private JSlider thicknessSlider;
	private int width, height;
	private ChangeListener thick = new ChangeListener() {
		public void stateChanged(ChangeEvent e) {
			thicknessStat.setText(String.format("%s",
					thicknessSlider.getValue()));
			canvas.setThickness(thicknessSlider.getValue());
		}
	};
	ActionListener listener = new ActionListener() {

		public void actionPerformed(ActionEvent event) {
			if (event.getSource() == clearButton) {
				canvas.clear();
			} else if (event.getSource() == blueButton) {
				canvas.blue();
			} else if (event.getSource() == greenButton) {
				canvas.green();
			} else if (event.getSource() == redButton) {
				canvas.red();
			} else if (event.getSource() == magentaButton) {
				canvas.magenta();
			} else if (event.getSource() == grayButton) {
				canvas.gray();
			} else if (event.getSource() == orangeButton) {
				canvas.orange();
			} else if (event.getSource() == yellowButton) {
				canvas.yellow();
			} else if (event.getSource() == pinkButton) {
				canvas.pink();
			} else if (event.getSource() == cyanButton) {
				canvas.cyan();
			} else if (event.getSource() == lightGrayButton) {
				canvas.lightGray();
			} else if (event.getSource() == undoButton) {
				canvas.undo();
			} else if (event.getSource() == redoButton) {
				canvas.redo();
			} else if (event.getSource() == rectangle) {
				canvas.rect();
			} else if (event.getSource() == pencil) {
				canvas.pencil();
			} else if (event.getSource() == colorPicker) {
				color = JColorChooser.showDialog(null, "Pick your color!",
						color);
				if (color == null)
					color = (Color.WHITE);
				canvas.picker(color);
			} else if (event.getSource() == saveButton) {
				if (saveCounter == 0) {
					fileChooser = new JFileChooser();
					if (fileChooser.showSaveDialog(saveButton) == JFileChooser.APPROVE_OPTION) {
						file = fileChooser.getSelectedFile();
						saveCounter = 1;
						filenameBar.setText(file.toString());
						canvas.save(file);
					}
				} else {
					filenameBar.setText(file.toString());
					canvas.save(file);
				}
			} else if (event.getSource() == saveAsButton) {
				saveCounter = 1;
				fileChooser = new JFileChooser();
				if (fileChooser.showSaveDialog(saveAsButton) == JFileChooser.APPROVE_OPTION) {
					file = fileChooser.getSelectedFile();
					filenameBar.setText(file.toString());
					canvas.save(file);
				}
			} else if (event.getSource() == loadButton) {
				fileChooser = new JFileChooser();
				if (fileChooser.showOpenDialog(loadButton) == JFileChooser.APPROVE_OPTION) {
					file = fileChooser.getSelectedFile();
					filenameBar.setText(file.toString());
					canvas.load(file);
				}
			}
		}
	};

	public Colouring(BufferedImage img, int width, int height) {
		setWH(width, height);
		canvas = new Canvas(img, width, height);
		openPaint();
	}
	
	/** 
	 * @param width
	 * @param height
	 */
	public void setWH(int width,int height){
		this.width = width;
		this.height = height;
	}

	public void openPaint() {
		for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
	        if ("Nimbus".equals(info.getName())) {
	            try {
					UIManager.setLookAndFeel(info.getClassName());
				} catch (ClassNotFoundException | InstantiationException
						| IllegalAccessException
						| UnsupportedLookAndFeelException e) {
					e.printStackTrace();
				}
	            break;
	        }
	    }
		JFrame frame = new JFrame("Colouring");
		Container container = frame.getContentPane();
		container.setLayout(new BorderLayout());

		container.add(canvas, BorderLayout.CENTER);

		JPanel panel = new JPanel();
		JPanel panel1 = new JPanel();
		Box box = Box.createVerticalBox();
		Box box1 = Box.createHorizontalBox();

		panel1.setLayout(new FlowLayout());

		pencil = new JButton(new ImageIcon(imgC.binImgs[0]));
		pencil.setPreferredSize(new Dimension(40, 40));
		pencil.addActionListener(listener);
		rectangle = new JButton(new ImageIcon(imgC.binImgs[1]));
		rectangle.setPreferredSize(new Dimension(40, 40));
		rectangle.addActionListener(listener);
		thicknessSlider = new JSlider(JSlider.HORIZONTAL, 0, 50, 1);
		thicknessSlider.setMajorTickSpacing(25);
		thicknessSlider.setPaintTicks(true);
		thicknessSlider.setPreferredSize(new Dimension(40, 40));
		thicknessSlider.addChangeListener(thick);
		undoButton = new JButton(new ImageIcon(imgC.binImgs[4]));
		undoButton.setPreferredSize(new Dimension(20, 20));
		undoButton.addActionListener(listener);
		redoButton = new JButton(new ImageIcon(imgC.binImgs[2]));
		redoButton.setPreferredSize(new Dimension(20, 20));
		redoButton.addActionListener(listener);

		blueButton = new BufferedImage(40,40,BufferedImage.TYPE_INT_RGB);
		Graphics2D gBlue = blueButton.createGraphics();
        gBlue.setColor(Color.BLUE);
        gBlue.fillRect(0,0,40,40);
        gBlue.dispose();
		greenButton = new BufferedImage(40,40,BufferedImage.TYPE_INT_RGB);
		Graphics2D gGreen = greenButton.createGraphics();
        gGreen.setColor(Color.GREEN);
        gGreen.fillRect(0,0,40,40);
        gGreen.dispose();
		redButton = new BufferedImage(40,40,BufferedImage.TYPE_INT_RGB);
		Graphics2D gRed = redButton.createGraphics();
        gRed.setColor(Color.RED);
        gRed.fillRect(0,0,40,40);
        gRed.dispose();
		magentaButton = new BufferedImage(40,40,BufferedImage.TYPE_INT_RGB);
		Graphics2D gMag = magentaButton.createGraphics();
        gMag.setColor(Color.MAGENTA);
        gMag.fillRect(0,0,40,40);
        gMag.dispose();
		grayButton = new BufferedImage(40,40,BufferedImage.TYPE_INT_RGB);
		Graphics2D gGray = grayButton.createGraphics();
        gGray.setColor(Color.GRAY);
        gGray.fillRect(0,0,40,40);
        gGray.dispose();
		orangeButton = new BufferedImage(40,40,BufferedImage.TYPE_INT_RGB);
		Graphics2D gOrng = orangeButton.createGraphics();
        gOrng.setColor(Color.ORANGE);
        gOrng.fillRect(0,0,40,40);
        gOrng.dispose();
		yellowButton = new BufferedImage(40,40,BufferedImage.TYPE_INT_RGB);
		Graphics2D gYel = yellowButton.createGraphics();
        gYel.setColor(Color.YELLOW);
        gYel.fillRect(0,0,40,40);
        gYel.dispose();
		pinkButton = new BufferedImage(40,40,BufferedImage.TYPE_INT_RGB);
		Graphics2D gPink = pinkButton.createGraphics();
        gPink.setColor(Color.PINK);
        gPink.fillRect(0,0,40,40);
        gPink.dispose();
		cyanButton = new BufferedImage(40,40,BufferedImage.TYPE_INT_RGB);
		Graphics2D gCyan = cyanButton.createGraphics();
        gCyan.setColor(Color.CYAN);
        gCyan.fillRect(0,0,40,40);
        gCyan.dispose();
		lightGrayButton = new BufferedImage(40,40,BufferedImage.TYPE_INT_RGB);
		Graphics2D gLG = lightGrayButton.createGraphics();
        gLG.setColor(Color.LIGHT_GRAY);
        gLG.fillRect(0,0,40,40);
        gLG.dispose();
		colorPicker = new BufferedImage(40,40,BufferedImage.TYPE_INT_RGB);
		Graphics2D gColPick = colorPicker.createGraphics();
        gColPick.drawImage((Image)imgC.binImgs[5],0,0,40,40,null);
        gColPick.dispose();


		saveButton = new JButton(new ImageIcon(imgC.binImgs[3]));
		saveButton.addActionListener(listener);
		saveAsButton = new JButton("Save As");
		saveAsButton.addActionListener(listener);
		loadButton = new JButton("Load");
		loadButton.addActionListener(listener);
		clearButton = new JButton("Clear");
		clearButton.addActionListener(listener);

		filenameBar = new JLabel("No file");
		thicknessStat = new JLabel("1");

		box.add(Box.createVerticalStrut(40));
		box1.add(thicknessSlider, BorderLayout.NORTH);
		box1.add(thicknessStat, BorderLayout.NORTH);
		box.add(box1, BorderLayout.NORTH);
		panel1.add(filenameBar, BorderLayout.SOUTH);
		box.add(Box.createVerticalStrut(20));
		box.add(undoButton, BorderLayout.NORTH);
		box.add(Box.createVerticalStrut(5));
		box.add(redoButton, BorderLayout.NORTH);
		/*box.add(Box.createVerticalStrut(5));
		box.add(pencil, BorderLayout.NORTH);
		box.add(Box.createVerticalStrut(5));
		box.add(rectangle, BorderLayout.NORTH);*/
		ImageIcon[] bArr = new ImageIcon[]{new ImageIcon(blueButton), new ImageIcon(greenButton), new ImageIcon(redButton),
			new ImageIcon(magentaButton), new ImageIcon(grayButton), new ImageIcon(orangeButton), new ImageIcon(yellowButton),
			new ImageIcon(pinkButton), new ImageIcon(cyanButton), new ImageIcon(lightGrayButton), new ImageIcon(colorPicker)};

		colBu = new JComboBox<ImageIcon>(bArr);


		panel.add(colBu);
		panel.add(saveButton);
		panel.add(saveAsButton);
		panel.add(loadButton);
		panel.add(clearButton);

		container.add(panel, BorderLayout.NORTH);
		container.add(panel1, BorderLayout.SOUTH);
		container.add(box, BorderLayout.WEST);

		frame.setVisible(true);

		frame.setSize(width+100,height+11);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}
}