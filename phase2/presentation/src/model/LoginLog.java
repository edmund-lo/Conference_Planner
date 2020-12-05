package model;

import javafx.beans.property.*;
import java.time.LocalDateTime;

public class LoginLog {
    private final StringProperty username;
    private final ObjectProperty<LocalDateTime> loginTime;
    private final BooleanProperty success;

    public LoginLog() { this(null, null, false); }

    public LoginLog(String username, LocalDateTime loginTime, boolean success) {
        this.username = new SimpleStringProperty(username);
        this.loginTime = new SimpleObjectProperty<>(loginTime);
        this.success = new SimpleBooleanProperty(success);
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

    public LocalDateTime getLoginTime() {
        return loginTime.get();
    }

    public ObjectProperty<LocalDateTime> loginTimeProperty() {
        return loginTime;
    }

    public void setLoginTime(LocalDateTime loginTime) {
        this.loginTime.set(loginTime);
    }

    public boolean isSuccess() {
        return success.get();
    }

    public BooleanProperty successProperty() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success.set(success);
    }
}
