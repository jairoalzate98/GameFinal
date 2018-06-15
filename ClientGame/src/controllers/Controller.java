package controllers;

import java.io.IOException;

import javax.swing.JOptionPane;

import connection.Client;

public class Controller {
	
	private Client client;
	
	public Controller() throws IOException {
		String ip = JOptionPane.showInputDialog("Ingrese la ip");
		int port = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el puerto"));
		client = new Client(ip, port);
	}
}