package connection;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.logging.Level;

import models.Ball;
import models.Goal;
import models.Manager;
import models.Player;

public class ThreadSocket extends Thread{

	private Socket connection;
	private DataInputStream input;
	private DataOutputStream output;
	private boolean stop;
	private int idClient;
	private static int count;
	private String info;
	private Manager manager; 

	public ThreadSocket(Socket socket, Manager manager) throws IOException {
		this.connection = socket;
		this.manager = manager;
		idClient = ++count;
		input = new DataInputStream(socket.getInputStream());
		output = new DataOutputStream(socket.getOutputStream());
		output.writeUTF(Request.SEND_ID_USER.toString());
		output.writeUTF(String.valueOf(idClient));
		start();
	}

	@Override
	public void run() {
		while (!stop) {
			try {
				String request = input.readUTF();
				if (request != null) {
					manageRequest(request);
				}
			} catch (IOException e) {
				Server.LOGGER.log(Level.INFO, "Usuario con direccion ip = " + connection.getInetAddress().getHostAddress() + " desconectado");
				stop = true;
			}
		}
	}

	private void manageRequest(String request) throws IOException {
		if (request.equals(Request.SEND_INFO.toString())) {
			info = input.readUTF();
			try {
				if (!info.isEmpty()) {
					manager.setPositions(info);
				}
			}catch (Exception e) {
			}
		}
	}

	public String getInfo() {
		return info;
	}

	public boolean isStop() {
		return stop;
	}

	public void initGame() throws IOException {
		output.writeUTF(Request.INIT_GAME.toString());
	}

	public void failInitGame() throws IOException {
		output.writeUTF(Request.FAIL_INIT_GAME.toString());
	}

	public int getIdClient() {
		return idClient;
	}

	public void sendInfoGoals(ArrayList<Goal> goalList) throws IOException {
		output.writeUTF(Request.SEND_INFO_GOALS.toString());
		String send = "";
		for (Goal goal : goalList) {
			send += goal.getPosX() + "," + goal.getPosY() + "," + goal.getIdClient() + ";";
		}
		output.writeUTF(send);
	}

	public void sendInfoPlayers(ArrayList<Player> playerList) throws IOException {
		output.writeUTF(Request.SEND_INFO_PLAYERS.toString());
		String send = "";
		for (Player player : playerList) {
			send += player.getPosX() + "," + player.getPosY() + "," + player.getIdClient() + ";";
		}
		output.writeUTF(send);
	}

	public void sendPetitionGetInfoPlayer() throws IOException {
		output.writeUTF(Request.RECIBE_INFO_PLAYER.toString());
	}

	public void refreshPlayers(ArrayList<Player> playerList) throws IOException {
		output.writeUTF(Request.REFRESH_PLAYERS.toString());
		String send = "";
		for (Player player : playerList) {
			send += player.getIdClient() + "," + player.getPosX() + "," + player.getPosY() + ";";
		}
		output.writeUTF(send);
	}

	public void sendInfoBall(Ball ball) throws IOException {
		output.writeUTF(Request.SEND_INFO_BALL.toString());
		String send = ball.getPosX() + "," + ball.getPosY();
		output.writeUTF(send);
	}

	public void sendInfoPoints(ArrayList<Goal> goalList) throws IOException {
		output.writeUTF(Request.SEND_INFO_POINTS.toString());
		String send = "";
		for (Goal goal : goalList) {
			send += goal.getIdClient() + "," + goal.getGoals() + ";";
		}
		output.writeUTF(send);
	}

	public void sendSeconds(int seconds) throws IOException {
		output.writeUTF(Request.SEND_SECONDS.toString());
		output.writeUTF(String.valueOf(seconds));
	}

	public void sendEndGame() throws IOException {
		output.writeUTF(Request.SEND_END_GAME.toString());
	}
}