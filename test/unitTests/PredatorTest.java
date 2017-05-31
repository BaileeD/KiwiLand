package unitTests;

import gameModel.Game;
import gameModel.Island;
import gameModel.Position;
import gameModel.gameTiles.Predator;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * @author AS
 * @version 2011
 */
public class PredatorTest
{
	private Predator rat;
	private Position position;
	private Island   island;
        private Game     game;

	public PredatorTest()
	{
	}

	@Before public void setUp()
	{
		island = new Island(5, 5);
                game = new Game();
		position = new Position(island, 4, 4);
		rat = new Predator(position, "Rat", "A norway rat", game, island);
	}

	@After public void tearDown()
	{
		island = null;
		position = null;
		rat = null;
	}

	/**
	 * Test of getStringRepresentation method, of class Predator.
	 */
	@Test public void testGetStringRepresentation()
	{
		String expResult = "P";
		String result = rat.getStringRepresentation();
		assertEquals(expResult, result);
	}

}
