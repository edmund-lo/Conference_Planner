package model;

import javafx.beans.property.*;

public class UserAccount {
    private final StringProperty username;
    private final StringProperty password;
    private final BooleanProperty locked;
    private final IntegerProperty accountType;

    public UserAccount() {
        this(null, null, false, 0);
    }

    public UserAccount(String username, String password, boolean locked, int accountType) {
        this.username = new SimpleStringProperty(username);
        this.password = new SimpleStringProperty(password);
        this.locked = new SimpleBooleanProperty(locked);
        this.accountType = new SimpleIntegerProperty(accountType);
    }

    public String getUsername() {
        return username.get();
    }

    public StringProperty usernameProperty() {
        return username;
    }

    public void setUsername(String username) {
        this.username.set(username);
    }

    public String getPassword() {
        return password.get();
    }

    public StringProperty passwordProperty() {
        return password;
    }

    public void setPassword(String password) {
        this.password.set(password);
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

    public int getAccountType() {
        return accountType.get();
    }

    public IntegerProperty accountTypeProperty() {
        return accountType;
    }

    public void setAccountType(int accountType) {
        this.accountType.set(accountType);
    }
}
