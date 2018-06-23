package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;

import javax.swing.JOptionPane;
import javax.swing.Timer;

import connection.Client;
import models.Manager;
import models.Player;
import views.MainWindow;

public class Controller implements ActionListener, KeyListener{
	
	private Client client;
	private MainWindow mainWindow;
	private Timer timer;
	private Manager manager;
	
	public Controller() throws IOException {
		String ip = JOptionPane.showInputDialog("Ingrese la ip", "localhost");
		int port = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el puerto", "9000"));
		manager = new Manager();
		mainWindow = new MainWindow(this);
		client = new Client(ip, port, manager);
		timer = new Timer(5, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				verifyGame();
				mainWindow.setPlayers(manager.getPlayerList());
				mainWindow.setBall(manager.getBall());
				mainWindow.revalidate();
				mainWindow.repaint();
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
			if (client.isPlayers()) {
				if (!manager.isPlayersCreated()) {
					String info = client.getInfoPlayer();
					createPlayers(info);
				}
			}
		}
	}

	private void createPlayers(String info) {
		String[] player = info.split(";");
		for (String string : player) {
			String[] pos = string.split(",");
			try {
				manager.addPlayer(Manager.createPlayer(Integer.parseInt(pos[2]), Integer.parseInt(pos[1]), Integer.parseInt(pos[0])));
			}catch (Exception e) {
				// TODO: handle exception
			}
		}
		manager.setPlayersCreated(true);
		mainWindow.setPlayers(manager.getPlayerList());
		mainWindow.revalidate();
		mainWindow.repaint();
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

	@Override
	public void keyTyped(KeyEvent e) {
		if (KeyEvent.VK_UP == e.getKeyCode()) {
			manager.moveUp(client.getIdClient());
		} else if(KeyEvent.VK_DOWN == e.getKeyCode()){
			manager.moveDown(client.getIdClient());
		}else if(KeyEvent.VK_RIGHT == e.getKeyCode()){
			manager.moveRight(client.getIdClient());
		}else if(KeyEvent.VK_LEFT == e.getKeyCode()){
			manager.moveLeft(client.getIdClient());
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (KeyEvent.VK_UP == e.getKeyCode()) {
			manager.moveUp(client.getIdClient());
		} else if(KeyEvent.VK_DOWN == e.getKeyCode()){
			manager.moveDown(client.getIdClient());
		}else if(KeyEvent.VK_RIGHT == e.getKeyCode()){
			manager.moveRight(client.getIdClient());
		}else if(KeyEvent.VK_LEFT == e.getKeyCode()){
			manager.moveLeft(client.getIdClient());
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		if (KeyEvent.VK_UP == e.getKeyCode()) {
			manager.moveUp(client.getIdClient());
		} else if(KeyEvent.VK_DOWN == e.getKeyCode()){
			manager.moveDown(client.getIdClient());
		}else if(KeyEvent.VK_RIGHT == e.getKeyCode()){
			manager.moveRight(client.getIdClient());
		}else if(KeyEvent.VK_LEFT == e.getKeyCode()){
			manager.moveLeft(client.getIdClient());
		}
	}
	
	public Player getPlayerById(int id) {
		return manager.getPlaterById(id);
	}
}