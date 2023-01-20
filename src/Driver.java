import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

/**
 * Simple Main class that begins program
 * 
 * @author Misha Shostak
 * 
 * @version 1/19/2023
 */
public class Driver {
    /**
     * typical main method
     * 
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
                new ImageControl(fileChooser.getSelectedFile().getAbsolutePath());
                
            }
        } else if (reply == 1) {
            new InputWH();
        }
	}
}
