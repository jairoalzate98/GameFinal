package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.logging.Level;

import javax.swing.Timer;

import connection.Server;
import models.Manager;

public class Controller {

	private Server server;
	private Manager manager;
	private Timer timer;

	public Controller() throws IOException {
		manager = new Manager();
		server = new Server(manager);
		timer = new Timer(10, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				verifyInitGame();
				sendPetitionGetInfoPlayers();
				sendInfoBall();
				sendInfoGoals();
			}
		});
		timer.start();
	}

	public void sendInfoGoals() {
		try {
			server.sendInfoPoints(manager.getGoalList());
		} catch (IOException e) {
			Server.LOGGER.log(Level.INFO, "Error envio goles marcados");
		}
	}

	public void sendInfoBall() {
		try {
			server.sendInfoBall(manager.getBall());
		} catch (IOException e) {
			Server.LOGGER.log(Level.INFO, "Envio de balon fallido");
		}
	}

	private void sendPetitionGetInfoPlayers() {
		try {
			server.sendPetitionGetInfoPlayer();
			server.refreshPlayers(manager.getPlayerList());
		} catch (IOException e) {
			Server.LOGGER.log(Level.INFO, "Datos erroneos");
		}
	}

	private void verifyInitGame() {
		if (server.isInitGame()) {
			if (!manager.isCreatedGoals()) {
				manager.initGoals(server.getConnections());
				try {
					server.sendInfoGoals(manager.getGoalList());
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (!manager.isCreatedPlayers()) {
				manager.initPlayer(server.getConnections());
				try {
					server.sendInfoPlayers(manager.getPlayerList());
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}