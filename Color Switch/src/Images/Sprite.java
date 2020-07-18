package Images;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Sprite {

	private String fileName;
	private BufferedImage image;
	private int r, b, g;
	private int a;
	
	public Sprite(String fileName, int width, int height, int r, int g, int b, int a) {
		this.fileName = fileName;
		this.r = r;
		this.g = g;
		this.b = b;
		this.a = a;
		this.image = createImage(fileName);
		if(image.getWidth() != width || image.getHeight() != height) {
			this.image = resize(image, width, height);
		}
	}
	
	public BufferedImage createImage(String fileName) {
		try {
			image = ImageIO.read(new File(fileName));
			for(int i = 0; i < image.getWidth(); i++) {
				for(int k = 0; k < image.getHeight(); k++) {
					int p = image.getRGB(i, k);
					if(p != 0) {
						// get alpha 
				        int a = (p>>24) & 0xff; 
				  
				        // get red 
				        int r = (p>>16) & 0xff; 
				  
				        // get green 
				        int g = (p>>8) & 0xff; 
				  
				        // get blue 
				        int b = p & 0xff; 

				        a = this.a;
				        r = this.r; 
				        g = this.g; 
				        b = this.b; 
				  
				        //set the pixel value 
				        p = (a<<24) | (r<<16) | (g<<8) | b; 
				        image.setRGB(i, k, p);
					}
				}
			}
		}catch(IOException e) {
			e.printStackTrace();
		}
		return image;
	}
	
	public BufferedImage resize(BufferedImage image, int width, int height) {	
		Image tmp = image.getScaledInstance(width, height, Image.SCALE_FAST);
	    BufferedImage dimg = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
	    Graphics2D g2d = dimg.createGraphics();
	    g2d.drawImage(tmp, 0, 0, null);
	    g2d.dispose();
	    return dimg;	
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public BufferedImage getImage() {
		return image;
	}

	public void setImage(BufferedImage image) {
		this.image = image;
	}

	public int getR() {
		return r;
	}

	public void setR(int r) {
		this.r = r;
	}

	public int getB() {
		return b;
	}

	public void setB(int b) {
		this.b = b;
	}

	public int getG() {
		return g;
	}

	public void setG(int g) {
		this.g = g;
	}

	public int getA() {
		return a;
	}

	public void setA(int a) {
		this.a = a;
	}
	
}
