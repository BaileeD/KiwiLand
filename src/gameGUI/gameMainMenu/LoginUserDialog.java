package gameGUI.gameMainMenu;

import gameModel.Game;
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
 * This class is used to for login a user
 * 
 * @author Chaitanya Varma
 * @Version May 2017
 */
public class LoginUserDialog extends JDialog {

    private final int FRAME_WIDTH = 300;
    private final int FRAME_HEIGHT = 300;

    private JTextField textUserName;
    private JPasswordField textPassword;
    private JLabel labelUserName;
    private JLabel labelPassword;
    private JButton btnLogin;
    private JButton btnCancel;
    LoginMenuFrame loginMenuFrame;

    /**
     * Initial LoginUserDialog constructor.
     *
     * @param loginMenuFrame reference to LoginMenuFrame class
     */
    public LoginUserDialog(LoginMenuFrame loginMenuFrame) {
        this.loginMenuFrame = loginMenuFrame;
        initDialog();
    }

    /**
     * Initialize the dialog
     */
    private void initDialog() {
        this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        this.setTitle("Kiwi Land - Login User");
        this.setSize(FRAME_WIDTH, FRAME_HEIGHT);
        this.setResizable(false);
        setLocationRelativeTo(null);
        //

        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(new Color(89, 166, 60));
        GridBagConstraints cs = new GridBagConstraints();
        cs.fill = GridBagConstraints.HORIZONTAL;

        labelUserName = new JLabel("User Name: ");
        labelUserName.setForeground(Color.WHITE);
        cs.gridx = 0;
        cs.gridy = 0;
        cs.gridwidth = 1;
        cs.insets = new Insets(10, 10, 0, 10);  //top padding
        panel.add(labelUserName, cs);

        textUserName = new JTextField(15);
        cs.gridx = 1;
        cs.gridy = 0;
        cs.gridwidth = 2;
        panel.add(textUserName, cs);

        labelPassword = new JLabel("Password: ");
        labelPassword.setForeground(Color.WHITE);
        cs.gridx = 0;
        cs.gridy = 1;
        cs.gridwidth = 1;
        panel.add(labelPassword, cs);

        textPassword = new JPasswordField(15);
        cs.gridx = 1;
        cs.gridy = 1;
        cs.gridwidth = 2;
        panel.add(textPassword, cs);

        btnLogin = new JButton("Login");
        btnLogin.setPreferredSize(new Dimension(100, 25));
        btnLogin.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                loginUser();
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
        bp.add(btnLogin, cs);

        cs.gridx = 1;
        cs.gridy = 0;
        cs.gridwidth = 1;
        bp.add(btnCancel, cs);

        getContentPane().add(panel, BorderLayout.CENTER);
        getContentPane().add(bp, BorderLayout.PAGE_END);

        pack();
    }

    /**
     * logs in a user
     */
    private void loginUser() {
        String userName = this.textUserName.getText().trim();
        if (this.textUserName.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(this, "Please enter user name", "Invalid Input", JOptionPane.ERROR_MESSAGE);
            return;
        }
        String password = new String(this.textPassword.getPassword());
        if (password.trim().equals("")) {
            JOptionPane.showMessageDialog(this, "Please enter password", "Invalid Input", JOptionPane.ERROR_MESSAGE);
            return;
        } 

        User user = User.loginUser(userName, password);
        if(user == null)
        {
            JOptionPane.showMessageDialog(this, "Invalid user credentials", "Invalid Input", JOptionPane.ERROR_MESSAGE);
        }
        else
        {
            final Game game = new Game(user);
            final MainMenuFrame mainMenu = new MainMenuFrame(game);
            
            this.dispose();
            loginMenuFrame.dispose();
        }
    }
}
