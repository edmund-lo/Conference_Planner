package usecases;

import entities.UserAccountEntity;

import java.lang.reflect.Array;
import java.util.*;
import java.io.Serializable;
import java.util.Arrays;
import java.util.ArrayList;
import org.json.simple.*;

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
}
