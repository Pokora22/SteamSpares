package sample;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.StaxDriver;
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

import java.io.*;
import java.util.ArrayList;

public class MainStageController {
    private XStream xStream;

    @FXML
    private AnchorPane validPaneWrapper, usedPaneWrapper, menuPane;;
    @FXML
    private FlowPane usedPaneContent, validPaneContent;
    @FXML
    private Button btnValid, btnUsed;
    @FXML
    private Label labelValidKeys, labelUsedKeys, labelTotalKeys;

    @SuppressWarnings("CanBeFinal")
    private ArrayList<FlowPane> locationSet = new ArrayList<>();
    private ArrayList<GameEntry> games = new ArrayList<>();

    @FXML
    public void initialize() throws IOException{
        xStream = new XStream(new StaxDriver());

        File file = new File("gameCodes.xml");
        if(!file.exists() || (file.exists() && !file.canRead())) { //TODO: Check for writing rights too
            saveList();
        }

        locationSet.add(validPaneContent);
        locationSet.add(usedPaneContent);
        btnValid.getStyleClass().add("rich-blue");

        loadList();
        for(GameEntry game:games){
            FlowPane location = game.isUsed() ? usedPaneContent : validPaneContent;
            location.getChildren().add(newEntryPanel(game, location));
        }
        updateTotals();
    }

    @FXML
    private void handleButtonAction(ActionEvent actionEvent) {
        if(actionEvent.getSource() == btnUsed) usedPaneWrapper.toFront();
        else if (actionEvent.getSource() == btnValid) validPaneWrapper.toFront();

        Button[] buttons = {btnUsed, btnValid};
        for(Button b: buttons){
            b.getStyleClass().clear();
            b.getStyleClass().add("button");
        }
        ((Button)actionEvent.getSource()).getStyleClass().add("rich-blue");
    }

    @FXML
    private void newKeyWindow(ActionEvent actionEvent) throws IOException{
        Stage sourceStage = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("newKeyWindow.fxml"));
        Parent root = loader.load();
        NewKeyController newKeyController = loader.getController();
        newKeyController.setMainStageController(this);
        newKeyController.setMainScene(sourceStage.getScene());
        sourceStage.setTitle("Add new key");
        sourceStage.setScene(new Scene(root, 600, 300));
    }

    private FlowPane newEntryPanel(GameEntry game, FlowPane location){
        FlowPane pane = new FlowPane();


        Button moveBtn = new Button("Used/Unused");
        moveBtn.setTranslateX(4);
        moveBtn.setOnAction(actionEvent -> {
            game.setUsed(!game.isUsed());
            location.getChildren().remove(findGamePanel(game, location));
            FlowPane newLoc = locationSet.get((locationSet.indexOf(location)+1)%locationSet.size());
            newLoc.getChildren().add(newEntryPanel(game, newLoc));
            updateTotals();
        });

        Button removeBtn = new Button("Remove");
        removeBtn.setTranslateX(6);
        removeBtn.setOnAction(e->{
            location.getChildren().remove(findGamePanel(game, location));
            games.remove(game);
            try {
                saveList();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
            updateTotals();
        });

        Button codeBtn = new Button("Copy key");
        codeBtn.setTranslateX(2);
        codeBtn.setOnAction(e->{
            ClipboardContent cc = new ClipboardContent();
            cc.putString(game.getKey());
            Clipboard.getSystemClipboard().setContent(cc);
        });

        Label gName = new Label(game.getName());
        gName.setWrapText(true);
        gName.setPrefWidth(180);
        gName.setMinHeight(50);
        gName.setPadding(new Insets(2, 10, 2,10));

        pane.getChildren().addAll(gName, codeBtn, moveBtn, removeBtn);

        try {
            saveList();
        } catch (IOException e) {
            e.printStackTrace();
        }

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

    public void addGame(String name, String key) {
        GameEntry newGame = new GameEntry(name, key);
        games.add(newGame);
        validPaneContent.getChildren().add(newEntryPanel(newGame, validPaneContent));

        try {
            saveList();
        } catch (IOException e) {
            e.printStackTrace();
        }

        updateTotals();
    }

    private void updateTotals(){
        int usedKeys = 0;
        int validKeys = 0;
        for(GameEntry g:games){
            if (g.isUsed()) usedKeys++;
            else validKeys++;
        }

        labelValidKeys.setText("Valid keys: " + validKeys);
        labelUsedKeys.setText("Used keys: " + usedKeys);
        labelTotalKeys.setText("Total keys: " + (validKeys+usedKeys));
    }

    private void loadList() throws IOException {
        ObjectInputStream is = null;
        try {
            is = xStream.createObjectInputStream(new FileReader("gameCodes.xml"));
            games = (ArrayList<GameEntry>) is.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        is.close();
    }

    private void saveList() throws IOException {
        ObjectOutputStream out = xStream.createObjectOutputStream(new FileWriter("gameCodes.xml"));
        out.writeObject(games);
        out.close();
    }
}
