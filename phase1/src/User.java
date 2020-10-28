import java.util.ArrayList;
import java.util.List;

public abstract class User {
    private String userName;
    private String password;
    private int userID;
    private List<Integer> eventIDs;
    private List<Integer> messagableUserIDs;
    private List<Integer> messageIDs;

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public List<Integer> getEventIDs() {
        return eventIDs;
    }

    public List<Integer> getMessagableUserIDs() {
        return messagableUserIDs;
    }

    public List<Integer> getMessageIDs() {
        return messageIDs;
    }
}
