package medialab.minesweeper.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.MenuItem;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

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
    private void onMenuItemCreateClicked() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("fxml/create-game.fxml"));
        Parent root = (Parent) fxmlLoader.load();
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("prospathw sklira");
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.show();

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
        System.exit(0);
    }
}