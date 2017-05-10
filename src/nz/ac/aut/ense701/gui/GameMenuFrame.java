package nz.ac.aut.ense701.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * Created by Scott Richards on 09-May-17.
 */
public class GameMenuFrame extends JFrame
{
	private final int FRAME_WIDTH  = 200;
	private final int FRAME_HEIGHT = 270;

	GameScreenFrame gameFrame;

	private JPanel  pnlButtons;
	private JButton btnSaveGame;
	private JButton btnLoadGame;
	private JButton btnHowToPlay;
	private JButton btnBack;

	private JButton btnExit;

	public GameMenuFrame(GameScreenFrame theFrame)
	{
		gameFrame = theFrame;

		initPanel();
		initFrame();
		initButtons();
	}

	/**
	 * Initializes the frame
	 */
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
		btnExit = new JButton("Exit Game");

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
				btnSaveGameActionPerformed();
			}
		});

		btnLoadGame.addActionListener(new ActionListener()
		{
			public void actionPerformed(java.awt.event.ActionEvent evt)
			{

			}
		});

		btnHowToPlay.addActionListener(new ActionListener()
		{
			public void actionPerformed(java.awt.event.ActionEvent evt)
			{

			}
		});

		btnBack.addActionListener(new ActionListener()
		{
			public void actionPerformed(java.awt.event.ActionEvent evt)
			{
				gameFrame.setEnabled(true);
				gameFrame.setFocusable(true);
				dispose();
			}
		});

		btnExit.addActionListener(new ActionListener()
		{
			public void actionPerformed(java.awt.event.ActionEvent evt)
			{
				System.exit(0);
			}
		});
	}

	private void btnSaveGameActionPerformed() {
		JFrame loadFrame = new JFrame();

		String BACKGROUND_IMAGE = "resources/Main_Menu_UI.jpg";

		loadFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		loadFrame.setTitle("Kiwi Land - Main Menu");
		loadFrame.setContentPane(new JLabel(new ImageIcon(BACKGROUND_IMAGE))); // sets the background image
		loadFrame.setVisible(true);
		loadFrame.setLayout(new FlowLayout());
		loadFrame.setSize(900, 720);
		loadFrame.setLocationRelativeTo(null); // centers it in the screen
		loadFrame.setResizable(false); // so the screen size cant be changed

		loadFrame.add(new LoadGamePanel(this, loadFrame, gameFrame.getGame().getCurrentLevelNumber()));
		loadFrame.setVisible(true);
	}
}
