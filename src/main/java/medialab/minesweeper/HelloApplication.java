package medialab.minesweeper;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import medialab.minesweeper.exception.InvalidDescriptionException;
import medialab.minesweeper.exception.InvalidValueException;
import medialab.minesweeper.model.MinesweeperModel;
import medialab.minesweeper.utility.GameConfigFileParser;

import java.io.IOException;
import java.util.Map;

public class HelloApplication {
/*    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("fxml/hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 640, 480);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }*/

    public static void main(String[] args) throws InvalidValueException, InvalidDescriptionException, IOException {
        GameConfigFileParser gameConfigParser = new GameConfigFileParser("/home/giorgis/Desktop/examples/level_2_example.txt");
        Map<String, Integer> config = gameConfigParser.getGameConfig();
        System.out.println(gameConfigParser.getGameConfig());
        MinesweeperModel model = new MinesweeperModel(config.get("gridHeight"), config.get("gridWidth"), config.get("numOfNukes"), config.get("hasSupernuke") == 1);
    }
}