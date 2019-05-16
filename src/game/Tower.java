package game;

import java.awt.Graphics2D;
import java.net.URL;

import javax.swing.JLabel;

public class Tower {
	private URL projectile;
	private int diameter;
	private int speed;
	private int cooldown;
	private int attack;
	
	private int cost;
	private String type;
	private URL image;
	
	public Tower(String tower) {
		switch (tower) {
		case "Arrow":
			type = "Arrow";
			attack = 4;
			speed = 10;
			cooldown = 0;
			cost = 25;
			diameter = (int)(Math.random() * 4) + 3;
			image = Tower.class.getResource("/assets/tower_arrow.png");
			projectile = Tower.class.getResource("/assets/arrow.png");
			break;
		}
	}
	
	public String getType() {
		return type;
	}
	
	public int getAttack() {
		return attack;
	}
	
	public int getSpeed() {
		return speed;
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
		temp.setDamage(attack);
		return temp;
	}
	
	public boolean getCooldown() {
		return cooldown == 0;
	}
	
	public int getRadius() {
		return diameter * 25;
	}
	
	public TowerRange getRange() {
		return new TowerRange(diameter);
	}
	
	public int getCost() {
		return cost;
	}
	
	public URL getImagePath() {
		return image;
	}
}
