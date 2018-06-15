package views;

import javax.swing.JFrame;

import controllers.Controller;

public class MainWindow extends JFrame{

	private static final String TITLE_GAME = "Football";
	private static final long serialVersionUID = 1L;
	private JDialogWaitInitGame jDialogWaitInitGame;

	public MainWindow(Controller controller) {
		setTitle(TITLE_GAME);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setExtendedState(MAXIMIZED_BOTH);
		jDialogWaitInitGame = new JDialogWaitInitGame(this);
	}
	
	public void setVisibleDialogWait() {
		jDialogWaitInitGame.setVisible(true);
	}
	
	public void setInvisibleDialogWait() {
		jDialogWaitInitGame.setVisible(false);
	}
}