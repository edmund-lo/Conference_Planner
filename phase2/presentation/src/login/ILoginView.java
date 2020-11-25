package login;

import common.IView;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public interface ILoginView extends IView {
    String getUsername();
    void setUsername(String username);
    String getPassword();
    void setPassword(String password);
    void setLoginErrorMsg(String error);

    EventHandler<ActionEvent> getLoginButtonAction();
    void setLoginButtonAction(EventHandler<ActionEvent> eventHandler);
    EventHandler<ActionEvent> getRegisterButtonAction();
    void setRegisterButtonAction(EventHandler<ActionEvent> eventHandler);
    EventHandler<ActionEvent> getForgotPasswordButtonAction();
    void setForgotPasswordButtonAction(EventHandler<ActionEvent> eventHandler);
}
