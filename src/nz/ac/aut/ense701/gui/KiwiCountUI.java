package nz.ac.aut.ense701.gui;

import nz.ac.aut.ense701.gameModel.Game;
import nz.ac.aut.ense701.gameModel.GameEventListener;
import nz.ac.aut.ense701.gameModel.GameState;
import nz.ac.aut.ense701.gameModel.MoveDirection;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/*
 * User interface form for Kiwi Island.
 * 
 * @author AS
 * 
 * @version July 2011
 */
public class KiwiCountUI extends JFrame implements GameEventListener, KeyListener
{
	private JButton      btnCollect;
	private JButton      btnCount;
	private JButton      btnDrop;
	private JButton      btnUse;
	private JLabel       lblKiwisCounted;
	private JLabel       lblPredators;
	private JList        listInventory;
	private JList        listObjects;
	private JPanel       gameTilesPanel;
	private JProgressBar progBackpackSize;
	private JProgressBar progBackpackWeight;
	private JProgressBar progPlayerStamina;
	private JLabel       txtKiwisCounted;
	private JLabel       txtPlayerName;
	private JLabel       txtPredatorsLeft;
	private Game         game;

	/**
	 * Creates a GUI for the KiwiIsland game.
	 *
	 * @param game the game object to represent with this GUI.
	 */
	public KiwiCountUI(Game game)
	{
		assert game != null : "Make sure game object is created before UI";

		this.game = game;
		game.addGameEventListener(this);

		initFrame();
		initIslandGrid();

		update();
	}

	private void initFrame()
	{
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setTitle("Kiwi Land");
		setSize(900, 770);
		setLocationRelativeTo(null);
		addKeyListener(this);
		setResizable(false);

		setLayout(new BorderLayout());
		setContentPane(new JLabel(new ImageIcon("resources/Game_UI.jpg")));

		pack();
	}

	/**
	 * Creates and initialises the island grid.
	 */
	private void initIslandGrid()
	{
		gameTilesPanel = new JPanel();
		gameTilesPanel.setSize(820, 420);
		add(gameTilesPanel);
		gameTilesPanel.setLocation(40, 80);

		// Add the grid
		int rows = game.getNumRows();
		int columns = game.getNumColumns();
		gameTilesPanel.setLayout(new GridLayout(rows, columns));

		// Creates all the grid square panels and adds them to the panel
		for (int row = 0; row < rows; row++)
		{
			for (int col = 0; col < columns; col++)
			{
				gameTilesPanel.add(new GridSquarePanel(game, row, col));
			}
		}
	}

	/**
	 * Updates the state of the UI based on the state of the game.
	 */
	private void update()
	{
		// update the grid square panels
		Component[] components = gameTilesPanel.getComponents();
		for (Component c : components)
		{
			// all components in the panel are GridSquarePanels so we can safely cast
			GridSquarePanel gsp = (GridSquarePanel) c;
			gsp.update();
		}
/*
		// update player information
		int[] playerValues = game.getPlayerValues();
		txtPlayerName.setText(game.getPlayerName());
		progPlayerStamina.setMaximum(playerValues[Game.MAXSTAMINA_INDEX]);
		// Sets maximum each time so the max can be changed while playing, and still never go over the max??
		progPlayerStamina.setValue(playerValues[Game.STAMINA_INDEX]);
		progBackpackWeight.setMaximum(playerValues[Game.MAXWEIGHT_INDEX]);
		progBackpackWeight.setValue(playerValues[Game.WEIGHT_INDEX]);
		progBackpackSize.setMaximum(playerValues[Game.MAXSIZE_INDEX]);
		progBackpackSize.setValue(playerValues[Game.SIZE_INDEX]);

		// Update Kiwi and Predator information
		txtKiwisCounted.setText(Integer.toString(game.getKiwiCount()));
		txtPredatorsLeft.setText(Integer.toString(game.getPredatorsRemaining()));

		// update inventory list
		listInventory.setListData(game.getPlayerInventory());
		listInventory.clearSelection();
		listInventory.setToolTipText(null);
		btnUse.setEnabled(false);
		btnDrop.setEnabled(false);

		// update list of visible objects
		listObjects.setListData(game.getOccupantsPlayerPosition());
		listObjects.clearSelection();
		listObjects.setToolTipText(null);
		btnCollect.setEnabled(false);
		btnCount.setEnabled(false);
*/
	}

