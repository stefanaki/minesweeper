package medialab.minesweeper.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import medialab.minesweeper.Main;
import medialab.minesweeper.definition.Messages;
import medialab.minesweeper.exception.InvalidDescriptionException;
import medialab.minesweeper.exception.InvalidValueException;
import medialab.minesweeper.utility.GameConfig;
import medialab.minesweeper.utility.GameConfigFileParser;
import medialab.minesweeper.view.PreviousRoundsView;

import java.io.File;
import java.io.IOException;

public class MainMenuController implements Controller {

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
        Main.startGame();
    }

    @FXML
    private void onMenuItemExitClicked() {
        System.exit(0);
    }

    @FXML
    private void onMenuItemRoundsClicked() {
        Stage stage = new Stage();

        VBox previousRoundsView = new PreviousRoundsView().getRoot();
        Scene scene = new Scene(previousRoundsView);
        stage.setResizable(true);
        stage.setTitle("Previous Rounds");
        stage.setScene(scene);
        stage.setWidth(600);
        stage.showAndWait();
    }

    @FXML
    private void onMenuItemSolutionClicked() {
        Main.getGameBoardModel().stopGame();
        Main.getGameBoardView().updateView();
    }
}