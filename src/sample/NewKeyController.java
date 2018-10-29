package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class NewKeyController {
    @FXML
    private TextField newGameKey, newGameName;
    private MainStageController mainStageController;
    private Scene mainScene;

    public void addKey(ActionEvent actionEvent) {
        mainStageController.addGame(newGameName.getText(), newGameKey.getText());

        newGameName.clear();
        newGameKey.clear();

        goToMainScene(actionEvent);
    }

    public void goToMainScene(ActionEvent actionEvent) {
        Stage sourceStage = ((Stage) ((Node)actionEvent.getSource()).getScene().getWindow());
        sourceStage.setTitle("SteamSpares!");
        sourceStage.setScene(mainScene);
    }

    public void setMainStageController(MainStageController mainStageController) {
        this.mainStageController = mainStageController;
    }

    public void setMainScene(Scene mainScene) {
        this.mainScene = mainScene;
    }
}
