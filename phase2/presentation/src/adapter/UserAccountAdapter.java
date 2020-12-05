package adapter;

import model.UserAccount;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import java.util.ArrayList;
import java.util.List;

import static java.lang.Integer.parseInt;

public class UserAccountAdapter {
    private static final UserAccountAdapter Instance = new UserAccountAdapter();

    public static UserAccountAdapter getInstance() { return Instance; }

    private UserAccountAdapter() {}

    public List<UserAccount> adaptData(JSONArray data) {
        List<UserAccount> userAccounts = new ArrayList<>();
        for (Object datum : data) {
            JSONObject jsonObject = (JSONObject) datum;
            userAccounts.add(mapUserAccount(jsonObject));
        }
        return userAccounts;
    }

    private UserAccount mapUserAccount(JSONObject jsonObject) {
        String username = String.valueOf(jsonObject.get("username"));
        String password = String.valueOf(jsonObject.get("password"));
        boolean locked = jsonObject.get("locked").equals("true");
        String accountType = String.valueOf(jsonObject.get("userType"));
        boolean setSecurity = jsonObject.get("setSecurity").equals("true");

        return new UserAccount(username, password, accountType, locked, setSecurity);
    }
}
