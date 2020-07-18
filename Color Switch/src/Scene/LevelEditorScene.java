package Scene;

import java.awt.Graphics;
import java.awt.MouseInfo;
import java.util.ArrayList;

import Entity.GameObject;
import Images.Sprite;
import Miscellaneous.Grid;
import Miscellaneous.Vector2;

public class LevelEditorScene extends Scene{
	
	private ArrayList<GameObject> gameObjects = new ArrayList<GameObject>();
	private Grid grid;
	
	private static LevelEditorScene scene;
	
	public static LevelEditorScene getScene() {
		if(LevelEditorScene.scene == null) {
			LevelEditorScene.scene = new LevelEditorScene();
		}
		
		return LevelEditorScene.scene;
	}
	
	public static void delete() {
		LevelEditorScene.scene = null;
	}
	
	public void addObject(GameObject go) {
		gameObjects.add(go);
	}
	
	@Override
	public void init() {
		grid = new Grid(20);
	}

	@Override
	public void update() {
		if(Window.ML.mousePressed && Window.ML.mouseButton == 1) {
			int x = MouseInfo.getPointerInfo().getLocation().x;
			int y = MouseInfo.getPointerInfo().getLocation().y;
			int newx = x/grid.getLength();
			int newy = y/grid.getLength();
			addObject(new GameObject(new Vector2(newx * grid.getLength() - (186 / 2), newy * grid.getLength() - (60 / 2)), new Sprite("src/Images/obstacle1.png", 186, 60, 255, 255, 255, 255)));
		}
	}

	@Override
	public void draw(Graphics g) {
		for(int i = 0; i < gameObjects.size(); i++) {
			gameObjects.get(i).draw(g);
		}
		grid.draw(g);
	}

}
