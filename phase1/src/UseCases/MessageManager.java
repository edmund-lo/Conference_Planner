package UseCases;

import Entities.Message;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.*;


/**
 * Helper that manages all interaction with the Entities.Message classes and ensures no rules are broken.
 */
public class MessageManager implements Serializable {
    private HashMap<String, Message> allMessages;
    private List<String> allContents;

    /**
     * Constructor for UseCases.MessageManager
     *
     */
    public MessageManager(){
        this.allMessages = new HashMap<String, Message>();
        this.allContents = new ArrayList<String>();
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

    /**
     * A string representation of all the message contents
     *
     * @return allContents
     */

    public List<String> messageContents(){
        for (Message message : this.allMessages.values()){
            this.allContents.add(message.toString());
        }
        return this.allContents;
    }

    /**;
     * Add new content to all contents
     */

    public void addMessageContent(HashMap<String, Message> message){
        this.allContents.add(message.toString());
    }

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
        } while(messageExists(messageId));
        LocalDateTime messageTime = LocalDateTime.now();
        Message newMessage = new Message(content, senderId, messageId, receiverId, messageTime);
        this.allMessages.put(newMessage.getMessageID(), newMessage);
        this.allContents.add(newMessage.toString());
        return messageId;
    }

    /**
     * Sender for a new Entities.Message
     *
     * @param content The text of this message
     * @param senderId The id of the sender of this message
     * @param receiverId The Id of the receiver
     *
     * Return the messageId that is created
     */

    public boolean sendMessage(String receiverId, String senderId, String content) {
        if (receiverId != senderId && content != "") {
            createMessage(receiverId, senderId, content);
            return true;
        }
        return false;
    }

    public String getMessageToString(String messageID) {
        return allMessages.get(messageID).toString();
    }
}