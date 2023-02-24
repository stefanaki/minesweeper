package medialab.minesweeper;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import medialab.minesweeper.controller.GameBoardController;
import medialab.minesweeper.model.GameBoardModel;
import medialab.minesweeper.utility.GameConfig;
import medialab.minesweeper.view.GameBoardView;

import java.io.IOException;

public class Main extends Application {
    static boolean isGameBoardSet = false;
    static GameBoardModel model;
    static GameBoardView view;
    static GameBoardController controller;

    static Stage primaryStage;

    public static void setGameBoard(GameConfig gameConfig) {
        try {
            model = new GameBoardModel(gameConfig);
            view = new GameBoardView(model);
            controller = new GameBoardController(model, view);
            isGameBoardSet = true;

            updatePrimaryStageView(primaryStage);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static void updatePrimaryStageView(Stage primaryStage) throws IOException {
        // Load the main menu view
        FXMLLoader mainMenuLoader = new FXMLLoader(Main.class.getResource("fxml/main-menu.fxml"));
        BorderPane rootLayout = mainMenuLoader.load();

        if (!isGameBoardSet) {
            // Load the initial view and add it to a new layout container
            FXMLLoader welcomeLoader = new FXMLLoader(Main.class.getResource("fxml/welcome-view.fxml"));
            AnchorPane welcomeView = welcomeLoader.load();

            rootLayout.setCenter(welcomeView);
        } else {
            rootLayout.setCenter(view.getRoot());
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

    @Override
    public void start(Stage stage) throws IOException {
        primaryStage = stage;

        primaryStage.setTitle("Medialab Minesweeper");
        primaryStage.setResizable(true);
        primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("images/smileywon.gif")));
        updatePrimaryStageView(stage);
    }
}