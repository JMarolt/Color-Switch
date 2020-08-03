package Scene;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;

import Entity.CircleObstacle;
import Entity.GameObject;
import Entity.Player;
import Entity.Star;
import Images.Sprite;
import Miscellaneous.Camera;
import Miscellaneous.Constants;
import Miscellaneous.Vector2;

public class LevelScene extends Scene {

	private static LevelScene scene;
	private Player player;
	public static ArrayList<GameObject> gameObjects = new ArrayList<GameObject>();
	private static final String DATA_FILE = "src/Scene/saveLevel";
	@SuppressWarnings("unused")
	private Color[] colors = Sprite.colors;
	private boolean isRunning = false;
	private int score = 0;

	public LevelScene() {
		camera = new Camera(new Vector2(0.0f, 0.0f));
	}

	public static LevelScene getScene() {
		if (LevelScene.scene == null) {
			LevelScene.scene = new LevelScene();
		}

		return LevelScene.scene;
	}

	public static void delete() {
		LevelScene.scene = null;
	}

	private void playerInit() {
		String imgURL = "src/Images/player.png";
		int radius = Constants.PLAYER_RADIUS;
		int a = 255;
		player = new Player(Constants.SPAWN_POINT, new Sprite(imgURL, radius, radius, a, radius, true));
	}

	@Override
	public void init() {
		playerInit();
		player.init();
		for (int i = 0; i < gameObjects.size(); i++) {
			if (gameObjects.get(i) instanceof CircleObstacle) {
				gameObjects.get(i).setCanRotate(true);
				gameObjects.get(i).setRps(Math.PI / 256);
			}
		}
	}

	@Override
	public void update() {
		if (player != null) {
			player.update();
		}
		for (int i = 0; i < gameObjects.size(); i++) {
			gameObjects.get(i).update();
		}
		if (Window.KL.isKeyPressed(KeyEvent.VK_SPACE) || (Window.ML.mousePressed && Window.ML.mouseButton == 1)) {
			if (isRunning == false) {
				isRunning = true;
			}
			for (int i = 0; i < gameObjects.size(); i++) {
				gameObjects.get(i).jump();
				// player.jump();
			}
		}
		for (int i = 0; i < gameObjects.size(); i++) {
			if (collides(player, gameObjects.get(i))) {
				player.die();
			}
		}
		for (int i = 0; i < gameObjects.size(); i++) {
			if (player.getVec().getY() < camera.getVec().getY() + 500) {
				if (!gameObjects.get(i).isPlayer()) {
					gameObjects.get(i).getVec().setY(gameObjects.get(i).getVec().getY() + 10);
					camera.getVec().setY(
							camera.getVec().getY() - (10 + (camera.getVec().getY() + 500 - player.getVec().getY())));
				}
			}
		}
		if (player.getVec().getY() + 400 > camera.getVec().getY() + Constants.SCREEN_HEIGHT && isRunning) {
			player.die();
			//Window.profile.setScore(Window.profile.getScore() + this.score);
			this.score = 0;
		}
	}

	@Override
	public void draw(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		g2d.setColor(Constants.BACKGROUND_COLOR);
		g2d.fillRect(0, 0, Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT);
		if (player != null) {
			player.draw(g);
		}
		for (int i = 0; i < gameObjects.size(); i++) {
			gameObjects.get(i).draw(g);
		}
	}

	@SuppressWarnings("static-access")
	public boolean collides(Player p, GameObject go) {
		Color color = Color.black;
		if ((p.getVec().getY() < (go.getVec().getY() + go.getHeight()) && (p.getVec().getY()
				+ p.getHeight()) > (go.getVec().getY() + go.getHeight() - go.getSprite().getoHeight()))
				|| ((p.getVec().getY() < go.getVec().getY() + go.getSprite().getoHeight())
						&& p.getVec().getY() + p.getHeight() > go.getVec().getY())) {
			if (go instanceof CircleObstacle) {
				double rad = go.getRotationPos() % Math.PI;
				if (rad > Math.PI / 4 && rad < 3 * (Math.PI / 4)) {
					color = go.getSprite().colors[0];
				} else if (rad > 3 * (Math.PI / 4) && rad < 5 * (Math.PI / 4)) {
					color = go.getSprite().colors[3];
				} else if (rad > 5 * (Math.PI / 4) && rad < 7 * (Math.PI / 4)) {
					color = go.getSprite().colors[2];
				} else {
					color = go.getSprite().colors[1];
				}
			}
			if (go instanceof Star) {
				if ((p.getVec().getX() < (go.getVec().getX() + go.getWidth())
						|| (p.getVec().getX() + p.getWidth()) > (go.getVec().getX()))) {
					score++;
					gameObjects.remove(go);
				}
			}
			if (!p.getSprite().getColor().equals(color)) {
				return true;
			}
		} else {
			return false;
		}
		return false;
	}

	@SuppressWarnings("unchecked")
	public ArrayList<GameObject> load() {
		try (FileInputStream fis = new FileInputStream(new File(DATA_FILE));
				ObjectInputStream ois = new ObjectInputStream(fis)) {
			return (ArrayList<GameObject>) ois.readObject();
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return null;
		}
	}

}
