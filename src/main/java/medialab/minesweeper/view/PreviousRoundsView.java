package medialab.minesweeper.view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;
import medialab.minesweeper.model.PreviousRoundsModel;
import medialab.minesweeper.utility.GameConfig;
import medialab.minesweeper.utility.PreviousRound;

import java.util.ArrayList;

public class PreviousRoundsView {
    private final ObservableList<String> items = FXCollections.observableArrayList();
    private VBox root;

    public PreviousRoundsView() {
        this.root = new VBox();

        // create a ListView to display the data
        ListView<String> listView = new ListView<>(items);

        // add items to the ListView
        for (PreviousRound round : PreviousRoundsModel.getPreviousRounds()) {
            GameConfig config = round.getGameConfig();
            boolean userWon = round.getUserWon();
            int userMoveCount = round.getUserMoveCount();


            String log = "";
            log += userWon ? "WON " : "LOST ";
            log += config.getScenarioId() + ": ";
            log += config.getDifficulty() == 1 ? "(EASY) " : "(ADVANCED) ";
            log += Integer.toString(config.getGridHeight()) + "*" + Integer.toString(config.getGridWidth()) + " ";
            log += "Mines " + Integer.toString(config.getNumOfNukes()) + " ";
            log += "Moves " + Integer.toString(userMoveCount);
            items.add(log);
        }

        root.getChildren().add(listView);
    }

    public VBox getRoot() {
        return this.root;
    }
}
