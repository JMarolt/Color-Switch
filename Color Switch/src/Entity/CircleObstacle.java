package Entity;

import Images.Sprite;
import Miscellaneous.Vector2;

public class CircleObstacle extends GameObject{

	public CircleObstacle(Vector2 vec, Sprite sprite) {
		super(vec, sprite);
		this.setPlayer(false);
		this.setWidth(300);
		this.setHeight(300);
	}
	
	public void test() {
		
	}
	
}
