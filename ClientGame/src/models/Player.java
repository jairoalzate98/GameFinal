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

	public void moveUp() {
		if (posY > 0) {
			posY -= 10;
		}
	}

	public void moveDown() {
		if (posY < 600) {
			posY += 10;
		}
	}

	public void moveRight() {
		if (posX < 600) {
			posX += 10;
		}
	}

	public void moveLeft() {
		if (posX > 0) {
			posX -= 10;
		}
	}

	@Override
	public String toString() {
		return "Player [idClient=" + idClient + ", posX=" + posX + ", posY=" + posY + "]";
	}
}