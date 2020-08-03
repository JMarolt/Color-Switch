package Scene;

import java.awt.Graphics;
import java.awt.MouseInfo;
import java.awt.event.KeyEvent;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import Entity.CircleObstacle;
import Entity.GameObject;
import Entity.Player;
import Entity.Star;
import Images.Sprite;
import Miscellaneous.Camera;
import Miscellaneous.Constants;
import Miscellaneous.Grid;
import Miscellaneous.Vector2;

public class EditScene extends Scene {

	public static ArrayList<GameObject> gameObjects = new ArrayList<GameObject>();
	private Grid grid = new Grid(Constants.GRID_LENGTH);
	private static final String DATA_FILE = "src/Scene/saveLevel";
	private Player player;
	private boolean isRunning = true;
	private boolean movedUp = false;
	private boolean movedDown = false;

	private static EditScene scene;

	public EditScene() {
		camera = new Camera(new Vector2(0.0f, 0.0f));
	}

	public static EditScene getScene() {
		if (EditScene.scene == null) {
			EditScene.scene = new EditScene();
		}

		return EditScene.scene;
	}

	public static void delete() {
		EditScene.scene = null;
	}

	public void addObject(GameObject go) {
		gameObjects.add(go);
	}

	public void removeObject(GameObject go) {
		gameObjects.remove(go);
	}

	public void playerInit() {
		String imgURL = "src/Images/player.png";
		int radius = Constants.PLAYER_RADIUS;
		int a = 255;
		player = new Player(Constants.SPAWN_POINT, new Sprite(imgURL, radius, radius, a, radius, true));
	}

	public void undo() {
		if (gameObjects.size() > 0) {
			gameObjects.remove(gameObjects.size() - 1);
		}
	}

	public void addCameraY() {
		for (int i = 0; i < gameObjects.size(); i++) {
			gameObjects.get(i).getVec().setY(gameObjects.get(i).getVec().getY() + Constants.GRID_LENGTH);
		}
	}

	public void subtractCameraY() {
		for (int i = 0; i < gameObjects.size(); i++) {
			gameObjects.get(i).getVec().setY(gameObjects.get(i).getVec().getY() - Constants.GRID_LENGTH);
		}
	}

	public void checkDuplicates(ArrayList<GameObject> gameObjects) {
		for (int i = 0; i < gameObjects.size(); i++) {
			for (int k = i + 1; k < gameObjects.size(); k++) {
				if (gameObjects.get(i).getVec().getX() == gameObjects.get(k).getVec().getX()) {
					if (gameObjects.get(i).getVec().getY() == gameObjects.get(k).getVec().getY()) {
						if (gameObjects.get(i).getWidth() == gameObjects.get(k).getWidth()) {
							if (gameObjects.get(i).getHeight() == gameObjects.get(k).getHeight()) {
								gameObjects.remove(i);
							}
						}
					}
				}
			}
		}
	}

	@Override
	public void init() {
		playerInit();
		player.init();
		addObject(player);
	}

