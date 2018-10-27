package sample;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

public class StageDirector {
    /*
    public void startMain(Stage mainWindow) throws Exception{
        double mult = 1.02;

        Parent root = FXMLLoader.load(getClass().getResource("mainWindow.fxml"));
        mainWindow.setTitle("Your spares!");
        Scene mainScene = new Scene(root, 700, 500);
        mainWindow.setMaxHeight(mainScene.getHeight()+40);
        mainWindow.setMinHeight(mainScene.getHeight()+40);
        mainWindow.setMaxWidth(mainScene.getWidth()*mult);
        mainWindow.setMinWidth(mainScene.getWidth()*mult);
        mainWindow.setScene(mainScene);

        mainWindow.show();
    }
    */

    /*
    public void startNewKey() {
        Stage newKeyStage = new Stage();
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("newKeyWindow.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        newKeyStage.setTitle("Add new key");
        Scene mainScene = new Scene(root, 600, 300);
        newKeyStage.setMaxHeight(mainScene.getHeight()+40);
        newKeyStage.setMinHeight(mainScene.getHeight()+40);
        newKeyStage.setMaxWidth(mainScene.getWidth());
        newKeyStage.setMinWidth(mainScene.getWidth());
        newKeyStage.setScene(mainScene);

        newKeyStage.show();
    }
    */
}
