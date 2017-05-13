package gameGUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * Created by Scott Richards on 09-May-17.
 */
public class GameMenuFrame extends JFrame
{
	private final int FRAME_WIDTH  = 200;
	private final int FRAME_HEIGHT = 270;

	GameScreenFrame gameFrame;
	MainMenuFrame   mainMenuFrame;

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

	private void setButtonProperties(JButton aButton)
	{
		aButton.setFocusable(false);
		aButton.setToolTipText("");
		aButton.setFont(new Font("Arial", Font.PLAIN, 15));
		aButton.setMaximumSize(new Dimension(160, 35));
		aButton.setMinimumSize(new Dimension(160, 35));
		aButton.setPreferredSize(new Dimension(160, 35));
	}

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
				gameFrame.setEnabled(true);
				gameFrame.setFocusable(true);
				setVisible(false);
			}
		});

		btnExit.addActionListener(new ActionListener()
		{
			public void actionPerformed(java.awt.event.ActionEvent evt)
			{
				int reply = JOptionPane
						.showConfirmDialog(null, "Are you want to quit to Main Menu?\nAll Progress will be lost.",
								"Quit?", JOptionPane.YES_NO_OPTION);

				if (reply == JOptionPane.YES_OPTION)
				{
					gameFrame.dispose();
					dispose();
					mainMenuFrame.setVisible(true);
				}
			}
		});
	}

	private void setupHowToPlayButton()
	{
		JFrame howToFrame = new JFrame();

		String BACKGROUND_IMAGE = "resources/Main_Menu_UI.jpg";

		howToFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		howToFrame.setTitle("Kiwi Land - Main Menu");
		howToFrame.setContentPane(new JLabel(new ImageIcon(BACKGROUND_IMAGE))); // sets the background image
		howToFrame.setVisible(true);
		howToFrame.setLayout(new FlowLayout());
		howToFrame.setSize(900, 720);
		howToFrame.setLocationRelativeTo(null); // centers it in the screen
		howToFrame.setResizable(false); // so the screen size cant be changed
		howToFrame.setAlwaysOnTop(true);

		HowToPlayPanel howTo = new HowToPlayPanel(this);

		howToFrame.add(howTo);
		howToFrame.setVisible(true);
	}

	private void setupSaveGameButton()
	{
		JFrame saveFrame = new JFrame();

		String BACKGROUND_IMAGE = "resources/Menu_UI.jpg";

		saveFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		saveFrame.setTitle("Kiwi Land - Save Game");
		saveFrame.setContentPane(new JLabel(new ImageIcon(BACKGROUND_IMAGE))); // sets the background image
		saveFrame.setVisible(true);
		saveFrame.setLayout(new FlowLayout());
		saveFrame.setSize(900, 720);
		saveFrame.setLocationRelativeTo(null); // centers it in the screen
		saveFrame.setResizable(false); // so the screen size cant be changed

		saveFrame.add(new LoadGamePanel(this, saveFrame, gameFrame.getGame().getCurrentLevelNumber()));
		saveFrame.setVisible(true);
		this.setVisible(false);
	}

	private void setupLoadGameButton()
	{
		JFrame loadFrame = new JFrame();

		String BACKGROUND_IMAGE = "resources/Menu_UI.jpg";

		loadFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		loadFrame.setTitle("Kiwi Land - Load Game");
		loadFrame.setContentPane(new JLabel(new ImageIcon(BACKGROUND_IMAGE))); // sets the background image
		loadFrame.setVisible(true);
		loadFrame.setLayout(new FlowLayout());
		loadFrame.setSize(900, 720);
		loadFrame.setLocationRelativeTo(null); // centers it in the screen
		loadFrame.setResizable(false); // so the screen size cant be changed

		loadFrame.add(new LoadGamePanel(this, loadFrame));
		loadFrame.setVisible(true);
		this.setVisible(false);
	}

	@Override public void setVisible(boolean visible)
	{
		super.setVisible(visible);
		gameFrame.setVisible(visible);
	}
}
