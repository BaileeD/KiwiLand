package gameGUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * Created by Scott Richards on 07-May-17.
 */
public class MainMenuPanel extends JPanel
{
	private MainMenuFrame theFrame;

	private JPanel  pnlButtons;
	private JButton btnNewGame;
	private JButton btnLoadGame;
	private JButton btnHowToPlay;
	private JButton btnExit;

	public MainMenuPanel(MainMenuFrame frame)
	{
		theFrame = frame;
		setOpaque(false);
		setFocusable(false);

		initPanel();
		initButtons();
	}

	private void initPanel()
	{
		pnlButtons = new JPanel();

		btnNewGame = new JButton("NEW GAME");
		btnLoadGame = new JButton("LOAD GAME");
		btnHowToPlay = new JButton("HOW TO PLAY");
		btnExit = new JButton("EXIT");

		setButtonProperties(btnNewGame);
		setButtonProperties(btnLoadGame);
		setButtonProperties(btnHowToPlay);
		setButtonProperties(btnExit);

		pnlButtons.setOpaque(false);
		pnlButtons.setFocusable(false);
		BoxLayout layout = new BoxLayout(pnlButtons, BoxLayout.Y_AXIS);
		pnlButtons.setLayout(layout);

		int buttonSpacing = 30;
		pnlButtons.add(Box.createRigidArea(new Dimension(1, buttonSpacing * 6)));
		pnlButtons.add(btnNewGame);
		pnlButtons.add(Box.createRigidArea(new Dimension(1, buttonSpacing)));
		pnlButtons.add(btnLoadGame);
		pnlButtons.add(Box.createRigidArea(new Dimension(1, buttonSpacing)));
		pnlButtons.add(btnHowToPlay);
		pnlButtons.add(Box.createRigidArea(new Dimension(1, buttonSpacing)));
		pnlButtons.add(btnExit);
		add(pnlButtons);
	}

	private void setButtonProperties(JButton aButton)
	{
		aButton.setFocusable(false);
		aButton.setToolTipText("");
		aButton.setFont(new Font("Arial", Font.PLAIN, 25));
		aButton.setMaximumSize(new Dimension(350, 80));
		aButton.setMinimumSize(new Dimension(350, 80));
		aButton.setPreferredSize(new Dimension(350, 80));
	}

	private void initButtons()
	{
		btnNewGame.addActionListener(new ActionListener()
		{
			public void actionPerformed(java.awt.event.ActionEvent evt)
			{
				theFrame.openNewGameMenu();
			}
		});

		btnLoadGame.addActionListener(new ActionListener()
		{
			public void actionPerformed(java.awt.event.ActionEvent evt)
			{
				theFrame.openLoadMenu();
			}
		});

		btnHowToPlay.addActionListener(new ActionListener()
		{
			public void actionPerformed(java.awt.event.ActionEvent evt)
			{
				theFrame.openHowToPlayMenu();
			}
		});

		btnExit.addActionListener(new ActionListener()
		{
			public void actionPerformed(java.awt.event.ActionEvent evt)
			{
				theFrame.exitGame();
			}
		});
	}
}
