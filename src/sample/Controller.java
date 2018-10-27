package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

public class Controller {
    @FXML
    public AnchorPane validPane, usedPane;
    @FXML
    private FlowPane usedPaneContent, validPaneContent;
    @FXML
    private Button btnValid, btnUsed, btnNewKey;
    private ArrayList<AnchorPane> entries;

    @FXML
    private void handleButtonAction(ActionEvent actionEvent) {
        if(actionEvent.getSource() == btnUsed) usedPane.toFront();
        else if (actionEvent.getSource() == btnValid) validPane.toFront();
    }

    @FXML
    private void addNewKey(ActionEvent actionEvent) {
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

    private void showEntries(){

    }

    public void fillKeys(Collection<GameEntry> games){
        for(GameEntry game:games){
            FlowPane location = game.isUsed()? usedPaneContent:validPaneContent;
            try {
                location.getChildren().add(FXMLLoader.load(getClass().getResource("validKeyEntry.fxml")));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    private void addKey(ActionEvent actionEvent) {
    }

    public void addNote(ActionEvent actionEvent) {
    }

    public void copyKeyToCboard(ActionEvent actionEvent) {
    }

    public void moveKey(ActionEvent actionEvent) {
    }
}
