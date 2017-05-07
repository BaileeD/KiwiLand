package nz.ac.aut.ense701.gui;

import nz.ac.aut.ense701.gameModel.Game;

import javax.swing.*;
import java.awt.event.ActionListener;

/**
 * Created by Scott Richards on 07-May-17.
 */
public class LoadGamePanel extends JPanel
{
	private MainMenuFrame theFrame;

	private JButton btnBack;

	public LoadGamePanel(MainMenuFrame frame)
	{
		theFrame = frame;

		initPanel();
	}

	private void initPanel()
	{
		btnBack = new JButton("Back");

		btnBack.addActionListener(new ActionListener()
		{
			public void actionPerformed(java.awt.event.ActionEvent evt)
			{
				theFrame.openMainMenu();
			}
		});

		add(btnBack);
	}
}
