package organizer.impl;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.User;
import organizer.IMessageUsersView;

public class MessageSpeakersView implements IMessageUsersView {
    @FXML
    private TableView<User> userTable;
    @FXML
    private CheckBox selectAll;
    @FXML
    private TableColumn<User, Boolean> checkedColumn;
    @FXML
    private TableColumn<User, String> firstNameColumn;
    @FXML
    private TableColumn<User, String> lastNameColumn;
    @FXML
    private TableColumn<User, String> usernameColumn;
    @FXML
    private TextField sender;
    @FXML
    private TextField recipients;
    @FXML
    private TextArea content;
    @FXML
    private Text result;

    @FXML
    public void executeAddSend(ActionEvent event) {
        if (createAccountButtonAction != null) createAccountButtonAction.handle(event);
    }
    @FXML
    public void executeAddSelectAll(ActionEvent event) {
        if (selectAllAction != null) selectAllAction.handle(event);
    }
    @FXML
    public void initialize() {
        this.presenter = new MessageSpeakersPresenter(this);
    }

    private MessageSpeakersPresenter presenter;
    private EventHandler<ActionEvent> createAccountButtonAction;
    private EventHandler<ActionEvent> selectAllAction;
    private Stage stage;
    private String sessionUsername;

    @Override
    public TableView<User> getUserTable() {
        return this.userTable;
    }

    @Override
    public TableColumn<User, Boolean> getCheckedColumn() {
        return this.checkedColumn;
    }

    @Override
    public TableColumn<User, String> getFirstNameColumn() {
        return this.firstNameColumn;
    }

    @Override
    public TableColumn<User, String> getLastNameColumn() {
        return this.lastNameColumn;
    }

    @Override
    public TableColumn<User, String> getUsernameColumn() {
        return this.usernameColumn;
    }

    @Override
    public CheckBox getSelectAll() {
        return this.selectAll;
    }

    @Override
    public String getSender() {
        return this.sender.getText();
    }

    @Override
    public void setSender(String sender) {
        this.sender.setText(sender);
    }

    @Override
    public String getRecipients() {
        return this.recipients.getText();
    }

    @Override
    public void setRecipients(String recipients) {
        this.recipients.setText(recipients);
    }

    @Override
    public String getContent() {
        return this.content.getText();
    }

    @Override
    public void setContent(String content) {
        this.content.setText(content);
    }

    @Override
    public void setResultMsg(String resultMsg) {
        this.result.setText(resultMsg);
    }

    @Override
    public TextField getRecipientsField() {
        return this.recipients;
    }

    @Override
    public EventHandler<ActionEvent> getSendButtonAction() {
        return this.createAccountButtonAction;
    }

    @Override
    public void setSendButtonAction(EventHandler<ActionEvent> eventHandler) {
        this.createAccountButtonAction = eventHandler;
    }

    @Override
    public EventHandler<ActionEvent> getSelectAllAction() {
        return this.selectAllAction;
    }

    @Override
    public void setSelectAllAction(EventHandler<ActionEvent> eventHandler) {
        this.selectAllAction = eventHandler;
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
    public String getSessionUsername() {
        return this.sessionUsername;
    }

    @Override
    public void setSessionUsername(String username) {
        this.sessionUsername = username;
    }
}
