package models;

import java.util.ArrayList;

public class Manager {

	private ArrayList<Goal> goalList;
	private ArrayList<Player> playerList;
	private boolean goalsCreated;
	private boolean playersCreated;
	private Ball ball;
	
	public Manager() {
		playerList = new ArrayList<>();
		goalList = new ArrayList<>();
		ball = new Ball(0, 0);
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

	public Player moveUp(int idClient) {
		Player p = null;
		for (Player player : playerList) {
			if (player.getIdClient() == idClient) {
				player.moveUp();
				p = player;
			}
		}
		return p;
	}

	public Player moveDown(int idClient) {
		Player p = null;
		for (Player player : playerList) {
			if (player.getIdClient() == idClient) {
				player.moveDown();
				p = player;
			}
		}
		return p;
	}

	public Player moveRight(int idClient) {
		Player p = null;
		for (Player player : playerList) {
			if (player.getIdClient() == idClient) {
				player.moveRight();
				p = player;
			}
		}
		return p;
	}

	public Player moveLeft(int idClient) {
		Player p = null;
		for (Player player : playerList) {
			if (player.getIdClient() == idClient) {
				player.moveLeft();
				p = player;
			}
		}
		return p;
	}

	public Player getPlaterById(int idClient) {
		Player p = null;
		for (Player player: playerList) {
			if (player.getIdClient() == idClient) {
				p = player;
			}
		}
		return p;
	}

	public void setPositions(String info) {
		String [] in = info.split(";");
		for (String string : in) {
			String[] information = string.split(",");
			for (Player player : playerList) {
				if (player.getIdClient() == Integer.parseInt(information[0])) {
					player.setPosX(Integer.parseInt(information[1]));
					player.setPosY(Integer.parseInt(information[2]));
				}
			}
		}
	}

	public Ball getBall() {
		return ball;
	}

	public void setBall(String info) {
		ball.setPosX(Float.parseFloat(info.split(",")[0]));
		ball.setPosY(Float.parseFloat(info.split(",")[1]));
	}

	public void setPoints(String info) {
		String[] goals = info.split(";");
		for (String string : goals) {
			String[] points = string.split(",");
			for (Goal goal : goalList) {
				if (goal.getIdClient() == Integer.parseInt(points[0])) {
					goal.setGoals(Integer.parseInt(points[1]));
				}
		}
		}
	}
}