package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JOptionPane;
import javax.swing.Timer;

import connection.Client;
import models.Manager;
import views.MainWindow;

public class Controller implements ActionListener{
	
	private Client client;
	private MainWindow mainWindow;
	private Timer timer;
	private Manager manager;
	
	public Controller() throws IOException {
		String ip = JOptionPane.showInputDialog("Ingrese la ip", "localhost");
		int port = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el puerto", "9000"));
		manager = new Manager();
		mainWindow = new MainWindow(this);
		client = new Client(ip, port);
		timer = new Timer(1000, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				verifyGame();
			}
		});
		timer.setRepeats(true);
		timer.start();
	}
	
	private void verifyGame() {
		if (client.isGame()) {
			mainWindow.setInvisibleDialogWait();
			mainWindow.setVisible(true);
			if (client.isGoals()) {
				if (!manager.isGoalsCreated()) {
					String info = client.getInfoGoal();
					createGoals(info);
				}
			}
		}
	}

	private void createGoals(String info) {
		String[] goal = info.split(";");
		for (String string : goal) {
			String[] pos = string.split(",");
			manager.addGoal(Manager.createGoal(Integer.parseInt(pos[2]), Integer.parseInt(pos[1]), Integer.parseInt(pos[0])));
		}
		manager.setGoalsCreated(true);
		mainWindow.setGoals(manager.getGoalList());
		mainWindow.revalidate();
		mainWindow.repaint();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		switch (Events.valueOf(e.getActionCommand())) {
			
		}
	}
}