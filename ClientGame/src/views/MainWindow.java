package views;

import javax.swing.ImageIcon;
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
		setIconImage(new ImageIcon(getClass().getResource("/img/icon.png")).getImage());
		jDialogWaitInitGame = new JDialogWaitInitGame(this);
		jDialogWaitInitGame.setVisible(true);
	}
	
	public void setVisibleDialogWait() {
		setSize(500, 500);
	}
	
	public void setInvisibleDialogWait() {
		jDialogWaitInitGame.setVisible(false);
	}
}