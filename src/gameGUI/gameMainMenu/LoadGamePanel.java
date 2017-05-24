package gameGUI.gameMainMenu;

import gameGUI.gameInGame.GameMenuFrame;
import gameModel.Game;
import gameModel.GameSave;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Scott Richards on 07-May-17.
 * Code added by Chaitanya Varma
 */
public class LoadGamePanel extends JPanel
{
	private MainMenuFrame mainMenuFrame = null;
	private GameMenuFrame gameMenuFrame;
	private JFrame        loadFrame;

	//private JTextField        textPlayerName;
	private JLabel            labelPlayerName;
	//private JButton           btnRetrieveSaves;
	private JTable            tableData;
	private DefaultTableModel model;
	private JScrollPane       scrollPane;
	private JButton           btnBack;
	private JButton           btnLoad;
	private JButton           btnSave;
	private JButton           btnSaveOver;
	private JButton           btnDeleteSave;
        private Game game;
	/**
	 * LoadGamePanel constructor, used when saving the game while playing it
	 *
	 * @param loadFrame parent loadFrame
	 * @param level     game level
	 */
	public LoadGamePanel(GameMenuFrame gameMenuFrame, JFrame loadFrame, Game game)
	{
		this.gameMenuFrame = gameMenuFrame;
		this.loadFrame = loadFrame;
                this.game = game;
		setOpaque(false);
		setFocusable(false);
		setLayout(new BorderLayout());

		initPanels();
		loadAllGameSaves();

		btnLoad.setVisible(false);
	}

	/**
	 * LoadGamePanel constructor, used when loading the game while playing it
	 *
	 * @param loadFrame parent loadFrame
	 */
	/*public LoadGamePanel(GameMenuFrame gameMenuFrame, JFrame loadFrame, Game game)
	{
		this.gameMenuFrame = gameMenuFrame;
		this.loadFrame = loadFrame;
                this.game = game;
                
		setOpaque(false);
		setFocusable(false);
		setLayout(new BorderLayout());

		initPanels();
		loadAllGameSaves();

		btnSave.setVisible(false);
		btnSaveOver.setVisible(false);
		btnDeleteSave.setVisible(true);
	}*/

	/**
	 * LoadGamePanel constructor, used when loading the game from the main menu
	 *
	 * @param loadFrame reference to MainMenuFrame class
	 */
	public LoadGamePanel(MainMenuFrame loadFrame, Game game)
	{
		mainMenuFrame = loadFrame;
                this.game = game;
                
		setOpaque(false);
		setFocusable(false);
		setLayout(new BorderLayout());

		initPanels();
		loadAllGameSaves();

		btnSave.setVisible(false);
		btnSaveOver.setVisible(false);
		btnDeleteSave.setVisible(true);
	}

	/**
	 * initialize the panel
	 */
	private void initPanels()
	{
		initSearchPlayerPanel();
		initGameSavesPanel();
		initButtonComponents();
	}

	private void initSearchPlayerPanel()
	{
		JPanel pnlSearchPlayer = new JPanel();
		pnlSearchPlayer.setOpaque(false);

                labelPlayerName = new JLabel("Hello " + this.game.getUser().getPlayerName());
                labelPlayerName.setForeground(Color.WHITE);
                

		/*textPlayerName = new JTextField(15);
		btnRetrieveSaves = new JButton("Search");
		btnRetrieveSaves.setPreferredSize(new Dimension(120, 25));
		btnRetrieveSaves.addActionListener(new ActionListener()
		{
			public void actionPerformed(java.awt.event.ActionEvent evt)
			{
				retrieveGameSaves();
			}
		});*/

		pnlSearchPlayer.add(labelPlayerName);
		//pnlSearchPlayer.add(textPlayerName);
		//pnlSearchPlayer.add(btnRetrieveSaves);

		add(pnlSearchPlayer, BorderLayout.PAGE_START);
	}

	private void initGameSavesPanel()
	{
		JPanel pnlGameSaves = new JPanel();
		pnlGameSaves.setOpaque(false);

		String[] columnNames = {"Save Name", "Date", "Level", "id" };
		model = new DefaultTableModel(0, 0);
		model.setColumnIdentifiers(columnNames);

		tableData = new JTable(model)
		{
			@Override public Dimension getPreferredScrollableViewportSize()
			{
				return new Dimension(875, tableData.getRowHeight() * 36);
			}
		};
		tableData.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tableData.removeColumn(tableData.getColumnModel().getColumn(3));
		tableData.setOpaque(false);
		tableData.setShowGrid(false);
		//((DefaultTableCellRenderer)tableData.getDefaultRenderer(Object.class)).setOpaque(false);

		scrollPane = new JScrollPane(tableData);
		scrollPane.setOpaque(false);
		scrollPane.getViewport().setOpaque(false);

		pnlGameSaves.add(scrollPane);

		add(pnlGameSaves, BorderLayout.CENTER);
	}

