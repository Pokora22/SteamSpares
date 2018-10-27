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
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class Controller {
    @FXML
    private AnchorPane validPane;
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

    @FXML
    public void initialize(){
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
                    System.out.println(innerNode.getClass());
                    System.out.println(Label.class);
                    if (innerNode.getClass().equals(Label.class) ) {
                        if( ((Label)innerNode).getText().equals(game.getName()) ) {
                            System.out.println("Found label: " + innerNode.toString());
                            return (FlowPane)node;
                        }
                    }
                }
            }
        }
        return null;
    }

    public void addKey(ActionEvent actionEvent) {
        //TODO: Add new item
    }
}
