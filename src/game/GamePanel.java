package game;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.net.URL;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;

public class GamePanel extends JLayeredPane {
	private ArrayList<Projectile> projectiles;
	private ArrayList<Grid> towers;
	private ArrayList<Enemy> enemies;
	int[][] path;
	private Grid[][] grid;
	private Grid selectedGrid;
	private MouseListener displayml, ml, towerML;
	private Enemy selectedEnemy;
	private Enemy attackingEnemy;
	private Tower selectedTower;
	
	public GamePanel() {
		this.setBounds(0, 0, 601, 450);
		this.setLayout(null);
		
		projectiles = new ArrayList<>();
		towers = new ArrayList<>();
		enemies = new ArrayList<>();
		
		grid = new Grid[9][12];
		for (int r = 0, i = 0; r < grid.length; r++) {
			for (int c = 0; c < grid[0].length; c++, i++) {
				grid[r][c] = new Grid(i + "");
				grid[r][c].setBounds(c * 50 + 1, r * 50 + 1, 50, 50);
				grid[r][c].addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						Grid grid = (Grid)e.getSource();
						selectedGrid = grid;
					}
				});
				ml = new MouseListener() {
					@Override
					public void mouseClicked(MouseEvent arg0) {
						Grid g = (Grid)arg0.getSource();

						g.removeMouseListener(this);
						GameControl.takeAction(Action.buy);
					}

					@Override
					public void mouseEntered(MouseEvent arg0) {}

					@Override
					public void mouseExited(MouseEvent arg0) {}

					@Override
					public void mousePressed(MouseEvent arg0) {}

					@Override
					public void mouseReleased(MouseEvent arg0) {}
				};
				towerML = new MouseListener() {
					@Override
					public void mouseClicked(MouseEvent arg0) {
						Grid g = (Grid)arg0.getSource();
						selectedTower = g.getTower();
						GameControl.takeAction(Action.displayTower);
					}

					@Override
					public void mouseEntered(MouseEvent arg0) {
						Grid g = (Grid)arg0.getSource();
						g.getRange().setVisible(true);
						
						
					}

					@Override
					public void mouseExited(MouseEvent arg0) {
						Grid g = (Grid)arg0.getSource();
						g.getRange().setVisible(false);
					}

					@Override
					public void mousePressed(MouseEvent arg0) {}

					@Override
					public void mouseReleased(MouseEvent arg0) {}
				};
				displayml = new MouseListener() {
					@Override
					public void mouseClicked(MouseEvent arg0) {
						GameControl.takeAction(Action.undisplay);
					}

					@Override
					public void mouseEntered(MouseEvent arg0) {}

					@Override
					public void mouseExited(MouseEvent arg0) {}

					@Override
					public void mousePressed(MouseEvent arg0) {}

					@Override
					public void mouseReleased(MouseEvent arg0) {}
				};
				grid[r][c].addMouseListener(displayml);
				
