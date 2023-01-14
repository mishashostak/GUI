import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class Monoing {

	private MonoCanvas canvas;
	private ImageControl imgC = new ImageControl();
	private JButton clearButton, saveButton, rectangle, pencil, undoButton, redoButton;
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
			} else if (event.getSource() == undoButton) {
				canvas.undo();
			} else if (event.getSource() == redoButton) {
				canvas.redo();
			} else if (event.getSource() == rectangle) {
				canvas.rect();
			} else if (event.getSource() == pencil) {
				canvas.pencil();
			} else if (event.getSource() == saveButton) {
				canvas.save();
			}
		}
	};
	
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
		JFrame frame = new JFrame("Drawing");
		Container container = frame.getContentPane();
		container.setLayout(new BorderLayout());
		canvas = new MonoCanvas();

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
		

		panel.add(saveButton);
		panel.add(clearButton);

		container.add(panel, BorderLayout.NORTH);
		container.add(panel1, BorderLayout.SOUTH);
		container.add(box, BorderLayout.WEST);

		frame.setSize(width+100,height+11);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
}