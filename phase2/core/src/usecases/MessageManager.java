package usecases;

import entities.Message;
import entities.MessageThread;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;


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

    /**
     * getter for the primaryInbox
     *
     * @return The primaryInbox variable
     */
    public HashMap<String, MessageThread> getAllMessageThreads() { return this.allMessageThreads; }

    /**
     * getter for one messageThread given it's Id
     *
     * @return The messageThread variable
     */

    public MessageThread getMessageThread(String messageThreadId){
        return this.allMessageThreads.get(messageThreadId);
    }

    public void deleteMessage(String messageThreadId){
        this.allMessageThreads.remove(messageThreadId);
    }

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

    public String createMessage(String content, String sender, ArrayList receivers, String subject) {
        String messageThreadId;
        do {
            messageThreadId = UUID.randomUUID().toString();
        } while(this.messageThreadExists(messageThreadId));
        LocalDateTime messageTime = LocalDateTime.now();
        Message newMessage = new Message(sender, messageTime, content);
        MessageThread newMessageThread = new MessageThread(sender, receivers, messageThreadId, subject);
        newMessageThread.getMessages().add(newMessage);
        this.allMessageThreads.put(newMessageThread.getMessageThreadId(), newMessageThread);
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

    public boolean messageCheck(String content, String sender, ArrayList<String> receivers) {
        if (content.equals("")){return false;}
        for(String receiver : receivers) {
            if (receiver.equals(sender)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Change the status of the messageThread to true, given it's Id
     *
     * @param  messageThreadId id of the message user want to change.
     */

    public void readMessage(String messageThreadId){
        MessageThread messageThread = allMessageThreads.get(messageThreadId);
        messageThread.setRead(true);
    }

    /**
     * Change the status of the messageThread to false, given it's Id
     *
     * @param  messageThreadId id of the message user want to change.
     */

    public void unreadMessage(String messageThreadId){
        MessageThread messageThread = allMessageThreads.get(messageThreadId);
        messageThread.setRead(false);
    }

    /**
     * Check the current status of the messageThread is read or unread, given it's Id
     *
     * @param  messageThreadId id of the message user want to check.
     *
     * @return a boolean tells the status, return true iff the message has read, false otherwise
     */

    public boolean checkMessageStatus(String messageThreadId){
        return allMessageThreads.get(messageThreadId).getRead();
    }

    public JSONObject getOneMessageThreadToJson(String messageThreadId){
        JSONObject json = new JSONObject();
        JSONArray array = new JSONArray();
        JSONObject item = new JSONObject();

        item.put(messageThreadId, getAllMessageThreads().get(messageThreadId).convertToJSON());

        array.add(item);

        json.put("MessageThread", array);

        return json;
    }

    public JSONObject getAllMessageThreadToJson(){
        JSONObject json = new JSONObject();
        JSONArray array = new JSONArray();
        JSONObject item = new JSONObject();

        for(String ID: getAllMessageThreads().keySet())
            item.put(ID, getAllMessageThreads().get(ID).convertToJSON());

        array.add(item);

        json.put("MessageThreads", array);

        return json;
    }
}