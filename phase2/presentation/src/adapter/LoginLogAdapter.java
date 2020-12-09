package adapter;

import model.LoginLog;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import util.DateTimeUtil;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class LoginLogAdapter {
    private static final LoginLogAdapter Instance = new LoginLogAdapter();

    public static LoginLogAdapter getInstance() { return Instance; }

    private LoginLogAdapter() {}

    public List<LoginLog> adaptData(JSONArray data) {
        List<LoginLog> users = new ArrayList<>();
        for (Object datum : data) {
            JSONObject jsonObject = (JSONObject) datum;
            users.add(mapLoginLog(jsonObject));
        }
        return users;
    }

    private LoginLog mapLoginLog(JSONObject jsonObject) {
        String username = String.valueOf(jsonObject.get("username"));
        LocalDateTime loginTime = DateTimeUtil.getInstance().parse(String.valueOf(jsonObject.get("firstName")));
        boolean success = String.valueOf(jsonObject.get("condition")).equals("true");

        return new LoginLog(username, loginTime, success);
    }
}
