package attendee;

import common.ILoggedInView;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import model.User;

public interface IFriendsView extends ILoggedInView {
    TableView<User> getFriendTable();
    TableColumn<User, String> getFirstNameFriendColumn();
    TableColumn<User, String> getLastNameFriendColumn();
    TableColumn<User, String> getUsernameFriendColumn();
    TableColumn<User, String> getUserTypeFriendColumn();
    TableView<User> getUserTable();
    TableColumn<User, String> getFirstNameUserColumn();
    TableColumn<User, String> getLastNameUserColumn();
    TableColumn<User, String> getUsernameUserColumn();
    TableColumn<User, String> getUserTypeUserColumn();
    TableColumn<User, Boolean> getPendingUserColumn();
    Text getResultTextControl(int index);
    VBox getCommonEventTableContainer();

    void setUsernameFriend(String username);
    void setFirstNameFriend(String firstName);
    void setLastNameFriend(String lastName);
    void setUserTypeFriend(String userType);
    void setUsernameUser(String username);
    void setFirstNameUser(String firstName);
    void setLastNameUser(String lastName);
    void setUserTypeUser(String userType);
    void setResultText(String resultText, int index);

    EventHandler<ActionEvent> getAddFriendButtonAction();
    void setAddFriendButtonAction(EventHandler<ActionEvent> eventHandler);
    EventHandler<ActionEvent> getRemoveFriendButtonAction();
    void setRemoveFriendButtonAction(EventHandler<ActionEvent> eventHandler);
}
