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

public class InsertController implements  Initializable {
    @FXML
    private TextField insertWordTarget;

    @FXML
    private HTMLEditor insertWordMeaning;
    @FXML
    private Button insertConfirm;

    @FXML
    private Button insertCancel;

    public MainController mainController;
    public Stage insertStage;

    public void setInsertStage(Stage insertStage){
        this.insertStage = insertStage;
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    void initData(MainController mainController) {
        this.mainController = mainController;
        System.out.println(mainController.toString());
        if (mainController!=null&& mainController.getSelectedWord()!= null){
            System.out.println(mainController.getSelectedWord().getWordTarget());
            System.out.println(mainController.getSelectedWord().getWordTarget());
        }

    }




}
