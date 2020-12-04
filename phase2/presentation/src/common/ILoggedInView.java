package common;

public interface ILoggedInView extends IView {
    String getSessionUsername();
    void setSessionUsername(String username);
    int getSessionUserType();
    void setSessionUserType(int userType);
}
