package medialab.minesweeper.controller;

import javafx.fxml.FXML;
import javafx.scene.control.MenuItem;

public class MainMenuController {
    @FXML
    private MenuItem menuItemCreate;

    @FXML
    private MenuItem menuItemLoad;

    @FXML
    private MenuItem menuItemStart;

    @FXML
    private MenuItem menuItemExit;

    @FXML
    private void onMenuItemCreateClicked() {

    }

    @FXML
    private void onMenuItemLoadClicked() {
        // Show a file chooser for selecting a saved game to load
    }

    @FXML
    private void onMenuItemStartClicked() {
        // Start the game
    }

    @FXML
    private void onMenuItemExitClicked() {
        // Exit the application
    }
}