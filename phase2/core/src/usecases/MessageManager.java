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
    private HashMap<String, Message> allMessages;
    //private List<String> allContents;

    /**
     * Constructor for UseCases.MessageManager
     *
     */
    public MessageManager(){
        this.allMessages = new HashMap<>();
        //this.allContents = new ArrayList<>();
    }

    /**
     * A helper to check if the message is in allMessages by its ID
     *
     * @param messageID the ID of the event we are checking
     * @return True iff the event is in allMessages
     */
    private boolean messageExists(String messageID){
        return this.allMessages.containsKey(messageID);
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
     * Creator for a new Entities.Message
     *
     * Precondition: the senderID and receiverIds exist in users
     *
     * @param content The text of this message
     * @param senderId The id of the sender of this message
     * @param receiverId The list of ids for the receivers of this message
     *
     * return the created message
     */

    public String createMessage(String receiverId, String senderId, String content) {
        String messageId;
        do {
            messageId = UUID.randomUUID().toString();
        } while(this.messageExists(messageId));
        LocalDateTime messageTime = LocalDateTime.now();
        Message newMessage = new Message(content, senderId, messageId, receiverId, messageTime);
        this.allMessages.put(newMessage.getMessageID(), newMessage);
        //this.allContents.add(newMessage.toString());
        return messageId;
    }

    /**
     * Checks if a message fulfils all the requirements to be sent.
     *
     * @param content The text of this message
     * @param senderId The id of the sender of this message
     * @param receiverId The Id of the receiver
     *
     * @return boolean value that signifies the result of the check.
     */

    public boolean messageCheck(String receiverId, String senderId, String content) {
        return (!receiverId.equals(senderId) && !content.equals(""));
    }

    /**
     * Make the sent message to String
     *
     * @param messageID The id of message want to construct to string
     *
     * Return the String of that message
     */

    public String getSentMessageToString(String messageID) {
        return this.allMessages.get(messageID).toStringSent();
    }

    /**
     * Make the received message to String
     *
     * @param messageID The id of message want to construct to string
     *
     * Return the String of that message
     */

    public String getReceivedMessageToString(String messageID) {
        return this.allMessages.get(messageID).toStringReceived();
    }

    public JSONObject getAllMessagesJson(){
        JSONObject json = new JSONObject();
        JSONArray array = new JSONArray();
        JSONObject item = new JSONObject();

        for(String ID: allMessages.keys())
            item.put(ID, allMessages.get(ID).convertToJSON());

        array.add(item);

        json.put("Messages", array);

        return json;
    }
}