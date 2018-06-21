package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.Timer;

import connection.Server;
import models.Manager;

public class Controller {

	private Server server;
	private Manager manager;
	private Timer timer;

	public Controller() throws IOException {
		server = new Server();
		manager = new Manager();
		timer = new Timer(1000, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				verifyInitGame();
			}
		});
		timer.start();
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
		}
	}
}