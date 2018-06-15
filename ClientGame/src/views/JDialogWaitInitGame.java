package views;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JLabel;

public class JDialogWaitInitGame extends JDialog{

	public static final Font FONT_UBUNTU = new Font("Ubuntu", Font.BOLD, 30);
	public static final String TEXT_INIT_GAME = "<html>Esperando jugadores <br/> para el inicio del juego</html>";
	public static final String LOADING_GIF = "/gif/loading.gif";
	public static final int WIDTH_AND_HEIGHT = 400;
	private static final long serialVersionUID = 1L;

	public JDialogWaitInitGame(MainWindow mainWindow) {
		super(mainWindow, false);
		setSize(WIDTH_AND_HEIGHT, WIDTH_AND_HEIGHT);
		setLocationRelativeTo(mainWindow);
		getContentPane().setBackground(Color.WHITE);
		setLayout(new FlowLayout());
		JLabel jlGif = new JLabel(new ImageIcon(getClass().getResource(LOADING_GIF)));
		jlGif.setPreferredSize(new Dimension(250, 200));
		add(jlGif);
		JLabel jlText = new JLabel(TEXT_INIT_GAME);
		jlText.setFont(FONT_UBUNTU);
		add(jlText);
	}
}