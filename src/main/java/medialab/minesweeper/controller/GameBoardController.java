package medialab.minesweeper.controller;

import javafx.scene.control.Button;
import javafx.scene.input.MouseButton;
import medialab.minesweeper.model.GameBoardModel;
import medialab.minesweeper.view.GameBoardView;

public class GameBoardController implements Controller {
    private GameBoardModel model;
    private GameBoardView view;

    public GameBoardController(GameBoardModel model, GameBoardView view) {
        this.model = model;
        this.view = view;
        for (int i = 0; i < model.getRows(); i++) {
            for (int j = 0; j < model.getColumns(); j++) {
                Button cell = (Button) view.getRoot().getChildren().get(i * model.getColumns() + j);
                int finalI = i;
                int finalJ = j;
                cell.setOnMouseClicked(event -> {
                    MouseButton e = event.getButton();
                    switch (e) {
                        case PRIMARY:
                            // Open the cell if left-clicked
                            model.revealCell(finalI, finalJ);
                            if (model.isGameOver()) {
                                // End the game if the cell contains a mine
                                cell.setText("Game Over");
                            }
                            view.updateView();
                            break;
                        case SECONDARY:
                            model.flagCell(finalI, finalJ);
                            view.updateView();
                            break;
                        default:
                            view.updateView();
                            break;
                    }
                });
            }
        }
    }
}
