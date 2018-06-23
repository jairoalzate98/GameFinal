package models;

public class Ball implements Runnable{

	private int posX;
	private int posY;
	private Thread thread;
	private boolean stop;
	
	public Ball(int posX, int posY) {
		this.posX = posX;
		this.posY = posY;
		thread = new Thread(this);
		thread.start();
	}

	public int getPosX() {
		return posX;
	}

	public void setPosX(int posX) {
		this.posX = posX;
	}

	public int getPosY() {
		return posY;
	}

	public void setPosY(int posY) {
		this.posY = posY;
	}

	@Override
	public void run() {
		while (!stop) {
			
		}
	}
}