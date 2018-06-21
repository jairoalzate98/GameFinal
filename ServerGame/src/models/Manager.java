package models;

import java.util.ArrayList;

import connection.ThreadSocket;

public class Manager {

	public static final int SIZE_GOAL_VECTOR = 4;
	private ArrayList<Goal> goalList;
	private boolean createdGoals;
	
	public Manager() {
		createdGoals = false;
		goalList = new ArrayList<>();
	}
	
	public void addGoal(Goal g) {
		goalList.add(g);
	}
	
	public static Goal createGoal(int idClient, int posX, int posY) {
		return new Goal(posX, posY, idClient);
	}

	public void initGoals(ArrayList<ThreadSocket> sockets) {
		addGoal(createGoal(sockets.get(0).getIdClient(), 0, 260));
		addGoal(createGoal(sockets.get(1).getIdClient(), 260, 0));
		addGoal(createGoal(sockets.get(2).getIdClient(), 260, 600));
		addGoal(createGoal(sockets.get(3).getIdClient(), 600, 260));
		createdGoals = true;
	}

	public ArrayList<Goal> getGoalList() {
		return goalList;
	}

	public boolean isCreatedGoals() {
		return createdGoals;
	}
}