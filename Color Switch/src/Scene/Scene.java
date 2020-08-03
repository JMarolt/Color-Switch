package Scene;

import java.awt.Graphics;

import Miscellaneous.Camera;

public abstract class Scene {

	public static Camera camera;
	
	public Scene() {}
	
	public abstract void init();
	public abstract void update();
	public abstract void draw(Graphics g);
	
}
