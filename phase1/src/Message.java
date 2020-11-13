import java.time.LocalDateTime;
import java.util.List;

/**
 * Implementation of a message and all details about it.
 */
public class Message<LocalDataTime> {
    private final String messageId;
    private final String senderId;
    private final String content;
    private final List<String> receiverIds;
    private final LocalDataTime messageTime;

    /**
     * Constructor for Message
     *
     * @param content The text of this message
     * @param messageId The randomly generated ID for this message
     * @param senderId The id of the sender of this message
     * @param receiverIds The list of ids for the receivers of this message
     * @param messageTime The time this message is sent and received
     */

    public Message(String content, String senderId, String messageId,
                   List<String> receiverIds, LocalDataTime messageTime){
        this.content = content;
        this.senderId = senderId;
        this.messageId = messageId;
        this.receiverIds = receiverIds;
        this.messageTime = messageTime;
    }

    /**
     * getter for ID of the message
     *
     * @return The ID of the message which is generated randomly in the manager
     */
    public String getMessageID() {
        return this.messageId;
    }

    /**
     * getter for ID of the sender
     *
     * @return The ID of the user who send this message
     */
    public String getSenderId() {
        return this.senderId;
    }

    /**
     * getter for the list of receivers
     *
     * @return An arraylist with IDs of all receivers of this message
     */
    public List<String> getReceiverIds() {
        return this.receiverIds;
    }

    /**
     * getter for the content of this message
     *
     * @return the content of this message
     */
    public String getContent() {
        return this.content;
    }

    /**
     * getter for the send and receive time of this message
     *
     * @return the send and receive time of this message
     */
    public LocalDateTime getMessageTime() {
        return (LocalDateTime) this.messageTime;
    }

    /**
     * adder for the list of receivers
     *
     * Send the message to a new receiver
     */
    public void addReceiver(final String receiverId) {
        this.receiverIds.add(receiverId);
    }

    /**
     * the toString method for Message
     *
     * @return a String representation of Message that contains the senderId, receiverIds, the content in this message
     * and the time when it is sent and received
     */
    public String toString(){
        return "Sender Name: "+ this.senderId +"\n" +
                "Time: "+String.valueOf(this.messageTime)+
                "\n" + "# of Attending Users: "+ this.receiverIds.size() +
                getContent();
    }
}
