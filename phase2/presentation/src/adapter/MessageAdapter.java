package adapter;

import model.Message;
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

    public List<Message> adaptData(JSONArray data) {
        List<Message> message = new ArrayList<>();
        for (Object datum : data) {
            JSONObject jsonObject = (JSONObject) datum;
            message.add(mapMessage(jsonObject));
        }
        return message;
    }

    private Message mapMessage(JSONObject jsonObject) {
        String messageId = String.valueOf(jsonObject.get("messageId"));
        String senderName = String.valueOf(jsonObject.get("senderName"));
        List<String> recipientNames = new ArrayList<>();
        String subject = String.valueOf(jsonObject.get("subject"));
        String content = String.valueOf(jsonObject.get("content"));
        boolean read = jsonObject.get("read").equals("true");
        LocalDateTime messageTime = DateTimeUtil.getInstance().parse(String.valueOf(jsonObject.get("messageTime")));

        return new Message(messageId, senderName, recipientNames, subject, content, read, messageTime);
    }
}
