package model;

import common.Selectable;
import javafx.beans.property.*;

public class User extends Selectable {
    private final StringProperty username;
    private final StringProperty firstName;
    private final StringProperty lastName;
    private final StringProperty userType;
    private final BooleanProperty vip;

    public User() {
        this(null, null, null, null, false);
    }

    public User(String username, String firstName, String lastName, String userType, boolean vip) {
        super();
        this.username = new SimpleStringProperty(username);
        this.firstName = new SimpleStringProperty(firstName);
        this.lastName = new SimpleStringProperty(lastName);
        this.userType = new SimpleStringProperty(userType);
        this.vip = new SimpleBooleanProperty(vip);
    }

    public String getUsername() {
        return username.get();
    }

    public void setUsername(String username) { this.username.set(username); }

    public String getFirstName() {
        return firstName.get();
    }

    public void setFirstName(String firstName) {
        this.firstName.set(firstName);
    }

    public String getLastName() {
        return lastName.get();
    }

    public void setLastName(String lastName) {
        this.lastName.set(lastName);
    }

    public String getUserType() {
        return userType.get();
    }

    public void setUserType(String userType) {
        this.userType.set(userType);
    }

    public boolean isVip() {
        return vip.get();
    }

    public BooleanProperty vipProperty() {
        return vip;
    }

    public void setVip(boolean vip) {
        this.vip.set(vip);
    }
}
