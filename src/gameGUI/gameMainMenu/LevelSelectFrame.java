package gameGUI.gameMainMenu;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * Created by Scott Richards on 27-May-17.
 */
public class LevelSelectFrame extends JFrame
{
	private final int FRAME_WIDTH  = 200;
	private final int FRAME_HEIGHT = 400;

	MainMenuFrame mainMenuFrame;    // so you bring this frame back to be visible after quitting to menu

	private JPanel  pnlButtons;
	private JButton btnLvl1;
	private JButton btnLvl2;
	private JButton btnLvl3;
	private JButton btnLvl4;
	private JButton btnLvl5;
	private JButton btnLvl6;
	private JButton btnLvl7;
	private JButton btnLvl8;
	private JButton btnBack;

	public LevelSelectFrame(MainMenuFrame mainMenuFrame)
	{
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
		setTitle("Level Select");
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

		btnLvl1 = new JButton("Level 1");
		btnLvl2 = new JButton("Level 2");
		btnLvl3 = new JButton("Level 3");
		btnLvl4 = new JButton("Level 4");
		btnLvl5 = new JButton("Level 5");
		btnLvl6 = new JButton("Level 6");
		btnLvl7 = new JButton("Level 7");
		btnLvl8 = new JButton("Level 8");
		btnBack = new JButton("Back");

		setButtonProperties(btnLvl1);
		setButtonProperties(btnLvl2);
		setButtonProperties(btnLvl3);
		setButtonProperties(btnLvl4);
		setButtonProperties(btnLvl5);
		setButtonProperties(btnLvl6);
		setButtonProperties(btnLvl7);
		setButtonProperties(btnLvl8);
		setButtonProperties(btnBack);

		pnlButtons.setOpaque(false);
		pnlButtons.setFocusable(false);
		BoxLayout layout = new BoxLayout(pnlButtons, BoxLayout.Y_AXIS);
		pnlButtons.setLayout(layout);

		// Adds the buttons to the menu. The boxes create spaces bewteen the buttons
		int buttonSpacing = 5;
		pnlButtons.add(Box.createRigidArea(new Dimension(1, buttonSpacing)));
		pnlButtons.add(btnLvl1);
		pnlButtons.add(Box.createRigidArea(new Dimension(1, buttonSpacing)));
		pnlButtons.add(btnLvl2);
		pnlButtons.add(Box.createRigidArea(new Dimension(1, buttonSpacing)));
		pnlButtons.add(btnLvl3);
		pnlButtons.add(Box.createRigidArea(new Dimension(1, buttonSpacing)));
		pnlButtons.add(btnLvl4);
		pnlButtons.add(Box.createRigidArea(new Dimension(1, buttonSpacing)));
		pnlButtons.add(btnLvl5);
		pnlButtons.add(Box.createRigidArea(new Dimension(1, buttonSpacing)));
		pnlButtons.add(btnLvl6);
		pnlButtons.add(Box.createRigidArea(new Dimension(1, buttonSpacing)));
		pnlButtons.add(btnLvl7);
		pnlButtons.add(Box.createRigidArea(new Dimension(1, buttonSpacing)));
		pnlButtons.add(btnLvl8);
		pnlButtons.add(Box.createRigidArea(new Dimension(1, buttonSpacing * 5)));
		pnlButtons.add(btnBack);

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
		btnLvl1.addActionListener(new ActionListener()
		{
			public void actionPerformed(java.awt.event.ActionEvent evt)
			{
				mainMenuFrame.setEnabled(true);
				mainMenuFrame.setFocusable(true);
				mainMenuFrame.createGame(1);
				dispose();
			}
		});
		btnLvl2.addActionListener(new ActionListener()
		{
			public void actionPerformed(java.awt.event.ActionEvent evt)
			{
				mainMenuFrame.setEnabled(true);
				mainMenuFrame.setFocusable(true);
				mainMenuFrame.createGame(2);
				dispose();
			}
		});
		btnLvl3.addActionListener(new ActionListener()
		{
			public void actionPerformed(java.awt.event.ActionEvent evt)
			{
				mainMenuFrame.setEnabled(true);
				mainMenuFrame.setFocusable(true);
				mainMenuFrame.createGame(3);
				dispose();
			}
		});
		btnLvl4.addActionListener(new ActionListener()
		{
			public void actionPerformed(java.awt.event.ActionEvent evt)
			{
				mainMenuFrame.setEnabled(true);
				mainMenuFrame.setFocusable(true);
				mainMenuFrame.createGame(4);
				dispose();
			}
		});
		btnLvl5.addActionListener(new ActionListener()
		{
			public void actionPerformed(java.awt.event.ActionEvent evt)
			{
				mainMenuFrame.setEnabled(true);
				mainMenuFrame.setFocusable(true);
				mainMenuFrame.createGame(5);
				dispose();
			}
		});
		btnLvl1.addActionListener(new ActionListener()
		{
			public void actionPerformed(java.awt.event.ActionEvent evt)
			{
				mainMenuFrame.setEnabled(true);
				mainMenuFrame.setFocusable(true);
				mainMenuFrame.createGame(1);
				dispose();
			}
		});
		btnLvl6.addActionListener(new ActionListener()
		{
			public void actionPerformed(java.awt.event.ActionEvent evt)
			{
				mainMenuFrame.setEnabled(true);
				mainMenuFrame.setFocusable(true);
				mainMenuFrame.createGame(6);
				dispose();
			}
		});
		btnLvl7.addActionListener(new ActionListener()
		{
			public void actionPerformed(java.awt.event.ActionEvent evt)
			{
				mainMenuFrame.setEnabled(true);
				mainMenuFrame.setFocusable(true);
				mainMenuFrame.createGame(7);
				dispose();
			}
		});
		btnLvl8.addActionListener(new ActionListener()
		{
			public void actionPerformed(java.awt.event.ActionEvent evt)
			{
				mainMenuFrame.setEnabled(true);
				mainMenuFrame.setFocusable(true);
				mainMenuFrame.createGame(8);
				dispose();
			}
		});
		btnBack.addActionListener(new ActionListener()
		{
			public void actionPerformed(java.awt.event.ActionEvent evt)
			{
				// Makes the game frame clickable again, and gets rid of the menu
				mainMenuFrame.setEnabled(true);
				mainMenuFrame.setFocusable(true);
				dispose();
			}
		});
	}

