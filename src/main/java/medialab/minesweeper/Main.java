package medialab.minesweeper;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import medialab.minesweeper.controller.GameBoardController;
import medialab.minesweeper.model.GameBoardModel;
import medialab.minesweeper.utility.GameConfig;
import medialab.minesweeper.view.GameBoardView;
import medialab.minesweeper.view.GameStateInfoView;

import java.io.IOException;

public class Main extends Application {
    static GameBoardModel gameBoardModel;
    static GameBoardView gameBoardView;

    static GameStateInfoView gameStateView;
    static GameBoardController gameBoardController;

    static Stage primaryStage;

    public static void setGameBoard(GameConfig gameConfig) {
        try {
            gameBoardModel = new GameBoardModel(gameConfig);
            gameBoardView = new GameBoardView(gameBoardModel);
            gameBoardController = new GameBoardController(gameBoardModel, gameBoardView);
            gameStateView = new GameStateInfoView(gameBoardModel);

            updatePrimaryScene(primaryStage);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static void startGame() {
        try {
            if (gameBoardModel != null) {
                if (gameBoardModel.isGameOver()) {
                    gameBoardModel = new GameBoardModel(gameBoardModel.getGameConfig());
                    gameBoardView = new GameBoardView(gameBoardModel);
                    gameBoardController = new GameBoardController(gameBoardModel, gameBoardView);
                    gameStateView = new GameStateInfoView(gameBoardModel);

                    updatePrimaryScene(primaryStage);
                } else {
                    gameBoardModel.startGame();
                }
            }
        } catch (IOException e) {

        }
    }

    public static void updatePrimaryScene(Stage primaryStage) throws IOException {
        // Load the main menu view
        FXMLLoader mainMenuLoader = new FXMLLoader(Main.class.getResource("fxml/main-menu.fxml"));
        BorderPane rootLayout = mainMenuLoader.load();

        if (gameBoardView == null) {
            // Load the initial view and add it to a new layout container
            FXMLLoader welcomeLoader = new FXMLLoader(Main.class.getResource("fxml/welcome-view.fxml"));
            AnchorPane welcomeView = welcomeLoader.load();

            primaryStage.setResizable(true);
            rootLayout.setCenter(welcomeView);
            primaryStage.setResizable(false);
        } else {
            primaryStage.setResizable(true);
            VBox main = new VBox();
            main.setAlignment(Pos.CENTER);
            main.setFillWidth(true);
            main.setStyle("-fx-background-color: lightgrey");
            rootLayout.setCenter(main);
            main.getChildren().addAll(gameStateView.getRoot(), gameBoardView.getRoot());
            primaryStage.setResizable(false);
        }

        // Set the scene and show the stage
        Scene scene = new Scene(rootLayout);
        primaryStage.setScene(scene);
        primaryStage.sizeToScene();
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

    public static GameBoardModel getGameBoardModel() {
        return gameBoardModel;
    }

    public static GameBoardView getGameBoardView() {
        return gameBoardView;
    }

    @Override
    public void start(Stage stage) throws IOException {
        primaryStage = stage;
        primaryStage.setTitle("Medialab Minesweeper");
        primaryStage.setResizable(false);
        primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("images/smileywon.gif")));
        updatePrimaryScene(primaryStage);
    }
}