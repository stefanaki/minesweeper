package medialab.minesweeper.utility;

public class PreviousRound {
    private final GameConfig gameConfig;
    private final boolean userWon;

    private final int userMoveCount;

    public PreviousRound(GameConfig gameConfig, boolean userWon, int userMoveCount) {
        this.gameConfig = gameConfig;
        this.userWon = userWon;
        this.userMoveCount = userMoveCount;
    }

    public GameConfig getGameConfig() {
        return this.gameConfig;
    }

    public boolean getUserWon() {
        return this.userWon;
    }

    public int getUserMoveCount() {
        return this.userMoveCount;
    }
}