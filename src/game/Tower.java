package game;

import java.awt.Graphics2D;

import javax.swing.JLabel;

public class Tower {
	private TowerRange rangeVisual;
	private Projectile projectile;
	private int diameter;
	
	private int cost;
	private String image;
	
	public Tower(String tower) {
		switch (tower) {
		case "Arrow":
			diameter = 5;
			image = "src\\assets\\tower_arrow.png";
			projectile = new Projectile("src\\assets\\arrow.png");
			break;
		}
		
		
		rangeVisual = new TowerRange(diameter);
		
		cost = 25;
	}
	
	public int getRadius() {
		return diameter * 25;
	}
	
	public TowerRange getRange() {
		return rangeVisual;
	}
	
	public int getCost() {
		return cost;
	}
	
	public String getImagePath() {
		return image;
	}
}
