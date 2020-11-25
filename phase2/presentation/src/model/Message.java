package model;

import javafx.beans.property.*;

import java.time.LocalDateTime;

public class Message {
    private final StringProperty senderName;
    private final StringProperty recipientName;
    private final StringProperty content;
    private final BooleanProperty read;
    private final ObjectProperty<LocalDateTime> messageTime;

    public Message() {
        this(null, null, null, false, null);
    }

    public Message(String senderName, String recipientName, String content, boolean read, LocalDateTime messageTime) {
        this.senderName = new SimpleStringProperty(senderName);
        this.recipientName = new SimpleStringProperty(recipientName);
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

    public String getRecipientName() {
        return recipientName.get();
    }

    public StringProperty recipientNameProperty() {
        return recipientName;
    }

    public void setRecipientName(String recipientName) {
        this.recipientName.set(recipientName);
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
