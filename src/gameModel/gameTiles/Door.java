package gameModel.gameTiles;

import gameModel.Position;

/**
 * Created by Scott Richards on 10-May-17.
 */
public class Door extends Occupant
{
	public Door(Position pos, String name, String description)
	{
		super(pos, name, description);
	}

	@Override public String getStringRepresentation()
	{
		return "D";
	}
}
