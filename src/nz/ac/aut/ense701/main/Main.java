package nz.ac.aut.ense701.main;

import nz.ac.aut.ense701.gameModel.Game;
import nz.ac.aut.ense701.gui.GameScreenFrame;
import nz.ac.aut.ense701.gui.MainMenuFrame;

/**
 * Kiwi Count Project
 * 
 * @author AS
 * @version 2011
 */
public class Main 
{
    /**
     * Main method of Kiwi Count.
     * 
     * @param args the command line arguments
     */
    public static void main(String[] args) 
    {

        // create the game object
        final Game game = new Game();
        // create the GUI for the game
        //final GameScreenFrame gui  = new GameScreenFrame(game);
        final MainMenuFrame mainMenu = new MainMenuFrame(game);
        // make the GUI visible
        java.awt.EventQueue.invokeLater(new Runnable() 
        {
            @Override
            public void run() 
            {
               // gui.setVisible(true);
            }
        });
    }

}