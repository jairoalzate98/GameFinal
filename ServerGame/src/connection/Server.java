package connection;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JOptionPane;

public class Server extends Thread{

	private ServerSocket server;	
	private boolean stop;
	public final static Logger LOGGER = Logger.getGlobal();
	private ArrayList<ThreadSocket> connections;

	public Server() throws IOException {
		connections = new ArrayList<>();
		int port = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el puerto", "9000"));
		server = new ServerSocket(port);
		LOGGER.log(Level.INFO, "Servidor inicado en puerto: " + port);
		start();
	}

	@Override
	public void run() {	
		while (!stop) {
			Socket connection;
			try {
				connection = server.accept();
				connections.add(new ThreadSocket(connection));
				LOGGER.log(Level.INFO, "Conexion aceptada: " + connection.getInetAddress().getHostAddress());
				initGame();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public ArrayList<ThreadSocket> getConnections() {
		return connections;
	}
	
	public void initGame() {
		int clients = getNumberOfUser();
		if (clients == 4) {
			initGameCorrect();
		} else {
			initGameFail();
		}
	}

	private int getNumberOfUser() {
		int clients = 0;
		for (ThreadSocket ts : connections) {
			if (!ts.isStop()) {
				clients++;
			}
		}
		return clients;
	}

	private void initGameFail() {
		for (ThreadSocket ts : connections) {
			if (!ts.isStop()) {
				try {
					ts.failInitGame();
				} catch (IOException e) {
					LOGGER.log(Level.INFO, "Fallo en inicio de juego");
				}
			}
		}
	}

	private void initGameCorrect() {
		for (ThreadSocket ts : connections) {
			if (!ts.isStop()) {
				try {
					ts.initGame();
				} catch (IOException e) {
					LOGGER.log(Level.INFO, "Fallo en inicio de juego");
				}
			}
		}
	}
}