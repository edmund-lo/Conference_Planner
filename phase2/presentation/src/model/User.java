package model;

import common.Selectable;
import javafx.beans.property.*;
import javafx.scene.control.CheckBox;

public class User extends Selectable {
    private final StringProperty username;
    private final StringProperty firstName;
    private final StringProperty lastName;
    private final IntegerProperty userType;
    private final BooleanProperty vip;

    public User() {
        this(null, null, null, 0, false);
    }

    public User(String username, String firstName, String lastName, int userType, boolean vip) {
        setChecked(false);
        this.username = new SimpleStringProperty(username);
        this.firstName = new SimpleStringProperty(firstName);
        this.lastName = new SimpleStringProperty(lastName);
        this.userType = new SimpleIntegerProperty(userType);
        this.vip = new SimpleBooleanProperty(vip);
    }

    public String getUsername() {
        return username.get();
    }

    public StringProperty usernameProperty() { return username; }

    public void setUsername(String username) { this.username.set(username); }

    public String getFirstName() {
        return firstName.get();
    }

    public StringProperty firstNameProperty() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName.set(firstName);
    }

    public String getLastName() {
        return lastName.get();
    }

    public StringProperty lastNameProperty() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName.set(lastName);
    }

    public int getUserType() {
        return userType.get();
    }

    public IntegerProperty userTypeProperty() {
        return userType;
    }

    public void setUserType(int userType) {
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
