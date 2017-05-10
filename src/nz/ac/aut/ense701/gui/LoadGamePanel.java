package nz.ac.aut.ense701.gui;

import nz.ac.aut.ense701.gameModel.Game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;
import nz.ac.aut.ense701.gameModel.GameSave;

/**
 * Created by Scott Richards on 07-May-17.
 */
public class LoadGamePanel extends JPanel {

    private MainMenuFrame theFrame;
    private JTextField textPlayerName;
    private JLabel labelPlayerName;
    private JButton btnRetrieveSaves;
    private JTable tableData;
    private DefaultTableModel model;
    private JScrollPane scrollPane;
    private JButton btnBack;
    private JButton btnLoad;
    private JButton btnDeleteSave;

    public LoadGamePanel(MainMenuFrame frame) {
        theFrame = frame;

        setOpaque(false);
        setFocusable(false);

        initPanel();
        enableButtons(false);
    }

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
        String[] columnNames = {"Save Name", "Date", "Level", "id"};
        model = new DefaultTableModel(0, 0);
        model.setColumnIdentifiers(columnNames);
        tableData = new JTable(model) {
            @Override
            public Dimension getPreferredScrollableViewportSize() {
                return new Dimension(875, tableData.getRowHeight() * 36);
            }
        };
        tableData.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tableData.removeColumn(tableData.getColumnModel().getColumn(3));

        scrollPane = new JScrollPane(tableData);
        panel.add(scrollPane);
        this.add(panel, BorderLayout.CENTER);

        btnBack = new JButton("Back");
        btnBack.setPreferredSize(new Dimension(120, 25));
        btnBack.addActionListener(new ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                theFrame.openMainMenu();
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

    private void retreiveGameSaves() {
        if (this.textPlayerName.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(this, "Please enter player name", "Invalid Input", JOptionPane.ERROR_MESSAGE);
            return;
        }

        ArrayList<GameSave> gameSaves = GameSave.getAllGameSaves(this.textPlayerName.getText().trim());
        setTableData(gameSaves);
    }

    private void setTableData(ArrayList<GameSave> gameSaves) {
        model.setRowCount(0);
        if (gameSaves != null) {
            for (GameSave gameSave : gameSaves) {
                model.addRow(new Object[]{
                    gameSave.getSaveName(),
                    gameSave.getSaveDate(),
                    gameSave.getLevel(),
                    gameSave.getGameSaveId()
                });
            }
        }
        enableButtons(model.getRowCount() > 0 ? true : false);
    }

    private void enableButtons(boolean enable) {
        this.btnDeleteSave.setEnabled(enable);
        this.btnLoad.setEnabled(enable);
    }

    private void loadGame() {
        int row = tableData.getSelectedRow();
        if (row != -1) {
            Integer value = Integer.parseInt(tableData.getModel().getValueAt(row, 2).toString());
            theFrame.openSaveGameMenu(value);
        } else {
            JOptionPane.showMessageDialog(this, "Please select a row", "Invalid Input", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void deleteSave() {
        int row = tableData.getSelectedRow();
        if (row != -1) {
            Integer value = Integer.parseInt(tableData.getModel().getValueAt(row, 3).toString());
            GameSave.deleteSave(value);
            model.removeRow(row);
        } else {
            JOptionPane.showMessageDialog(this, "Please select a row", "Invalid Input", JOptionPane.ERROR_MESSAGE);
        }
    }
}
