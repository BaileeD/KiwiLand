package gameGUI.gameInGame;

import gameGUI.gameMainMenu.MainMenuFrame;
import gameModel.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/*
 * User interface form for Kiwi Island.
 * 
 * @author AS
 * 
 * @version July 2011
 */
public class GameScreenFrame extends JFrame implements GameEventListener, KeyListener {

    private final int FRAME_WIDTH = 900;
    private final int FRAME_HEIGHT = 720;
    private final String BACKGROUND_IMAGE = "resources/Backgrounds/Game_UI.jpg";

    GameMenuFrame menuFrame;
    private boolean goToDoorShown = false;

    private JButton btnCollect;
    private JButton btnCount;
    private JButton btnDrop;
    private JButton btnUse;
    private JButton btnExamine;
    private JList listInventory;
    private JList listObjects;
    private JPanel gameTilesPanel;
    private Game game;

    /**
     * Creates a GUI for the KiwiIsland game.
     *
     * @param game the game object to represent with this GUI.
     */
    public GameScreenFrame(Game game, MainMenuFrame mainMenuFrame) {
        assert game != null : "Make sure game object is created before UI";

        this.menuFrame = new GameMenuFrame(this, mainMenuFrame);
        this.game = game;
        game.addGameEventListener(this);

        menuFrame.setVisible(false);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowActivated(WindowEvent e) {
                menuFrame.setAlwaysOnTop(true);
            }

            @Override
            public void windowDeactivated(WindowEvent e) {
                menuFrame.setAlwaysOnTop(false);
            }
        });

        initFrame();
        initIslandGrid();
        initPlayerInterface();
        update();
    }

    /**
     * Initializes the frame
     */
    private void initFrame() {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("Kiwi Land - Game");
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
    private void initIslandGrid() {
        int panelxLocation = 50;
        int panelyLocation = 88;
        int panelWidth = 800;
        int panelHeight = 400;

        gameTilesPanel = new JPanel();
        gameTilesPanel.setSize(panelWidth, panelHeight);
        gameTilesPanel.setOpaque(false);
        add(gameTilesPanel);
        gameTilesPanel.setLocation(panelxLocation, panelyLocation);

        // Add the grid
        int rows = game.getNumRows();
        int columns = game.getNumColumns();
        gameTilesPanel.setLayout(new GridLayout(rows, columns));

        // Creates all the grid square panels and adds them to the panel
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < columns; col++) {
                gameTilesPanel.add(new GameScreenPanel(game, row, col));
            }
        }
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        // Sets up the Play Information
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

        // Puts controls for the player to see how to play
        g.setColor(new Color(175, 101, 0));
        g.drawString("Controls", 430, 670);
        g.setColor(new Color(255, 148, 40));
        g.drawString("W: Up    S: Down", 410, 685);
        g.drawString("A:  Left   D: Right", 410, 700);
        g.drawString("Esc: Menu", 410, 715);
    }

    /**
     * Initialises: Player Name, Player Stamina, Backpack Weight/Fullness and
     * Kiwis/Predators counted/caught
     */
    private void initPlayerInterface() {
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
        listInventory.setModel(new AbstractListModel() {
            String[] strings = {"Item 1", "Item 2", "Item 3"};

            public int getSize() {
                return strings.length;
            }

            public Object getElementAt(int i) {
                return strings[i];
            }
        });

        listInventory.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        listInventory.setVisibleRowCount(listRows);
        listInventory.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                listInventoryValueChanged();
            }
        });
        listInventory.setFocusable(false);
        listInventory.setFixedCellWidth(listWidth);
        scrollInventory.setViewportView(listInventory);

        // Buttons
        btnUse = new JButton("Use");
        setButtonListProperties(btnUse);
        btnUse.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                btnUseActionPerformed();
            }
        });
        btnDrop = new JButton("Drop");
        setButtonListProperties(btnDrop);
        btnDrop.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                btnDropActionPerformed();
            }
        });
        JPanel pnlInventoryButtons = new JPanel(new FlowLayout());
        pnlInventoryButtons.add(btnUse);
        pnlInventoryButtons.add(btnDrop);
        pnlInventoryButtons.setOpaque(false);

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
        listObjects.setModel(new AbstractListModel() {
            String[] strings = {"Item 1", "Item 2", "Item 3"};

            public int getSize() {
                return strings.length;
            }

            public Object getElementAt(int i) {
                return strings[i];
            }
        });
        listObjects.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        listObjects.setVisibleRowCount(listRows);
        listObjects.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                listObjectsValueChanged();
            }
        });
        listObjects.setFocusable(false);
        listObjects.setFixedCellWidth(listWidth);
        scrollObjects.setViewportView(listObjects);

        // Buttons
        btnCollect = new JButton("Collect");
        setButtonListProperties(btnCollect);
        btnCollect.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                btnCollectActionPerformed();
            }
        });
        btnCount = new JButton("Count");
        setButtonListProperties(btnCount);
        btnCount.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                btnCountActionPerformed();
            }
        });

        JPanel pnlObjectButtons = new JPanel(new FlowLayout());
        pnlObjectButtons.add(btnCollect);
        pnlObjectButtons.add(btnCount);
        pnlObjectButtons.setOpaque(false);
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
        btnViewFacts.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                btnViewFactActionPerformed();
            }
        });

        // Examine Button
        btnExamine = new JButton("Examine");
        btnExamine.setFocusable(false);
        btnExamine.setToolTipText("");
        btnExamine.setMaximumSize(new Dimension(100, 23));
        btnExamine.setMinimumSize(new Dimension(100, 23));
        btnExamine.setPreferredSize(new Dimension(100, 23));
        btnExamine.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                btnExamineActionPerformed();
            }
        });

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
        btnOptions.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                btnOpenOptionsActionPerformed();
            }
        });

        // -----------------------
        // ---- Sound Buttons ----
        // -----------------------
        JPanel pnlOptions = new JPanel();
        pnlOptions.add(btnOptions);
        pnlOptions.setOpaque(false);
        pnlOptions.setSize(30, 30);
        pnlOptions.setLocation(824, 1);
        pnlOptions.setFocusable(false);
        add(pnlOptions);

        JCheckBox cboxMusic = new JCheckBox("<html><font color='orange'>Music</font></html>");
        cboxMusic.setFocusable(false);
        cboxMusic.setOpaque(false);
        cboxMusic.setSelected(Sound.musicActive);
        cboxMusic.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    Sound.startMusic();
                } else {
                    Sound.stopMusic();
                }
            }
        });
        JCheckBox cboxSoundEffects = new JCheckBox("<html><font color='orange'>Sound Effects</font></html>");
        cboxSoundEffects.setFocusable(false);
        cboxSoundEffects.setOpaque(false);
        cboxSoundEffects.setSelected(Sound.soundEffectsActive);
        cboxSoundEffects.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    Sound.enableSoundEffects();
                } else {
                    Sound.disableSoundEffects();
                }
            }
        });
        JPanel pnlSounds = new JPanel();
        pnlSounds.setLayout(new GridLayout(3, 3));
        pnlSounds.setAlignmentY(JComponent.LEFT_ALIGNMENT);
        pnlSounds.add(cboxMusic);
        pnlSounds.add(cboxSoundEffects);
        pnlSounds.setOpaque(false);
        pnlSounds.setSize(120, 70);
        pnlSounds.setLocation(735, 620);
        pnlSounds.setFocusable(false);
        add(pnlSounds);
    }

    private void setButtonListProperties(JButton button) {
        button.setFocusable(false);
        button.setToolTipText("");
        button.setMaximumSize(new Dimension(75, 23));
        button.setMinimumSize(new Dimension(75, 23));
        button.setPreferredSize(new Dimension(75, 23));
    }

    /**
     * Updates the state of the UI based on the state of the game.
     */
    private void update() {
        // update the grid square panels
        Component[] components = gameTilesPanel.getComponents();
        for (Component c : components) {
            // all components in the panel are GridSquarePanels so we can safely cast
            GameScreenPanel gsp = (GameScreenPanel) c;
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

        if (game.getIsland().getOccupantStringRepresentation(game.getPlayer().getPosition()).equals("F")) {
            btnExamine.setEnabled(true);
        } else {
            btnExamine.setEnabled(false);
        }

        setFocusable(true);
        repaint();
    }

    /**
     * This method is called by the game model every time something changes.
     * Trigger an update.
     */
    @Override
    public void gameStateChanged() {
        remove(gameTilesPanel);
        initIslandGrid();
        update();

        // check for "game over" or "game won"
        if (game.getState() == GameState.LOST) {
            JOptionPane.showMessageDialog(this, game.getLoseMessage(), "Game over!", JOptionPane.INFORMATION_MESSAGE);

            game.createNewGame();
        } else if (game.getState() == GameState.WINNABLE) {
            if (!goToDoorShown) {
                JOptionPane.showMessageDialog(this, game.getWinMessage(), "Go to the Door!",
                        JOptionPane.INFORMATION_MESSAGE);
                goToDoorShown = true;
            }
        } else if (game.getState() == GameState.WON) {
            game.answerQuestion();
            JOptionPane.showMessageDialog(this, game.getWinMessage(), "Well Done!", JOptionPane.INFORMATION_MESSAGE);
            game.createNewGame();
            goToDoorShown = false;
            update();
        } else if (game.messageForPlayer()) {
            JOptionPane.showMessageDialog(this, game.getPlayerMessage(), "Important Information",
                    JOptionPane.INFORMATION_MESSAGE);
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();

        switch (key) {
            case KeyEvent.VK_W: // upwards
                if (game.isPlayerMovePossible(MoveDirection.NORTH)) {
                    game.playerMove(MoveDirection.NORTH);
                }
                break;
            case KeyEvent.VK_A: // to the left
                if (game.isPlayerMovePossible(MoveDirection.WEST)) {
                    game.playerMove(MoveDirection.WEST);
                }
                break;
            case KeyEvent.VK_S: // downwards
                if (game.isPlayerMovePossible(MoveDirection.SOUTH)) {
                    game.playerMove(MoveDirection.SOUTH);

                }
                break;
            case KeyEvent.VK_D: // to the right
                if (game.isPlayerMovePossible(MoveDirection.EAST)) {
                    game.playerMove(MoveDirection.EAST);

                }
                break;
            case KeyEvent.VK_1: // Auto go to next level
                game.nextLevel();
                game.createNewGame();
                update();
                break;
            case KeyEvent.VK_ESCAPE: // Opens Menu
                btnOpenOptionsActionPerformed();
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    public Game getGame() {
        return game;
    }

    /**
     * Opens the in Game Menu
     */
    public void btnOpenOptionsActionPerformed() {
        menuFrame.setVisible(true);
        setEnabled(false);
        setFocusable(false);
    }

    /**
     * Collects the selected item on the ground
     */
    private void btnCollectActionPerformed() {
        Object obj = listObjects.getSelectedValue();
        game.collectItem(obj);
    }

    /**
     * Drops the selected item
     */
    private void btnDropActionPerformed() {
        game.dropItem(listInventory.getSelectedValue());
    }

    private void listObjectsValueChanged() {
        Object occ = listObjects.getSelectedValue();

        if (occ != null) {
            btnCollect.setEnabled(game.canCollect(occ));
            btnCount.setEnabled(game.canCount(occ));
            listObjects.setToolTipText(game.getOccupantDescription(occ));
        }
    }

    private void btnUseActionPerformed() {
        game.useItem(listInventory.getSelectedValue());
    }

    private void listInventoryValueChanged() {
        Object item = listInventory.getSelectedValue();
        btnDrop.setEnabled(true);

        if (item != null) {
            btnUse.setEnabled(game.canUse(item));
            listInventory.setToolTipText(game.getOccupantDescription(item));
        }
    }

    private void btnCountActionPerformed() {
        game.countKiwi();
    }

    private void btnViewFactActionPerformed() {
        game.getDiscoveredFacts();
    }

    private void btnExamineActionPerformed() {
        game.examineFauna();
    }
}
