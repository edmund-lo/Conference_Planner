package model;

import javafx.beans.property.*;

public class UserAccount {
    private final StringProperty username;
    private final StringProperty password;
    private final BooleanProperty locked;
    private final IntegerProperty accountType;
    private final BooleanProperty setSecurity;

    public UserAccount() {
        this(null, null, 0, false, false);
    }

    public UserAccount(String username, String password, int accountType, boolean locked, boolean setSecurity) {
        this.username = new SimpleStringProperty(username);
        this.password = new SimpleStringProperty(password);
        this.accountType = new SimpleIntegerProperty(accountType);
        this.locked = new SimpleBooleanProperty(locked);
        this.setSecurity = new SimpleBooleanProperty(setSecurity);
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

    public int getAccountType() {
        return accountType.get();
    }

    public IntegerProperty accountTypeProperty() {
        return accountType;
    }

    public void setAccountType(int accountType) {
        this.accountType.set(accountType);
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

    public BooleanProperty setSecurityProperty() {
        return setSecurity;
    }

    public void setSetSecurity(boolean setSecurity) {
        this.setSecurity.set(setSecurity);
    }
}
