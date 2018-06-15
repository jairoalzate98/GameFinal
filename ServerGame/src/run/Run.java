package run;

import java.io.IOException;

import controllers.Controller;

public class Run {

	public static void main(String[] args) {
		try {
			new Controller();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
