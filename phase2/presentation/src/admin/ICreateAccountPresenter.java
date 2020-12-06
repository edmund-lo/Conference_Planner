package admin;

import common.IPresenter;
import javafx.event.ActionEvent;

public interface ICreateAccountPresenter extends IPresenter {
    void createAccountButtonAction(ActionEvent actionEvent);
    void setResultText(String resultText, String status);
}
