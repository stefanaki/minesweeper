package medialab.minesweeper.view;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.util.Duration;
import medialab.minesweeper.Main;
import medialab.minesweeper.model.GameBoardModel;

public class GameStateInfoView implements View {
    private final GameBoardModel gameBoardModel;
    private final Text timerLabel;
    private final Text nukesLabel;
    private final Text markedCellsLabel;

    private final VBox root;

    private final Timeline timeline;

    public GameStateInfoView(GameBoardModel model) {
        this.gameBoardModel = model;

        Font font = Font.loadFont(Main.class.getResourceAsStream("fonts/PressStart2P-Regular.ttf"), 24);

        this.timerLabel = new Text(Integer.toString(gameBoardModel.getMaxTime()));
        this.nukesLabel = new Text("NUKES\n" + gameBoardModel.getNukesCount());
        this.markedCellsLabel = new Text("MARKED\n" + gameBoardModel.getMarkedCellsCount());

        this.timerLabel.setFont(font);
        this.timerLabel.setFill(Color.RED);
        this.nukesLabel.setFont(font);
        this.markedCellsLabel.setFont(font);

        HBox stats = new HBox(nukesLabel, markedCellsLabel);
        stats.setAlignment(Pos.CENTER);
        stats.setSpacing(40);
        this.root = new VBox(stats, timerLabel);

        this.root.setPadding(new Insets(30));
        this.root.setSpacing(30);
        this.root.setAlignment(Pos.CENTER);
        this.root.setFillWidth(true);

        timeline = new Timeline(new KeyFrame(Duration.millis(500), actionEvent -> updateView()));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
        updateView();
    }

    public void updateView() {
        if (gameBoardModel.hasWon()) {
            timerLabel.setText("YOU WIN!");
            gameBoardModel.logRoundToPreviousRounds();
            timeline.stop();
            return;
        }

        if (gameBoardModel.getStartTime() == -1) {
            timerLabel.setText(":)");
            return;
        }

        long elapsed = System.currentTimeMillis() - gameBoardModel.getStartTime();
        int remainingTime = (int) ((gameBoardModel.getMaxTime() * 1000 - elapsed) / 1000);

        timerLabel.setText(Integer.toString(remainingTime));
        nukesLabel.setText("NUKES\n" + gameBoardModel.getNukesCount());
        markedCellsLabel.setText("MARKED\n" + gameBoardModel.getMarkedCellsCount());

        if (remainingTime == 0 || gameBoardModel.isGameOver()) {
            gameBoardModel.stopGame();
            timerLabel.setText(":(");
            timeline.stop();
        }
    }

    public VBox getRoot() {
        return root;
    }
}
