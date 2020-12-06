package entities;

public class UserAccount {
    private String username;
    private String password;
    private Boolean locked;
    private String userType;
    private Boolean setSecurity;

    public UserAccount() {
        this(null, null, null, false, false);
    }

    public UserAccount(String username, String password, String userType, boolean locked, boolean setSecurity) {
        this.username = username;
        this.password = password;
        this.userType = userType;
        this.locked = locked;
        this.setSecurity = setSecurity;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public boolean isLocked() {
        return locked;
    }

    public void Lock() {
        this.locked = true;
    }

    public void Unlock() {
        this.locked = false;
    }

    public boolean isSetSecurity() {
        return setSecurity;
    }

    public void setSetSecurity(boolean setSecurity) {
        this.setSecurity = setSecurity;
    }

    public JSONArray getJSON() {
        JSONObject json = new JSONObject();
        JSONArray array = new JSONArray();

        json.put("Username", username);
        json.put("Account Type", userType);

        array.add(json);

        return array;
    }
}


