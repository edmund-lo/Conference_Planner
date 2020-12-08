package adapter;

import model.Message;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import util.DateTimeUtil;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class MessageAdapter {
    private static final MessageAdapter Instance = new MessageAdapter();

    public static MessageAdapter getInstance() { return Instance; }

    private MessageAdapter() {}

    public List<Message> adaptData(JSONArray data) {
        List<Message> messageThread = new ArrayList<>();
        for (Object datum : data) {
            JSONObject jsonObject = (JSONObject) datum;
            messageThread.add(mapMessage(jsonObject));
        }
        return messageThread;
    }

    private Message mapMessage(JSONObject jsonObject) {
        String senderName = String.valueOf(jsonObject.get("sender"));
        String content = String.valueOf(jsonObject.get("content"));
        LocalDateTime messageTime = DateTimeUtil.getInstance().parse(String.valueOf(jsonObject.get("messageTime")));

        return new Message(senderName, content, messageTime);
    }
}
