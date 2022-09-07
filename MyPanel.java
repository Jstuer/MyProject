package TankGame;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Iterator;
import java.util.Vector;

//坦克大战绘图区域

import javax.swing.JPanel;

public class MyPanel extends JPanel implements KeyListener,Runnable{
	

//	定义坦克
	MyTank hero = null;
//	定义敌方坦克，放到vector中
	Vector< EnemyTank >enemyTanks = new Vector<>();
	int enemyTankSize = 3;
	public MyPanel() {
		// TODO Auto-generated constructor stub
		hero = new MyTank(100, 100);// 初始化一个坦克
		for(int i = 0; i<enemyTankSize; i++) {
			EnemyTank enemyTank = new EnemyTank((100 * (i + 1)), 0);
			enemyTank.setDirect(1);
			enemyTanks.add(enemyTank);
			Shot shot = new Shot(enemyTank.getX()+20, enemyTank.getY()+60, enemyTank.getDirect());
			enemyTank.shots.add(shot);
			Thread thread = new Thread(shot);
			thread.start();
		}
		
	}
	public void run() {
		while(true) {
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if(hero.shot != null&&hero.shot.alive == true ) {
				for(int i = 0; i<enemyTanks.size();i++) {
					EnemyTank enemyTank = enemyTanks.get(i);
					hitTank(hero.shot, enemyTank);
					System.out.println(11);
				}
			}
			this.repaint();
		}
	}
	
	public void hitTank(Shot s, EnemyTank enemyTank) {
		switch (s.getDirect()){
		case 0: 
		case 1:{
			if(s.getX()>enemyTank.getX()&&s.getX()<enemyTank.getX()+40
					&&s.getY()>enemyTank.getY()&&s.getY()<enemyTank.getY()+60) {
				s.alive = false;
				enemyTank.alive = false;
			}
			break;
		}
		case 2:
		case 3:{
			if(s.getX()>enemyTank.getX()&&s.getX()<enemyTank.getX()+60
					&&s.getY()>enemyTank.getY()&&s.getY()<enemyTank.getY()+40) {
				s.alive = false;
				enemyTank.alive = false;
			}
			break;
		}
		default:
			throw new IllegalArgumentException("没有过case");
		}
	}
	@Override
	public void paint(Graphics g) {
		// TODO Auto-generated method stub
		super.paint(g);
		g.fillRect(0, 0, 1000, 750);
		hero.setSpeed(3);
		drawTank(hero.getX(), hero.getY(), g, hero.getDirect(), 0);
		
		for(int i = 0; i<enemyTanks.size(); i++) {
			EnemyTank enemyTank = enemyTanks.get(i);
			if(enemyTank.alive == true) {
				drawTank(enemyTank.getX(), enemyTank.getY(), g,enemyTank.getDirect(),1);
				for(int j = 0; j<enemyTank.shots.size(); j++) {
					Shot shot = enemyTank.shots.get(j);
					if(shot.alive == true || shot != null)
							g.fill3DRect(shot.getX(), shot.getY(), 2, 2, false);
					else
						enemyTank.shots.remove(j);
				}
			}
			else {
				enemyTanks.remove(i);
			}
		}
		if(hero.shot!=null && hero.shot.alive == true) {
			g.fill3DRect(hero.shot.getX(), hero.shot.getY(), 2, 2, false);
			
		}
	}
	
	
	
	public void drawTank(int x, int y, Graphics g, int direct,	int type) {
		switch (type) {
			case 0: {
				g.setColor(Color.BLUE);
				break;
			}
			case 1:{
				g.setColor(Color.YELLOW);
			}
		}
		switch (direct) {
//			上
			case 0: {
				//俩轮子
				g.fill3DRect(x, y, 10, 60,false);
				g.fill3DRect(x+30, y, 10, 60,false);
				//坦克盖子
				g.fill3DRect(x+10, y+10, 20, 40,false);
				//圆形盖子炮筒
				g.fillOval(x+10, y+20, 20, 20);//圆左上角非圆心
				g.drawLine(x+20, y+20, x+20, y);
				break;
			}
//			下
			case 1:{
				//俩轮子
				g.fill3DRect(x, y, 10, 60,false);
				g.fill3DRect(x+30, y, 10, 60,false);
				//坦克盖子
				g.fill3DRect(x+10, y+10, 20, 40,false);
				//圆形盖子炮筒
				g.fillOval(x+10, y+20, 20, 20);//圆左上角非圆心
				g.drawLine(x+20, y+20, x+20, y + 60);
				break;
			}
//			左
			case 2:{
				//俩轮子
				g.fill3DRect(x, y, 60, 10,false);
				g.fill3DRect(x, y+30, 60, 10,false);
				//坦克盖子
				g.fill3DRect(x+10, y+10, 40, 20,false);
				//圆形盖子炮筒
				g.fillOval(x+20, y+10, 20, 20);//圆左上角非圆心
				g.drawLine(x+20, y+20, x-20, y+20);
				break;
			}
//			右
			case 3:{
				//俩轮子
				g.fill3DRect(x, y, 60, 10,false);
				g.fill3DRect(x, y+30, 60, 10,false);
				//坦克盖子
				g.fill3DRect(x+10, y+10, 40, 20,false);
				//圆形盖子炮筒
				g.fillOval(x+20, y+10, 20, 20);//圆左上角非圆心
				g.drawLine(x+20, y+20, x+60, y+20);
				break;
			}
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}	
	
	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		int x = hero.getX();
		int y = hero.getY();
		int speed = hero.getSpeed();
		if(e.getKeyCode() == KeyEvent.VK_J) {
			hero.shotEnemy();
		}
		if(e.getKeyCode() == KeyEvent.VK_UP) {
			hero.setY(y-speed);
			hero.setDirect(0);
		}
		else if(e.getKeyCode() == KeyEvent.VK_DOWN) {
			hero.setY(y+speed);
			hero.setDirect(1);
		}
		else if(e.getKeyCode() == KeyEvent.VK_LEFT) {
			hero.setDirect(2);
			hero.setX(x-speed);
		}
		else if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
			hero.setX(x+speed);
			hero.setDirect(3);
		}
		
//		面板重绘，paint方法再次被调用·
		this.repaint();
	}
}
