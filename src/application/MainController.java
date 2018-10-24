package application;


import java.io.IOException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

public class MainController implements Initializable {

    @FXML
    private ListView<Word> searchResult;

    @FXML
    private TextField searchField;

    @FXML
    private Button findButton;

    @FXML
    private Button insertButton;

    @FXML
    private Button editButton;

    @FXML
    private Button deleteButton;

    @FXML
    private WebView meaningField;

    private Dictionary dictionary;

    private FXMLLoader editLoader;
    private EditController editController;

    private FXMLLoader insertLoader;
    private InsertController insertController;


    public Dictionary getDictionary() {
        return this.dictionary;
    }

    public Word getSelectedWord() {
        return searchResult.getSelectionModel().getSelectedItem();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        SQLiteJDBCDriverConnection sqLiteJDBCDriverConnection = new SQLiteJDBCDriverConnection();
        sqLiteJDBCDriverConnection.connect();
        dictionary = new Dictionary(sqLiteJDBCDriverConnection.initialDictionary());
        searchResult.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        initSearchResult();
        sqLiteJDBCDriverConnection.disconnect();

//        try {
//            FXMLLoader fxmlLoader = new FXMLLoader();
//            fxmlLoader.setLocation(getClass().getResource("/application/EditUI.fxml"));
//            fxmlLoader.load();
//            editController = fxmlLoader.getController();
//            editController.initData(this);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

    }


    public void initSearchResult() {
        ObservableList<Word> items = FXCollections.observableArrayList(dictionary.iterate());
        searchResult.setItems(items);
    }

    public void onSearchFieldChange(KeyEvent e) {
        if (e.getSource() == searchField) {
            String wordTarget = searchField.getText();
            lockUp(wordTarget);
        }
    }

    public void lockUp(String wordTarget) {
        ObservableList<Word> items = FXCollections.observableArrayList(dictionary.lockUp(wordTarget));
        searchResult.setItems(items);
    }

    public void onClickedResultField() {
        Word w = searchResult.getSelectionModel().getSelectedItem();
        if (w != null) {
            displayMeaning(w.getWordMeaning());
        }
    }

    public void displayMeaning(String html) {
        WebEngine webEngine = meaningField.getEngine();
        webEngine.loadContent(html, "text/html");
        ;
    }

    public void onClickedDeleteButton() {
        Word w = searchResult.getSelectionModel().getSelectedItem();
        this.dictionary.deleteWord(w.getWordTarget(), w);
        //reset search field
        initSearchResult();
        SQLiteJDBCDriverConnection sqLiteJDBCDriverConnection = new SQLiteJDBCDriverConnection();
        sqLiteJDBCDriverConnection.connect();
        //sqLiteJDBCDriverConnection.deleteWordDictionary(w.getId());
        sqLiteJDBCDriverConnection.disconnect();
    }

    public void onClickedEditButton() {
        try {
            editLoader = new FXMLLoader(getClass().getResource("/application/EditUI.fxml"));
            Scene editScene = new Scene( editLoader.load());

            Stage newWindow = new Stage();
            newWindow.setTitle("Edit");
            newWindow.setScene(editScene);


            newWindow.setX(200);
            newWindow.setY(100);

            editController =
                    editLoader.<EditController>getController();
            editController.initData(searchResult.getSelectionModel().getSelectedItem());
            editController.setEditStage(newWindow);
            newWindow.show();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void onClickedInsertButton() {
        try {
            insertLoader = new FXMLLoader(getClass().getResource("/application/InsertUI.fxml"));
            Scene insertScene = new Scene( insertLoader.load());

            Stage newWindow = new Stage();
            newWindow.setTitle("Edit");
            newWindow.setScene(insertScene);


            newWindow.setX(200);
            newWindow.setY(100);
            insertController = insertLoader.<InsertController>getController();
            insertController.setInsertStage(newWindow);
            newWindow.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



}

