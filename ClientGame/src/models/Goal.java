package models;

public class Goal {

	private int posX;
	private int posY;
	private int idClient;
	private int goals;
	
	public Goal(int idClient, int posX, int posY) {
		this.idClient = idClient;
		this.posX = posX;
		this.posY = posY;
	}

	public int getPosX() {
		return posX;
	}

	public int getPosY() {
		return posY;
	}

	public int getIdClient() {
		return idClient;
	}

	public int getGoals() {
		return goals;
	}

	public void setGoals(int goals) {
		this.goals = goals;
	}
}