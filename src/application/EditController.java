package application;

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
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.web.HTMLEditor;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

public class EditController implements  Initializable {
    @FXML
    private TextField editWordTarget;

    @FXML
    private HTMLEditor editWordMeaning;
    @FXML
    private Button editConfirm;

    @FXML
    private Button editCancel;

    public Word editWord;
    public Stage editStage;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void initData(Word word) {
        this.editWord = word;
        if(word==null){
            System.out.println("Please select a word");
        } else {
            editWordTarget.setText(word.getWordTarget());
            editWordMeaning.setHtmlText(word.getWordMeaning());
        }

    }

    public void setEditStage(Stage editStage) {
        this.editStage = editStage;
    }

    public void  onClickEdit (){
        this.editWord.setWordTarget(editWordTarget.getText());
        this.editWord.setWordMeaning(editWordMeaning.getHtmlText());
        SQLiteJDBCDriverConnection sqLiteJDBCDriverConnection = new SQLiteJDBCDriverConnection();
        sqLiteJDBCDriverConnection.connect();
        sqLiteJDBCDriverConnection.editWordDictionary(editWord.getId(), editWord.getWordTarget(), editWord.getWordMeaning());
        sqLiteJDBCDriverConnection.disconnect();
        this.editStage.close();
    }

    public void onClickCancel(){
        this.editStage.close();
    }




}
