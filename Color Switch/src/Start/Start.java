package Start;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import Scene.Window;

public class Start implements ActionListener{

	static Window window;
	
	public static void main(String[] args) {
		window = Window.getWindow();
		window.init();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		window.actionPerformed(e);
	}
	
}
