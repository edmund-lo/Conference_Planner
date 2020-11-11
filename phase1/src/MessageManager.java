import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;
import Message.java;


/**
 * Helper that manages all interaction with the Message classes and ensures no rules are broken.
 */
public class MessageManager{
    private HashMap<String, Message> allMessages;

    /**
     * Constructor for MessageManager
     *
     * @param allMessages A hashmap where the values are content of the messages and the keys are the IDs to each
     *                    respective Message, its sender and receivers
     */
    public messageManager(HashMap<String, Message> allMessages){
        this.allMessages = allMessages;
    }

    /**
     * A helper to check if the message is in allMessages by its ID
     *
     * @param messageID the ID of the event we are checking
     * @return True iff the event is in allMessages
     */
    private boolean messageExists(String messageID){
        return allMessages.containsKey(messageID);
    }

    /**
     * Creator for a new Message
     *
     * @param content The text of this message
     * @param messageID The randomly generated ID for this message
     * @param senderId The id of the sender of this message
     * @param receiverIds The list of ids for the receivers of this message
     * @param messageTime The time this message is sent and received
     */
    public void createNewMessage(String content, String senderId, List<String> receiverIds,
                                 LocalDataTime messageTime) {
        String messageId;
        do {
            messageId = String(UUID.randomUUID());
        } while(messageExists(messageId));
        Message newMessage = new Message(content, senderId, messageId, receiverIds, messageTime);
        allMessages.put(newMessage.getMessageID(), newMessage);
    }

    /**
     * For getting the list of all events with corresponding IDs
     *
     * @return allEvents
     */

    public HashMap<String, Event> getAllMessages() {
        return allMessages;
    }

}