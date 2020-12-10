package usecases;

import entities.User;
import entities.UserAccountEntity;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Helper that manages all interaction with the Entities.UserAccountEntity classes and
 * ensures no rules are broken.
 * @author dylan
 * @version 1.0
 */
public class UserAccountManager implements Serializable {
    // key value pairing with username and all the properties of a UserAccountEntity
    // such as security questions, username, password, etc.
    private HashMap<String, UserAccountEntity> allUserAccounts;
    //ArrayList<String[]> logInfo = new ArrayList<>();
    //ArrayList<UserAccountEntity> logEntity = new ArrayList<>();
    /**
     * Constructor for UserAccountManager object. Initializes empty HashMap.
     *
     */
    public UserAccountManager(){
        this.allUserAccounts = new HashMap<>();

    }

    public void addAccount(String username, String password, String type, boolean security, String q1,
                           String q2, String ans1, String ans2) {

        UserAccountEntity account = new UserAccountEntity(username, password, type, false, security,
                q1, q2, ans1, ans2);

        allUserAccounts.put(username, account);
    }

    public void addAccount(String username, String password, String type) {
        UserAccountEntity account = new UserAccountEntity(username, password, type);
        allUserAccounts.put(username, account);
    }

//    public UserAccountEntity getUserAccount(String username) {
//        if (this.allUserAccountEntities.containsKey(username)) {
//            return this.allUserAccountEntities.get(username);
//        }
//        return null;
//    }

//    public ArrayList<String[]> getAccountInfo() {
//        //iterate through the keys of the UAM,
//        //for each key, add the username and password as the two index in the array of strings
//
//        String getUsername1 = "";
//        String getPassword1 = "";
//        for(String i : allUserAccountEntities.keySet()) {
//            logEntity.add(this.allUserAccountEntities.get(i));
//
//        }
//        for(int i = 0; i < logEntity.size(); i++) {
//            getUsername1= logEntity.get(i).getUsername();
//            getPassword1 = logEntity.get(i).getPassword();
//
//            logInfo.add(i,new String[]{getUsername1, getPassword1});
//        }
//        return logInfo;
//    }



//    public void updateAccount(String username, UserAccountEntity Account){
//        allUserAccountEntities.put(username, Account);
//    }

    public void setUsername(String username, String newUsername) {
        allUserAccounts.get(username).setUsername(newUsername);
    }

    public void setPassword(String username, String newPassword) {
        allUserAccounts.get(username).setPassword(newPassword);
    }

    public void setUserType(String username, String newUserType) {
        allUserAccounts.get(username).setUserType(newUserType);
    }

    public boolean isLocked(String username) {
        return allUserAccounts.get(username).isLocked();
    }

    public void lockAccount(String username) {
        allUserAccounts.get(username).lock();
    }

    public void unlockAccount(String username) {
        allUserAccounts.get(username).unlock();
    }

    public void setSecurityQuestions(String username, String Q1, String A1, String Q2, String A2) {
        allUserAccounts.get(username).setSecurityQuestions(Q1, A1, Q2, A2);
    }

    public void setSetSecurity(String username, boolean setSecurity) {
        allUserAccounts.get(username).setSetSecurity(setSecurity);
    }

    public String[] getSecurityAns(String username) {
        return allUserAccounts.get(username).getSecurityAns();
    }

    /**
     * Gets info on all users' username and password.
     *
     * @return An arraylist of Strings containing each user's username and password
     */
    public ArrayList<String[]> getAccountInfo() {
        ArrayList<String[]> accountInfo = new ArrayList<>();
        for (UserAccountEntity userAccount : allUserAccounts.values()) {
            String[] info = {userAccount.getUsername(), userAccount.getPassword()};
            accountInfo.add(info);
        }
        return accountInfo;
    }

    public JSONArray getAccountJSON(String username) {
        return allUserAccounts.get(username).convertToJSON();
    }

    public JSONObject getAllAccountsJSON() {
        JSONObject json = new JSONObject();
        JSONArray array = new JSONArray();
        JSONObject item = new JSONObject();

        for(String ID: allUserAccounts.keySet()) {
            item.put(ID, allUserAccounts.get(ID).convertToJSON());
        }
        array.add(item);

        json.put("User Accounts", array);

        return json;
    }
}
