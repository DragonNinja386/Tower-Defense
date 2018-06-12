package game;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;

public class Grid extends JButton {
	private TowerRange range;
	private String dir;
	private Tower tower;
	private boolean pathTile;
	private boolean towerPlace;
	private String imagePathTile;
	private String imagePath;
	private MouseListener ml;
	
	public Grid(String s) {
		super(s);
		
		this.setBorderPainted(false);
		this.setFocusPainted(false);
		this.setContentAreaFilled(false);
		
		ml = new MouseListener() {
			@Override
			public void mouseClicked(MouseEvent arg0) {}

			@Override
			public void mouseEntered(MouseEvent e) {
				imagePath = tower.getImagePath();
			}

			@Override
			public void mouseExited(MouseEvent e) {
				imagePath = imagePathTile;
			}

			@Override
			public void mousePressed(MouseEvent e) {}

			@Override
			public void mouseReleased(MouseEvent e) {}

		};
		
		dir = null;
		tower = null;
		pathTile = false;
		imagePath = null;
	}
	
	public boolean getPath() {
		return pathTile;
	}
	
	public TowerRange getRange() {
		return range;
	}
	
	public Tower getTower() {
		return tower;
	}
	
	public String getDir() {
		return dir;
	}
	
	public boolean getTowerPlaced() {
		return towerPlace;
	}
	
	public void setDir(String s) {
		dir = s;
	}
	
	public void intializeRange() {
		range = tower.getRange();
		range.setBounds(this.getX(), this.getY());
	}
	
	public void setTower(boolean isPlaced) {
		towerPlace = isPlaced;
		this.removeMouseListener(ml);
		if (!isPlaced) {
			imagePath = imagePathTile;
		} else {
			imagePath = tower.getImagePath();
		}
	}
	
	public void setTower(Tower tower) {
		if (!towerPlace) {
			this.tower = tower;
		}
	}
	
	public void setImage(String s) {
		imagePathTile = s;
		imagePath = imagePathTile;
	}
	
	public void setPath(boolean b) {
		pathTile = b;
	}
	
	public void togglePlacement(boolean toggle) {
		if (toggle) {
			if (!towerPlace) {
				this.addMouseListener(ml);
			}
		} else {
			if (!towerPlace) {
				this.removeMouseListener(ml);
				imagePath = imagePathTile;
			}
		}
	}
	
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
		    g.drawImage(image, 0, 0, null);
		}
	}

}
