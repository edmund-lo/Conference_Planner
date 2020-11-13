import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;


/**
 * Helper that manages all interaction with the Message classes and ensures no rules are broken.
 */
public class MessageManager{
    private HashMap<String, Message> allMessages;
    private List<String> allContents;

    /**
     * Constructor for MessageManager
     *
     * @param allMessages A hashmap where the values are content of the messages and the keys are the IDs to each
     *                    respective Message, its sender and receivers
     */
    public MessageManager(HashMap<String, Message> allMessages){
        this.allMessages = allMessages;
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
     * For getting the list of all events with corresponding IDs
     *
     * @return allEvents
     */

    public HashMap<String,Message> getAllMessages() {
        return this.allMessages;
    }

    /**
     * A string representation of all the message contents
     *
     * @return allContents
     */

    public List<String> messageContents(){
        for (Message message : allMessages.values()){
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
     * Creator for a new Message
     *
     * Precondition: the senderID and receiverIds exist in users
     *
     * @param content The text of this message
     * @param senderId The id of the sender of this message
     * @param receiverIds The list of ids for the receivers of this message
     * @param messageTime The time this message is sent and received
     *
     * return the created message
     */

    public String createMessage(String content, String senderId, List<String> receiverIds,
                                 LocalDateTime messageTime) {
        String messageId;
        do {
            messageId = UUID.randomUUID().toString();
        } while(messageExists(messageId));
        Message newMessage = new Message(content, senderId, messageId, receiverIds, messageTime);
        allMessages.put(newMessage.getMessageID(), newMessage);
        contents.append(newMessage.toString());
        return messageId;
    }

    /**
     * Sender for a new Message
     *
     * @param senderId The id of the sender of this message
     * @param messageId The ID of the message that is going to send
     *
     * Return True iff the message has been sent successfully, False otherwise
     */

    public boolean sendMessage(String senderId, String messageId){
        if(userExists(senderId)){
            User user = allUsers.get(senderId);
            user.addMessageID(messageId);
            return True;
        }else{return False;}
    }

    /**
     * Receiver for a new Message
     *
     * @param receiverIds The list of ids for the receivers of this message
     * @param messageId The ID of the message been sent
     *
     * Return True iff all receivers has received the message, False otherwise
     */

    public boolean receiveMessage(List<String> receiverIds, String messageId){
        for(String receiver : receiverIds){
            if (userExists(receiver)) {
                User user = allUsers.get(receiver);
                user.addMessageID(messageId);
            } else return False;
        }
        return True;
    }

    /**
     * Receiver for a new Message
     *
     * @param eventId ID of the event that the speaker's talk
     * @param speakerId The id of the speaker of this event
     * @param messageId The ID of the message the speaker what to send
     *
     * Return True iff the speaker has sent the message and all attendees has received the message, False otherwise
     */

    public boolean sendToAllAttendees(String eventId, String speakerId, String messageId){
        Speaker speaker = allUsers.get(speakerId);
        Event event = allEvents.get(eventId);
        boolean send = sendMessage(speaker.getUserName(), messageId);
        if(send == False){return False;}
        for(String receiver : event.getAttendingUsers()){
            boolean receive = receiveMessage(receiver.getUserName(), messageId);
            if(receive == False){return False;}
        }
        return True;
    }
}