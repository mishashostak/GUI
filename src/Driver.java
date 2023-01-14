import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

public class Driver {
    /**
	 * @param args
	 */
	public static void main(String[] args) {
        String[] bs = {"Open File", "Create"};
        int reply = JOptionPane.showOptionDialog(null,
            "Would you like to open or create an image",
                "", JOptionPane.DEFAULT_OPTION,
                    JOptionPane.QUESTION_MESSAGE, null, bs, null);
        if (reply == 0) {
            JFileChooser fileChooser= new JFileChooser();
            fileChooser.setCurrentDirectory(null);
            int response = fileChooser.showSaveDialog(null);

            if(response == JFileChooser.APPROVE_OPTION) {
                File file = new File(fileChooser.getSelectedFile().getAbsolutePath());
                new ImageControl(file);
            }
        } else if (reply == 1) {
            new InputWH();
        }
	}
}
