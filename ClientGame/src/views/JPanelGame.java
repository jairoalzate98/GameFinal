package views;

import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import models.Goal;

public class JPanelGame extends JPanel{

	private static final long serialVersionUID = 1L;
	private ArrayList<Goal> goalList;
	
	public void setGoalList(ArrayList<Goal> goalList) {
		this.goalList = goalList;
	}
	
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		for (Goal goal : goalList) {
			g.drawImage(new ImageIcon(getClass().getResource("/img/goal.png")).getImage(), goal.getPosX(), goal.getPosY(), 80, 40, this);
		}
	}
}