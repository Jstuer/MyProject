package TankGame;

import java.awt.event.KeyListener;

import javax.swing.JFrame;

public class TankGame01 extends JFrame {
	MyPanel myPanel = null;
	
	public static void main(String[] args) {
		TankGame01 tankGame01 = new TankGame01();
	}
	
	public TankGame01() {
		// TODO Auto-generated constructor stub
		myPanel = new MyPanel();
		Thread thread = new Thread(myPanel);
		thread.start();
		this.add(myPanel);	
		this.addKeyListener(myPanel);
		this.setSize(1000,750);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}
}
