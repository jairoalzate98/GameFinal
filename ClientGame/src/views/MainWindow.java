package views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

import controllers.Controller;
import models.Ball;
import models.Goal;
import models.Player;

public class MainWindow extends JFrame{

	private static final String TITLE_GAME = "Football";
	private static final long serialVersionUID = 1L;
	private JDialogWaitInitGame jDialogWaitInitGame;
	private JPanelGame jPanelGame;
	private JDialogEndGame dialogEndGame;

	public MainWindow(Controller controller) {
		setTitle(TITLE_GAME);
		setBackground(Color.WHITE);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(1060, 680);
		setLocationRelativeTo(null);
		setIconImage(new ImageIcon(getClass().getResource("/img/icon.png")).getImage());
		jDialogWaitInitGame = new JDialogWaitInitGame(this);
		jDialogWaitInitGame.setVisible(true);
		dialogEndGame = new JDialogEndGame(this);
		jPanelGame = new JPanelGame(controller);
		jPanelGame.setFocusable(true);
		add(jPanelGame, BorderLayout.CENTER);
	}
	
	public void setWin(boolean t) {
		dialogEndGame.setWin(t);
	}
	
	public void setGoals(ArrayList<Goal> goalList) {
		jPanelGame.removeAll();
		jPanelGame.setGoalList(goalList);
	}
	
	public void setPlayers(ArrayList<Player> playerList) {
		jPanelGame.removeAll();
		jPanelGame.setPlayerList(playerList);
	}
	
	public void setInvisibleDialogWait() {
		jDialogWaitInitGame.setVisible(false);
	}
	
	public void setBall(Ball ball) {
		jPanelGame.setBall(ball);
	}
	
	public void setSeconds(int seconds) {
		jPanelGame.setSeconds(seconds);
	}

	public void setVisibleDialogEndGame() {
		dialogEndGame.setVisible(true);
	}
}