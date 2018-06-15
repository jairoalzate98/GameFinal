package models;

public class Goal {
	
	private int posX;
	private int posY;
	private int idClient;
	
	public Goal(int posX, int posY, int idClient) {
		this.posX = posX;
		this.posY = posY;
		this.idClient = idClient;
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