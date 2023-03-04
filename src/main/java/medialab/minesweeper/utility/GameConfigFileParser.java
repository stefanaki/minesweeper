package medialab.minesweeper.utility;

import medialab.minesweeper.exception.InvalidDescriptionException;
import medialab.minesweeper.exception.InvalidValueException;

import java.nio.file.InvalidPathException;
import java.util.ArrayList;
import java.util.UUID;

import static medialab.minesweeper.definition.Constants.GridSizes;

/**
 * Class for parsing game configuration text files.
 */
public class GameConfigFileParser extends FileParser {
    private GameConfig gameConfig;

    /**
     * @param fileName Path of the configuration file on the system.
     */
    public GameConfigFileParser(String fileName) throws InvalidPathException, InvalidDescriptionException, InvalidValueException {
        super(fileName);
        parseGameConfig();
    }

    /**
     * Checks for game values validity and updates the gameConfig object.
     */
    private void parseGameConfig() throws InvalidValueException, InvalidDescriptionException {
        ArrayList<String> data;
        int difficulty, numOfNukes, maxTime, hasSupernuke;

        try {
            if ((data = this.getLines()).size() != 4) throw new Exception();

            difficulty = Integer.parseInt(data.get(0));
            numOfNukes = Integer.parseInt(data.get(1));
            maxTime = Integer.parseInt(data.get(2));
            hasSupernuke = Integer.parseInt(data.get(3));
        } catch (Exception n) {
            throw new InvalidDescriptionException("invalidConfigFile");
        }

        String scenarioId = getScenarioId();
        this.gameConfig = new GameConfig(scenarioId, difficulty, numOfNukes, maxTime, hasSupernuke == 1, GridSizes.get(difficulty)[0], GridSizes.get(difficulty)[1]);
    }

    /**
     * @return The game configuration object.
     */
    public GameConfig getGameConfig() {
        return gameConfig;
    }

    public String getScenarioId() {
        String input = Long.toString(System.currentTimeMillis());
        UUID uuid = UUID.nameUUIDFromBytes(input.getBytes());
        return uuid.toString();
    }
}
