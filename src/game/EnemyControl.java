package game;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class EnemyControl {
	private class EnemyImage extends JLabel {
		String imagePath;
		int cost;
		double scale;
		
		@Override
		protected void paintComponent(Graphics g) {
			if (imagePath != null) {
				File file = new File(imagePath);
				Image image = null;
				try {
					image = ImageIO.read(file);
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
	
	public MainFrame mf;
	public JPanel panel;
	
	public int costLimit;
	public int costCount;
	public boolean selectEnemy;
	
	public JLabel[] costLabel;
	public EnemyImage[] enemyLabel;
	public ArrayList<EnemyImage> lineUp;
	
	public EnemyControl() {
		mf = new MainFrame();
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
			costLabel[i].addMouseListener(new MouseListener() {
				@Override
				public void mouseClicked(MouseEvent e) {
					// TODO Auto-generated method stub
					EnemyImage e = (EnemyImage)e.getSource();
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
			panel.add(enemyLabel[i]);
		}
		enemyLabel[0].setBounds(180,35,50,50);
		enemyLabel[0].cost = 2;
		enemyLabel[0].imagePath = "src//assets//enemyBasic.png";
		enemyLabel[1].setBounds(50,35,50,50);
		enemyLabel[1].cost = 1;
		enemyLabel[1].imagePath = "src//assets//enemyFast.png";
		enemyLabel[2].setBounds(180,110,50,50);
		enemyLabel[2].cost = 4;
		enemyLabel[2].imagePath = "src//assets//enemyShield.png";
		enemyLabel[3].setBounds(50,110,50,50);
		enemyLabel[3].cost = 5;
		enemyLabel[3].imagePath = "src//assets//enemyHeal.png";
		enemyLabel[4].setBounds(180,185,50,50);
		enemyLabel[4].cost = 3;
		enemyLabel[4].imagePath = "src//assets//enemyDodge.png";
		enemyLabel[5].setBounds(50,185,50,50);
		enemyLabel[5].cost = 20;
		enemyLabel[5].imagePath = "src//assets//enemyBoss.png";
		
		
		costCount = 14;
		
		lineUp = new ArrayList<>();
		
		selectEnemy = true;
		
		panel.revalidate();
		panel.repaint();
		mf.revalidate();
		mf.repaint();
	}
}
