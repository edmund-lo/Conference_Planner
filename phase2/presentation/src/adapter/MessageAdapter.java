package adapter;

import model.MessageThread;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import util.DateTimeUtil;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static java.lang.Integer.parseInt;

public class MessageAdapter {
    private static final MessageAdapter Instance = new MessageAdapter();

    public static MessageAdapter getInstance() { return Instance; }

    private MessageAdapter() {}

    public List<MessageThread> adaptData(JSONArray data) {
        List<MessageThread> messageThread = new ArrayList<>();
        for (Object datum : data) {
            JSONObject jsonObject = (JSONObject) datum;
            messageThread.add(mapMessage(jsonObject));
        }
        return messageThread;
    }

    private MessageThread mapMessage(JSONObject jsonObject) {
        String messageId = String.valueOf(jsonObject.get("messageId"));
        String senderName = String.valueOf(jsonObject.get("senderName"));
        List<String> recipientNames = new ArrayList<>();
        String subject = String.valueOf(jsonObject.get("subject"));
        String content = String.valueOf(jsonObject.get("content"));
        boolean read = jsonObject.get("read").equals("true");
        LocalDateTime messageTime = DateTimeUtil.getInstance().parse(String.valueOf(jsonObject.get("messageTime")));

        return new MessageThread(messageId, senderName, recipientNames, subject, content, read, messageTime);
    }
}