	/**
	 * This method is called by the game model every time something changes.
	 * Trigger an update.
	 */
	@Override public void gameStateChanged()
	{
		update();

		// check for "game over" or "game won"
		if (game.getState() == GameState.LOST)
		{
			JOptionPane.showMessageDialog(this, game.getLoseMessage(), "Game over!", JOptionPane.INFORMATION_MESSAGE);
			game.createNewGame();
		}
		else if (game.getState() == GameState.WON)
		{
			JOptionPane.showMessageDialog(this, game.getWinMessage(), "Well Done!", JOptionPane.INFORMATION_MESSAGE);
			game.createNewGame();
		}
		else if (game.messageForPlayer())
		{
			JOptionPane.showMessageDialog(this, game.getPlayerMessage(), "Important Information",
					JOptionPane.INFORMATION_MESSAGE);
		}
	}

	@Override public void keyTyped(KeyEvent e)
	{

	}

	@Override public void keyPressed(KeyEvent e)
	{
		int key = e.getKeyCode();

		switch (key)
		{
			case KeyEvent.VK_W: // upwards
				if (game.isPlayerMovePossible(MoveDirection.NORTH))
				{
					game.playerMove(MoveDirection.NORTH);
				}
				break;
			case KeyEvent.VK_A: // to the left
				if (game.isPlayerMovePossible(MoveDirection.WEST))
				{
					game.playerMove(MoveDirection.WEST);
				}
				break;
			case KeyEvent.VK_S: // downwards
				if (game.isPlayerMovePossible(MoveDirection.SOUTH))
				{
					game.playerMove(MoveDirection.SOUTH);
				}
				break;
			case KeyEvent.VK_D: // to the right
				if (game.isPlayerMovePossible(MoveDirection.EAST))
				{
					game.playerMove(MoveDirection.EAST);
				}
				break;
		}
	}

	@Override public void keyReleased(KeyEvent e)
	{

	}

	private void btnCollectActionPerformed()
	{
		Object obj = listObjects.getSelectedValue();
		game.collectItem(obj);
	}

	private void btnDropActionPerformed()
	{
		game.dropItem(listInventory.getSelectedValue());
	}

	private void listObjectsValueChanged()
	{
		Object occ = listObjects.getSelectedValue();

		if (occ != null)
		{
			btnCollect.setEnabled(game.canCollect(occ));
			btnCount.setEnabled(game.canCount(occ));
			listObjects.setToolTipText(game.getOccupantDescription(occ));
		}
	}

	private void btnUseActionPerformed()
	{
		game.useItem(listInventory.getSelectedValue());
	}

	private void listInventoryValueChanged()
	{
		Object item = listInventory.getSelectedValue();
		btnDrop.setEnabled(true);

		if (item != null)
		{
			btnUse.setEnabled(game.canUse(item));
			listInventory.setToolTipText(game.getOccupantDescription(item));
		}
	}

	private void btnCountActionPerformed()
	{
		game.countKiwi();
	}


