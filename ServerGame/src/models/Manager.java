package models;

import java.util.ArrayList;

import connection.ThreadSocket;

public class Manager {

	public static final int SIZE_GOAL_VECTOR = 4;
	private ArrayList<Goal> goalList;
	private boolean createdGoals;
	
	public Manager() {
		goalList = new ArrayList<>();
	}
	
	public void addGoal(Goal g) {
		goalList.add(g);
	}
	
	public static Goal createGoal(int idClient, int posX, int posY) {
		return new Goal(posX, posY, idClient);
	}

	public void initGoals(ArrayList<ThreadSocket> sockets) {
		for (Goal goal : goalList) {
			goalList.add(new Goal(goal.getPosX(), goal.getPosY(), goal.getIdClient()));
		}
		createdGoals = true;
	}

	public ArrayList<Goal> getGoalList() {
		return goalList;
	}

	public boolean isCreatedGoals() {
		return createdGoals;
	}
}