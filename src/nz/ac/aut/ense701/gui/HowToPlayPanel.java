package nz.ac.aut.ense701.gui;

import javax.swing.*;
import java.awt.*;
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

		JPanel pnlBackButton = new JPanel();
		pnlBackButton.setFocusable(false);
		pnlBackButton.setOpaque(false);

		pnlBackButton.add(btnBack);
		add(pnlBackButton);
	}
}
