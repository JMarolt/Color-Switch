package Entity;

import static Scene.Scene.camera;

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
	private double rotationPos = initialRotation;
	private final float MAX_VY = 50;
	public boolean isRunning = false;
	private boolean isPlayer;
	private boolean canRotate;

	public GameObject(Vector2 vec, Sprite sprite) {
		this.vec = vec;
		this.sprite = sprite;
	}

	public void update() {
		if (isRunning && !isPlayer) {
			gravity = 1;
			vy -= gravity;
			this.vec.setY(this.vec.getY() + vy);
			if (vy > MAX_VY) {
				vy = MAX_VY;
			}
			if (-vy < -MAX_VY) {
				vy = -MAX_VY;
			}
			if(this.canRotate) {
				rotate();
			}
		}
	}

	public void init() {

	}

	public void addObject(GameObject go) {
		objects.add(go);
	}

	public ArrayList<GameObject> getObjects() {
		return this.objects;
	}

	public void jump() {
		isRunning = true;
		vy = 12;
	}

	public void draw(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		g2d.rotate(initialRotation, vec.getX() + (width / 2) - 1, vec.getY() + (height/2) - 1);
		g2d.drawImage(sprite.getImage(), (int) vec.getX(), (int) (vec.getY() - camera.getVec().getY()), null);
		//g2d.rotate(rotationPos, vec.getX() + (width / 2) - 1, vec.getY() + (height/2) - 1);
//		if(initialRotation != 0) {
//			initialRotation = 0;
//		}
	}
	
	public void rotate() {
		rotationPos += (rps);
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

	public boolean isPlayer() {
		return isPlayer;
	}

	public void setPlayer(boolean isPlayer) {
		this.isPlayer = isPlayer;
	}

	public boolean isRunning() {
		return isRunning;
	}

	public void setRunning(boolean isRunning) {
		this.isRunning = isRunning;
	}

	public double getRotationPos() {
		return rotationPos;
	}

	public void setRotationPos(double rotationPos) {
		this.rotationPos = rotationPos;
	}

	public boolean isCanRotate() {
		return canRotate;
	}

	public void setCanRotate(boolean canRotate) {
		this.canRotate = canRotate;
	}

}
