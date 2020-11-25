package model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class User {
    private final StringProperty firstName;
    private final StringProperty lastName;
    private final IntegerProperty userType;

    public User() {
        this(null, null, 0);
    }

    public User(String firstName, String lastName, int userType) {
        this.firstName = new SimpleStringProperty(firstName);
        this.lastName = new SimpleStringProperty(lastName);
        this.userType = new SimpleIntegerProperty(userType);
    }

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
}
