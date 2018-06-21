package views;

import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import models.Goal;

public class JPanelGame extends JPanel{

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
			if (goal.getPosX() == 0 && goal.getPosY() == 260) {
				g.drawImage(new ImageIcon(getClass().getResource("/img/goal Left.png")).getImage(), goal.getPosX(), goal.getPosY(), 40, 80, this);
			}else if(goal.getPosX() == 260 && goal.getPosY() == 0){
				g.drawImage(new ImageIcon(getClass().getResource("/img/goal.png")).getImage(), goal.getPosX(), goal.getPosY(), 80, 40, this);
			}else if(goal.getPosX() == 260 && goal.getPosY() == 600){
				g.drawImage(new ImageIcon(getClass().getResource("/img/goal Down.png")).getImage(), goal.getPosX(), goal.getPosY(), 80, 40, this);
			}else {
				g.drawImage(new ImageIcon(getClass().getResource("/img/goal Right.png")).getImage(), goal.getPosX(), goal.getPosY(), 40, 80, this);
			}
		}
	}
}