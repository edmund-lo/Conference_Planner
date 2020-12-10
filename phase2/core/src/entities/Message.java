package entities;

import org.json.simple.*;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * Implementation of a message and all details about it.
 */
public class Message implements Serializable {
    private String senderName;
    private LocalDateTime messageTime;
    private String content;

    /**
     * Constructor for Entities.Message
     *
     * @param content The text of this message
     * @param senderName The id of the sender of this message
     * @param messageTime The time this message is sent and received
     */

    public Message( String senderName, LocalDateTime messageTime, String content){
        this.senderName = senderName;
        this.messageTime = messageTime;
        this.content = content;
    }

    /**
     * getter for the sender's username of this message
     *
     * @return The username of the sender
     */
    public String getSenderName() { return this.senderName; }

    /**
     * getter for the sent time of this message
     *
     * @return the messaging time in LocalDataTime format
     */
    public LocalDateTime getMessageTime() {
        return this.messageTime;
    }

    /**
     * getter for the content of this message
     *
     * @return the content of this message
     */
    public String getContent() {
        return this.content;
    }

//    /**
//     * the toString method for sent message
//     *
//     * @return a String representation of Entities.Message that contains the senderId, receiverIds, the content in this
//     * message and the time when it is sent and received
//     */
//    public String toStringSent(){
//        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
//        return "To: " + this.receiverId + "\n" +
//                "Time: "+(dtf.format(this.messageTime)) + "\n"
//                + "Message: " + this.content + "\n";
//    }
//
//    /**
//     * the toString method for received message
//     *
//     * @return a String representation of Entities.Message that contains the senderId, receiverIds, the content in this
//     * message and the time when it is sent and received
//     */
//
//    public String toStringReceived(){
//        String x;
//        if(this.read){ x = "Read."; }
//        else{ x = "Unread."; }
//        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
//        return "From: " + this.senderId + "\n" +
//                "Time: " + (dtf.format(this.messageTime)) + "\n" +
//                "Message: " + this.content + "\n";
//    }
//
//    /**
//     * the toString method for inbox message
//     *
//     * @return a String representation of Entities.Message that contains the senderId, receiverIds, the content in this
//     * message and the time when it is sent and received
//     */
//
//    public String toStringInbox(){
//        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
//        return "From: " + this.senderId + "\n" +
//                "Time: " + (dtf.format(this.messageTime)) + "\n" +
//                "Message: " + this.content + "\n";
//    }

    @SuppressWarnings("unchecked")
    public JSONObject convertToJSON() {
        JSONObject json = new JSONObject();
        JSONArray array = new JSONArray();
        JSONObject item = new JSONObject();

        json.put("type", "Message");

        item.put("sender", getSenderName());
        item.put("time", getMessageTime());
        item.put("content", getContent());

        array.add(item);

        json.put("data", array);

        return json;
    }
}
