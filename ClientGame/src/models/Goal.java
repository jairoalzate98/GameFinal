package models;

public class Goal {

	private int posX;
	private int posY;
	private int idClient;
	
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
}