package medialab.minesweeper.controller;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.Region;
import javafx.stage.Stage;
import medialab.minesweeper.Main;
import medialab.minesweeper.definition.Constants;
import medialab.minesweeper.definition.Messages;
import medialab.minesweeper.exception.InvalidValueException;
import medialab.minesweeper.utility.GameConfig;

public class CreateNewGameController implements Controller {
    @FXML
    ToggleGroup difficultyGroup;
    GameConfig gameConfig;
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

    @FXML
    public void initialize() {
        difficultyGroup = new ToggleGroup();
        easyDifficulty.setToggleGroup(difficultyGroup);
        advancedDifficulty.setToggleGroup(difficultyGroup);
    }

    @FXML
    public void onCreateGameClicked() {
        try {
            String scenarioId = this.scenarioIdField.getText();
            int selectedDifficulty = difficultyGroup.getSelectedToggle() == easyDifficulty ? 1 : 2;
            boolean hasSupernuke = hasSupernukeCheckbox.isSelected();
            int maxTime = Integer.parseInt(gameTimeField.getText());
            int numOfNukes = Integer.parseInt(nukesField.getText());
            int gridWidth = Constants.GridSizes.get(selectedDifficulty)[0];
            int gridHeight = Constants.GridSizes.get(selectedDifficulty)[1];

            gameConfig = new GameConfig(scenarioId, selectedDifficulty, numOfNukes, maxTime, hasSupernuke, gridHeight, gridWidth);

            Main.setGameBoard(gameConfig);
            Stage stage = (Stage) this.createButton.getScene().getWindow();
            stage.close();
        } catch (InvalidValueException e) {
            String errorMessage = Messages.configExceptionMessages.get(e.getMessage());
            Alert alert = new Alert(Alert.AlertType.INFORMATION, errorMessage);
            alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
            alert.showAndWait();
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "All the fields are required.");
            alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
            alert.showAndWait();
        }
    }
}
