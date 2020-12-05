package login;

import common.IView;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TitledPane;
import javafx.scene.text.Text;

public interface IForgotView extends IView {
    String getUsername();
    void setUsername(String username);
    String getSecurityQuestion();
    void setSecurityQuestion(String securityQuestion);
    String getSecurityAnswer();
    void setSecurityAnswer(String securityAnswer);
    String getPassword();
    void setPassword(String password);
    String getConfirmPassword();
    void setConfirmPassword(String password);
    void setResultText(String resultText, int index);

    PasswordField getSecurityAnswerField();
    Text getResultTextControl(int index);
    TitledPane getTitledPane(int index);

    EventHandler<ActionEvent> getFirstBackButtonAction();
    void setFirstBackButtonAction(EventHandler<ActionEvent> eventHandler);
    EventHandler<ActionEvent> getSecondBackButtonAction();
    void setSecondBackButtonAction(EventHandler<ActionEvent> eventHandler);
    EventHandler<ActionEvent> getPreviousButtonAction();
    void setPreviousButtonAction(EventHandler<ActionEvent> eventHandler);
    EventHandler<ActionEvent> getSecurityButtonAction();
    void setSecurityButtonAction(EventHandler<ActionEvent> eventHandler);
    EventHandler<ActionEvent> getConfirmButtonAction();
    void setConfirmButtonAction(EventHandler<ActionEvent> eventHandler);
    EventHandler<ActionEvent> getResetButtonAction();
    void setResetButtonAction(EventHandler<ActionEvent> eventHandler);
}
