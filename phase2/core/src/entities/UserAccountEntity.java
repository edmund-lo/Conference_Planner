package entities;
import org.json.simple.*;

import java.util.ArrayList;

/**
 * An entity class for a user account.
 */
public class UserAccountEntity {
    private String username;
    private String password;
    private Boolean locked;
    private String userType;
    private Boolean setSecurity;

    private String SecurityQ1;
    private String SecurityQ2;

    private String SecurityA1;
    private String SecurityA2;

    public UserAccountEntity(String username, String password, String userType, boolean locked, boolean setSecurity,
                             String Q1, String Q2, String A1, String A2) {
        this.username = username;
        this.password = password;
        this.userType = userType;
        this.locked = locked;
        this.setSecurity = setSecurity;

        this.SecurityQ1 = Q1;
        this.SecurityQ2 = Q2;

        this.SecurityA1 = A1;
        this.SecurityA2 = A2;
    }

    public UserAccountEntity(String username, String password, String userType) {
        this.username = username;
        this.password = password;
        this.userType = userType;
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

    public void lock() {
        this.locked = true;
    }

    public void unlock() {
        this.locked = false;
    }

    public void setSecurityQuestions(String Q1, String A1, String Q2, String A2){
        this.SecurityQ1 = Q1;
        this.SecurityQ2 = Q2;

        this.SecurityA1 = A1;
        this.SecurityA2 = A2;
    }

    public String[] getSecurityAns(){
        return new String[]{this.SecurityA1, this.SecurityA2};
    }

    public boolean getSetSecurity() {
        return setSecurity;
    }

    public void setSetSecurity(boolean setSecurity) {
        this.setSecurity = setSecurity;
    }

    /**
     * @return A JSONArray that contains the JSON representation of this class
     */
    @SuppressWarnings("unchecked")
    public JSONArray convertToJSON() {
        JSONObject json = new JSONObject();
        JSONArray array = new JSONArray();

        json.put("Username", username);
        json.put("Account Type", userType);
        json.put("Locked", locked);
        json.put("Security", setSecurity);
        json.put("Security Q1", SecurityQ1);
        json.put("Security A1", SecurityA1);
        json.put("Security Q2", SecurityQ2);
        json.put("Security A2", SecurityA2);

        array.add(json);

        return array;
    }
}


