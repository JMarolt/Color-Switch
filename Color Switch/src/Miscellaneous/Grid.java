package Miscellaneous;

import java.awt.Color;
import java.awt.Graphics;

public class Grid {

	private int length;
	
	public Grid(int length) {
		this.length = length;
	}
	
	public void draw(Graphics g) {
		g.setColor(Color.white);
		for(int i = 0; i < 540; i += length) {
			for(int k = 0; k < 960; k+= length) {
				g.drawRect(i, k, length, length);
			}
		}
	}

	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}
	
}
