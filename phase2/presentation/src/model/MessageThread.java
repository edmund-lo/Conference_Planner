package model;

import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.util.List;

/**
 * Model class for MessageThread object
 */
public class MessageThread {
    private final StringProperty messageThreadId;
    private final StringProperty senderName;
    private final ListProperty<String> recipientNames;
    private final StringProperty subject;
    private final BooleanProperty read;
    private final ListProperty<Message> messageHistory;

    /**
     * Initialises a MessageThread object with default attributes
     */
    public MessageThread() {
        this(null, null, null, null, false, null);
    }

    /**
     * Initialises a MessageThread object with given parameters as attributes
     * @param messageThreadId String object representing message thread's unique ID
     * @param senderName String object representing message thread's original sender's username
     * @param recipientNames List of String objects representing message thread's recipients' usernames
     * @param subject String object representing message thread's subject topic
     * @param read boolean representing whether message thread has been read or not
     * @param messageHistory List of Message objects representing the message thread's conversation history
     */
    public MessageThread(String messageThreadId, String senderName, List<String> recipientNames, String subject,
                         boolean read, List<Message> messageHistory) {
        this.messageThreadId = new SimpleStringProperty(messageThreadId);
        this.senderName = new SimpleStringProperty(senderName);
        this.recipientNames = new SimpleListProperty<>(FXCollections.observableArrayList(recipientNames));
        this.subject = new SimpleStringProperty(subject);
        this.read = new SimpleBooleanProperty(read);
        this.messageHistory = new SimpleListProperty<>(FXCollections.observableArrayList(messageHistory));
    }

    //region Getters and Setters
    public String getMessageThreadId() {
        return messageThreadId.get();
    }

    public StringProperty messageThreadIdProperty() {
        return messageThreadId;
    }

    public void setMessageThreadId(String messageThreadId) {
        this.messageThreadId.set(messageThreadId);
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

    public boolean isRead() {
        return read.get();
    }

    public BooleanProperty readProperty() {
        return read;
    }

    public void setRead(boolean read) {
        this.read.set(read);
    }

    public ObservableList<Message> getMessageHistory() {
        return messageHistory.get();
    }

    public ListProperty<Message> messageHistoryProperty() {
        return messageHistory;
    }

    public void setMessageHistory(ObservableList<Message> messageHistory) {
        this.messageHistory.set(messageHistory);
    }
    //endregion
}
