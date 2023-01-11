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
                String imgPath = "images/";

                switch (model.getCellStatus(i, j)) {
                    case REVEALED:
                        int adjacentMines = model.getAdjacentNukes(i, j);
                        imgPath += "open" + adjacentMines + ".gif";
                        break;
                    case FLAGGED:
                        imgPath += "flagged.gif";
                        break;
                    default:
                        imgPath += "blank.gif";
                        break;
                }

                imgView = new ImageView(new Image(Main.class.getResourceAsStream(imgPath)));
                imgView.setFitWidth(30);
                imgView.setFitHeight(30);
                cell.setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
                cell.setGraphic(imgView);

                imgPath = "images/";
                if (model.isGameOver()) {
                    switch (model.getCellStatus(i, j)) {
                        case REVEALED:
                            int adjacentMines = model.getAdjacentNukes(i, j);
                            imgPath += model.isNuke(i, j) ? "mineclicked.gif" : "open" + adjacentMines + ".gif";
                            break;
                        case FLAGGED:
                            imgPath += model.isNuke(i, j) ? "mine.gif" : "misflagged.gif";
                            break;
                        case CLOSED:
                            imgPath += model.isNuke(i, j) ? "mine.gif" : "blank.gif";
                    }

                    imgView = new ImageView(new Image(Main.class.getResourceAsStream(imgPath)));
                    imgView.setFitWidth(30);
                    imgView.setFitHeight(30);
                    cell.setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
                    cell.setGraphic(imgView);
                }
            }
        }
    }

    public GridPane getRoot() {
        return this.root;
    }
}
