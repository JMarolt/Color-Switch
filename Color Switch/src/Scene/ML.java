package Scene;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

public class ML extends MouseAdapter implements MouseMotionListener{

	public boolean mousePressed = false;
	public boolean mouseDragged = false;
	public int mouseButton = -1;
	public int x = -1, y = -1;
	public float dx = -1.0f, dy = -1.0f;
	
	@Override
	public void mouseClicked(MouseEvent mouseEvent) {
		
	}
	
	@Override
	public void mousePressed(MouseEvent mouseEvent) {
		this.mousePressed = true;
		this.mouseButton = mouseEvent.getButton();
	}
	
	@Override
	public void mouseReleased(MouseEvent mouseEvent) {
		this.mouseDragged = false;
		this.mousePressed = false;
		this.dx = 0;
		this.dy = 0;
	}
	
	@Override
	public void mouseMoved(MouseEvent mouseEvent) {
		this.x = mouseEvent.getX();
		this.y = mouseEvent.getY();
	}
	
	@Override
	public void mouseDragged(MouseEvent mouseEvent) {
		this.mouseDragged = true;
		this.dx = mouseEvent.getX() - this.x;
		this.dy = mouseEvent.getY() - this.y;
	}
	
}
