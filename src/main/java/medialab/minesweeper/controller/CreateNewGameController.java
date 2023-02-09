package medialab.minesweeper.controller;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import medialab.minesweeper.definition.Constants;
import medialab.minesweeper.definition.Messages;
import medialab.minesweeper.exception.InvalidValueException;
import medialab.minesweeper.utility.GameConfig;

public class CreateNewGameController {
    @FXML
    private TextField scenarioIdField;

    @FXML
    private RadioButton easyDifficulty;

    @FXML
    private RadioButton advancedDifficulty;

    @FXML
    private CheckBox hasSupernukeCheckbox;

    @FXML
    private TextField gameTimeField;

    @FXML
    private TextField nukesField;

    @FXML
    private Button createButton;

    @FXML ToggleGroup difficultyGroup;

    @FXML
    public void initialize() {
    difficultyGroup = new ToggleGroup();



        easyDifficulty.setToggleGroup(difficultyGroup);
        advancedDifficulty.setToggleGroup(difficultyGroup);
    }

    public int getScenarioId() {
        return Integer.parseInt(scenarioIdField.getText());
    }

    public void initializeGame() {
    }

    @FXML
    public void onCreateGameClicked() throws InvalidValueException {
        try {
            String scenarioId = this.scenarioIdField.getText();
            int selectedDifficulty = difficultyGroup.getSelectedToggle() == easyDifficulty ? 1 : 2;
            boolean hasSupernuke = hasSupernukeCheckbox.isSelected();
            int maxTime = Integer.valueOf(gameTimeField.getText());
            int numOfNukes = Integer.valueOf(nukesField.getText());
            int gridWidth = Constants.GridSizes.get(selectedDifficulty)[0];
            int gridHeight = Constants.GridSizes.get(selectedDifficulty)[1];

            GameConfig newGameConfig = new GameConfig(selectedDifficulty, numOfNukes, maxTime, hasSupernuke, gridHeight, gridWidth);
            // ....
            Platform.exit();

        } catch (InvalidValueException e) {
            System.out.println(Messages.configExceptionMessages.get(e.getMessage()));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
