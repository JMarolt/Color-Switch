package Scene;

import java.awt.Graphics;

public class MainMenuScene extends Scene{

	private static MainMenuScene scene;
	
	public static MainMenuScene getScene() {
		if(MainMenuScene.scene == null) {
			MainMenuScene.scene = new MainMenuScene();
		}
		
		return MainMenuScene.scene;
	}
	
	@Override
	public void init() {
		
	}

	@Override
	public void update() {
		
	}

	@Override
	public void draw(Graphics g) {
		
	}

}
