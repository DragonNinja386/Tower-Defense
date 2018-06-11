package game;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
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
	private MouseListener ml, towerML;
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
					public void mouseClicked(MouseEvent arg0) {}

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
					grid[r][c].setImage("src\\assets\\" + image);
				}
				else {
					//TODO display path tiles
				}
			}
		}
		
		this.revalidate();
		this.repaint();
	}
	
	private int[][] createPath() {
		int[][] path = new int[14][2];
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
		
		return path;
	}
	
	public void update() {
		for (Enemy e : enemies) {
			e.move();
			if (e.getGrid() != -1) {
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
			System.out.println(g + ": " + g.getTower().cooldown());
		}
		
		for (Projectile p : projectiles) {
			p.move();
			if (p.checkCollision()) {
				this.remove(p);
				p.setVisible(false);
			}
		}
		for (int i = 0; i < projectiles.size(); i++) {
			if (!projectiles.get(i).isVisible()) {
				projectiles.remove(i);
				i--;
			}
		}
		
		System.out.println(projectiles.size());
		System.out.println("end of update");
	}
	
	public void placeTower() {
		selectedGrid.addMouseListener(towerML);
		selectedGrid.intializeRange();
		this.add(selectedGrid.getRange(), 1);
		towers.add(selectedGrid);
		selectedGrid.setTower(true);
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
		selectedTower = t;
	}
	
	public void spawnEnemy(Enemy e) {
		e.setBounds(316, 425);
		enemies.add(e);
		this.add(enemies.get(enemies.size() - 1), 1);
	}
}
