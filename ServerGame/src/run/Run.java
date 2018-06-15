package run;

import java.io.IOException;

import connection.Server;

public class Run {

	public static void main(String[] args) {
		try {
			new Server();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
