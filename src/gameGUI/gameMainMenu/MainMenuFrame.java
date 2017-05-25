package gameGUI.gameMainMenu;

import gameGUI.gameInGame.GameScreenFrame;
import gameModel.Game;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Scott Richards on 06-May-17.
 */
public class MainMenuFrame extends JFrame
{
	private final int FRAME_WIDTH  = 900;
	private final int FRAME_HEIGHT = 720;

	private String BACKGROUND_TEXT  = "resources/Backgrounds/Main_Menu_UI.jpg";
	private String BACKGROUND_PLAIN = "resources/Backgrounds/Menu_UI.jpg";
	private JLabel backgroundLbl;
	private Game   game;

	private CardLayout cardLayout;

	private JPanel         pnlContents;
	public  MainMenuPanel  pnlMainMenu;
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
		backgroundLbl = new JLabel();
		backgroundLbl.setIcon(new ImageIcon(BACKGROUND_TEXT));
		setContentPane(backgroundLbl); // sets the background image
		setVisible(true);
		setLayout(new FlowLayout());
		setSize(FRAME_WIDTH, FRAME_HEIGHT);
		setLocationRelativeTo(null); // centers it in the screen
		setResizable(false); // so the screen size cant be changed

		//pack(); // so the screen is as tight as it can be
		add(pnlContents);
	}

	private void initPanel()
	{
		pnlContents = new JPanel();
		cardLayout = new CardLayout();

		pnlMainMenu = new MainMenuPanel(this);
		pnlLoadGame = new LoadGamePanel(this, game);
		pnlHowToPlay = new HowToPlayPanel(this);

		pnlContents.setOpaque(false);
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
		backgroundLbl.setIcon(new ImageIcon(BACKGROUND_TEXT));
		setTitle("Kiwi Land - Main Menu");
		getCardLayout().show(pnlContents, "1");
	}

	public void openNewGameMenu()
	{
		this.createGame(1);
	}

	public void openSaveGameMenu(int level)
	{
		this.createGame(level);
	}

	public void openLoadMenu()
	{
		pnlLoadGame.loadAllGameSaves();
		backgroundLbl.setIcon(new ImageIcon(BACKGROUND_PLAIN));
		setTitle("Kiwi Land - Load Game");
		getCardLayout().show(pnlContents, "2");
	}

	public void openHowToPlayMenu()
	{
		backgroundLbl.setIcon(new ImageIcon(BACKGROUND_PLAIN));
		setTitle("Kiwi Land - How to Play");
		getCardLayout().show(pnlContents, "3");
	}

	public void exitGame()
	{
		System.exit(0);
	}

	public void createGame(int level)
	{
		final GameScreenFrame gui = new GameScreenFrame(game, this);
		setVisible(false);
		game.setCurrentLevelNumber(level);
		game.createNewGame();

		// make the GUI visible
		EventQueue.invokeLater(new Runnable()
		{
			@Override public void run()
			{
				gui.setVisible(true);
			}
		});
	}
}
