package medialab.minesweeper.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.Region;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import medialab.minesweeper.Main;
import medialab.minesweeper.definition.Messages;
import medialab.minesweeper.exception.*;
import medialab.minesweeper.utility.GameConfig;
import medialab.minesweeper.utility.GameConfigFileParser;

import java.io.File;
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
    private void onMenuItemCreateClicked() {
        try {
            Stage stage = new Stage();
            FXMLLoader loader = new FXMLLoader(Main.class.getResource("fxml/create-game.fxml"));

            Parent root = loader.load();
            Scene scene = new Scene(root);
            stage.setResizable(false);
            stage.setScene(scene);
            stage.showAndWait();


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void onMenuItemLoadClicked() {
        // Show a file chooser for selecting a saved game to load
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Game Configuration File");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Text Files", "*.txt"));
        File selectedFile = fileChooser.showOpenDialog(new Stage());

        if (selectedFile != null) {
            try {
                String fileName = selectedFile.getAbsolutePath();
                GameConfigFileParser parser = new GameConfigFileParser(fileName);
                GameConfig gameConfig = parser.getGameConfig();
                Main.setGameBoard(gameConfig);
            } catch (InvalidValueException | InvalidDescriptionException e) {
                String errorMessage = Messages.configExceptionMessages.get(e.getMessage());
                Alert alert = new Alert(Alert.AlertType.WARNING, errorMessage);
                alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
                alert.showAndWait();
            } catch (Exception e) {
                Alert alert = new Alert(Alert.AlertType.WARNING, "Something went wrong");
                alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
                alert.showAndWait();
            }
        }
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