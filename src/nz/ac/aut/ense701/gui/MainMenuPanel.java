package nz.ac.aut.ense701.gui;

import javax.swing.*;
import java.awt.event.ActionListener;

/**
 * Created by Scott Richards on 07-May-17.
 */
public class MainMenuPanel extends JPanel
{
	private MainMenuFrame theFrame;
	private JPanel thePanel;

	private JPanel  pnlButtons;
	private JButton btnNewGame;
	private JButton btnLoadGame;
	private JButton btnHowToPlay;
	private JButton btnExit;

	public MainMenuPanel(MainMenuFrame frame, JPanel panel)
	{
		theFrame = frame;
		thePanel = panel;

		initPanel();
		initButtons();
	}

	private void initPanel()
	{
		pnlButtons = new JPanel();

		btnNewGame = new JButton("New Game");
		btnLoadGame = new JButton("Load Game");
		btnHowToPlay = new JButton("How to Play");
		btnExit = new JButton("Exit");

		pnlButtons.add(btnNewGame);
		pnlButtons.add(btnLoadGame);
		pnlButtons.add(btnHowToPlay);
		pnlButtons.add(btnExit);

		add(pnlButtons);
	}

	private void initButtons()
	{
		btnNewGame.addActionListener(new ActionListener()
		{
			public void actionPerformed(java.awt.event.ActionEvent evt)
			{

			}
		});

		btnLoadGame.addActionListener(new ActionListener()
		{
			public void actionPerformed(java.awt.event.ActionEvent evt)
			{
				theFrame.getCardLayout().show(thePanel, "2");
			}
		});

		btnHowToPlay.addActionListener(new ActionListener()
		{
			public void actionPerformed(java.awt.event.ActionEvent evt)
			{
				theFrame.getCardLayout().show(thePanel, "2");
			}
		});

		btnExit.addActionListener(new ActionListener()
		{
			public void actionPerformed(java.awt.event.ActionEvent evt)
			{
				System.exit(1);
			}
		});
	}
}
