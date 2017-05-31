package gameMain;

import gameGUI.gameMainMenu.LoginMenuFrame;
import gameGUI.gameMainMenu.MainMenuFrame;
import gameModel.Game;
import gameModel.Sound;

/**
 * Kiwi Count Project
 *
 * @author AS
 * @version 2011
 */
public class Main {

    /**
     * Main method of Kiwi Count.
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {
        Sound.startMusic();

        // create the game object
        //final Game game = new Game();
        // create the GUI for the game
        //final MainMenuFrame mainMenu = new MainMenuFrame(game);
        final LoginMenuFrame mainMenu = new LoginMenuFrame();
    }

}
