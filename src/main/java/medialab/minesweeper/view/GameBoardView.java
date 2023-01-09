package medialab.minesweeper.view;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import medialab.minesweeper.model.GameBoardModel;

public class GameBoardView implements View {
    private final GameBoardModel model;
    private final GridPane root;

    public GameBoardView(GameBoardModel model) {
        this.model = model;
        root = new GridPane();
        for (int i = 0; i < model.getRows(); i++) {
            for (int j = 0; j < model.getColumns(); j++) {
                Button cell = new Button();
                cell.setMinSize(30, 30);
                cell.setMaxSize(30, 30);
                cell.setStyle("-fx-background-color: grey; -fx-border-color: black;");
                root.add(cell, j, i);
            }
        }

        root.setPadding(new Insets(30, 30, 30, 30));
    }

    public void updateView() {
        for (int i = 0; i < model.getRows(); i++) {
            for (int j = 0; j < model.getColumns(); j++) {
                Button cell = (Button) root.getChildren().get(i * model.getColumns() + j);

                switch (model.getCellStatus(i, j)) {
                    case REVEALED:
                        cell.setStyle("-fx-background-color: blue; -fx-border-color: black; -fx-text-fill: white");
                        int adjacentMines = model.getAdjacentNukes(i, j);
                        if (adjacentMines > 0) {
                            cell.setText(Integer.toString(adjacentMines));
                        }
                        break;
                    case FLAGGED:
                        cell.setStyle("-fx-background-color: green; -fx-border-color: black");
                        break;
                    default:
                        cell.setStyle("-fx-background-color: grey; -fx-border-color: black");
                        break;
                }

                if (model.isGameOver() && model.isNuke(i, j)) {
                    cell.setStyle("-fx-background-color: red; -fx-border-color: black;");
                }
            }
        }
    }

    public GridPane getRoot() {
        return this.root;
    }
}
