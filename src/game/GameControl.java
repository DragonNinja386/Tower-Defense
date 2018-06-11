/* For calculating collision:
 *  float dx = c1.x - c2.x;
 *  float dy = c1.y - c2.y;
 *  float distance = dx * dx + dy * dy;
 *  float radiusSum = c1.radius + c2.radius;
 *  return distance < radiusSum * radiusSum;
 */
/* TODO
 * Implement second window
 * Implement Enemies
 * Implement targeting
 */

package game;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

public class GameControl {
	private static Action currentAction;
	private int money;
	private GamePanel gp;
	private InfoPanel ip;
	private TowerPanel tp;
	private Timer gameTimer;
	private Timer uiTimer;
	
	public GameControl() {
		MainFrame mf = new MainFrame();
		gp = new GamePanel();
		ip = new InfoPanel();
		tp = new TowerPanel();
		mf.setUpPanels(gp, ip, tp);
		
		gp.spawnEnemy(new Enemy()); //TODO debug
		
		money = 100;
		
		currentAction = null;

		gameTimer = new Timer(/*20*/100, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				tick();
				
			}
		});
		
		uiTimer = new Timer (50, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				//infoTick();
				gp.revalidate();
				gp.repaint();
			}
		});
		
		uiTimer.start();
		gameTimer.start();
	}
	
	public void tick() {
		if (currentAction != null) {
			switch (currentAction) {
				case place:
					gp.selectTower(tp.towerToPlace());
					gp.placement();
					currentAction = null;
					break;
				case unplace:
					gp.stopPlacement();
					tp.stopPlacement();
					currentAction = null;
					break;
				case buy:
					int buying = tp.towerToPlace().getCost();
					if (money - buying >= 0) {
						money -= buying;
						gp.placeTower();
					} else {
						tp.displayMessage("Not enough money!");
					}
					currentAction = Action.unplace;
					break;
				default:
					break;
			}
		}
		gp.update();
	}

	public void infoTick() {
		ip.update();
	}

	public static void takeAction(Action a) {
		currentAction = a;
	}
}
