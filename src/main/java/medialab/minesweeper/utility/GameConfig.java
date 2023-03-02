package medialab.minesweeper.utility;

import medialab.minesweeper.exception.InvalidValueException;

/**
 * The GameConfig class represents the configuration for a Minesweeper game.
 */
public class GameConfig {
    /**
     * The ID of the game scenario
     */
    final String scenarioId;

    /**
     * The difficulty level of the game
     */
    final int difficulty;
    /**
     * The number of nukes in the game
     */
    final int numOfNukes;
    /**
     * The maximum time limit for the game
     */
    final int maxTime;
    /**
     * Whether the game has a supernuke
     */
    final boolean hasSupernuke;
    /**
     * The height of the grid in the game
     */
    final int gridHeight;
    /**
     * The width of the grid in the game
     */
    final int gridWidth;

    /**
     * Creates a new instance of the GameConfig class.
     *
     * @param difficulty   The difficulty level of the game
     * @param numOfNukes   The number of nukes in the game
     * @param maxTime      The maximum time limit for the game
     * @param hasSupernuke Whether the game has a supernuke
     * @param gridHeight   The height of the grid in the game
     * @param gridWidth    The width of the grid in the game
     * @throws InvalidValueException if the input values are invalid
     */
    public GameConfig(String scenarioId, int difficulty, int numOfNukes, int maxTime, boolean hasSupernuke, int gridHeight, int gridWidth) throws InvalidValueException {
        if (scenarioId.isEmpty()) {
            throw new InvalidValueException("scenarioIdEmpty");
        }

        switch (difficulty) {
            case 1:
                if (numOfNukes < 9 || numOfNukes > 11)
                    throw new InvalidValueException("invalidNumOfNukes1");
                if (maxTime < 120 || maxTime > 180)
                    throw new InvalidValueException("invalidMaxTime1");
                if (hasSupernuke)
                    throw new InvalidValueException("invalidSupernuke1");
                break;
            case 2:
                if (numOfNukes < 35 || numOfNukes > 45)
                    throw new InvalidValueException("invalidNumOfNukes2");
                if (maxTime < 240 || maxTime > 360)
                    throw new InvalidValueException("invalidMaxTime2");
                break;
            default:
                throw new InvalidValueException("invalidDifficulty");
        }

        this.scenarioId = scenarioId;
        this.difficulty = difficulty;
        this.numOfNukes = numOfNukes;
        this.maxTime = maxTime;
        this.hasSupernuke = hasSupernuke;
        this.gridHeight = gridHeight;
        this.gridWidth = gridWidth;
    }

    /**
     * Gets the difficulty level of the game.
     *
     * @return The difficulty level of the game
     */
    public int getDifficulty() {
        return difficulty;
    }

    /**
     * Gets the number of nukes in the game.
     *
     * @return The number of nukes in the game
     */
    public int getNumOfNukes() {
        return numOfNukes;
    }

    /**
     * Returns the maximum time allowed for the game.
     *
     * @return the maximum time allowed for the game
     */
    public int getMaxTime() {
        return maxTime;
    }


    /**
     * Returns whether the game has a supernuke or not.
     *
     * @return whether the game has a supernuke or not
     */
    public boolean getHasSupernuke() {
        return hasSupernuke;
    }

    /**
     * Returns the height of the grid in the game.
     *
     * @return the height of the grid in the game
     */
    public int getGridHeight() {
        return gridHeight;
    }

    /**
     * Returns the width of the grid in the game.
     *
     * @return the width of the grid in the game
     */
    public int getGridWidth() {
        return gridWidth;
    }

    /**
     * Returns the ID of the game scenario.
     *
     * @return the ID of the game scenario
     */
    public String getScenarioId() {
        return scenarioId;
    }
}
