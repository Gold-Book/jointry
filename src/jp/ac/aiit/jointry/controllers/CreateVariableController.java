package jp.ac.aiit.jointry.controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Window;

public class CreateVariableController implements Initializable {

    @FXML
    private TextField variableName;
    private DialogOption selectedOption = DialogOption.CANCEL;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }

    public String getVariableName() {
        return variableName.getText();
    }

    public DialogOption getSelectedOption() {
        return selectedOption;
    }

    @FXML
    void handleBtnYesAction(ActionEvent event) {
        handleCloseAction(DialogOption.YES);
    }

    @FXML
    void handleEnterKey(KeyEvent event) {
        // TODO: ENTERキーが押されたらOKにしたい。getCode()だとUNDEFINEDが戻ってくる。
        // if (event.getCharacter().equals("\n")) {
        //    handleCloseAction(DialogOption.YES);
        //}
    }

    @FXML
    void handleBtnCancelAction(ActionEvent event) {
        handleCloseAction(DialogOption.CANCEL);
    }

    private void handleCloseAction(DialogOption selectedOption) {
        this.selectedOption = selectedOption;
        getWindow().hide();
    }

    private Window getWindow() {
        return variableName.getScene().getWindow();
    }

    public enum DialogOption {

        YES, CANCEL;
    }
}
