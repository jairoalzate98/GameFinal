package models;

import java.util.ArrayList;

public class Manager {

	private ArrayList<Goal> goalList;
	private boolean goalsCreated;
	
	public Manager() {
		goalList = new ArrayList<>();
	}

	public ArrayList<Goal> getGoalList() {
		return goalList;
	}
	
	public static Goal createGoal(int idClient, int posY, int posX) {
		return new Goal(idClient, posX, posY);
	}
	
	public void addGoal(Goal goal) {
		goalList.add(goal);
	}

	public boolean isGoalsCreated() {
		return goalsCreated;
	}

	public void setGoalsCreated(boolean goalsCreated) {
		this.goalsCreated = goalsCreated;
	}
}