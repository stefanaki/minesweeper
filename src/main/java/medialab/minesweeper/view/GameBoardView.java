package medialab.minesweeper.view;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import medialab.minesweeper.Main;
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
                ImageView imgView = new ImageView(new Image(Main.class.getResourceAsStream("images/blank.gif")));
                imgView.setFitWidth(30);
                imgView.setFitHeight(30);
                cell.setGraphic(imgView);
                root.add(cell, j, i);
            }
        }

        root.setPadding(new Insets(30, 30, 30, 30));
    }

    public void updateView() {
        for (int i = 0; i < model.getRows(); i++) {
            for (int j = 0; j < model.getColumns(); j++) {
                Button cell = (Button) root.getChildren().get(i * model.getColumns() + j);
                ImageView imgView;
                switch (model.getCellStatus(i, j)) {

                    case REVEALED:
                        int adjacentMines = model.getAdjacentNukes(i, j);
                        imgView = new ImageView(new Image(Main.class.getResourceAsStream("images/open" + adjacentMines + ".gif")));
                        break;
                    case FLAGGED:
                        imgView = new ImageView(new Image(Main.class.getResourceAsStream("images/flagged.gif")));
                        break;
                    default:
                        imgView = new ImageView(new Image(Main.class.getResourceAsStream("images/blank.gif")));
                        break;
                }

                if (model.isGameOver() && model.isNuke(i, j)) {
                    switch (model.getCellStatus(i, j)) {
                        case REVEALED:
                            imgView = new ImageView(new Image(Main.class.getResourceAsStream("images/mineclicked.gif")));
                            break;
                        case FLAGGED:
                            imgView = new ImageView(new Image(Main.class.getResourceAsStream("images/misflagged.gif")));
                            break;
                        default:
                            imgView = new ImageView(new Image(Main.class.getResourceAsStream("images/mine.gif")));
                            break;
                    }
                }

                imgView.setFitWidth(30);
                imgView.setFitHeight(30);
                cell.setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
                cell.setGraphic(imgView);
            }
        }
    }

    public GridPane getRoot() {
        return this.root;
    }
}
