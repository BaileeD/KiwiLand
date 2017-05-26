/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package unitTests;

import gameModel.GameSave;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Date;

import static junit.framework.TestCase.*;

/**
 * @author Chaitanya Varma
 * @version May 2017
 */
public class GameSavesTest
{

	/**
	 * Test of save method, of class GameSave.
	 */
	@Test public void testSaveGame()
	{
		GameSave gameSave = new GameSave();
		gameSave.setPlayerName("John Doe");
		gameSave.setSaveName("ThunderBird");
		gameSave.setLevel(1);
		gameSave.setSaveDate(new Date());
		boolean save = gameSave.save();
		assertTrue(save);
	}

	/**
	 * Test of getAllGameSaves method, of class GameSave.
	 */
	@Test public void testGetAllGameSaves()
	{
		ArrayList<GameSave> gameSaves = GameSave.getAllGameSaves();
		assertTrue(gameSaves.size() > 0);
	}

	/**
	 * Test of getAllGameSaves method, of class GameSave with no player name.
	 */
	@Test public void testGetPlayerGameSaves()
	{
		ArrayList<GameSave> gameSaves = GameSave.getPlayerGameSaves("John Doe");
		assertTrue(gameSaves.size() > 0);
	}

	/**
	 * Test of getLastGameSave method, of class GameSave.
	 */
	@Test public void testLastGameSave()
	{
		GameSave gameSave = GameSave.getLastGameSave("John Doe");
		assertEquals(gameSave.getPlayerName(), "John Doe");
	}

	/**
	 * Test of getAllGameSaves method, of class GameSave with no player name.
	 */
	@Test public void testAllGameSavesInvalidPlayer()
	{
		ArrayList<GameSave> gameSaves = GameSave.getPlayerGameSaves("John Doeeeee");
		assertTrue(gameSaves.isEmpty());
	}

	/**
	 * Test of getLastGameSave method, of class GameSave with no player name.
	 */
	@Test public void testLastGameSaveInvalidPlayer()
	{
		GameSave gameSave = GameSave.getLastGameSave("");
		assertNull(gameSave);
	}

	/**
	 * Test of updateGame method, of class GameSave with new level id
	 */
	@Test public void testSaveOver()
	{
		GameSave gameSave = new GameSave();
		gameSave.setPlayerName("John Doe");
		gameSave.setSaveName("ThunderBird");
		gameSave.setLevel(1);
		gameSave.setSaveDate(new Date());
		boolean save = gameSave.save();
		assertTrue(save);

		ArrayList<GameSave> gameSaves = GameSave.getPlayerGameSaves("John Doe");
		assertEquals(gameSaves.get(0).getLevel(), 1);

		boolean status = GameSave.updateGame(gameSaves.get(0).getGameSaveId(), 3, new Date());
		assertTrue(status);
	}

	/**
	 * Test of deleteSave method, of class GameSave
	 */
	@Test public void testDeleteGameSave()
	{
		ArrayList<GameSave> gameSaves = GameSave.getPlayerGameSaves("John Doe");
		boolean status = GameSave.deleteSave(gameSaves.get(0).getGameSaveId());
		assertTrue(status);
	}

}