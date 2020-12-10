package entities;

import org.json.simple.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class MessageThread implements Serializable {
    private String sender;
    private ArrayList receivers;
    private String messageThreadId;
    private String subject;
    private ArrayList messages;

    /**
     * Constructor for Entities.MessageThread
     *
     * @param sender The username for the sender of these messages
     * @param messageThreadId The id of this collection of messages
     * @param subject The subject line of this collection of messages
     */

    public MessageThread(String sender, ArrayList receivers, String messageThreadId, String subject){
        this.sender = sender;
        this.receivers = receivers;
        this.messageThreadId = messageThreadId;
        this.subject = subject;
        this.messages = new ArrayList<>();
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
    public ArrayList getReceivers() { return this.receivers; }

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
     * getter for all the messages stored in this thread
     *
     * @return List of Messages
     */
    public ArrayList getMessages() {
        return this.messages;
    }

    public void addMessages(Message message) {
        this.messages.add(message);
    }

    /**
     * For converting this class to a JSON format
     * @return a JSONObject that describes this entity
     */
    @SuppressWarnings("unchecked")
    public JSONObject convertToJSON() {
        JSONObject json = new JSONObject();
        JSONArray array = new JSONArray();
        JSONObject item = new JSONObject();

        json.put("type", "MessageThread");

        item.put("sender", getSender());
        item.put("receivers", getReceivers());
        item.put("subject", getSubject());
        item.put("messageThreadId", getMessageThreadId());
        item.put("messages", getMessages());

        array.add(item);

        json.put("data", array);

        return json;
    }
}