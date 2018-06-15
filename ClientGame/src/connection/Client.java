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

	public boolean isGame() {
		return game;
	}

	private void manageResponse(String response) {
		if (response.equals(Request.INIT_GAME.toString())) {
			LOGGER.log(Level.INFO, "Inicio de juego");
			game = true;
		} else if(response.equals(Request.FAIL_INIT_GAME.toString())) {
			LOGGER.log(Level.INFO, "Esperando inicio de juego");
			game = false;
		} else if(response.equals(Request.SEND_INFO_GOALS.toString())) {
			LOGGER.log(Level.INFO, "Recibido datos de porterias");
		}
	}
}