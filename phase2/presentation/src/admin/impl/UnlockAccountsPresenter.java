package admin.impl;

import admin.IUnlockAccountsPresenter;
import admin.IUnlockAccountsView;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.scene.control.cell.PropertyValueFactory;
import model.LoginLog;
import model.UserAccount;
import util.DateTimeUtil;

import java.util.List;

public class UnlockAccountsPresenter implements IUnlockAccountsPresenter {
    private IUnlockAccountsView view;

    public UnlockAccountsPresenter(IUnlockAccountsView view) {
        this.view = view;
        init();
    }

    @Override
    public void unlockButtonAction(ActionEvent actionEvent) {
        clearResult();

        //call ac.unlockAccount method
    }

    @Override
    public void setResultText(String resultText) {
        this.view.setResultText(resultText);
    }

    @Override
    public List<UserAccount> getUserAccounts() {
        return null;
    }

    @Override
    public void displayUserAccounts(List<UserAccount> accounts) {
        this.view.getUsernameColumn().setCellValueFactory(new PropertyValueFactory<>("username"));
        this.view.getUserTypeColumn().setCellValueFactory(new PropertyValueFactory<>("userType"));
        this.view.getLockedColumn().setCellValueFactory(param -> param.getValue().lockedProperty());
        this.view.getUserTable().setItems(FXCollections.observableList(accounts));
        this.view.getUserTable().getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> handleSelect(newValue));
    }

    @Override
    public void displayUserAccountDetails(UserAccount account) {
        this.view.setUsername(account.getUsername());
        this.view.setUserType(account.getUserType());
    }

    @Override
    public List<LoginLog> getUserLoginLogs(String username) {
        return null;
    }

    @Override
    public void displayUserLoginLogs(List<LoginLog> logs) {
        DateTimeUtil.getInstance().setLoginDateTimeCellFactory(this.view.getLoginTimeColumn());
        this.view.getLoginTimeColumn().setCellValueFactory(new PropertyValueFactory<>("loginTime"));
        this.view.getSuccessColumn().setCellValueFactory(param -> param.getValue().successProperty());
        this.view.getLogsTable().setItems(FXCollections.observableList(logs));
    }

    @Override
    public void init() {
        this.view.setUnlockButtonAction(this::unlockButtonAction);
        List<UserAccount> accounts = getUserAccounts();
        displayUserAccounts(accounts);
    }

    private void clearResult() {
        this.view.setResultText("");
    }

    private void handleSelect(UserAccount account) {
        getUserLoginLogs(account.getUsername());
        displayUserAccountDetails(account);
    }
}
