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
	private final int    FRAME_WIDTH      = 900;
	private final int    FRAME_HEIGHT     = 720;
	private final String BACKGROUND_IMAGE = "resources/Game_UI.jpg";

	private JButton      btnCollect;
	private JButton      btnCount;
	private JButton      btnDrop;
	private JButton      btnUse;
	private JLabel       lblPredators;
	private JList        listInventory;
	private JList        listObjects;
	private JPanel       gameTilesPanel;
	private JProgressBar progBackpackSize;
	private JProgressBar progBackpackWeight;
	private JProgressBar progPlayerStamina;
	private JLabel       lblKiwisCounted;
	private JLabel       lblPlayerName;
	private JLabel       lblPredatorsLeft;
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
		initPlayerInterface();

		update();
	}

	/**
	 * Initializes the frame
	 */
	private void initFrame()
	{
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setTitle("Kiwi Land");
		setSize(FRAME_WIDTH, FRAME_HEIGHT);
		setLocationRelativeTo(null); // centers it in the screen
		addKeyListener(this); // makes keyboard input work
		setResizable(false); // so the screen size cant be changed
		setContentPane(new JLabel(new ImageIcon(BACKGROUND_IMAGE))); // sets the background image

		pack(); // so the screen is as tight as it can be
	}

	/**
	 * Creates and initialises the island grid.
	 */
	private void initIslandGrid()
	{
		int panelWidth = 820;
		int panelHeight = 420;
		int panelxLocation = 40;
		int panelyLocation = 80;

		gameTilesPanel = new JPanel();
		gameTilesPanel.setSize(panelWidth, panelHeight);
		add(gameTilesPanel);
		gameTilesPanel.setLocation(panelxLocation, panelyLocation);

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

	@Override public void paint(Graphics g)
	{
		super.paint(g);
		int yPosition = 40;
		g.setColor(new Color(255, 255, 255));

		g.drawString("Name: " + game.getPlayerName(), 75, yPosition);
		g.drawString("Stamina: " + game.getPlayer().getStaminaLevel() + "/" + game.getPlayer().getMaximumStaminaLevel(),
				250, yPosition);
		g.drawString("Backpack Size: " + game.getPlayer().getCurrentBackpackSize() + "/" + game.getPlayer()
				.getMaximumBackpackSize(), 425, yPosition);
		g.drawString("Backpack Weight: " + game.getPlayer().getCurrentBackpackWeight() + "/" + game.getPlayer()
				.getMaximumBackpackWeight(), 425, yPosition + 17);
		g.drawString("Predators Remaining: " + game.getPredatorsRemaining(), 675, yPosition);
		g.drawString("Kiwis Counted: " + game.getKiwiCount(), 675, yPosition + 17);

	}

	/**
	 * Initialises: Player Name, Player Stamina, Backpack Weight/Fullness and Kiwis/Predators counted/caught
	 */
	private void initPlayerInterface()
	{
		Color backgrondColor = new Color(85, 79, 64);
		int listRows = 5;
		int listWidth = 160;
		int panelxSize = 170;
		int panelySize = 170;
		int panelInvxPosition = 180;
		int panelObjxPosition = 550;
		int panelyPosition = 550;

		// -------------------
		// ---- Inventory ----
		// -------------------
		JScrollPane scrollInventory = new JScrollPane();
		listInventory = new JList();
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
		listInventory.setVisibleRowCount(listRows);
		listInventory.addListSelectionListener(evt -> listInventoryValueChanged());
		listInventory.setFocusable(false);
		listInventory.setFixedCellWidth(listWidth);
		scrollInventory.setViewportView(listInventory);

		// Buttons
		btnUse = new JButton("Use");
		setButtonListProperties(btnUse);
		btnUse.addActionListener(evt -> btnUseActionPerformed());
		btnDrop = new JButton("Drop");
		setButtonListProperties(btnDrop);
		btnDrop.addActionListener(evt -> btnDropActionPerformed());
		JPanel pnlInventoryButtons = new JPanel(new FlowLayout());
		pnlInventoryButtons.add(btnUse);
		pnlInventoryButtons.add(btnDrop);
		pnlInventoryButtons.setBackground(backgrondColor);

		// Label
		JLabel lblInventory = new JLabel("<html> <font color='white'>Inventory</font></html>");

		// Panel Setup
		JPanel pnlInventory = new JPanel();
		pnlInventory.add(lblInventory);
		pnlInventory.add(scrollInventory);
		pnlInventory.add(pnlInventoryButtons);
		pnlInventory.setOpaque(false);
		pnlInventory.setSize(panelxSize, panelySize);
		pnlInventory.setLocation(panelInvxPosition, panelyPosition);
		pnlInventory.setFocusable(false);
		add(pnlInventory);

		// --------------------
		// --- Object Panel ---
		// --------------------
		JScrollPane scrollObjects = new JScrollPane();
		listObjects = new JList();
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
		listObjects.setVisibleRowCount(listRows);
		listObjects.addListSelectionListener(evt -> listObjectsValueChanged());
		listObjects.setFocusable(false);
		listObjects.setFixedCellWidth(listWidth);
		scrollObjects.setViewportView(listObjects);

		// Buttons
		btnCollect = new JButton("Collect");
		setButtonListProperties(btnCollect);
		btnCollect.addActionListener(evt -> btnCollectActionPerformed());
		btnCount = new JButton("Count");
		setButtonListProperties(btnCount);
		btnCount.addActionListener(evt -> btnCountActionPerformed());
		JPanel pnlObjectButtons = new JPanel(new FlowLayout());

		pnlObjectButtons.add(btnCollect);
		pnlObjectButtons.add(btnCount);
		pnlObjectButtons.setBackground(backgrondColor);

		// Label
		JLabel objectLabel = new JLabel("<html> <font color='white'>Objects</font></html>");

		// Panel Setup
		JPanel pnlObjects = new JPanel();
		pnlObjects.add(objectLabel);
		pnlObjects.add(scrollObjects);
		pnlObjects.add(pnlObjectButtons);
		pnlObjects.setOpaque(false);
		pnlObjects.setSize(panelxSize, panelySize);
		pnlObjects.setLocation(panelObjxPosition, panelyPosition);
		pnlObjects.setFocusable(false);
		add(pnlObjects);

		// -----------------------
		// ---- Other Buttons ----
		// -----------------------
		// View Facts Button
		JButton btnViewFacts = new JButton("View Facts");
		btnViewFacts.setFocusable(false);
		btnViewFacts.setToolTipText("");
		btnViewFacts.setMaximumSize(new Dimension(100, 23));
		btnViewFacts.setMinimumSize(new Dimension(100, 23));
		btnViewFacts.setPreferredSize(new Dimension(100, 23));
		btnViewFacts.addActionListener(evt -> btnViewFactActionPerformed());

		// Examine Button
		JButton btnExamine = new JButton("Examine");
		btnExamine.setFocusable(false);
		btnExamine.setToolTipText("");
		btnExamine.setMaximumSize(new Dimension(100, 23));
		btnExamine.setMinimumSize(new Dimension(100, 23));
		btnExamine.setPreferredSize(new Dimension(100, 23));
		btnExamine.addActionListener(evt -> btnExamineActionPerformed());

		// Panel for View Facts and Examine Buttons
		JPanel pnlFacts = new JPanel();
		pnlFacts.add(btnViewFacts);
		pnlFacts.add(btnExamine);
		pnlFacts.setOpaque(false);
		pnlFacts.setSize(100, 90);
		pnlFacts.setLocation(400, 570);
		pnlFacts.setFocusable(false);
		add(pnlFacts);

		// Options button (Invisible)
		JButton btnOptions = new JButton("");
		btnOptions.setFocusable(false);
		btnOptions.setOpaque(false);
		btnOptions.setContentAreaFilled(false);
		btnOptions.setBorderPainted(false);
		btnOptions.setToolTipText("Options");
		btnOptions.setMaximumSize(new Dimension(25, 25));
		btnOptions.setMinimumSize(new Dimension(25, 25));
		btnOptions.setPreferredSize(new Dimension(25, 25));
		btnOptions.addActionListener(evt -> btnOpenOptionsActionPerformed());

		JPanel pnlOptions = new JPanel();
		pnlOptions.add(btnOptions);
		pnlOptions.setOpaque(false);
		pnlOptions.setSize(30, 30);
		pnlOptions.setLocation(824, 1);
		pnlOptions.setFocusable(false);
		add(pnlOptions);
	}

	private void setButtonListProperties(JButton button)
	{
		button.setFocusable(false);
		button.setToolTipText("");
		button.setMaximumSize(new Dimension(75, 23));
		button.setMinimumSize(new Dimension(75, 23));
		button.setPreferredSize(new Dimension(75, 23));
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

		setFocusable(true);
		repaint();
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
			game.nextLevel();
			game.createNewGame();
			update();
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
			case KeyEvent.VK_1:
				game.nextLevel();
				game.createNewGame();
				update();
				break;
		}
	}

	@Override public void keyReleased(KeyEvent e)
	{

	}

	public void btnOpenOptionsActionPerformed()
	{
		System.out.println("You did it!");
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

	private void btnViewFactActionPerformed()
	{

	}

	private void btnExamineActionPerformed()
	{

	}

	/**
	 * This method is called from within the constructor to
	 * initialize the form.
	 * WARNING: Do NOT modify this code. The content of this method is
	 * always regenerated by the Form Editor.
	 */
	private void initComponents()
	{
		JScrollPane scrlInventory = new JScrollPane();
		JScrollPane scrlObjects = new JScrollPane();

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

		btnDrop.setText("Drop");
		btnDrop.addActionListener(evt -> btnDropActionPerformed());

		btnUse.setText("Use");
		btnUse.addActionListener(evt -> btnUseActionPerformed());

		btnCollect.setText("Collect");
		btnCollect.setToolTipText("");
		btnCollect.setMaximumSize(new Dimension(61, 23));
		btnCollect.setMinimumSize(new Dimension(61, 23));
		btnCollect.setPreferredSize(new Dimension(61, 23));
		btnCollect.addActionListener(evt -> btnCollectActionPerformed());

		btnCount.setText("Count");
		btnCount.addActionListener(evt -> btnCountActionPerformed());
	}
}
