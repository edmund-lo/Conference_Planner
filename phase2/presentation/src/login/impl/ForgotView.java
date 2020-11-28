package login.impl;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import login.IForgotView;

public class ForgotView implements IForgotView {
    @FXML
    private TextField username;
    @FXML
    private Text promptText;
    @FXML
    private PasswordField promptInput;
    @FXML
    private Text result;

    @FXML
    public void executeAddGoBack(ActionEvent event) {
        if (backButtonAction != null) backButtonAction.handle(event);
    }
    @FXML
    public void executeAddRecover(ActionEvent event) {
        if (recoverButtonAction != null) recoverButtonAction.handle(event);
    }
    @FXML
    public void initialize() {
        this.presenter = new ForgotPresenter(this);
    }

    private ForgotPresenter presenter;
    private EventHandler<ActionEvent> recoverButtonAction;
    private EventHandler<ActionEvent> backButtonAction;
    private Stage stage;

    @Override
    public String getUsername() {
        return this.username.getText();
    }

    @Override
    public void setUsername(String username) {
        this.username.setText(username);
    }

    @Override
    public String getPromptText() {
        return this.promptText.getText();
    }

    @Override
    public void setPromptText(String promptText) {
        this.promptText.setText(promptText);
    }

    @Override
    public String getPromptInput() {
        return this.promptInput.getText();
    }

    @Override
    public void setPromptInput(String promptInput) {
        this.promptInput.setText(promptInput);
    }

    @Override
    public void setResultMsg(String result) {
        this.result.setText(result);
    }

    @Override
    public TextField getUsernameField() {
        return this.username;
    }

    @Override
    public PasswordField getPromptInputField() {
        return this.promptInput;
    }

    @Override
    public EventHandler<ActionEvent> getBackButtonAction() {
        return backButtonAction;
    }

    @Override
    public void setBackButtonAction(EventHandler<ActionEvent> eventHandler) {
        this.backButtonAction = eventHandler;
    }

    @Override
    public EventHandler<ActionEvent> getRecoverButtonAction() {
        return recoverButtonAction;
    }

    @Override
    public void setRecoverButtonAction(EventHandler<ActionEvent> eventHandler) {
        this.recoverButtonAction = eventHandler;
    }

    @Override
    public Stage getStage() {
        return this.stage;
    }

    @Override
    public void setStage(Stage stage) {
        this.stage = stage;
    }
}
