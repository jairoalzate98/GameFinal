package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JOptionPane;

import connection.Client;
import views.MainWindow;

public class Controller implements ActionListener{
	
	private Client client;
	private MainWindow mainWindow;
	
	public Controller() throws IOException {
		String ip = JOptionPane.showInputDialog("Ingrese la ip");
		int port = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el puerto"));
		mainWindow = new MainWindow(this);
		client = new Client(ip, port, this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		switch (Events.valueOf(e.getActionCommand())) {
			
		}
	}
	
	public void waitInitGame() {
		mainWindow.setVisibleDialogWait();
	}
}