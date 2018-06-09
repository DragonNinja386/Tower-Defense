package game;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;

public class InfoPanel extends JPanel {

	public InfoPanel() {
		this.setBounds(600, 0, 200, 400);
		this.setLayout(null);
		
		JButton jb = new JButton("Test");
		jb.setBounds(0, 0, 200, 30);
		jb.setBorderPainted(false);
	    jb.setFocusPainted(false);
	    jb.setContentAreaFilled(false);
		this.add(jb);
		
		this.revalidate();
		this.repaint();
	}

	public void update() {
		
	}
}
