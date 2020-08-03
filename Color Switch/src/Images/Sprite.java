package Images;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Sprite{

	private String fileName;
	private BufferedImage image;
	private int a;
	public static Color[] colors = { new Color(50, 227, 242), // Blue
			new Color(255, 0, 129), // Pink
			new Color(246, 224, 6), // Yellow
			new Color(141, 11, 251) // Purple
	};
	
	private int oHeight;
	private int r, g, b;
	private boolean customColor;
	
	final Color player = playerColor();

	public Sprite(String fileName, int width, int height, int a, int oHeight, boolean customColor) {
		this.fileName = fileName;
		this.a = a;
		this.customColor = customColor;
		this.oHeight = oHeight;
		this.image = createImage(fileName);
		if (image.getWidth() != width || image.getHeight() != height) {
			this.image = resize(image, width, height);
		}
	}

	public BufferedImage createImage(String fileName) {
		try {
			image = ImageIO.read(new File(fileName));
			for (int i = 0; i < image.getWidth(); i++) {
				for (int k = 0; k < image.getHeight(); k++) {
					Color c = new Color(image.getRGB(i, k));
					int p = (int) (image.getRGB(i, k));
					if (p != 0 && this.customColor) {
						int a = this.a;
						int b = 0;
						int g = 0;
						int r = 0;
						if (c.getBlue() > 150) {
							r = colors[0].getRed();
							g = colors[0].getGreen();
							b = colors[0].getBlue();
						} else if (c.getRed() > 150) {
							r = colors[1].getRed();
							g = colors[1].getGreen();
							b = colors[1].getBlue();
						} else if (c.getGreen() > 100) {
							r = colors[2].getRed();
							g = colors[2].getGreen();
							b = colors[2].getBlue();
						} else {
							r = colors[3].getRed();
							g = colors[3].getGreen();
							b = colors[3].getBlue();
						}
						if(c.getBlue() > 200 && c.getRed() > 200 && c.getGreen() > 200) {
							r = player.getRed();
							g = player.getGreen();
							b = player.getBlue();
						}
						this.r = r;
						this.g = g;
						this.b = b;

						// set the pixel value
						p = (a << 24) | (r << 16) | (g << 8) | b;
						image.setRGB(i, k, p);
					}
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return image;
	}

	public BufferedImage resize(BufferedImage image, int width, int height) {
		Image tmp = image.getScaledInstance(width, height, Image.SCALE_SMOOTH);
		BufferedImage dimg = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g2d = dimg.createGraphics();
		g2d.drawImage(tmp, 0, 0, null);
		g2d.dispose();
		return dimg;
	}
	
	public Color setColor(int num) {
		switch (num) {
		case 0:
			return colors[0];
		case 1:
			return colors[1];
		case 2:
			return colors[2];
		case 3:
			return colors[3];
		default:
			System.out.println("null");
			return null;
		}
	}
	
	public Color playerColor() {
		int rand = (int)(Math.random()*4);
		switch (rand) {
		case 0:
			return colors[0];
		case 1:
			return colors[1];
		case 2:
			return colors[2];
		case 3:
			return colors[3];
		default:
			System.out.println("That wasn't supposed to happen..");
			return null;
		}
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

	public int getA() {
		return a;
	}

	public void setA(int a) {
		this.a = a;
	}

	public int getoHeight() {
		return oHeight;
	}

	public void setoHeight(int oHeight) {
		this.oHeight = oHeight;
	}

	public int getR() {
		return r;
	}

	public void setR(int r) {
		this.r = r;
	}

	public int getG() {
		return g;
	}

	public void setG(int g) {
		this.g = g;
	}

	public int getB() {
		return b;
	}

	public void setB(int b) {
		this.b = b;
	}
	
	public Color getColor() {
		return new Color(getR(), getG(), getB());
	}

	public boolean isCustomColor() {
		return customColor;
	}

	public void setCustomColor(boolean customColor) {
		this.customColor = customColor;
	}

}
