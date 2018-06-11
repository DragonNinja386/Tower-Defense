package game;

import java.awt.Graphics2D;

import javax.swing.JLabel;

public class Tower {
	private TowerRange rangeVisual;
	private String projectile;
	private int diameter;
	private int speed;
	private int cooldown;
	
	private int cost;
	private String image;
	
	public Tower(String tower) {
		switch (tower) {
		case "Arrow":
			speed = 5;
			cooldown = 0;
			cost = 25;
			diameter = 15;
			image = "src\\assets\\tower_arrow.png";
			projectile = "src\\assets\\arrow.png";
			break;
		}
		
		
		rangeVisual = new TowerRange(diameter);
	}
	
	public void setCooldown() {
		cooldown = speed;
	}
	
	public int cooldown() {
		if (cooldown > 0) {
			cooldown--;
		}
		return cooldown;
	}
	
	public Projectile getProjectile(Enemy e) {
		Projectile temp = new Projectile(projectile, e);
		return temp;
	}
	
	public boolean getCooldown() {
		return cooldown == 0;
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
