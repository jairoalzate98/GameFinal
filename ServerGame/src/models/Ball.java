package models;

public class Ball implements Runnable{

	private Thread thread;
	private boolean stop;
	private static final int BOX_WIDTH = 600;
	private static final int BOX_HEIGHT = 600;  
	private float ballRadius = 30; // Ball's radius
	private float posX = ballRadius + 10; // Ball's center (x, y)
	private float posY = ballRadius + 50; 
	private float ballSpeedX = 3;   // Ball's speed for x and y
	private float ballSpeedY = 2;

	public Ball(int posX, int posY) {
		thread = new Thread(this);
		thread.start();
	}

	@Override
	public void run() {
		while (!stop) { 
  		  posX += ballSpeedX;
  		  posY += ballSpeedY;
  		  if (posX - ballRadius < 0) {
  			  ballSpeedX = -ballSpeedX;
  			  posX = ballRadius; 
  		  } else if (posX + ballRadius > BOX_WIDTH) {
  			  ballSpeedX = -ballSpeedX;
  			  posX = BOX_WIDTH - ballRadius;
  		  }
  		  if (posY - ballRadius < 0) {
  			  ballSpeedY = -ballSpeedY;
  			  posY = ballRadius;
  		  } else if (posY + ballRadius > BOX_HEIGHT) {
  			  ballSpeedY = -ballSpeedY;
  			  posY = BOX_HEIGHT - ballRadius;
  		  }
  		  try {
  			  Thread.sleep(50); 
  		  } catch (InterruptedException ex) { }
		}
	}

	public float getPosX() {
		return posX;
	}

	public void setPosX(float posX) {
		this.posX = posX;
	}

	public float getPosY() {
		return posY;
	}

	public void setPosY(float posY) {
		this.posY = posY;
	}
}