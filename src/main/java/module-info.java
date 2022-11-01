module medialab.minesweeper {
    requires javafx.controls;
    requires javafx.fxml;


    opens medialab.minesweeper to javafx.fxml;
    exports medialab.minesweeper;
}