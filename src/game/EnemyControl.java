package game;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class EnemyControl {
	private class EnemyImage extends JLabel {
		String enemy;
		URL imagePath;
		int cost;
		int cooldown;
		double scale;
		
		@Override
		protected void paintComponent(Graphics g) {
			if (imagePath != null) {
				Image image = null;
				try {
					image = ImageIO.read(imagePath);
				} catch (IOException e) {
					e.printStackTrace();
				}
				
				super.paintComponent(g);
				Graphics2D g2d = (Graphics2D)g;
		        g2d.scale(scale, scale);
		        g2d.drawImage(image,0,0,null);
			}
		}

	}
	
	private MainFrame mf;
	private JPanel panel;
	
	private static int costLimit;
	private static int costCount;
	private static String selectedEnemy;
	private static int cooldown;
	
	private static JLabel costCountLabel;
	private JLabel[] costLabel;
	private EnemyImage[] enemyLabel;
	
	public EnemyControl() {
		mf = new MainFrame();
		mf.setTitle("Two Player Game");
		mf.setSize(300, 600);

		panel = new JPanel();
		panel.setBounds(0, 0, 300, 600);
		panel.setLayout(null);
		mf.add(panel);
		
		costLabel = new JLabel[6];
		for (int i = 0; i < costLabel.length; i++) {
			costLabel[i] = new JLabel();
		}
		costLabel[0].setBounds(160,45,100,30);
		costLabel[0].setText("" + 2);
		costLabel[1].setBounds(30,45,100,30);
		costLabel[1].setText("" + 1);
		costLabel[2].setBounds(160,120,100,30);
		costLabel[2].setText("" + 4);
		costLabel[3].setBounds(30,120,100,30);
		costLabel[3].setText("" + 5);
		costLabel[4].setBounds(160,195,100,30);
		costLabel[4].setText("" + 3);
		costLabel[5].setBounds(30,195,100,30);
		costLabel[5].setText(20 + "");
		for (int i = 0; i < costLabel.length; i++) {
			panel.add(costLabel[i]);
		}
		
		enemyLabel = new EnemyImage[6];
		for (int i = 0; i < enemyLabel.length; i++) {
			enemyLabel[i] = new EnemyImage();
			enemyLabel[i].scale = 2;
			panel.add(enemyLabel[i]);
		}
		enemyLabel[0].setBounds(180,35,50,50);
		enemyLabel[0].cost = 2;
		enemyLabel[0].cooldown = 8;
		enemyLabel[0].enemy = "basic";
		enemyLabel[0].imagePath = EnemyControl.class.getResource("/assets/enemyBasic.png");
		enemyLabel[1].setBounds(50,35,50,50);
		enemyLabel[1].cost = 1;
		enemyLabel[1].cooldown = 5;
		enemyLabel[1].enemy = "fast";
		enemyLabel[1].imagePath = EnemyControl.class.getResource("/assets/enemyFast.png");
		enemyLabel[2].setBounds(180,110,50,50);
		enemyLabel[2].cost = 4;
		enemyLabel[2].cooldown = 15;
		enemyLabel[2].enemy = "shield";
		enemyLabel[2].imagePath = EnemyControl.class.getResource("/assets/enemyShield.png");
		enemyLabel[3].setBounds(50,110,50,50);
		enemyLabel[3].cost = 5;
		enemyLabel[3].cooldown = 15;
		enemyLabel[3].enemy = "heal";
		enemyLabel[3].imagePath = EnemyControl.class.getResource("/assets/enemyHeal.png");
		enemyLabel[4].setBounds(180,185,50,50);
		enemyLabel[4].cost = 3;
		enemyLabel[4].cooldown = 10;
		enemyLabel[4].enemy = "dodge";
		enemyLabel[4].imagePath = EnemyControl.class.getResource("/assets/enemyDodge.png");
		enemyLabel[5].setBounds(50,185,50,50);
		enemyLabel[5].cost = 20;
		enemyLabel[5].cooldown = 25;
		enemyLabel[5].enemy = "boss";
		enemyLabel[5].imagePath = EnemyControl.class.getResource("/assets/enemyBoss.png");
		for (EnemyImage el : enemyLabel) {
			el.addMouseListener(new MouseListener() {
				@Override
				public void mouseClicked(MouseEvent e) {
						if (cooldown == 0) {
						// TODO Auto-generated method stub
						EnemyImage enemy = (EnemyImage)e.getSource();
						if (costCount + enemy.cost <= costLimit) {
							costCount += enemy.cost;
							addEnemy(enemy.enemy);
							cooldown = enemy.cooldown;
							costCountLabel.setText(costCount + "/" + costLimit);
							if (costCount == costLimit) {
								endPlacement();
							}
						}
					}
				}
				@Override
				public void mouseEntered(MouseEvent e) {}
				@Override
				public void mouseExited(MouseEvent e) {}
				@Override
				public void mousePressed(MouseEvent e) {}
				@Override
				public void mouseReleased(MouseEvent e) {}
			});
		}
		
		cooldown = 0;
		costLimit = 14;
		costCount = 0;
		
		costCountLabel = new JLabel(0 + "/" + costLimit);
		costCountLabel.setBounds(5, 5, 100, 30);
		panel.add(costCountLabel);
		
		panel.revalidate();
		panel.repaint();
		mf.revalidate();
		mf.repaint();
	}
	
	public static void tickCooldown() {
		if (cooldown > 0) {
			cooldown--;
		}
	}
	
	public static void resetCost() {
		costCount = 0;
		costLimit += 4;
		costCountLabel.setText(0 + "/" + costLimit);
	}
	
	public static String getEnemy() {
		return selectedEnemy;
	}
	
	private void endPlacement() {
		GameControl.endPlacement();
	}
	
	private void addEnemy(String e) {
		selectedEnemy = e;
		GameControl.takeAction(Action.placeEnemy);
	}
}
