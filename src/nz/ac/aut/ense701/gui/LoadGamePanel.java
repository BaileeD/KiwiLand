package nz.ac.aut.ense701.gui;

import javax.swing.*;
import java.awt.*;
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

		setOpaque(false);
		setFocusable(false);

		initPanel();
	}

	private void initPanel()
	{
		btnBack = new JButton("BACK");
		btnBack.setMaximumSize(new Dimension(150, 50));
		btnBack.setMinimumSize(new Dimension(150, 50));
		btnBack.setPreferredSize(new Dimension(150, 50));

		btnBack.addActionListener(new ActionListener()
		{
			public void actionPerformed(java.awt.event.ActionEvent evt)
			{
				theFrame.openMainMenu();
			}
		});

		btnBack.setLocation(50, 50);

		add(btnBack);

	}
}
