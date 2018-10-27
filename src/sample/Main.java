package sample;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class Main extends Application {
    private static ArrayList<GameEntry> games;

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

        cntrl.fillKeys(games);
        mainWindow.show();
    }

    public static void main(String[] args) {
        games = new ArrayList<>();
        for(int i = 0; i < 27; i++){
            games.add(new GameEntry("Name " + i,"key here"));
        }
        for(GameEntry g:games){
            g.setUsed(games.indexOf(g)%4 == 0);
        }
        launch(args);
    }




}
