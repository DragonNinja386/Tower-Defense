package game;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class InfoPanel extends JPanel {
	private JLabel money;
	private JLabel health;
	
	private JLabel image;
	private JLabel entityHealth;
	private JLabel entityDamage;
	private JLabel entitySpeed;
	
	private Enemy enemy;
	
	public InfoPanel() {
		this.setBounds(600, 0, 200, 400);
		this.setLayout(null);
		
		health = new JLabel();
		health.setBounds(10, 2, 200, 30);
		this.add(health);
		
		money = new JLabel();
		money.setBounds(10, 20, 200, 30);
		this.add(money);
		
		
		entityHealth = new JLabel();
		entityHealth.setBounds(10, 100, 200, 30);
		this.add(entityHealth);
		
		entityDamage = new JLabel();
		entityDamage.setBounds(10, 200, 200, 30);
		this.add(entityDamage);
		
		entitySpeed = new JLabel();
		entitySpeed.setBounds(10, 300, 200, 30);
		this.add(entitySpeed);
		
		/*
		JButton jb = new JButton("Test");
		jb.setBounds(0, 0, 200, 30);
		jb.setBorderPainted(false);
	    jb.setFocusPainted(false);
	    jb.setContentAreaFilled(false);
		this.add(jb);
		*/
		
		this.revalidate();
		this.repaint();
	}
	
	public void setMoney(int value) {
		money.setText("Money: " + value);
	}
	
	public void setHealth(int value) {
		health.setText("Health: " + value);
	}
	
	public void displayEnemy(Enemy e) {
		enemy = e;
	}
	
	public void displayTower(Tower t) {
		//TODO This is broken, too complicated to fix
		this.repaint();
		this.revalidate();
	}
	
	public void undisplay() {
		enemy = null;
		entityHealth.setText("");
		entityDamage.setText("");
		entitySpeed.setText("");
	}
}
