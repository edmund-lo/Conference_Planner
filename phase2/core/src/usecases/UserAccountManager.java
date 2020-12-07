package usecases;

import entities.UserAccountEntity;
import java.util.*;
import java.io.Serializable;
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
    ArrayList<String> logInfo = new ArrayList<>();
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

//    public ArrayList<String[]> getAccountInfo() {
//        logInfo.
//        logInfo.add(this.);
//        logInfo
//    }
}
