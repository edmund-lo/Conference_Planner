package attendee;

import common.ILoggedInView;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import model.Message;

import java.time.LocalDateTime;
import java.util.List;

public interface IMessagingView extends ILoggedInView {
    void setPrimarySender(String sender);
    void setPrimaryRecipientNames(List<String> recipientNames);
    void setPrimarySubject(String subject);
    void setArchivedSender(String sender);
    void setArchivedRecipientNames(List<String> recipientNames);
    void setArchivedSubject(String subject);
    void setTrashSender(String sender);
    void setTrashRecipientNames(List<String> recipientNames);
    void setTrashSubject(String subject);
    String getContent();
    void setContent(String content);
    void setResultText(String resultText);

    TableView<Message> getPrimaryInbox();
    TableColumn<Message, String> getPrimaryMembersColumn();
    TableColumn<Message, String> getPrimarySubjectColumn();
    VBox getPrimaryThreadContainer();
    TableView<Message> getArchivedInbox();
    TableColumn<Message, String> getArchivedMembersColumn();
    TableColumn<Message, String> getArchivedSubjectColumn();
    VBox getArchivedThreadContainer();
    TableView<Message> getTrashInbox();
    TableColumn<Message, String> getTrashMembersColumn();
    TableColumn<Message, String> getTrashSubjectColumn();
    VBox getTrashThreadContainer();
    TabPane getTabPane();

    EventHandler<ActionEvent> getNewMessageButtonAction();
    void setNewMessageButtonAction(EventHandler<ActionEvent> eventHandler);
    EventHandler<ActionEvent> getReplyButtonAction();
    void setReplyButtonAction(EventHandler<ActionEvent> eventHandler);
    EventHandler<ActionEvent> getMoveToPrimaryFirstButtonAction();
    void setMoveToPrimaryFirstButtonAction(EventHandler<ActionEvent> eventHandler);
    EventHandler<ActionEvent> getMoveToPrimarySecondButtonAction();
    void setMoveToPrimarySecondButtonAction(EventHandler<ActionEvent> eventHandler);
    EventHandler<ActionEvent> getMoveToTrashButtonAction();
    void setMoveToTrashButtonAction(EventHandler<ActionEvent> eventHandler);
    EventHandler<ActionEvent> getMoveToArchivedButtonAction();
    void setMoveToArchivedButtonAction(EventHandler<ActionEvent> eventHandler);
}
