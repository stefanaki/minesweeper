package medialab.minesweeper.definition;

import java.util.HashMap;
import java.util.Map;

public class Constants {
    public static enum Level {
        BEGINNER,
        ADVANCED
    }

    public static final Map<Integer, Integer[]> GridSizes = new HashMap<>() {{
        put(1, new Integer[] {9, 9});
        put(2, new Integer[] {16, 16});
    }};

    public enum CellStatus {
        CLOSED,
        FLAGGED,
        REVEALED,
        NUKE
    }

    public enum NukeCellType {
        NONE,
        NUKE,
        SUPERNUKE
    }
}
