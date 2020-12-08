package model;

import javafx.beans.property.*;

import java.time.LocalDateTime;

public class Message {
    private final StringProperty senderName;
    private final StringProperty content;
    private final ObjectProperty<LocalDateTime> messageTime;

    public Message() {
        this(null, null, null);
    }

    public Message(String senderName, String content, LocalDateTime messageTime) {
        this.senderName = new SimpleStringProperty(senderName);
        this.content = new SimpleStringProperty(content);
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

    public String getContent() {
        return content.get();
    }

    public StringProperty contentProperty() {
        return content;
    }

    public void setContent(String content) {
        this.content.set(content);
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
