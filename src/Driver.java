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
            return;
        } else {
            return;
        }
	}
}
