/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nz.ac.aut.ense701.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import nz.ac.aut.ense701.gameModel.Game;
import nz.ac.aut.ense701.gameModel.GameSave;

/**
 *
 * @author KCV
 */
public class SaveGameDialog extends JDialog {

    private final int FRAME_WIDTH = 300;
    private final int FRAME_HEIGHT = 300;

    private JTextField textPlayerName;
    private JTextField textSaveName;
    private JLabel labelPlayerName;
    private JLabel labelSaveName;
    private JButton btnSave;
    private JButton btnCancel;
    private Game game;

    public SaveGameDialog(GameScreenFrame frame, Game game) {
        super(frame);
        this.game = game;
        initDialog();
    }

    private void initDialog() {
        this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        this.setTitle("Kiwi Land - Save Game");
        this.setSize(FRAME_WIDTH, FRAME_HEIGHT);
        this.setResizable(false);
        setLocationRelativeTo(null);
        //

        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(new Color(89, 166, 60));
        GridBagConstraints cs = new GridBagConstraints();
        cs.fill = GridBagConstraints.HORIZONTAL;

        labelPlayerName = new JLabel("Player Name: ");
        cs.gridx = 0;
        cs.gridy = 0;
        cs.gridwidth = 1;
        cs.insets = new Insets(10, 10, 0, 10);  //top padding
        panel.add(labelPlayerName, cs);

        textPlayerName = new JTextField(15);
        cs.gridx = 1;
        cs.gridy = 0;
        cs.gridwidth = 20;
        panel.add(textPlayerName, cs);

        labelSaveName = new JLabel("Save Name: ");
        cs.gridx = 0;
        cs.gridy = 1;
        cs.gridwidth = 1;
        panel.add(labelSaveName, cs);

        textSaveName = new JTextField(15);
        cs.gridx = 1;
        cs.gridy = 1;
        cs.gridwidth = 2;
        panel.add(textSaveName, cs);

        btnSave = new JButton("Save Game");
        btnSave.setPreferredSize(new Dimension(100, 25));
        btnSave.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                saveGame();
            }
        });

        btnCancel = new JButton("Cancel");
        btnCancel.setPreferredSize(new Dimension(100, 25));
        btnCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        JPanel bp = new JPanel(new GridBagLayout());
        bp.setBackground(new Color(89, 166, 60));
        cs = new GridBagConstraints();
        cs.fill = GridBagConstraints.HORIZONTAL;
        cs.insets = new Insets(20, 10, 20, 10);
        cs.gridx = 0;
        cs.gridy = 0;
        cs.gridwidth = 1;
        bp.add(btnSave, cs);

        cs.gridx = 1;
        cs.gridy = 0;
        cs.gridwidth = 1;
        bp.add(btnCancel, cs);

        getContentPane().add(panel, BorderLayout.CENTER);
        getContentPane().add(bp, BorderLayout.PAGE_END);

        pack();
    }

    private void saveGame() {
        if (this.textPlayerName.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(this, "Please enter player name", "Invalid Input", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (this.textSaveName.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(this, "Please enter save name", "Invalid Input", JOptionPane.ERROR_MESSAGE);
            return;
        }

        GameSave gameSave = new GameSave();
        gameSave.setPlayerName(this.textPlayerName.getText().trim());
        gameSave.setSaveName(this.textSaveName.getText().trim());
        gameSave.setSaveDate(new Date());
        gameSave.setLevel(this.game.getCurrentLevelNumber());
        boolean save = gameSave.save();
        if (save) {
            JOptionPane.showMessageDialog(this, "Saved Game", "Save", JOptionPane.PLAIN_MESSAGE);
            this.dispose();
        }
    }

}
