package connection;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

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

	public Client(String ip, int port) throws IOException {
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
		}
	}

	public boolean isPlayers() {
		return players;
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