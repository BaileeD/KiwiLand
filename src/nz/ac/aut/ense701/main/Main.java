package nz.ac.aut.ense701.main;

import nz.ac.aut.ense701.gameModel.Game;
import nz.ac.aut.ense701.gui.GameScreenFrame;
import nz.ac.aut.ense701.gui.MainMenuFrame;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;
import java.io.File;
import java.net.URL;

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
	public static void main(String[] args) throws Exception
	{
		Clip clip = AudioSystem.getClip();
		clip.open(AudioSystem.getAudioInputStream(new File("resources/Background_Music.wav")));
		clip.loop(Clip.LOOP_CONTINUOUSLY);

		// create the game object
		final Game game = new Game();
		// create the GUI for the game
		final MainMenuFrame mainMenu = new MainMenuFrame(game);
	}

}