				this.add(grid[r][c], 2);
			}
		}
		
		path = createPath();
		
		for (int i = 0; i < path.length; i++) {
			grid[path[i][0]][path[i][1]].setPath(true);
		}
		
		for (int r = 0; r < grid.length; r++) {
			for (int c = 0; c < grid[0].length; c++) {
				if (!grid[r][c].getPath()) {
					String image = "grass" + (int)(Math.random() * 3) + ".png";
					URL imgURL = GamePanel.class.getResource("/assets/" + image);
					grid[r][c].setImage(imgURL);
				}
			}
		}
		
		String currentDir = "up";
		String imagePath = "/assets/path_";
		for (int i = 0; i < path.length; i++) {
			//TODO display path tiles
			if (grid[path[i][0]][path[i][1]].getDir() != null) {
				currentDir += grid[path[i][0]][path[i][1]].getDir();
				URL imgURL1 = GamePanel.class.getResource(imagePath + currentDir + ".png");
				grid[path[i][0]][path[i][1]].setImage(imgURL1); 
				currentDir = grid[path[i][0]][path[i][1]].getDir();
			} else {
				URL imgURL1 = GamePanel.class.getResource(imagePath + currentDir + ".png");
				grid[path[i][0]][path[i][1]].setImage(imgURL1); 
			}
		}
		
		this.revalidate();
		this.repaint();
	}
	
	public boolean enemiesPresent() {
		return enemies.size() > 0;
	}
	
	public Enemy getAttackingEnemy() {
		return attackingEnemy;
	}
	
	public Enemy getSelectedEnemy() {
		return selectedEnemy;
	}
	
	public Tower getSelectedTower() {
		return selectedTower;
	}
	
	private int[][] createPath() {
		int[][] path = new int[28][2];
		path[0][1] = 6;
		path[0][0] = 8;
		path[1][1] = 6;
		path[1][0] = 7;
		grid[path[1][0]][path[1][1]].setDir("right");
		path[2][1] = 7;
		path[2][0] = 7;
		path[3][1] = 8;
		path[3][0] = 7;
		path[4][1] = 9;
		path[4][0] = 7;
		grid[path[4][0]][path[4][1]].setDir("up");
		path[5][1] = 9;
		path[5][0] = 6;
		path[6][1] = 9;
		path[6][0] = 5;
		grid[path[6][0]][path[6][1]].setDir("left");
		path[7][1] = 8;
		path[7][0] = 5;
		path[8][1] = 7;
		path[8][0] = 5;
		path[9][1] = 6;
		path[9][0] = 5;
		path[10][1] = 5;
		path[10][0] = 5;
		path[11][1] = 4;
		path[11][0] = 5;
		path[12][1] = 3;
		path[12][0] = 5;
		grid[path[12][0]][path[12][1]].setDir("down");
		path[13][1] = 3;
		path[13][0] = 6;
		path[14][1] = 3;
		path[14][0] = 7;
		grid[path[14][0]][path[14][1]].setDir("left");
		path[15][1] = 2;
		path[15][0] = 7;
		path[16][1] = 1;
		path[16][0] = 7;
		grid[path[16][0]][path[16][1]].setDir("up");
		path[17][1] = 1;
		path[17][0] = 6;
		path[18][1] = 1;
		path[18][0] = 5;
		path[19][1] = 1;
		path[19][0] = 4;
		path[20][1] = 1;
		path[20][0] = 3;
		path[21][1] = 1;
		path[21][0] = 2;
		grid[path[21][0]][path[21][1]].setDir("right");
		path[22][1] = 2;
		path[22][0] = 2;
		path[23][1] = 3;
		path[23][0] = 2;
		path[24][1] = 4;
		path[24][0] = 2;
		path[25][1] = 5;
		path[25][0] = 2;
		grid[path[25][0]][path[25][1]].setDir("up");
		path[26][1] = 5;
		path[26][0] = 1;
		path[27][1] = 5;
		path[27][0] = 0;
		

		
		return path;
	}
	
	public void update() {
		for (Enemy e : enemies) {
			if (e.getHeal()) {
				for (Enemy e2 : enemies) {
					if (e != e2) {
						int distance = (int)(Math.sqrt(Math.pow(e2.getX() - e.getX(), 2) + Math.pow(e2.getY() - e.getY(), 2)));
						if (distance < 25) {
							e2.heal();
						}
					}
				}
			} else if (e.getShield()) {
				for (Enemy e2 : enemies) {
					if (e != e2) {
						int distance = (int)(Math.sqrt(Math.pow(e2.getX() - e.getX(), 2) + Math.pow(e2.getY() - e.getY(), 2)));
						if (distance < 75) {
							e2.shield();
						}
					}
				}
			}
			if (e.getHealth() <= 0) {
				this.remove(e);
				e.setVisible(false);
				break;
			}
			e.move();
			if (e.getGrid() + 1 == path.length) {
				if (e.getY() <= 0) {
					attackingEnemy = e;
					GameControl.takeAction(Action.attacked);
					this.remove(e);
					e.setVisible(false);
					break;
				}
			}
			else if (e.getGrid() != -1) {
				Grid g = grid[path[e.getGrid()][0]][path[e.getGrid()][1]];
				switch (e.getDir()) {
					case "up":
						if (e.getY() <= g.getY() + 13) {
							if (g.getDir() != null) {
								e.setDir(g.getDir());
							}
							e.nextGrid();
						}
						break;
					case "right":
						if (e.getX() >= g.getX() + 13) {
							if (g.getDir() != null) {
								e.setDir(g.getDir());
							}
							e.nextGrid();
						}
						break;
					case "down":
						if (e.getY() >= g.getY() + 13) {
							if (g.getDir() != null) {
								e.setDir(g.getDir());
							}
							e.nextGrid();
						}
						break;
					case "left":
						if (e.getX() <= g.getX() + 13) {
							if (g.getDir() != null) {
								e.setDir(g.getDir());
							}
							e.nextGrid();
						}
						break;
				}
			} else {
				if (e.getY() < 437) {
					e.nextGrid();
				}
			}
			
			for (Grid g : towers) {
				Tower t = g.getTower();
					if (t.getCooldown()) {
					int towerRadius = t.getRadius();
					int enemyRadius = e.getWidth() / 2;
					int distance = (int)(Math.sqrt(Math.pow(g.getX() - e.getX(), 2) + Math.pow(g.getY() - e.getY(), 2)));
					if(towerRadius + enemyRadius >= distance) {
						//TODO Trig calc stuff
						int enemyX = e.getX() + e.getWidth() / 2, enemyY = e.getY() + e.getHeight() / 2;
						int towerX = g.getX() + g.getWidth() / 2, towerY = g.getY() + g.getHeight() / 2;
						
						Projectile p = t.getProjectile(e);
						p.setBounds(towerX, towerY, p.getWidth(), p.getHeight());
						this.add(p, 1);
						projectiles.add(p);
						t.setCooldown();
					}
				}
			}
			
		}
		for (Grid g : towers) {
			g.getTower().cooldown();
		}
		
		for (Projectile p : projectiles) {
			p.move();
			if (p.checkCollision()) {
				this.remove(p);
				p.setVisible(false);
			}
		}
		for (int i = 0; i < enemies.size(); i++) {
			if (!enemies.get(i).isVisible()) {
				enemies.remove(i);
				i--;
			}
		}
		for (int i = 0; i < projectiles.size(); i++) {
			if (!projectiles.get(i).isVisible()) {
				projectiles.remove(i);
				i--;
			}
		}
		
	}
	
	public void placeTower() {
		selectedGrid.removeMouseListener(displayml);
		selectedGrid.addMouseListener(towerML);
		selectedGrid.intializeRange();
		this.add(selectedGrid.getRange(), 1);
		towers.add(selectedGrid);
		selectedGrid.setTower(true);
		for (Grid g : towers) {
			System.out.println(g);
			System.out.println(g.getTower());
		}
		System.out.println();
	}
	
	public void placement() {
		for (int r = 0; r < grid.length; r++) {
			for (int c = 0; c < grid[0].length; c++) {
				if (!grid[r][c].getPath() && !grid[r][c].getTowerPlaced()) {
					grid[r][c].setTower(selectedTower);
					grid[r][c].addMouseListener(ml);
					grid[r][c].togglePlacement(true);
				}
			}
		}
	}
	
	public void stopPlacement() {
		for (int r = 0; r < grid.length; r++) {
			for (int c = 0; c < grid[0].length; c++) {
				if (!grid[r][c].getPath()) {
					grid[r][c].removeMouseListener(ml);
					grid[r][c].togglePlacement(false);
				}
			}
		}
	}
	
	public void selectTower(Tower t) {
		selectedTower = new Tower(t.getType());
	}
	
	public void spawnEnemy(Enemy e) {
		e.setBounds(316, 425);
		enemies.add(e);
		this.add(enemies.get(enemies.size() - 1), 1);
	}
}
