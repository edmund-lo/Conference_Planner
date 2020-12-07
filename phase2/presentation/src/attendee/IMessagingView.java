package attendee;

import common.ILoggedInView;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.*;
import model.MessageThread;
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
    void setResultText(String resultText);

    TableView<MessageThread> getPrimaryInbox();
    TableColumn<MessageThread, String> getPrimaryMembersColumn();
    TableColumn<MessageThread, String> getPrimarySubjectColumn();
    TableColumn<MessageThread, Boolean> getPrimaryUnreadColumn();
    ScrollPane getPrimaryThreadContainer();
    TableView<MessageThread> getArchivedInbox();
    TableColumn<MessageThread, String> getArchivedMembersColumn();
    TableColumn<MessageThread, String> getArchivedSubjectColumn();
    TableColumn<MessageThread, Boolean> getArchivedUnreadColumn();
    ScrollPane getArchivedThreadContainer();
    TableView<MessageThread> getTrashInbox();
    TableColumn<MessageThread, String> getTrashMembersColumn();
    TableColumn<MessageThread, String> getTrashSubjectColumn();
    TableColumn<MessageThread, Boolean> getTrashUnreadColumn();
    ScrollPane getTrashThreadContainer();
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
    EventHandler<ActionEvent> getUnreadPrimaryButtonAction();
    void setUnreadPrimaryButtonAction(EventHandler<ActionEvent> eventHandler);
    EventHandler<ActionEvent> getUnreadArchivedButtonAction();
    void setUnreadArchivedButtonAction(EventHandler<ActionEvent> eventHandler);
    EventHandler<ActionEvent> getUnreadTrashButtonAction();
    void setUnreadTrashButtonAction(EventHandler<ActionEvent> eventHandler);
}
