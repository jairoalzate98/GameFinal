package models;

public class Player {

	private int idClient;
	private int posX;
	private int posY;

	public Player(int idClient, int posX, int posY) {
		this.idClient = idClient;
		this.posX = posX;
		this.posY = posY;
	}

	public int getIdClient() {
		return idClient;
	}

	public int getPosX() {
		return posX;
	}

	public int getPosY() {
		return posY;
	}

	public void setPosX(int posX) {
		this.posX = posX;
	}

	public void setPosY(int posY) {
		this.posY = posY;
	}
}