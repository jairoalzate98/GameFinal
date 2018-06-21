package views;

import java.awt.BorderLayout;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

import controllers.Controller;
import models.Goal;

public class MainWindow extends JFrame{

	private static final String TITLE_GAME = "Football";
	private static final long serialVersionUID = 1L;
	private JDialogWaitInitGame jDialogWaitInitGame;
	private JPanelGame jPanelGame;

	public MainWindow(Controller controller) {
		setTitle(TITLE_GAME);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(600, 600);
		setLocationRelativeTo(null);
		setIconImage(new ImageIcon(getClass().getResource("/img/icon.png")).getImage());
		jDialogWaitInitGame = new JDialogWaitInitGame(this);
		jDialogWaitInitGame.setVisible(true);
		jPanelGame = new JPanelGame();
		add(jPanelGame, BorderLayout.CENTER);
	}
	
	public void setGoals(ArrayList<Goal> goalList) {
		jPanelGame.setGoalList(goalList);
	}
	
	public void setInvisibleDialogWait() {
		jDialogWaitInitGame.setVisible(false);
	}
}