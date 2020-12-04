package model;

import javafx.beans.property.*;
import javafx.collections.ObservableList;
import java.time.LocalDateTime;
import java.util.List;

public class Message {
    private final StringProperty senderName;
    private final ListProperty<StringProperty> recipientNames;
    private final StringProperty content;
    private final BooleanProperty read;
    private final ObjectProperty<LocalDateTime> messageTime;

    public Message() {
        this(null, null, null, false, null);
    }

    public Message(String senderName, List<String> recipientNames, String content, boolean read, LocalDateTime messageTime) {
        this.senderName = new SimpleStringProperty(senderName);
        this.recipientNames = new SimpleListProperty<>();
        this.content = new SimpleStringProperty(content);
        this.read = new SimpleBooleanProperty(read);
        this.messageTime = new SimpleObjectProperty<>(messageTime);
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

    public ObservableList<StringProperty> getRecipientNames() {
        return recipientNames.get();
    }

    public ListProperty<StringProperty> recipientNamesProperty() {
        return recipientNames;
    }

    public void setRecipientNames(ObservableList<StringProperty> recipientNames) {
        this.recipientNames.set(recipientNames);
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
