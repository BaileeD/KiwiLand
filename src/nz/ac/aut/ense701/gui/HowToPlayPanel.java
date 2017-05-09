package nz.ac.aut.ense701.gui;

import nz.ac.aut.ense701.gameModel.Game;

import javax.swing.*;
import java.awt.event.ActionListener;

/**
 * Created by Scott Richards on 07-May-17.
 */
public class HowToPlayPanel extends JPanel
{
	private MainMenuFrame theFrame;

	private JButton btnBack;

	public HowToPlayPanel(MainMenuFrame frame)
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

		JPanel pnlBackButton = new JPanel();
		pnlBackButton.add(btnBack);
		pnlBackButton.setSize(100, 90);
		pnlBackButton.setFocusable(false);

		add(pnlBackButton);
		pnlBackButton.setLocation(400, 570);

	}
}
