package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JOptionPane;
import javax.swing.Timer;

import connection.Client;
import views.MainWindow;

public class Controller implements ActionListener{
	
	private Client client;
	private MainWindow mainWindow;
	private Timer timer;
	
	public Controller() throws IOException {
		String ip = JOptionPane.showInputDialog("Ingrese la ip", "localhost");
		int port = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el puerto", "9000"));
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
		}else {
			mainWindow.setVisibleDialogWait();
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		switch (Events.valueOf(e.getActionCommand())) {
			
		}
	}
}