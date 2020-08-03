package Entity;

import java.awt.Graphics;

import Images.Sprite;
import Miscellaneous.Vector2;

public class Player extends GameObject{

	public Player(Vector2 vec, Sprite sprite) {
		super(vec, sprite);
		this.setPlayer(true);
		this.setCanRotate(false);
	}
	
	public void jump() {
		super.jump();
	}
	
	public void update() {
		super.update();
	}
	
	public void init() {
		super.init();
	}
	
	public void draw(Graphics g) {
		super.draw(g);
	}
	
	public void die() {
		System.out.println("dead");
	}

}
