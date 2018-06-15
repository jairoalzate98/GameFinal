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
		int port = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el puerto"));
		server = new ServerSocket(port);
		start();
		LOGGER.log(Level.INFO, "Servidor inicado en puerto: " + port);
	}

	@Override
	public void run() {	
		while (!stop) {
			Socket connection;
			try {
				connection = server.accept();
				connections.add(new ThreadSocket(connection, this));
				LOGGER.log(Level.INFO, "Conexion aceptada: " + connection.getInetAddress().getHostAddress());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public ArrayList<ThreadSocket> getConnections() {
		return connections;
	}
}