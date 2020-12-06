package admin;

import common.ILoggedInView;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import model.MessageThread;

import java.time.LocalDateTime;
import java.util.List;

public interface IDeleteMessagesView extends ILoggedInView {
    TableView<MessageThread> getMessageTable();
    TableColumn<MessageThread, String> getSenderNameColumn();
    TableColumn<MessageThread, String> getContentColumn();
    TableColumn<MessageThread, Boolean> getCheckedColumn();
    TableColumn<MessageThread, LocalDateTime> getMessageTimeColumn();
    TextField getRecipientsField();

    void setSummarySenderName(String senderName);
    void setSummaryRecipientNames(List<String> recipientNames);
    void setSummaryContent(String content);
    void setSummaryRead(boolean read);
    void setSummaryMessageTime(LocalDateTime messageTime);

    EventHandler<ActionEvent> getRemoveButtonAction();
    void setSendButtonAction(EventHandler<ActionEvent> messageHandler);
}
