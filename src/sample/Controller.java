package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class Controller {
    @FXML private Button newEntryBtn;
    @FXML private TableView<GameEntry> gamesTable;
    @FXML private TableColumn tName, tKey, tNote, tUsed;
    @FXML private TextField newNameString, newKeyString, newNoteString;

    public void initialize(){
        tName.setCellValueFactory(new PropertyValueFactory<GameEntry, String>("name"));
        tKey.setCellValueFactory(new PropertyValueFactory<GameEntry, String>("key"));
        tNote.setCellValueFactory(new PropertyValueFactory<GameEntry, String>("note"));
        tUsed.setCellValueFactory(new PropertyValueFactory<GameEntry, Boolean>("used"));

        gamesTable.setItems(gameEntries());
    }

    public ObservableList<GameEntry> gameEntries(){
        ObservableList<GameEntry> games = FXCollections.observableArrayList();
        for(int i = 0; i < 27; i++){
            games.add(new GameEntry("Name " + i,"key here", "note"));
        }
        for(GameEntry g:games){
            g.setUsed(games.indexOf(g)%4 == 0);
        }

        return games;
    }

    public void addNewKey(ActionEvent actionEvent) {
        gameEntries().add(new GameEntry(newNameString.getText(), newKeyString.getText(), newNoteString.getText()));
        clearForm();
        gameEntries().clear();
    }

    private void clearForm(){
        newNoteString.clear();
        newKeyString.clear();
        newNameString.clear();
    }
}
