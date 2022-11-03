package medialab.minesweeper.definitions;

import java.util.HashMap;
import java.util.Map;

public class Messages {
    public static final Map<String, String> configExceptionMessages = new HashMap<>() {{
        put("invalidMaxTime", "Invalid value for maximum play time");
        put("invalidSupernuke", "Invalid value for supernuke");
        put("invalidNumOfNukes", "Invalid value for number of nukes");
        put("invalidDifficulty", "Invalid value for game difficulty");
        put("invalidConfigFile", "Invalid configuration file");
    }};
}
