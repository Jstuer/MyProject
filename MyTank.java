package TankGame;

public class MyTank extends Tank{
	
	public Shot shot = null;
	
	public MyTank(int x, int y) {
		super(x, y);
		// TODO Auto-generated constructor stub
	}
	
	public void shotEnemy() {
		switch (this.getDirect()) {
		case 0: {
			shot = new Shot(getX()+20, getY(),0);
			break;
		}
		case 1:{
			shot = new Shot(getX()+20, getY()+60,1);
			break;
		}
		case 2:{
			shot = new Shot(getX(), getY()+20,2);
			break;
		}
		case 3:{
			shot = new Shot(getX()+60, getY()+20,3);
			break;
		}
		default:
			throw new IllegalArgumentException("Unexpected value: " );
		}
		Thread thread = new Thread(shot);
		thread.start();
	}
		
}
