package connection;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.logging.Level;

import models.Goal;

public class ThreadSocket extends Thread{
	
	private Socket connection;
	private DataInputStream input;
	private DataOutputStream output;
	private boolean stop;
	private int idClient;
	private static int count;

	public ThreadSocket(Socket socket) throws IOException {
		this.connection = socket;
		idClient = ++count;
		input = new DataInputStream(socket.getInputStream());
		output = new DataOutputStream(socket.getOutputStream());
		start();
	}
	
	@Override
	public void run() {
		while (!stop) {
			try {
				String request = input.readUTF();
				if (request != null) {
					manageRequest(request);
				}
			} catch (IOException e) {
				Server.LOGGER.log(Level.INFO, "Usuario con direccion ip = " + connection.getInetAddress().getHostAddress() + " desconectado");
				stop = true;
			}
		}
	}

	public boolean isStop() {
		return stop;
	}

	private void manageRequest(String request) {
		
	}
	
	public void initGame() throws IOException {
		output.writeUTF(Request.INIT_GAME.toString());
	}
	
	public void failInitGame() throws IOException {
		output.writeUTF(Request.FAIL_INIT_GAME.toString());
	}

	public int getIdClient() {
		return idClient;
	}

	public void sendInfoGoals(ArrayList<Goal> goalList) throws IOException {
		output.writeUTF(Request.SEND_INFO_GOALS.toString());
		String send = "";
		for (Goal goal : goalList) {
			send += goal.getPosX() + "," + goal.getPosY() + "," + goal.getIdClient() + ";";
		}
		output.writeUTF(send);
	}
}