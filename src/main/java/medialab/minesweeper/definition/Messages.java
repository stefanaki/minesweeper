package medialab.minesweeper.definition;

import java.util.HashMap;
import java.util.Map;

public class Messages {
    public static final Map<String, String> configExceptionMessages = new HashMap<>() {{
        put("invalidMaxTime1", "Invalid value for maximum play time. On easy difficulty, choose a value between 120 and 180 seconds.");
        put("invalidSupernuke1", "Invalid value for supernuke. On easy difficulty, choose no supernuke.");
        put("invalidNumOfNukes1", "Invalid value for number of nukes. On easy difficulty, choose a value between 9 and 11.");
        put("invalidMaxTime2", "Invalid value for maximum play time. On advanced difficulty, choose a value between 240 and 360 seconds.");
        put("invalidNumOfNukes2", "Invalid value for number of nukes. On easy difficulty, choose a value between 35 and 45.");
        put("invalidConfigFile", "Invalid configuration file");
    }};
}