	/**
	 * This method is called from within the constructor to
	 * initialize the form.
	 * WARNING: Do NOT modify this code. The content of this method is
	 * always regenerated by the Form Editor.
	 */
	private void initComponents()
	{
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setTitle("Kiwi Count");

		JPanel pnlContent = new JPanel();
		JPanel pnlPlayerData = new JPanel();
		JPanel pnlControls = new JPanel();

		JPanel pnlPlayer = new JPanel();
		JPanel pnlInventory = new JPanel();
		JPanel pnlObjects = new JPanel();

		gameTilesPanel = new JPanel();

		txtPlayerName = new JLabel();
		progPlayerStamina = new JProgressBar();
		progBackpackWeight = new JProgressBar();
		progBackpackSize = new JProgressBar();
		lblPredators = new JLabel();
		txtPredatorsLeft = new JLabel();
		lblKiwisCounted = new JLabel();
		txtKiwisCounted = new JLabel();

		listInventory = new JList();
		btnDrop = new JButton();
		btnUse = new JButton();

		listObjects = new JList();
		btnCollect = new JButton();
		btnCount = new JButton();

		JLabel lblPlayerName = new JLabel();
		JLabel lblPlayerStamina = new JLabel();
		JLabel lblBackpackWeight = new JLabel();
		JLabel lblBackpackSize = new JLabel();
		JScrollPane scrlInventory = new JScrollPane();
		JScrollPane scrlObjects = new JScrollPane();

		//--//

		lblPlayerName.setText("Name:");
		txtPlayerName.setText("Player Name");
		lblPlayerStamina.setText("Stamina:");
		progPlayerStamina.setStringPainted(true);
		lblBackpackWeight.setText("Backpack Weight:");
		progBackpackWeight.setStringPainted(true);
		lblBackpackSize.setText("Backpack Size:");
		progBackpackSize.setStringPainted(true);
		lblPredators.setText("Predators Left:");
		lblKiwisCounted.setText("Kiwis Counted :");
		txtKiwisCounted.setText("0");
		txtPredatorsLeft.setText("P");

		pnlInventory.setBorder(BorderFactory.createTitledBorder("Inventory"));

		listInventory.setModel(new AbstractListModel()
		{
			String[] strings = { "Item 1", "Item 2", "Item 3" };

			public int getSize()
			{
				return strings.length;
			}

			public Object getElementAt(int i)
			{
				return strings[i];
			}
		});
		listInventory.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		listInventory.setVisibleRowCount(3);
		listInventory.addListSelectionListener(evt -> listInventoryValueChanged());

		scrlInventory.setViewportView(listInventory);


		btnDrop.setText("Drop");
		btnDrop.addActionListener(evt -> btnDropActionPerformed());

		btnUse.setText("Use");
		btnUse.addActionListener(evt -> btnUseActionPerformed());

		pnlObjects.setBorder(BorderFactory.createTitledBorder("Objects"));
		GridBagLayout pnlObjectsLayout = new GridBagLayout();
		pnlObjectsLayout.columnWidths = new int[] { 0, 5, 0 };
		pnlObjectsLayout.rowHeights = new int[] { 0, 5, 0 };
		pnlObjects.setLayout(pnlObjectsLayout);

		listObjects.setModel(new AbstractListModel()
		{
			String[] strings = { "Item 1", "Item 2", "Item 3" };

			public int getSize()
			{
				return strings.length;
			}

			public Object getElementAt(int i)
			{
				return strings[i];
			}
		});
		listObjects.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		listObjects.setVisibleRowCount(3);
		listObjects.addListSelectionListener(evt -> listObjectsValueChanged());

		scrlObjects.setViewportView(listObjects);

		btnCollect.setText("Collect");
		btnCollect.setToolTipText("");
		btnCollect.setMaximumSize(new Dimension(61, 23));
		btnCollect.setMinimumSize(new Dimension(61, 23));
		btnCollect.setPreferredSize(new Dimension(61, 23));
		btnCollect.addActionListener(evt -> btnCollectActionPerformed());

		btnCount.setText("Count");
		btnCount.addActionListener(evt -> btnCountActionPerformed());

		pnlContent.add(pnlControls, BorderLayout.EAST);

		getContentPane().add(pnlContent, BorderLayout.CENTER);
		pack();
	}
}
