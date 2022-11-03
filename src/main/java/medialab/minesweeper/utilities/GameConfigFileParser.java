package medialab.minesweeper.utilities;

import medialab.minesweeper.exceptions.*;

import java.nio.file.InvalidPathException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static medialab.minesweeper.definitions.Constants.GridSizes;

public class GameConfigFileParser extends FileParser {
    private final Map<String, Integer> gameConfig = new HashMap<>();

    public GameConfigFileParser(String fileName) throws InvalidPathException, InvalidDescriptionException, InvalidValueException {
        super(fileName);
        parseGameConfig();
    }

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

        switch (difficulty) {
            case 1:
                if (numOfNukes < 9 || numOfNukes > 11)
                    throw new InvalidValueException("invalidNumOfNukes");
                if (maxTime < 120 || maxTime > 180)
                    throw new InvalidValueException("invalidMaxTime");
                if (hasSupernuke != 0)
                    throw new InvalidValueException("invalidSupernuke");
                break;
            case 2:
                if (numOfNukes < 35 || numOfNukes > 45)
                    throw new InvalidValueException("invalidNumOfNukes");
                if (maxTime < 240 || maxTime > 360)
                    throw new InvalidValueException("invalidMaxTime");
                if (hasSupernuke != 0 && hasSupernuke != 1)
                    throw new InvalidValueException("invalidSupernuke");
                break;
            default:
                throw new InvalidValueException("invalidDifficulty");
        }

        gameConfig.put("difficulty", difficulty);
        gameConfig.put("numOfNukes", numOfNukes);
        gameConfig.put("maxTime", maxTime);
        gameConfig.put("hasSupernuke", hasSupernuke);
        gameConfig.put("gridWidth", GridSizes.get(difficulty)[0]);
        gameConfig.put("gridHeight", GridSizes.get(difficulty)[1]);
    }

    public Map<String, Integer> getGameConfig() {
        return gameConfig;
    }
}
