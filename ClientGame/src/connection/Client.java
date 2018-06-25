package connection;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

import models.Manager;
import models.Player;

public class Client extends Thread{

	private Socket connection;
	private DataInputStream input;
	private DataOutputStream output;
	private boolean stop;
	public final static Logger LOGGER = Logger.getGlobal();
	private volatile boolean game;
	private boolean goals;
	private boolean players;
	private String infoGoal;
	private String infoPlayer;
	private int idClient;
	private Manager manager;
	private int seconds;
	private boolean endGame;

	public Client(String ip, int port, Manager manager) throws IOException {
		this.manager = manager;
		this.connection = new Socket(ip, port);
		LOGGER.log(Level.INFO, "Conexion iniciada en el puerto -> " + port + " y en la ip -> " + ip);
		input = new DataInputStream(connection.getInputStream());
		output = new DataOutputStream(connection.getOutputStream());
		start();
	}

	@Override
	public void run() {
		while (!stop) {
			String response = null;
			try {
				response = input.readUTF();
				if (response != null) {
					manageResponse(response);
				}
			} catch (IOException e) {
				LOGGER.log(Level.INFO, "Servidor desconectado");
				stop = true;
			}
		}
	}

	public int getIdClient() {
		return idClient;
	}

	public boolean isGame() {
		return game;
	}

	private void manageResponse(String response) throws IOException {
		if (response.equals(Request.INIT_GAME.toString())) {
			LOGGER.log(Level.INFO, "Inicio de juego");
			game = true;
		} else if(response.equals(Request.FAIL_INIT_GAME.toString())) {
			LOGGER.log(Level.INFO, "Esperando inicio de juego");
			game = false;
		} else if(response.equals(Request.SEND_INFO_GOALS.toString())) {
			infoGoal = input.readUTF();
			goals =  true;
			LOGGER.log(Level.INFO, "Recibido datos de porterias");
		} else if(response.equals(Request.SEND_INFO_PLAYERS.toString())) {
			infoPlayer = input.readUTF();
			players = true;
			LOGGER.log(Level.INFO, "Recibido datos de jugadores");
		} else if(response.equals(Request.SEND_ID_USER.toString())) {
			idClient = Integer.parseInt(input.readUTF());
			LOGGER.log(Level.INFO, "Recibido id cliente");
		} else if(response.equals(Request.RECIBE_INFO_PLAYER.toString())) {
			output.writeUTF(Request.SEND_INFO.toString());
			sendInfoPlayer();
		} else if(response.equals(Request.REFRESH_PLAYERS.toString())) {
			String info = input.readUTF();
			manager.setPositions(info);
		}else if(response.equals(Request.SEND_INFO_BALL.toString())) {
			String info = input.readUTF();
			manager.setBall(info);
			LOGGER.log(Level.INFO, "Informacion balon recibida");
		}else if (response.equals(Request.SEND_INFO_POINTS.toString())) {
			String info = input.readUTF();
			manager.setPoints(info);
			LOGGER.log(Level.INFO, "Informacion goles recibida");
		}else if(response.equals(Request.SEND_SECONDS.toString())) {
			seconds = Integer.parseInt(input.readUTF());
			LOGGER.log(Level.INFO, "Informacion segundos recibida");
		}else if(response.equals(Request.SEND_END_GAME.toString())) {
			endGame = true;
			LOGGER.log(Level.INFO, "Fin del Juego");
		}
	}

	public boolean isEndGame() {
		return endGame;
	}

	private void sendInfoPlayer() throws IOException {
		Player p = manager.getPlaterById(idClient);
		try {
			String send = p.getIdClient() + "," + p.getPosX() + "," + p.getPosY();
			output.writeUTF(send);
		}catch (Exception e) {
		}
	}

	public boolean isPlayers() {
		return players;
	}

	public int getSeconds() {
		return seconds;
	}

	public String getInfoPlayer() {
		return infoPlayer;
	}

	public boolean isGoals() {
		return goals;
	}

	public String getInfoGoal() {
		return infoGoal;
	}
}