package medialab.minesweeper.model;

import medialab.minesweeper.definition.Constants.CellStatus;
import medialab.minesweeper.definition.Constants.NukeType;
import medialab.minesweeper.utility.GameConfig;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Random;

public class GameBoardModel implements Model {
    /**
     * The 2D array representing the game board. Each element in the array represents the status of a cell on the board.
     */
    private final CellStatus[][] gameBoard;

    /**
     * The 2D array representing the nukes on the game board. Each element in the array represents the type of nuke (if any) at
     * the corresponding position on the game board.
     */
    private final NukeType[][] nukes;

    /**
     * The number of rows on the game board.
     */
    private final int rows;

    /**
     * The number of columns on the game board.
     */
    private final int columns;

    /**
     * The number of nukes on the game board.
     */
    private final int nukesCount;

    /**
     * A flag indicating whether the game board has a supernuke.
     */
    private final boolean hasSupernuke;

    /**
     * A flag indicating whether the game is over.
     */
    private boolean gameOver;

    /**
     * The number of moves made by the player.
     */
    private int userMoveCount;

    /**
     * Constructs a new MinesweeperModel with the specified game configuration object.
     *
     * @param gameConfig The game configuration object
     *
     * @throws IOException if an I/O error occurs while writing to the file
     */
    public GameBoardModel(GameConfig gameConfig) throws IOException {
        this.rows = gameConfig.getGridHeight();
        this.columns = gameConfig.getGridWidth();
        this.nukesCount = gameConfig.getNumOfNukes();
        this.hasSupernuke = gameConfig.getHasSupernuke();
        this.gameOver = false;
        this.gameBoard = new CellStatus[rows][columns];
        this.nukes = new NukeType[rows][columns];
        this.userMoveCount = 0;

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                gameBoard[i][j] = CellStatus.CLOSED;
                nukes[i][j] = NukeType.NONE;
            }
        }
        placeNukes();
        saveBoard("/home/giorgis/Desktop/nuketest/mines.txt");
    }

    /**
     * Places nukes randomly on the game board. If hasSupernuke is true, one of the nukes is a supernuke.
     */
    private void placeNukes() {
        int nukesPlaced = 0;
        Random random = new Random();
        while (nukesPlaced < nukesCount) {
            int row = random.nextInt(rows);
            int column = random.nextInt(columns);
            if (nukes[row][column] == NukeType.NONE) {
                // place a mine at the randomly selected position
                nukes[row][column] = NukeType.NUKE;
                nukesPlaced++;
                if (hasSupernuke && nukesPlaced == nukesCount - 1) {
                    nukes[row][column] = NukeType.SUPERNUKE;
                }
            }
        }
    }

    /**
     * Returns true if the cell at the specified row and column is a nuke, false otherwise.
     *
     * @param row the row of the cell to check
     * @param column the column of the cell to check
     * @return true if the cell at the specified row and column is a nuke, false otherwise
     */
    public boolean isNuke(int row, int column) {
        return nukes[row][column] != NukeType.NONE;
    }

    /**
     * Returns true if the cell at the specified row and column is the supernuke, false otherwise.
     *
     * @param row the row of the cell to check
     * @param column the column of the cell to check
     * @return true if the cell at the specified row and column is the supernuke, false otherwise
     */
    public boolean isSupernuke(int row, int column) {
        return nukes[row][column] == NukeType.SUPERNUKE;
    }

    /**
     * Reveals the cell at the specified row and column on the game board. If the cell is a nuke, the game is over and the gameOver
     * variable is updated to reflect this. If the cell is not a nuke, it is revealed and all adjacent cells are also revealed
     * if they do not have any adjacent nukes. If the cell is a supernuke and the player has made less than 4 moves, all
     * cells in the same row and column as the supernuke are also revealed.
     *
     * @param row the row of the cell to be revealed
     * @param column the column of the cell to be revealed
     */
    public void revealCell(int row, int column) {
        if (gameBoard[row][column] != CellStatus.CLOSED) {
            return;
        }

        // Reveal cell
        gameBoard[row][column] = CellStatus.REVEALED;
        if (isNuke(row, column)) {
            gameOver = true;
            return;
        }

        int adjacentNukes = getAdjacentNukes(row, column);
        if (adjacentNukes == 0) {
            // no adjacent mines - reveal all adjacent cells
            for (int i = row - 1; i <= row + 1; i++) {
                for (int j = column - 1; j <= column + 1; j++) {
                    if (i >= 0 && i < rows && j >= 0 && j < columns && !(i == row && j == column)) {
                        revealCell(i, j);
                    }
                }
            }
        }

        if (nukes[row][column] == NukeType.SUPERNUKE && userMoveCount < 4) {
            for (int i = 0; i < rows; i++) {
                revealCell(i, column);
            }
            for (int j = 0; j < columns; j++) {
                revealCell(row, j);
            }
        }
        userMoveCount++;
    }

    /**
     * Flags or unflags the cell at the specified row and column on the game board. If the cell is closed, it is flagged. If
     * the cell is flagged, the flag is removed.
     *
     * @param row the row of the cell to be flagged or unflagged
     * @param column the column of the cell to be flagged or unflagged
     */
    public void flagCell(int row, int column) {
        if (gameBoard[row][column] == CellStatus.CLOSED) {
            // flag the cell if it is closed
            gameBoard[row][column] = CellStatus.FLAGGED;
        } else if (gameBoard[row][column] == CellStatus.FLAGGED) {
            // remove the flag if the cell is flagged
            gameBoard[row][column] = CellStatus.CLOSED;
        }
    }

    /**
     * Saves the current state of the game board to a file at the specified file path.
     *
     * @param fileName the file path to save the game board to
     * @throws IOException if an I/O error occurs while writing to the file
     */
    private void saveBoard(String fileName) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, StandardCharsets.UTF_8));
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                if (nukes[i][j] == NukeType.NUKE) {
                    writer.write(i + "," + j + "," + "0" + "\n");
                } else if (nukes[i][j] == NukeType.SUPERNUKE) {
                    writer.write(i + "," + j + "," + "1" + "\n");
                }

            }
        }
        writer.close();
    }

    /**
     * Returns the number of nukes adjacent to the cell at the specified row and column on the game board.
     *
     * @param row the row of the cell to check
     * @param column the column of the cell to check
     * @return the number of nukes adjacent to the cell at the specified row and column
     */
    public int getAdjacentNukes(int row, int column) {
        int count = 0;
        for (int i = row - 1; i <= row + 1; i++) {
            for (int j = column - 1; j <= column + 1; j++) {
                if (i >= 0 && i < rows && j >= 0 && j < columns && isNuke(i, j)) {
                    count++;
                }
            }
        }
        return count;
    }

    /**
     * Checks if the player has won the game by checking if all non-nuke cells on the game board have been revealed.
     *
     * @return true if the player has won the game, false otherwise
     */
    public boolean hasWon() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                if (gameBoard[i][j] == CellStatus.CLOSED && nukes[i][j] == NukeType.NONE) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Returns the status of the cell at the specified row and column on the game board.
     *
     * @param row the row of the cell to check
     * @param column the column of the cell to check
     * @return the status of the cell at the specified row and column
     */
    public CellStatus getCellStatus(int row, int column) {
        return gameBoard[row][column];
    }

    public boolean isGameOver() { return gameOver; }

    /**
     * Returns the number of rows on the game board.
     *
     * @return the number of rows on the game board
     */
    public int getRows() { return rows; }

    /**
     * Returns the number of columns on the game board.
     *
     * @return the number of columns on the game board
     */
    public int getColumns() { return columns; }

    /**
     * Returns the number of nukes on the game board.
     *
     * @return the number of nukes on the game board
     */
    public int getNukesCount() { return nukesCount; }


    /**
     * Returns true if the game board has a supernuke, false otherwise.
     *
     * @return true if the game board has a supernuke, false otherwise
     */
    public boolean hasSupernuke() {
        return this.hasSupernuke;
    }
}
