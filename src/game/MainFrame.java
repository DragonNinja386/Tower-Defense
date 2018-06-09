//How to prepare button for picture:
//jb.setBorderPainted(false);
//jb.setFocusPainted(false);
//jb.setContentAreaFilled(false);

package game;

import java.awt.Component;
import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;

public class MainFrame extends JFrame
{
	//Swing variables

	
	//Frame variables
	private static final long serialVersionUID = 1L;
	private final int APPLICATION_HEIGHT = 600;
	private final int APPLICATION_WIDTH = 800;
	
	public MainFrame() {
		super("Cool Game!");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // <--
		this.setResizable(false); // <--
		this.setLayout(null); // <--
		this.setSize(APPLICATION_WIDTH + 6, APPLICATION_HEIGHT + 29); // <--
		this.setLocationRelativeTo(null);
		this.setTitle("Tower Defense Revolution"); // <--
		this.setVisible(true); // <--
		
	}
	
	public void setUpPanels(JLayeredPane gp, JPanel ip, JPanel tp) {
		JPanel panel = new JPanel();
		panel.setLayout(null);
		//panel.setSize(this.getWidth(), this.getHeight());
		panel.add(gp);
		panel.add(ip);
		panel.add(tp);
		this.setContentPane(panel);
		
		panel.revalidate();
		panel.repaint();
	}
	
	@Override
	public int getWidth()
	{ return APPLICATION_WIDTH; }
	
	@Override
	public int getHeight()
	{ return APPLICATION_HEIGHT; }
}
