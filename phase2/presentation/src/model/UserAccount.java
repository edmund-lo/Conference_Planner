package model;

import javafx.beans.property.*;

/**
 * Model class for UserAccount object
 */
public class UserAccount {
    private final StringProperty username;
    private final StringProperty password;
    private final BooleanProperty locked;
    private final StringProperty userType;
    private final BooleanProperty setSecurity;

    /**
     * Initialises a UserAccount object with default attributes
     */
    public UserAccount() {
        this(null, null, null, false, false);
    }

    /**
     * Initialises a UserAccount object with given parameters as attributes
     * @param username String object representing user's username
     * @param password String object representing user's password
     * @param userType String object representing user's user type
     * @param locked boolean representing whether user's account is locked or not
     * @param setSecurity boolean representing whether user has set security questions or not
     */
    public UserAccount(String username, String password, String userType, boolean locked, boolean setSecurity) {
        this.username = new SimpleStringProperty(username);
        this.password = new SimpleStringProperty(password);
        this.userType = new SimpleStringProperty(userType);
        this.locked = new SimpleBooleanProperty(locked);
        this.setSecurity = new SimpleBooleanProperty(setSecurity);
    }

    //region Getters and Setters
    public String getUsername() {
        return username.get();
    }

    public void setUsername(String username) {
        this.username.set(username);
    }

    public String getPassword() {
        return password.get();
    }

    public void setPassword(String password) {
        this.password.set(password);
    }

    public String getUserType() {
        return userType.get();
    }

    public void setUserType(String userType) {
        this.userType.set(userType);
    }

    public boolean isLocked() {
        return locked.get();
    }

    public BooleanProperty lockedProperty() {
        return locked;
    }

    public void setLocked(boolean locked) {
        this.locked.set(locked);
    }

    public boolean isSetSecurity() {
        return setSecurity.get();
    }
    //endregion
}
