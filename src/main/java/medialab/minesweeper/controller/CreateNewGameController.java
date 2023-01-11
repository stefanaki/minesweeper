package medialab.minesweeper.controller;

import javafx.fxml.FXML;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;

public class CreateNewGameController {
    @FXML
    private TextField scenarioIdField;

    @FXML
    private RadioButton easyDifficulty;

    @FXML
    private RadioButton hardDifficulty;

    @FXML
    private TextField gameTimeField;

    @FXML
    private TextField nukesField;

    @FXML
    public void initialize() {
        // This method is called when the FXML file is loaded.
        // You can initialize any variables or other objects here.
    }

    public int getScenarioId() {
        return Integer.parseInt(scenarioIdField.getText());
    }

    public void initializeGame() {
    }
}
