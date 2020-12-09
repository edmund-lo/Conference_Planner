package admin.impl;

import admin.IUnlockAccountsView;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.LoginLog;
import model.UserAccount;
import java.time.LocalDateTime;

public class UnlockAccountsView implements IUnlockAccountsView {
    @FXML
    private TableView<UserAccount> userTable;
    @FXML
    private TableView<LoginLog> logsTable;
    @FXML
    private TableColumn<UserAccount, String> usernameColumn;
    @FXML
    private TableColumn<UserAccount, String> userTypeColumn;
    @FXML
    private TableColumn<UserAccount, Boolean> lockedColumn;
    @FXML
    private TableColumn<LoginLog, LocalDateTime> loginTimeColumn;
    @FXML
    private TableColumn<LoginLog, Boolean> successColumn;
    @FXML
    private TextField username;
    @FXML
    private TextField userType;
    @FXML
    private Text resultText;

    @FXML
    public void executeAddUnlock(ActionEvent event) {
        if (unlockButtonAction != null) unlockButtonAction.handle(event);
    }
    @FXML
    public void initialize() {
        this.presenter = new UnlockAccountsPresenter(this);
    }

    private UnlockAccountsPresenter presenter;
    private EventHandler<ActionEvent> unlockButtonAction;
    private Stage stage;
    private String sessionUsername;
    private String sessionUserType;

    @Override
    public TableView<UserAccount> getUserTable() {
        return this.userTable;
    }

    @Override
    public TableColumn<UserAccount, String> getUsernameColumn() {
        return this.usernameColumn;
    }

    @Override
    public TableColumn<UserAccount, String> getUserTypeColumn() {
        return this.userTypeColumn;
    }

    @Override
    public TableColumn<UserAccount, Boolean> getLockedColumn() {
        return this.lockedColumn;
    }

    @Override
    public TableView<LoginLog> getLogsTable() {
        return this.logsTable;
    }

    @Override
    public TableColumn<LoginLog, LocalDateTime> getLoginTimeColumn() {
        return this.loginTimeColumn;
    }

    @Override
    public TableColumn<LoginLog, Boolean> getSuccessColumn() {
        return this.successColumn;
    }

    @Override
    public String getUsername() {
        return this.username.getText();
    }

    @Override
    public void setUsername(String username) {
        this.username.setText(username);
    }

    @Override
    public String getUserType() {
        return this.userType.getText();
    }

    @Override
    public void setUserType(String userType) {
        this.userType.setText(userType);
    }

    @Override
    public void setResultText(String resultText) {
        this.resultText.setText(resultText);
    }

    @Override
    public void setUnlockButtonAction(EventHandler<ActionEvent> eventHandler) {
        this.unlockButtonAction = eventHandler;
    }

    @Override
    public String getSessionUsername() {
        return this.sessionUsername;
    }

    @Override
    public void setSessionUsername(String username) {
        this.sessionUsername = username;
    }

    @Override
    public String getSessionUserType() {
        return this.sessionUserType;
    }

    @Override
    public void setSessionUserType(String userType) {
        this.sessionUserType = userType;
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
