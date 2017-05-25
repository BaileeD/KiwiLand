package gameModel.gameTiles;

import gameModel.Facts;
import gameModel.Position;

/**
 * Kiwi represents a kiwi living on the island
 *
 * @author AS
 * @version July 2011
 */
public class Kiwi extends Fauna
{

	private boolean counted;
	private String stringRepresentation;

	/**
	 * Constructor for objects of class Kiwi
	 *
	 * @param pos         the position of the kiwi object
	 * @param name        the name of the kiwi object
	 * @param description a longer description of the kiwi
	 */
	public Kiwi(Position pos, String name, String description)
	{
		super(pos, name, description);
		counted = false;

		stringRepresentation = "K";
	}

	/**
	 * Count this kiwi
	 */
	public void count()
	{
		counted = true;

		stringRepresentation = "k";
	}

	/**
	 * Has this kiwi been counted
	 *
	 * @return true if counted.
	 */
	public boolean counted()
	{
		return counted;
	}

	@Override public String getStringRepresentation()
	{
		return stringRepresentation;
	}
}
