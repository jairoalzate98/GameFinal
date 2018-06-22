package views;

import java.awt.BorderLayout;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

import controllers.Controller;
import models.Goal;
import models.Player;

public class MainWindow extends JFrame{

	private static final String TITLE_GAME = "Football";
	private static final long serialVersionUID = 1L;
	private JDialogWaitInitGame jDialogWaitInitGame;
	private JPanelGame jPanelGame;

	public MainWindow(Controller controller) {
		setTitle(TITLE_GAME);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(660, 680);
		setLocationRelativeTo(null);
		setIconImage(new ImageIcon(getClass().getResource("/img/icon.png")).getImage());
		jDialogWaitInitGame = new JDialogWaitInitGame(this);
		jDialogWaitInitGame.setVisible(true);
		jPanelGame = new JPanelGame(controller);
		jPanelGame.setFocusable(true);
		add(jPanelGame, BorderLayout.CENTER);
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
}