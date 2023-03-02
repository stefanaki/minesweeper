package medialab.minesweeper.utility;

public class PreviousRound {
    private GameConfig gameConfig;
    private boolean userWon;

    private int userMoveCount;

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