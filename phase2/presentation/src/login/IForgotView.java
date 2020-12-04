package login;

import common.IView;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

public interface IForgotView extends IView {
    String getUsername();
    void setUsername(String username);
    String getSecurityQuestion();
    void setSecurityQuestion(String securityQuestion);
    String getSecurityAnswer();
    void setSecurityAnswer(String securityAnswer);
    void setResultText(String resultText);

    VBox getPasswordRegion();

    EventHandler<ActionEvent> getBackButtonAction();
    void setBackButtonAction(EventHandler<ActionEvent> eventHandler);
    EventHandler<ActionEvent> getRecoverButtonAction();
    void setRecoverButtonAction(EventHandler<ActionEvent> eventHandler);
}
