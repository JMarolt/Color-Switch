package Scene;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.Timer;

import Miscellaneous.Constants;

public class Window extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;

	private Timer timer;
	private Panel panel;
	public static KL KL = new KL();
	public static ML ML = new ML();
	public static Window window = null;
	public static Scene scene = null;

	public Window() {
		panel = new Panel();
		this.setIconImage(new ImageIcon("src/Images/colorSwitchFrontImage.png").getImage());
		this.setTitle(Constants.TITLE);
		this.setPreferredSize(new Dimension(Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT));
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.addKeyListener(Window.KL);
		this.addMouseListener(Window.ML);
		this.setFocusable(true);
		// this.setUndecorated(true);
		this.setResizable(false);
		this.add(panel);
		this.pack();
		this.setVisible(true);
		timer = new Timer(1000 / 60, this);
		timer.start();
	}

	public static Window getWindow() {
		if (Window.window == null) {
			Window.window = new Window();
		}

		return Window.window;
	}

	public void setScene(int scene) {
		switch (scene) {
		case 0:
			Window.scene = MainMenuScene.getScene();
			Window.scene.init();
			break;
		case 1:
			Window.scene = LevelScene.getScene();
			Window.scene.init();
			break;
		case 2:
			Window.scene = LevelEditorScene.getScene();
			Window.scene.init();
			break;
		default:
			System.out.println("Unknown Scene");
			Window.scene = null;
		}
	}
	
	public void init() {
		setScene(1);
	}

	public void update() {
		Window.scene.update();
		if (KL.isKeyPressed(KeyEvent.VK_8)) {
			System.exit(-1);
		}
		if(KL.isKeyPressed(KeyEvent.VK_CONTROL) && KL.isKeyPressed(KeyEvent.VK_F)) {
			setScene(2);
		}
		if(Window.scene == LevelEditorScene.getScene() && KL.isKeyPressed(KeyEvent.VK_K)) {
			//save
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		this.panel.repaint();
		update();
	}

}
