package views;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.ImageIcon;
import javax.swing.JDialog;

public class JDialogEndGame extends JDialog {
	
	public static final Font FONT_UBUNTU = new Font("Ubuntu", Font.BOLD, 27);
	private static final long serialVersionUID = 1L;
	private boolean win;

	public JDialogEndGame(MainWindow mainWindow) {
		super(mainWindow);
		setSize(400, 400);
		setLocationRelativeTo(mainWindow);
		getContentPane().setBackground(Color.WHITE);
	}

	public void setWin(boolean win) {
		this.win = win;
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		this.removeAll();
		if (win) {
			g.drawImage(new ImageIcon(getClass().getResource("/img/victory.png")).getImage(), 40, 40, 300, 200, this);
		}else {
			g.drawImage(new ImageIcon(getClass().getResource("/img/gameOverText.png")).getImage(), 40, 40, 300, 200, this);
		}
	}
}