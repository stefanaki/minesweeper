module medialab.minesweeper {
    requires javafx.controls;
    requires javafx.fxml;


    opens medialab.minesweeper to javafx.fxml;
    exports medialab.minesweeper;
    exports medialab.minesweeper.controller;
    opens medialab.minesweeper.controller to javafx.fxml;
}