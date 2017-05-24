package gameGUI.gameInGame;

import gameGUI.gameMainMenu.HowToPlayPanel;
import gameGUI.gameMainMenu.LoadGamePanel;
import gameGUI.gameMainMenu.MainMenuFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * This class is the frame that opens the in game menu.
 * It has options for save game, load game, how to play, return to game,
 * and quit to main menu
 * <p>
 * This frame should also pause the game
 * <p>
 * Created by Scott Richards on 09-May-17.
 */
public class GameMenuFrame extends JFrame
{
	private final int FRAME_WIDTH  = 200;
	private final int FRAME_HEIGHT = 270;

	GameScreenFrame gameFrame;        // so it can toggle this frame to be clickable after you return to game
	MainMenuFrame   mainMenuFrame;    // so you bring this frame back to be visible after quitting to menu

	private JPanel  pnlButtons;
	private JButton btnSaveGame;
	private JButton btnLoadGame;
	private JButton btnHowToPlay;
	private JButton btnBack;
	private JButton btnExit;

	public GameMenuFrame(GameScreenFrame theFrame, MainMenuFrame mainMenuFrame)
	{
		gameFrame = theFrame;
		this.mainMenuFrame = mainMenuFrame;

		initPanel();
		initFrame();
		initButtons();
	}

