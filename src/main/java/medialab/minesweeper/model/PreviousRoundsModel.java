package medialab.minesweeper.model;


import medialab.minesweeper.utility.GameConfig;
import medialab.minesweeper.utility.PreviousRound;

import java.util.ArrayList;

public class PreviousRoundsModel implements Model {
    private static ArrayList<PreviousRound> previousRounds = new ArrayList<>();

    public static ArrayList<PreviousRound> getPreviousRounds() {
        return previousRounds;
    }

    public static void addNewRound(GameConfig gameConfig, boolean userWon, int userMoveCount) {
        previousRounds.add(new PreviousRound(gameConfig, userWon, userMoveCount));
    }
}
