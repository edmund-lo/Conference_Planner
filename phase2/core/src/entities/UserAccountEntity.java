package entities;
import org.json.simple.*;

public class UserAccountEntity {
    private String username;
    private String password;
    private Boolean locked;
    private String userType;
    private Boolean setSecurity;

    private String SecurityQ1;
    private String SecurityQ2;
    private String SecurityQ3;

    private String SecurityA1;
    private String SecurityA2;
    private String SecurityA3;

    public UserAccountEntity() {
        this(null, null, null, false, false, null, null, null, null, null, null);
    }

    public UserAccountEntity(String username, String password, String userType, boolean locked, boolean setSecurity,
                             String Q1, String Q2, String Q3, String A1, String A2, String A3) {
        this.username = username;
        this.password = password;
        this.userType = userType;
        this.locked = locked;
        this.setSecurity = setSecurity;

        this.SecurityQ1 = Q1;
        this.SecurityQ2 = Q2;
        this.SecurityQ3 = Q3;

        this.SecurityA1 = A1;
        this.SecurityA2 = A2;
        this.SecurityA3 = A3;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setSecurityQuestions(String Q1, String A1, String Q2, String A2, String Q3, String A3){
        this.SecurityQ1 = Q1;
        this.SecurityQ2 = Q2;
        this.SecurityQ3 = Q3;

        this.SecurityA1 = A1;
        this.SecurityA2 = A2;
        this.SecurityA3 = A3;
    }

    public String getSecurityAns(int i){
        if (i == 1)
            return this.SecurityA1;
        if (i == 2)
            return this.SecurityA2;
        if (i == 3)
            return this.SecurityA3;
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

    /**
     * @return A JSONArray that contains the JSON representation of this class
     */
    @SuppressWarnings("unchecked")
    public JSONArray getJSON() {
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
        json.put("Security Q3", SecurityQ3);
        json.put("Security A3", SecurityA3);

        array.add(json);

        return array;
    }
}


