package models;

import java.util.ArrayList;

import connection.ThreadSocket;

public class Manager {

	public static final int SIZE_GOAL_VECTOR = 4;
	private ArrayList<Goal> goalList;
	private boolean createdGoals;
	private boolean createdPlayers;
	private ArrayList<Player> playerList;
	private Ball ball;

	public Manager() {
		createdGoals = false;
		goalList = new ArrayList<>();
		playerList = new ArrayList<>();
		ball = new Ball(300, 300, this);
	}

	public void setPlayerList(ArrayList<Player> playerList) {
		this.playerList = playerList;
	}

	public void addGoal(Goal g) {
		goalList.add(g);
	}

	public void addPlayer(Player p) {
		playerList.add(p);
	}

	public static Goal createGoal(int idClient, int posX, int posY) {
		return new Goal(posX, posY, idClient);
	}

	public static Player createPlayer(int idClient, int posX, int posY) {
		return new Player(idClient, posX, posY);
	}

	public void initGoals(ArrayList<ThreadSocket> sockets) {
		addGoal(createGoal(sockets.get(0).getIdClient(), 0, 260));
		addGoal(createGoal(sockets.get(1).getIdClient(), 260, 0));
		addGoal(createGoal(sockets.get(2).getIdClient(), 260, 600));
		addGoal(createGoal(sockets.get(3).getIdClient(), 600, 260));
		createdGoals = true;
	}

	public void initPlayer(ArrayList<ThreadSocket> sockets) {
		addPlayer(createPlayer(sockets.get(0).getIdClient(), 50, 260));
		addPlayer(createPlayer(sockets.get(1).getIdClient(), 260, 50));
		addPlayer(createPlayer(sockets.get(2).getIdClient(), 260, 550));
		addPlayer(createPlayer(sockets.get(3).getIdClient(), 550, 260));
		createdPlayers = true;
	}

	public boolean isCreatedPlayers() {
		return createdPlayers;
	}

	public ArrayList<Goal> getGoalList() {
		return goalList;
	}

	public boolean isCreatedGoals() {
		return createdGoals;
	}

	public ArrayList<Player> getPlayerList() {
		return playerList;
	}

	public void setPositions(String info) {
		String[] information = info.split(",");
		for (Player player : playerList) {
			if (player.getIdClient() == Integer.parseInt(information[0])) {
				player.setPosX(Integer.parseInt(information[1]));
				player.setPosY(Integer.parseInt(information[2]));
			}
		}
	}

	public Ball getBall() {
		return ball;
	}

	public void setBall(Ball ball) {
		this.ball = ball;
	}
}