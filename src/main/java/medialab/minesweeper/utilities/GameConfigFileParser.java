package medialab.minesweeper.utilities;

import java.nio.file.InvalidPathException;
import java.util.HashMap;
import java.util.Map;

public class GameConfigFileParser extends FileParser {
    private final Map<String, String> config = new HashMap<>();

    public GameConfigFileParser(String fileName) throws InvalidPathException {
        super(fileName);
        parseGameConfig();
    }

    private void parseGameConfig() {
        config.put("key1", "val1");
        config.put("key2", "val2");
        config.put("key3", "val3");
    }

    public Map<String, String> getGameConfig() {
        return config;
    }
}
