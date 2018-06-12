package game;

import java.util.ArrayList;

import javax.swing.JLabel;

public class EnemyControl {
	public MainFrame mf;
	public boolean selectEnemy;
	
	public JLabel[] enemyLabel;
	public ArrayList<JLabel> lineUp;
	
	public EnemyControl() {
		mf = new MainFrame();
		
		enemyLabel = new JLabel[6];
		enemyLabel[0] = new JLabel();
		
		
		lineUp = new ArrayList<>();
		
		selectEnemy = true;
	}
}
