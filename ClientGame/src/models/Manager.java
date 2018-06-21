package models;

import java.util.ArrayList;

public class Manager {

	private ArrayList<Goal> goalList;
	private ArrayList<Player> playerList;
	private boolean goalsCreated;
	private boolean playersCreated;
	
	public Manager() {
		playerList = new ArrayList<>();
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
	
	public static Player createPlayer(int idClient, int posY, int posX) {
		return new Player(idClient, posX, posY);
	}
	
	public void addPlayer(Player player) {
		playerList.add(player);
	}

	public boolean isGoalsCreated() {
		return goalsCreated;
	}

	public void setGoalsCreated(boolean goalsCreated) {
		this.goalsCreated = goalsCreated;
	}

	public boolean isPlayersCreated() {
		return playersCreated;
	}

	public void setPlayersCreated(boolean playersCreated) {
		this.playersCreated = playersCreated;
	}

	public ArrayList<Player> getPlayerList() {
		return playerList;
	}
}