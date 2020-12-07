package model;

import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.time.LocalDateTime;
import java.util.List;

public class MessageThread {
    private final StringProperty messageId;
    private final StringProperty senderName;
    private final ListProperty<String> recipientNames;
    private final StringProperty subject;
    private final StringProperty content;
    private final BooleanProperty read;
    private final ObjectProperty<LocalDateTime> messageTime;

    public MessageThread() {
        this(null, null, null, null, null, false, null);
    }

    public MessageThread(String messageId, String senderName, List<String> recipientNames, String subject, String content, boolean read,
                         LocalDateTime messageTime) {
        this.messageId = new SimpleStringProperty(messageId);
        this.senderName = new SimpleStringProperty(senderName);
        this.recipientNames = new SimpleListProperty<>(FXCollections.observableArrayList(recipientNames));
        this.subject = new SimpleStringProperty(subject);
        this.content = new SimpleStringProperty(content);
        this.read = new SimpleBooleanProperty(read);
        this.messageTime = new SimpleObjectProperty<>(messageTime);
    }

    public String getMessageId() {
        return messageId.get();
    }

    public StringProperty messageIdProperty() {
        return messageId;
    }

    public void setMessageId(String messageId) {
        this.messageId.set(messageId);
    }

    public String getSenderName() {
        return senderName.get();
    }

    public StringProperty senderNameProperty() {
        return senderName;
    }

    public void setSenderName(String senderName) {
        this.senderName.set(senderName);
    }

    public ObservableList<String> getRecipientNames() {
        return recipientNames.get();
    }

    public ListProperty<String> recipientNamesProperty() {
        return recipientNames;
    }

    public void setRecipientNames(ObservableList<String> recipientNames) {
        this.recipientNames.set(recipientNames);
    }

    public String getSubject() {
        return subject.get();
    }

    public StringProperty subjectProperty() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject.set(subject);
    }

    public String getContent() {
        return content.get();
    }

    public StringProperty contentProperty() {
        return content;
    }

    public void setContent(String content) {
        this.content.set(content);
    }

    public boolean isRead() {
        return read.get();
    }

    public BooleanProperty readProperty() {
        return read;
    }

    public void setRead(boolean read) {
        this.read.set(read);
    }

    public LocalDateTime getMessageTime() {
        return messageTime.get();
    }

    public ObjectProperty<LocalDateTime> messageTimeProperty() {
        return messageTime;
    }

    public void setMessageTime(LocalDateTime messageTime) {
        this.messageTime.set(messageTime);
    }
}
