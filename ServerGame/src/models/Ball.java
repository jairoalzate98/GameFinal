package models;

import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Iterator;

public class Ball implements Runnable{

	private Thread thread;
	private boolean stop;
	private static final int BOX_WIDTH = 610;
	private static final int BOX_HEIGHT = 610;  
	private float ballRadius = 30; 
	private float posX = ballRadius + 10; 
	private float posY = ballRadius + 50; 
	private float ballSpeedX = 3;  
	private float ballSpeedY = 2;
	private Manager manager;

	public Ball(int posX, int posY, Manager manager) {
		this.manager = manager;
		thread = new Thread(this);
		thread.start();
	}

	@Override
	public void run() {
		while (!stop) { 
			posX += ballSpeedX;
			posY += ballSpeedY;
			Player p = verifyCollision();
			if (p != null) {
				if (posX - ballRadius < p.getPosX()) {
					ballSpeedX = -ballSpeedX;
					posX = p.getPosX() - ballRadius; 
				} else if (posX + ballRadius > p.getPosX() + 50) {
					ballSpeedX = -ballSpeedX;
					posX = p.getPosX() + 50 - ballRadius;
				}else if (posY - ballRadius < p.getPosY()) {
					ballSpeedY = -ballSpeedY;
					posY = p.getPosY() - ballRadius;
				} else if (posY + ballRadius > p.getPosY() + 60) {
					ballSpeedY = -ballSpeedY;
					posY = p.getPosY() + 60 - ballRadius;
				}
			}else {
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
			}
			verifyGoals();
			try {
				Thread.sleep(15); 
			} catch (InterruptedException ex) { }
			p = null;
		}
	}

	private void verifyGoals() {
		ArrayList<Goal> goals = manager.getGoalList();
		Iterator<Goal> iter = goals.iterator();
		while(iter.hasNext()) {
			Goal goal = iter.next();
			Rectangle ball = new Rectangle((int)posX, (int)posY, 30, 30);
			Rectangle goalRectangle = new Rectangle(goal.getPosX(), goal.getPosY(), 50, 60);
			if (ball.intersects(goalRectangle)) {
				goal.setGoals(goal.getGoals() + 1);
				setPosX(30);
				setPosY(30);
			}
		}
	}

	public Player verifyCollision() {
		Player col = null;
		ArrayList<Player> players = manager.getPlayerList();
		Iterator<Player> iter = players.iterator();
		while(iter.hasNext()) {
			Player player = iter.next();
			Rectangle ball = new Rectangle((int)posX, (int)posY, 30, 30);
			Rectangle playerRectangle = new Rectangle(player.getPosX(), player.getPosY(), 50, 60);
			if (ball.intersects(playerRectangle)) {
				col = player;
			}
		}
		return col;
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