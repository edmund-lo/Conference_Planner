package common;

import model.UserAccount;

public class UserAccountHolder {
    private UserAccount userAccount;
    private final static UserAccountHolder Instance = new UserAccountHolder();

    private UserAccountHolder() {}

    public static UserAccountHolder getInstance() { return Instance; }

    public void setUserAccount(UserAccount ua) {
        this.userAccount = ua;
    }

    public UserAccount getUserAccount() {
        return this.userAccount;
    }
}
