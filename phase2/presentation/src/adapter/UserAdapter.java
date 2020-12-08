package adapter;

import model.User;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import java.util.ArrayList;
import java.util.List;

public class UserAdapter {
    private static final UserAdapter Instance = new UserAdapter();

    public static UserAdapter getInstance() { return Instance; }

    private UserAdapter() {}

    public List<User> adaptData(JSONArray data) {
        List<User> users = new ArrayList<>();
        for (Object datum : data) {
            JSONObject jsonObject = (JSONObject) datum;
            users.add(mapUser(jsonObject));
        }
        return users;
    }

    private User mapUser(JSONObject jsonObject) {
        String username = String.valueOf(jsonObject.get("username"));
        String firstName = String.valueOf(jsonObject.get("firstName"));
        String lastName = String.valueOf(jsonObject.get("lastName"));
        String userType = String.valueOf(jsonObject.get("userType"));
        boolean vip = jsonObject.get("vip").equals("true");

        return new User(username, firstName, lastName, userType, vip);
    }
}
