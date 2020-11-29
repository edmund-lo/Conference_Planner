package organizer;

import common.ILoggedInView;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import model.User;

public interface IMessageUsersView extends ILoggedInView {
    TableView<User> getUserTable();
    TableColumn<User, Boolean> getCheckedColumn();
    TableColumn<User, String> getFirstNameColumn();
    TableColumn<User, String> getLastNameColumn();
    TableColumn<User, String> getUsernameColumn();
    CheckBox getSelectAll();
    String getSender();
    void setSender(String sender);
    String getRecipients();
    void setRecipients(String recipients);
    String getContent();
    void setContent(String content);
    void setResultMsg(String resultMsg);
    TextField getRecipientsField();

    EventHandler<ActionEvent> getSendButtonAction();
    void setSendButtonAction(EventHandler<ActionEvent> eventHandler);
    EventHandler<ActionEvent> getSelectAllAction();
    void setSelectAllAction(EventHandler<ActionEvent> eventHandler);
}
