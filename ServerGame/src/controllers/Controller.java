package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.Timer;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactoryConfigurationError;

import connection.Server;
import models.Manager;
import persistence.FileManager;

public class Controller {
	
	private Server server;
	private Manager manager;
	private Timer timer;
	private FileManager fileManager;
	
	public Controller() throws IOException {
		server = new Server();
		manager = new Manager();
		fileManager = new FileManager();
		timer = new Timer(1000, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				verifyInitGame();
				try {
					if (!manager.getGoalList().isEmpty()) {
						fileManager.writeReport(manager.getGoalList());
					}
				} catch (ParserConfigurationException | TransformerFactoryConfigurationError
						| TransformerException e1) {
					e1.printStackTrace();
				}
			}
		});
		timer.start();
	}

	private void verifyInitGame() {
		if (server.isInitGame()) {
			if (!manager.isCreatedGoals()) {
				manager.initGoals(server.getConnections());
			}
			try {
				server.sendInfoGoals();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}