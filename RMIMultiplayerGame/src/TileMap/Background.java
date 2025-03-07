package TileMap;

import Main.GamePanel;

import java.awt.*;
import java.awt.image.*;
import javax.imageio.ImageIO;


/**
 * Deze methode verzorgt de achtergrond wanneer de speler een spel speelt
 * 
 * @param s een string
 * @param ms een schaal voor het bewegen van de achtergrond
 * 
 * @author Seppe Van Hees & Stijn Van Crombrugge
 */


public class Background {
	
	private BufferedImage image;
	
	private double x;
	private double y;
	private double dx;
	private double dy;
	
	private double moveScale;
	
	public Background(String s, double ms) {
		
		try {
			image = ImageIO.read(
				getClass().getResourceAsStream(s)
			);
			moveScale = ms;
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public void setPosition(double x, double y) {
		this.x = (x * moveScale) % GamePanel.WIDTH;
		this.y = (y * moveScale) % GamePanel.HEIGHT;
	}
	
	public void setVector(double dx, double dy) {
		this.dx = dx;
		this.dy = dy;
	}
	
	public void update() {
		x += dx;
		y += dy;
	}
	
	
	/**
	 * Deze methode verzorgt het tekenen van de achhtergrond
	 * 
	 * @param g de graphics benodigd voor het tekenen van de achtergrond
	 */
	
	public void draw(Graphics2D g) {
		
		g.drawImage(image, (int)x, (int)y, null);
		
		if(x < 0) {
			g.drawImage(
				image,
				(int)x + GamePanel.WIDTH,
				(int)y,
				null
			);
		}
		if(x > 0) {
			g.drawImage(
				image,
				(int)x - GamePanel.WIDTH,
				(int)y,
				null
			);
		}
	}
	
}







