package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class NewKeyController {
    @FXML
    private TextField newGameKey, newGameName;

    public void addKey(ActionEvent actionEvent) {
        Main.cntrl.addGame(newGameName.getText(), newGameKey.getText());

        newGameName.clear();
        newGameKey.clear();
    }

    public String getNewGameKey() {
        return newGameKey.getText();
    }

    public String getNewGameName() {
        return newGameName.getText();
    }
}
