package entities;

import org.json.simple.*;
import java.io.Serializable;

public class Message implements Serializable {
    private final String sender;
    private final List[String] receivers;
    private final String messageThreadId;
    private String subject;
    private boolean read;
    private List[Message] messages;

    /**
     * Constructor for Entities.Message
     *
     * @param sender The username for the sender of these messages
     * @param receivers The usenames for the receiver of these messages
     * @param messageThreadId The id of this collection of messages
     * @param subject The subject line of this collection of messages
     * @param messages The collection of messages stored
     */

    public Message(String sender, List[String] receivers, String messageThreadId, String subject,
                   List[Message] messages){
        this.sender = sender;
        this.receivers = receivers;
        this.messageThreadId = messageThreadId;
        this.receiverId = receiverId;
        this.subject = subject;
        this.read = false;
        this.messages = messages;
    }

    /**
     * getter for username of the sender of these messages
     *
     * @return The sender's username
     */
    public String getSender() { return this.sender; }

    /**
     * getter for usernames of the receivers of these messages
     *
     * @return The a list of receivers' usernames
     */
    public String getReceivers() { return this.receivers; }

    /**
     * getter for messageThreadId of these messages
     *
     * @return The Id of this collection of messages
     */
    public String getMessageThreadId() { return this.messageThreadId; }

    /**
     * getter for subject line of these messages
     *
     * @return The subject/title of these messages
     */
    public String getSubject() { return this.subject; }

    /**
     * getter for these messages were read or not, return true if read, false otherwise
     *
     * @return The read bool variable
     */
    public boolean getRead() {
        return this.read;
    }

    /**
     * getter for all the messages stored in this thread
     *
     * @return List of Messages
     */
    public List[String] getMessages() {
        return this.messages;
    }

    public JSONObject convertToJSON() {
        JSONObject json = new JSONObject();
        JSONArray array = new JSONArray();
        JSONObject item = new JSONObject();

        json.put("type", "MessageThread");

        item.put("sender", getSender());
        item.put("receivers", getReceivers());
        item.put("messageThreadId", getMessageThreadId());
        item.put("subject", getSubject());
        item.put("read", getRead());
        item.put("messages", getMessages());

        array.add(item);

        json.put("data", array);

        return json;
    }
}