package usecases;

import entities.UserAccountEntity;


import java.util.*;
import java.io.Serializable;
import java.util.ArrayList;

/**
 * Helper that manages all interaction with the Entities.UserAccountEntity classes and
 * ensures no rules are broken.
 * @author dylan
 * @version 1.0
 */
public class UserAccountManager implements Serializable {
    // key value pairing with username and all the properties of a UserAccountEntity
    // such as security questions, username, password, etc.
    private HashMap<String, UserAccountEntity> allUserAccountEntities;
    ArrayList<String[]> logInfo = new ArrayList<>();
    ArrayList<UserAccountEntity> logEntity = new ArrayList<>();
    /**
     * Constructor for UserAccountManager object. Initializes empty HashMap.
     *
     */
    public UserAccountManager(){
        this.allUserAccountEntities = new HashMap<>();

    }

    public UserAccountEntity getUserAccount(String username) {
        if (this.allUserAccountEntities.containsKey(username)) {
            return this.allUserAccountEntities.get(username);
        }
        return null;
    }

    public ArrayList<String[]> getAccountInfo() {
        //iterate through the keys of the UAM,
        //for each key, add the username and password as the two index in the array of strings
//        String[] temp;
//        String[] temp1;
        String getUsername1 = "";
        String getPassword1 = "";
        for(String i : allUserAccountEntities.keySet()) {
            logEntity.add(this.allUserAccountEntities.get(i));

        }
        for(int i = 0; i < logEntity.size(); i++) {
            getUsername1= logEntity.get(i).getUsername();
            getPassword1 = logEntity.get(i).getPassword();

            logInfo.add(i,new String[]{getUsername1, getPassword1});
        }
        return logInfo;
    }

    public void addAccount(String username, String password, String type, boolean security, String q1,
                           String q2, String ans1, String ans2) {

        UserAccountEntity Account = new UserAccountEntity(username, password, type, false, security,
                q1, q2, ans1, ans2);

        allUserAccountEntities.put(username, Account);
    }

    public void addAccount(String username, String password, String type) {
        UserAccountEntity Account = new UserAccountEntity(username, password, type);
        allUserAccountEntities.put(username, Account);
    }

    public void updateAccount(String username, UserAccountEntity Account){
        allUserAccountEntities.put(username, Account);
    }

    public void unlockAccount(String username) {
        allUserAccountEntities.get(username).unlock();
    }
}