	private void initButtonComponents()
	{
		JPanel pnlButtons = new JPanel();
		pnlButtons.setOpaque(false);

		btnBack = new JButton("Back");
		btnBack.setPreferredSize(new Dimension(120, 25));
		btnBack.addActionListener(new ActionListener()
		{
			public void actionPerformed(java.awt.event.ActionEvent evt)
			{
				if (mainMenuFrame != null)
				{
					mainMenuFrame.openMainMenu();
				}
				else
				{
					gameMenuFrame.setVisibilityForGameAndMenu(true);
					loadFrame.dispose();
				}
			}
		});

		btnLoad = new JButton("Load Save");
		btnLoad.setPreferredSize(new Dimension(120, 25));
		btnLoad.addActionListener(new ActionListener()
		{
			public void actionPerformed(java.awt.event.ActionEvent evt)
			{
				loadGame();
			}
		});

		btnSave = new JButton("Create New Save");
		btnSave.setPreferredSize(new Dimension(140, 25));
		btnSave.addActionListener(new ActionListener()
		{
			public void actionPerformed(java.awt.event.ActionEvent evt)
			{
				newSaveGame();
			}
		});

		btnSaveOver = new JButton("Save Over");
		btnSaveOver.setPreferredSize(new Dimension(120, 25));
		btnSaveOver.addActionListener(new ActionListener()
		{
			public void actionPerformed(java.awt.event.ActionEvent evt)
			{
				saveOverGame();
			}
		});

		btnDeleteSave = new JButton("Delete Save");
		btnDeleteSave.setPreferredSize(new Dimension(120, 25));
		btnDeleteSave.addActionListener(new ActionListener()
		{
			public void actionPerformed(java.awt.event.ActionEvent evt)
			{
				deleteSave();
			}
		});

		pnlButtons.add(btnLoad);
		pnlButtons.add(btnSave);
		pnlButtons.add(btnSaveOver);
		pnlButtons.add(btnDeleteSave);
		pnlButtons.add(btnBack);

		add(pnlButtons, BorderLayout.PAGE_END);
	}

	/**
	 * Retrieve game saves of a player
	 */
	/*private void retrieveGameSaves()
	{
		if (this.textPlayerName.getText().trim().equals(""))
		{
			ArrayList<GameSave> gameSaves = GameSave.getAllGameSaves();
			setTableData(gameSaves);
		}
		else
		{
			ArrayList<GameSave> gameSaves = GameSave.getPlayerGameSaves(this.textPlayerName.getText().trim());
			setTableData(gameSaves);
		}
	}*/

	/**
	 * Retrieve all game saves
	 */
	public void loadAllGameSaves()
	{
		ArrayList<GameSave> gameSaves = GameSave.getAllGameSaves(this.game.getUser().getUserId());
		setTableData(gameSaves);
	}

	/**
	 * Populate JTable with the game saves data
	 */
	private void setTableData(ArrayList<GameSave> gameSaves)
	{
		model.setRowCount(0);

		if (gameSaves != null)
		{
			for (GameSave gameSave : gameSaves)
			{
				model.addRow(new Object[] {gameSave.getSaveName(), gameSave.getSaveDate(),
						gameSave.getLevel(), gameSave.getGameSaveId() });
			}
		}

		enableButtons(model.getRowCount() > 0 ? true : false);
	}

	/**
	 * enable or disable the buttons based on number of rows in table
	 */
	private void enableButtons(boolean enable)
	{
		this.btnDeleteSave.setEnabled(enable);
		this.btnSaveOver.setEnabled(enable);
		this.btnLoad.setEnabled(enable);
	}

	/**
	 * Load a game
	 */
	private void loadGame()
	{
		int row = tableData.getSelectedRow();
		if (row != -1)
		{
			Integer level = Integer.parseInt(tableData.getModel().getValueAt(row, 2).toString());
			mainMenuFrame.openSaveGameMenu(level);
		}
		else
		{
			JOptionPane.showMessageDialog(this, "Please select a row", "Invalid Input", JOptionPane.ERROR_MESSAGE);
		}
	}

	/**
	 * Delete a save
	 */
	private void deleteSave()
	{
		int row = tableData.getSelectedRow();
		if (row != -1)
		{
			int reply = JOptionPane.showConfirmDialog(null,
					"Are you want to quit to delete this save?\nYou wont be able to get it back.", "Delete?",
					JOptionPane.YES_NO_OPTION);

			if (reply == JOptionPane.YES_OPTION)
			{
				Integer value = Integer.parseInt(tableData.getModel().getValueAt(row, 3).toString());
				GameSave.deleteSave(value);
				model.removeRow(row);
				enableButtons(model.getRowCount() > 0 ? true : false);
			}
		}
		else
		{
			JOptionPane.showMessageDialog(this, "Please select a row", "Invalid Input", JOptionPane.ERROR_MESSAGE);
		}
	}

	/**
	 * Create a new save
	 */
	private void newSaveGame()
	{            
            SaveGameDialog dialog = new SaveGameDialog(this, this.game.getCurrentLevelNumber(), this.game.getUser().getUserId());
            dialog.setVisible(true);
	}

	/**
	 * Update an existing save
	 */
	private void saveOverGame()
	{
		int row = tableData.getSelectedRow();
		if (row != -1)
		{
			Integer id = Integer.parseInt(tableData.getModel().getValueAt(row, 3).toString());
			boolean save = GameSave.updateGame(id, this.game.getCurrentLevelNumber(), new Date());
			if (save)
			{
				refreshGameSaves();
				JOptionPane.showMessageDialog(this, "Saved Over Game", "Save", JOptionPane.PLAIN_MESSAGE);
			}

		}
		else
		{
			JOptionPane.showMessageDialog(this, "Please select a row", "Invalid Input", JOptionPane.ERROR_MESSAGE);
		}
	}

	/**
	 * Re populate table
	 */
	public void refreshGameSaves()
	{
		loadAllGameSaves();
	}
}
