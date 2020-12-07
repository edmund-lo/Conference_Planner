package admin;

import common.IPresenter;
import javafx.event.ActionEvent;
import model.LoginLog;
import model.UserAccount;
import java.util.List;

public interface IUnlockAccountsPresenter extends IPresenter {
    void unlockButtonAction(ActionEvent actionEvent);
    void setResultText(String resultText, String status);
    List<UserAccount> getUserAccounts();
    void displayUserAccounts(List<UserAccount> accounts);
    void displayUserAccountDetails(UserAccount account);
    List<LoginLog> getUserLoginLogs(String username);
    void displayUserLoginLogs(List<LoginLog> logs);
}
