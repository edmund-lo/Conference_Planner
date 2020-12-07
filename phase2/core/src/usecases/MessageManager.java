package usecases;

import entities.Message;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.*;

import entities.MessageThread;
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

    /**
     * getter for the primaryInbox
     *
     * @return The primaryInbox variable
     */
    public HashMap<String, MessageThread> getAllMessageThreads() { return this.allMessageThreads; }

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

    public void readMessage(String messageThreadId){
        MessageThread messageThread = allMessageThreads.get(messageThreadId);
        messageThread.setRead(true);
    }

    public void unreadMessage(String messageThreadId){
        MessageThread messageThread = allMessageThreads.get(messageThreadId);
        messageThread.setRead(false);
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