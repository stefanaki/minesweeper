package medialab.minesweeper;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import medialab.minesweeper.utilities.GameConfigFileParser;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("fxml/hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        // launch();

        try {
            GameConfigFileParser p = new GameConfigFileParser("/home/giorgis/Desktop/examples/level_2_example.txt");
            System.out.println(p.getGameConfig().toString());
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}