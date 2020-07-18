package Entity;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;

import Images.Sprite;
import Miscellaneous.Vector2;

public class GameObject {

	private Vector2 vec;
	private Sprite sprite;
	private int width;
	private int height;
	private ArrayList<GameObject> objects = new ArrayList<GameObject>();
	private float gravity;
	private float vy;
	private double initialRotation;
	private double rps;
	private final float MAX_VY = 50;
	public static boolean started = false;

	public GameObject(Vector2 vec, Sprite sprite) {
		this.vec = vec;
		this.sprite = sprite;
	}

	public void update() {
		if (started) {
			gravity = 1;
			vy -= gravity;
			this.vec.setY(this.vec.getY() - vy);
			if (vy > MAX_VY) {
				vy = MAX_VY;
			}
			if (-vy < -MAX_VY) {
				vy = -MAX_VY;
			}
		}
	}

	public void init() {

	}

	public void removeObject(GameObject me) {
		for (int i = 0; i < objects.size(); i++) {
			if (me.equals(objects.get(i))) {
				objects.remove(i);
				break;
			}
		}
		me = null;
	}

	public void addObject(GameObject go) {
		objects.add(go);
	}

//	public void clone() {
//		objects.add(new GameObject(this.vec, this.sprite));
//	}

	public ArrayList<GameObject> getObjects() {
		return this.objects;
	}

	public void jump() {
		started = true;
		vy = 15;
	}

	public void draw(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		g2d.rotate(initialRotation, vec.getX() + (width/2), vec.getY() + (height/2));
		g2d.drawImage(sprite.getImage(), (int) vec.getX(), (int) vec.getY(), null);
	}

	public float getGravity() {
		return gravity;
	}

	public void setGravity(float gravity) {
		this.gravity = gravity;
	}

	public float getVy() {
		return vy;
	}

	public void setVy(float vy) {
		this.vy = vy;
	}

	public void setObjects(ArrayList<GameObject> objects) {
		this.objects = objects;
	}

	public Vector2 getVec() {
		return vec;
	}

	public void setVec(Vector2 vec) {
		this.vec = vec;
	}

	public Sprite getSprite() {
		return sprite;
	}

	public void setSprite(Sprite sprite) {
		this.sprite = sprite;
	}

	public double getInitialRotation() {
		return initialRotation;
	}

	public void setInitialRotation(double initialRotation) {
		this.initialRotation = initialRotation;
	}

	public double getRps() {
		return rps;
	}

	public void setRps(double rps) {
		this.rps = rps;
	}

	public float getMAX_VY() {
		return MAX_VY;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

}
