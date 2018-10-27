package sample;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class Controller {
    @FXML
    private TextField newGameKey, newGameName;
    @FXML
    private AnchorPane validPaneWrapper;
    @FXML
    private AnchorPane usedPane;
    @FXML
    private FlowPane usedPaneContent, validPaneContent;
    @FXML
    private Button btnValid, btnUsed, btnNewKey;

    @SuppressWarnings("CanBeFinal")
    private ArrayList<FlowPane> locationSet = new ArrayList<>();
    private ArrayList<GameEntry> games = new ArrayList<>();

    @FXML
    private void handleButtonAction(ActionEvent actionEvent) {
        if(actionEvent.getSource() == btnUsed) usedPane.toFront();
        else if (actionEvent.getSource() == btnValid) validPaneWrapper.toFront();
    }

    @FXML
    private void newKeyWindow(ActionEvent actionEvent) {
        System.out.println(validPaneContent);
        Stage newKeyStage = new Stage();
        System.out.println(validPaneContent);
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("newKeyWindow.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(validPaneContent);
        newKeyStage.setTitle("Add new key");
        Scene mainScene = new Scene(root, 600, 300);
        newKeyStage.setMaxHeight(mainScene.getHeight()+40);
        newKeyStage.setMinHeight(mainScene.getHeight()+40);
        newKeyStage.setMaxWidth(mainScene.getWidth());
        newKeyStage.setMinWidth(mainScene.getWidth());
        newKeyStage.setScene(mainScene);
        System.out.println(validPaneContent);
        newKeyStage.show();
        System.out.println(validPaneContent);
    }

    @FXML
    public void fillContent(){
        locationSet.add(validPaneContent);
        locationSet.add(usedPaneContent);

        //TODO: Move new games to a loader somewhere - below is just testing
        for(int i = 0; i < 27; i++){
            games.add(new GameEntry("Name " + i,"key here"));
        }
        for(GameEntry g:games){
            g.setUsed(games.indexOf(g)%4 == 0);
        }

        for(GameEntry game:games){
            FlowPane location = game.isUsed()? usedPaneContent:validPaneContent;
            location.getChildren().add(newEntryPanel(game, location));
        }
    }

    private FlowPane newEntryPanel(GameEntry game, FlowPane location){
        FlowPane pane = new FlowPane();

        Button moveBtn = new Button("Used/Unused");
        moveBtn.setTranslateX(10);
        moveBtn.setOnAction(actionEvent -> {
            game.setUsed(!game.isUsed());
            location.getChildren().remove(findGamePanel(game, location));
            FlowPane newLoc = locationSet.get((locationSet.indexOf(location)+1)%locationSet.size());
            newLoc.getChildren().add(newEntryPanel(game, newLoc));
        });

        Button codeBtn = new Button("Copy key");
        codeBtn.setTranslateX(5);
        codeBtn.setOnAction(e->{
            ClipboardContent cc = new ClipboardContent();
            cc.putString(game.getKey());
            Clipboard.getSystemClipboard().setContent(cc);
        });

        Label gName = new Label(game.getName());
        gName.setWrapText(true);
        gName.setMinWidth(200);
        gName.setPadding(new Insets(2, 10, 2,10));

        pane.getChildren().addAll(gName, codeBtn, moveBtn);

        return pane;
    }


    private FlowPane findGamePanel(GameEntry game, FlowPane location){
        for(Node node: location.getChildren()){
            {
                ObservableList<Node> innerLoc = ((FlowPane)node).getChildren();
                for(Node innerNode : innerLoc){
                    if (innerNode.getClass().equals(Label.class) && ((Label) innerNode).getText().equals(game.getName()))
                        return (FlowPane) node;
                }
            }
        }
        return null;
    }

    public void addKey(ActionEvent actionEvent) {
        System.out.println(validPaneContent);
        GameEntry newGame = new GameEntry(newGameName.getText(), newGameKey.getText());
        games.add(newGame);
        validPaneWrapper.getChildren().add(newEntryPanel(newGame, validPaneContent)); //TODO: returns null when new window is open - switch to multiple controllers?

        newGameName.clear();
        newGameKey.clear();

        //TODO: Add new item
    }
}
