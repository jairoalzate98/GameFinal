package connection;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

import controllers.Controller;

public class Client extends Thread{
	
	private Socket connection;
	private DataInputStream input;
	private DataOutputStream output;
	private boolean stop;
	private Controller controller;
	public final static Logger LOGGER = Logger.getGlobal();
	
	public Client(String ip, int port, Controller controller) throws IOException {
		this.controller = controller;
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

	private void manageResponse(String response) {
		if (response.equals(Request.INIT_GAME.toString())) {
			System.out.println("Inicio de juego");
		} else if(response.equals(Request.FAIL_INIT_GAME.toString())) {
			System.out.println("Esperando inicio de juego");
			controller.waitInitGame();
		}
	}
}