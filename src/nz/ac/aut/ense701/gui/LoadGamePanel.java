package nz.ac.aut.ense701.gui;

import nz.ac.aut.ense701.gameModel.Game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.table.DefaultTableModel;
import nz.ac.aut.ense701.gameModel.GameSave;

/**
 * Created by Scott Richards on 07-May-17.
 * Code added by Chaitanya Varma 
 */
public class LoadGamePanel extends JPanel {

    private MainMenuFrame theFrame = null;
    private GameMenuFrame gameFrame = null;
    private JTextField textPlayerName;
    private JLabel labelPlayerName;
    private JButton btnRetrieveSaves;
    private JTable tableData;
    private DefaultTableModel model;
    private JScrollPane scrollPane;
    private JButton btnBack;
    private JButton btnLoad;
    private JButton btnSave;
    private JButton btnSaveOver;
    private JButton btnDeleteSave;
    private int gameLevel;
    private JFrame frame;
    
    /**
     * LoadGamePanel constructor. 
     *
     * @param gameframe reference to GameMenuFrame class
     * @param frame parent frame
     * @param level game level
     */
    public LoadGamePanel(GameMenuFrame gameframe, JFrame frame, int level) {
        gameFrame = gameframe;
        gameLevel = level;
        this.frame = frame;
        setOpaque(false);
        setFocusable(false);

        initPanel();
        getAllGameSaves();
        btnLoad.setVisible(false);
    }
    
   /**
     * LoadGamePanel constructor. 
     *
     * @param frame reference to MainMenuFrame class
     */ 
    public LoadGamePanel(MainMenuFrame frame) {
        theFrame = frame;

        setOpaque(false);
        setFocusable(false);

        initPanel();
        getAllGameSaves();
        btnSave.setVisible(false);
        btnSaveOver.setVisible(false);
        btnDeleteSave.setVisible(false);
    }

