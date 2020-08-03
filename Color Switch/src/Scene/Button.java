package Scene;

import java.awt.Dimension;

import javax.swing.JButton;

public class Button extends JButton{

	private static final long serialVersionUID = 1L;
	
	public Button(String text, int x, int y, int width, int height) {
		setText(text);
		setBounds(x, y, width, height);
		setLocation(x, y);
		setPreferredSize(new Dimension(width, height));
	}

}
