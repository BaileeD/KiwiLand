package nz.ac.aut.ense701.gui;

import nz.ac.aut.ense701.gameModel.Game;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Created by Scott Richards on 06-May-17.
 */
public class MainMenuFrame extends JFrame
{
	private final int    FRAME_WIDTH      = 900;
	private final int    FRAME_HEIGHT     = 720;
	private final String BACKGROUND_IMAGE = "resources/Game_UI.jpg";
	private Game game;

	private CardLayout cardLayout;

	private JPanel         pnlContents;
	private MainMenuPanel  pnlMainMenu;
	private LoadGamePanel  pnlLoadGame;
	private HowToPlayPanel pnlHowToPlay;

	public MainMenuFrame(Game aGame)
	{
		game = aGame;
		initPanel();
		initFrame();
	}

	/**
	 * Initializes the frame
	 */
	private void initFrame()
	{
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setTitle("Kiwi Land - Main Menu");
		setSize(FRAME_WIDTH, FRAME_HEIGHT);
		setLocationRelativeTo(null); // centers it in the screen
		setResizable(false); // so the screen size cant be changed
		//setContentPane(new JLabel(new ImageIcon(BACKGROUND_IMAGE))); // sets the background image
		setVisible(true);

		//pack(); // so the screen is as tight as it can be
		add(pnlContents);
	}

	private void initPanel()
	{
		pnlContents = new JPanel();
		cardLayout = new CardLayout();

		pnlMainMenu = new MainMenuPanel(this);
		pnlLoadGame = new LoadGamePanel(this);
		pnlHowToPlay = new HowToPlayPanel(this);

		pnlContents.setLayout(cardLayout);

		pnlContents.add(pnlMainMenu, "1");
		pnlContents.add(pnlLoadGame, "2");
		pnlContents.add(pnlHowToPlay, "3");

		cardLayout.show(pnlContents, "1");
	}

	public CardLayout getCardLayout()
	{
		return cardLayout;
	}

	public void openMainMenu()
	{
		setTitle("Kiwi Land - Main Menu");
		getCardLayout().show(pnlContents, "1");
	}

	public void openNewGameMenu()
	{
		setVisible(false);
		createGame();
	}

	public void openLoadMenu()
	{
		setTitle("Kiwi Land - Load Game");
		getCardLayout().show(pnlContents, "2");
	}

	public void openHowToPlayMenu()
	{
		setTitle("Kiwi Land - How to Play");
		getCardLayout().show(pnlContents, "3");
	}

	public void exitGame()
	{
		System.exit(0);
	}

	public void createGame()
	{
		final GameScreenFrame gui = new GameScreenFrame(game);

		// make the GUI visible
		java.awt.EventQueue.invokeLater(new Runnable()
		{
			@Override public void run()
			{
				gui.setVisible(true);
			}
		});
	}

	class ImagePanel extends JComponent
	{
		private Image image;

		public ImagePanel(Image image)
		{
			this.image = image;
		}

		@Override protected void paintComponent(Graphics g)
		{
			super.paintComponent(g);
			g.drawImage(image, 0, 0, this);
		}
	}
}
