package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.ArrayList;

public class Main extends Application {
    @Override
    public void start(Stage mainWindow) throws Exception{
        double mult = 1.02;

        FXMLLoader loader = new FXMLLoader(getClass().getResource("mainWindow.fxml"));
        Parent root = loader.load();
        Controller cntrl = loader.getController();
        mainWindow.setTitle("Your spares!");
        Scene mainScene = new Scene(root, 700, 500);
        mainWindow.setMaxHeight(mainScene.getHeight()+40);
        mainWindow.setMinHeight(mainScene.getHeight()+40);
        mainWindow.setMaxWidth(mainScene.getWidth()*mult);
        mainWindow.setMinWidth(mainScene.getWidth()*mult);
        mainWindow.setScene(mainScene);

        cntrl.fillContent();
        mainWindow.show();
    }

    public static void main(String[] args) {
        launch(args);
    }




}
