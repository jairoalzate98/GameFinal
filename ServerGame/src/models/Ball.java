package models;

public class Ball implements Runnable{

	private int posX;
	private int posY;
	private int posXLast;
	private int posYLast;
	private float angle;
	private Thread thread;
	private boolean stop;
	public static final int VELOCITY = 4;

	public Ball(int posX, int posY) {
		this.posX = 90;
		this.posY = 90;
		posXLast = 80;
		posYLast = 80;
		thread = new Thread(this);
		thread.start();
	}

	public int getPosX() {
		return posX;
	}

	public void setPosX(int posX) {
		this.posXLast = this.posX;
		this.posX = posX;
	}

	public int getPosY() {
		return posY;
	}

	public void setPosY(int posY) {
		this.posYLast = this.posY;
		this.posY = posY;
	}

	@Override
	public void run() {
		while (!stop) {
			try {
				Thread.sleep(60);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			angle = getAngle();
			verifyAngle();
		}
	}

	private void verifyAngle() {
		posX += VELOCITY * Math.cos(angle * Math.PI /180);
		posY += VELOCITY * Math.sin(angle * Math.PI /180);
		if (posX < 0 || posX > 591) {
			angle = 180 - angle;   
		}
		else if (posX < 0 ||posX > 591) {
			angle = 360 - angle; 
		}
	}

	public float getAngle() {
		float angle = (float) Math.toDegrees(Math.atan2(posY - posYLast, posX - posXLast));
		if(angle < 0){
			angle += 360;
		}
		return angle;
	}
}