    /**
     * initialize the panel
     *
     */ 
    private void initPanel() {
        this.setLayout(new BorderLayout());
        JPanel panel = new JPanel();
        panel.setOpaque(false);
        labelPlayerName = new JLabel("Player Name: ");
        labelPlayerName.setForeground(Color.WHITE);
        panel.add(labelPlayerName);

        textPlayerName = new JTextField(15);
        panel.add(textPlayerName);

        btnRetrieveSaves = new JButton("Get Saves");
        btnRetrieveSaves.setPreferredSize(new Dimension(120, 25));
        btnRetrieveSaves.addActionListener(new ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                retreiveGameSaves();
            }
        });
        panel.add(btnRetrieveSaves);
        this.add(panel, BorderLayout.PAGE_START);

        panel = new JPanel();
        panel.setOpaque(false);
        String[] columnNames = {"Player Name", "Save Name", "Date", "Level", "id"};
        model = new DefaultTableModel(0, 0);
        model.setColumnIdentifiers(columnNames);
        tableData = new JTable(model) {
            @Override
            public Dimension getPreferredScrollableViewportSize() {
                return new Dimension(875, tableData.getRowHeight() * 36);
            }
        };
        tableData.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tableData.removeColumn(tableData.getColumnModel().getColumn(4));

        scrollPane = new JScrollPane(tableData);
        panel.add(scrollPane);
        this.add(panel, BorderLayout.CENTER);

        btnBack = new JButton("Back");
        btnBack.setPreferredSize(new Dimension(120, 25));
        btnBack.addActionListener(new ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                if(theFrame != null)
                    theFrame.openMainMenu();
                else
                {
                    frame.dispose();
                }
            }
        });
        JPanel panelBottom = new JPanel();
        panelBottom.setOpaque(false);
        panelBottom.add(btnBack);

        btnLoad = new JButton("Load Save");
        btnLoad.setPreferredSize(new Dimension(120, 25));
        btnLoad.addActionListener(new ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loadGame();
            }
        });
        panelBottom.add(btnLoad);
        
        btnSave = new JButton("New Save");
        btnSave.setPreferredSize(new Dimension(120, 25));
        btnSave.addActionListener(new ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                newSaveGame();
            }
        });
        panelBottom.add(btnSave);
        
        btnSaveOver = new JButton("Save Over");
        btnSaveOver.setPreferredSize(new Dimension(120, 25));
        btnSaveOver.addActionListener(new ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveOverGame();
            }
        });
        panelBottom.add(btnSaveOver);

        btnDeleteSave = new JButton("Delete Save");
        btnDeleteSave.setPreferredSize(new Dimension(120, 25));
        btnDeleteSave.addActionListener(new ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteSave();
            }
        });
        panelBottom.add(btnDeleteSave);

        this.add(panelBottom, BorderLayout.PAGE_END);
    }

    /**
     * Retrieve game saves of a player
     */
    private void retreiveGameSaves() {
        if (this.textPlayerName.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(this, "Please enter player name", "Invalid Input", JOptionPane.ERROR_MESSAGE);
            return;
        }

        ArrayList<GameSave> gameSaves = GameSave.getPlayerGameSaves(this.textPlayerName.getText().trim());
        setTableData(gameSaves);
    }
    
    /**
     * Retrieve all game saves
     */
    private void getAllGameSaves()
    {
        ArrayList<GameSave> gameSaves = GameSave.getAllGameSaves();
        setTableData(gameSaves);
    }

    /**
     * Populate JTable with the game saves data
     */
    private void setTableData(ArrayList<GameSave> gameSaves) {
        model.setRowCount(0);
        if (gameSaves != null) {
            for (GameSave gameSave : gameSaves) {
                model.addRow(new Object[]{
                    gameSave.getPlayerName(),
                    gameSave.getSaveName(),
                    gameSave.getSaveDate(),
                    gameSave.getLevel(),
                    gameSave.getGameSaveId()
                });
            }
        }
        enableButtons(model.getRowCount() > 0 ? true : false);
    }

    /**
     * enable or disable the buttons based on number of rows in table
     */
    private void enableButtons(boolean enable) {
        this.btnDeleteSave.setEnabled(enable);
        this.btnSaveOver.setEnabled(enable);
        this.btnLoad.setEnabled(enable);
    }

    /**
     * Load a game
     */
    private void loadGame() {
        int row = tableData.getSelectedRow();
        if (row != -1) {
            Integer level = Integer.parseInt(tableData.getModel().getValueAt(row, 3).toString());
            theFrame.openSaveGameMenu(level);
        } else {
            JOptionPane.showMessageDialog(this, "Please select a row", "Invalid Input", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Delete a save
     */
    private void deleteSave() {
        int row = tableData.getSelectedRow();
        if (row != -1) {
            Integer value = Integer.parseInt(tableData.getModel().getValueAt(row, 4).toString());
            GameSave.deleteSave(value);
            model.removeRow(row);
            enableButtons(model.getRowCount() > 0 ? true : false);
        } else {
            JOptionPane.showMessageDialog(this, "Please select a row", "Invalid Input", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    /**
     * Create a new save
     */
    private void newSaveGame()
    {
        SaveGameDialog dialog = new SaveGameDialog(this, gameLevel);
        dialog.setVisible(true);
    }
    
    /**
     * Update an existing save
     */
    private void saveOverGame()
    {
        int row = tableData.getSelectedRow();
        if (row != -1) {
            Integer id = Integer.parseInt(tableData.getModel().getValueAt(row, 4).toString());
            boolean save = GameSave.updateGame(id, gameLevel, new Date());
            if (save) {
                refreshGameSaves();
                JOptionPane.showMessageDialog(this, "Saved Over Game", "Save", JOptionPane.PLAIN_MESSAGE);
            }
            
        } else {
            JOptionPane.showMessageDialog(this, "Please select a row", "Invalid Input", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    /**
     * Re populate table
     */
    public void refreshGameSaves()
    {
        getAllGameSaves();
    }
}
