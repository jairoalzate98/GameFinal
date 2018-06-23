package connection;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JOptionPane;

import models.Ball;
import models.Goal;
import models.Manager;
import models.Player;

public class Server extends Thread{

	private ServerSocket server;	
	private boolean stop;
	public final static Logger LOGGER = Logger.getGlobal();
	private ArrayList<ThreadSocket> connections;
	private boolean initGame;
	private Manager manager;

	public Server(Manager manager) throws IOException {
		this.manager = manager;
		connections = new ArrayList<>();
		int port = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el puerto", "9000"));
		server = new ServerSocket(port);
		LOGGER.log(Level.INFO, "Servidor inicado en puerto: " + port);
		start();
	}

	public boolean isInitGame() {
		return initGame;
	}

	@Override
	public void run() {	
		while (!stop) {
			Socket connection;
			try {
				connection = server.accept();
				connections.add(new ThreadSocket(connection, manager));
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
		initGame = true;
	}
	
	public void sendInfoGoals(ArrayList<Goal> goalList) throws IOException {
		for (ThreadSocket ts : connections) {
			ts.sendInfoGoals(goalList);
		}
	}

	public void sendInfoPlayers(ArrayList<Player> playerList) throws IOException {
		for (ThreadSocket ts : connections) {
			ts.sendInfoPlayers(playerList);
		}
	}
	
	public void sendPetitionGetInfoPlayer() throws IOException {
		for (ThreadSocket ts : connections) {
			ts.sendPetitionGetInfoPlayer();
		}
	}

	public ArrayList<String> getPosition() {
		ArrayList<String> info = new ArrayList<>();
		for (ThreadSocket ts : connections) {
			info.add(ts.getInfo());
		}
		return info;
	}

	public void refreshPlayers(ArrayList<Player> playerList) throws IOException {
		for (ThreadSocket ts : connections) {
			ts.refreshPlayers(playerList);
		}
	}

	public void sendInfoBall(Ball ball) throws IOException {
		for (ThreadSocket ts : connections) {
			ts.sendInfoBall(ball);
		}
	}
}