	@Override
	public void update() {
		int x = MouseInfo.getPointerInfo().getLocation().x;
		int y = (int) (MouseInfo.getPointerInfo().getLocation().y + camera.getVec().getY());
		int newx = x / grid.getLength();
		int newy = y / grid.getLength();
		if (Window.ML.mousePressed && Window.ML.mouseButton == 1) {
			addObject(new CircleObstacle(
					new Vector2((newx * grid.getLength()) - (300 / 2) + (grid.getLength() / 2),
							(newy * grid.getLength()) - (300 / 2) - (grid.getLength() / 2)),
					new Sprite("src/Images/circleob.png", 300, 300, 255, 29, true)));
		}
		if (Window.KL.isKeyPressed(KeyEvent.VK_BACK_SPACE)) {
			undo();
		}
		if (Window.KL.isKeyPressed(KeyEvent.VK_T)) {
			//save(gameObjects);
		}
		if (Window.KL.isKeyPressed(KeyEvent.VK_Q) && Window.KL.isKeyPressed(KeyEvent.VK_CONTROL)) {
			//gameObjects = load().getGo();
		}
		if (Window.KL.isKeyPressed(KeyEvent.VK_W)) {
			movedUp = true;
			camera.getVec().setY(camera.getVec().getY() - Constants.GRID_LENGTH);
		}
		if (Window.KL.isKeyPressed(KeyEvent.VK_S)) {
			movedDown = true;
			camera.getVec().setY(camera.getVec().getY() + Constants.GRID_LENGTH);
		}
		if (Window.KL.isKeyPressed(KeyEvent.VK_8)) {
			gameObjects.get(gameObjects.size() - 1)
					.setInitialRotation(gameObjects.get(gameObjects.size() - 1).getInitialRotation() + (Math.PI / 2));
		}
		if (Window.KL.isKeyPressed(KeyEvent.VK_9)) {
			gameObjects.get(gameObjects.size() - 1)
					.setInitialRotation(gameObjects.get(gameObjects.size() - 1).getInitialRotation() - (Math.PI / 2));
		}
		if (movedUp) {
			addCameraY();
			movedUp = false;
		}
		if (movedDown) {
			subtractCameraY();
			movedDown = false;
		}
		if (Window.KL.isKeyPressed(KeyEvent.VK_1)) {
			addObject(new Star(
					new Vector2((newx * grid.getLength()) - (50 / 2) + (grid.getLength() / 2),
							(newy * grid.getLength()) - (50 / 2) - (grid.getLength() / 2)),
					new Sprite("src/Images/star.png", 50, 50, 255, 50, false)));
		}
		checkDuplicates(gameObjects);
	}

	@Override
	public void draw(Graphics g) {
		g.setColor(Constants.BACKGROUND_COLOR);
		g.fillRect(0, 0, Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT);
		grid.draw(g);
		if (player != null) {
			player.draw(g);
		}
		for (int i = 0; i < gameObjects.size(); i++) {
			gameObjects.get(i).draw(g);
		}
	}

//	private static void save(Data data) {
//		File file = new File(DATA_FILE);
//		if (!file.exists()) {
//			try {
//				File directory = new File(file.getParent());
//				if (!directory.exists()) {
//					directory.mkdirs();
//				}
//				file.createNewFile();
//			} catch (IOException e) {
//				System.out.println("Excepton Occured: " + e.toString());
//			}
//		}
//
//		try {
//			// Convenience class for writing character files
//			FileWriter writer;
//			writer = new FileWriter(file.getAbsoluteFile(), true);
//
//			// Writes text to a character-output stream
//			BufferedWriter bufferWriter = new BufferedWriter(writer);
//			bufferWriter.write(data.toString());
//			bufferWriter.close();
//
//			System.out.println("Level Saved");
//		} catch (IOException e) {
//			System.out.println("Level could not be saved: " + e.toString());
//		}
//	}
	
//	private static void save(ArrayList<GameObject> go) {
//		try {
//			FileOutputStream f = new FileOutputStream(new File(DATA_FILE));
//			ObjectOutputStream o = new ObjectOutputStream(f);
//			o.writeObject(go);
//			
//			for (int i = 0; i < gameObjects.size(); i++) {
//				File file = new File(gameObjects.get(i).getSprite().getFileName());
//				ImageIO.write(gameObjects.get(i).getSprite().getImage(), "png", file);
//			}
//
//			o.close();
//			f.close();
//		}catch(Exception e) {
//			e.printStackTrace();
//		}
//	}

//	@SuppressWarnings("unchecked")
//	public static ArrayList<GameObject> load() {
//		File file = new File(DATA_FILE);
//		if (!file.exists())
//			System.out.println("File does not exist");
//		try {
//			FileInputStream fis = new FileInputStream(file);
//			ObjectInputStream ois = new ObjectInputStream(fis);
//			fis.reset();
//			ois.reset();
//			ArrayList<GameObject> temp = (ArrayList<GameObject>) ois.readObject();
//			temp.toString();
//			ois.close();
//			fis.close();
//			System.out.println("Successfully loaded level");
//			return temp;
//
//		} catch (Exception e) {
//			System.out.println("Unable to load file");
//			return null;
//		}
//	}

	public boolean isRunning() {
		return isRunning;
	}

	public void setRunning(boolean isRunning) {
		this.isRunning = isRunning;
	}

}
