package medialab.minesweeper.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.MenuItem;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import medialab.minesweeper.Main;

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
    private MenuItem menuItemRounds;

    @FXML
    private MenuItem menuItemSolution;

    @FXML
    private void onMenuItemCreateClicked() throws IOException {
        try {
            Stage stage = new Stage();
            FXMLLoader loader = new FXMLLoader(Main.class.getResource("fxml/create-game.fxml"));

            Parent root = (Parent) loader.load();
            Scene scene = new Scene(root);
            stage.setResizable(false);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void onMenuItemLoadClicked() {
        // Show a file chooser for selecting a saved game to load
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Resource File");
        fileChooser.showOpenDialog(new Stage());


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