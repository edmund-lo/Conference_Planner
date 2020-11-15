package Entities;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Implementation of a message and all details about it.
 */
public class Message implements Serializable {
    private String messageId;
    private String senderId;
    private String content;
    private LocalDateTime messageTime;
    private String receiverId;

    /**
     * Constructor for Entities.Message
     *
     * @param content The text of this message
     * @param messageId The randomly generated ID for this message
     * @param senderId The id of the sender of this message
     * @param receiverId The id for the receiver of this message
     * @param messageTime The time this message is sent and received
     */

    public Message(String content, String senderId, String messageId, String receiverId,
                   LocalDateTime messageTime){
        this.content = content;
        this.senderId = senderId;
        this.messageId = messageId;
        this.receiverId = receiverId;
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
     * @return String of the receiver ID
     */
    public String getReceiverId() {
        return this.receiverId;
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
        return this.messageTime;
    }

    /**
     * the toString method for Entities.Message
     *
     * @return a String representation of Entities.Message that contains the senderId, receiverIds, the content in this message
     * and the time when it is sent and received
     */
    public String toStringSent(){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        return "To: " + getReceiverId() + "\n" +
                "Time: "+(dtf.format(getMessageTime())) +
                "\n" + "Message: " + getContent() + "\n";
    }

    public String toStringReceived(){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        return "From: " + getSenderId() + "\n" +
                "Time: " + (dtf.format(getMessageTime())) +
                "\n" + "Message: " + getContent() + "\n";
    }
}
