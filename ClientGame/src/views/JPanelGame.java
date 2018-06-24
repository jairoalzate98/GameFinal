package views;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import controllers.Controller;
import models.Ball;
import models.Goal;
import models.Player;

public class JPanelGame extends JPanel{

	public static final Font FONT_UBUNTU = new Font("Ubuntu", Font.BOLD, 18);
	private static final long serialVersionUID = 1L;
	private ArrayList<Goal> goalList;
	private ArrayList<Player> playerList;
	private Ball ball;
	private ImageIcon goalUp = new ImageIcon(getClass().getResource("/img/goal.png"));
	private ImageIcon goalLeft = new ImageIcon(getClass().getResource("/img/goal Left.png"));
	private ImageIcon goalDown = new ImageIcon(getClass().getResource("/img/goal Down.png"));
	private ImageIcon goalRight = new ImageIcon(getClass().getResource("/img/goal Right.png"));
	private ImageIcon person = new ImageIcon(getClass().getResource("/img/persona.png"));
	private ImageIcon image = new ImageIcon(getClass().getResource("/img/pasto.jpg"));
	private ImageIcon ballImage = new ImageIcon(getClass().getResource("/img/ball.png"));

	public JPanelGame(Controller controller) {
		goalList = new ArrayList<>();
		playerList = new ArrayList<>();
		addKeyListener(controller);
	}

	public void setGoalList(ArrayList<Goal> goalList) {
		this.goalList = goalList;
	}

	public void setPlayerList(ArrayList<Player> playerList) {
		this.playerList = playerList;
	}

	public ArrayList<Goal> getGoalList() {
		return goalList;
	}

	public ArrayList<Player> getPlayerList() {
		return playerList;
	}

	public Ball getBall() {
		return ball;
	}

	public void setBall(Ball ball) {
		this.ball = ball;
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		g.drawImage(image.getImage(), 0, 0, 660, 680, this);
		for (Goal goal : goalList) {
			g.setColor(Color.WHITE);
			g.setFont(FONT_UBUNTU);
			if (goal.getPosX() == 0 && goal.getPosY() == 260) {
				g.drawImage(goalLeft.getImage(), goal.getPosX(), goal.getPosY(), 40, 80, this);
				g.drawString(String.valueOf(goal.getIdClient()), goal.getPosX(), goal.getPosY() + 100);
			}else if(goal.getPosX() == 260 && goal.getPosY() == 0){
				g.drawImage(goalUp.getImage(), goal.getPosX(), goal.getPosY(), 80, 40, this);
				g.drawString(String.valueOf(goal.getIdClient()), goal.getPosX() - 20, goal.getPosY() + 20);
			}else if(goal.getPosX() == 260 && goal.getPosY() == 600){
				g.drawImage(goalDown.getImage(), goal.getPosX(), goal.getPosY(), 80, 40, this);
				g.drawString(String.valueOf(goal.getIdClient()), goal.getPosX() + 100, goal.getPosY() + 20);
			}else {
				g.drawImage(goalRight.getImage(), goal.getPosX(), goal.getPosY(), 40, 80, this);
				g.drawString(String.valueOf(goal.getIdClient()), goal.getPosX() + 20, goal.getPosY() - 10);
			}
		}
		for (Player player : playerList) {
			g.setColor(Color.YELLOW);
			if (player.getPosX() == 0 && player.getPosY() == 260) {
				g.drawImage(person.getImage(), player.getPosX(), player.getPosY(), 50, 60, this);
				g.drawString(String.valueOf(player.getIdClient()), player.getPosX() + 20, player.getPosY() - 10);
			}else if(player.getPosX() == 260 && player.getPosY() == 0){
				g.drawImage(person.getImage(), player.getPosX(), player.getPosY(), 50, 60, this);
				g.drawString(String.valueOf(player.getIdClient()), player.getPosX() + 20, player.getPosY() - 10);
			}else if(player.getPosX() == 260 && player.getPosY() == 600){			
				g.drawImage(person.getImage(), player.getPosX(), player.getPosY(), 50, 60, this);
				g.drawString(String.valueOf(player.getIdClient()), player.getPosX() +  20, player.getPosY() - 10);
			}else {
				g.drawImage(person.getImage(), player.getPosX(), player.getPosY(), 50, 60, this);
				g.drawString(String.valueOf(player.getIdClient()), player.getPosX() + 20, player.getPosY() - 10);
			}
		}
		try {
			g.drawImage(ballImage.getImage(),(int) ball.getPosX(),(int) ball.getPosY(), 30, 30, this);
		}catch (Exception e) {
		}
	}
}