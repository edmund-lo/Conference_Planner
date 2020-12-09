package model;

import javafx.beans.property.*;

public class UserAccount {
    private final StringProperty username;
    private final StringProperty password;
    private final BooleanProperty locked;
    private final StringProperty userType;
    private final BooleanProperty setSecurity;

    public UserAccount() {
        this(null, null, null, false, false);
    }

    public UserAccount(String username, String password, String userType, boolean locked, boolean setSecurity) {
        this.username = new SimpleStringProperty(username);
        this.password = new SimpleStringProperty(password);
        this.userType = new SimpleStringProperty(userType);
        this.locked = new SimpleBooleanProperty(locked);
        this.setSecurity = new SimpleBooleanProperty(setSecurity);
    }

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
}
