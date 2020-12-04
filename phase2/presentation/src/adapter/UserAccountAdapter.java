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
        for (int i = 0; i < data.size(); i++) {
            JSONObject jsonObject = (JSONObject) data.get(i);
            userAccounts.add(mapUserAccount(jsonObject));
        }
        return userAccounts;
    }

    private UserAccount mapUserAccount(JSONObject jsonObject) {
        String username = String.valueOf(jsonObject.get("username"));
        String password = String.valueOf(jsonObject.get("username"));
        boolean locked = jsonObject.get("locked").equals("true");
        int accountType = parseInt(String.valueOf(jsonObject.get("accountType")));
        boolean setSecurity = jsonObject.get("username").equals("true");

        return new UserAccount(username, password, accountType, locked, setSecurity);
    }
}