	private void initFrame()
	{
		setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
		setUndecorated(true); // removes the borders of the frame
		getRootPane().setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.BLACK));
		setTitle("Menu");
		setVisible(true);
		setLayout(new FlowLayout());
		setSize(FRAME_WIDTH, FRAME_HEIGHT);
		setLocationRelativeTo(null); // centers it in the screen
		setResizable(false); // so the screen size cant be changed

		//pack(); // so the screen is as tight as it can be
	}

	private void initPanel()
	{
		pnlButtons = new JPanel();

		btnSaveGame = new JButton("Save");
		btnLoadGame = new JButton("Load");
		btnHowToPlay = new JButton("How to Play");
		btnBack = new JButton("Return to Game");
		btnExit = new JButton("Main Menu");

		setButtonProperties(btnSaveGame);
		setButtonProperties(btnLoadGame);
		setButtonProperties(btnHowToPlay);
		setButtonProperties(btnBack);
		setButtonProperties(btnExit);

		pnlButtons.setOpaque(false);
		pnlButtons.setFocusable(false);
		BoxLayout layout = new BoxLayout(pnlButtons, BoxLayout.Y_AXIS);
		pnlButtons.setLayout(layout);

		// Adds the buttons to the menu. The boxes create spaces bewteen the buttons
		int buttonSpacing = 5;
		pnlButtons.add(Box.createRigidArea(new Dimension(1, buttonSpacing)));
		pnlButtons.add(btnSaveGame);
		pnlButtons.add(Box.createRigidArea(new Dimension(1, buttonSpacing)));
		pnlButtons.add(btnLoadGame);
		pnlButtons.add(Box.createRigidArea(new Dimension(1, buttonSpacing)));
		pnlButtons.add(btnHowToPlay);
		pnlButtons.add(Box.createRigidArea(new Dimension(1, buttonSpacing * 11)));
		pnlButtons.add(btnBack);
		pnlButtons.add(Box.createRigidArea(new Dimension(1, buttonSpacing)));
		pnlButtons.add(btnExit);

		add(pnlButtons);
	}

	/**
	 * Sets the properties for the buttons in the main menu, so they are all the same size
	 *
	 * @param aButton
	 */
	private void setButtonProperties(JButton aButton)
	{
		aButton.setFocusable(false);
		aButton.setToolTipText("");
		aButton.setFont(new Font("Arial", Font.PLAIN, 15));
		aButton.setMaximumSize(new Dimension(160, 35));
		aButton.setMinimumSize(new Dimension(160, 35));
		aButton.setPreferredSize(new Dimension(160, 35));
	}

	/**
	 * Sets up the buttons and what happens when you click them
	 */
	private void initButtons()
	{
		btnSaveGame.addActionListener(new ActionListener()
		{
			public void actionPerformed(java.awt.event.ActionEvent evt)
			{
				setupSaveGameButton();
			}
		});

		btnLoadGame.addActionListener(new ActionListener()
		{
			public void actionPerformed(java.awt.event.ActionEvent evt)
			{
				setupLoadGameButton();
			}
		});

		btnHowToPlay.addActionListener(new ActionListener()
		{
			public void actionPerformed(java.awt.event.ActionEvent evt)
			{
				setupHowToPlayButton();
			}
		});

		btnBack.addActionListener(new ActionListener()
		{
			public void actionPerformed(java.awt.event.ActionEvent evt)
			{
				// Makes the game frame clickable again, and gets rid of the menu
				gameFrame.setEnabled(true);
				gameFrame.setFocusable(true);
				dispose();
			}
		});

		btnExit.addActionListener(new ActionListener()
		{
			public void actionPerformed(java.awt.event.ActionEvent evt)
			{
				// Asks the player if they are sure before they quit
				int reply = JOptionPane
						.showConfirmDialog(null, "Are you want to quit to Main Menu?\nAll Progress will be lost.",
								"Quit?", JOptionPane.YES_NO_OPTION);

				// If the player says yes, get rid of the game and menu, then open the main menu
				if (reply == JOptionPane.YES_OPTION)
				{
					mainMenuFrame.pnlMainMenu.setSoundCheckBoxState(); // so the sound boxes are in the right state
					mainMenuFrame.setVisible(true);
					gameFrame.dispose();
					dispose();
				}
			}
		});
	}

	/**
	 * Sets up the frame for how to play and makes the game frame/menu invisible while its open
	 */
	private void setupHowToPlayButton()
	{
		JFrame howToFrame = new JFrame();
		String BACKGROUND_IMAGE = "resources/Backgrounds/Menu_UI.jpg";

		howToFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		howToFrame.setTitle("Kiwi Land - Save Game");
		howToFrame.setContentPane(new JLabel(new ImageIcon(BACKGROUND_IMAGE))); // sets the background image
		howToFrame.setVisible(true);
		howToFrame.setLayout(new FlowLayout());
		howToFrame.setSize(900, 720);
		howToFrame.setLocationRelativeTo(null); // centers it in the screen
		howToFrame.setResizable(false); // so the screen size cant be changed

		howToFrame.add(new HowToPlayPanel(this, howToFrame));
		howToFrame.setVisible(true);

		setVisibilityForGameAndMenu(false);
	}

	/**
	 * Sets up the frame for save game and makes the game frame/menu invisible while its open
	 * <p>
	 * This uses the same panel as the load panel, but makes it so some buttons aren't visible
	 * and others are
	 */
	private void setupSaveGameButton()
	{
		JFrame saveFrame = new JFrame();

		String BACKGROUND_IMAGE = "resources/Backgrounds/Menu_UI.jpg";

		saveFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		saveFrame.setTitle("Kiwi Land - Save Game");
		saveFrame.setContentPane(new JLabel(new ImageIcon(BACKGROUND_IMAGE))); // sets the background image
		saveFrame.setVisible(true);
		saveFrame.setLayout(new FlowLayout());
		saveFrame.setSize(900, 720);
		saveFrame.setLocationRelativeTo(null); // centers it in the screen
		saveFrame.setResizable(false); // so the screen size cant be changed

		saveFrame.add(new LoadGamePanel(this, saveFrame, gameFrame.getGame()));
		saveFrame.setVisible(true);

		setVisibilityForGameAndMenu(false);
	}

	/**
	 * Sets up the frame for load game and makes the game frame/menu invisible while its open
	 */
	private void setupLoadGameButton()
	{
		JFrame loadFrame = new JFrame();

		String BACKGROUND_IMAGE = "resources/Backgrounds/Menu_UI.jpg";

		loadFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		loadFrame.setTitle("Kiwi Land - Load Game");
		loadFrame.setContentPane(new JLabel(new ImageIcon(BACKGROUND_IMAGE))); // sets the background image
		loadFrame.setVisible(true);
		loadFrame.setLayout(new FlowLayout());
		loadFrame.setSize(900, 720);
		loadFrame.setLocationRelativeTo(null); // centers it in the screen
		loadFrame.setResizable(false); // so the screen size cant be changed

		loadFrame.add(new LoadGamePanel(this, loadFrame, mainMenuFrame.getGame()));
		loadFrame.setVisible(true);

		setVisibilityForGameAndMenu(false);
	}

	/**
	 * Sets the game visibility, and the ingame menu visibility
	 * Used to make the game disappear at the same time when opening other screen such as the howtoplay and load screen
	 */
	public void setVisibilityForGameAndMenu(boolean visibility)
	{
		gameFrame.setVisible(visibility);
		setVisible(visibility);
	}
}
