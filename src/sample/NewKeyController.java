package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class NewKeyController {
    @FXML
    private TextField newGameKey, newGameName;
    private MainStageController mainStageController;

    public void addKey(ActionEvent actionEvent) {
        mainStageController.addGame(newGameName.getText(), newGameKey.getText());

        newGameName.clear();
        newGameKey.clear();


    }

    public void setMainStageController(MainStageController mainStageController) {
        this.mainStageController = mainStageController;
    }

    public String getNewGameKey() {
        return newGameKey.getText();
    }

    public String getNewGameName() {
        return newGameName.getText();
    }
}
