package nz.ac.aut.ense701.gui;

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
        private JButton btnExit1;
         
	public MainMenuPanel(MainMenuFrame frame)
	{
		theFrame = frame;

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
