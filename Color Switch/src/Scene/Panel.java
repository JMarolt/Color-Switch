package Scene;

import java.awt.Graphics;

import javax.swing.JPanel;

public class Panel extends JPanel{

	private static final long serialVersionUID = 1L;
	
	public Panel() {
		this.setLayout(null);
	}
	
	public void paintComponent(Graphics g) {
		if(Window.scene != null) {
			Window.scene.draw(g);
		}
	}

}
