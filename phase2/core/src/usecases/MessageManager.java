package usecases;

import entities.Message;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.*;
import org.json.simple.*;


/**
 * Helper that manages all interaction with the Entities.Message classes and ensures no rules are broken.
 */
public class MessageManager implements Serializable {
    private HashMap<String, MessageThread> allMessageThreads;
    //private List<String> allContents;

    /**
     * Constructor for UseCases.MessageManager
     *
     */
    public MessageManager(){
        this.allMessageThreads = new HashMap<>();
        //this.allContents = new ArrayList<>();
    }

    /**
     * A helper to check if the messageThread is in allMessageThreads by its Id
     *
     * @param messageThreadId the Id of the messageThread that we are checking
     * @return True iff the messageThread is in allMessageThreads
     */
    private boolean messageThreadExists(String messageThreadId){
        return this.allMessageThreads.containsKey(messageThreadId);
    }

//      Save for phase 2
    /*/**
     * A string representation of all the message contents
     *
     * @return allContents
     */

    /*public List<String> getMessageContents(){
        for (Message message : this.allMessages.values()){
            this.allContents.add(message.toString());
        }
        return this.allContents;
    }*/

    /*/**;
     * Add new content to all contents
     *//*

    public void addMessageContent(HashMap<String, Message> message){
        this.allContents.add(message.toString());
    }*/

    /**
     * Creator for a new Entities.Message and the MessageThread it belongs to
     *
     * Precondition: the sender and receivers exist in users
     *
     * @param content The text of this message
     * @param sender The username of the sender for this message
     * @param receivers The list of usernames for the receivers of this message
     * @param subject The subject of MessageThread this message belongs to
     *
     * return the created messageThreadId
     */

    public String createMessage(String content, String sender, String receivers, String subject) {
        String messageThreadId;
        do {
            messageThreadId = UUID.randomUUID().toString();
        } while(this.messageExists(messageId));
        LocalDateTime messageTime = LocalDateTime.now();
        Message newMessage = new Message(content, sender, messageTime);
        List[Message] messages = [newMessage];
        MessageThread newMessageThread = new MessageThread(sender, receivers, messageThreadId, subject, messages)
        this.allMessages.put(newMessage.getMessageID(), newMessage);
        //this.allContents.add(newMessage.toString());
        return messageThreadId;
    }

    /**
     * Checks if a message fulfils all the requirements to be sent.
     *
     * Precondition: the sender and receivers exist in users
     *
     * @param content The text of this message
     * @param sender The username of the sender for this message
     * @param receivers The username of the receiver for this message
     *
     * @return boolean value that signifies the result of the check.
     */

    public boolean messageCheck(String content, String sender, String receviers) {
        return (!receiver.equals(sender) && !content.equals(""));
    }

    /**
     * Make the sent message to String
     *
     * @param messageID The id of message want to construct to string
     *
     * Return the String of that message
     */

    public String getSentMessageToString(String messageID) {
        return this.allMessageThreads.getMessages().get(messageID).toStringSent();
    }

    /**
     * Make the received message to String
     *
     * @param messageID The id of message want to construct to string
     *
     * Return the String of that message
     */

    public String getReceivedMessageToString(String messageID) {
        return this.allMessageThreads.getMessages().get(messageID).toStringReceived();
    }

    /**
     * Make the inbox message to String
     *
     * @param messageID The id of message want to construct to string
     *
     * Return the String of that message
     */

    public String getInboxMessageToString(String messageID) {
        return this.allMessageThreads.getMessages().get(messageID).toStringInbox();
    }

    public JSONObject getAllMessageThreadsToJson(){
        JSONObject json = new JSONObject();
        JSONArray array = new JSONArray();
        JSONObject item = new JSONObject();

        for(String ID: allMessageThreads.keySet())
            item.put(ID, allMessageThreads.get(ID).convertToJSON());

        array.add(item);

        json.put("MessageThreads", array);

        return json;
    }
}