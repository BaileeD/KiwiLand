package gameModel.gameTiles;

import gameModel.Facts;
import gameModel.Game;
import gameModel.GameState;
import gameModel.Island;
import gameModel.MoveDirection;
import gameModel.Position;
import gameModel.Terrain;
import java.util.Random;

/**
 * Kiwi represents a kiwi living on the island
 *
 * @author AS
 * @version July 2011
 */
public class Kiwi extends Fauna implements Runnable{

    private boolean counted;
    //Facts fact = new Facts();
    private String stringRepresentation;
    private long delay;
    private Island island;
    private Game game;

    /**
     * Constructor for objects of class Kiwi
     *
     * @param pos the position of the kiwi object
     * @param name the name of the kiwi object
     * @param description a longer description of the kiwi
     */
    public Kiwi(Position pos, String name, String description, Game game, Island island) {
        super(pos, name, description);
        counted = false;
        stringRepresentation = "K";
        delay = 10;
        this.game = game;
        this.island = island;
    }

    /**
     * Count this kiwi
     */
    public void count() {
        counted = true;
        //fact.getFact("Kiwi");

        stringRepresentation = "k";
    }

    /**
     * Has this kiwi been counted
     *
     * @return true if counted.
     */
    public boolean counted() {
        return counted;
    }

    @Override
    public String getStringRepresentation() {
        return stringRepresentation;
    }

    private int randInt(int min, int max) {
        Random rand = new Random();
        int randomNum = rand.nextInt((max - min) + 1) + min;
        if (randomNum == 0) {
            randomNum = rand.nextInt((max - min) + 1) + min;
        }
        return randomNum;
    }
    private boolean kiwiCanMove() {
        return (isKiwiMovePossible(MoveDirection.NORTH) || isKiwiMovePossible(MoveDirection.SOUTH)
                || isKiwiMovePossible(MoveDirection.EAST) || isKiwiMovePossible(MoveDirection.WEST));

    }

    public boolean isKiwiMovePossible(MoveDirection direction) {
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

    public void kiwiMove(MoveDirection direction) {
        // what terrain is the kiwi moving on currently
        if (isKiwiMovePossible(direction)) {
            Position newPosition = this.getPosition().getNewPosition(direction);
            Terrain terrain = island.getTerrain(newPosition);
            if (terrain != Terrain.WATER) {
            // move the kiwi to new position
            island.updateKiwiPosition(this, newPosition);
            }
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
                this.kiwiMove(MoveDirection.NORTH);
                break;
            case 2:
                this.kiwiMove(MoveDirection.EAST);
                break;
            case 3:
                this.kiwiMove(MoveDirection.SOUTH);
                break;
            case 4:
                this.kiwiMove(MoveDirection.WEST);
                break;
        }
    }
}
