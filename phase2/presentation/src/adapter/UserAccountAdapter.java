package adapter;

import model.UserAccount;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import java.util.ArrayList;
import java.util.List;

/**
 * Adapter class for mapping UserAccount entities to models
 */
public class UserAccountAdapter {
    private static final UserAccountAdapter Instance = new UserAccountAdapter();

    /**
     * Gets current instance of a UserAccountAdapter
     * @return An instance of a UserAccountAdapter object
     */
    public static UserAccountAdapter getInstance() { return Instance; }

    /**
     * Empty UserAccountAdapter constructor
     */
    private UserAccountAdapter() {}

    /**
     * Adapts data into a list of user accounts
     * @param data JSONArray of JSON formatted UserAccount entities
     * @return List of UserAccount models
     */
    public List<UserAccount> adaptData(JSONArray data) {
        List<UserAccount> userAccounts = new ArrayList<>();
        for (Object datum : data) {
            JSONObject jsonObject = (JSONObject) datum;
            userAccounts.add(mapUserAccount(jsonObject));
        }
        return userAccounts;
    }

    /**
     * Maps the jsonObject into a UserAccount model
     * @param jsonObject JSONObject of JSON formatted UserAccount entity
     * @return UserAccount model with mapped attributes
     */
    private UserAccount mapUserAccount(JSONObject jsonObject) {
        String username = String.valueOf(jsonObject.get("username"));
        String password = String.valueOf(jsonObject.get("password"));
        boolean locked = String.valueOf(jsonObject.get("locked")).equals("true");
        String accountType = String.valueOf(jsonObject.get("userType"));
        boolean setSecurity = String.valueOf(jsonObject.get("setSecurity")).equals("true");

        return new UserAccount(username, password, accountType, locked, setSecurity);
    }
}
