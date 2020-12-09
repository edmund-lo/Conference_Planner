package adapter;

import model.Message;
import model.MessageThread;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import java.util.ArrayList;
import java.util.List;

public class MessageThreadAdapter {
    private static final MessageThreadAdapter Instance = new MessageThreadAdapter();

    public static MessageThreadAdapter getInstance() { return Instance; }

    private MessageThreadAdapter() {}

    public List<MessageThread> adaptData(JSONArray data) {
        List<MessageThread> messageThread = new ArrayList<>();
        for (Object datum : data) {
            JSONObject jsonObject = (JSONObject) datum;
            messageThread.add(mapMessageThread(jsonObject));
        }
        return messageThread;
    }

    private MessageThread mapMessageThread(JSONObject jsonObject) {
        String messageId = String.valueOf(jsonObject.get("messageId"));
        String senderName = String.valueOf(jsonObject.get("sender"));
        JSONArray recipientsArray = (JSONArray) jsonObject.get("receivers");
        List<String> recipientNames = new ArrayList<>();
        for (Object o : recipientsArray) recipientNames.add(String.valueOf(o));
        String subject = String.valueOf(jsonObject.get("subject"));
        boolean read = String.valueOf(jsonObject.get("read")).equals("true");
        List<Message> messageHistory = MessageAdapter.getInstance().adaptData((JSONArray) jsonObject.get("messages"));

        return new MessageThread(messageId, senderName, recipientNames, subject, read, messageHistory);
    }
}
