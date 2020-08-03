package Scene;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.Timer;

import Miscellaneous.Constants;
import Miscellaneous.Profile;

public class Window extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;

	private Timer timer;
	public static Panel panel;
	public static KL KL = new KL();
	public static ML ML = new ML();
	public static Window window = null;
	public static Scene scene = null;
	public static TextInput userInput;
	public static TextInput passInput;
	public static Button signUp;
	public static Button logIn;
	
	private boolean loggedIn = false;
	
	public static Profile profile;

	public Window() {
		panel = new Panel();
		initializeOtherComponents();
		this.setIconImage(new ImageIcon("src/Images/colorSwitchFrontImage.png").getImage());
		this.setTitle(Constants.TITLE);
		this.setPreferredSize(new Dimension(Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT));
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.addKeyListener(Window.KL);
		this.addMouseListener(Window.ML);
		this.setFocusable(true);
		//this.setUndecorated(true);
		this.setResizable(false);
		panel.add(userInput);
		panel.add(passInput);
		panel.add(signUp);
		panel.add(logIn);
		this.add(panel);
		this.pack();
		this.setVisible(true);
		timer = new Timer(1000 / 60, this);
		timer.start();
	}
	
	public void initializeOtherComponents() {
		userInput = new TextInput((Constants.SCREEN_WIDTH/2) - 100, 500, 200, 30);
		passInput = new TextInput((Constants.SCREEN_WIDTH/2) - 100, 550, 200, 30);
		signUp = new Button("Sign Up", (Constants.SCREEN_WIDTH/2) - 90, 600, 80, 80);
		signUp.setFocusable(false);
		signUp.addActionListener(e -> {
			userInput.setAttemptedText(userInput.getText());
			passInput.setAttemptedText(passInput.getText());
			LogInScene.isSigningUp = true;
			if(userInput.getText().length() > 0 && passInput.getText().length() > 0) {
				LogInScene.setClicked(true);
				loggedIn = true;
			}
		});
		logIn = new Button("Log In", (Constants.SCREEN_WIDTH/2) + 10, 600, 80, 80);
		logIn.setFocusable(false);
		logIn.addActionListener(e -> {
			userInput.setAttemptedText(userInput.getText());
			passInput.setAttemptedText(passInput.getText());
			LogInScene.isSigningUp = false;
			LogInScene.setClicked(true);
		});
		userInput.addActionListener(this);
		passInput.addActionListener(this);
		userInput.setFocusable(true);
		passInput.setFocusable(true);
		userInput.isEditable();
		passInput.isEditable();
	}

	public static Window getWindow() {
		if (Window.window == null) {
			Window.window = new Window();
		}

		return Window.window;
	}

	public void setScene(int scene) {
		EditScene.delete();
		LevelScene.delete();
		switch (scene) {
		case 0:
			Window.scene = MainMenuScene.getScene();
			Window.scene.init();
			LogInScene.deleteScene(panel);
			break;
		case 1:
			Window.scene = LevelScene.getScene();
			Window.scene.init();
			break;
		case 2:
			LogInScene.deleteScene(panel);
			Window.scene = EditScene.getScene();
			Window.scene.init();
			break;
		case 3:
			Window.scene = LogInScene.getScene();
			Window.scene.init();
			break;
		default:
			System.out.println("Unknown Scene");
			Window.scene = null;
		}
	}
	
	public void init() {
		if(!loggedIn) {
			setScene(3);
		}else {
			setScene(2);
		}
	}

	public void update() {
		if(Window.scene != null) {
			Window.scene.update();
		}
		if (KL.isKeyPressed(KeyEvent.VK_CONTROL) && KL.isKeyPressed(KeyEvent.VK_8)) {
			Window.profile.save(Window.profile);
			System.exit(-1);
		}
		if(KL.isKeyPressed(KeyEvent.VK_G)) {
			this.dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
		}
		if(KL.isKeyPressed(KeyEvent.VK_CONTROL) && KL.isKeyPressed(KeyEvent.VK_F)) {
			LevelScene.gameObjects = EditScene.gameObjects;
			setScene(1);
		}
		if(KL.isKeyPressed(KeyEvent.VK_CONTROL) && KL.isKeyPressed(KeyEvent.VK_R)) {
			setScene(2);
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Window.panel.repaint();
		update();
	}

}
