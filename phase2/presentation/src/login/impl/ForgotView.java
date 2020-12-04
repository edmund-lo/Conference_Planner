package login.impl;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import login.IForgotView;

public class ForgotView implements IForgotView {
    @FXML
    private TextField username;
    @FXML
    private Text securityQuestion;
    @FXML
    private TextField securityAnswer;
    @FXML
    private Text resultText;
    @FXML
    private VBox passwordRegion;

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
    public String getSecurityQuestion() {
        return this.securityQuestion.getText();
    }

    @Override
    public void setSecurityQuestion(String securityQuestion) {
        this.securityQuestion.setText(securityQuestion);
    }

    @Override
    public String getSecurityAnswer() {
        return this.securityAnswer.getText();
    }

    @Override
    public void setSecurityAnswer(String securityAnswer) {
        this.securityAnswer.setText(securityAnswer);
    }

    @Override
    public void setResultText(String resultText) {
        this.resultText.setText(resultText);
    }

    @Override
    public VBox getPasswordRegion() {
        return this.passwordRegion;
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

    @Override
    public Text getResultTextControl() {
        return this.resultText;
    }
}
