package medialab.minesweeper.utility;

import medialab.minesweeper.exception.*;

import java.nio.file.InvalidPathException;
import java.util.ArrayList;

import static medialab.minesweeper.definition.Constants.GridSizes;

/**
 * Class for parsing game configuration text files.
 */
public class GameConfigFileParser extends FileParser {
    private GameConfig gameConfig;

    /**
     * @param fileName Path of the configuration file on the system.
     * @throws InvalidDescriptionException
     * @throws InvalidValueException
     */
    public GameConfigFileParser(String fileName) throws InvalidPathException, InvalidDescriptionException, InvalidValueException {
        super(fileName);
        parseGameConfig();
    }

    /**
     * Checks for game values validity and updates the gameConfig object.
     *
     * @throws InvalidValueException
     * @throws InvalidDescriptionException
     */
    private void parseGameConfig() throws InvalidValueException, InvalidDescriptionException {
        ArrayList<String> data;
        int difficulty, numOfNukes, maxTime, hasSupernuke;

        try {
            if ((data = this.getLines()).size() != 4)
                throw new Exception();

            difficulty = Integer.parseInt(data.get(0));
            numOfNukes = Integer.parseInt(data.get(1));
            maxTime = Integer.parseInt(data.get(2));
            hasSupernuke = Integer.parseInt(data.get(3));
        } catch (Exception n) {
            throw new InvalidDescriptionException("invalidConfigFile");
        }

        this.gameConfig = new GameConfig(difficulty, numOfNukes, maxTime, hasSupernuke == 1, GridSizes.get(difficulty)[0], GridSizes.get(difficulty)[1]);
    }

    /**
     * @return The game configuration object.
     */
    public GameConfig getGameConfig() {
        return gameConfig;
    }
}
