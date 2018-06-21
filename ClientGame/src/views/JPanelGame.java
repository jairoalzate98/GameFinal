package views;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import models.Goal;

public class JPanelGame extends JPanel{

	public static final Font FONT_UBUNTU = new Font("Ubuntu", Font.BOLD, 18);
	private static final long serialVersionUID = 1L;
	private ArrayList<Goal> goalList;
	
	public JPanelGame() {
		goalList = new ArrayList<>();
	}
	
	public void setGoalList(ArrayList<Goal> goalList) {
		this.goalList = goalList;
	}
	
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		g.drawImage(new ImageIcon(getClass().getResource("/img/pasto.jpg")).getImage(), 0, 0, 660, 680, this);
		for (Goal goal : goalList) {
			g.setColor(Color.WHITE);
			g.setFont(FONT_UBUNTU);
			if (goal.getPosX() == 0 && goal.getPosY() == 260) {
				g.drawImage(new ImageIcon(getClass().getResource("/img/goal Left.png")).getImage(), goal.getPosX(), goal.getPosY(), 40, 80, this);
				g.drawString(String.valueOf(goal.getIdClient()), goal.getPosX(), goal.getPosY() + 100);
			}else if(goal.getPosX() == 260 && goal.getPosY() == 0){
				g.drawImage(new ImageIcon(getClass().getResource("/img/goal.png")).getImage(), goal.getPosX(), goal.getPosY(), 80, 40, this);
				g.drawString(String.valueOf(goal.getIdClient()), goal.getPosX() - 20, goal.getPosY() + 20);
			}else if(goal.getPosX() == 260 && goal.getPosY() == 600){
				g.drawImage(new ImageIcon(getClass().getResource("/img/goal Down.png")).getImage(), goal.getPosX(), goal.getPosY(), 80, 40, this);
				g.drawString(String.valueOf(goal.getIdClient()), goal.getPosX() + 100, goal.getPosY() + 20);
			}else {
				g.drawImage(new ImageIcon(getClass().getResource("/img/goal Right.png")).getImage(), goal.getPosX(), goal.getPosY(), 40, 80, this);
				g.drawString(String.valueOf(goal.getIdClient()), goal.getPosX() + 20, goal.getPosY() - 10);
			}
		}
	}
}