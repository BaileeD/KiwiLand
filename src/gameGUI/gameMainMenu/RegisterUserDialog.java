package gameGUI.gameMainMenu;

import gameModel.User;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

/**
 * This class is used to for registering a new user
 * 
 * @author Chaitanya Varma
 * @Version May 2017
 */
public class RegisterUserDialog extends JDialog {

    private final int FRAME_WIDTH = 300;
    private final int FRAME_HEIGHT = 300;

    private JTextField textPlayerName;
    private JTextField textUserName;
    private JPasswordField textPassword;
    private JLabel labelPlayerName;
    private JLabel labelUserName;
    private JLabel labelPassword;
    private JButton btnRegister;
    private JButton btnCancel;
    LoginMenuFrame loginMenuFrame;

    /**
     * Initial RegisterUserDialog constructor.
     *
     * @param loginMenuFrame reference to LoginMenuFrame class
     */
    public RegisterUserDialog(LoginMenuFrame loginMenuFrame) {
        this.loginMenuFrame = loginMenuFrame;
        initDialog();
    }

    /**
     * Initialize the dialog
     */
    private void initDialog() {
        this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        this.setTitle("Kiwi Land - Register User");
        this.setSize(FRAME_WIDTH, FRAME_HEIGHT);
        this.setResizable(false);
        setLocationRelativeTo(null);
        //

        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(new Color(89, 166, 60));
        GridBagConstraints cs = new GridBagConstraints();
        cs.fill = GridBagConstraints.HORIZONTAL;

        labelPlayerName = new JLabel("Player Name: ");
        labelPlayerName.setForeground(Color.WHITE);
        cs.gridx = 0;
        cs.gridy = 0;
        cs.gridwidth = 1;
        cs.insets = new Insets(10, 10, 0, 10);  //top padding
        panel.add(labelPlayerName, cs);

        textPlayerName = new JTextField(15);
        cs.gridx = 1;
        cs.gridy = 0;
        cs.gridwidth = 2;
        panel.add(textPlayerName, cs);

        labelUserName = new JLabel("User Name: ");
        labelUserName.setForeground(Color.WHITE);
        cs.gridx = 0;
        cs.gridy = 1;
        cs.gridwidth = 1;
        panel.add(labelUserName, cs);

        textUserName = new JTextField(15);
        cs.gridx = 1;
        cs.gridy = 1;
        cs.gridwidth = 2;
        panel.add(textUserName, cs);

        labelPassword = new JLabel("Password: ");
        labelPassword.setForeground(Color.WHITE);
        cs.gridx = 0;
        cs.gridy = 2;
        cs.gridwidth = 1;
        panel.add(labelPassword, cs);

        textPassword = new JPasswordField(15);
        cs.gridx = 1;
        cs.gridy = 2;
        cs.gridwidth = 2;
        panel.add(textPassword, cs);

        btnRegister = new JButton("Register");
        btnRegister.setPreferredSize(new Dimension(100, 25));
        btnRegister.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                registerUser();
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
        bp.add(btnRegister, cs);

        cs.gridx = 1;
        cs.gridy = 0;
        cs.gridwidth = 1;
        bp.add(btnCancel, cs);

        getContentPane().add(panel, BorderLayout.CENTER);
        getContentPane().add(bp, BorderLayout.PAGE_END);

        pack();
    }

    /**
     * registers a user
     */
    private void registerUser() {
        String playerName = this.textPlayerName.getText().trim();
        if (playerName.equals("")) {
            JOptionPane.showMessageDialog(this, "Please enter player name", "Invalid Input", JOptionPane.ERROR_MESSAGE);
            return;
        }
        String userName = this.textUserName.getText().trim();
        if (userName.equals("")) {
            JOptionPane.showMessageDialog(this, "Please enter user name", "Invalid Input", JOptionPane.ERROR_MESSAGE);
            return;
        }
        String password = new String(this.textPassword.getPassword());
        if (password.trim().equals("")) {
            JOptionPane.showMessageDialog(this, "Please enter password", "Invalid Input", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        if(User.isUserExists(userName))
        {
            JOptionPane.showMessageDialog(this, "User name already taken. Please enter another user name.", "User Exists", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        if(User.createNewUser(playerName, userName, password))
        {
            JOptionPane.showMessageDialog(this, "Registration is successful. Please login to play a game.", "Registration complete", JOptionPane.ERROR_MESSAGE);
            this.dispose();
        }
    }

}