	public void disableUnreachedLevelButtons()
	{
		if (mainMenuFrame.getGame().getUser().getHighestLevelReached() < 2)
		{
			btnLvl2.setEnabled(false);
			btnLvl3.setEnabled(false);
			btnLvl4.setEnabled(false);
			btnLvl5.setEnabled(false);
			btnLvl6.setEnabled(false);
			btnLvl7.setEnabled(false);
			btnLvl8.setEnabled(false);
		}
		else if (mainMenuFrame.getGame().getUser().getHighestLevelReached() < 3)
		{
			btnLvl3.setEnabled(false);
			btnLvl4.setEnabled(false);
			btnLvl5.setEnabled(false);
			btnLvl6.setEnabled(false);
			btnLvl7.setEnabled(false);
			btnLvl8.setEnabled(false);
		}
		else if (mainMenuFrame.getGame().getUser().getHighestLevelReached() < 4)
		{
			btnLvl4.setEnabled(false);
			btnLvl5.setEnabled(false);
			btnLvl6.setEnabled(false);
			btnLvl7.setEnabled(false);
			btnLvl8.setEnabled(false);
		}
		else if (mainMenuFrame.getGame().getUser().getHighestLevelReached() < 5)
		{
			btnLvl5.setEnabled(false);
			btnLvl6.setEnabled(false);
			btnLvl7.setEnabled(false);
			btnLvl8.setEnabled(false);
		}
		else if (mainMenuFrame.getGame().getUser().getHighestLevelReached() < 6)
		{
			btnLvl6.setEnabled(false);
			btnLvl7.setEnabled(false);
			btnLvl8.setEnabled(false);
		}
		else if (mainMenuFrame.getGame().getUser().getHighestLevelReached() < 7)
		{
			btnLvl7.setEnabled(false);
			btnLvl8.setEnabled(false);
		}
		else if (mainMenuFrame.getGame().getUser().getHighestLevelReached() < 8)
		{
			btnLvl8.setEnabled(false);
		}
	}
}
