package organizer;

import common.IView;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public interface ICreateAccountView extends IView {
    String getUserType();
    void setUserType(String userType);
    String getUsername();
    void setUsername(String username);
    String getFirstName();
    void setFirstName(String firstName);
    String getLastName();
    void setLastName(String lastName);
    String getPassword();
    void setPassword(String password);
    String getConfirmPassword();
    void setConfirmPassword(String password);
    void setResultMsg(String result);
    TextField getUsernameField();
    TextField getFirstNameField();
    TextField getLastNameField();
    PasswordField getPasswordField();
    PasswordField getConfirmPasswordField();

    EventHandler<ActionEvent> getCreateAccountButtonAction();
    void setCreateAccountButtonAction(EventHandler<ActionEvent> eventHandler);
}
