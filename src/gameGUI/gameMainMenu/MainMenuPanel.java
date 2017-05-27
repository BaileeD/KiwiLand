package gameGUI.gameMainMenu;

import gameModel.Sound;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

/**
 * Created by Scott Richards on 07-May-17.
 */
public class MainMenuPanel extends JPanel
{
	private MainMenuFrame theFrame;
	private LevelSelectFrame levelSelectFrame;

	private JPanel    pnlButtons;
	private JButton   btnNewGame;
	private JButton	  btnLevelSelect;
	private JButton   btnLoadGame;
	private JButton   btnHowToPlay;
	private JButton   btnExit;
	private JCheckBox cboxMusic;
	private JCheckBox cboxSoundEffects;

	public MainMenuPanel(MainMenuFrame frame)
	{
		theFrame = frame;
		levelSelectFrame = new LevelSelectFrame(theFrame);
		setOpaque(false);
		setFocusable(false);

		initPanel();
		initButtons();
	}

	private void initPanel()
	{
		pnlButtons = new JPanel();

		btnNewGame = new JButton("NEW GAME");
		btnLevelSelect = new JButton("LEVEL SELECT");
		btnLoadGame = new JButton("LOAD GAME");
		btnHowToPlay = new JButton("HOW TO PLAY");
		btnExit = new JButton("EXIT");

		cboxMusic = new JCheckBox("Music");
		cboxMusic.setOpaque(false);
		cboxMusic.setSelected(Sound.musicActive);
		cboxSoundEffects = new JCheckBox("Sound Effects");
		cboxSoundEffects.setOpaque(false);
		cboxSoundEffects.setSelected(Sound.soundEffectsActive);

		setButtonProperties(btnNewGame);
		setButtonProperties(btnLevelSelect);
		setButtonProperties(btnLoadGame);
		setButtonProperties(btnHowToPlay);
		setButtonProperties(btnExit);

		pnlButtons.setOpaque(false);
		pnlButtons.setFocusable(false);
		BoxLayout layout = new BoxLayout(pnlButtons, BoxLayout.Y_AXIS);
		pnlButtons.setLayout(layout);

		int buttonSpacing = 20;
		pnlButtons.add(Box.createRigidArea(new Dimension(1, buttonSpacing * 7)));
		pnlButtons.add(btnNewGame);
		pnlButtons.add(Box.createRigidArea(new Dimension(1, buttonSpacing)));
		pnlButtons.add(btnLevelSelect);
		pnlButtons.add(Box.createRigidArea(new Dimension(1, buttonSpacing)));
		pnlButtons.add(btnLoadGame);
		pnlButtons.add(Box.createRigidArea(new Dimension(1, buttonSpacing)));
		pnlButtons.add(btnHowToPlay);
		pnlButtons.add(Box.createRigidArea(new Dimension(1, buttonSpacing)));
		pnlButtons.add(btnExit);

		JPanel pnlSounds = new JPanel();
		pnlSounds.setLayout(new FlowLayout());
		pnlSounds.setOpaque(false);
		pnlSounds.setFocusable(false);
		pnlSounds.add(cboxMusic);
		pnlSounds.add(cboxSoundEffects);
		pnlButtons.add(pnlSounds);
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

		btnLevelSelect.addActionListener(new ActionListener()
		{
			public void actionPerformed(java.awt.event.ActionEvent evt)
			{
				levelSelectFrame.disableUnreachedLevelButtons();
				levelSelectFrame.setVisible(true);
				theFrame.setEnabled(false);
				theFrame.setFocusable(false);
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

		cboxMusic.addItemListener(new ItemListener()
		{
			@Override public void itemStateChanged(ItemEvent e)
			{
				if (e.getStateChange() == ItemEvent.SELECTED)
				{
					Sound.startMusic();
				}
				else
				{
					Sound.stopMusic();
				}
			}
		});
		cboxSoundEffects.addItemListener(new ItemListener()
		{
			@Override public void itemStateChanged(ItemEvent e)
			{
				if (e.getStateChange() == ItemEvent.SELECTED)
				{
					Sound.enableSoundEffects();
				}
				else
				{
					Sound.disableSoundEffects();
				}
			}
		});
	}

	public void setSoundCheckBoxState()
	{
		cboxMusic.setSelected(Sound.musicActive);
		cboxSoundEffects.setSelected(Sound.soundEffectsActive);
	}
}
