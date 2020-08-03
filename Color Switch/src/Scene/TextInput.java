package Scene;

import java.awt.Dimension;

import javax.swing.JTextField;

public class TextInput extends JTextField{

	private static final long serialVersionUID = 1L;
	private String attemptedText;
	
	public TextInput(int x, int y, int width, int height) {
		setText("");
		setBounds(x, y, width, height);
		setLocation(x, y);
		setPreferredSize(new Dimension(width, height));
	}
	
	public String getAttemptedText() {
		return attemptedText;
	}

	public void setAttemptedText(String attemptedText) {
		this.attemptedText = attemptedText;
	}
	
}
