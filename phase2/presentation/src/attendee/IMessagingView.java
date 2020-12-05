package attendee;

import common.ILoggedInView;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import model.Message;

import java.time.LocalDateTime;
import java.util.List;

public interface IMessagingView extends ILoggedInView {
    TableView<Message> getMessageTable();
    TableColumn<Message, String> getSenderNameColumn();
    TableColumn<Message, String> getContentColumn();
    TableColumn<Message, Boolean> getCheckedColumn();
    TableColumn<Message, LocalDateTime> getMessageTimeColumn();
    TextField getRecipientsField();

    void setSummarySenderName(String senderName);
    void setSummaryRecipientNames(List<String> recipientNames);
    void setSummaryContent(String content);
    void setSummaryRead(boolean read);
    void setSummaryMessageTime(LocalDateTime messageTime);

    EventHandler<ActionEvent> getSendButtonAction();
    void setSendButtonAction(EventHandler<ActionEvent> eventHandler);
}
