package gameModel;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.File;
import java.io.IOException;

/**
 * Created by Scott Richards on 14-May-17.
 */
public class Sound
{
	// Music
	private static       Clip    music          = null;
	public static        boolean musicActive    = true;
	private static final String  MUSIC_LOCATION = "resources/Sounds/Background_Music.wav";

	// Sound Effects
	private static       Clip    soundEffects       = null;
	public static        boolean soundEffectsActive = true;
	private static final String  CATCH_LOCATION     = "resources/Sounds/Caught_Predator.wav";
	private static final String  COUNT_LOCATION     = "resources/Sounds/Counted_Kiwi.wav";

	/**
	 * Starts playing the ingame music
	 * It will loop until it is toggled to stop
	 */
	public static void startMusic()
	{
		try
		{
			musicActive = true;
			music = AudioSystem.getClip();
			music.open(AudioSystem.getAudioInputStream(new File(MUSIC_LOCATION)));
			music.loop(Clip.LOOP_CONTINUOUSLY);
		}
		catch (LineUnavailableException e)
		{
			System.out.println("Audio not working");
		}
		catch (UnsupportedAudioFileException e)
		{
			System.out.println("Unsupported Audio File");
		}
		catch (IOException e)
		{
			System.out.println("Cannot find sound file");
		}
	}

	/**
	 * Stops the looping music
	 */
	public static void stopMusic()
	{
		musicActive = false;
		music.stop();
	}

	/**
	 * Plays a sound signifying a kiwi has been counted
	 */
	public static void playCountKiwiSound()
	{
		if (soundEffectsActive)
		{
			try
			{
				soundEffects = AudioSystem.getClip();
				soundEffects.open(AudioSystem.getAudioInputStream(new File(COUNT_LOCATION)));
				soundEffects.start();
			}
			catch (LineUnavailableException e)
			{
				System.out.println("Audio not working");
			}
			catch (UnsupportedAudioFileException e)
			{
				System.out.println("Unsupported Audio File");
			}
			catch (IOException e)
			{
				System.out.println("Cannot find sound file");
			}
		}
	}

	/**
	 * Plays a sound signifying a predator has been caught
	 */
	public static void playCatchPredatorSound()
	{
		if (soundEffectsActive)
		{
			try
			{
				soundEffects = AudioSystem.getClip();
				soundEffects.open(AudioSystem.getAudioInputStream(new File(CATCH_LOCATION)));
				soundEffects.start();
			}
			catch (LineUnavailableException e)
			{
				System.out.println("Audio not working");
			}
			catch (UnsupportedAudioFileException e)
			{
				System.out.println("Unsupported Audio File");
			}
			catch (IOException e)
			{
				System.out.println("Cannot find sound file");
			}
		}
	}

	/**
	 * Enables sound effects (Kiwi and Predators)
	 */
	public static void enableSoundEffects()
	{
		soundEffectsActive = true;
	}

	/**
	 * Disables sound effects (Kiwi and Predators)
	 */
	public static void disableSoundEffects()
	{
		soundEffectsActive = false;
	}
}
