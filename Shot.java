package TankGame;

public class Shot implements Runnable{
	private int x;
	private int y;
	private int speed = 4;
	private int direct;
	public boolean alive = true;
	
	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public Shot(int x, int y, int direct) {
		this.x = x;
		this.y = y;
		this.direct = direct;
		// TODO Auto-generated constructor stub
	}
	
	public boolean isAlive() {
		return alive;
	}

	public void setAlive(boolean alive) {
		this.alive = alive;
	}

	public int getSpeed() {
		return speed;
	}



	public void setSpeed(int speed) {
		this.speed = speed;
	}



	public int getDirect() {
		return direct;
	}



	public void setDirect(int direct) {
		this.direct = direct;
	}



	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(true) {
			try {
				Thread.sleep(50);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
//			System.out.println(x+"   "+y);
			
			switch (direct) {
			case 0: {
				y-=speed;
				break;	
			}
			case 1 :{
				y+=speed;
				break;
			}
			case 2:{
				x-=speed;
				break;
			}
			case 3:{
				x+=speed;
				break;
			}
			default:
				throw new IllegalArgumentException("Unexpected value: " + direct);
			}
			if(x<0||x>1000||y<0||y>750||alive == false) {
				alive = false;
//				System.out.println("ÍË³ö");
				break;
			}
		}
		
	}
	
}
