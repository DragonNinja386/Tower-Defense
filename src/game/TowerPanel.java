package game;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

public class TowerPanel extends JPanel {
	private boolean placement;
	private Tower current;
	private Tower[] towers;
	private Timer time;
	private JLabel message;
	
	public TowerPanel() {
		this.setBounds(0, 450, 800, 150);
		this.setLayout(null);
		this.setBorder(BorderFactory.createLineBorder(Color.black));
		
		message = new JLabel();
		message.setBounds(20, 120, 300, 30);
		message.setVisible(false);
		this.add(message);
		
		JButton jb1 = new JButton("Arrow");
		jb1.setBounds(100, 35, 80, 80);
		jb1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (!placement || current != towers[0]) {
					current = towers[0];
					placement = true;
					GameControl.takeAction(Action.place);
				} else {
					placement = false;
					GameControl.takeAction(Action.unplace);
				}
			}
		});
		this.add(jb1);
		
		time = new Timer(2000, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				message.setVisible(false);
				time.stop();
			}
		});
		time.setInitialDelay(2000);
		
		
		
		towers = new Tower[4];
		towers[0] = new Tower("Arrow");
		placement = false;
		current = null;
		
		this.revalidate();
		this.repaint();
	}
	
	public void displayMessage(String messageToDisplay) {
		message.setText(messageToDisplay);
		message.setVisible(true);
		time.start();
		
		this.revalidate();
		this.repaint();
	}
	
	public void stopPlacement() {
		placement = false;
	}
	
	public boolean towerPlace() {
		return placement;
	}
	
	public Tower towerToPlace() {
		return current;
	}
}
