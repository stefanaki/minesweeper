package medialab.minesweeper;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import medialab.minesweeper.controller.GameBoardController;
import medialab.minesweeper.exception.InvalidDescriptionException;
import medialab.minesweeper.exception.InvalidValueException;
import medialab.minesweeper.model.GameBoardModel;
import medialab.minesweeper.utility.GameConfigFileParser;
import medialab.minesweeper.view.GameBoardView;

import java.io.IOException;
import java.util.Map;

public class Bootstrap extends Application {
    private GameBoardModel model;
    private GameBoardView view;
    private GameBoardController controller;

    public Bootstrap() throws IOException, InvalidValueException, InvalidDescriptionException {
        GameConfigFileParser gameConfigParser = new GameConfigFileParser("/home/giorgis/Desktop/examples/level_2_example.txt");
        Map<String, Integer> config = gameConfigParser.getGameConfig();
        System.out.println(gameConfigParser.getGameConfig());
        model = new GameBoardModel(config.get("gridHeight"), config.get("gridWidth"), config.get("numOfNukes"), config.get("hasSupernuke") == 1);
        view = new GameBoardView(model);
        controller = new GameBoardController(model, view);
    }

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("fxml/main-menu.fxml"));

        // Add the game board view to the center of the root layout
        BorderPane rootLayout = loader.load();
        rootLayout.setCenter(view.getRoot());

        // Set the scene and show the stage
        Scene scene = new Scene(rootLayout);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Medialab Minesweeper");
        primaryStage.setResizable(false);
        primaryStage.show();
    }
}