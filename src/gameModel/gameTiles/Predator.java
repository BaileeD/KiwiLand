package gameModel.gameTiles;

import gameModel.Game;
import gameModel.Island;
import gameModel.MoveDirection;
import gameModel.Position;
import gameModel.Terrain;
import java.util.Random;

/**
 * Predator represents a predator on the island. If more specific behaviour is
 * required for particular predators, descendants for this class should be
 * created
 *
 * @author AS
 * @version July 2011
 */
public class Predator extends Fauna implements Runnable {

    private Game game;
    private Island island;
    private long delay;

    /**
     * Constructor for objects of class Predator
     *
     * @param pos the position of the predator object
     * @param name the name of the predator object
     * @param description a longer description of the predator object
     */
    public Predator(Position pos, String name, String description, Game game, Island island) {
        super(pos, name, description);
        this.game = game;
        this.island = island;
        delay = 10;
    }

    @Override
    public String getStringRepresentation() {
        return "P";
    }

    private int randInt(int min, int max) {
        Random rand = new Random();
        int randomNum = rand.nextInt((max - min) + 1) + min;
        if (randomNum == 0) {
            randomNum = rand.nextInt((max - min) + 1) + min;
        }
        return randomNum;
    }

    private boolean predatorCanMove() {
        return (isPredatorMovePossible(MoveDirection.NORTH) || isPredatorMovePossible(MoveDirection.SOUTH)
                || isPredatorMovePossible(MoveDirection.EAST) || isPredatorMovePossible(MoveDirection.WEST));

    }

    public boolean isPredatorMovePossible(MoveDirection direction) {
        boolean isMovePossible = false;
        // what position is the kiwi moving to?
        Position newPosition = this.getPosition().getNewPosition(direction);
        // is that a valid position?
        if ((newPosition != null) && newPosition.isOnIsland()) {
            // what is the terrain at that new position?
            island.getTerrain(newPosition);
            isMovePossible = true;
        }
        return isMovePossible;
    }

    public void predatorMove(MoveDirection direction) {
        // what terrain is the kiwi moving on currently
        if (isPredatorMovePossible(direction)) {
            Position newPosition = this.getPosition().getNewPosition(direction);
            Terrain terrain = island.getTerrain(newPosition);
            //if (terrain != Terrain.WATER) {
            // move the kiwi to new position
            island.updatePredatorPosition(this, newPosition);
            //}
        }
    }

    @Override
    public void run() {
        int num = this.randInt(1, 4);
        try {
            Thread.sleep(delay);
        } catch (InterruptedException e) {
            System.out.println("interrupted");
        }
        switch (num) {
            case 1:
                this.predatorMove(MoveDirection.NORTH);
                break;
            case 2:
                this.predatorMove(MoveDirection.EAST);
                break;
            case 3:
                this.predatorMove(MoveDirection.SOUTH);
                break;
            case 4:
                this.predatorMove(MoveDirection.WEST);
                break;
        }
    }
}
