package attendee.impl;

import attendee.IFriendsView;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.User;

public class FriendsView implements IFriendsView {
    @FXML
    private TableView<User> friendTable;
    @FXML
    private TableView<User> userTable;
    @FXML
    private TableColumn<User, String> firstNameFriendColumn;
    @FXML
    private TableColumn<User, String> lastNameFriendColumn;
    @FXML
    private TableColumn<User, String> usernameFriendColumn;
    @FXML
    private TableColumn<User, String> userTypeFriendColumn;
    @FXML
    private TableColumn<User, String> firstNameUserColumn;
    @FXML
    private TableColumn<User, String> lastNameUserColumn;
    @FXML
    private TableColumn<User, String> usernameUserColumn;
    @FXML
    private TableColumn<User, String> userTypeUserColumn;
    @FXML
    private TableColumn<User, Boolean> pendingUserColumn;
    @FXML
    private Text usernameFriend;
    @FXML
    private Text firstNameFriend;
    @FXML
    private Text lastNameFriend;
    @FXML
    private Text userTypeFriend;
    @FXML
    private Text usernameUser;
    @FXML
    private Text firstNameUser;
    @FXML
    private Text lastNameUser;
    @FXML
    private Text userTypeUser;
    @FXML
    private Text resultText1;
    @FXML
    private Text resultText2;
    @FXML
    private VBox commonEventTableContainer;

    @FXML
    public void executeAddRemoveFriend(ActionEvent event) {
        if (removeFriendButtonAction != null) removeFriendButtonAction.handle(event);
    }
    @FXML
    public void executeAddAddFriend(ActionEvent event) {
        if (addFriendButtonAction != null) addFriendButtonAction.handle(event);
    }
    @FXML
    public void initialize() { this.presenter = new FriendsPresenter(this); }

    private FriendsPresenter presenter;
    private EventHandler<ActionEvent> removeFriendButtonAction;
    private EventHandler<ActionEvent> addFriendButtonAction;
    private Stage stage;
    private String sessionUsername;
    private String sessionUserType;

    @Override
    public TableView<User> getFriendTable() {
        return this.friendTable;
    }

    @Override
    public TableColumn<User, String> getFirstNameFriendColumn() {
        return this.firstNameFriendColumn;
    }

    @Override
    public TableColumn<User, String> getLastNameFriendColumn() {
        return this.lastNameFriendColumn;
    }

    @Override
    public TableColumn<User, String> getUsernameFriendColumn() {
        return this.usernameFriendColumn;
    }

    @Override
    public TableColumn<User, String> getUserTypeFriendColumn() {
        return this.userTypeFriendColumn;
    }

    @Override
    public TableView<User> getUserTable() {
        return this.userTable;
    }

    @Override
    public TableColumn<User, String> getFirstNameUserColumn() {
        return this.firstNameUserColumn;
    }

    @Override
    public TableColumn<User, String> getLastNameUserColumn() {
        return this.lastNameUserColumn;
    }

    @Override
    public TableColumn<User, String> getUsernameUserColumn() {
        return this.usernameUserColumn;
    }

    @Override
    public TableColumn<User, String> getUserTypeUserColumn() {
        return this.userTypeUserColumn;
    }

    @Override
    public TableColumn<User, Boolean> getPendingUserColumn() {
        return this.pendingUserColumn;
    }

    @Override
    public Text getResultTextControl(int index) {
        Text resultText = new Text();
        if (index == 1) resultText = this.resultText1;
        if (index == 2) resultText = this.resultText2;
        return resultText;
    }

    @Override
    public VBox getCommonEventTableContainer() {
        return this.commonEventTableContainer;
    }

    @Override
    public void setUsernameFriend(String username) {
        this.usernameFriend.setText(username);
    }

    @Override
    public void setFirstNameFriend(String firstName) {
        this.firstNameFriend.setText(firstName);
    }

    @Override
    public void setLastNameFriend(String lastName) {
        this.lastNameFriend.setText(lastName);
    }

    @Override
    public void setUserTypeFriend(String userType) {
        this.userTypeFriend.setText(userType);
    }

    @Override
    public void setUsernameUser(String username) {
        this.usernameUser.setText(username);
    }

    @Override
    public void setFirstNameUser(String firstName) {
        this.firstNameUser.setText(firstName);
    }

    @Override
    public void setLastNameUser(String lastName) {
        this.lastNameUser.setText(lastName);
    }

    @Override
    public void setUserTypeUser(String userType) {
        this.userTypeUser.setText(userType);
    }

    @Override
    public void setResultText(String resultText, int index) {
        getResultTextControl(index).setText(resultText);
    }

    @Override
    public EventHandler<ActionEvent> getAddFriendButtonAction() {
        return this.addFriendButtonAction;
    }

    @Override
    public void setAddFriendButtonAction(EventHandler<ActionEvent> eventHandler) {
        this.addFriendButtonAction = eventHandler;
    }

    @Override
    public EventHandler<ActionEvent> getRemoveFriendButtonAction() {
        return this.removeFriendButtonAction;
    }

    @Override
    public void setRemoveFriendButtonAction(EventHandler<ActionEvent> eventHandler) {
        this.removeFriendButtonAction = eventHandler;
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
        return null;
    }
}
