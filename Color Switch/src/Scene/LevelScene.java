package Scene;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;

import Entity.Player;
import Images.Sprite;
import Miscellaneous.Constants;

public class LevelScene extends Scene{
	
	private static LevelScene scene;
	private Player player;
	private static Color[] colors = { new Color(50, 227, 242), // Blue
			new Color(255, 0, 129), // Pink
			new Color(246, 224, 6), // Yellow
			new Color(141, 11, 251) // Purple
	};
	
	public static LevelScene getScene() {
		if(LevelScene.scene == null) {
			LevelScene.scene = new LevelScene();
		}
		
		return LevelScene.scene;
	}
	
	public static Color setColor(int num) {
		switch (num) {
		case 0:
			return colors[0];
		case 1:
			return colors[1];
		case 2:
			return colors[2];
		case 3:
			return colors[3];
		default:
			System.out.println("null");
			return null;
		}
	}
	
	private void playerInit() {
		String imgURL = "src/Images/player.png";
		int radius = Constants.PLAYER_RADIUS;
		int rand = (int) Math.random()*4;
		int red = setColor(rand).getRed();
		int green = setColor(rand).getGreen();
		int blue = setColor(rand).getBlue();
		int a = 255;
		player = new Player(Constants.SPAWN_POINT, new Sprite(imgURL, radius, radius, red, green, blue, a));
	}

	@Override
	public void init() {
		playerInit();
		player.init();
	}

	@Override
	public void update() {
		if(player != null) {
			player.update();
		}
		if (Window.KL.isKeyPressed(KeyEvent.VK_SPACE) || (Window.ML.mousePressed && Window.ML.mouseButton == 1)) {
			player.jump();
		}
	}

	@Override
	public void draw(Graphics g) {
		g.setColor(Constants.BACKGROUND_COLOR);
		g.fillRect(0, 0, Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT);
		if(player != null) {
			player.draw(g);
		}
	}

}
