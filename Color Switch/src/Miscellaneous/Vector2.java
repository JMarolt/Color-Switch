package Miscellaneous;

import java.io.Serializable;

public class Vector2 implements Serializable{

	private static final long serialVersionUID = 1L;
	private float x, y;
	
	public Vector2(float x, float y) {
		this.x = x;
		this.y = y;
	}

	public float getX() {
		return x;
	}

	public void setX(float x) {
		this.x = x;
	}

	public float getY() {
		return y;
	}

	public void setY(float y) {
		this.y = y;
	}
	
	public float length() {
		return (float)Math.sqrt((x * x) + (y * y));
	}
	
	public String toString() {
		return "Vector2<" + this.x + ", " + this.y + ">";
	}
	
}
