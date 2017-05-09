/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nz.ac.aut.ense701.gameModel;

import java.util.ArrayList;
import java.util.Date;
import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNull;
import static junit.framework.TestCase.assertTrue;
import org.junit.Test;

/**
 *
 * @author Chaitanya Varma
 * @version May 2017
 */
public class GameSavesTest {
    
    /**
     * Test of save method, of class GameSave.
     */
    @Test
    public void testSaveGame() {
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
    @Test
    public void testGetAllGameSaves() {
        ArrayList<GameSave> gameSaves = GameSave.getAllGameSaves("John Doe");
        assertTrue(gameSaves.size() > 0);
    }
    
    /**
     * Test of getLastGameSave method, of class GameSave.
     */
    @Test
    public void testLastGameSave() {
        GameSave gameSave = GameSave.getLastGameSave("John Doe");
        assertEquals(gameSave.getPlayerName(), "John Doe");
    }
    
    /**
     * Test of getAllGameSaves method, of class GameSave with no player name.
     */
    @Test
    public void testAllGameSavesInvalidPlayer() {
        ArrayList<GameSave> gameSaves = GameSave.getAllGameSaves("");
        assertTrue(gameSaves.isEmpty());
    }
    
    /**
     * Test of getLastGameSave method, of class GameSave with no player name.
     */
    @Test
    public void testLastGameSaveInvalidPlayer() {
        GameSave gameSave = GameSave.getLastGameSave("");
        assertNull(gameSave);
    }
}
