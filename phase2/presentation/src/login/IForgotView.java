package login;

import common.IView;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public interface IForgotView extends IView {
    String getUsername();
    void setUsername(String username);
    String getPromptText();
    void setPromptText(String promptText);
    String getPromptInput();
    void setPromptInput(String promptInput);
    void setResultMsg(String result);
    TextField getUsernameField();
    PasswordField getPromptInputField();

    EventHandler<ActionEvent> getBackButtonAction();
    void setBackButtonAction(EventHandler<ActionEvent> eventHandler);
    EventHandler<ActionEvent> getRecoverButtonAction();
    void setRecoverButtonAction(EventHandler<ActionEvent> eventHandler);
}
