package gameGUI.gameMainMenu;

import gameGUI.gameInGame.GameScreenFrame;
import gameModel.Game;
import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionListener;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

/**
 * This class shows initial login screen
 * 
 * @author Chaitanya Varma
 * @Version May 2017
 */
public class LoginMenuFrame extends JFrame {

    private final int FRAME_WIDTH = 900;
    private final int FRAME_HEIGHT = 720;

    private String BACKGROUND_TEXT = "resources/Backgrounds/Main_Menu_UI.jpg";
    private String BACKGROUND_PLAIN = "resources/Backgrounds/Menu_UI.jpg";
    private JLabel backgroundLbl;

    private CardLayout cardLayout;

    /**
     * default constructor
     */
    public LoginMenuFrame() {
        initPanel();
        initFrame();
        initButtons();
    }

    /**
     * Initializes the frame
     */
    private void initFrame() {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("Kiwi Land - Login");
        backgroundLbl = new JLabel();
        backgroundLbl.setIcon(new ImageIcon(BACKGROUND_TEXT));
        setContentPane(backgroundLbl); // sets the background image
        setVisible(true);
        setLayout(new FlowLayout());
        setSize(FRAME_WIDTH, FRAME_HEIGHT);
        setLocationRelativeTo(null); // centers it in the screen
        setResizable(false); // so the screen size cant be changed

        //pack(); // so the screen is as tight as it can be
        add(pnlButtons);
    }

    private JPanel pnlButtons;
    private JButton btnLogin;
    private JButton btnRegister;

    /**
     * initialized the panel
     */
    private void initPanel() {
        cardLayout = new CardLayout();

        pnlButtons = new JPanel();
        pnlButtons.setOpaque(false);
        pnlButtons.setLayout(cardLayout);

        btnLogin = new JButton("LOGIN");
        btnRegister = new JButton("REGISTER");

        setButtonProperties(btnLogin);
        setButtonProperties(btnRegister);

        pnlButtons.setOpaque(false);
        pnlButtons.setFocusable(false);
        BoxLayout layout = new BoxLayout(pnlButtons, BoxLayout.Y_AXIS);
        pnlButtons.setLayout(layout);

        int buttonSpacing = 30;
        pnlButtons.add(Box.createRigidArea(new Dimension(1, buttonSpacing * 6)));
        pnlButtons.add(btnLogin);
        pnlButtons.add(Box.createRigidArea(new Dimension(1, buttonSpacing)));
        pnlButtons.add(btnRegister);
    }

    /**
     * sets properties for button
     */
    private void setButtonProperties(JButton aButton) {
        aButton.setFocusable(false);
        aButton.setToolTipText("");
        aButton.setFont(new Font("Arial", Font.PLAIN, 25));
        aButton.setMaximumSize(new Dimension(350, 80));
        aButton.setMinimumSize(new Dimension(350, 80));
        aButton.setPreferredSize(new Dimension(350, 80));
    }

    public CardLayout getCardLayout() {
        return cardLayout;
    }

    /**
     * exits a game
     */
    public void exitGame() {
        System.exit(0);
    }
    
    /**
     * adds action listeners to button controls
     */
    private void initButtons() {
        btnLogin.addActionListener(new ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
               openLoginDialog();    
            }
        });

        btnRegister.addActionListener(new ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                openRegisterDialog();
            }
        });

    }
    
    /**
     * shows login dialog
     */
    private void openLoginDialog(){
        LoginUserDialog dialog = new LoginUserDialog(this);
        dialog.setModal(true);
        dialog.setVisible(true);
    }
    
    /**
     * shows new user registration dialog
     */
    private void openRegisterDialog(){
        RegisterUserDialog dialog = new RegisterUserDialog(this);
        dialog.setModal(true);
        dialog.setVisible(true);
    }
}
